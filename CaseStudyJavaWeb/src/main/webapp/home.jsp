<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 28/03/2022
  Time: 1:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Home Page</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <%--    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"> &lt;%&ndash; icon--%>
  <link rel="stylesheet" href="static/css/style.css">
<%--  <style>--%>
<%--    .col-1 {width: 8.33%}--%>
<%--    .col-2 {width: 16.66%}--%>
<%--    .col-3 {width: 25%}--%>
<%--    .col-4 {width: 33.3%}--%>
<%--    .col-5 {width: 41.66%}--%>
<%--    .col-6 {width: 50%}--%>
<%--    .col-7 {width: 58.33%}--%>
<%--    .col-8 {--%>
<%--      width: 66.66%;}--%>
<%--    .col-9 {--%>
<%--      width: 75%;}--%>
<%--    .col-10 {--%>
<%--      width: 83.33%;}--%>
<%--    .col-11 {--%>
<%--      width: 91.66%;--%>
<%--    }--%>
<%--    .col-12 {--%>
<%--      width: 100%;--%>
<%--    }--%>
<%--    .row {--%>
<%--      content: "";--%>
<%--      display: block;--%>
<%--      clear: both;--%>
<%--    }--%>
<%--    [class*="col-"] {--%>
<%--      float: left;--%>
<%--      padding: 5px;--%>
<%--    }--%>
<%--    @media screen and (max-width: 1000px) {--%>
<%--      [class*="col-"] {--%>
<%--        width: 50%;--%>
<%--      }--%>
<%--      .title {--%>
<%--        background-color: blue;--%>
<%--      }--%>
<%--    }--%>
<%--    .col-2{--%>
<%--      color: #0033CC;--%>
<%--      text-align: center;--%>
<%--      height: 600px;--%>
<%--      background-color: lightgrey;--%>
<%--      padding:10px;--%>
<%--      margin-left: 10px;--%>
<%--    }--%>
<%--    .col-6{--%>
<%--      margin: 20px;--%>
<%--      width: 1000px;--%>
<%--      text-align: justify;--%>
<%--      padding:10px;--%>
<%--    }--%>
<%--    .col-6 h2{--%>
<%--      font-weight: bolder;--%>
<%--      font-size: 50px;--%>
<%--    }--%>
<%--  </style>--%>
</head>
<body>
<style>
  * {
    box-sizing: border-box;
  }

  body {
    margin: 0;
    font-family: arial;
    width:100%;
  }

  /* ?????nh d???ng header */
  .header {
    background-color: deepskyblue;
    color: #58257b;
    padding: 10px;
    text-align: center;
    width: 100%;

  }
  .header h2{
    float: left;
  }
  img { float: left}
  p{ background:bisque}

  /* ?????nh d???ng thanh ??i???u h?????ng tr??n c??ng */
  .topnav {
    overflow: hidden;
    background-color: #FFCC00;
  }

  /* ?????nh d???ng link ??i???u h?????ng */
  .topnav a {
    float: left;
    display: block;
    color: #CC0000;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
  }

  /* Thay ?????i m??u li??n k???t khi di chu???t qua */
  .topnav a:hover {
    background-color: darkviolet;
    color: white;
    font-weight: bold
  }
  .footer{
    font-size: 20px;
    font-weight: bolder;
    text-align: center;
    color: black;
    clear:both;
    margin: 10px;
    padding: 10px;
  }
</style>
<div class="header">
  <img src="static/img/Codegym%20X.png"  width="200" height="50">
  <h2>Nguy???n Th??? H???ng Nhung</h2>
</div>
<div class="topnav">
  <a href="#">Home</a>
  <a href="#"> Employee</a>
  <a href=customers>Customer</a>
  <a href=services>Service</a>
  <a href="#">Contract</a>
</div>
<div class="main">
  <div class="sidebar">
  </div>
</div>
  <div class="col-6">
    <h3>Welcome</h3>
    <article>
      <p>?????n ng??y thu ho???ch nh??ng th????ng l??i ch??a mua do c???a kh???u ph??a B???c ??n ???, nhi???u n??ng d??n tr???ng d??a ??? Gia Lai ?????ng ng???i kh??ng y??n.</p>

      <p>
        ????m 31/12 n??m nay, th??nh ph??? kh??ng b???n ph??o hoa nh?? m???i n??m, vi???c t??? ch???c c??c s??? ki???n m???ng n??m m???i ph??? thu???c v??o c???p ????? d???ch.
      </p>

      <p>
        6 ng?????i ???????c c???nh s??t k???p th???i gi???i c???u kh???i ????m ch??y c??n h??? t???ng 12 chung c?? Tecco Green Nest, qu???n 12, TP.HCM.
        Tuy nhi??n,  c?? hai ng?????i nh???p vi???n do h??t ph???i kh?? ?????c.
      </p>
    </article>
    <hr>
    <h4>Test</h4>
  </div>
</div>
<div>
  <div class="footer">
    <p style="color: #0b2e13">Footer</p>
  </div>
</div>
</div>
</body>
</html>