package com.myorg.javacourse.model;



public class Portfolio {
	
	private	static final int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	private Stock [] stocks;
	public int protfolioSize; 
	
	public Portfolio() {
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];	
		protfolioSize=0;
	}
	
	public Portfolio(Stock[] stocks, String title){
		this.stocks = stocks;
		this.title = title;
	}
	
	public Portfolio(Portfolio p){
		this(new Stock[p.stocks.length],p.getTitle());
		Stock[] copy = new Stock[MAX_PORTFOLIO_SIZE];
		for (int i=0 ; i< 3 ; i++){
			copy[i]= stocks[i];
		}
		//this.title = p.getTitle();
			
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

	public String getHtmlString () {
		String str = "<h1>" + this.title + "</h1>";
		for (int i=0; i< this.protfolioSize; i++){
			str += "<br>"+ this.stocks[i].getHtmlDescription();
		}
		return str;
	}
	

}
