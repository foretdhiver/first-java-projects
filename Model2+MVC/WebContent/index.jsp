<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%  request.setCharacterEncoding("EUC-KR"); %>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Index 페이지입니다</title>
		<!-- 
		<script type="text/javascript"
			    src="../js/jquery-1.11.0.min.js">
		</script>
		 -->
		<script type="text/javascript"
				src="https://code.jquery.com/jquery-1.11.0.min.js">
		</script>
		<script type="text/javascript">
			$(document).ready(function(){
				alert('비회원 index 페이지입니다');
				$("#B").click(function(){
					alert("비회원은 게시판을 볼 수 없습니다.");
				});
				
				$("#L").click(function(){
					alert("로그인 함수");
					$("#ISUD_TYPE").attr("value", "L");
					$("#indexForm")
					//http://localhost:8088/kyj/MemberControllerServlet
					.attr("action", "/kyj/member/login.jsp")
					.submit();
				});
				
				$("#I").click(function(){
					alert("로그인 함수");
					$("#ISUD_TYPE").attr("value", "I");
					$("#indexForm")
					//http://localhost:8088/kyj/MemberControllerServlet
					.attr("action", "/kyj/member/insertKyjMember.jsp")
					.submit();
				});
			});
		</script>
	</head>
	<body>
		<form name="indexForm" 
		 	  id="indexForm" 
			  method="POST"
			  enctype="multipart/form-data">
		<table align="center">
			<tr>
				<td colspan="2" align="center">
					<h3>메인페이지입니다!!</h3>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="hidden" name ="ISUD_TYPE" id ="ISUD_TYPE">
					<input type="button" value="로그인" id="L">
					<input type="button" value="회원가입" id="I">
					<input type="button" value="게시판" id="B">
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>