<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>MongoBlog</title>
</head>
<body>
<h1>Posts</h1>
<br/>
<br/>

<c:forEach items="${posts}" var="post">

	${post.title} - ${post.dateCreated}<br/><br/>
	${post.content}
	<hr/><br/>
	
</c:forEach>

</body>
</html>