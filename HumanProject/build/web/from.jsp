<%-- 
    Document   : from
    Created on : Aug 9, 2024, 10:16:30 AM
    Author     : AnhVuNAV
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${human != null ? "Update Human" : "Create Human"}</title>
</head>
<body>
<h1>${human != null ? "Update Information Human" : "Create New Human"}</h1>
<form action="${human != null ? 'EditController' : 'InsertController'}" method="post">
    <c:if test="${human != null}">
        <input type="hidden" name="id" value="${human.id}" />
    </c:if>
    Name: <input type="text" name="name" value="${human != null ? human.name : ''}" /><br>
    DOB: <input type="date" name="dob" value="${human != null ? human.dob : ''}" /><br>
    Gender: 
    <select name="gender">
        <option value="true" ${human != null && human.gender ? 'selected' : ''}>Male</option>
        <option value="false" ${human != null && !human.gender ? 'selected' : ''}>Female</option>
    </select><br>
    Type: 
    <select name="typeid">
        <c:forEach var="type" items="${types}">
            <option value="${type.typeId}" ${human != null && type.typeId == human.type.typeId ? 'selected' : ''}>${type.name}</option>
        </c:forEach>
    </select><br>
    <input type="submit" value="${human != null ? 'Update' : 'Create'}" />
</form>
<a href="ListController">
    <button>
        Back to list
    </button>
</a>
</body>
</html>

