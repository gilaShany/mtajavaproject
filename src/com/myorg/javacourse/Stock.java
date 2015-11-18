package com.myorg.javacourse;

import java.util.Date;

public class Stock {
	
private String symbol;
private float ask; //ערך הקנייה של המנייה
private float bid; //ערך המכירה של המנייה
private Date date;
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
final String result = "Symbol= " + this.symbol + " Ask= " + this.ask + " Bid= " + this.bid + " Date= " + this.date;
return result;
}

}


