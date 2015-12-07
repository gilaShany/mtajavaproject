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
	
	/**
	 * portfolio constractors	
	 */
	public Portfolio() {
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];	
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
	
	public int getProtfolioSize() {
		return protfolioSize;
	}

	public void setProtfolioSize(int protfolioSize) {
		this.protfolioSize = protfolioSize;
	}

	public void addStock (Stock stock){
		this.stocks[protfolioSize]= stock;
		protfolioSize++;
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
	 * This method returns a string of stocks in the portfolio
	 * @return the string description of stocks in the portfolio
	 */
	public String getHtmlString () {
		String str = "<h1>" + this.title + "</h1>";
		for (int i=0; i< this.protfolioSize; i++){
			str += "<br>"+ this.stocks[i].getHtmlDescription();
		}
		return str;
	}

}
