<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="bitcamp.java142.ch5.kyjjf.dao.KyjMemberDAOImpl" %>
<%@ page import="bitcamp.java142.ch5.kyjjf.dao.KyjMemberDAO" %> 
<%@ page import="bitcamp.java142.ch5.kyjjf.vo.KyjMemberVO" %> 

<%@ page import="java.util.ArrayList" %>

<% request.setCharacterEncoding("EUC-KR"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<% 	
			String kname = request.getParameter("kname"); 
			KyjMemberDAO kdao = new KyjMemberDAOImpl();
			KyjMemberVO _kvo = null;
			_kvo = new KyjMemberVO();
			_kvo.setKname(kname);
		
			boolean bFlag = kdao.deleteKyjMember(_kvo);
			
			if(bFlag){
		%>
		<script>
			alert("Ż�� �Ϸ�!");
			location.href="/babyWeb/member.html";
		</script>
		<% }else{ %>
		<script>
			alert("Ż�� �����߰ų� �������� �ʴ� ȸ���Դϴ�!");
			location.href="/babyWeb/member.html";
		</script>
		<% } %>
	</body>
</html>