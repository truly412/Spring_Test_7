<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		var i = ${fn:length(view.files)};	//taglib fn 테그에
		$(".del").click(function() {
			var del = $(this);
			var fnum=$(this).attr("title");
			var fname=$(this).attr("id");
			var check=confirm("삭제시 복구가 불가능 합니다.");//불리언
			if(check) {
				$.ajax({
					url:"../file/fileDelete",
					type:"GET",
					data:{
						fnum:fnum,
						fname:fname
					},
					success:function(data){
						if(data.trim()==1) {
							$(del).prev().remove();
							$(del).remove();
							i--;
						}
					}
				})
			}
			
		})
		
		
		
		$("#btn").click(function() {
			if (i < 5) {
				var ex = $("#ex").html();
				$("#result").append(ex);
				i++;
			}else{
				alert("최대 5개까지다")
			}
		});

		$("#result").on("click", ".x", function() {
			$(this).prev().remove();
			$(this).remove();
			i--;
		});

		/* var i=0;
		$("#btn").click(function() {
			if(i<5){
				$("#result").append('<input type="file" name="file"><span class="x">X</span>');
				
			i++;
			}else {
				alert("5개만 가능");
			}
		}); */
	});
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
	<h1>${board}Update</h1>
	<form action="${board}Update" method="post" enctype="multipart/form-data">
		<input type="hidden" name="num" value="${view.num}">
		<p>Writer : <input type="text" value="${view.writer}" disabled="disabled">
		<p>Title : <input type="text" value="${view.title }" name="title"></p>
		<p>Contents : <textarea rows="" cols="" name="contents">${view.contents }</textarea>
		<p><input type="button" value="FileAdd" id="btn"></p>
		<div id="result">
		<c:forEach items="${view.files}" var="file">
			<p><input type="text" value="${file.oname}" readonly="readonly"><span class="del" title="${file.fnum}" id="${file.fname}">X</span></p>
		</c:forEach>
		</div>
		<input type="submit" value="Update">
	</form>
	<div id="ex">
		<input type="file" name="file"><span class="x">X</span>
	</div>

</body>
</html>