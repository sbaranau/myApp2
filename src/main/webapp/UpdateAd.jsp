<%@ taglib prefix="jstlpg" uri="/WEB-INF/Login.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  %TODO
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
    <link href="static/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="wrapper">


    <div class="right">
        <jstlpg:Login userName="${sessionScope.UserName}"/>
    </div>

    <div style="margin-top: 10%; margin-left: 20%; width: 600px">
        <form:form action="UpdateAd.do" commandName="adv" method="POST">


        <input type="hidden" name="page" value="${param.page}"/>


        <table>
            <tr>
                <td></td>
                <td>
                    <font color="grey" size="6" style=" margin-left: 35%">Edit</font>
                </td>
            </tr>
            <tr>
                <td>
                    Activity:
                </td>
                <td>
                    <form:select path="activity">
                        <form:option value="1" label="sell"/>
                        <form:option value="2" label="buy"/>
                        <form:option value="3" label="change"/>
                    </form:select>

                </td>
                <td>
                    <form:errors path="activity" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td>Title:</td>
                <td><form:input path="title" size="45"/></td>
                <td><form:errors path="title" cssClass="error"/></td>
            </tr>
            <tr>
                <td><font>Text: </font></td>
                <td><form:textarea path="text" rows="8" cols="45"/></td>
                <td><form:errors path="text" cssClass="error"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="checkbox" name="loadPhoto" value="loadPhoto">I want to upload new photo
                </td>
            </tr>
            <tr>
                <td>
                    <div align="center">
                        <input class="button_edit" type="button" value="Delete Adv" onclick="DeleteButton()"
                               onMouseOver="style.backgroundColor='ba0800'"
                               onMouseOut="style.backgroundColor='ffb190' ">
                        <script>
                            function DeleteButton() {
                                location.href = "UpdateAd/delete.do";
                            }
                        </script>
                    </div>
                </td>
                <td>
                    <div class="right">
                        <input class="button_edit" type="button" value="Cancel" onclick="CancelButton()"
                               onMouseOver="style.backgroundColor='grey'"
                               onMouseOut="style.backgroundColor='rgba(223, 227, 216, 0.62)' ">
                        <script>
                            function CancelButton() {
                                location.href = "${Back}";
                            }
                        </script>
                        <input class="button_edit" type="submit" value="Update"
                               onMouseOver="style.backgroundColor='grey'"
                               onMouseOut="style.backgroundColor='rgba(223, 227, 216, 0.62)' "/>

                    </div>
                </td>

            </tr>
        </table>

        <font style="color: red;"><br/>

            <p>${error}</p></font>
    </div>

    <table align="left">

        <c:forEach items="${pics}" var="picName" varStatus="roll">
            <c:if test="${(roll.count - 1)% 5 == 0}">
                <tr>
            </c:if>
            <td>

                <c:url value="Picture.do" var="imgUrl">
                    <c:param name="picName" value="${picName}"/>
                    <c:param name="Auther" value="${sessionScope.UserName}"/>
                    <c:param name="AdId" value="${sessionScope.AdId}"/>
                </c:url>

                <img src="${imgUrl}" WIDTH="150" ALT="${picName}" align="right"/>
                <br/>
                <input type="checkbox" name="picForDelete" value="${picName}">delete
            </td>

            <c:if test="${roll.count % 5 == 0}">
                </tr>
            </c:if>
        </c:forEach>

    </table>
    </form:form>

<%@include file="Footer.jsp" %>