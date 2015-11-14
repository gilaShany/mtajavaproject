package com.myorg.javacourse;

import java.io.IOException;
import javax.servlet.http.*;
import com.myorg.javacourse.MathEx3.*;
@SuppressWarnings("serial")
public class MathServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		resp.setContentType("text/html");
		
		MathEx3 mathEx3 = new MathEx3();
		String resultStr = mathEx3.getResults();
		
		resp.getWriter().println(resultStr);
	}

	}

