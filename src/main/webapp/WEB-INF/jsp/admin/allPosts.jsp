<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<strong>ALL POSTS</strong><br>
<p style="text-align: right;"><a href="/logout">Wyloguj</a></p>
<hr>
<p style="text-align: right;"><a href="/admin/allRestaurants"><strong>ALL RESTAURANTS</strong></a><br></p>
<p style="text-align: right;"><a href="/admin/allUsers"><strong>ALL USERS</strong></a><br></p>


<hr>
<c:forEach items="${posts}" var="p">
    <strong><c:out value="${p.restaurant.name}: ${p.message}"/></strong><br>
    </strong>
    <br />
    <a href="editPost?idToEdit=${p.id}">
        Edytuj
    </a>
    </br />
    <a href="deletePost?toRemoveId=${p.id}">
        Usuń
    </a>
    <hr>
</c:forEach>