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
