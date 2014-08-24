<%@taglib uri="/WEB-INF/Login.tld" prefix="jstlpg" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
            <td class="right">Title:</td>
            <td><c:out value="${Title}"/></td>
        </tr>
        <tr>
            <td class="right">Author:</td>
            <td><c:out value="${Author}"/></td>
        </tr>
        <tr>
            <td class="right">Text:</td>
            <td><c:out value="${Text}"/></td>
        </tr>
        <tr>
            <td class="right">Views:</td>
            <td><c:out value="${Views}"/></td>
        </tr>

    </table>
    <table align="left">

        <c:forEach items="${pics}" var="picName" varStatus="roll">
            <c:if test="${(roll.count - 1)% 5 == 0}"><tr></c:if>
            <td>
                <c:url value="Picture.do" var="imgUrl">
                    <c:param name="picName" value="${picName}"/>
                    <c:param name="Auther" value="${Author}"/>
                    <c:param name="AdId" value="${AdId}"/>
                </c:url>

                <img src="${imgUrl}" WIDTH="150" ALT="${picName}" align="right"/>

            </td>
            <c:if test="${roll.count % 5 == 0}">
                </tr>
            </c:if>
        </c:forEach>
    <tr>
        <c:forEach items="${Comments}" var="comment" varStatus="roll">
        <td>
          <table>
              <tr>
                  <td>
                      ${comment}
                  </td>
              </tr>
          </table>


        </td>
        <c:if test="${roll.count % 5 == 0}">
        </c:if>
        </c:forEach>
    </tr>
    </table>
    <div class="clear" align="right"></div>
    <br/>
    <a href="${Back}">Back</a>


<%@include file="Footer.jsp" %>