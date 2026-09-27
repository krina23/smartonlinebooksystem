<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Write Review</title>

    <style>
        .star {
            font-size: 30px;
            color: #ccc;
            cursor: pointer;
        }

        .star.active {
            color: gold;
        }
    </style>
</head>

<body>

<h2>⭐ Write Review</h2>

<p><b>Book:</b> ${book.title}</p>

<form action="review" method="post">

    <input type="hidden" name="bookId" value="${book.bookId}">
    <input type="hidden" name="rating" id="ratingValue">

    <!-- ⭐ STAR UI -->
    <span class="star" onclick="setRating(1)">★</span>
    <span class="star" onclick="setRating(2)">★</span>
    <span class="star" onclick="setRating(3)">★</span>
    <span class="star" onclick="setRating(4)">★</span>
    <span class="star" onclick="setRating(5)">★</span>

    <br><br>

    Review:<br>
    <textarea name="reviewText"></textarea><br><br>

    <button type="submit">Submit Review</button>

</form>

<br>

<hr>

<!-- ✅ DISPLAY REVIEWS (CORRECT PLACE) -->
<h3>📢 All Reviews</h3>

<c:if test="${empty reviewList}">
    <p>No reviews yet</p>
</c:if>

<c:forEach var="r" items="${reviewList}">
    <p>
        ⭐ Rating:
        <c:forEach begin="1" end="${r.rating}">
            ⭐
        </c:forEach>
        <br>
        💬 ${r.reviewText}
    </p>
    <hr>
</c:forEach>

<br>
<a href="books">⬅ Back to Books</a>

<!-- ✅ JAVASCRIPT ONLY -->
<script>
function setRating(rating) {
    document.getElementById("ratingValue").value = rating;

    let stars = document.getElementsByClassName("star");

    for (let i = 0; i < stars.length; i++) {
        if (i < rating) {
            stars[i].classList.add("active");
        } else {
            stars[i].classList.remove("active");
        }
    }
}
</script>

</body>
</html>
