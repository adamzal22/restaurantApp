<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edytuj użytkownika</title>
</head>
<body>

<form:form modelAttribute="user">

    <form:hidden path="id"/><br/>
    login: <form:input path="login"/><br/>
    email: <form:input path="email"/><br/>
    miasto: <form:select path="city.id" items="${cities}" itemLabel="name" itemValue="id"/><br/>
    password: <form:input path="password"/><br/>


    <input type="submit" value="Zapisz"><br/>

</form:form>

</body>
</html>