<%-- 
    Document   : insert
    Created on : Aug 8, 2024, 9:24:35 AM
    Author     : AnhVuNAV
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Insert Human</title>
</head>
<body>
<h1>Insert Human</h1>
<form action="InsertController" method="post">
    Name: <input type="text" name="name"/><br>
    DOB: <input type="date" name="dob"/><br>
    Gender: 
    <select name="gender">
        <option value="true">Male</option>
        <option value="false">Female</option>
    </select><br>
    Type: 
    <select name="typeid">
        <c:forEach var="type" items="${types}">
            <option value="${type.typeId}">${type.name}</option>
        </c:forEach>
    </select><br>
    <input type="submit" value="Insert"/>
</form>
<a href="ListController">
    <button>
        Back to list
    </button>
</a>
</body>
</html>
