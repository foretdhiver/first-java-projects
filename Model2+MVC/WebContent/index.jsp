<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%  request.setCharacterEncoding("EUC-KR"); %>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Index �������Դϴ�</title>
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
				alert('��ȸ�� index �������Դϴ�');
				$("#B").click(function(){
					alert("��ȸ���� �Խ����� �� �� �����ϴ�.");
				});
				
				$("#L").click(function(){
					alert("�α��� �Լ�");
					$("#ISUD_TYPE").attr("value", "L");
					$("#indexForm")
					//http://localhost:8088/kyj/MemberControllerServlet
					.attr("action", "/kyj/member/login.jsp")
					.submit();
				});
				
				$("#I").click(function(){
					alert("�α��� �Լ�");
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
					<h3>�����������Դϴ�!!</h3>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="hidden" name ="ISUD_TYPE" id ="ISUD_TYPE">
					<input type="button" value="�α���" id="L">
					<input type="button" value="ȸ������" id="I">
					<input type="button" value="�Խ���" id="B">
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>