<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>

	</head>
	<body>
		<%
		Boolean bFlag = (Boolean)request.getAttribute("bFlag");
		System.out.println("bflag >>> : " + bFlag);
		boolean bool = false;
		if(bFlag.booleanValue()){
			bool=true;
		}else{
			bool=false;
		}
	    %>
		<?xml version='1.0' encoding="EUC-KR" ?>
			<login>
				<result><%=bool %></result>
			</login>
	</body>
</html>