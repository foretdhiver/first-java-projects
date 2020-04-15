<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.common.EaFileReadUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>eadownload.jsp</title>
</head>
<body>
<%
	Object filePath = request.getAttribute("filePath");
	filePath = (String)filePath;
	
	Object ea_file = request.getAttribute("ea_file");
	ea_file = (String)ea_file;
	
	request.setAttribute("filePath", filePath);
	request.setAttribute("ea_file" ,ea_file);
	
	EaFileReadUtil fru = new EaFileReadUtil();
	fru.readFile(request, response);

%>


         
</body>
</html>