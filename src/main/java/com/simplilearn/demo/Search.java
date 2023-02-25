package com.simplilearn.demo;

import java.io.IOException;
import java.sql.ResultSetMetaData;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		
		// get data from index page	
		String source = req.getParameter("from");
		String destination = req.getParameter("to");
		
		Properties props=new  Properties();
		InputStream in=getServletContext().getResourceAsStream("/WEB-INF/application.properties");
		props.load(in);
		
		Connection conn=DBConfig.getConnection(props);
		
		if(conn!=null) {
						
			try {
				
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM airline_table WHERE source=? AND destination=?");
				
				ps.setString(2, destination);
				ps.setString(1, source);
				
				out.print("<table width=75% border=1>");
				out.print("<caption>Search Results : </caption>");
				
				ResultSet rs = ps.executeQuery();
				
				ResultSetMetaData rsmd = rs.getMetaData();
				int totalColumn = rsmd.getColumnCount();
				
				out.print("Search Results for Flights from "+source+"to "+destination);
				
				out.print("<tr>");
				for(int i=1; i<=totalColumn; i++) {
					
					out.print("<th>" + rsmd.getColumnName(i)+"</th>");
									
				}
				out.print("<tr>");
				while(rs.next()) {
					
					out.print("<tr><td>"+ rs.getInt(1)+"</td><td>"+ rs.getString(2)+"</td><td>"+ rs.getString(3)+"</td><td>"+ rs.getString(4)+"</td><td>"+ rs.getString(5)+"</td><td>"+
					rs.getString(6)+"</td><td>"+ rs.getInt(7)+"</td><td>"+"<a href=user_info.html>BOOK</a> "+"</td>");
							
				}
				
				out.print("</table>");	
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		else {
			out.println("Error While Connecting");
		}
		
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
