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
		Portfolio portfolio = portfolioManager.getPortfolio();
		resp.getWriter().println(portfolio.getHtmlString());
		
		Portfolio portfolio2 = new Portfolio(portfolio);
		portfolio2.setTitle("Portfolio #2");
		resp.getWriter().println(portfolio.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
	}
	}