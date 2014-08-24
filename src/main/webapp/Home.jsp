<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%--
 %TODO
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link href="static/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<%@include file="LoginHead.jsp" %>
<div id="wrapper">


    <div>
        <div>
            <c:if test="${sessionScope.UserName ne null}">
                <div class="left">
                    <input class="button_edit" type="button" value="<spring:message code="label.Create"/>"
                           onclick="CreateButton()"
                           onMouseOver="style.backgroundColor='grey'"
                           onMouseOut="style.backgroundColor='rgba(223, 227, 216, 0.62)' "/>
                    <script>
                        function CreateButton() {
                            location.href = "Create.do";
                        }
                    </script>
                </div>
            </c:if>
        </div>
        <div class="left">

            <span>${PreviousPage}</span>
            <c:choose>
                <c:when test="${CurrentPage eq null}">
                    <span><spring:message code="label.NoData"/></span>
                </c:when>
                <c:otherwise>
                    <span><spring:message code="label.ShownAdvs"/>${CurrentPage}</span>
                </c:otherwise>
            </c:choose>

            <span>${NextPage}</span>

        </div>
        <br/>
        <br/>

        <div>
            <table cellpadding="0" cellspacing="0" id="table">
                <tr bgcolor="#778899">
                    <td width="180px">
                        <spring:message code="label.Date"/>
                    </td>
                    <td width="70em">
                        <spring:message code="label.Action"/>
                    </td>
                    <td>
                        <spring:message code="label.Title"/>
                    </td>
                    <td width="70em">
                        <spring:message code="label.Views"/>
                    </td>
                    <td width="100em">
                        <spring:message code="label.User"/>
                    </td>
                    <td width="50em">
                        <spring:message code="label.Comments"/>
                    </td>
                    <td width="30em">
                        <spring:message code="label.Foto"/>
                    </td>
                    <td class="empty">
                    </td>
                </tr>

                <c:forEach items="${Advs}" var="parameter" varStatus="roll">

                    <tr
                            <c:if test="${roll.count % 2 eq 0}">class="grey"</c:if> >
                        <td>
                                ${parameter.date}
                        </td>
                        <td>
                            <font color="<c:choose>
                            <c:when test="${parameter.activity eq 1}">
                                blue"> sell
                                </c:when>
                                <c:when test="${parameter.activity eq 2}">
                                    green">buy
                                </c:when>
                                <c:otherwise>
                                    red"> change
                                </c:otherwise>
                                </c:choose>
                            </font>
                        </td>
                        <td>
                            <a href="ViewAd.do?AdId=${parameter.idAdv}&page=${param.page}">${ parameter.title}</a>
                        </td>
                        <td>

                                ${parameter.views}
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${parameter.user.idUser eq sessionScope.UserId}">
                                    ${parameter.user.username}
                                </c:when>
                                <c:otherwise>
                                    <a href="ViewUser.do?IdUser=${parameter.user.idUser}&page=${param.page}">${parameter.user.username}</a>
                                </c:otherwise>
                            </c:choose>

                        </td>
                        <td>
                                ${parameter.comments}
                        </td>
                        <td>
                            <c:if test="${parameter.picture ne null}">

                                <img src="static/img/photo.png" HEIGHT="30" ALT="foto" align="middle"/>
                            </c:if>
                        </td>
                        <td class="buttom">
                            <c:if test="${parameter.user.idUser eq sessionScope.UserId}">
                                <form action="UpdateAd.do" method="get">
                                    <input type="hidden" name="AdId" value="${parameter.idAdv}"/>
                                    <input type="hidden" name="page" value="${param.page}"/>
                                    <input class="button_edit" type="submit" value="<spring:message code="label.Edit"/>"
                                           onMouseOver="style.backgroundColor='grey'"
                                           onMouseOut="style.backgroundColor='rgba(223, 227, 216, 0.62)' ">
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="7" style="border: 0; ">
                        <spring:message code="label.AdvsOnPage"/>:<select value=1 id="countity" onchange="Reload()">
                        <option value="10"
                                <c:if test="${sessionScope.countity eq 10}">selected="selected"</c:if> >10
                        </option>
                        <option value="20"
                                <c:if test="${sessionScope.countity eq 20}">selected="selected"</c:if> >20
                        </option>
                        <option value="50"
                                <c:if test="${sessionScope.countity eq 50}">selected="selected"</c:if> >50
                        </option>
                        <option value="2"
                                <c:if test="${sessionScope.countity eq 2}">selected="selected"</c:if> >all
                        </option>
                    </select>
                        <script type="text/javascript">

                            function Reload() {
                                var countity = document.getElementById("countity").value;
                                location.href = "Home.do?countity=" + countity;
                            }
                        </script>
                        <c:if test="${countOfPages gt 1}">
                            <c:forEach begin="1" end="${countOfPages}" varStatus="stat">
                                <a href="Home.do?page=${stat.current}"><c:out value="${stat.current}"/></a>
                            </c:forEach>
                        </c:if>
                    </td>
                </tr>

            </table>
        </div>
    </div>


    <%@include file="Footer.jsp" %>
