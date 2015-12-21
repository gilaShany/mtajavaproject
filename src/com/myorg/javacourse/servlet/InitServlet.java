package com.myorg.javacourse.servlet;

import java.io.IOException;
import java.util.Date;

import com.myorg.javacourse.model.Portfolio;
import com.myorg.javacourse.model.Stock.*;
import com.myorg.javacourse.service.PortfolioManager;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.algo.service.PortfolioManagerInterface;
import org.algo.service.ServiceManager;

@SuppressWarnings("serial")
public class InitServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		PortfolioManager pm = new PortfolioManager();
		ServiceManager.setPortfolioManager(pm);
	
	}
}