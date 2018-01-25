<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="../resources/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		//전역변수
	      var obj = [];
	      //스마트에디터 프레임생성
	      nhn.husky.EZCreator.createInIFrame({
	         oAppRef : obj,
	         elPlaceHolder : "contents",
	         sSkinURI : "../resources/SE2/SmartEditor2Skin.html",
	         htParams : {
	            // 툴바 사용 여부
	            bUseToolbar : true,
	            // 입력창 크기 조절바 사용 여부
	            bUseVerticalResizer : true,
	            // 모드 탭(Editor | HTML | TEXT) 사용 여부
	            bUseModeChanger : true,
	         }
	      });
	      //전송버튼
	      $("#save").click(function() {
	         //id가 smarteditor인 textarea에 에디터에서 대입
	         obj.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []);
	         //폼 submit
	         $("#frm").submit();
	      });
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
	<form id="frm" action="./${board}Write" method="post" enctype="multipart/form-data">
		<p>
			Writer : <input type="text" name="writer">
		</p>
		<p>
			Title : <input type="text" name="title">
		</p>
		<p>
			Contents :
			<textarea rows="" cols="" name="contents" id="contents"></textarea>
		</p>
		<p>
			<input type="button" value="FileAdd" id="btn">
		</p>
		<div id="result"></div>

		<input type="button" value="Write" id="save">
	</form>
		<div id="ex">
			<input type="file" name="file"><span class="x">X</span>
		</div>

</body>
</html>