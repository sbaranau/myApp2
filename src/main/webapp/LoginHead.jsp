<span style="float: right; padding-right: 2%">
        <a href="?locate=en">en</a>
        <a href="?locate=ru">ru</a>
    </span>

<br/>

<div style="padding-right: 5%; float: right;">
    <c:choose>
        <c:when test="${sessionScope.UserName ne null}">
            <table cellpadding="0" cellspacing="0" width="100" align="right">
                <tr>
                    <td>
                        <spring:message code="label.LoginAs"/>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                            ${sessionScope.UserName}
                    </td>
                </tr>
                <tr>
                    <td>

                        <c:choose>
                            <c:when test="${sessionScope.Avatar eq null}">
                                ${sessionScope.Avatar}
                                <img src="static/img/defaultAvatar.jpg" WIDTH="150" ALT="avatar" align="right"/>
                            </c:when>
                            <c:otherwise>

                                <c:url value="Picture.do" var="imgUrl">
                                    <c:param name="picType" value="Avatar"/>
                                    <c:param name="picName" value="${sessionScope.Avatar}"/>
                                    <c:param name="Auther" value="${sessionScope.UserName}"/>
                                    <c:param name="AdId"/>
                                </c:url>

                                <img src="${imgUrl}" WIDTH="150" ALT="${picName}" align="right"/>

                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <a href="Profile.do"><spring:message code="label.Profile"/> </a>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <a href="Logout.do"><spring:message code="label.Logout"/> </a>
                    </td>
                </tr>
            </table>
        </c:when>
        <c:otherwise>
            <div style="padding-right: 5%; float: right;">
                <span><spring:message code="label.NotLogin"/><br/> <a href="Login.do"> <spring:message
                        code="label.Login"/> </a><br/><a href="Reg.do"><spring:message
                        code="label.Registration"/></a></span>
            </div>
        </c:otherwise>
    </c:choose>


</div>