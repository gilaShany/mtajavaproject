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
	 * portfolio constractors	
	 */
	public Portfolio() {
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.balance=0;
		protfolioSize=0;
	}
	
	public Portfolio(String title, int protfolioSize ){
		this.protfolioSize = protfolioSize;
		this.title = title;
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
	}
	
	/**
	 * portfolio copy constractor
	 * @param p the copied portfolio
	 */
	public Portfolio(Portfolio p){
		this (p.getTitle(), p.getProtfolioSize());
		for (int i=0; i < p.protfolioSize; i++){
			this.stocks[i] = new Stock (p.getStocks()[i]); 
		}		
	}
	
	public void updateBalance(float amount){
		if (((amount < 0) && ((-amount) < this.balance)) || (amount > 0)){
			this.balance += amount;
		}
		else{
			System.out.println("Cant add this amount");
		}	
	}
	
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
	
	public boolean removeStock(String symbol){
		int i=0;
		while (!symbol.equals(stocks[i].getSymbol())){
			i++;
		}
		boolean didItWentWell;
		didItWentWell = sellStock(symbol,-1);
		
		if (didItWentWell){
			for (int j = i+1 ; j < this.protfolioSize-1 ; j++){
				this.stocks[j] = this.stocks[j+1];
			}
			this.protfolioSize --;
			return true;
		}
		else{
			return false;
		}
	}
	
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
	
	public float getStockValue (){
		float sum =0;
		for (int i=0 ; i < this.protfolioSize; i++){
			sum += (this.stocks[i].getAsk() * this.stocks[i].getStockQuantity());
		}
		return sum;
	}
	
	public float getBalance() {
		return balance;
	}
	
	public float getToatlValue(){
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
		str += "<br>"+ "Total Portfolio Value: " + getToatlValue() + " $, Total Stock Value: "
				+ getStockValue () + " $, Balance:" + getBalance() + "$." + "</br>";
		return str;
	} 

}
