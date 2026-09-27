<%-- 
    Document   : checkout
    Created on : 29 Mar 2026, 11:17:42 am
    Author     : patel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Checkout</title>
</head>
<body>

<h2>📦 Checkout</h2>

<form action="order" method="post">
<input type="hidden" name="cartItemId" value="${cartItemId}">
    Full Address:<br>
    <textarea name="fullAddress" required></textarea><br><br>

    City:<br>
    <input type="text" name="city" required><br><br>

    State:<br>
    <input type="text" name="state" required><br><br>

    Pincode:<br>
    <input type="number" name="pincode" required><br><br>

    <button type="submit">Place Order</button>

</form>

<br>
<a href="cart">⬅ Back to Cart</a>

</body>
</html>
