package servlet;

import entity.Books;
import entity.Users;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.BookService;
import service.WishlistService;

import java.io.IOException;
import java.util.List;
import entity.Wishlist;

@WebServlet("/wishlist")
public class WishlistServlet extends HttpServlet {

    @EJB
    private WishlistService wishlistService;

    @EJB
    private BookService bookService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("userLogin.jsp");
            return;
        }

        // 🔷 VIEW WISHLIST
        if (action == null || action.equals("view")) {
            List<Wishlist> list = wishlistService.getWishlist(user);
            request.setAttribute("wishlist", list);
            request.getRequestDispatcher("wishlist.jsp").forward(request, response);
        }

        // 🔷 ADD
        else if ("add".equals(action)) {
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            Books book = bookService.getBookById(bookId);

            wishlistService.addToWishlist(user, book);

            response.sendRedirect("books");
        }

        // 🔷 REMOVE
        else if ("remove".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            wishlistService.remove(id);

            response.sendRedirect("wishlist");
        }
    }
}