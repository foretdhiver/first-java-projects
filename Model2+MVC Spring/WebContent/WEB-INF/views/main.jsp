<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>main.jsp</title>
		<!-- <script type="text/javascript" src="../lib/jquery-1.11.0.min.js"></script> -->
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			alert('main.jsp 페이지 입니다.');
			
			$("#board").click(function(){
				location.href="../board/listBoard.kyj";
			});
			
			$("#logout").click(function(){
				$("#logOutForm").attr("action", "/member/logOut.kyj");
				$("#logOutForm").attr("enctype", "application/x-www-form-urlencoded");
				$("#logOutForm").attr("method", "POST");
				$("#logOutForm").submit();
			});
		});
		</script>
		<style type="text/css">
			a{text-decoration:none;}
		</style>	
	</head>
	<body>
		<form id="logOutForm" name="logOutForm">
			<input type="hidden" name="kid" id="kid" value="${memberVO.kid}" />
		</form>
		<h2 align="center">회원 페이지</h2>
		<c:if test="${mode=='fail'}">
			<div align="center">
				로그인 실패<br>
				<a href="../index.jsp">[메인으로]</a>
			</div>
		</c:if>
		<c:if test="${mode=='success'}">
			<div align="center">
				로그인 성공!
				${memberVO.kid}님 안녕하세요!<br>
				${sessionID}<br>
				${sessionVal}
			</div>
			<div align="center">
				<button id="board" name="board">게시판</button>
				<button id="logout" name="logout">로그아웃</button>
			</div>
			</c:if>
	</body>
</html>