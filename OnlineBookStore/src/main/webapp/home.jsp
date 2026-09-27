<%-- 
    Document   : home
    Created on : 27 Mar 2026, 11:58:18 am
    Author     : patel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Readify</title>

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:'Poppins',sans-serif;
}

body{
    background:#0f172a;
    color:white;
}

/* HERO SECTION */
.hero{
    height:100vh;
    background:
    linear-gradient(rgba(0,0,0,.65),rgba(0,0,0,.65)),
    url('https://images.unsplash.com/photo-1524995997946-a1c2e315a42f');

    background-size:cover;
    background-position:center;

    display:flex;
    align-items:center;
    justify-content:center;
    text-align:center;
}

.hero-content{
    max-width:800px;
}

.hero h1{
    font-size:70px;
    margin-bottom:20px;
}

.hero span{
    color:#facc15;
}

.hero p{
    font-size:20px;
    color:#ddd;
    margin-bottom:30px;
}

.btn{
    display:inline-block;
    text-decoration:none;
    padding:15px 35px;
    border-radius:30px;
    font-weight:bold;
    margin:10px;
}

.btn-primary{
    background:#facc15;
    color:black;
}

.btn-secondary{
    background:white;
    color:black;
}

/* FEATURES */
.features{
    padding:80px 10%;
}

.section-title{
    text-align:center;
    font-size:40px;
    margin-bottom:50px;
    color:#facc15;
}

.feature-grid{
    display:grid;
    grid-template-columns:repeat(auto-fit,minmax(250px,1fr));
    gap:25px;
}

.feature-card{
    background:#1e293b;
    padding:30px;
    border-radius:20px;
    text-align:center;
    transition:.3s;
}

.feature-card:hover{
    transform:translateY(-10px);
}

.feature-card h3{
    margin:15px 0;
}

/* STATS */
.stats{
    display:flex;
    justify-content:space-around;
    flex-wrap:wrap;
    padding:60px 20px;
    background:#111827;
}

.stat{
    text-align:center;
    margin:20px;
}

.stat h2{
    color:#facc15;
    font-size:45px;
}

/* FOOTER */
footer{
    background:black;
    text-align:center;
    padding:25px;
}

</style>
</head>

<body>

<!-- HERO -->

<section class="hero">

<div class="hero-content">

<h1>
Discover Your Next
<span>Favorite Book</span>
</h1>

<p>
Explore thousands of books, authors and stories from around the world.
</p>

<a href="books?action=list" class="btn btn-primary">
Explore Library
</a>

<a href="books?action=bestseller" class="btn btn-secondary">
Best Sellers
</a>

</div>

</section>

<!-- FEATURES -->

<section class="features">

<h2 class="section-title">
Why Choose Readify?
</h2>

<div class="feature-grid">

<div class="feature-card">
<h3>📚 Huge Collection</h3>
<p>Thousands of books from every genre.</p>
</div>

<div class="feature-card">
<h3>🚚 Fast Delivery</h3>
<p>Quick and reliable order processing.</p>
</div>

<div class="feature-card">
<h3>⭐ Reviews</h3>
<p>Read ratings and reviews before buying.</p>
</div>

<div class="feature-card">
<h3>❤️ Wishlist</h3>
<p>Save your favorite books for later.</p>
</div>

</div>

</section>

<!-- STATS -->

<section class="stats">

<div class="stat">
<h2>5000+</h2>
<p>Books</p>
</div>

<div class="stat">
<h2>1000+</h2>
<p>Readers</p>
</div>

<div class="stat">
<h2>500+</h2>
<p>Reviews</p>
</div>

<div class="stat">
<h2>100+</h2>
<p>Authors</p>
</div>

</section>

<!-- FOOTER -->

<footer>
© 2026 Readify | Your Digital Library
</footer>

</body>
</html>