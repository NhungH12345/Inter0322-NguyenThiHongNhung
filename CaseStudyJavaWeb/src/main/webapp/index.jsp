<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 28/03/2022
  Time: 1:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Home Page</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <%--    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"> &lt;%&ndash; icon--%>
  <link rel="stylesheet" href="css/style.css">
  <style>
    .col-1 {width: 8.33%}
    .col-2 {width: 16.66%}
    .col-3 {width: 25%}
    .col-4 {width: 33.3%}
    .col-5 {width: 41.66%}
    .col-6 {width: 50%}
    .col-7 {width: 58.33%}
    .col-8 {
      width: 66.66%;}
    .col-9 {
      width: 75%;}
    .col-10 {
      width: 83.33%;}
    .col-11 {
      width: 91.66%;
    }
    .col-12 {
      width: 100%;
    }
    .row {
      content: "";
      display: block;
      clear: both;
    }
    [class*="col-"] {
      float: left;
      padding: 5px;
    }
    @media screen and (max-width: 1000px) {
      [class*="col-"] {
        width: 50%;
      }
      .title {
        background-color: blue;
      }
    }
    .col-2{
      color: #0033CC;
      text-align: center;
      height: 600px;
      background-color: lightgrey;
      padding:10px;
      margin-left: 10px;
    }
    .col-6{
      margin: 20px;
      width: 1000px;
      text-align: justify;
      padding:10px;
    }
    .col-6 h2{
      font-weight: bolder;
      font-size: 50px;
    }
  </style>

</head>
<body>
<div class="header">
  <img src="assets/img/logo/Codegym%20X.png" alt="Logo"height="200"width="400" />
  <p>Nguyễn Văn A</p>
</div>
<div id="menu">
  <ul>
    <li><a href="#">Home</a></li>
    <li><a href="#">Employee</a></li>
    <li><a href="#">Customer</a></li>
    <li><a href="#">Service</a></li>
    <li><a href="#">Contract</a></li>
  </ul>
</div>
<form class="box">
  <input type="search" placeholder="Search" aria-label="Search">
  <button type="submit">Search</button>
</form>
<div class="row">
  <div class="col-2">
    <p>Item One</p>
    <p>Item two</p>
    <p>Item three</p>
  </div>
  <div class="col-6">
    <h2>Welcome</h2>
    <article>
      <p>Đến ngày thu hoạch nhưng thương lái chưa mua do cửa khẩu phía Bắc ùn ứ, nhiều nông dân trồng dưa ở Gia Lai đứng ngồi không yên.</p>

      <p>
        Đêm 31/12 năm nay, thành phố không bắn pháo hoa như mọi năm, việc tổ chức các sự kiện mừng năm mới phụ thuộc vào cấp độ dịch.
      </p>

      <p>
        6 người được cảnh sát kịp thời giải cứu khỏi đám cháy căn hộ tầng 12 chung cư Tecco Green Nest, quận 12, TP.HCM.
        Tuy nhiên,  có hai người nhập viện do hít phải khí độc.
      </p>
    </article>
    <hr>
    <h2>Test</h2>
  </div>
</div>
<div>
  <div class="footer">
    <p>Footer</p>
  </div>
</div>
</div>
</body>
</html>