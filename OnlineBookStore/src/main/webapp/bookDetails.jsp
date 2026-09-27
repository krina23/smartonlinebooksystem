<%-- 
    Document   : bookDetails
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Book Details</title>
</head>

<body>

<h2>📖 Book Details</h2>

<p><b>Title:</b> ${book.title}</p>
<p><b>Description:</b> ${book.description}</p>
<p><b>ISBN:</b> ${book.isbn}</p>
<p><b>Price:</b> ${book.price}</p>
<p><b>Stock:</b> ${book.stockQuantity}</p>
<p><b>Pages:</b> ${book.pages}</p>

<p>
    <img src="image?name=${book.imageUrl}" width="120">
</p>

<a href="review?bookId=${book.bookId}">⭐ Write Review</a>
<br>
<a href="books">⬅ Back to List</a>

</body>
</html>