<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
	<a href="./notice/noticeList">NOTICE</a>
	<a href="./qna/qnaList">Q&A</a>
	<c:if test="${empty member}">
		<a href="./member/memberLogin">Login</a>
		<a href="./member/memberJoin">Join</a>
	</c:if>
	<c:if test="${not empty member}">
		<a href="./member/memberView">Mypage</a>
		<a href="./member/memberDelete">delete</a>
	</c:if>
</body>
</html>
