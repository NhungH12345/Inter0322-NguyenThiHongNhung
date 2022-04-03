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
<form action="/customer?action=add" method="post">
    ID          <input type="text" name="id"><br><br>
    Name        <input type="text" name="name"><br><br>
    Birthday    <input type="date" name="birthday"><br><br>
    Gender      <input type="radio" checked name="gender" value="1">Male
                <input type="radio" name="gender" value="0">Female<br><br>
    Phone       <input type="text" name="phone"><br><br>
    Email       <input type="text" name="email"><br><br>

    <select name="type">
        <c:forEach var="type" items="${listType}">
            <option value="${type.id_type}"> ${type.name_type}</option>
        </c:forEach>
    </select><br>
    Address       <input type="text" name="address"><br><br>
    <button type="submit">Add</button>
    <input type="reset" value="Back" style="color:white;background: black"/>
</form>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

</body>
</html>
