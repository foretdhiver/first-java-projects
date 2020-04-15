<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List"%>
<%@ page import="com.spring.ge.vo.EaVO_" %>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>
<%  request.setCharacterEncoding("EUC-KR"); %>
<%
	Object object = session.getAttribute("eminfo");
	EmInfoVO emvo = null;
	emvo = new EmInfoVO();
	emvo = (EmInfoVO)object;
	
	Object obj1 = request.getAttribute("ea_lineno");
	Object obj2 = request.getAttribute("ea_stepno");
	Object obj3 = request.getAttribute("ea_empno");
	Object obj4 = request.getAttribute("ea_empno1");
	Object obj5 = request.getAttribute("ea_empno2");
	Object obj6 = request.getAttribute("send");
	
	System.out.println("라인 확인 : " + obj1);
	System.out.println("스탭 확인 : " + obj2);
	System.out.println("번호 확인 : " + obj3);
	System.out.println("번호1 확인 : " + obj4);
	System.out.println("번호2 확인 : " + obj5);
	System.out.println("샌드 확인 : " + obj6);
	
	String lineno = "";
	String stepno = "";
	String empno = "";
	String empno1 = "";
	String empno2 = "";
	String send = "";
	
	if(object != null){
		System.out.println("여기에 들어왔다는 확신이 필요해");
		lineno = String.valueOf(obj1);
		stepno = String.valueOf(obj2);
		empno = String.valueOf(obj3);
		empno1 = String.valueOf(obj4);
		empno2 = String.valueOf(obj5);
		send = String.valueOf(obj6);
	}
	System.out.println("ea_lineno : " + lineno);
	System.out.println("ea_stepno : " + stepno);
	System.out.println("empno : " + empno);
	System.out.println("empno1 : " + empno1);
	System.out.println("empno2 : " + empno2);
	System.out.println("send : " + send);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				resultF();
			});
			
			function resultF(){
				window.opener.name = "login";
				$("#mainSend").attr("action", "/ea_/gotoEaForm.ge");
				$("#mainSend").attr("target", "login");
				$("#mainSend").submit();
				window.close();
			}
		</script>
	</head>
	<body>
		<form id="mainSend" name="mainSend" method="post">
			<input type="hidden" id="ea_lineno" name="ea_lineno" value=<%=lineno%>>
			<input type="hidden" id="ea_stepno" name="ea_stepno" value=<%=stepno%>>
			<input type="hidden" id="empno" name="ea_empno" value=<%=empno%>>
			<input type="hidden" id="empno1" name="ea_empno1" value=<%=empno1%>>
			<input type="hidden" id="empno2" name="ea_empno2" value=<%=empno2%>>
			<input type="hidden" id="send" name="send" value=<%=send%>>
		</form>
	</body>
</html>