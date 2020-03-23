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
		
		alert('board.jsp ������ �Դϴ�.');
			
			function insertBoard(){
				alert('insertBoard �Լ� ����');
				window.open("", "pop", "width=850, height=650");
				$("#boardForm").attr("action", "/board/selectBoard.kyj");
				$("#boardForm").attr("enctype", "multipart/form-data");
				$("#boardForm").attr("method", "POST");
				$("#boardForm").attr("target", "pop");
				$("#boardForm").submit();
			}
			
			function selectBoard(kno){
				alert('selectBoard �Լ� ����');
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
				alert('listSearch �Լ� ����');
				$("#searchForm").attr("action", "/board/listBoard.kyj");
				$("#boardForm").attr("enctype", "application/x-www-form-urlencoded");
				$("#boardForm").attr("method", "POST");
				$("#searchForm").submit();
			}
			
			function refresh(){
				alert('refresh �Լ� ����');
				
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
					<input type="button" onclick="refresh();" value="��ü����" />
					<input type="button" onclick="insertBoard();" value="�۾���" />
				</td>
			</tr>
			<tr>
				<td><input type="hidden" id="kno" name="kno" /></td>
				<td align="center">����</td>
			<!--<td align="center">��й�ȣ</td> -->
				<td align="center" width="100">�ۼ���</td>
				<td align="center">�ۼ���</td>
				<td align="center">������</td>
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