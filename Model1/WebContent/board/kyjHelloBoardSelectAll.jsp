<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="bitcamp.java142.board.dao.KyjHelloBoardDAOImpl" %>
<%@ page import="bitcamp.java142.board.dao.KyjHelloBoardDAO" %> 
<%@ page import="bitcamp.java142.board.vo.KyjHelloBoardVO" %> 
<%@ page import="java.util.ArrayList" %>

<% request.setCharacterEncoding("EUC-KR"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>게시판 목록</title>
		<script type="text/javascript">
			
			function boardFunction(str){
				var strValue = str;
				alert(strValue);
			
				if('I' == strValue){
					document.boardForm.ISUD_TYPE.value="I";
					document.boardForm.action="/babyWeb/board/kyjHelloBoard.html";
					alert('I >>> : ' + document.boardForm.action);
				}
				
				if('S' == strValue){
					document.boardForm.ISUD_TYPE.value="S";
					document.boardForm.action="/babyWeb/board/kyjHelloBoardSelectAll.jsp";
					alert('S >>> : ' + document.boardForm.action);
				}
				
				if('U' == strValue){
					document.boardForm.ISUD_TYPE.value="U";
					document.boardForm.action="/babyWeb/board/kyjHelloBoardUpdate.jsp";
					alert('U >>> : ' + document.boardForm.action);
				}
				
				if('D' == strValue){
					document.boardForm.ISUD_TYPE.value="D";
					// http://localhost:8088/babyWeb/board/kyjHelloBoardDelete.jsp
					document.boardForm.action="/babyWeb/board/kyjHelloBoardDelete.jsp";
					alert('D >>> : ' + document.boardForm.action);
				}
				
				document.boardForm.submit();
			}
		</script>
	</head>
	<body>
<%
	KyjHelloBoardDAO kdao = new KyjHelloBoardDAOImpl();
	ArrayList aListAll = kdao.selectKyjHelloBoard();
	int nCnt = aListAll.size();
%>
	<center>
		<form name="boardForm" method="POST">
		<table border="1" align="center">
			<thead>
			<tr>
				<td colspan="10" align="center">
					<h2>게시판</h2>
				</td>
			</tr>
			<tr>
				<td colspan="10" align="right">
					<input type="button" value="글쓰기" onclick="boardFunction('I')">
					<input type="button" value="글목록" onclick="boardFunction('S')">
					<input type="button" value="글수정" onclick="boardFunction('U')">
					<input type="button" value="글삭제" onclick="boardFunction('D')">
				</td>
			</tr>
			</thead>
			
<%
	for(int i=0;i<nCnt;i++)
		{ KyjHelloBoardVO kvo = (KyjHelloBoardVO)aListAll.get(i);
%>
			<tbody>
			<tr>
				<td align="center">
						<input type="checkbox" name="chkInKno" 
								id="chkInKno"  value=<%= kvo.getKno()%>
						 	    onclick="checkOnly(this)">
				</td>
				<td align="center"><%= kvo.getKno() %></td>
				<td align="center"><%= kvo.getKsubject() %></td>
				<td align="center"><%= kvo.getKname() %></td>
				<td align="center"><%= kvo.getKpw() %></td>
				<td align="center"><%= kvo.getKmemo() %></td>
				<td align="center"><%= kvo.getKinsertdate() %></td>
				<td align="center"><%= kvo.getKupdatedate() %></td>
			</tr>
<% 	} // end of for문 %>
			<tr>
				<td colspan="10" align="right">
				<input type="hidden" name ="ISUD_TYPE">
					<input type="button" value="글쓰기" onclick="boardFunction('I')">
					<input type="button" value="글목록" onclick="boardFunction('S')">
					<input type="button" value="글수정" onclick="boardFunction('U')">
					<input type="button" value="글삭제" onclick="boardFunction('D')">
				</td>
			</tr>	
			</tbody>
		</table>
		</form>
	</center>
	</body>
</html>