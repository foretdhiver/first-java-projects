<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  request.setCharacterEncoding("EUC-KR"); %>    
<%@ page import="com.kyjspring.board.common.Filepath" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.util.List" %>
<%@ page import="com.kyjspring.board.vo.BoardVO" %>
<% request.setCharacterEncoding("EUC-KR"); %>
<%
	Object obj = request.getAttribute("boardList");
	List<BoardVO> list = null;
	if(obj!=null){
		list = (List<BoardVO>)obj;	
		System.out.println("board.jsp.list.size : " + list.size());
		int listCnt = list.size();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>board.jsp</title>
		<!-- <script type="text/javascript" src="../lib/jquery-1.11.0.min.js"></script> -->
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript">	
		
		alert('board.jsp 페이지 입니다.');
			
			function insertBoard(){
				alert('insertBoard 함수 진입');
				window.open("", "pop", "width=850, height=650");
				$("#boardForm").attr("action", "/board/selectBoard.kyj");
				$("#boardForm").attr("enctype", "multipart/form-data");
				$("#boardForm").attr("method", "POST");
				$("#boardForm").attr("target", "pop");
				$("#boardForm").submit();
			}
			
			function selectBoard(kno){
				alert('selectBoard 함수 진입');
				$("#kno").val(kno);
				alert('kno :  ' + kno );
				window.open("", "pop", "width=850, height=980");
				$("#boardForm").attr("action", "/board/selectBoard.kyj");
				$("#boardForm").attr("enctype", "application/x-www-form-urlencoded");
				$("#boardForm").attr("target", "pop");
				$("#boardForm").attr("method", "POST");
				$("#boardForm").submit();
			}
			
			function listSearch(){
				alert('listSearch 함수 진입');
				$("#searchForm").attr("action", "/board/listBoard.kyj");
				$("#boardForm").attr("enctype", "application/x-www-form-urlencoded");
				$("#boardForm").attr("method", "POST");
				$("#searchForm").submit();
			}
			
			function refresh(){
				alert('refresh 함수 진입');
				
			}
		</script>
		<style type="text/css">
			a{color:black;}
		</style>
	</head>
	<body>
		<center>
		<form name="boardForm" id="boardForm" >
		<div align="center">
		<h2>게시판</h2>
		</div>
		<table border="1" width="800" align="center">
			<thead>
			<tr>
				<td colspan="5" align="right">
					<input type="button" onclick="refresh();" value="전체보기" />
					<input type="button" onclick="insertBoard();" value="글쓰기" />
				</td>
			</tr>
			<tr>
				<td><input type="hidden" id="kno" name="kno" /></td>
				<td align="center">제목</td>
			<!--<td align="center">비밀번호</td> -->
				<td align="center" width="100">작성자</td>
				<td align="center">작성일</td>
				<td align="center">수정일</td>
			</tr>
			</thead>
			<% 
			for(int i=0;i<listCnt;i++)
			{ BoardVO kvo = (BoardVO)list.get(i);
			%>
			
			<tbody>
			<tr>	
				
				<td align="center"><%= kvo.getKno() %></td>
				<td align="center" width="40%"><a href="#" onclick="selectBoard('<%= kvo.getKno() %>');"><%= kvo.getKsubject() %></a></td>
				<td align="center"><%= kvo.getKname() %></td>
				<td align="center"><%= kvo.getKinsertdate() %></td>
				<td align="center"><%= kvo.getKupdatedate() %></td>
			</tr>
			</tbody>
			<%} 
			}%>
		</table>
		<!-- searchForm -->
		</form>
		<div align="center">
		<form id="searchForm" name="searchForm">
			<table border="0" width="800" cellpadding="1" cellspacing="1">
				<tr align="right">
					<!-- 밑에 input id랑 name 검색시킬껄로 바꾸기 -->
					<td><input type="text" id="ksubject" name="ksubject"/>
					<input type="button" onclick="listSearch()" value="검색"/></td>
				</tr>
			</table>	
		</form>
		</div>
	</center>
	</body>
</html>