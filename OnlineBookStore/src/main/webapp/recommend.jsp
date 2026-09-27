<%-- 
    Document   : recommend
    Created on : 31 Mar 2026, 11:08:22 am
    Author     : patel
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<html>
<head>
    <title>Recommended Books</title>
</head>
<body>

<h2>🔥 Recommended For You</h2>

<c:if test="${empty recommendList}">
    <p>No recommendations available</p>
</c:if>

<c:forEach var="b" items="${recommendList}">

    <div style="border:1px solid black; padding:10px; margin:10px; width:200px;">
        
        <img src="image?name=${b.imageUrl}" width="120"><br>

        <b>${b.title}</b><br>
        ₹ ${b.price}<br>

        <a href="books?action=view&id=${b.bookId}">
            View Details
        </a>
    </div>

</c:forEach>

</body>
</html>
