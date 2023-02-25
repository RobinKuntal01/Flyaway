package com.simplilearn.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		
		Properties props=new  Properties();
		InputStream in=getServletContext().getResourceAsStream("/WEB-INF/application.properties");
		props.load(in);
		
		Connection conn=DBConfig.getConnection(props);
		
		//get the parameter
		
		String param=req.getParameter("id");
		int id=Integer.parseInt(param);
		
		if(conn!=null) {
			out.print("Connection Established");
				
			try {
				PreparedStatement stmt=conn.prepareStatement("delete from airline_table where id=?");
				stmt.setInt(1, id);
				
				int x=stmt.executeUpdate();
				
				if(x>0) {
					out.print(x+ " Deleted Successfully from database");
				
					//redirect to fetch servlet
					resp.sendRedirect("dashboard");
				}
				else {
					out.print("Error While Deleting a data");
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}
		else {
			out.println("Error While Connecting");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
}