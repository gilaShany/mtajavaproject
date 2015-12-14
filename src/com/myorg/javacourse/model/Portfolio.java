package com.myorg.javacourse.model;

/**
 * This class presents the portfolio
 * @author Shany & Gila
 *
 */

public class Portfolio {
	
	private	static final int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	private Stock [] stocks;
	public int protfolioSize; 
	private float balance;
	public enum ALGO_RECOMMENDATION {BUY, SELL, REMOVE, HOLD};
	
	/**
	 * Portfolio constractor	
	 */
	public Portfolio() {
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.balance=0;
		protfolioSize=0;
	}
	
	/**
	 * Portfolio constractor 
	 * @param title the name of the portfolio, protfolioSize the size of the portfolio
	 */
	public Portfolio(String title, int protfolioSize ){
		this.protfolioSize = protfolioSize;
		this.title = title;
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
	}
	
	/**
	 * Portfolio copy constractor
	 * @param p the copied portfolio
	 */
	public Portfolio(Portfolio p){
		this (p.getTitle(), p.getProtfolioSize());
		for (int i=0; i < p.protfolioSize; i++){
			this.stocks[i] = new Stock (p.getStocks()[i]); 
		}		
	}
	
	/**
	 * Update portfolio Balance 
	 * @param amount the amount you want to remove or add from portfolio Balance 
	 */
	public void updateBalance(float amount){
		if (((amount < 0) && ((-amount) < this.balance)) || (amount > 0)){
			this.balance += amount;
		}
		else{ 
			System.out.println("Cant add this amount");
		}	
	}
	
	/**
	 * Add a stock to portfolio stocks array
	 * @param stock the stock you want to add
	 * @return success or fail
	 */
	public boolean addStock (Stock stock){
		boolean inTheStocks = false;
		for (int i=0 ; i < this.protfolioSize && !inTheStocks; i++){
			if (stock.getSymbol().equals(this.stocks[i].getSymbol())){
				inTheStocks = true;
			}
		}
		if (!inTheStocks){
			if (this.protfolioSize > MAX_PORTFOLIO_SIZE-1){
				System.out.println("Cant add new stock, portfolio can have only " + MAX_PORTFOLIO_SIZE + " stocks");
				return false;
			}
			else{
				this.stocks[protfolioSize]= stock;
				this.stocks[protfolioSize].setStockQuantity(0);
				protfolioSize++;	
				return true;
			}	
		}
		return false;
	}
	
	/**
	 *	Remove a stock from portfolio stocks array
	 * @param symbol the symbol of the stock you want to remove
	 * @return success or fail
	 */
	public boolean removeStock(String symbol){
		int i=0;
		while (!symbol.equals(stocks[i].getSymbol())){
			i++;
		}
		boolean didItWentWell;
		didItWentWell = sellStock(symbol,-1);
		
		if (didItWentWell){
			for (int j = i ; j < this.protfolioSize-1 ; j++){
				this.stocks[j] = this.stocks[j+1];
			}
			this.protfolioSize --;
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Sell a stock from portfolio 
	 * @param symbol the symbol of the stock you want to sell, quantity the quantity you want to  sell
	 * @return success or fail
	 */
	public boolean sellStock(String symbol, int quantity){
		int i=0;
		while (!symbol.equals(stocks[i].getSymbol())){
			i++;
		}
		if (quantity == -1){
			updateBalance(this.stocks[i].getStockQuantity()* this.stocks[i].getBid());
			this.stocks[i].setStockQuantity(0);
			return true;
		}
		else if ((quantity < -1)){
			return false;
		}
		else if ((this.stocks[i].getStockQuantity() < quantity)){
			System.out.println("Not enough stocks to sell");
			return false;
		}
		else{
			updateBalance ((quantity* this.stocks[i].getBid()));
			this.stocks[i].setStockQuantity(this.stocks[i].getStockQuantity()-quantity); 
			return true;
		}
	}
	
	/**
	 * Buy stocks 
	 * @param stock the stock you want to buy, quantity the quantity you want to  buy
	 * @return success or fail
	 */
	public boolean buyStock(Stock stock, int quantity){
		boolean wasFound = false;
		int i=0;
		
		for (i=0 ; i < this.protfolioSize && !wasFound ; i++){
			if (stock.getSymbol().equals(this.stocks[i].getSymbol())){
				wasFound = true;
			}
		}
		
		if (!wasFound){
			boolean add = addStock(stock);
			if (!add){
				return false;
			}
		}
		else
			i--;
		
		if (quantity == -1){
			int numOfStocksYouCanBuy;
			numOfStocksYouCanBuy =  (int)(this.balance/stock.getAsk());
			this.stocks[i].setStockQuantity(this.stocks[i].getStockQuantity()+numOfStocksYouCanBuy);
			updateBalance(-(numOfStocksYouCanBuy * this.stocks[i].getAsk()));
			return true;
		}
		else if (this.balance < (quantity * this.stocks[i].getAsk())){
			System.out.println("Not enougt balance to complete purchase");
			return false;
		}
		else
		{
			this.stocks[i].setStockQuantity(this.stocks[i].getStockQuantity()+ quantity);
			updateBalance(- (quantity * this.stocks[i].getAsk()));
			return true;
		}	
	}
	
	public int getProtfolioSize() {
		return protfolioSize;
	}

	public void setProtfolioSize(int protfolioSize) {
		this.protfolioSize = protfolioSize;
	}
	
	public Stock [] getStocks(){
		return this.stocks;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Calcultae the protfolio stocks value
	 * @return the protfolio stocks value
	 */
	public float getStockValue (){
		float sum =0;
		for (int i=0 ; i < this.protfolioSize; i++){
			sum += (this.stocks[i].getAsk() * this.stocks[i].getStockQuantity());
		}
		return sum;
	}
	
	public float getBalance() {
		return this.balance;
	}
	
	/**
	 * Calcultae the protfolio total value
	 * @return the protfolio stocks value plus the balance
	 */
	public float getTotalValue(){
		return (getStockValue()+ getBalance());
	}

	/**
	 * This method returns a string of stocks in the portfolio
	 * @return the string description of stocks in the portfolio
	 */
	public String getHtmlString () {
		String str = "<h1>" + this.title + "</h1>";
		for (int i=0; i< this.protfolioSize; i++){
			str += "<br>"+ this.stocks[i].getHtmlDescription();
		}
		str += "<br>"+ "Total Portfolio Value: " + getTotalValue() + " $, Total Stocks Value: "
				+ getStockValue () + " $, Balance:" + getBalance() + "$." + "</br>";
		return str;
	} 

}
