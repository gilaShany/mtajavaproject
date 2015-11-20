package com.myorg.javacourse;

import java.util.Date;
import java.text.*;

import com.google.api.server.spi.types.SimpleDate;

public class Stock {
	
private String symbol;
private float ask; //ערך הקנייה של המנייה
private float bid; //ערך המכירה של המנייה
private static Date date;

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
	SimpleDateFormat sDate = new SimpleDateFormat("MM/dd/yyyy");
 String result = "<b>Stock Symbol= </b>" + getSymbol() + "<b> Ask= </b>" + getAsk() + "<b> Bid= </b>" + getBid() +
		 		" <b>Date= </b>"+ sDate.format(getDate());
return result;
}

}


