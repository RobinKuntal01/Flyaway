package com.simplilearn.demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin")
public class LoginAdmin extends HttpServlet {
    public static boolean isLoggedIn = false;
    public static String password = "Admin";
    public static String email = "admin@flyaway.com";

    @Override
    public void doPost (HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        String email = req.getParameter("email");
        String pass = req.getParameter("pass");

        if (email.equals(LoginAdmin.email) && pass.equals(LoginAdmin.password)){
            isLoggedIn = true;
            out.println("You have LoggedIn");
            resp.sendRedirect("dashboard");
        }
        else {
            isLoggedIn = false;
            out.println("Login Failed : Incorrect email or Password");
        }
        out.close();
    }
}