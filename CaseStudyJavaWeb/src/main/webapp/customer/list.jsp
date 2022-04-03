<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title> Customer List</title>
</head>
<body>
<center>
    <h1>Customer List</h1>
    <h2>
        <a href="/customer?action=create">Add New Customer</a>
        <%--        <a href="/users?action=sort">Sort User</a>--%>
        <%--        <input type="text" name="country" id="country" size="15"/>--%>
        <%--        <a href="/users?action=search">Search</a>--%>
    </h2>
    <form method="get" action="/customer">
        <input type="hidden" name="action" value="search">
        <input type="text" name="name">
        <button type="submit">Search</button>
    </form>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Birthday</th>
            <th>Gender</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Name Type</th>
            <th>Address</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="customer" items="${listCustomer}">
            <tr>
                <td>${customer.id}</td>
                <td>${customer.name}</td>
                <td>${customer.birthday}</td>
<%--                <td><c:out value="${customer.gender}"/></td>--%>
                <td>
                    <c:if test="${student.gender}">
                        <sp>Male</sp>
                    </c:if>
                    <c:if test="${!student.gender}">
                        <sp>Female</sp>
                    </c:if>
                </td>
                <td>${customer.phone}</td>
                <td>${customer.email}</td>
                <td>${customer.getType().getName_type()}</td>
                <td>${customer.address}</td>

                <td>
                    <a href="/customer?action=edit&id=${customer.id}">Edit</a>
                    <a href="/customer?action=delete&id=${customer.id}">Delete</a>
                <td><button type="button" onclick="change1('${customer.id}','${customer.name}')">Delete</button></td>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<script>
    function change1(id,name) {
        alert(id+name);
    }
</script>
</body>
</html>