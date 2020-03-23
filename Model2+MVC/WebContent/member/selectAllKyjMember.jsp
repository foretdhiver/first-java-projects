<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%  request.setCharacterEncoding("EUC-KR"); %>    
<%@ page import="kyj.member.common.FilePath" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kyj.member.vo.KyjMemberVO" %>
<% request.setCharacterEncoding("EUC-KR"); %>

<%	
	ArrayList<KyjMemberVO> aList = (ArrayList<KyjMemberVO>)request.getAttribute("aList");
	String filepath = FilePath.DOWNLOAD_FILEPATH; 

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Select All</title>
	</head>
	
	<body>
		<table border="1">
			<tr>
				<td colspan="12" align="center"><h3>ȸ������</h3></td>
			</tr>
			<tr>
				<td align="center">ȸ����ȣ</td>
				<td align="center">���̵�</td>
				<td align="center">��й�ȣ</td>
				<td align="center">�̸�</td>
				<td align="center">�������</td>
				<td align="center">�޴�����ȣ</td>
				<td align="center">�̸���</td>
				<td align="center">�����ȣ</td>
				<td align="center">���θ��ּ�</td>
				<td align="center">�̹���</td>
			</tr>
		<% 
		for(int i=0;i<aList.size();i++){
			KyjMemberVO kvo = aList.get(i);
		%>
			<tr>
				<td><%=kvo.getKnumm() %></td>
				<td><%=kvo.getKid() %></td>
				<td><%=kvo.getKpw() %></td>
				<td><%=kvo.getKname() %></td>
				<td><%=kvo.getKbirth() %></td>
				<td><%=kvo.getKhp() %></td>
				<td><%=kvo.getKemail() %></td>
				<td><%=kvo.getKpostno() %></td>
				<td><%=kvo.getKjuso() %></td>
				<td><img src="<%=kvo.getKimage() %>" border=0 width="100" height="100"></td>
			</tr>
		<%} %>
		</table>
	</body>
</html>