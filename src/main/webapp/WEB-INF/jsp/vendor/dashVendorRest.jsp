<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<title>DashboardUser</title>

<body>
<h1 style="text-align: center;">Witaj ${user.login}</h1>

<p style="text-align: right;">User: <c:out value="${user.login}"/><br />
    City: <c:out value="${user.city}"/></p>

<p style="text-align: right;"><a href="/vendor/find{id}">Zobacz konto</a></p>
<hr>
<p style="text-align: right;"><a href="/logout">Wyloguj</a></p>
<p>Twoje restauracje</p>
<hr>
<%@include file="../search.jsp"%> <%--okienko search--%>

<ul>
    <c:forEach items="${restaurants}" var="r">
        <li> <strong><c:out value="${r.name}"/></strong><text>   OCENA: </text><strong><c:out value="${r.rating}"/></strong></li>
<br />
        <a href="editRestaurant?idToEdit=${r.id}">
            Edytuj post
        </a>
        </br />
        <a href="deletePost?toRemoveId=${r.id}">
            Usuń
        </a>
        <hr>
    </c:forEach>
</ul>
<p>&nbsp;</p>
<hr>
<p>&nbsp;</p>
<p><a href="/vendor/addRestaurant">Dodaj nową restaurację</a></p>
<p><a href="/vendor/dashboard">Sprawdź posty</a></p>
<hr>
<hr>
</body>
<ul>

</ul>