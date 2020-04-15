<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("EUC-KR"); %>
<%@ page import = "com.spring.ge.vo.PjCalendarVO" %> 
<%@ page import="java.util.ArrayList" %>   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>::: GE PROJECT :::</title>
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.js"></script>
		
		<!-- datetimepicker -->
		<link rel="stylesheet" href="/../datetimepicker-master/build/jquery.datetimepicker.min.css">
		<link rel="stylesheet" href="/../datetimepicker-master/jquery.datetimepicker.css">
		<script src="/../datetimepicker-master/jquery.datetimepicker.js"></script>
		
		<script src="/../datetimepicker-master/build/jquery.datetimepicker.full.js"></script>
		<script src="/../datetimepicker-master/build/jquery.datetimepicker.full.min.js"></script>
		<script src="/../datetimepicker-master/build/jquery.datetimepicker.min.js"></script>
		
		<style type="text/css">
		 
		 table {
	        width: 100%;
	        border-top: 1px solid #444444;
	        border-collapse: collapse;
	        margin-top : 10px;
	        margin-bottom : 0px;
	
	      }
	      th, td {
		        border-bottom: 1px solid #444444;
/* 		        padding:9px; */
		       
	      }
	      th {
		    background-color: #d9d9d9;
		  }
/* 		input[type=text]{ */
/* 			 border: hidden; */
/* 		} */
		
		</style>
		
		<script type="text/javascript">	
	
		$(document).ready(function(){
			
			$.datetimepicker.setLocale('en');
			
			$("#pcsdate").datetimepicker({
				onSelect:function(dateText, inst) {
					console.log(dateText);
					console.log(inst);
				}
			});
			
			$("#pcedate").datetimepicker({
				onSelect:function(dateText, inst) {
					console.log(dateText);
					console.log(inst);
				}
			});
			
			
			$("#calUpdateBut").click(function(){
				$("#calUpdateform").attr("action","/pjCalendar/calUpdate.ge").submit();
				alert("�����Ͽ����ϴ�.");
				window.close();	
			});//calUpdateBut
		
		});//ready end
		
		
		</script>
	</head>
	<body>
<%
	Object obj = request.getAttribute("calUpdateForm");
	PjCalendarVO cvo = (PjCalendarVO)obj;

%>	
	<div id="calBut" align="right" style=" margin-right : 5px;">
			<input type="button" value="����" name="calUpdateBut" id="calUpdateBut">
			<input type="button" value="���" onclick="javascript:self.close();" >
			
		</div>
		<div>
			<form id="calUpdateform" name="calUpdateform">
				<table border="1">
					<colgroup>
						<col width="10%" />
						<col width="25%" />
					</colgroup>
					<thead>
						<tr>
							<th>������ȣ</th>
							<td>&nbsp;<input type="text" id="pcno" name="pcno" value="<%=cvo.getPcno()%>" readonly></td>
						</tr>
						<tr>
							<th>������</th>
							<td>&nbsp;<input type="text" id="pcsub" name="pcsub" value="<%=cvo.getPcsub()%>"></td>
						</tr>
						<tr>
							<th>�ۼ���</th>
							<td>
								&nbsp;<select name="deptname" id="deptname">
								<option value="" selected>�μ�</option>
								<option value="������">������</option>
								<option value="����������">����������</option>
								<option value="������">������</option>
								<option value="���������">���������</option>
								</select> <br>
								&nbsp;<select name="jobname" id="jobname">
								<option value="" selected>����</option>
								<option value="������">������</option>
								<option value="����">����</option>
								<option value="����">����</option>
								<option value="����">����</option>
								<option value="����">����</option>
								<option value="�븮">�븮</option>
								<option value="���">���</option>
								</select><br>
								&nbsp;<input type="text" name="emname" id="emname" value="<%=cvo.getEmname()%>"/>
							</td>						
						</tr>
						<tr>
							<th>���� ��¥</th>
							<td>&nbsp;<input type="text" id="pcsdate" name="pcsdate" value="<%=cvo.getPcsdate()%>"></td>
						</tr>
						<tr>
							<th>���� ��¥</th>
							<td>&nbsp;<input type="text" id="pcedate" name="pcedate" value="<%=cvo.getPcedate()%>"></td>
						</tr>
						<tr>
							<th>����</th>
							<td height="50">&nbsp;<input type="text" id="pcmemo" name="pcmemo" value="<%=cvo.getPcmemo()%>"
								style="height:60px; width:200px; border: hidden;"></td>
						</tr>
					</thead>
				</table>
				<br>
			</form>
		</div>
	</body>
</html>