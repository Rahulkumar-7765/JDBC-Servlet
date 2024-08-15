package org.jsp.GetApp;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String sid = req.getParameter("i");
        int id = Integer.parseInt(sid);
        

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT * FROM btm.student WHERE id = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
            pstmt = con.prepareStatement(qry);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            PrintWriter out = resp.getWriter();
           

            if (rs.next()) { // Use rs.next() to check for data
                String name = rs.getString("name");
                String dept = rs.getString("dept");

                out.println("<html><body bgcolor='green'>"
                        + "<h1>Student Name is " + name + " and Department is " + dept + "</h1>"
                        + "</body></html>");
            } else {
                out.println("<html><body bgcolor='red'>"
                        + "<h1>Invalid ID.</h1>"
                        + "</body></html>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}