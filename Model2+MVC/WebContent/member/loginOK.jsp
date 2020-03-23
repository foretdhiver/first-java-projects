<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("EUC-KR"); %>
<%@ page import="kyj.member.dao.KyjMemberDAOImpl" %>
<%@ page import="kyj.member.dao.KyjMemberDAO" %>
<%@ page import="kyj.member.vo.KyjMemberVO" %>
<%@ page import="java.util.ArrayList" %>

<%
 	// request : 내장형 오브젝트
 	// getParameter() 함수는 외부에서 jsp에 요청하는 데이터를 받는 함수
 	// 데이터는 문자열로 받는다.
 	
	
 	String id = request.getParameter("kloginid");
 	String pw = request.getParameter("kloginpw");
 	System.out.println("kIdVal >>> : " + id);
	System.out.println("kPwVal >>> : " + pw);	
	out.println(id);
	out.println(pw);
	
	// 백엔드biz에 있는 DAO와 프론트 연결작업
	// id pw 함수는 안만들었어서 여기서 만드는듯..?
	// value 호출할 때 데이터 null 체크 꼭 하기
	// http://localhost:8088/babyWeb/login.jsp
	// http://localhost:8088/babyWeb/login.jsp?id=aa&pw=bb <-쿼리 스트링으로 key&value 데이터 들어오나 테스트
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
			alert("로그인 성공!");
			location.href="/kyj/index.jsp";
		</script>
<%
}else{ 
%>
    	<script>
			alert("실패!");
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