<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
String[] quotes = {
    "Every book is a new adventure.",
    "Books open doors to endless worlds.",
    "Reading fuels imagination.",
    "A library is a gateway to knowledge.",
    "Discover your next favorite story."
};

int random = (int)(Math.random() * quotes.length);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Readify Register</title>

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:'Segoe UI',sans-serif;
}

body{
    height:100vh;
    display:flex;
    justify-content:center;
    align-items:center;

    background:
    linear-gradient(
        rgba(0,0,0,0.6),
        rgba(0,0,0,0.6)
    ),
    url('https://images.unsplash.com/photo-1507842217343-583bb7270b66');

    background-size:cover;
    background-position:center;
    background-repeat:no-repeat;
}

.register-card{

    width:450px;
    padding:35px;

    border-radius:25px;

    background:rgba(255,255,255,0.12);

    backdrop-filter:blur(12px);

    border:1px solid rgba(255,255,255,0.2);

    box-shadow:0 8px 32px rgba(0,0,0,0.4);

    color:white;
}

.logo{
    text-align:center;
    font-size:55px;
}

.title{
    text-align:center;
    font-size:30px;
    font-weight:bold;
    margin-top:10px;
}

.subtitle{
    text-align:center;
    color:#ddd;
    margin-top:5px;
}

.quote{
    text-align:center;
    margin-top:15px;
    margin-bottom:20px;
    font-style:italic;
    color:#f5f5f5;
}

.form-group{
    margin-bottom:15px;
}

.form-group label{
    display:block;
    margin-bottom:6px;
}

.form-group input{
    width:100%;
    padding:12px;

    border:none;
    outline:none;

    border-radius:10px;
}

.register-btn{

    width:100%;
    padding:14px;

    border:none;
    border-radius:30px;

    background:linear-gradient(
        45deg,
        #4facfe,
        #00f2fe
    );

    color:white;
    font-size:16px;
    font-weight:bold;

    cursor:pointer;
}

.register-btn:hover{
    opacity:0.9;
}

.links{
    text-align:center;
    margin-top:20px;
}

.links a{
    color:white;
    text-decoration:none;
}

.links a:hover{
    color:#00f2fe;
}

.error{
    text-align:center;
    color:#ffb3b3;
    margin-top:15px;
}

.success{
    text-align:center;
    color:#b6ffb6;
    margin-top:15px;
}

</style>

</head>

<body>

<div class="register-card">

<div class="logo">📚</div>

<div class="title">READIFY</div>

<div class="subtitle">
    Create Your Reader Account
</div>

<div class="quote">
    "<%= quotes[random] %>"
</div>

<form action="user" method="post">

<input type="hidden" name="action" value="register"/>

<div class="form-group">
    <label>Full Name</label>
    <input type="text"
           name="fullname"
           value="${fullname}"
           placeholder="Enter your full name"
           required>
</div>

<div class="form-group">
    <label>Email Address</label>
    <input type="email"
           name="email"
           value="${email}"
           placeholder="Enter your email"
           required>
</div>

<div class="form-group">
    <label>Password</label>
    <input type="password"
           name="password"
           placeholder="Create password"
           required>
</div>

<div class="form-group">
    <label>Phone Number</label>
    <input type="text"
           name="phone"
           value="${phone}"
           placeholder="Enter phone number"
           required>
</div>

<button type="submit" class="register-btn">
    Create Account
</button>

</form>

<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>

<c:if test="${not empty success}">
    <div class="success">${success}</div>
</c:if>

<div class="links">
    Already have an account?
    <a href="userLogin.jsp">Login Here</a>
</div>

</div>

</body>
</html>