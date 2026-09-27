<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Admin Login - Readify</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;700&display=swap" rel="stylesheet">

    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(to right, #000000, #434343);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
        }

        .admin-box {
            width: 100%;
            max-width: 400px;
            padding: 35px;
            border-radius: 20px;
            background: rgba(255,255,255,0.08);
            backdrop-filter: blur(10px);
            box-shadow: 0 10px 30px rgba(0,0,0,0.5);
            animation: fadeIn 0.8s ease-in-out;
        }

        .admin-title {
            text-align: center;
            font-weight: 600;
            margin-bottom: 20px;
        }

        .form-control {
            border-radius: 25px;
            padding: 12px;
            border: none;
        }

        .form-control:focus {
            box-shadow: 0 0 8px rgba(255,255,255,0.5);
        }

        .btn-admin {
            border-radius: 30px;
            padding: 10px;
            font-weight: 600;
            background: #00ffcc;
            border: none;
            transition: 0.3s;
        }

        .btn-admin:hover {
            background: #00c9a7;
            transform: scale(1.05);
        }

        .alert {
            border-radius: 15px;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(25px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
    </style>
</head>

<body>

<div class="admin-box">

    <h3 class="admin-title">🛠 Admin Panel Login</h3>

    <form action="admin" method="post">

        <input type="hidden" name="action" value="login">

        <input class="form-control mb-3" type="text" name="username" placeholder="👤 Username" required>

        <input class="form-control mb-3" type="password" name="password" placeholder="🔑 Password" required>

        <button class="btn btn-admin w-100">Login</button>

    </form>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <div class="alert alert-danger mt-3 text-center"><%= error %></div>
    <%
        }
    %>

</div>

</body>
</html>