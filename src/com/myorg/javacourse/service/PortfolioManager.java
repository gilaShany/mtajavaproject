package com.myorg.javacourse.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.algo.dto.PortfolioDto;
import org.algo.dto.PortfolioTotalStatus;
import org.algo.dto.StockDto;
import org.algo.exception.PortfolioException;
import org.algo.exception.SymbolNotFoundInNasdaq;
import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;
import org.algo.service.MarketService;
import org.algo.service.PortfolioManagerInterface;
import org.algo.service.ServiceManager;

import com.google.appengine.api.datastore.DatastoreService;
import com.myorg.javacourse.exception.BalanceException;
import com.myorg.javacourse.exception.PortfolioFullException;
import com.myorg.javacourse.exception.StockAlreadyExistsException;
import com.myorg.javacourse.exception.StockNotExistException;
import com.myorg.javacourse.model.Portfolio;
import com.myorg.javacourse.model.Stock;
import com.myorg.javacourse.model.Portfolio.ALGO_RECOMMENDATION;
/**
 * this class manages the portfolio
 * @author Shany & Gila
 *
 */
public class PortfolioManager implements PortfolioManagerInterface{
	
	private org.algo.service.DatastoreService datastoreService = ServiceManager.datastoreService();
	/**
	 * This method returns a portfolio
	 * @return portfolio
	 */
	public PortfolioInterface getPortfolio() {
		PortfolioDto portfolioDto = ((org.algo.service.DatastoreService) datastoreService).getPortfolilo();
		return fromDto(portfolioDto);
	}

