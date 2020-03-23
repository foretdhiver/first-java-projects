<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="bitcamp.java142.board.dao.KyjHelloBoardDAOImpl" %>
<%@ page import="bitcamp.java142.board.dao.KyjHelloBoardDAO" %> 
<%@ page import="bitcamp.java142.board.vo.KyjHelloBoardVO" %> 
<%@ page import="java.util.ArrayList" %>

<% request.setCharacterEncoding("EUC-KR"); %>
<%
	String isudType = request.getParameter("ISUD_TYPE");
	String chkInKno = request.getParameter("chkInKno");
	String kno = request.getParameter("kno");
	String ksubject = request.getParameter("ksubject");
	String kname = request.getParameter("kname");
	String kpw = request.getParameter("kpw");
	String kmemo = request.getParameter("kmemo");
	
	if("UOK".equals(isudType.toUpperCase())){
		System.out.println(" ISUD_TYPE >>> : " + isudType);
		
		// update logic
		KyjHelloBoardDAO kdao = new KyjHelloBoardDAOImpl();
		
		KyjHelloBoardVO kvo = null;
		kvo = new KyjHelloBoardVO();
		kvo.setKno(kno);
		kvo.setKsubject(ksubject);
		kvo.setKmemo(kmemo);
		
		int nCntU = kdao.updateKyjHelloBoard(kvo);
		
		if (nCntU == 1){
			System.out.println(" 수정 성공 페이지로 보내기 ");
%>
			<script>
				alert("게시판 글 수정 성공 !!!");
				location.href="/babyWeb/board/kyjHelloBoardSelectAll.jsp";
			</script>
<%
		}else{
			out.println(" 에러 페이지로 보내기 ");
		} // end of if-else문
	} // end of ("UOK".equals(isudType.toUpperCase())) 문
	
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
	
	</body>
</html>