<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="jstlpg" uri="/WEB-INF/Login.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  %TODO
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LoadPhoto</title>
    <link href="static/style.css" rel="stylesheet" type="text/css">
    <script language="JavaScript">
        function Validate() {
            var image = document.getElementById("image").value;
            if (image != '') {
                var checkimg = image.toLowerCase();
                if (!checkimg.match(/(\.jpg|\.png|\.JPG|\.PNG|\.jpeg|\.JPEG|\.bmp)$/)) {
                    alert("it is not a picture or unknown format!!!");
                    document.getElementById("image").focus();
                    return false;
                }
            }
            return true;
        }
    </script>
</head>
<body>
<div id="wrapper" style="border-bottom-width: 10px; padding-bottom: 200px;">
    <div class="right">
        <jstlpg:Login userName='${sessionScope.UserName}'/>

    </div>
    <div>
        <table style="margin-top: 10%; margin-left: 30%; width:350px;">
            <tr>
                <td>
                    <c:if test="${picName ne null}">
                        <span>Pic successfully upload!!!</span>
                        <br/>
                        <c:url var="imgUrl" value="Picture.do">
                            <c:param name="picName" value="${picName}"/>
                            <c:param name="Auther" value="${sessionScope.UserName}"/>
                            <c:param name="AdId" value="${sessionScope.AdId}"/>
                        </c:url>
                        <img src="${imgUrl}" WIDTH="150" ALT="${picName}" align="middle"/>
                    </c:if>
                </td>
            </tr>

            <tr>
                <td>
                    <form:form method="POST" modelAttribute="uploadItem" enctype="multipart/form-data"
                               onSubmit="return Validate();" action="LoadPhoto.do">
                        <form:input path="fileData" id="image" type="file"/>
                        <input name="pics" type="hidden" value="${pics}"/>
                        <input class="button_edit" type="submit" value="Upload"
                               onMouseOver="style.backgroundColor='grey'"
                               onMouseOut="style.backgroundColor='rgba(223, 227, 216, 0.62)' "/>
                    </form:form>

                </td>
            </tr>
            <tr>
                <td>
                    <form:form method="POST" action="LoadPhoto/ready.do">
                        <input name="pics" type="hidden" value="${pics}"/>
                        <input class="button_edit" type="submit" value="ready"
                               onMouseOver="style.backgroundColor='grey'"
                               onMouseOut="style.backgroundColor='rgba(223, 227, 216, 0.62)' "/>
                    </form:form>
                </td>
            </tr>
            <tr>
                <td>
                    <font color="red">${UploadError}</font>
                </td>
            </tr>
        </table>
    </div>

<%@include file="Footer.jsp" %>