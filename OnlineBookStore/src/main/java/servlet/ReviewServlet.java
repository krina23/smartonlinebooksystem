package servlet;

import entity.*;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.BookService;
import service.ReviewService;

import java.io.IOException;
import java.util.List;

@WebServlet("/review")
public class ReviewServlet extends HttpServlet {

    @EJB
    private ReviewService reviewService;

    @EJB
    private BookService bookService;

    // 🔷 LOAD REVIEW PAGE
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int bookId = Integer.parseInt(request.getParameter("bookId"));

        Books book = bookService.getBookById(bookId);

        List<Review> reviews = reviewService.getReviewsByBook(book);

        request.setAttribute("book", book);
        request.setAttribute("reviewList", reviews);

        request.getRequestDispatcher("review.jsp").forward(request, response);
    }

    // 🔷 SAVE REVIEW
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("userLogin.jsp");
            return;
        }

        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String reviewText = request.getParameter("reviewText");

        Books book = bookService.getBookById(bookId);

        // 🔥 SAVE REVIEW
        reviewService.addReview(user, book, rating, reviewText);

        // 🔥 REDIRECT (IMPORTANT FIX)
        response.sendRedirect("review?bookId=" + bookId);
    }
}