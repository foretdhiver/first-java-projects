<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.spring.ge.vo.EaVO" %>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>
<%@ page import="java.util.Iterator"%>
<%  request.setCharacterEncoding("EUC-KR"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="/css/docform.css" />
	<script type="text/javascript" src="/js/clock.js"></script>
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> 
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
	<script type="text/javascript">
	$(document).ready(function(){
		setInterval("dpTime()",0);
	
		$('#logout').click(function(){
			location.href='/ea/geLogOut.ge';
		});
		
		$('#tomain').click(function(){
			location.href='/ea/eamain.ge';
		});
		
		
	});
	
	function newEa(){
		// alert('newEa 함수');
	//	window.open("", "pop", "width=550, height=200");
		$("#ge_main").attr("action", '/ea/eadoccdform.ge');
		$("#ge_main").attr("enctype", "multipart/form-data");
		$("#ge_main").attr("method", "POST");
	//	$("#ge_main").attr("target", "pop");
		$("#ge_main").submit();
	}
	
	function eaDocLib(){
		// alert('eaDocLib 함수');
//		window.open("", "pop", "width=450, height=200");
		$("#ge_main").attr("action", '/ea/eadoclib.ge');
		$("#ge_main").attr("enctype", "multipart/form-data");
		$("#ge_main").attr("method", "POST");
//		$("#ge_main").attr("target", "pop");
		$("#ge_main").submit();
	}
	</script>
	</head>
	<body>
		<div id="ge_menudiv">
			<div class="topinfo">
				
			</div>
		</div>
		<div class="ge_maindiv">
			
			<center>로그아웃</center>
				
			<div class="bottom">
			<div class="bottominfo">
			GE Project ｜ 서울시 서초구 강남대로 459 (서초동, 백암빌딩)<br>
			TEL : 02-1234-5678 / FAX : 02-1234-5678<br>
			Copyright ⓒ GE Project. All Rights Reserved.
			</div>
			</div>
		</div>
	</body>
</html>