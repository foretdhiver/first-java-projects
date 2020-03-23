<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="kyj.member.common.FilePath" %>
<%@ page import="kyj.member.dao.KyjMemberDAO" %>
<%@ page import="kyj.member.dao.KyjMemberDAOImpl" %>
<%@ page import="kyj.member.vo.KyjMemberVO" %>
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
//		String idCheck = request.getParameter("idCheck");
//		if("kIdCheck".equals(idCheck.toUpperCase())){
			String kid = request.getParameter("kid");
			System.out.println("kid >>> : " + kid );
			
			KyjMemberDAO kdao = new KyjMemberDAOImpl();
			KyjMemberVO kvo = null;
			kvo = new KyjMemberVO();
			kvo.setKid(kid);
			
			int nCnt = 0;
			boolean bFlag = false;
			
			nCnt = kdao.idValueCkeck(kvo);
			
			if(nCnt==0){
				System.out.println("(log) jqueryIdCheck. 아이디 생성 가능");
				bFlag = true;
				System.out.println("(log) jqueryIdCheck."+  bFlag);
			}else if (nCnt>0){
				System.out.println("(log) jqueryIdCheck.  아이디 생성 ㄴㄴ");
				bFlag = false;
				System.out.println("(log) jqueryIdCheck."+  bFlag);
			}
			
			request.setAttribute("bFlag", new Boolean(bFlag));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/member/idCheckXml.jsp");
			dispatcher.forward(request, response);
			
//		}
		%>

	</body>
</html>