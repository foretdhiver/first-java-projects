<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import="bitcamp.java142.ch5.kyjjf.dao.KyjMemberDAOImpl" %>
<%@ page import="bitcamp.java142.ch5.kyjjf.dao.KyjMemberDAO" %> 
<%@ page import="bitcamp.java142.ch5.kyjjf.vo.KyjMemberVO" %> 
<%@ page import="java.util.ArrayList" %>      
    <!-- EUC-KR : 2byte �����ϴ� ��� -->
    <!-- UTF-8 : 1byte �����ϴ� ��� -->
    
<% 
    request.setCharacterEncoding("EUC-KR");
%>

    <!-- JAVA �ڵ� ġ�� �� -->
<%
 	// request : ������ ������Ʈ
 	// getParameter() �Լ��� �ܺο��� jsp�� ��û�ϴ� �����͸� �޴� �Լ�
 	// �����ʹ� ���ڿ��� �޴´�.
 	String id = request.getParameter("id");
 	String pw = request.getParameter("pw");
 	System.out.println("id >>> : " + id);
	System.out.println("pw >>> : " + pw);	
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
			location.href="/babyWeb/index.html";
		</script>
<%
}else{ 
%>
    	<script>
			alert("����!");
			location.href="/babyWeb/html5.html";
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