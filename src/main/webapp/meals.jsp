<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <style>
        TABLE {
            border-collapse: collapse;
        }

        TD, TH {
            padding: 3px;
            border: 1px solid black;
        }

        TH {
            background: #b0e0e6;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<br>
<h3><a href="meals?action=insert">Add meal</a></h3>
<br>

<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th colspan=2>Action</th>
    </tr>

    <jsp:useBean id="mealsTo" scope="request" type="java.util.List"/>
    <c:forEach var="mealTo" items="${mealsTo}">
        <form method="POST" action='meals'>
            <tr style="color:${mealTo.excess ? 'red' : 'green'}">
                <td>
                    <fmt:parseDate value="${mealTo.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                                   type="both"/>
                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}"/>
                </td>
                <td>${mealTo.description}</td>
                <td>${mealTo.calories}</td>
                <td>
                    <a href="meals?action=update&id=${mealTo.id}">Update</a>
                </td>
                <td>
                    <a href="meals?action=delete&id=${mealTo.id}">Delete</a>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
</body>
</html>