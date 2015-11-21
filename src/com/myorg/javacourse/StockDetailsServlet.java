package com.myorg.javacourse;

import java.io.IOException;
import java.util.Date;
import com.myorg.javacourse.Stock.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class StockDetailsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
	
		Stock stock1 = new Stock();
		Stock stock2 = new Stock();
		Stock stock3 = new Stock();
		Date date = new Date (2014,11,15);
		
		stock1.setSymbol("PIH");
		stock1.setAsk((float) 13.1);
		stock1.setBid((float) 12.4);
		stock1.setDate(date);
		
		stock2.setSymbol("ALL");
		stock2.setAsk((float) 5.78);
		stock2.setBid((float) 5.5);
		stock2.setDate(date);
		
		stock3.setSymbol("CAAS");
		stock3.setAsk((float) 32.2);
		stock3.setBid((float) 31.5);
		stock3.setDate(date);
		
		String resultStr = stock1.getHtmlDescription() + "<br>" + stock2.getHtmlDescription() + "<br>" + stock3.getHtmlDescription();
		resp.getWriter().println(resultStr);
		
	}	
}

