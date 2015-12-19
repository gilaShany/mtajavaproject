package com.myorg.javacourse.service;

import java.util.Date;

import org.algo.dto.PortfolioTotalStatus;
import org.algo.exception.PortfolioException;
import org.algo.model.PortfolioInterface;
import org.algo.service.PortfolioManagerInterface;

import com.myorg.javacourse.model.Portfolio;
import com.myorg.javacourse.model.Stock;
/**
 * this class manages the portfolio
 * @author Shany & Gila
 *
 */
public class PortfolioManager implements PortfolioManagerInterface{
	
	/**
	 * This method returns a portfolio
	 * @return portfolio
	 */
	public PortfolioInterface getPortfolio() {
		Portfolio myPortfolio = new Portfolio();
		myPortfolio.setTitle("Exercise 7 portfolio");
		myPortfolio.updateBalance(10000f);
		Date date4 = new Date(114, 11, 15);
		Date date5 = new Date(114, 11, 15);
		Date date6 = new Date(114, 11, 15);
		
		Stock stock4 = new Stock("PIH",10.0f,8.5f,date4,0);
		Stock stock5 = new Stock("AAL",30.0f,25.5f,date5,0);
		Stock stock6 = new Stock("CAAS",20.0f,15.5f,date6,0);
		
		myPortfolio.buyStock(stock4,20);
		myPortfolio.buyStock(stock5,30);
		myPortfolio.buyStock(stock6,40);
		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");
		return myPortfolio;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBalance(float value) throws PortfolioException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PortfolioTotalStatus[] getPortfolioTotalStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addStock(String symbol) throws PortfolioException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buyStock(String symbol, int quantity) throws PortfolioException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sellStock(String symbol, int quantity)
			throws PortfolioException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeStock(String symbol) throws PortfolioException {
		// TODO Auto-generated method stub
		
	}

}
