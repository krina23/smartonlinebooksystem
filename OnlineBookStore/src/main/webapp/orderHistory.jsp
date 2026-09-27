<%-- 
    Document   : orderHistory
    Created on : 29 Mar 2026, 11:52:25 am
    Author     : patel
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2>📦 My Orders</h2>

<c:forEach var="o" items="${orders}">

    <div style="border:1px solid black; padding:10px; margin:10px;">

        <b>Order ID:</b> ${o.orderId} <br>
        <b>Date:</b> ${o.orderDate} <br>
        <b>Total:</b> ₹${o.totalAmount} <br>
        <b>Status:</b> ${o.orderStatus} <br>

        <br>
        <b>Items:</b>
        <ul>
            <c:forEach var="item" items="${o.orderItemsCollection}">
                <li>
                    ${item.bookId.title} - Qty: ${item.quantity}
                </li>
            </c:forEach>
        </ul>

    </div>

</c:forEach>

<br>
<a href="books">⬅ Back to Home</a>
