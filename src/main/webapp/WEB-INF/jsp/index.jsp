<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<title>HungryBrowser</title>

<body>

<form action="<c:url value="/"/>" method="post">
    <input class="fa fa-id-badge" type="submit" value="Wyloguj">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</p>

<p>Co się dzieje w Twojej okolicy?</p>

<p>&nbsp;</p>
<ul>
    <c:forEach items="${posts}" var="p">
        <li> <strong><c:out value="${p.message}"/></strong></li>

    </c:forEach>
</ul>
<p>&nbsp;</p>

<p>&nbsp;</p>

<p><a href="/user/dashboard/restaurants">Sprawdź wszystkie knajpy w Twoim mieście.</a></p>
</body>
<ul>

</ul>