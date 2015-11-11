package com.myorg.javacourse;

import java.io.IOException;
import javax.servlet.http.*;
import com.myorg.javacourse.MathEx3.*;
@SuppressWarnings("serial")
public class MathServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		int x =1;
		int y =1;
		
		resp.setContentType("text/plain");
		resp.getWriter().println("1+1 = " + MathEx3.sum(x,y));
	}
}
