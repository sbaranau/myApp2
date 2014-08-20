<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  %TODO
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Registration</title>
    <link href="static/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<div id="wrapper" style="border-bottom-width: 10px; padding-bottom: 200px;">
    <div class="right">Not logged in <a href="Login.do">Login</a></div>
        <%
    System.out.println("Reg.jsp");

  %>
    <div>

        <form:form method="POST" commandName="userVar" enctype="multipart/form-data" action="Reg.do">
            <table style="margin-top: 15%; margin-left: 35%; width:450px;">
                <tr>
                    <td colspan="3">
                        <h2>Registration</h2>
                    </td>
                </tr>
                <tr>
                    <td>Login :</td>
                    <td><form:input path="name"/></td>
                    <td><form:errors path="name" cssClass="error"/></td>
                </tr>
                <tr>
                    <td>Password :</td>
                    <td><form:input path="password" type="password"/></td>
                    <td><form:errors path="password" cssClass="error"/></td>
                </tr>
                <tr>
                    <td>Password* :</td>
                    <td><form:input path="comPassword" type="password"/></td>
                    <td><form:errors path="comPassword" cssClass="error"/></td>

                </tr>
                <tr>
                    <td>Email :</td>
                    <td><form:input path="email"/></td>
                    <td><form:errors path="email" cssClass="error"/></td>

                </tr>
                <tr>
                    <td>Email* :</td>
                    <td><form:input path="comEmail"/></td>
                    <td><form:errors path="comEmail" cssClass="error"/></td>

                </tr>
                <tr>
                    <td colspan="3">
                        Avatarka<form:input path="fileData" type="file"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input class="button_edit" type="button" value="Cancel" onclick="CancelButton()"
                               onMouseOver="style.backgroundColor='grey'"
                               onMouseOut="style.backgroundColor='rgba(223, 227, 216, 0.62)' ">
                        <script>
                            function CancelButton() {
                                location.href = "Home.do";
                            }
                        </script>

                        <input class="button_edit" type="submit" value="sent"
                               onMouseOver="style.backgroundColor='grey'"
                               onMouseOut="style.backgroundColor='rgba(223, 227, 216, 0.62)' "/>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="3">
                        <form:errors path="*" cssClass="errorblock" element="div"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <font color="red">${baseerror}</font>
                    </td>
                </tr>
            </table>
        </form:form>

    </div>

<%@include file="Footer.jsp" %>