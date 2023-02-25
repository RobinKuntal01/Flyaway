package com.simplilearn.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/save")
public class SaveUserData extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//1. check the connectivity
		
		Properties props=new Properties();
		InputStream in=getServletContext().getResourceAsStream("/WEB-INF/application.properties");
		props.load(in);
		//2.get connection object
		
		Connection conn=DBConfig.getConnection(props);
				
		//3.get parameters from html
		
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		
		int mobile_no= Integer.parseInt(req.getParameter("mobile_no"));
		
		String gender=req.getParameter("gender");
		
		
		//4.write query to insert data
		PrintWriter out=resp.getWriter();
		if(conn!=null) {
			out.print("connection Established");
			//query to insert data using prepared statements
			
			try {
				
				
				PreparedStatement stmt=conn.prepareStatement("insert into user_table (name,email,mob_no,gender) values (?,?,?,?)");
				
				stmt.setString(1, name);
				stmt.setString(2, email);
				stmt.setInt(3, mobile_no);
				stmt.setString(4, gender);
				
				
				int x=stmt.executeUpdate();
				
				if(x>0) {
					System.out.println("Data inserted successfully");
					out.print("Data inserted Successfully");
					//action
					resp.sendRedirect("payment_gateway.html");
				}
				else {
					System.out.println("Error While Inserting a Data");
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}