package servlet;

import entity.Books;
import entity.CartItems;
import entity.Users;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import service.BookService;
import service.CartService;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    @EJB
    private CartService cartService;

    @EJB
    private BookService bookService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        // 🔴 LOGIN CHECK
        if (user == null) {
            response.sendRedirect("userLogin.jsp");
            return;
        }

        // 🔷 ADD TO CART
        if ("add".equals(action)) {

        int bookId = Integer.parseInt(request.getParameter("bookId"));
        Books book = bookService.getBookById(bookId);

         // 🔥 GET MESSAGE FROM SERVICE
        String msg = cartService.addToCart(user, book);

        // 🔥 STORE MESSAGE
        request.getSession().setAttribute("msg", msg);

        response.sendRedirect("books");
    }

        // 🔷 REMOVE ITEM
        else if ("remove".equals(action)) {

            int id = Integer.parseInt(request.getParameter("id"));
            cartService.removeItem(id);
            response.sendRedirect("cart");
        }

        // 🔷 UPDATE QUANTITY
        else if ("update".equals(action)) {

            int id = Integer.parseInt(request.getParameter("id"));
            String type = request.getParameter("type");

            CartItems item = cartService.getCartItemById(id);

            if (item != null) {
                int qty = item.getQuantity();

                if ("increase".equals(type)) {
                    qty++;
                } else if ("decrease".equals(type)) {
                    qty--;
                }

                // ❗ Remove if 0
                if (qty <= 0) {
                    cartService.removeItem(id);
                } else {
                    // OPTIONAL: STOCK CHECK
                    if (qty > item.getBookId().getStockQuantity()) {
                            request.getSession().setAttribute("msg", "Stock limit reached");
                            response.sendRedirect("cart");
                            return;
                        }

                    item.setQuantity(qty);
                    cartService.updateItem(item);
                }
            }

            response.sendRedirect("cart");
        }

        // 🔷 VIEW CART
        else {

            List<CartItems> cartList = cartService.getCartItems(user);
            request.setAttribute("cartList", cartList);

            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
    }
}