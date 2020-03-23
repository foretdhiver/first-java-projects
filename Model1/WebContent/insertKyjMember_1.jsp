<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="bitcamp.java142.ch5.kyjjf.dao.KyjMemberDAO" %>
<%@ page import="bitcamp.java142.ch5.kyjjf.dao.KyjMemberDAOImpl" %>
<%@ page import="bitcamp.java142.ch5.kyjjf.vo.KyjMemberVO" %>

<%@ page import="java.util.ArrayList" %>

<% request.setCharacterEncoding("EUC-KR"); %>

<% 
	// String knumm;
	String kid = request.getParameter("kid");
	String kpw = request.getParameter("kpw");
	String kname = request.getParameter("kname");
	String khp = request.getParameter("khp");
	String kbirth = request.getParameter("kbirth");
	String kmail = request.getParameter("kmail");
	String kpostno = request.getParameter("kpostno");
	String kjuso = request.getParameter("kjuso");
	// String kdeleteyn;
	// String kinsertdate;
	// String kupdatedate;
	
	KyjMemberDAO kdao = new KyjMemberDAOImpl();
	KyjMemberVO kvo = null;
	kvo = new KyjMemberVO();
	kvo.setKid(kid);
	kvo.setKpw(kpw);
	kvo.setKname(kname);
	kvo.setKbirth(kbirth);
	kvo.setKhp(khp);
	kvo.setKemail(kmail);
	kvo.setKpostno(kpostno);
	kvo.setKjuso(kjuso);
	
	boolean bFlag = kdao.insertKyjMember(kvo);
	
	if(bFlag){

%>	
	<script>
		alert("업데이트 성공!");
		location.href="/babyWeb/selectKyjMember.jsp?kname=" + "<%= kname%>";
	</script>
	 
	<%}else{%>
		<script>
		alert("입력 실패! 아이디, 패스워드, 이름은 필수입력 대상입니다.");
		location.href="/babyWeb/member.html";
	</script>
	<% } %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

</body>
</html>