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
			alert("ready");
			$("#update").click(function(){
				alert("update �Լ�");
				$("#detailForm").attr("action", "/em/evUpdate.ge");						
				$("#detailForm").submit();
			});
		});
		</script>
		<title>�� �� ��ȸ</title>
	</head>
	<body>
<%
		Object obj = request.getAttribute("myEvList");

		ArrayList<EmHrVO> myEvList = null;
		myEvList = (ArrayList<EmHrVO>)obj;
		
		System.out.println("(log) myEvList.size() >>> : " + myEvList.size());
		
		if(myEvList.size() > 0){
			for(int i=0; i<myEvList.size(); i++){
				EmHrVO ehvo = (EmHrVO)myEvList.get(i);
%>	
		<form name="detailForm"
			  id="detailForm"
			  method="get"
			  enctype="application/x-www-form-urlencoded">
			<table border="1">
				<tr>
					<td colspan="2" align="center">
						<center>�� ��ȸ</center>
					</td>
				</tr>
				<tr>
					<td width="100" align="center">�򰡹�ȣ</td>
					<td width="300">
						<%=ehvo.getEvno() %>
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
					<td align="center">�򰡿���</td>
					<td>
						<%=ehvo.getEvalyear() %>
					</td>
				</tr>
				<tr>
					<td align="center">�򰡵��</td>
					<td>
						<%=ehvo.getEvalgrade() %>
						<select name="evalgrade" id="evalgrade">
							<option value="<%=ehvo.evalgrade %>">�򰡵��</option>
							<option value="A">A</option>
							<option value="B">B</option>
							<option value="C">C</option>
							<option value="D">D</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">���</td>
					<td>
						<textarea rows="8" cols="50" name="evalnote"><%=ehvo.getEvalnote() %></textarea>
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