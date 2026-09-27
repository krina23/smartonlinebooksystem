<%-- 
    Document   : navbar
    Created on : Mar 29, 2026, 12:29:25 AM
    Author     : jeel
--%>

<%@ page contentType="text/html;charset=UTF-8" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
    body {
        font-family: 'Poppins', sans-serif;
    }

    .navbar {
        backdrop-filter: blur(10px);
        background: rgba(0, 0, 0, 0.7) !important;
        border-radius: 0 0 15px 15px;
    }

    .navbar-brand {
        font-size: 22px;
        letter-spacing: 1px;
    }

    .nav-btn {
        border-radius: 20px;
        transition: all 0.3s ease;
    }

    .nav-btn:hover {
        transform: translateY(-2px);
        background: white !important;
        color: black !important;
    }

    .btn-warning {
        border-radius: 20px;
        font-weight: bold;
    }

    .btn-danger {
        border-radius: 20px;
    }
</style>

<nav class="navbar navbar-expand-lg navbar-dark shadow sticky-top">
    <div class="container">

        <a class="navbar-brand fw-bold" href="home.jsp">📚 Readify</a>

        <div class="ms-auto d-flex align-items-center">

            <a class="btn btn-outline-light nav-btn me-2" href="books">Books</a>
            <a class="btn btn-outline-light nav-btn me-2" href="cart">Cart</a>
            <a class="btn btn-outline-light nav-btn me-2" href="wishlist">Wishlist</a>

            <%
                Object user = session.getAttribute("user");
            %>

            <% if (user == null) { %>
                <a class="btn btn-outline-light nav-btn me-2" href="userLogin.jsp">Login</a>
                <a class="btn btn-warning me-2" href="userRegister.jsp">Register</a>
            <% } else { %>
                <a class="btn btn-outline-light nav-btn me-2" href="order?action=history">Orders</a>
                <a class="btn btn-danger me-2" href="user?action=logout">Logout</a>
            <% } %>

            <a class="btn btn-secondary nav-btn" href="adminLogin.jsp">Admin</a>

        </div>
    </div>
</nav>