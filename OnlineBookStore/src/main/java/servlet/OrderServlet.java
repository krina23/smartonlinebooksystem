package servlet;

import entity.*;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.CartService;
import service.OrderService;

import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    @EJB
    private OrderService orderService;

    @EJB
    private CartService cartService;

    // 🔷 GET (Checkout + Order History)
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

        // 🔥 DEBUG
        System.out.println("Action = " + action);

        // 🔷 ORDER HISTORY
        if ("history".equals(action)) {

            List<Orders> orders = orderService.getOrdersByUser(user);

            request.setAttribute("orders", orders);
            request.getRequestDispatcher("orderHistory.jsp").forward(request, response);
        }

        // 🔷 SINGLE ITEM CHECKOUT
        else if (request.getParameter("cartItemId") != null) {

            String cartItemId = request.getParameter("cartItemId");
            request.setAttribute("cartItemId", cartItemId);

            request.getRequestDispatcher("checkout.jsp").forward(request, response);
        }

        // 🔷 DEFAULT → OPEN CHECKOUT
        else {
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
        }
    }

    // 🔷 POST (Place Order)
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    HttpSession session = request.getSession();
    Users user = (Users) session.getAttribute("user");

    if (user == null) {
        response.sendRedirect("userLogin.jsp");
        return;
    }

    // 🔥 CHECK PAYMENT STEP
    String paymentType = request.getParameter("paymentType");

    // =====================================================
    // 🔷 STEP 1 → FROM CHECKOUT → GO TO PAYMENT PAGE
    // =====================================================
    if (paymentType == null) {

        String fullAddress = request.getParameter("fullAddress");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String pincode = request.getParameter("pincode");
        String cartItemId = request.getParameter("cartItemId");

        // send data to payment.jsp
        request.setAttribute("fullAddress", fullAddress);
        request.setAttribute("city", city);
        request.setAttribute("state", state);
        request.setAttribute("pincode", pincode);
        request.setAttribute("cartItemId", cartItemId);

        request.getRequestDispatcher("payment.jsp").forward(request, response);
        return;
    }

    // =====================================================
    // 🔷 STEP 2 → FROM PAYMENT → PLACE ORDER
    // =====================================================

    String fullAddress = request.getParameter("fullAddress");
    String city = request.getParameter("city");
    String state = request.getParameter("state");
    int pincode = Integer.parseInt(request.getParameter("pincode"));

    String cartItemIdStr = request.getParameter("cartItemId");

    if (cartItemIdStr != null && !cartItemIdStr.isEmpty()) {

        int cartItemId = Integer.parseInt(cartItemIdStr);

        CartItems item = cartService.getCartItemById(cartItemId);

        orderService.placeSingleOrder(user, item,
                fullAddress, city, state, pincode);
    }

    // OPTIONAL FULL CART
    else {
        List<CartItems> cartItems = cartService.getCartItems(user);

        orderService.placeOrder(user, cartItems,
                fullAddress, city, state, pincode);
    }

    // 🔥 PRINT PAYMENT (for testing)
    System.out.println("Payment Type: " + paymentType);

    // 🔥 SUCCESS PAGE
    response.sendRedirect("orderSuccess.jsp");
}
    @Override
    public String getServletInfo() {
        return "Order Servlet";
    }
}