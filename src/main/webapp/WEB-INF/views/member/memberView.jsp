<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<h1>Member Update</h1>
	<form action="./memberUpdate" method="post">
		id<input type="text" name="id" value="${list.id}" readonly="readonly">
		pw<input type="text" name="pw" value="${list.pw}">
		name<input type="text" name="name" value="${list.name}">
		email<input type="text" name="email" value="${list.email}">
		phone<input type="text" name="phone" value="${list.phone}">
		age<input type="text" name="age" value="${list.age}">
		job<input type="text" name="job" value="${list.job}">
		<input type="text" name="oname" value="${list.oname}" readonly="readonly"><span class="del" id="${list.fname}">X</span>
		<input type="text" name="fname" value="${list.fname}" readonly="readonly">
		<button>Update</button>
	</form>

</body>
</html>