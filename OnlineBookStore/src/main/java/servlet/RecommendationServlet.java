package servlet;

import entity.Books;
import entity.Users;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import service.BookService;

@WebServlet("/recommend")
public class RecommendationServlet extends HttpServlet {

    @EJB
    private BookService bookService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("userLogin.jsp");
            return;
        }

        List<Books> list = bookService.getRecommendedBooks(user);

        request.setAttribute("recommendList", list);
        request.getRequestDispatcher("recommend.jsp").forward(request, response);
    }
}