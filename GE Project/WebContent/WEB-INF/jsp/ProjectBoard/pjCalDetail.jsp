<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("EUC-KR"); %>
<%@ page import = "com.spring.ge.vo.PjCalendarVO" %> 

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
	      }
	      th {
		    background-color: #d9d9d9;
		  }
		
		
		</style>
		
		<script type="text/javascript">	
		
		$(document).ready(function(){
			
			$("#calUpdateBut").click(function(){
				window.open("","pjCalendarPopup","width=400, height=300, left=100, top=50"); 
				$("#calDetailform").attr("target","pjCalendarPopup");
				$("#calDetailform").attr("action","/pjCalendar/calUpdateForm.ge").submit();
				
			});//calInsertBut
			
			$("#calDeleteBut").click(function(){
				alert("삭제하시겠습니까? ");
				$("#calDetailform").attr("action","/pjCalendar/calDelete.ge").submit();

			});//calDeleteBut
		
		});//ready end
		
		
		</script>
	</head>
	<body>
<%
	Object obj = request.getAttribute("calDetail");
	PjCalendarVO cvo = (PjCalendarVO)obj;
	if(obj != null){

%>	
		<div id="calBut" align="right" style=" margin-right : 5px;">
			<input type="button" value="수정" name="calUpdateBut" id="calUpdateBut">
			<input type="button" value="삭제" name="calDeleteBut" id="calDeleteBut">
			<input type="button" value="취소" onclick="javascript:self.close();" >
		</div>
		<div>
			<form id="calDetailform" name="calDetailform">
			<input type="hidden" id="pcno" name="pcno" value="<%=cvo.getPcno()%>" />
			<input type="hidden" id="emname" name="emname" value="<%=cvo.getEmname()%>" />
			<input type="hidden" id="jobcd" name="jobcd" value="<%=cvo.getJobname()%>" />
			<input type="hidden" id="deptname" name="deptname" value="<%=cvo.getDeptname()%>" />
				<table border="1">
					<colgroup>
						<col width="10%" />
						<col width="25%" />
					</colgroup>
					<thead>
						<tr>
							<th>일정번호</th>
							<td>&nbsp;<%=cvo.getPcno()%></td>
						</tr>
						<tr>
							<th>일정명</th>
							<td>&nbsp;<%=cvo.getPcsub()%></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td>&nbsp;<%=cvo.getDeptname()%>
							<%=cvo.getJobname()%> 
							<%=cvo.getEmname()%></td>
						</tr>
						<tr>
							<th>시작 날짜</th>
							<td>&nbsp;<%=cvo.getPcsdate()%></td>
						</tr>
						<tr>
							<th>종료 날짜</th>
							<td>&nbsp;<%=cvo.getPcedate()%></td>
						</tr>
						<tr>
							<th>내용</th>
							<td height="100">&nbsp;<%=cvo.getPcmemo()%></td>
						</tr>
					</thead>
				</table>
				<br>
			</form>
		</div>
<%
	}else{
%>

<%		
	}//else
%>			
	</body>
</html>