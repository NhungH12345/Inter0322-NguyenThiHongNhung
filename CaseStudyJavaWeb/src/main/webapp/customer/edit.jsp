<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 02/04/2022
  Time: 7:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Edit Customer</h1>
<form action="/customer?action=edit" method="post" >
    ID      <input type="text" name="id" value="${customer.id}"><br>
    Name      <input type="text" name="name" value="${customer.name}"><br>
    Birthday   <input type="date" name="birthday" value="${student.birthday}"><br>
    Type      <select name="typeId">
                <option value="0">--Chọn loại--</option>
            <c:forEach var="type" items="${listType}">
           <option value="${type.id_type}"> ${type.name_type}</option>
            </c:forEach>
                </select><br>
    Gender      <input type="radio" ${student.gender=="1"?"checked":""} name="gender" value="1">Male
                 <input type="radio" name="gender"${student.gender=="0"?"checked":""} value="0">Female<br>
    Phone      <input type="text" name="phone" value="${customer.phone}"><br>
    Email       <input type="text" name="email" value="${customer.email}"><br>
    Address       <input type="text" name="address" value="${customer.address}"><br>
                <button type="submit">Save</button>
</form>
</body>
</html>
