package com.myorg.javacourse.servlet;

import java.io.IOException;
import java.util.Date;

import com.myorg.javacourse.model.Portfolio;
import com.myorg.javacourse.model.Stock.*;
import com.myorg.javacourse.service.PortfolioManager;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		
		PortfolioManager portfolioManager = new PortfolioManager();
		Portfolio portfolio1 = portfolioManager.getPortfolio();
		resp.getWriter().println(portfolio1.getHtmlString());
		/*
		Portfolio portfolio2 = new Portfolio (portfolio1);
		portfolio2.setTitle("Protfolio #2");
		
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
		for (int i = 0; i<portfolio1.getProtfolioSize()-1; i++){
			portfolio1.getStocks()[i] = portfolio1.getStocks()[i+1];
		}
		portfolio1.setProtfolioSize(portfolio1.getProtfolioSize()-1);
		
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
		portfolio2.getStocks()[portfolio2.getProtfolioSize()-1].setBid(55.55f);
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		*/
	}
}