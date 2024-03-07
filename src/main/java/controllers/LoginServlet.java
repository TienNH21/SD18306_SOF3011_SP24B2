package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Ánh xạ url "/login vào class LoginServlet"
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException, ServletException {
        // Chuyển request sang jsp để xử lý tiếp.
        request.getRequestDispatcher("/views/login.jsp")
            .forward(request, response);
    }

    public void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        String user = request.getParameter("username");
        String pwd = request.getParameter("password");

        System.out.println(user + "-" + pwd);
    }
}
