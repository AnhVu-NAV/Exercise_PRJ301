<%-- 
    Document   : list
    Created on : Aug 8, 2024, 9:23:43 AM
    Author     : AnhVuNAV
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Human List</title>
</head>
<body>
<h1>Human List</h1>
<c:if test="${not empty message}">
    <div style="color: green;">
        ${message}
    </div>
</c:if>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>DOB</th>
        <th>Gender</th>
        <th>Type</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="human" items="${humans}">
        <tr>
            <td>${human.id}</td>
            <td>${human.name}</td>
            <td><fmt:formatDate value="${human.dob}" pattern="dd-MM-yyyy"/></td>
            <td>${human.gender ? 'Male' : 'Female'}</td>
            <td>${human.type.name}</td>
            <td>
                <a href="EditController?id=${human.id}">
                    <button>edit</button>
                </a>
                <a href="DeleteController?id=${human.id}">
                    <button>delete</button>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="InsertController">
    <button>
        Insert New Human
    </button>
</a>
</body>
</html>