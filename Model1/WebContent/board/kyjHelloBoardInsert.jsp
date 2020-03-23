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
	String kno = request.getParameter("kno");
	String ksubject = request.getParameter("ksubject");
	String kname = request.getParameter("kname");
	String kpw = request.getParameter("kpw");
	String kmemo = request.getParameter("kmemo");
	System.out.println(		isudType + " : "
						  + chkInKno + " : "
						  + kno + " : "
						  + ksubject + " : "
						  + kname + " : "
						  + kpw + " : "
						  + kmemo);
	if (isudType !=null){
		System.out.println("isudType >>> : " + isudType);
		boolean bool = "I".equals(isudType.toUpperCase());
		
		if(bool){
			KyjHelloBoardDAO kdao = new KyjHelloBoardDAOImpl();
			System.out.println("kdao >>> : " + kdao);
			
			KyjHelloBoardVO kvo = null;
			kvo = new KyjHelloBoardVO();
			
			kvo.setKsubject(ksubject);
			kvo.setKname(kname);
			kvo.setKpw(kpw);
			kvo.setKmemo(kmemo);
			kvo.setKdeleteyn("Y");
			
			int nCnt = kdao.insertKyjHelloBoard(kvo);
			
			if(nCnt>=1){
%>
			<script>
				alert("글쓰기 성공!");
				// http://localhost:8088/babyWeb/board/kyjHelloBoardSelectAll.jsp
				location.href="/babyWeb/board/kyjHelloBoardSelectAll.jsp"
			</script>
<% 			
			}else{
%>
			<script>
				alert("글쓰기 실패!");
				location.href="/babyWeb/board/kyjHelloBoard.html"
			</script>
<%
			}
		}else{
			System.out.println("ISUD_TYPE 잘 넘기세요");
		}
		
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>게시판 글쓰기</title>
	</head>
	<body>
	
	</body>
</html>