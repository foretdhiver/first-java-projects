<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("EUC-KR"); %>
<%@ page import="kyj.member.dao.KyjMemberDAOImpl" %>
<%@ page import="kyj.member.dao.KyjMemberDAO" %>
<%@ page import="kyj.member.vo.KyjMemberVO" %>
<%@ page import="java.util.ArrayList" %>

<%
 	// request : ������ ������Ʈ
 	// getParameter() �Լ��� �ܺο��� jsp�� ��û�ϴ� �����͸� �޴� �Լ�
 	// �����ʹ� ���ڿ��� �޴´�.
 	
	
 	String id = request.getParameter("kloginid");
 	String pw = request.getParameter("kloginpw");
 	System.out.println("kIdVal >>> : " + id);
	System.out.println("kPwVal >>> : " + pw);	
	out.println(id);
	out.println(pw);
	
	// �鿣��biz�� �ִ� DAO�� ����Ʈ �����۾�
	// id pw �Լ��� �ȸ����� ���⼭ ����µ�..?
	// value ȣ���� �� ������ null üũ �� �ϱ�
	// http://localhost:8088/babyWeb/login.jsp
	// http://localhost:8088/babyWeb/login.jsp?id=aa&pw=bb <-���� ��Ʈ������ key&value ������ ������ �׽�Ʈ
	KyjMemberDAO kdao = new KyjMemberDAOImpl();
	KyjMemberVO kvo = null;
	kvo = new KyjMemberVO();
	kvo.setKid(id);
	kvo.setKpw(pw);
	
	ArrayList<KyjMemberVO> aList = kdao.loginKyjMember(kvo);
	System.out.println("" + aList.size());
	
	if(aList.size()==1){
%>
		<script>
			alert("�α��� ����!");
			location.href="/kyj/index.jsp";
		</script>
<%
}else{ 
%>
    	<script>
			alert("����!");
			location.href="/kyj/member/login.jsp";
		</script>
<% } %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>loginOK.jsp</title>
	</head>
	<body>
	
	</body>
</html>