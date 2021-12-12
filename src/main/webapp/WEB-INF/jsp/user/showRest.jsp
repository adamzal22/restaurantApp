<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Dane Restauracji</h1>
<h2><c:out value="${restaurant.name} "/></h2><br>
<p></p>
<hr>
<hr>
<c:out value="Adres: ${restaurant.street}, ${restaurant.streetNumber}"/><br>
<c:out value="kuchnia: ${restaurant.cuisine}"/><br>
<c:out value="Ocena:${restaurant.rating}"/>
<hr>

<p style="text-align: right" ><a href="/user/dashboard" ><strong>Powrót</strong></a></p>