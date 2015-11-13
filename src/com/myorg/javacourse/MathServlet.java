package com.myorg.javacourse;

import java.io.IOException;
import javax.servlet.http.*;
import com.myorg.javacourse.MathEx3.*;
@SuppressWarnings("serial")
public class MathServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		//MathEx3 MathEx3 = new MathEx3();
		String resultStr = com.myorg.javacourse.MathEx3.getResults();
		
		
		resp.setContentType("text/html");
		resp.getWriter().println(resultStr);
	}

	}

