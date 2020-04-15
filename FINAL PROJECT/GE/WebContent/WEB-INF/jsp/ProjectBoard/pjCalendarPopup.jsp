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
			
			
			$("#calInsertBut").click(function(){
				$("#calPopupform").attr("action","/pjCalendar/calInsert.ge").submit();
			});//calInsertBut		
		});//ready end
		
		
		</script>
	</head>
	<body>
	<div id="calBut" align="right" style=" margin-right : 5px;">
			<input type="button" value="����" name="calInsertBut" id="calInsertBut">
			<input type="button" value="���" onclick="javascript:self.close();" >
			
		</div>
		<div>
			<form id="calPopupform" name="calPopupform" method="POST">
				<table border="1">
					<colgroup>
						<col width="20%" />
						<col width="25%" />
					</colgroup>
					<thead>
						<tr>
							<th>������</th>
							<td>&nbsp;<input type="text" name="pcsub" id="pcsub"></td>
						</tr>
						<tr>
							<th>�ۼ���</th>
							<td>
								&nbsp;�μ�
								<select name="deptname">
								<option value="������">������</option>
								<option value="����������">����������</option>
								<option value="������">������</option>
								<option value="���������">���������</option>
								</select>
								&nbsp;����
								<select name="jobname" >
								<option value="������">������</option>
								<option value="����">����</option>
								<option value="����">����</option>
								<option value="����">����</option>
								<option value="����">����</option>
								<option value="�븮">�븮</option>
								<option value="���">���</option>
								</select>
								&nbsp;�̸�
								<input type="text" name="emname" id="emname">
							</td>						
						</tr>
						<tr>
							<th>���� ��¥</th>
							<td>&nbsp;<input type="text" name="pcsdate" id="pcsdate" size="12" style="vertical-align: bottom;"/>
							<button type="button" name="button1" onclick="$('#pcsdate').datetimepicker('show');" style="vertical-align: bottom;">
						 	<img src="/../datetimepicker-master/images/calendar.gif"></button>
							</td>						
						</tr>
						<tr>
							<th>���� ��¥</th>
							<td>&nbsp;<input type="text" name="pcedate" id="pcedate" size="12" style="vertical-align: bottom;"/>
							<button type="button" name="button2" onclick="$('#pcedate').datetimepicker('show');" style="vertical-align: bottom;">
						 	<img src="/../datetimepicker-master/images/calendar.gif"></button>
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td height="100">&nbsp;<textarea name="pcmemo" id="pcmemo" rows="10" cols="70"></textarea></td>
						</tr>
					</thead>
				</table>
				<br>
			</form>
		</div>	
	</body>
</html>