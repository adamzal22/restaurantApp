<%@ page contentType="text/html; charset=UTF-8" %>
<h2>Czy na pewno chcesz usunać użytkownika? </h2>

<form method="post">
    <input type="hidden" name="toRemoveId" value="${user.id}">
    <button type="submit" value="yes" name="confirmed">TAK</button>
    <button type="submit" value="no" name="confirmed">NIE</button>
</form>