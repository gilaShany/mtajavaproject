package com.myorg.javacourse.model;

import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;

import com.myorg.javacourse.exception.BalanceException;
import com.myorg.javacourse.exception.StockAlreadyExistsException;
import com.myorg.javacourse.exception.StockNotExistException;

/**
 * This class presents the portfolio
 * @author Shany & Gila
 *
 */

public class Portfolio implements PortfolioInterface {
	
	private	static final int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	private StockInterface[] stocks;
	
	public int portfolioSize; 
	private float balance;
	public enum ALGO_RECOMMENDATION {BUY, SELL, REMOVE, HOLD};
	
	/**
	 * Portfolio constractor	
	 */
	public Portfolio() {
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.balance=0;
		portfolioSize=0;
	}
	
	/**
	 * Portfolio constractor	
	 * @param array of stocks
	 */
	public Portfolio(StockInterface[] stock) {
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		int counter = 0;
		for (int i=0 ; i<stock.length ; i++){
			this.stocks[i] = stock[i];
			counter++;
		}
		
		this.portfolioSize= counter;
	}
	
	/**
	 * Portfolio constractor 
	 * @param title the name of the portfolio, portfolioSize the size of the portfolio
	 */
	public Portfolio(String title, int portfolioSize ){
		this.portfolioSize = portfolioSize;
		this.title = title;
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
	}
	
	/**
	 * Portfolio copy constractor
	 * @param p the copied portfolio
	 */
	public Portfolio(Portfolio p){
		this (p.getTitle(), p.getportfolioSize());
		for (int i=0; i < p.portfolioSize; i++){
			this.stocks[i] = new Stock (p.getStocks()[i]); 
		}		
	}
	
	/**
	 * This method updates the portfolios Balance 
	 * @param amount the amount you want to remove or add from portfolio Balance 
	 */
	
	public void updateBalance(float amount) throws BalanceException{
		if (((amount < 0) && ((-amount) < this.balance)) || (amount > 0)){
			this.balance += amount;
		}
		else{ 
			System.out.println("Cant add this amount");
		}	
	}
	
	/**
	 * This method adds a stock to the portfolio stocks array
	 * @param stock the stock you want to add
	 * @return success or fail
	 */
	public void addStock (Stock stock) throws StockAlreadyExistsException{
		boolean inTheStocks = false;
		for (int i=0 ; i < this.portfolioSize && !inTheStocks; i++){
			if (stock.getSymbol().equals(this.stocks[i].getSymbol())){
				inTheStocks = true;
			}
		}
		if (!inTheStocks){
			if (this.portfolioSize > MAX_PORTFOLIO_SIZE-1){
				System.out.println("Cant add new stock, portfolio can have only " + MAX_PORTFOLIO_SIZE + " stocks");
				//return false;
			}
			else{
				this.stocks[portfolioSize]= stock;
				((Stock) this.stocks[portfolioSize]).setStockQuantity(0);
				portfolioSize++;	
				//return true;
			}	
		}
		//return false;
	}
	
	/**
	 *	This method removes a stock from the portfolio stocks array
	 * @param symbol the symbol of the stock you want to remove
	 * @return success or fail
	 */
	public void removeStock(String symbol) throws StockNotExistException{
		int i=0;
		while (!(symbol.equals(this.stocks[i].getSymbol()))){
			i++;
		}
		boolean didItWentWell;
		didItWentWell = sellStock(symbol,-1);
		
		if (didItWentWell){
			if (this.portfolioSize == 1){
				this.stocks[0] = null;
				portfolioSize=0;
			}
			else{
				int j;
				for (j = i ; j < this.portfolioSize-1 ; j++){
				this.stocks[j] = this.stocks[j+1];
				}
			this.stocks[j] = null;
			this.portfolioSize --;
			}
			//return true;
		}
		else{
		//	return false;
		}
	}

