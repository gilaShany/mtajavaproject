package com.myorg.javacourse.model;

import java.util.Date;
import java.text.*;

import com.google.api.server.spi.types.SimpleDate;
import com.myorg.javacourse.model.Portfolio.ALGO_RECOMMENDATION;

/**
 * This class presents the stocks in the portfolio
 * @author Shany & Gila
 *
 */

public class Stock {
	
	private String symbol;
	private float ask; 
	private float bid;
	private Date date;
	private ALGO_RECOMMENDATION recommendation;
	private int stockQuantity;
	
	/**
	 * stock constractor
	 */
	public Stock(){
		this.symbol = "unknown";
		this.ask = 0;
		this.bid = 0;
		this.stockQuantity=0;
		this.date = date;
		
	}
	/**
	 * stock constractor
	 * @param symbol, ask, bid, date, stockQuantity
	 */
	public Stock(String symbol, float ask, float bid, Date date,int stockQuantity){
		this.symbol = symbol;
		this.ask = ask;
		this.bid = bid;
		this.date = date;
		this.stockQuantity = stockQuantity;
	}

	/**
	 * stock copy constractor
	 * @param s the copied stock
	 */
	public Stock(Stock s){
		this(s.getSymbol(),s.getAsk(),s.getBid(),new Date(s.getDate().getTime()), s.getStockQuantity());
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public float getAsk() {
		return ask;
	}
	
	public void setAsk(float ask) {
		this.ask = ask;
	}
	
	public float getBid() {
		return bid;
	}
	
	public void setBid(float bid) {
		this.bid = bid;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	} 
	
	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	
	/**
	 * This method returns a string of stock
	 * @return the string description of stock
	 */
	public  String getHtmlDescription() {
		SimpleDateFormat sDate = new SimpleDateFormat("M/d/yyyy");
		 String result = "<b>Stock Symbol= </b>" + getSymbol() + "<b> Ask= </b>" + getAsk() + "<b> Bid= </b>" + getBid() +
			 		" <b>Date= </b>"+ sDate.format(getDate()) + "<b> Stock quantity: </b>" + getStockQuantity();
		 return result;
		}

}
