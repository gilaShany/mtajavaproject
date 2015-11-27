package com.myorg.javacourse;

import java.util.Date;
import java.text.*;

import com.google.api.server.spi.types.SimpleDate;

public class Stock {
	
private String symbol;
private float ask; 
private float bid;
private Date date;
private int recommendation;
private int stockQuantity;
private  final static int BUY =0;
private final static int SELL = 1;
private final static int REMOVE = 2;
private final static int HOLD = 3;

public Stock(){
	this.symbol = "unknown";
	this.ask = 0;
	this.bid = 0;
	this.date = new Date();
	
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

public  String getHtmlDescription() {
	SimpleDateFormat sDate = new SimpleDateFormat("M/d/yyyy");
	 String result = "<b>Stock Symbol= </b>" + getSymbol() + "<b> Ask= </b>" + getAsk() + "<b> Bid= </b>" + getBid() +
		 		" <b>Date= </b>"+ sDate.format(getDate());
return result;
}

}

