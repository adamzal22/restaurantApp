<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<strong>ALL USERS</strong><br>
<p style="text-align: right;"><a href="/logout">Wyloguj</a></p>
<hr>
<p style="text-align: right;"><a href="/admin/allRestaurants"><strong>ALL RESTAURANTS</strong></a><br></p>
<p style="text-align: right;"><a href="/admin/allPosts"><strong>ALL POSTS</strong></a><br></p>

<hr>
<c:forEach items="${users}" var="u">


    <Strong>LOGIN: ${u.login} | EMAIL: ${u.email}</Strong><br>
    <br />
    <a href="editUser?idToEdit=${u.id}">
        Edytuj
    </a>
    </br />
    <a href="deleteUser?toRemoveId=${u.id}">
        Usuń
    </a>
    <hr>

</c:forEach>

<hr>
