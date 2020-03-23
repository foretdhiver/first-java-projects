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
			alert("탈퇴 완료!");
			location.href="/babyWeb/member.html";
		</script>
		<% }else{ %>
		<script>
			alert("탈퇴에 실패했거나 존재하지 않는 회원입니다!");
			location.href="/babyWeb/member.html";
		</script>
		<% } %>
	</body>
</html>