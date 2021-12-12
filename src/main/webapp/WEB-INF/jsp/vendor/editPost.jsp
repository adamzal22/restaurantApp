<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<head>
    <style>
        .error {
            color: red;
            border: 2px solid;
        }
    </style>
</head>

<h2>Edytuj Post</h2>
<form:form modelAttribute="post">
    <form:hidden path="id"/>
    Description: <form:textarea path="message" cols="50" rows="15"/><br>
    <form:select path="restaurants.id" items="${restaurants}" itemLabel="name" itemValue="id"/><br>
    <input type="submit" value="Zapisz">
</form:form>