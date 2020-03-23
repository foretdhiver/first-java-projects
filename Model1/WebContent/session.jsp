<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	String mid = request.getParameter("mid");
	String mpw = request.getParameter("mpw");
	out.println("mid >>> : " + mid + "<br>");
	out.println("mpw >>> : " + mpw + "<br>");
	session.setAttribute("mid", mid);
	session.setMaxInactiveInterval(60*60*9);
	Object sessionValue = session.getAttribute("mid");
	out.println("sessionValue >>> : " + sessionValue);
	// JSESSIONID
	String jsessionid = session.getId();
	String jsessionid1 = session.getId();
	out.println("session >>> : " + session + "<br>");
	out.println("jsessionid >>> : " + jsessionid + "<br>");
	out.println("jsessionid1 >>> : " + jsessionid1 + "<br>");
%>
	<h3>
		getSession(), getSession(true)<br>
		HttpSession이 존재하면 현재 HttpSession을 반환하고<br>
		존재하지 않으면 새로이 세션을 생성한다.
	</h3>
	<hr>
<%	
	HttpSession hs = request.getSession();
	out.println("hs >>> : " + hs + "<br>");
	if (hs.isNew()){
		// 새로 생성
	}else{
		// 기존 세션 리턴
	}
	HttpSession hs1 = request.getSession(true);	
	out.println("hs1 >>> : " + hs1 + "<br>");
	// 세션 생성
	hs1.setAttribute("key", "value");
%>
	<h3>
		getSession(false)<br>
		HttpSession이 존재하면 현재 HttpSession을 반환하고<br>
		존재하지 않으면 새로이 세션을 생성하지 않고 그냥 null을 반환한다.
	</h3>
	<hr>
<%
	HttpSession hs2 = request.getSession(false);
	if(hs2 !=null){
		// 세션 삭제 
		hs2.invalidate();
	}else{
		// 세션 생성
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
 
	<form action="session.jsp" method="GET">
		ID : <input type="text" id="mid" name="mid"><br>
		PW : <input type="text" id="mpw" name="mpw"><br>
		<input type="submit" value="보내기">
		<input type="reset" value="다시">
	</form>
	
</body>
</html>