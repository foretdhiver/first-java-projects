<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="bitcamp.java142.board.dao.KyjHelloBoardDAOImpl" %>
<%@ page import="bitcamp.java142.board.dao.KyjHelloBoardDAO" %> 
<%@ page import="bitcamp.java142.board.vo.KyjHelloBoardVO" %> 
<%@ page import="java.util.ArrayList" %>

<% request.setCharacterEncoding("EUC-KR"); %>

<% 

	String isudType = request.getParameter("ISUD_TYPE");
	String chkInKno	= request.getParameter("chkInKno"); 
	
	if(isudType!=null){
		System.out.println("isudType >>> : " + isudType);
		boolean bool = "U".equals(isudType.toUpperCase());
		
		if(bool){
			KyjHelloBoardDAO kdao = new KyjHelloBoardDAOImpl();
			System.out.println("kdao >>> : " + kdao);
			
			KyjHelloBoardVO kvo = null;
			kvo = new KyjHelloBoardVO();
			kvo.setKno(chkInKno);
			
			ArrayList<KyjHelloBoardVO> aList = kdao.selectKyjBoard(kvo);
			int nCnt = aList.size();	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>게시판 글 수정</title>
		<script type="text/javascript">
		
		function boardUpdateFunction(str){
			var strValue = str;
			alert(strValue);
			
			if('UOK' == strValue){
				// http://localhost:8088/babyWeb/board/kyjHelloBoardUpdateOK.jsp
				document.boardUpdate.action = "/babyWeb/board/kyjHelloBoardUpdateOK.jsp";
				alert('UOK >>> : ' + document.boardUpdate.action);
			}
			if('S' == strValue){
				document.boardUpdate.action = "/babyWeb/board/kyjHelloBoardSelectAll.jsp"
				alert('S >>> : ' + document.boardUpdate.action );
			}
			
			if('D' == strValue){
				document.boardUpdate.action = "/babyWeb/board/kyjHelloBoardDeleteOK.jsp"
				alert('D >>> : ' + document.boardUpdate.action );
			}
			
			document.boardUpdate.submit();
		}
		
		</script>
	</head>
	<body>
		<center>
		<form name="boardUpdate" method="POST">
			<table border="1" align="center">
<% 			
			if (1 == nCnt){
				for (int i=0; i<nCnt; i++){
					KyjHelloBoardVO _kvo = aList.get(i);
%>			
					<tr>
						<td colspan="2" align="center">게시판 글 수정하기</td>
					</tr>
					<tr>
						<td align="center">글번호</td>
						<td><input type="text" name="kno" id="kno" size="20" readonly value=<%= _kvo.getKno()%>></td>				
					</tr>
					<tr>
						<td align="center">제목</td>
						<td><input type="text" name="ksubject" id="ksubject" size="53" value=<%= _kvo.getKsubject() %>></td>
					</tr>
					<tr>
						<td align="center">이름</td>
						<td><input type="text" name="kname" id="kname" size="53" value=<%= _kvo.getKname() %>></td>
					</tr>
					<tr>
						<td align="center">입력일</td>
						<td><input type="text" name="kinsertdate" id="kinsertdate" size="20" disabled value=<%= _kvo.getKinsertdate() %>></td>
					</tr>				
					<tr>
						<td align="center">수정일</td>
						<td><input type="text" name="kupdatedate" id="kupdatedate" size="20" disabled value=<%= _kvo.getKupdatedate() %>></td>
					</tr>
					<tr>
						<td align="center">내용</td>
						<td><textarea name="kmemo" id="kmemo" cols="50" rows="10"><%= _kvo.getKmemo() %></textarea>
						</td>	
					</tr>
					<tr>
						<td align="center">비밀번호</td>
						<td>
							<input type="password" name="kpw" id="kpw" size="20" >
							<input type="button" id="kpwCheck" value="비밀번호확인">
						</td>
					</tr>
					<tr>
					<td colspan="2" align="center">
					<input type="hidden" name="ISUD_TYPE" value="UOK">
						<input type="button" value="글수정" onclick="boardUpdateFunction('UOK')">
						<input type="button" value="글삭제" onclick="boardUpdateFunction('D')">
						<input type="button" value="글목록" onclick="boardUpdateFunction('S')">
					</td>
				</tr>			
<% 				} // end of for문
%>		
			</table>
		</form>
<%
			}else{
%>
			<script>
				alert("수정 할 게시물의 체크박스 선택을 안했습니다.");
				location.href="/babyWeb/board/kyjHelloBoardSelectAll.jsp";
			</script>
<%
			} // end of else
		} // end of if (1 >= nCnt) 문
	}else{
%>
<%		
		
	} // end of else문
%>
		</center>
	</body>
</html>