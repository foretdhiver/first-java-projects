<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%  request.setCharacterEncoding("EUC-KR"); %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>login.jsp</title>
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
		 		alert("login 페이지 입니다");
		 		$("#Iid").focus();

		 		$("#L").click(function(){
		 			alert("로그인 함수 진입");
		 			var membertype = $("#memberType").val();
		 			var kIdVal = $("#Iid").val();
		 			var kPwVal = $("#Ipw").val();
		 			alert(">>>>>>>>>>> : " + membertype);
		 		
		 			if(!kIdVal){
		 				alert("id를 입력하세요");
		 				$("#Iid").focus();
		 				return;
		 			}
		 			
		 			if(!kPwVal){
		 				alert("password를 입력하세요");
		 				$("#Ipw").focus();
		 				return;
		 			}
		 			
		 			alert("id, pw >>> : " + kIdVal + ", " + kPwVal);

		 			$("#memberType").attr("value", "L");
					$("#loginForm").attr("action", "/kyj/MemberControllerServlet").submit();
					
					//.attr('action', '/kyj/member/loginOK.jsp')
					
					
		 		});
		 		
		 		$("#J").click(function(){
		 			alert("J 함수 진입");
		 			$(location).attr('href', '/kyj/member/insertKyjMember.jsp')
		 			
		 		});
		 	});
		 </script>
	</head>
	<body>
		<div>
		<form name="loginForm"
			  id="loginForm"
			  method="POST"
			  enctype="application/x-www-form-urLencoded">
			
			<table border="1" align="center">
				<tr>
					<td>ID</td>
					<td><input type="text" name="Iid" id="Iid"></td>
				</tr>
				<tr>
					<td>PassWord</td>
					<td><input type="password" name="Ipw" id="Ipw"></td>
				</tr>
				<tr>
					<td align="center" colspan="2">
					<input type="hidden" id="memberType" name="memberType" value="memberType">
					<input type="button" id="L" name="L" value="login">
					<input type="button" id="J" name="J" value="Join">
					<input type="reset" value="Reset">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right"><a href="/kyj/index.jsp">메인으로</a></td>
				</tr>
			</table>
				
		</form>
		</div>
	</body>
</html>