	/**
	 * This method sells an amount of a stock from the portfolio 
	 * @param symbol the symbol of the stock you want to sell, quantity the quantity you want to  sell
	 * @return success or fail
	 */
	public void sellStock(String symbol, int quantity) throws StockNotExistException{
		int i=0;
		while (!symbol.equals(((Stock)this.stocks[i]).getSymbol())){
			i++;
		}
		if (((Stock) this.stocks[i]).getStockQuantity() != 0){
			if (quantity == -1){
				updateBalance(((Stock) this.stocks[i]).getStockQuantity()* this.stocks[i].getBid());
				((Stock) this.stocks[i]).setStockQuantity(0);
			//	return true;
			}
			else if ((quantity < -1)){
			//	return false;
			}
			else if ((((Stock) this.stocks[i]).getStockQuantity() < quantity)){
				System.out.println("Not enough stocks to sell");
			//	return false;
			}
			else{
				updateBalance ((quantity* this.stocks[i].getBid()));
				((Stock)this.stocks[i]).setStockQuantity((((Stock)this.stocks[i]).getStockQuantity()-quantity)); 
			//	return true;
			}
		}
		//else
			//return true;	
	}
	
	/**
	 * This method buys an amount of a stock 
	 * @param stock the stock you want to buy, quantity the quantity you want to  buy
	 * @return success or fail
	 */
	public void buyStock(Stock stock, int quantity) throws StockAlreadyExistsException{
		boolean wasFound = false;
		int i=0;
		
		for (i=0 ; i < this.portfolioSize && !wasFound ; i++){
			if (stock.getSymbol().equals(this.stocks[i].getSymbol())){
				wasFound = true;
			}
		}
		
		if (!wasFound){
			boolean add = addStock(stock);
			if (!add){
			//	return false;
			}
		}
		else{
			i--;	
		}
		if (quantity == -1){
			int numOfStocksYouCanBuy;
			numOfStocksYouCanBuy =  (int)(this.balance/stock.getAsk());
			((Stock) this.stocks[i]).setStockQuantity(((Stock) this.stocks[i]).getStockQuantity()+numOfStocksYouCanBuy);
			updateBalance(-(numOfStocksYouCanBuy * this.stocks[i].getAsk()));
			return true;
		}
		else if (this.balance < (quantity * this.stocks[i].getAsk())){
			System.out.println("Not enougt balance to complete purchase");
			return false;
		}
		else
		{
			((Stock) this.stocks[i]).setStockQuantity(((Stock) this.stocks[i]).getStockQuantity()+ quantity);
			updateBalance(- (quantity * this.stocks[i].getAsk()));
			return true;
		}	
	}

	public int getportfolioSize() {
		return portfolioSize;
	}

	public void setportfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}
	
	public StockInterface [] getStocks(){
		return (Stock[]) this.stocks;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public static int getMaxSize() {
		return MAX_PORTFOLIO_SIZE;
	}
	public float getBalance() {
		return this.balance;
	}
	
	/**
	 * This method calcultaes the portfolio stocks value
	 * @return the portfolio stocks value
	 */
	public float getStockValue (){
		float sum =0;
		for (int i=0 ; i < this.portfolioSize; i++){
			sum
			
		+= (this.stocks[i].getBid() * ((Stock) this.stocks[i]).getStockQuantity());
		}
		return sum;
	}
	
	/**
	 * This method calcultaes the portfolio total value
	 * @return the portfolio stocks value plus the balance
	 */
	public float getTotalValue(){
		return (getStockValue()+ getBalance());
	}
	/**
	 * This method finds a stock
	 * @param symbol
	 * @return stock
	 */
	public StockInterface findStock(String symbol) {
		int i = 0;
		while (!symbol.equals(stocks[i].getSymbol()) && (i < MAX_PORTFOLIO_SIZE)){
			i++;
		}
		if (i >= MAX_PORTFOLIO_SIZE)
			return null;
		else
			return stocks[i];
	} 
	
	/**
	 * This method returns a string of stocks in the portfolio
	 * @return the string description of stocks in the portfolio
	 */
	public String getHtmlString () {
		String str = "<h1>" + this.title + "</h1>";
		for (int i=0; i< this.portfolioSize; i++){
			str += "<br>"+ ((Stock) this.stocks[i]).getHtmlDescription();
		}
		str += "<br>"+ "Total Portfolio Value: " + getTotalValue() + " $, Total Stocks Value: "
				+ getStockValue () + " $, Balance:" + getBalance() + "$." + "</br>";
		return str;
	}
}