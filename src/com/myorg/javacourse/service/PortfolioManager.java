package com.myorg.javacourse.service;

import java.util.Date;

import com.myorg.javacourse.model.Portfolio;
import com.myorg.javacourse.model.Stock;
/**
 * this class manages the portfolio
 * @author Shany & Gila
 *
 */
public class PortfolioManager {
	
	/**
	 * This method returns a portfolio
	 * @return portfolio
	 */
	public Portfolio getPortfolio() {
		Portfolio portfolio = new Portfolio();
		portfolio.setTitle("Portfolio #1");
		
		Stock stock1 = new Stock();
		Stock stock2 = new Stock();
		Stock stock3 = new Stock();
		
		Date date1 = new Date(114, 10, 15);
		Date date2 = new Date (114,10,15);
		Date date3 = new Date (114,10,15);
		
		stock1.setSymbol("PIH");
		stock1.setAsk((float) 13.1);
		stock1.setBid((float) 12.4);
		stock1.setDate(date1);
		portfolio.addStock(stock1);
		
		stock2.setSymbol("ALL");
		stock2.setAsk((float) 5.78);
		stock2.setBid((float) 5.5);
		stock2.setDate(date2);
		portfolio.addStock(stock2);
		
		stock3.setSymbol("CAAS");
		stock3.setAsk((float) 32.2);
		stock3.setBid((float) 31.5);
		stock3.setDate(date3);
		portfolio.addStock(stock3);
		
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

}
