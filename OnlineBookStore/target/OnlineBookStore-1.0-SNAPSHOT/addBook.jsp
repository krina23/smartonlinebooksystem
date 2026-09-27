<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add Book</title>
</head>
<body>

<h2>➕ Add Book</h2>

<!-- 🔷 MESSAGE -->
<%
    String msg = (String) request.getAttribute("message");
    if (msg != null) {
        if (msg.toLowerCase().contains("success")) {
%>
    <p style="color:green;"><%= msg %></p>
<%
        } else {
%>
    <p style="color:red;"><%= msg %></p>
<%
        }
    }
%>

<form action="books" method="post" enctype="multipart/form-data">

    <input type="hidden" name="action" value="add"/>

    <!-- TITLE -->
    Title: <br>
    <input type="text" name="title" required
           value="<%= request.getAttribute("title") != null ? request.getAttribute("title") : "" %>">
    <br><br>

    <!-- DESCRIPTION (OPTIONAL) -->
    Description: <br>
    <textarea name="description"><%= request.getAttribute("description") != null ? request.getAttribute("description") : "" %></textarea>
    <br><br>

    <!-- ISBN -->
    ISBN: <br>
    <input type="text" name="isbn" required
           value="<%= request.getAttribute("isbn") != null ? request.getAttribute("isbn") : "" %>">
    <br><br>

    <!-- PRICE -->
    Price: <br>
    <input type="number" name="price" step="0.01" required
           value="<%= request.getAttribute("price") != null ? request.getAttribute("price") : "" %>">
    <br><br>

    <!-- STOCK -->
    Stock: <br>
    <input type="number" name="stock" required
           value="<%= request.getAttribute("stock") != null ? request.getAttribute("stock") : "" %>">
    <br><br>

    <!-- PAGES -->
    Pages: <br>
    <input type="number" name="pages" required
           value="<%= request.getAttribute("pages") != null ? request.getAttribute("pages") : "" %>">
    <br><br>

    <!-- IMAGE -->
    Image: <br>
    <input type="file" name="imageFile" accept="image/*" required>
    <br><br>

    <button type="submit">Add Book</button>

</form>

<br>
<a href="books">📚 Back to Book List</a>

</body>
</html>