<%--
  %TODO
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Successfully</title>
    <link href="static/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="wrapper" style="border-bottom-width: 10px; padding-bottom: 200px;">
    <div style="margin-top: 30%; margin-left: 30%">

        <font color="blue"><a href="Home.do?page=${param.page}">You successfully ${action}! Go to Home page</a> </font>

        <meta http-equiv='Refresh' content='2; url=Home.do?page=${param.page}'>

    </div>
<%@include file="Footer.jsp" %>