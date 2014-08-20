<%@taglib uri="/WEB-INF/Login.tld" prefix="jstlpg" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
 %TODO
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ViewUSer</title>

    <link href="static/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="wrapper">


    <table style="width: 450px; margin-left: 20%; margin-top: 15%">
        <tr>
            <td></td>
            <td>
                <font color="grey" size="6" style=" margin-left: 35%">My Info</font>
            </td>
        </tr>

        <form:form method="POST" commandName="user" action="Profile.do">

        <tr>
            <td class="right">Name:</td>
            <td><form:input path="username"/></td>

        </tr>
        <tr>
            <td class="right">Email:</td>
            <td><form:input path="email"/></td>

        </tr>
        <tr>
            <td colspan="2" align="center" bgcolor="#dcdcdc">
                <span> To  change pass complete next row's</span>
            </td>
        </tr>
        <tr>
            <td class="right">Old Password</td>
            <td><input name="OldPassword" type="password"></td>

        </tr>
        <tr>
            <td class="right">NewPassword</td>
            <td><input name="NewPassword" type="password"></td>

        </tr>
        <tr>
            <td class="right">Reaped Password</td>
            <td><input name="NewPassword2" type="password"></td>

        </tr>
        <tr>
            <td colspan="2" align="center">
                <span style="color: red ">${error}</span>
            </td>
        </tr>
        <tr>
            <td class="right">Avatar:</td>
            <td>
                <c:choose>
                    <c:when test="${sessionScope.Avatar eq null}">
                        <img src="static/img/defaultAvatar.jpg" WIDTH="150" ALT="avatar" align="right"/>
                    </c:when>
                    <c:otherwise>
                        <c:url value="Picture.do" var="imgUrl">
                            <c:param name="picType" value="Avatar"/>
                            <c:param name="picName" value="${sessionScope.Avatar}"/>
                            <c:param name="Auther" value="${sessionScope.UserName}"/>
                            <c:param name="AdId"/>
                        </c:url>

                        <img src="${imgUrl}" WIDTH="150" ALT="${sessionScope.Avatar}" align="right"/>
                    </c:otherwise>
                </c:choose>

            </td>
        </tr>
        <tr>
            <td>
                <div class="right">
                    <input class="button_edit" type="submit" value="Change"
                           onMouseOver="style.backgroundColor='grey'"
                           onMouseOut="style.backgroundColor='rgba(223, 227, 216, 0.62)' "/>
                    <input class="button_edit" type="button" value="Cancel" onclick="CancelButton()"
                           onMouseOver="style.backgroundColor='grey'"
                           onMouseOut="style.backgroundColor='rgba(223, 227, 216, 0.62)' ">
                    <script>
                        function CancelButton() {
                            location.href = "Home.do";
                        }
                    </script>
                </div>
            </td>
        </tr>

    </table>

    </form:form>


<%@include file="Footer.jsp" %>