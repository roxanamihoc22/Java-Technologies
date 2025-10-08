package com.example.lab1;

import java.io.*;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/helloServlet")
public class Pages extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String selectedPage = req.getParameter("page");
        boolean apiCall = "true".equals(req.getParameter("api"));
        String method = req.getMethod();
        String clientIP = req.getRemoteAddr();
        String userAgent = req.getHeader("User-Agent");

        Enumeration<String> languages = req.getHeaders("Accept-Language");
        StringBuilder language = new StringBuilder();
        while (languages.hasMoreElements()) {
            language.append(languages.nextElement()).append(" ");
        }

        System.out.println("Request:");
        System.out.println("HTTP Method: " + method);
        System.out.println("Client IP: " + clientIP);
        System.out.println("User-Agent: " + userAgent);
        System.out.println("Client Languages: " + language.toString().trim());
        System.out.println("Parameter: " + selectedPage);

        if (apiCall) {
            resp.setContentType("text/plain");
            PrintWriter out = resp.getWriter();
            out.println(selectedPage != null ? selectedPage : "No parameter sent");
            out.close();
        } else {
            if ("1".equals(selectedPage)) {
                resp.sendRedirect("page1.html");
            } else if ("2".equals(selectedPage)) {
                resp.sendRedirect("page2.html");
            } else {
                resp.sendRedirect("index.jsp");
            }
        }
    }

    public void destroy() {
    }
}