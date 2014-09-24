<%@taglib uri="/WEB-INF/Login.tld" prefix="jstlpg" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  %TODO
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View</title>

    <link href="static/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="wrapper">

    <%@include file="LoginHead.jsp" %>
    <table style="width: 450px; margin-left: 20%; margin-top: 15%">
        <tr>
            <td></td>
            <td>
                <font color="grey" size="6" style=" margin-left: 35%">View</font>
            </td>
        </tr>

        <tr>
            <td class="right"><spring:message code="label.Title"/>:</td>
            <td><c:out value="${Adv.title}"/></td>
        </tr>
        <tr>
            <td class="right"><spring:message code="label.Creator"/>:</td>
            <td><c:out value="${Adv.user.username}"/></td>
        </tr>
        <tr>
            <td class="right"><spring:message code="label.Text"/>:</td>
            <td><c:out value="${Adv.text}"/></td>
        </tr>
        <tr>
            <td class="right"><spring:message code="label.Views"/>:</td>
            <td><c:out value="${Adv.views}"/></td>
        </tr>

    </table>
    <table align="left">

        <c:forEach items="${pics}" var="picName" varStatus="roll">
            <c:if test="${(roll.count - 1)% 5 == 0}"><tr></c:if>
            <td>
                <c:url value="Picture.do" var="imgUrl">
                    <c:param name="picName" value="${picName}"/>
                    <c:param name="Auther" value="${Adv.user.username}"/>
                    <c:param name="AdId" value="${Adv.idAdv}"/>
                </c:url>

                <img src="${imgUrl}" WIDTH="150" ALT="${picName}" align="right"/>

            </td>
            <c:if test="${roll.count % 5 == 0}">
                </tr>
            </c:if>
        </c:forEach>
    </table>

    <table style="width: 450px; margin-left: 20%; margin-top: 15%">
        <tr>
            <td colspan="2"><spring:message code="label.Comments"/></td>
        </tr>

        <c:forEach items="${Adv.comments}" var="comment" varStatus="roll">
            <tr>
                <td style="width: 100px;">
                        ${comment.user.username}
                </td>
                <td>
                        ${comment.commentText}
                </td>
            </tr>
        </c:forEach>

        <c:if test="${sessionScope.UserName ne null}">

            <tr>
                <td colspan="2"><spring:message code="label.AddComment"/></td>
            </tr>
            <tr>
                <form:form action="addComment.do" commandName="Comment" method="POST">
                    <input type="hidden" name="AdId" value="${Adv.idAdv}"/>
                    <td colspan="2">
                        <form:textarea path="commentText" cols="60" rows="4"/>
                        <br>
                        <input class="button_edit" type="submit" value="Add"
                               onMouseOver="style.backgroundColor='grey'"
                               onMouseOut="style.backgroundColor='rgba(223, 227, 216, 0.62)' "/>
                    </td>
                </form:form>
            </tr>
        </c:if>
    </table>
    <div class="clear" align="right"></div>
    <br/>
    <a href="${Back}">Back</a>


<%@include file="Footer.jsp" %>