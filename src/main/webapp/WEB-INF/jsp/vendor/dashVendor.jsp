<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<title>DashboardUser</title>

<body>
<h1 style="text-align: center;">Witaj ${user.login}</h1>

<p style="text-align: right;">User: <c:out value="${user.login}"/><br />
    City: <c:out value="${user.city}"/></p>

<p style="text-align: right;"><a href="/user/find{id}">Konto</a></p>
<hr>
<hr>
<p style="text-align: right;"><a href="/logout">Wyloguj</a></p>

<p><a href="/vendor/addPost">Dodaj nowy post</a></p>
<p>Tablica</p>
<hr>
<p>&nbsp;</p>

    <c:forEach items="${posts}" var="p">
        <strong><c:out value="${p.restaurant.name}: ${p.message}"/></strong>
        <br />
        <a href="editPost?idToEdit=${p.id}">
            Edytuj post
        </a>
        </br />
        <a href="deletePost?toRemoveId=${p.id}">
            Usuń
        </a>
        <hr>


    </c:forEach>

<p>&nbsp;</p>
<hr>
<hr>
<p>&nbsp;</p>

<p><a href="/vendor/dashboard/restaurants">Sprawdź swoje restauracje</a></p>
<p><a href="/vendor/addRestaurant">Dodaj nową restaurację</a></p>
<hr>
<hr>

</body>
