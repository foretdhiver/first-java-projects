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
		boolean bool = "D".equals(isudType.toUpperCase());
		
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
		<title>�Խ��� �� ����</title>
		<script type="text/javascript">
		
		function boardDeleteFunction(str){
			var strValue = str;
			alert(strValue);
			
			if('DOK' == strValue){
				// http://localhost:8088/babyWeb/board/kyjHelloBoardDeleteOK.jsp
				document.boardDelete.action = "/babyWeb/board/kyjHelloBoardDeleteOK.jsp";
				alert('DOK >>> : ' + document.boardDelete.action);
			}
			if('S' == strValue){
				document.boardDelete.action = "/babyWeb/board/kyjHelloBoardSelectAll.jsp"
				alert('S >>> : ' + document.boardDelete.action );
			}
			
			document.boardDelete.submit();
		} // end of function boardDeleteFunction(str)
		
		</script>
	</head>
	<body>
		<center>
		<form name="boardDelete" method="POST">
			<table border="1" align="center">
<% 			
			if (1 == nCnt){
			for (int i=0; i<nCnt; i++){
				KyjHelloBoardVO _kvo = aList.get(i);
%>			
				<tr>
					<td colspan="2" align="center">�Խ��� �� �����ϱ�</td>
				</tr>
				<tr>
					<td align="center">�۹�ȣ</td>
					<td><input type="text" name="kno" id="kno" size="20" readonly value=<%= _kvo.getKno()%>></td>				</tr>
				<tr>
					<td align="center">����</td>
					<td><input type="text" name="ksubject" id="ksubject" size="53" value=<%= _kvo.getKsubject() %>></td>
				</tr>
				<tr>
					<td align="center">�̸�</td>
					<td><input type="text" name="kname" id="kname" size="53" value=<%= _kvo.getKname() %>></td>
				</tr>
				<tr>
					<td align="center">�Է���</td>
					<td><input type="text" name="kinsertdate" id="kinsertdate" size="20" disabled value=<%= _kvo.getKinsertdate() %>></td>
				</tr>				
				<tr>
					<td align="center">������</td>
					<td><input type="text" name="kupdatedate" id="kupdatedate" size="20" disabled value=<%= _kvo.getKupdatedate() %>></td>
				</tr>
				<tr>
					<td align="center">����</td>
					<td><textarea name="kmemo" id="kmemo" cols="50" rows="10"><%= _kvo.getKmemo() %></textarea>
					</td>	
				</tr>
				<tr>
					<td align="center">��й�ȣ</td>
					<td>
						<input type="password" name="bpw" id="bpw" size="20" >
						<input type="button" id="kpwCheck" value="��й�ȣȮ��">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<input type="hidden" name="ISUD_TYPE" value="DOK">
						<input type="button" value="�ۻ���" onclick="boardDeleteFunction('DOK')">
						<input type="button" value="�۸��" onclick="boardDeleteFunction('S')">
					</td>
				</tr>
<% 			} // end of for
%>		
			</table>
		</form>
<%
			}else{
%>
				<script>
					alert("���� �� �Խù��� üũ�ڽ� ������ ���߽��ϴ�.");
					location.href="/babyWeb/board/kyjHelloBoardSelectAll.jsp";
				</script>
<%
			} // end of if-else
		} // end of if(bool)
	}else{// end of if(isudType!=null)

%>
<%  } %>
		</center>
	</body>
</html>