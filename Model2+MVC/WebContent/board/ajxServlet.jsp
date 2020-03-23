<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="kyj.member.common.FilePath" %>
<%@ page import="kyj.board.dao.KyjHelloBoardDAO" %>
<%@ page import="kyj.board.dao.KyjHelloBoardDAOImpl" %>
<%@ page import="kyj.board.vo.KyjHelloBoardVO" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Enumeration" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<% 	
		String memberType = request.getParameter("memberType");
		System.out.println("memberType >>> : " + memberType);
		if(memberType.equals("kPwCheck")){
//		if("kIdCheck".equals(memberType.toUpperCase())	){
			String kno = request.getParameter("kno");
			String kpw = request.getParameter("kpw");
			System.out.println("kpw >>> : " + kpw );
			System.out.println("kno >>> : " + kno );
			
			KyjHelloBoardDAO kdao = new KyjHelloBoardDAOImpl();
			KyjHelloBoardVO kvo = null;
			kvo = new KyjHelloBoardVO();
			kvo.setKpw(kpw);
			kvo.setKno(kno);
			
			int nCnt = 0;
			boolean bFlag = false;
			
			nCnt = kdao.pwValCheck(kvo);
			
			if(nCnt==0){
				System.out.println("(log) jqueryIdCheck. 비번이안같아서 수정불가");
				bFlag = false;
				System.out.println("(log) jqueryIdCheck."+  bFlag);
			}else if (nCnt>0){
				System.out.println("(log) jqueryIdCheck.  비번이같다");
				bFlag = true;
				System.out.println("(log) jqueryIdCheck."+  bFlag);
			}
			
			request.setAttribute("bFlag", new Boolean(bFlag));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/board/ajaxXml.jsp");
			dispatcher.forward(request, response);
		}
		%>
		
	</body>
</html>