<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%  request.setCharacterEncoding("EUC-KR"); %>    
<%@ page import="kyj.member.common.FilePath" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kyj.board.vo.KyjHelloBoardVO" %>
<% request.setCharacterEncoding("EUC-KR"); %>
<%	
	Object obj = request.getAttribute("aList");
	ArrayList<KyjHelloBoardVO> aList = null;
	String filepath = "";
	
	if (obj !=null){
		aList = (ArrayList<KyjHelloBoardVO>)obj;
		filepath = FilePath.DOWNLOAD_FILEPATH;
		System.out.println("aList.size() >>> : " + aList.size());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!--	<meta name="viewport" content="width=device-width, initial-scale=1">
  		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>		-->
		<title>Board List</title>
	</head>
		<!-- 
		<script type="text/javascript"
			    src="../js/jquery-1.11.0.min.js">
		</script>
		 -->
		<script type="text/javascript"
				src="https://code.jquery.com/jquery-1.11.0.min.js">
		</script>
	<script type="text/javascript">
		alert("전체조회 페이지입니다");
		$(document).ready(function(){
			alert("스크립트 잘되나");
			
			$('#R').click(function() {
				alert("R function 진입");
				location.reload();
				});
			
			$("#I").click(function(){
	 			alert("I function 진입");
	 			$("#ISUD_TYPE").attr("value", "I");
	 			$("#boardForm").attr('action', '/kyj/board/boardInsert.jsp').submit();
	 		});
			$("#S").click(function(){
	 			alert("S function 진입");
	 			$("#ISUD_TYPE").attr("value", "S");
	 			$("#boardForm").attr('action', '/kyj/HelloBoardControllerServlet').submit();
	 		});
	 		
	 		$("#U").click(function(){
	 			alert("U function 진입");
	 			$("#ISUD_TYPE").attr("value", "U");
	 			$("#boardForm").attr('action', '/kyj/HelloBoardControllerServlet').submit();
	 		});
	 		
	 		$("#D").click(function(){
	 			alert("D function 진입");
	 			$("#ISUD_TYPE").attr("value", "D");
	 			$("#boardForm").attr('action', '/kyj/HelloBoardControllerServlet').submit();
	 		});
		});
	</script>
	
	<body>
		<center>
		<form name="boardForm" 
			  id="boardForm" 
			  method="POST"
			  enctype="multipart/form-data">
		<table border="1" align="center">
			<thead>
			<tr>
				<td colspan="10" align="center">
					<h2>게시판</h2>
				</td>
			</tr>
			<tr>
				<td colspan="9" align="center">
					<input type="hidden" name="ISUD_TYPE" id="ISUD_TYPE">
					<input type="button" value="새로고침" id="R" name="R">
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<input type="button" value="글쓰기" id="I" name="I">
					<input type="button" value="글조회" id="S" name="S">
					<input type="button" value="글수정" id="U" name="U">
	<!-- 			<input type="button" value="글삭제" id="D" name="D">   -->
				</td>
			</tr>
			<tr>
				<td></td>
				<td align="center">게시물번호</td>
				<td align="center">글제목</td>
				<td align="center">글쓴이</td>
			<!--<td align="center">비밀번호</td> -->
				<td align="center">내용</td>
				<td align="center">입력일</td>
				<td align="center">수정일</td>
			</tr>
			</thead>
			<% 
			for(int i=0;i<aList.size();i++)
			{ KyjHelloBoardVO kvo = (KyjHelloBoardVO)aList.get(i);
			%>

			<tbody>
			<tr>
				<td align="center">
				<input type="checkbox" name="chkInKno" 
						 id="chkInKno" value=<%= kvo.getKno()%>
						 onclick="checkOnly(this)">
				</td>
				<td align="center"><%= kvo.getKno() %></td>
				<td align="center"><%= kvo.getKsubject() %></td>
				<td align="center"><%= kvo.getKname() %></td>
			<!--<td align="center"><%= kvo.getKpw() %></td> -->
				<td align="center"><%= kvo.getKmemo() %></td>
				<td align="center"><%= kvo.getKinsertdate() %></td>
				<td align="center"><%= kvo.getKupdatedate() %></td>
			<!--<td align="center"><%= kvo.getKimage() %></td> -->
			</tr>
 				<%} 
			}%>
			<tr>
				
			</tr>	
			
			</tbody>
		</table>
		</form>
	</center>
	</body>
</html>