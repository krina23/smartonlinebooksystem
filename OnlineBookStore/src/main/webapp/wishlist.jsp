<%-- 
    Document   : wishlist
    Created on : 29 Mar 2026, 7:58:24 pm
    Author     : patel
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Wishlist</title>
</head>
<body>

<h2>❤️ My Wishlist</h2>

<table border="1">
<tr>
    <th>Book</th>
    <th>Price</th>
    <th>Action</th>
</tr>

<c:forEach var="w" items="${wishlist}">
<tr>
    <td>${w.bookId.title}</td>
    <td>${w.bookId.price}</td>
    <td>
        <a href="cart?action=add&bookId=${w.bookId.bookId}">🛒 Move to Cart</a>
        |
        <a href="wishlist?action=remove&id=${w.wishlistId}">❌ Remove</a>
    </td>
</tr>
</c:forEach>

</table>

<br>
<a href="books">⬅ Continue Shopping</a>

</body>
</html>
