<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<html>
<head>
    <title>My Cart</title>
</head>
<body>

<h2>🛒 My Cart</h2>

<!-- 🔥 MESSAGE DISPLAY -->
<c:if test="${not empty sessionScope.msg}">
    <p style="color:red;">${sessionScope.msg}</p>
    <c:remove var="msg" scope="session"/>
</c:if>

<!-- 🔴 EMPTY CART -->
<c:if test="${empty cartList}">
    <p style="color:red;">Cart is empty</p>
</c:if>

<!-- 🟢 CART ITEMS -->
<c:if test="${not empty cartList}">

<table border="1" cellpadding="10">
<tr>
    <th>Book</th>
    <th>Price</th>
    <th>Stock</th>
    <th>Quantity</th>
    <th>Total</th>
    <th>Action</th>
</tr>

<c:set var="grandTotal" value="0" />

<c:forEach var="c" items="${cartList}">

<tr>
    <!-- 📚 BOOK TITLE -->
    <td>${c.bookId.title}</td>

    <!-- 💰 PRICE -->
    <td>₹ ${c.bookId.price}</td>

    <!-- 🔥 SHOW STOCK -->
    <td>
        <c:choose>
            <c:when test="${c.bookId.stockQuantity == 0}">
                <span style="color:red;">Out of Stock</span>
            </c:when>
            <c:when test="${c.bookId.stockQuantity <= 3}">
                <span style="color:orange;">Only ${c.bookId.stockQuantity} left</span>
            </c:when>
            <c:otherwise>
                ${c.bookId.stockQuantity}
            </c:otherwise>
        </c:choose>
    </td>

    <!-- 🔥 QUANTITY UPDATE -->
    <td>
        <a href="cart?action=update&type=decrease&id=${c.cartItemId}">➖</a>
        ${c.quantity}

        <!-- ✅ DISABLE IF MAX REACHED -->
        <c:choose>
            <c:when test="${c.quantity < c.bookId.stockQuantity}">
                <a href="cart?action=update&type=increase&id=${c.cartItemId}">➕</a>
            </c:when>
            <c:otherwise>
                <span style="color:red;">Max</span>
            </c:otherwise>
        </c:choose>
    </td>

    <!-- 🧮 ITEM TOTAL -->
    <td>₹ ${c.bookId.price * c.quantity}</td>

    <!-- ⚙️ ACTIONS -->
    <td>

        <!-- ❌ REMOVE -->
        <a href="cart?action=remove&id=${c.cartItemId}">
            ❌ Remove
        </a>

        <br><br>

        <!-- 🔴 DISABLE ORDER IF OUT OF STOCK -->
        <c:choose>
            <c:when test="${c.bookId.stockQuantity == 0}">
                <button disabled>Out of Stock</button>
            </c:when>
            <c:otherwise>
                <form action="order" method="get">
                    <input type="hidden" name="cartItemId" value="${c.cartItemId}">
                    <button type="submit">Place Order</button>
                </form>
            </c:otherwise>
        </c:choose>

    </td>
</tr>

<!-- 🔥 GRAND TOTAL -->
<c:set var="grandTotal" 
       value="${grandTotal + (c.bookId.price * c.quantity)}" />

</c:forEach>

</table>

<br>

<!-- 💰 TOTAL -->
<h3>Total Amount: ₹ ${grandTotal}</h3>

</c:if>

<br><br>

<!-- 📚 BACK -->
<a href="books">📚 Continue Shopping</a>

</body>
</html>