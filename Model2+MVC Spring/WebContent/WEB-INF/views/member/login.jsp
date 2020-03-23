<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>login.jsp</title>
		<!-- <script type="text/javascript" src="../lib/jquery-1.11.0.min.js"></script> -->
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				alert('login.jsp 페이지 입니다.');
				$("#login").click(function(){
					var mode = "${mode}";
					var kid = $("#kid").val();
					var kpw = $("#kpw").val();
					
					if(!validateForm()) return;
					
					$("#loginForm").attr("action", "../member/logIn.kyj");
					$("#loginForm").attr("enctype", "application/x-www-form-urlencoded");
					$("#loginForm").attr("method", "POST");
					$("#loginForm").submit();
				});
				$("#join").click(function(){
					alert('insertPopup 함수 진입');
					$("#kid").val("");
					window.open("", "pop", "width=480, height=500");
					$("#loginForm").attr("action", "/member/selectMember.kyj");
					$("#loginForm").attr("enctype", "multipart/form-data");
					$("#loginForm").attr("method", "POST");
					$("#loginForm").attr("target", "pop");
					$("#loginForm").submit();
				});
				
				function validateForm(){
					if($("#kid").val().replace(/\s/g,"")==""){
						alert('아이디를 입력해주세요.');
						return false;
					}
					if($("#kpw").val().replace(/\s/g,"")==""){
						alert('비밀번호를 입력해주세요.');
						return false;
					}
					return true;
				}
			});
		</script>
	</head>
	<body>
		<h2 align="center">로그인</h2>
		<form name="loginForm" id="loginForm">
			<div align="center">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="kid" id="kid"/></td>
				</tr>
				<tr>
					<td>패스워드</td>
					<td><input type="password" name="kpw" id="kpw"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="button" id="login">로그인</button>
					 	<button type="button" id="join">회원가입</button>
					<c:if test="${mode=='fail'}">
						<div>
							아이디 또는 비밀번호가 일치하지 않습니다.
						</div>
					</c:if>
					</td>
				</tr>
			</table>
			</div>
		</form>
	</body>
</html>