<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="1" cellpadding="5">
            <caption><h2>Markets</h2></caption>
            <tr>
                <th>path</th>
                <th>method name</th>
                <th>method body</th>
            </tr>
            <c:forEach var="microtask" items="${microtasks}">
                <tr>
                    <td><c:out value="${microtask.pathFile}" /></td>
                    <td><a href="/CODECOD-1.0/microtask/<c:out value="${microtask.pathFile}" />"><c:out value="${microtask.methodName}" /></a></td>
                    <td><c:out value="${microtask.methodBody}" /></td>
                </tr>
            </c:forEach>
        </table>
</body>
</html>