package com.myorg.javacourse;

import java.util.Date;
import java.text.*;

import com.google.api.server.spi.types.SimpleDate;

public class Stock {
	
private String symbol;
private float ask; 
private float bid;
private static Date date;

public Stock(){
	this.symbol = "unknown";
	this.ask = 0;
	this.bid = 0;
	this.date = new Date();
}

public Stock(String symbol, float ask, float bid, Date date){
	this.date = new Date(date.getYear(),date.getMonth(),date.getDate());
	this.symbol = symbol;
	this.ask = ask;
	this.bid = bid;
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
	this.date = new Date(date.getYear(),date.getMonth(),date.getDate()); 
} 

public  String getHtmlDescription() {
	String result = "<b>Stock Symbol= </b>" + getSymbol() + "<b> Ask= </b>" + getAsk() + "<b> Bid= </b>" + getBid() +
					" <b>Date= </b>"+ getDate().getMonth() + "/" + getDate().getDate() + "/" + getDate().getYear();
return result;
}

}


