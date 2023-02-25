package com.simplilearn.demo;

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
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;


@WebServlet("/AddNew")
public class addNewrouteServlet extends HttpServlet {

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			
			PrintWriter out=resp.getWriter();
			resp.setContentType("text/html");
			
			Properties props=new Properties();
			InputStream in =getServletContext().getResourceAsStream("/WEB-INF/application.properties");
			props.load(in);
			
			
			String airline_name=req.getParameter("airline_name");
			String capacity =req.getParameter("capacity");
			String source=req.getParameter("source");
			String destination=req.getParameter("destination");
			String time_taken=req.getParameter("time_taken");
			
			String aprice=req.getParameter("price");
			
			int price = Integer.parseInt(aprice);
			
			Connection conn=DBConfig.getConnection(props);
			
			if(conn!=null) {
				
				PreparedStatement stmt;
				try {
					stmt = conn.prepareStatement("insert into airline_table(airline_name, capacity, source, destination, time_taken, price) value (?,?,?,?,?,?)");
				
				stmt.setString(1, airline_name);
				stmt.setString(2, capacity);
				stmt.setString(3, source);
				stmt.setString(4, destination);
				stmt.setString(5, time_taken);
				
				stmt.setInt(6, price);
			
				
			
				int x=stmt.executeUpdate();
				if(x>0) {
					out.println("Route Added SuccessFully Successfully");
					resp.sendRedirect("dashboard");
				}
				else {
					out.print("Error While Updating a data");
					resp.sendRedirect("addNewroute.html");
				}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				out.print("Error While Connecting");
			}
			
		}

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(req, resp);
		}
		
		

	}