	/**
	 * Update portfolio with stocks
	 */
	@Override
	public void update() {
		StockInterface[] stocks = getPortfolio().getStocks();
		List<String> symbols = new ArrayList<>(Portfolio.getMaxSize());
		for (StockInterface si : stocks) {
			symbols.add(si.getSymbol());
		}

		List<Stock> update = new ArrayList<>(Portfolio.getMaxSize());
		List<Stock> currentStocksList = new ArrayList<Stock>();
		try {
			List<StockDto> stocksList = MarketService.getInstance().getStocks(symbols);
			for (StockDto stockDto : stocksList) {
				Stock stock = fromDto(stockDto);
				currentStocksList.add(stock);
			}

			for (Stock stock : currentStocksList) {
				update.add(new Stock(stock));
			}

			((org.algo.service.DatastoreService) datastoreService).saveToDataStore(toDtoList(update));

		} catch (SymbolNotFoundInNasdaq e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * get portfolio totals
	 */
	@Override
	public PortfolioTotalStatus[] getPortfolioTotalStatus () {

		Portfolio portfolio = (Portfolio) getPortfolio();
		Map<Date, Float> map = new HashMap<>();

		//get stock status from db.
		StockInterface[] stocks = portfolio.getStocks();
		for (int i = 0; i < stocks.length; i++) {
			StockInterface stock = stocks[i];

			if(stock != null) {
				List<StockDto> stockHistory = null;
				try {
					stockHistory = ((org.algo.service.DatastoreService) datastoreService).getStockHistory(stock.getSymbol(),30);
				} catch (Exception e) {
					return null;
				}
				for (StockDto stockDto : stockHistory) {
					Stock stockStatus = fromDto(stockDto);
					float value = stockStatus.getBid()*stockStatus.getStockQuantity();

					Date date = stockStatus.getDate();
					Float total = map.get(date);
					if(total == null) {
						total = value;
					}else {
						total += value;
					}

					map.put(date, value);
				}
			}
		}

		PortfolioTotalStatus[] ret = new PortfolioTotalStatus[map.size()];

		int index = 0;
		//create dto objects
		for (Date date : map.keySet()) {
			ret[index] = new PortfolioTotalStatus(date, map.get(date));
			index++;
		}

		//sort by date ascending.
		Arrays.sort(ret);

		return ret;
	}

	/**
	 * Add stock to portfolio 
	 */
	@Override
	public void addStock(String symbol)throws  StockAlreadyExistsException, PortfolioFullException {
		Portfolio portfolio = (Portfolio) getPortfolio();

		try {
			StockDto stockDto = ServiceManager.marketService().getStock(symbol);
			
			//get current symbol values from nasdaq.
			Stock stock = fromDto(stockDto);
			
			//first thing, add it to portfolio.
			portfolio.addStock(stock); 
			//or:
			//portfolio.addStock(stock);   

			//second thing, save the new stock to the database.
			((org.algo.service.DatastoreService) datastoreService).saveStock(toDto(portfolio.findStock(symbol)));
			
			flush(portfolio);
		} catch (SymbolNotFoundInNasdaq e) {
			System.out.println("Stock Not Exists: "+symbol);
		}
		catch (StockAlreadyExistsException saee){
			throw saee;
		}
		catch (PortfolioFullException pfe){
			throw pfe;
		}
	}

	/**
	 * update database with new portfolio's data
	 * @param portfolio
	 */
	private void flush(Portfolio portfolio) {
		((org.algo.service.DatastoreService) datastoreService).updatePortfolio(toDto(portfolio));
	}

	/**
	 * fromDto - get stock from Data Transfer Object
	 * @param stockDto
	 * @return Stock
	 */
	private Stock fromDto(StockDto stockDto) {
		Stock newStock = new Stock();

		newStock.setSymbol(stockDto.getSymbol());
		newStock.setAsk(stockDto.getAsk());
		newStock.setBid(stockDto.getBid());
		newStock.setDate(stockDto.getDate());
		newStock.setStockQuantity(stockDto.getQuantity());
		if(stockDto.getRecommendation() != null) newStock.setRecommendation(Portfolio.ALGO_RECOMMENDATION.valueOf(stockDto.getRecommendation()));

		return newStock;
	}

	/**
	 * toDto - covert Stock to Stock DTO
	 * @param inStock
	 * @return
	 */
	private StockDto toDto(StockInterface inStock) {
		if (inStock == null) {
			return null;
		}
		
		Stock stock = (Stock) inStock;
		
		if(stock.getRecommendation() == null) {
			stock.setRecommendation(ALGO_RECOMMENDATION.HOLD);
		}
			
		return new StockDto(stock.getSymbol(), stock.getAsk(), stock.getBid(), 
				stock.getDate(), stock.getStockQuantity(), stock.getRecommendation().name());
	}
	/**
	 * toDto - converts Portfolio to Portfolio DTO
	 * @param portfolio
	 * @return
	 */
	private PortfolioDto toDto(Portfolio portfolio) {
		StockDto[] array = null;
		StockInterface[] stocks = portfolio.getStocks();
		if(stocks != null) {
			array = new StockDto[stocks.length];
			for (int i = 0; i < stocks.length; i++) {
				array[i] = toDto(stocks[i]);
			}
		}
		return new PortfolioDto(portfolio.getTitle(), portfolio.getBalance(), array);
	}

	/**
	 * fromDto - converts portfolioDto to Portfolio
	 * @param dto
	 * @return portfolio
	 */
	private Portfolio fromDto(PortfolioDto dto) {
		StockDto[] stocks = dto.getStocks();
		Portfolio ret;
		if(stocks == null) {
			ret = new Portfolio();			
		}else {
			List<Stock> stockList = new ArrayList<Stock>();
			for (StockDto stockDto : stocks) {
				stockList.add(fromDto(stockDto));
			}
			
			Stock[] stockArray = stockList.toArray(new Stock[stockList.size()]);
			ret = new Portfolio(stockArray);
		}

		ret.setTitle(dto.getTitle());
	
		try {
			ret.updateBalance(dto.getBalance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}	

	/**
	 * toDtoList - convert List of Stocks to list of Stock DTO
	 * @param stocks
	 * @return stockDto
	 */
	private List<StockDto> toDtoList(List<Stock> stocks) {

		List<StockDto> ret = new ArrayList<StockDto>();

		for (Stock stockStatus : stocks) {
			ret.add(toDto(stockStatus));
		}

		return ret;
	}
	
	public void setTitle(String title) {
		Portfolio portfolio = (Portfolio) getPortfolio();
		portfolio.setTitle(title);
		flush(portfolio);
		
	}
	
	/** 
	 * This method updates the portfolios Balance 
	 * @param amount the amount you want to remove or add from portfolio Balance 
	 */
	public void updateBalance(float value) throws BalanceException {
		try{
			Portfolio portfolio = (Portfolio) getPortfolio();
			portfolio.updateBalance(value);
			flush(portfolio);
		}
		catch (BalanceException be){
			throw be;
		}
	}
	
	/**
	 * This method buys stock
	 */
	public void buyStock(String symbol, int quantity) throws StockAlreadyExistsException, PortfolioFullException, BalanceException {
		try{
			Portfolio portfolio =(Portfolio) getPortfolio();
			portfolio.buyStock((Stock)portfolio.findStock(symbol), quantity);
			flush(portfolio);
		}
		catch (StockAlreadyExistsException saee){
			throw saee;
		}
		catch (PortfolioFullException pfe){
			throw pfe;
		}
		catch (BalanceException be){
			throw be;
		}
	}
	
	/**
	 * This method sells stock
	 */
	public void sellStock(String symbol, int quantity) throws StockNotExistException,BalanceException {
		try{
			Portfolio portfolio =(Portfolio) getPortfolio();
			portfolio.sellStock(symbol, quantity);
			flush(portfolio);
		}
		catch (BalanceException be){
			throw be;
		}
		catch (StockNotExistException snee){
			throw snee;
		}
	}
	
	/**
	 * This method removes stock from portfolio
	 */
	public void removeStock(String symbol) throws StockNotExistException, BalanceException {
		try{
			Portfolio portfolio =(Portfolio) getPortfolio();
			portfolio.removeStock(symbol);
			flush(portfolio);
		}
		catch(StockNotExistException snee){
			throw snee;
		}
		catch (BalanceException be){
			throw be;
		}
	}
}
