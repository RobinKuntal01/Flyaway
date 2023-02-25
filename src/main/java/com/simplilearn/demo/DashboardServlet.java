package com.simplilearn.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");

		Properties props=new  Properties();
		InputStream in=getServletContext().getResourceAsStream("/WEB-INF/application.properties");
		props.load(in);

		Connection conn=DBConfig.getConnection(props);

		if(conn!=null) {
			out.print("Connection Established");

			Statement stmt;

			try {

				stmt=conn.createStatement();

				out.print("<table width=75% border=1>");
				out.print("<caption>Airline Table Schedule : </caption>");

				ResultSet rs=stmt.executeQuery("select * from airline_table");

				ResultSetMetaData rsmd = rs.getMetaData();
				int totalColumn = rsmd.getColumnCount();

				out.print("<tr>");
				for(int i=1; i<=totalColumn; i++) {

					out.print("<th>" + rsmd.getColumnName(i)+"</th>");
				}

				out.print("<tr>");
				while(rs.next()) {

					out.print("<tr><td>"+ rs.getInt(1)+"</td><td>"+ rs.getString(2)+"</td><td>"+ 
					rs.getString(3)+"</td><td>"+ rs.getString(4)+"</td><td>"+ rs.getString(5)+"</td><td>"+
							rs.getString(6)+"</td><td>"+ rs.getInt(7)+"</td><td>");

				}

				out.print("</table>");
				out.print("<br><br>");
				out.print("<a href=update.html>Update or Delete a Route</a>");
				out.print("<br><br>");
				out.print("<a href=addNewroute.html>Add a New Route</a>");
				out.print("<br><br>");
				out.print("<a href=changePass.html>Change Password?</a>");
				out.print("<br><br>");
				out.print("<a href='logout'> Logout </a>");

			} catch (Exception e) {
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