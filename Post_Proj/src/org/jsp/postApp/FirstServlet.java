package org.jsp.postApp;

import java.io.*;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class FirstServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException,IOException
	{
		//fetch ui/from Data
		String sid=req.getParameter("i");
		int id=Integer.parseInt(sid);
		
		String name=req.getParameter("nm");
		String dept=req.getParameter("dp");
		String sperc=req.getParameter("pr");
		double perc=Double.parseDouble(sperc);
		
		//Persentation Logic/Servlet technology
		PrintWriter out=resp.getWriter();
		out.println("<html><body bgcolor='cyan'>"+"<h1> Student name is "+name+" from "
				+ ""+dept+"</h1>"+"</body></html");
		out.close();
		
		//persistence logic/JDBC Technology
		Connection con=null;
		PreparedStatement pstmt=null;
		String qry="insert into btm.student values(?,?,?,?)";//DML
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			pstmt=con.prepareStatement(qry);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, dept);
			pstmt.setDouble(4, perc);
			
			//Execute
			pstmt.executeUpdate();
			System.out.println("Record inserted");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	

}
