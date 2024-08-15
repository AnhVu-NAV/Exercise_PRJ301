<%-- 
    Document   : edit
    Created on : Aug 8, 2024, 11:56:20 AM
    Author     : AnhVuNAV
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Human</title>
</head>
<body>
<h1>Update Information Human</h1>
<form action="EditController" method="post">
    <input type="hidden" name="id" value="${human.id}" />
    Name: <input type="text" name="name" value="${human.name}" /><br>
    DOB: <input type="date" name="dob" value="${human.dob}" /><br>
    Gender: 
    <select name="gender">
        <option value="true" ${human.gender ? 'selected' : ''}>Male</option>
        <option value="false" ${!human.gender ? 'selected' : ''}>Female</option>
    </select><br>
    Type: 
    <select name="typeid">
        <c:forEach var="type" items="${types}">
            <option value="${type.typeId}" ${type.typeId == human.type.typeId ? 'selected' : ''}>${type.name}</option>
        </c:forEach>
    </select><br>
    <input type="submit" value="Update" />
</form>
<a href="ListController">
    <button>
        Back to list
    </button>
</a>
</body>
</html>
