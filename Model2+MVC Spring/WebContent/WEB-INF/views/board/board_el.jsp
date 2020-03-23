<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			}
			
			function selectBoard(){
				alert('selectBoard 함수 진입');
			}
			
			function listSearch(){
				alert('listSearch 함수 진입');
				$("#searchForm").attr("action", "/board/listBoard.kyj");
				$("#boardForm").attr("enctype", "application/x-www-form-urlencoded");
				$("#boardForm").attr("method", "POST");
				$("#searchForm").submit();
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
					<input type="button" onclick="insertBoard();" value="글쓰기" />
				</td>
			</tr>
			<c:if test="${empty boardList}">
					<tr>
						<td colspan="13" align="center">
							등록된 게시물이 존재하지 않습니다.
						</td>
					</tr>
			</c:if>
			<tr>
				<td></td>
				<td align="center">제목</td>
			<!--<td align="center">비밀번호</td> -->
				<td align="center">작성자</td>
				<td align="center">작성일</td>
				<td align="center">수정일</td>
			</tr>
			</thead>
			<c:forEach items="${boardList}" var="row">
			<tbody>
			<tr>
				<td align="center">${row.kno}</td>
				<td align="center" width="40%"><a href="#" onclick="selectBoard()">${row.ksubject}</a></td>
				<td align="center">${row.kname}</td>
				<td align="center">${row.kinsertdate}</td>
				<td align="center">${row.kupdatedate}</td>
			</tr>
			</tbody>
			</c:forEach>
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