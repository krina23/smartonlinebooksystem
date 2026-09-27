<%@ page contentType="text/html;charset=UTF-8" %>

<%
    String admin = (String) session.getAttribute("admin");

    if (admin == null) {
        response.sendRedirect("adminLogin.jsp");
        return;
    }
%>

<html>
<head>
<title>Admin Dashboard - Readify</title>

<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Google Font -->
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;700&display=swap" rel="stylesheet">

<style>
    body {
        font-family: 'Poppins', sans-serif;
        background: linear-gradient(to right, #000000, #434343);
        color: white;
        min-height: 100vh;
    }

    .dashboard {
        margin-top: 50px;
    }

    .card-box {
        padding: 30px;
        border-radius: 20px;
        background: rgba(255,255,255,0.08);
        backdrop-filter: blur(10px);
        text-align: center;
        transition: 0.3s;
    }

    .card-box:hover {
        transform: translateY(-8px) scale(1.03);
        box-shadow: 0 15px 30px rgba(0,0,0,0.5);
    }

    .card-box h4 {
        margin-bottom: 15px;
        font-weight: 600;
    }

    .btn-custom {
        border-radius: 25px;
        padding: 10px 20px;
        font-weight: 500;
    }

    .header {
        text-align: center;
        margin-bottom: 40px;
    }

    .logout-btn {
        background: #ff4d4d;
        border: none;
    }

    .logout-btn:hover {
        background: #e60000;
    }
</style>
</head>

<body>

<div class="container dashboard">

    <div class="header">
        <h2>👨‍💼 Admin Dashboard</h2>
        <p>Manage your bookstore easily</p>
    </div>

    <div class="row g-4">

        <!-- ADD BOOK -->
        <div class="col-md-4">
            <div class="card-box">
                <h4>📚 Add New Book</h4>
                <a href="addBook.jsp" class="btn btn-success btn-custom">Add Book</a>
            </div>
        </div>

        <!-- MANAGE BOOKS -->
        <div class="col-md-4">
            <div class="card-box">
                <h4>📖 Manage Books</h4>
                <a href="books" class="btn btn-primary btn-custom">View Books</a>
            </div>
        </div>

        <!-- LOGOUT -->
        <div class="col-md-4">
            <div class="card-box">
                <h4>🚪 Logout</h4>
                <a href="admin?action=logout" class="btn logout-btn btn-custom">Logout</a>
            </div>
        </div>

    </div>

</div>

</body>
</html>