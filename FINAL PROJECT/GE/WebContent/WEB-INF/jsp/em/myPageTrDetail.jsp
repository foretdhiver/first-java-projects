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
						<center>�������� ��ȸ</center>
					</td>
				</tr>
				<tr>
					<td width="100" align="center">������ȣ</td>
					<td width="300">
						<%=ehvo.getTrno() %>
					</td>
				</tr>
				<tr>
					<td align="center">���</td>
					<td>
						<%=ehvo.getEmno() %>
					</td>
				</tr>
				<tr>
					<td align="center">�̸�</td>
					<td>
						<%=ehvo.getEmname() %>
					</td>
				</tr>
				<tr>
					<td align="center">�μ�</td>
					<td>
						<%=ehvo.getDeptname() %>
					</td>
				</tr>
				<tr>
					<td align="center">����</td>
					<td>
						<%=ehvo.getJobname() %>
					</td>
				</tr>
				<tr>
					<td align="center">�����Ͻ�</td>
					<td>
						<%=ehvo.getTrfday() %>
						-
						<%=ehvo.getTreday() %>
					</td>
				</tr>
				<tr>
					<td align="center">������</td>
					<td>
						<%=ehvo.getTrname() %>
					</td>
				</tr>
				<tr>
					<td align="center">��������</td>
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