package org.jsp.UIApp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;


public class FirstServlet extends GenericServlet
{

	@Override
	public void service(ServletRequest req, ServletResponse resp) 
			throws ServletException, IOException 
	{
		//fetch ui/form data
		String name =req.getParameter("nm");
		String place=req.getParameter("pl");
		
		//presentation logic
		PrintWriter out =resp.getWriter();
		out.print("<html><body bgcolor='yellow'>"+"<h1>Student name is "+name+"form "+place+"</h1>"+"</body></html>");
		out.close();
		
		
	}
	

}
