<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<title>DashboardUser</title>

<body>
<h1 style="text-align: center;">Witaj ${user.login}</h1>

<p style="text-align: right;">User: <c:out value="${user.login}"/><br />
    City: <c:out value="${user.city}"/></p>

<p style="text-align: right;"><a href="/user/find{id}">Zobacz konto</a></p>
<hr>
<hr>
<p style="text-align: right;"><a href="/logout">Wyloguj</a></p>

<hr>
<p>Co się dzieje w Twojej okolicy?</p>
<hr>
<ul>
<c:forEach items="${posts}" var="p">
   <li> <strong><c:out value="${p.message}"/></strong></li>

</c:forEach>
</ul>
<p>&nbsp;</p>
<hr>
<hr>
<p>&nbsp;</p>

<p><a href="/user/dashboard/restaurants">Sprawdź wszystkie knajpy w Twoim mieście.</a></p>
</body>
