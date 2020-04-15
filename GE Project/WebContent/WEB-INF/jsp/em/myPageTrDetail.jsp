<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmHrVO" %>    
<%@ page import="java.util.ArrayList" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<script type="text/javascript"
				src="https://code.jquery.com/jquery-1.11.0.min.js">
		</script>
		<script type="text/javascript">
		$(document).ready(function(){
		
		});
		</script>
		<title>Insert title here</title>
	</head>
	<body>
<%
		Object obj = request.getAttribute("myTrList");

		ArrayList<EmHrVO> myTrList = null;
		myTrList = (ArrayList<EmHrVO>)obj;
		
		System.out.println("(log) myTrList.size() >>> : " + myTrList.size());
		
		if(myTrList.size() > 0){
			for(int i=0; i<myTrList.size(); i++){
				EmHrVO ehvo = (EmHrVO)myTrList.get(i);
%>	
		<form name="detailForm"
			  id="detailForm"
			  method="get"
			  enctype="application/x-www-form-urlencoded">
			<table border="1" align="center">
				<tr>
					<td colspan="2" align="center">
						<center>교육사항 조회</center>
					</td>
				</tr>
				<tr>
					<td width="100" align="center">교육번호</td>
					<td width="300">
						<%=ehvo.getTrno() %>
					</td>
				</tr>
				<tr>
					<td align="center">사번</td>
					<td>
						<%=ehvo.getEmno() %>
					</td>
				</tr>
				<tr>
					<td align="center">이름</td>
					<td>
						<%=ehvo.getEmname() %>
					</td>
				</tr>
				<tr>
					<td align="center">부서</td>
					<td>
						<%=ehvo.getDeptname() %>
					</td>
				</tr>
				<tr>
					<td align="center">직위</td>
					<td>
						<%=ehvo.getJobname() %>
					</td>
				</tr>
				<tr>
					<td align="center">교육일시</td>
					<td>
						<%=ehvo.getTrfday() %>
						-
						<%=ehvo.getTreday() %>
					</td>
				</tr>
				<tr>
					<td align="center">교육명</td>
					<td>
						<%=ehvo.getTrname() %>
					</td>
				</tr>
				<tr>
					<td align="center">교육내용</td>
					<td>
						<textarea rows="8" cols="50" name="trcontent"><%=ehvo.getTrcontent() %></textarea>
					</td>
				</tr>
			</table>
		</form>	
<%
			}	// end of for
		}	// end of if
%>				
	</body>
</html>