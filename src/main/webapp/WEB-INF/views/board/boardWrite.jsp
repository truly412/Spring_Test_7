<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		var i = 0;
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

.x {
	cursor: pointer;
}

.x:hover {
	color: red;
}
</style>
</head>
<body>
	<h1>${board} Write</h1>
	<form action="./${board}Write" method="post" enctype="multipart/form-data">
		<p>
			Writer : <input type="text" name="writer">
		</p>
		<p>
			Title : <input type="text" name="title">
		</p>
		<p>
			Contents :
			<textarea rows="" cols="" name="contents"></textarea>
		</p>
		<p>
			<input type="button" value="FileAdd" id="btn">
		</p>
		<div id="result"></div>
		<div id="ex">
			<input type="file" name="file"><span class="x">X</span>
		</div>

		<button>Write</button>
	</form>

</body>
</html>