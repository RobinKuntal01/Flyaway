package com.simplilearn.demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

@WebServlet("/changePassword")
public class ChangePassWord extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        String pass = req.getParameter("pass");
        if (!LoginAdmin.isLoggedIn){
            out.println("You must login first");
        }
        else if (pass.equals("")){
            out.println("Password can't be empty");
        }
        else if (LoginAdmin.isLoggedIn && !pass.equals("")){
            LoginAdmin.password = pass;
            out.println("Password changed. New Password is "+LoginAdmin.password);
        }
        else {
            out.println("Sorry, Something went wrong");
        }
        out.close();
    }
}