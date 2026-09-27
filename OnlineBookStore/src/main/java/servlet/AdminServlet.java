/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author jeel
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");

        if ("login".equals(action)) {

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // 🔥 STATIC LOGIN
            if ("admin".equals(username) && "admin123".equals(password)) {

                HttpSession session = request.getSession();
                session.setAttribute("admin", "true");
System.out.println("Login success, redirecting...");
                response.sendRedirect(request.getContextPath() + "/adminDashboard.jsp");

            } else {
                request.setAttribute("error", "Invalid Admin Credentials");
                request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
            }
        }

        if ("logout".equals(action)) {
            request.getSession().invalidate();
            response.sendRedirect("adminLogin.jsp");
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
