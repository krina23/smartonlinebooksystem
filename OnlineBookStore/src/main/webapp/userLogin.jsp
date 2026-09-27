<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
String[] quotes = {
    "A reader lives a thousand lives before he dies.",
    "Books are a uniquely portable magic.",
    "Reading is dreaming with open eyes.",
    "Today a reader, tomorrow a leader.",
    "Knowledge begins with a book."
};

int random = (int)(Math.random() * quotes.length);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Readify Login</title>

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

.login-card{

    width:420px;
    padding:40px;

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
    font-size:32px;
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
    margin-top:20px;
    margin-bottom:25px;
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

.login-btn{

    width:100%;
    padding:14px;

    border:none;
    border-radius:30px;

    background:linear-gradient(
        45deg,
        #ff6b6b,
        #ffb347
    );

    color:white;
    font-size:16px;
    font-weight:bold;

    cursor:pointer;
}

.login-btn:hover{
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
    color:#ffb347;
}

.reader-count{
    text-align:center;
    margin-top:20px;
    color:#ddd;
}

.message{
    text-align:center;
    color:#ffcccc;
    margin-top:15px;
}

</style>
</head>

<body>

<div class="login-card">

<div class="logo">📚</div>

<div class="title">READIFY</div>

<div class="subtitle">
    Online Book Store
</div>

<div class="quote">
    "<%= quotes[random] %>"
</div>

<form action="user" method="post">

    <input type="hidden" name="action" value="login"/>

    <div class="form-group">
        <label>Email</label>
        <input type="email"
               name="email"
               placeholder="Enter your email"
               required/>
    </div>

    <div class="form-group">
        <label>Password</label>
        <input type="password"
               name="password"
               placeholder="Enter your password"
               required/>
    </div>

    <button type="submit" class="login-btn">
        Login
    </button>

</form>

<div class="links">
    <a href="userRegister.jsp">Create Account</a>
    |
    <a href="forgotPassword.jsp">Forgot Password?</a>
</div>

<div class="reader-count">
    👥 Trusted by Thousands of Readers
</div>

<div class="message">
    ${message}
</div>

</div>

</body>
</html>