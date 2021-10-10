<%@ page contentType="text/html;charset=UTF-8" %>
>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <title>Edit meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit meal</h2>
<br><br>
<form method="POST" action='meals?id=${meal.id}'>
    Date : <input type="datetime-local" name="datetime"
                  value="<c:out value="${meal.dateTime}"/>"/>
    <br><br>
    Description : <input type="text" name="description" value="<c:out value="${meal.description}"/>"/>
    <br><br>
    Calories : <input type="number" name="calories" value="<c:out value="${meal.calories}"/>"/>
    <br><br>
    <input type="submit" value="Save"/>
    <a href="meals"><input type="button" value="Cancel"/></a>
</form>
</body>
</html>