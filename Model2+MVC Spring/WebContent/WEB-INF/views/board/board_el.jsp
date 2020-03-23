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
			alert('board.jsp ������ �Դϴ�.');
			function insertBoard(){
				alert('insertBoard �Լ� ����');
			}
			
			function selectBoard(){
				alert('selectBoard �Լ� ����');
			}
			
			function listSearch(){
				alert('listSearch �Լ� ����');
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
		<h2>�Խ���</h2>
		</div>
		<table border="1" width="800" align="center">
			<thead>
			<tr>
				<td colspan="5" align="right">
					<input type="button" onclick="insertBoard();" value="�۾���" />
				</td>
			</tr>
			<c:if test="${empty boardList}">
					<tr>
						<td colspan="13" align="center">
							��ϵ� �Խù��� �������� �ʽ��ϴ�.
						</td>
					</tr>
			</c:if>
			<tr>
				<td></td>
				<td align="center">����</td>
			<!--<td align="center">��й�ȣ</td> -->
				<td align="center">�ۼ���</td>
				<td align="center">�ۼ���</td>
				<td align="center">������</td>
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
					<!-- �ؿ� input id�� name �˻���ų���� �ٲٱ� -->
					<td><input type="text" id="ksubject" name="ksubject"/>
					<input type="button" onclick="listSearch()" value="�˻�"/></td>
				</tr>
			</table>	
		</form>
		</div>
	</center>
	</body>
</html>