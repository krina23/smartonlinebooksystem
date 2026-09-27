<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, entity.Books" %>

<!DOCTYPE html>
<html>
<head>
<title>Readify - Explore Books</title>

<script src="https://cdn.tailwindcss.com"></script>

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
}

body{
    font-family:'Poppins',sans-serif;

    background:
    linear-gradient(
        rgba(8,15,30,0.92),
        rgba(8,15,30,0.92)
    ),
    url('https://images.unsplash.com/photo-1507842217343-583bb7270b66');

    background-size:cover;
    background-position:center;
    background-attachment:fixed;

    color:white;
}

/* HEADER */

.header{
    background:rgba(255,255,255,0.08);
    backdrop-filter:blur(15px);
    border-bottom:1px solid rgba(255,255,255,0.1);

    position:sticky;
    top:0;
    z-index:100;
}

.logo{
    font-size:30px;
    font-weight:700;
    color:#facc15;
}

/* HERO */

.hero{
    text-align:center;
    padding:80px 20px;
}

.hero h1{
    font-size:60px;
    font-weight:700;
    color:#facc15;
}

.hero p{
    color:#cbd5e1;
    margin-top:15px;
    font-size:18px;
}

/* SEARCH */

.search-box{
    border-radius:50px;
    overflow:hidden;
}

/* TITLE */

.page-title{
    text-align:center;
    font-size:42px;
    font-weight:700;
    color:#facc15;
    margin-bottom:40px;
}

/* BOOK CARD */

.book-card{
    background:rgba(255,255,255,0.08);
    backdrop-filter:blur(15px);
    border-radius:20px;
    overflow:hidden;
    border:1px solid rgba(255,255,255,0.1);

    transition:0.4s;
}

.book-card:hover{
    transform:translateY(-10px);
    box-shadow:0 20px 40px rgba(250,204,21,0.25);
}

.book-img{
    width:100%;
    height:300px;
    object-fit:cover;
    transition:0.4s;
}

.book-card:hover .book-img{
    transform:scale(1.05);
}

.book-title{
    font-size:18px;
    font-weight:600;
}

.book-author{
    color:#cbd5e1;
    font-size:14px;
}

.book-price{
    color:#facc15;
    font-size:22px;
    font-weight:bold;
}

/* BUTTONS */

.btn-view{
    background:#3b82f6;
    padding:8px 14px;
    border-radius:8px;
}

.btn-cart{
    background:#10b981;
    padding:8px 14px;
    border-radius:8px;
}

.btn-wishlist{
    background:#ef4444;
    padding:8px 14px;
    border-radius:8px;
}

/* FOOTER */

footer{
    background:rgba(0,0,0,0.5);
    backdrop-filter:blur(10px);
}

</style>

</head>

<body class="flex flex-col min-h-screen">

<%
entity.Users user =
(entity.Users)session.getAttribute("user");
%>

<!-- HEADER -->

<div class="header px-6 py-4 flex justify-between items-center">

    <div class="logo">
        📚 Readify
    </div>

    <form action="books" method="get" class="w-1/3">

        <input type="hidden" name="action" value="search">

        <div class="search-box flex">

            <input
                type="text"
                name="keyword"
                placeholder="Search books..."
                class="w-full px-4 py-2 text-black">

            <button
                class="bg-yellow-400 text-black px-5">
                Search
            </button>

        </div>

    </form>

    <div class="flex gap-6 items-center">

        <a href="home">🏠 Home</a>

        <a href="wishlist">❤️ Wishlist</a>

        <a href="cart">🛒 Cart</a>

        <% if(user!=null){ %>

        <span>
            👤 <%=user.getFullname()%>
        </span>

        <% } %>

    </div>

</div>

<!-- HERO -->

<div class="hero">

    <h1>Explore Your Next Great Read</h1>

    <p>
        Discover thousands of books from your favorite authors.
    </p>

</div>

<!-- BOOK LIST -->

<div class="flex-grow px-8 py-10">

    <h2 class="page-title">
        📖 Available Books
    </h2>

    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-8">

        <%
        List<Books> books =
        (List<Books>)request.getAttribute("bookList");

        if(books==null || books.isEmpty()){
        %>

        <h3 class="col-span-full text-center text-2xl">
            No Books Found
        </h3>

        <%
        }else{

        for(Books b : books){
        %>

        <div class="book-card">

            <img
                src="image?name=<%=b.getImageUrl()%>"
                class="book-img">

            <div class="p-4">

                <h3 class="book-title">
                    <%=b.getTitle()%>
                </h3>

                <p class="book-author">
                    <%= b.getAuthorId()!=null ?
                    b.getAuthorId().getAuthorName()
                    : "Unknown Author" %>
                </p>

                <p class="book-price">
                    ₹ <%=b.getPrice()%>
                </p>

                <div class="flex justify-between mt-4">

                    <a
                       href="books?action=view&id=<%=b.getBookId()%>"
                       class="btn-view">
                       View
                    </a>

                    <a
                       href="cart?action=add&bookId=<%=b.getBookId()%>"
                       class="btn-cart">
                       Cart
                    </a>

                    <a
                       href="wishlist?action=add&bookId=<%=b.getBookId()%>"
                       class="btn-wishlist">
                       ♥
                    </a>

                </div>

            </div>

        </div>

        <%
        }
        }
        %>

    </div>

</div>

<!-- FOOTER -->

<footer class="text-center py-6">

    <h3 class="text-2xl font-bold text-yellow-400">
        📚 Readify
    </h3>

    <p class="mt-2">
        Your Digital Library for Endless Reading
    </p>

    <p class="mt-2 text-gray-400">
        © 2026 Readify. All Rights Reserved.
    </p>

</footer>

</body>
</html>