<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Rejestracja</title>
    <style>
        .error {
            color: red;
            border: 2px solid;
        }
    </style>
</head>
<body>

<form:form modelAttribute="user">

    <form:hidden path="id"/><br/>
    login: <form:input path="login"/><br/>
    <form:errors path="login" cssClass="error"/> <br>
    email: <form:input path="email"/><br/>
    <form:errors path="email" cssClass="error"/> <br>
    miasto: <form:select path="city.id" items="${cities}" itemLabel="name" itemValue="id"/><br/>
    <form:errors path="city" cssClass="error"/> <br>
    password: <form:password path="password"/><br/>
    <form:errors path="password" cssClass="error"/> <br>



    <input type="submit" value="Zarejestruj"><br/>

</form:form>

</body>
</html>