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
		HttpSession�� �����ϸ� ���� HttpSession�� ��ȯ�ϰ�<br>
		�������� ������ ������ ������ �����Ѵ�.
	</h3>
	<hr>
<%	
	HttpSession hs = request.getSession();
	out.println("hs >>> : " + hs + "<br>");
	if (hs.isNew()){
		// ���� ����
	}else{
		// ���� ���� ����
	}
	HttpSession hs1 = request.getSession(true);	
	out.println("hs1 >>> : " + hs1 + "<br>");
	// ���� ����
	hs1.setAttribute("key", "value");
%>
	<h3>
		getSession(false)<br>
		HttpSession�� �����ϸ� ���� HttpSession�� ��ȯ�ϰ�<br>
		�������� ������ ������ ������ �������� �ʰ� �׳� null�� ��ȯ�Ѵ�.
	</h3>
	<hr>
<%
	HttpSession hs2 = request.getSession(false);
	if(hs2 !=null){
		// ���� ���� 
		hs2.invalidate();
	}else{
		// ���� ����
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
		<input type="submit" value="������">
		<input type="reset" value="�ٽ�">
	</form>
	
</body>
</html>