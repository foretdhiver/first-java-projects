<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List" %> 
<%@ page import="com.spring.ge.vo.EaVO" %>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>    
<%  request.setCharacterEncoding("EUC-KR"); %>    
<%
	Object obj = session.getAttribute("eminfo");
	EmInfoVO emvo = null;
	emvo = new EmInfoVO();
	emvo = (EmInfoVO)obj;
	
	Object obj0 = request.getAttribute("ea_lineno");
	Object obj1 = request.getAttribute("ea_stepno");
	Object obj2 = request.getAttribute("ea_empno");
	Object obj3 = request.getAttribute("ea_empno1");
	Object obj4 = request.getAttribute("ea_empno2");
	Object obj5 = request.getAttribute("eadoccd");
	
	List<EaVO> list = null;
	String ea_lineno = "";
	String ea_stepno = "";
	String ea_empno = "";
	String ea_empno1 = "";
	String ea_empno2 = "";
	String ea_doccd = "";
	
	if(obj!=null){
		ea_lineno = obj0.toString();
		ea_stepno = obj1.toString();
		ea_empno = obj2.toString();
		ea_empno1 = obj3.toString();
		ea_empno2 = obj4.toString();
		ea_doccd = obj5.toString();
	}
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>result.jsp</title>
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript">

		$(document).ready(function(){
			resultF();
		});
		
//		function resultF(){
//			$("#r_form").attr("action", "/ea/eawriteform.ge");
//			$("#r_form").attr("enctype", "multipart/form-data");
//			$("#r_form").attr("method", "GET");
//			$("#r_form").submit();
//		}
		function resultF(){
	//		window.opener.name = "eamain";
			$("#r_form").attr("action", "/ea/eawriteform.ge");
	//		$("#r_form").attr("target", "eamain");
			$("#r_form").submit();
	//		window.close();
		}
		
//		function resultF(){
//			window.opener.name = "parentPage";
//			document.popForm.target = "parentPage";
//			document.r_form.action="/ea/eawriteform.ge";
//			document.r_form.submit();
//			window.close();
//		}
		</script>
	</head>
	<body>
		<form name="r_form" id="r_form">
			<input type="hidden" name="ea_lineno" id="ea_lineno" value="<%=ea_lineno%>">
			<input type="hidden" name="ea_stepno" id="ea_stepno" value="<%=ea_stepno%>">
			<input type="hidden" name="ea_empno" id="ea_empno" value="<%=ea_empno%>">
			<input type="hidden" name="ea_empno1" id="ea_empno1" value="<%=ea_empno1%>">
			<input type="hidden" name="ea_empno2" id="ea_empno2" value="<%=ea_empno2%>">
			<input type="hidden" name="ea_doccd" id="ea_doccd" value="<%=ea_doccd%>">		
		</form>
	</body>
</html>