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
		$(".del").click(function() {
			var del = $(this);
			var id = $(this).attr("id");
			var fname = $(this).attr("title");
			var check = confirm("sure?");
			if(check) {
				$.ajax({
					url:"./memberFileDelete",
					type:"GET",
					data:{
						id:id, 
						fname:fname
					},
					success:function(data) {
						if(data.trim()=1) {
							$(del).prev().remove();
							$(del).remove();
						}
					}
				})
			}
		})
	})
</script>
<style type="text/css">
#ex {
	display: none;
}

.x, .del {
	cursor: pointer;
}

.x:hover {
	color: red;
}
</style>
</head>
<body>
	<h1>Member View</h1>
	<form action="./memberUpdate" method="post" enctype="multipart/form-data">
		id<input type="text" name="id" value="${list.id}" readonly="readonly" class="id">
		pw<input type="text" name="pw" value="${list.pw}">
		name<input type="text" name="name" value="${list.name}">
		email<input type="text" name="email" value="${list.email}">
		phone<input type="text" name="phone" value="${list.phone}">
		age<input type="text" name="age" value="${list.age}">
		job<input type="text" name="job" value="${list.job}">
		<input type="text" name="oname" value="${list.oname}" readonly="readonly"><span class="del" title="${list.fname}" id="${list.id}">X</span>
		<input type="text" name="fname" value="${list.fname}" readonly="readonly">
		<img src="../resources/upload/${list.fname}">
		<button>Update</button>
	</form>
	<div id="ex">
		<input type="file" name="file"><span class="x">X</span>
	</div>
</body>
</html>