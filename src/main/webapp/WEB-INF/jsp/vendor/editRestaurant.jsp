<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edytuj knajpę</title>
    <style>
        .error {
            color: red;
            border: 2px solid;
        }
    </style>
</head>
<body>

<form:form modelAttribute="restaurant">

    <form:hidden path="id"/><br/>
    nazwa: <form:input path="name"/><br/>
    <form:errors path="name" cssClass="error"/><br/>
    kuchnia: <form:input path="cuisine"/><br/>
    <form:errors path="cuisine" cssClass="error"/><br/>
    miasto: <form:select path="city.id" items="${cities}" itemLabel="name" itemValue="id"/><br/>
    Rating: <form:input path="rating"/><br/>
    <form:errors path="rating" cssClass="error"/><br/>
    Ulica: <form:input path="street"/>, <form:input path="streetNumber"/><br/>
    <form:errors path="street" cssClass="error"/><br/>

    <input type="submit" value="Zapisz"><br/>

</form:form>

</body>
</html>