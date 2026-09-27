package servlet;

import entity.Users;
import jakarta.ejb.EJB;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserService;

@WebServlet(name = "UserServlet", urlPatterns = {"/user"})
public class UserServlet extends HttpServlet {

    @EJB
    private UserService userService;

    // 🔷 HANDLE GET (LOGOUT + DEFAULT)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // 🔷 DEFAULT → LOGIN PAGE
        if (action == null) {
            response.sendRedirect("userLogin.jsp");
            return;
        }

        // 🔷 LOGOUT
        if ("logout".equals(action)) {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("userLogin.jsp");
        }
    }

    // 🔷 HANDLE POST (REGISTER + LOGIN)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // 🔷 DEBUG (REMOVE LATER)
        System.out.println("Action: " + action);
        System.out.println("Fullname: " + request.getParameter("fullname"));
        System.out.println("Email: " + request.getParameter("email"));
        System.out.println("Password: " + request.getParameter("password"));
        System.out.println("Phone: " + request.getParameter("phone"));

        // 🔷 REGISTER
       if ("register".equals(action)) {

    Users user = new Users();
    user.setFullname(request.getParameter("fullname"));
    user.setEmail(request.getParameter("email"));
    user.setPassword(request.getParameter("password"));
    user.setPhone(request.getParameter("phone"));

    String result = userService.register(user);

    // 🔷 CHECK SUCCESS OR ERROR
    if (result.equals("Registration successful")) {

        // ✅ CLEAR FIELDS
        request.setAttribute("fullname", "");
        request.setAttribute("email", "");
        request.setAttribute("phone", "");

        request.setAttribute("success", result);

    } else {

        // ❌ KEEP VALUES
        request.setAttribute("fullname", request.getParameter("fullname"));
        request.setAttribute("email", request.getParameter("email"));
        request.setAttribute("phone", request.getParameter("phone"));

        request.setAttribute("error", result);
    }

    request.getRequestDispatcher("userRegister.jsp").forward(request, response);
}

        // 🔷 LOGIN
        else if ("login".equals(action)) {

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            Users user = userService.login(email, password);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("home.jsp");
            } else {
                request.setAttribute("message", "Invalid email or password");
                request.getRequestDispatcher("userLogin.jsp").forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "User Servlet";
    }
}