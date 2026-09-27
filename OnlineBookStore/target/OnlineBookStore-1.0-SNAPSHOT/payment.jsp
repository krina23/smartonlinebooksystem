<%-- 
    Document   : payment
    Created on : 29 Mar 2026, 2:42:03 pm
    Author     : patel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h2>💳 Payment</h2>

<form action="order" method="post">

    <!-- HIDDEN VALUES -->
    <input type="hidden" name="fullAddress" value="${fullAddress}">
    <input type="hidden" name="city" value="${city}">
    <input type="hidden" name="state" value="${state}">
    <input type="hidden" name="pincode" value="${pincode}">
    <input type="hidden" name="cartItemId" value="${cartItemId}">

    <h3>Select Payment Method</h3>

    <input type="radio" name="paymentType" value="COD" required> COD <br>
    <input type="radio" name="paymentType" value="QR"> QR <br><br>

    <button type="submit">Confirm Order</button>

</form>