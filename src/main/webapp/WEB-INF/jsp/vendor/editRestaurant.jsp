<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edytuj knajpę</title>
</head>
<body>

<form:form modelAttribute="restaurant">

    <form:hidden path="id"/><br/>
    nazwa: <form:input path="name"/><br/>
    kuchnia: <form:input path="cuisine"/><br/>
    miasto: <form:select path="city.id" items="${cities}" itemLabel="name" itemValue="id"/><br/>
    Rating: <form:input path="rating"/><br/>
    Ulica: <form:input path="street"/>, <form:input path="streetNumber"/><br/>


    <input type="submit" value="Dodaj"><br/>

</form:form>

</body>
</html>