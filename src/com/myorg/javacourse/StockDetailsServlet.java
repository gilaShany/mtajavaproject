package com.myorg.javacourse;

import java.io.IOException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class StockDetailsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		
		Stock stock = new Stock();
		String resultStr = Stock.getHtmlDescription();
		resp.getWriter().println(resultStr);
	}

	
	}

