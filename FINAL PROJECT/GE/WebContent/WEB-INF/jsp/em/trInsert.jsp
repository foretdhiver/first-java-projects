<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	request.setCharacterEncoding("EUC-KR");
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>�������׵��</title>
		<script type="text/javascript"
				src=https://code.jquery.com/jquery-1.11.0.min.js>;
		</script>
		<script type="text/javascript">
			$(document).ready(function(){
				alert("document.ready �Լ�");
				$("#emnoBt").click(function(){
					alert("��� ��ȸ ��ư Ŭ��");
					window.open("/em/trPopup.ge", "pop", "width=500, height=300");
				});
				$("#insert").click(function(){
					alert("inset �Լ�")
					$("#insertForm").attr("action", "/em/trInsert.ge");
					$("#insertForm").submit();
				});
			});
		</script>
	</head>
	<body>
		<form name="insertForm"
		      id="insertForm"
		      method="get"
		      enctype="application/x-www-form-urlencoded">
			<table border="1">
				<tr>
					<td colspan="2" align="center">
						<center>�������� ���</center>
					</td>
				</tr>
				<tr>
					<td width="100" align="center">���</td>
					<td width="300">
						<input type="text" name="emno" id="emno">
						<input type="button" value="�����ȸ" name="emnoBt" id="emnoBt">
					</td>
				</tr>
				<tr>
					<td align="center">�̸�</td>
					<td>
						<input type="text" name="emname" id="emname">
					</td>
				</tr>
				<tr>
					<td align="center">�μ�</td>
					<td>
						<input type="text" name="deptcd" id="deptcd">
					</td>
				</tr>
				<tr>
					<td align="center">����</td>
					<td>
						<input type="text" name="jobcd" id="jobcd">
					</td>
				</tr>
				<tr>
					<td align="center">�򰡿���</td>
					<td>
						<input type="text" name="evalyear" id="evalyear">
					</td>
				</tr>
				<tr>
					<td align="center">������</td>
					<td>
						<input type="text" name="trname" id="trname">
					</td>
				</tr>
				<tr>
					<td align="center">��������</td>
					<td>
						<textarea rows="8" cols="50" name="trcontent"></textarea>
					</td>
				</tr>
				<tr>
					<td align="center">���</td>
					<td>
						<textarea rows="8" cols="50" name="trnote"></textarea>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="���" id="insert" name="insert">
					</td>
				</tr>
			</table>      
		</form>
	</body>
</html>