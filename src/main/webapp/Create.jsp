<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/WEB-INF/Login.tld" prefix="jstlpg" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%--
  %TODO
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
    <link href="static/style.css" rel="stylesheet" type="text/css">
</head>
<body>


<div id="wrapper">
    <div class="right">
        <jstlpg:Login userName='${sessionScope.UserName}'/>

    </div>
    <div style="margin-top: 20%; margin-left: 25%; width: 550px">
        <form:form method="POST" commandName="adv" action="Create.do">


            <table>
                <tr>

                    <td colspan="3">
                        <font color="grey" size="6" style=" margin-left: 35%">Create</font>
                    </td>
                </tr>
                <tr>
                    <td>Action:</td>
                    <td colspan="1">
                        <form:select path="activity">
                            <form:option value="" label="Select"/>
                            <form:option value="1" label="sell"/>
                            <form:option value="2" label="buy"/>
                            <form:option value="3" label="change"/>
                        </form:select>
                    </td>
                    <td><form:errors path="activity" cssClass="error"/></td>
                </tr>
                <tr>
                    <td>Title:</td>
                    <td><form:input path="title" size="45"/></td>
                    <td><form:errors path="title" cssClass="error"/></td>
                </tr>
                <tr>
                    <td>Text:</td>
                    <td><form:textarea path="text" rows="8" cols="35" name='Text'/></td>
                    <td><form:errors path="text" cssClass="error"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        Do you want to add some photo?
                    </td>
                    <td>
                        <input type="radio" name="photo" value="yes" checked="checked"/> Yes, of course<br/>
                        <input type="radio" name="photo" value="no"/> No, thanks<br/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <div class="right">
                            <input class="button_edit" type="reset" value="Clear"
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
                    <td>

                        <input class="button_edit" type="submit" value="Create"
                               onMouseOver="style.backgroundColor='grey'"
                               onMouseOut="style.backgroundColor='rgba(223, 227, 216, 0.62)' "/>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <c:if test="${error}">
                            <font color="red">error with base</font>
                        </c:if>
                    </td>
                </tr>
            </table>

        </form:form>

    </div>

<%@include file="Footer.jsp" %>