<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>INDEX.jsp 페이지 입니다.</title>
		<!-- <script type="text/javascript" src="../lib/jquery-1.11.0.min.js"></script> -->
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			var mode = "${mode}";
			console.log(mode);
			alert('index.jsp 페이지 입니다.');
			$("#login").click(function(){
				location.href="member/logIn.kyj";
			});
			$("#memberlist").click(function(){
				location.href="member/listMember.kyj";
			});
		});
		</script>
		<style type="text/css">
			a{text-decoration:none;}
		</style>
	</head>
	<body>
		<h2 align="center">INDEX</h2>
		
		<div align="center">
		<c:if test="${mode!='logout'}">
		<button id="login" name="login">로그인</button>
		<button id="memberlist" name="memberlist">멤버리스트</button>
		</c:if>
		</div>
		<c:if test="${mode=='logout'}">
			<div align="center">
				로그아웃 성공<br>
			<button id="return" name="return" onclick="location.href='/index.jsp'">처음으로</button>
			</div>
		</c:if>

	<!-- 
		http://localhost:8088/WEB-INF/views/login/login.jsp
		<div><a href="board/listBoard.kyj">[게시판]</a></div>
	 -->
	 
	</body>
</html>