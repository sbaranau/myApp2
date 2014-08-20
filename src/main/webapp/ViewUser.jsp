<%@taglib uri="/WEB-INF/Login.tld" prefix="jstlpg" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%--
  %TODO
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ViewUser</title>

    <link href="static/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="wrapper">

    <%@include file="LoginHead.jsp" %>
    <table style="width: 450px; margin-left: 20%; margin-top: 15%">
        <tr>
            <td></td>
            <td>
                <font color="grey" size="6" style=" margin-left: 35%">User Info</font>
            </td>
        </tr>

        <tr>
            <td class="right">Name:</td>
            <td><c:out value="${User.username}"/></td>
        </tr>
        <tr>
            <td class="right">Email:</td>
            <td><c:out value="${User.email}"/></td>
        </tr>
        <tr>
            <td class="left">Avatar:</td>
            <td>
                <c:choose>
                    <c:when test="${User.avatar eq null}">
                        <img src="static/img/defaultAvatar.jpg" WIDTH="150" ALT="avatar" align="right"/>
                    </c:when>
                    <c:otherwise>
                        <c:url value="Picture.do" var="imgUrl">
                            <c:param name="picType" value="Avatar"/>
                            <c:param name="picName" value="${User.avatar}"/>
                            <c:param name="Auther" value="${User.username}"/>
                            <c:param name="AdId"/>
                        </c:url>

                        <img src="${imgUrl}" WIDTH="150" ALT="${User.username}" align="left"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>


    </table>


    <div class="clear" align="right"></div>
    <br/>
    <a href="${Back}">Back</a>


<%@include file="Footer.jsp" %>