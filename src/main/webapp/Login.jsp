<%--
  %TODO
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="label.LoginPage"/></title>
    <link href="static/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="wrapper" style="border-bottom-width: 10px; padding-bottom: 200px;">

    <form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>

        <table style="width: 450px; margin-left: 35%; margin-top: 20%">
            <tr>
                <td width="60px"></td>
                <td>
                    <h2><spring:message code="label.Login"/></h2>
                </td>
                <td></td>
            </tr>
            <tr>
                <td colspan="3">
                    <c:if test="${error}">
                        <div class="errorblock">
                            Your login attempt was not successful, try again.<br/>
                            Caused : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                        </div>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td><spring:message code="label.User"/>:</td>
                <td><input type='text' name='j_username'/>
                </td>
            </tr>
            <tr>
                <td><spring:message code="label.Password"/>:</td>
                <td><input type='password' name='j_password'/>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <input type='checkbox' name='_spring_security_remember_me'/> <spring:message
                        code="label.RememberMe"/><br/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="<spring:message code="label.Login"/>" class="button_edit"/>
                </td>
                <td>
                    <input type="reset" value="<spring:message code="label.Clear"/>" class="button_edit"/>
                    <input type="button" value="<spring:message code="label.Cancel"/>" class="button_edit"
                           onclick="CancelButton()"
                           onMouseOver="style.backgroundColor='grey'"
                           onMouseOut="style.backgroundColor='rgba(223, 227, 216, 0.62)' ">
                    <script>
                        function CancelButton() {
                            location.href = "${Back}";
                        }
                    </script>
                </td>
                <td></td>
            </tr>

        </table>

    </form>

<%@include file="Footer.jsp" %>