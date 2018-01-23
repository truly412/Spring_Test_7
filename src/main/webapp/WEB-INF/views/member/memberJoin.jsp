<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#id").keyup(function() {
			var id = $(this).val();
			$.ajax({
				url:"./memberIdCheck",
				type:"GET",
				data:{
					id:id
				},
				success:function(data){
					$("#result").html(data);
				}
			})
		})
	});

</script>
</head>
<body>
	<h1>memberJoin</h1>
	<form action="./memberJoin" method="post" enctype="multipart/form-data">
		<P>id<input type="text" name="id" id="id"></P>
		<div id="result"></div>
		<P>pw<input type="text" name="pw"></P>
		<P>name<input type="text" name="name"></P>
		<P>email<input type="text" name="email"></P>
		<P>phone<input type="text" name="phone"></P>
		<P>age<input type="text" name="age"></P>
		<P>job<input type="text" name="job"></P>
		<p>photo<input type="file" name="file"></p>
		<button>Join</button>
	</form>
</body>
</html>

















