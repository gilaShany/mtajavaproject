package com.myorg.javacourse.servlet;

import java.io.IOException;
import java.util.Date;

import com.myorg.javacourse.model.Portfolio;
import com.myorg.javacourse.model.Stock.*;
import com.myorg.javacourse.service.PortfolioManager;

import javax.servlet.http.*;

import org.algo.service.PortfolioManagerInterface;

@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		
		PortfolioManager portfolioManager = new PortfolioManager();
		Portfolio portfolio1 = PortfolioManagerInterface.getPortfolio();
		resp.getWriter().println(portfolio1.getHtmlString());
	
	}
}