<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 02/04/2022
  Time: 7:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Delete Customer</h1>
ID:<span>${customer.id}</span>
Name:<span>${student.name}</span>
Birthday:<span>${student.birthday}</span>
Gender:<span>${student.gender}</span>
Emal:<span>${student.email}</span>
<form action="/customer?action=delete&id=${customer.id}" method="post">
    <button type="submit">Delete</button>
</form>
</body>
</html>
