<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmInfoVO"%>
<%@ page import="com.spring.ge.vo.CalendarVO"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.spring.ge.common.ChangeName"%>
<%
	request.setCharacterEncoding("EUC-KR");
%>
<%
Object obj = session.getAttribute("eminfo");
EmInfoVO evo = null;
evo = new EmInfoVO();
evo = (EmInfoVO)obj;


Object objDateTest = request.getParameter("dateTest");
String dateTest = "";
dateTest = objDateTest.toString();
System.out.println("[CalendarPopup.jsp] dateTest >>> " + dateTest);

System.out.println("jobcd >>> " + evo.getJobcd());

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>일정등록</title>
		<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
  		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  		
  		<link rel="stylesheet" href="/material-datetime-picker-master/dist/material-datetime-picker.css">
		<script src="https://unpkg.com/babel-polyfill@6.2.0/dist/polyfill.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.17.1/moment.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/rome/2.1.22/rome.standalone.js"></script>
		<script src="/material-datetime-picker-master/dist/material-datetime-picker.js" charset="euc-kr"></script>
		
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script src="/include/js/chageDate.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				
				var adminCheck = "<%=evo.getJobcd()%>";
				
				$("#adminnotice").hide();
				
				if(adminCheck == 01){
					$("#adminnotice").show();
				}
				
				var noFormatDate = "<%=dateTest%>";
				console.log(noFormatDate);
				var date1 = $("#calstarttime").val();
				var date2 = $("#calendtime").val();

				
				$("#allDay").change(function(){
			        if($("#allDay").is(":checked")){
			            $("#calstarttime").val(noFormatDate);
			            $("#calendtime").val(noFormatDate);
			            
			        }else{
			        	$("#calstarttime").val("<%=dateTest %>");
			        	$("#calendtime").val("");
			        }
			    });

				var picker1 = new MaterialDatetimePicker({})
				.on('submit', function(a) {
					var formatDate = moment(a).format('YYYY/MM/DD HH:mm');
					var dateStart = moment(formatDate).format('YYYYMMDDHHmm');
					var dateEnd = moment(calendtime.value).format('YYYYMMDDHHmm');
					if(dateEnd<dateStart){
						alert("시작시간을 종료시간 보다 늦게 설정할 수 없습니다. \n시간을 다시 확인해주세요");
						return;
					}

					calstarttime.value = formatDate;
				});
				var picker2 = new MaterialDatetimePicker({})
				.on('submit', function(d) {
					var formatDate = moment(d).format('YYYY/MM/DD HH:mm');
					var dateStart = moment(calstarttime.value).format('YYYYMMDDHHmm');
					var dateEnd = moment(formatDate).format('YYYYMMDDHHmm');
					if(dateEnd<dateStart){
						alert("종료 시간을 시작시간 보다 일찍 설정할 수 없습니다. \n시간을 다시 확인해주세요");
						return;
					}
					calendtime.value = formatDate;		
				});
		
				var el1 = document.querySelector('.start-datepicker-btn');
				el1.addEventListener('click', function() {
					
					picker1.open();
				}, true);
				
				
				var el2 = document.querySelector('.end-datepicker-btn');
				el2.addEventListener('click', function() {
					picker2.open();
				}, true);
				
				$("#calendarInsert").click(function(){
					$("#calendarPopupForm").attr("action", "/calendar/calendarInsert.ge").submit();
				});
				$("#calendarDelete").click(function(){
					if(confirm("일정을 삭제하시겠습니까?")){
						$("#calendarPopupForm").attr("action", "/calendar/calendarDelete.ge").submit();						
					}					
				});
				$("#calendarReset").click(function(){
					$("#calsubject").val("");
					$("#calstarttime").val("");
					$("#calendtime").val("");
					$('#calcategory option:eq(0)').prop('selected', true);
					$("#calcontent").val("");
				});
				
				$("#popupClose").click(function(){
					window.close();
				});
				
			});
	</script>
	<style>
		table{
			margin:auto;
			margin-top:-98px;
			width: 360px;
			height:400px;
		}
		.thTitle{
			font-size: 30px;
			border-radius: 2px;
	      	background: #D8D8D8;
	     	color: #393939;		
		}
		.tdTitle{
			font-size: 12px;
			border-radius: 2px;
	      	background: #D8D8D8;
	     	color: #393939;		
		}
		.tdContent{
			font-size: 15px;
			border-radius: 2px;
	     	color: #393939;
	     	width:240px;		
		}

	    .button {
				background-color: white; /* Green */
				border: 1px solid #cccccc;
				color: #393939;
				padding: 5px 10px;
				text-align: center;
				text-decoration: none;
				display: inline-block;
				font-size: 12px;
				border-radius:1px;
		}
		.button:hover{
				background-color: #ededed;
				cursor:pointer;
		}
	    
  	</style>
  
	</head>
	<body>
		<form id="calendarPopupForm" name="calendarPopupForm" method="POST">
		<br>
		<br>
		<br>
		<br>
		<br>
			
			<table>
				<tr>
					<th class="thTitle" colspan="2">일정등록</th>
				</tr>
				<tr>
					<td class="tdTitle" height="30">일정 이름</td>
					<td class="tdContent"><input type="text" id="calsubject" name="calsubject" style="width:100%; border:none">
					</td>
				</tr>
				<tr>
					<td class="tdTitle">일정 시작시간</td>
					<td class="tdContent">
					<input type="checkbox"	id="allDay"			name="allDay"		value="allDay" /> 하루종일 <br>
					<input type="text"		id="calstarttime"	name="calstarttime" value="<%=dateTest %>" style="width:65%; border:none">
					<input type="button"	id="startTime"		name="startTime" 	value="날짜" class="start-datepicker-btn button"/><br>
					</td>
				</tr>
				<tr>
					<td class="tdTitle">일정 끝나는시간</td>
					<td class="tdContent">
					<input type="text"	id="calendtime"	name="calendtime"	placeholder="날짜를 선택해 주세요" style="width:65%; border:none">
					<input type="button"id="endTime"	name="endTime"		value="날짜" class="end-datepicker-btn button"/>
					</td>
				</tr>
				<tr>
					<td class="tdTitle" height="30">카테고리</td>
					<td class="tdContent">
						<select id="calcategory" name="calcategory">
<%
							if(evo.getJobcd().equals("01")){
%>
							<option value=<%=evo.getDeptcd() %>><%=evo.getDeptname() %> 일정</option>
							<option value="11">개인 일정</option>
							<option value="22">회사전체 일정(개인등록)</option>
							<option value="00">공지해야할 회사일정</option>	
<%								
							}else{
%>								
							<option value=<%=evo.getDeptcd() %>><%=evo.getDeptname() %> 일정</option>
							<option value="11">개인 일정</option>
							<option value="22">회사전체 일정(개인등록)</option>
<%								
							}

%>							
						</select>
					</td>
				</tr>
				<tr>
					<td class="tdTitle">일정 내용</td>
					<td class="tdContent"><textarea id="calcontent" name="calcontent" rows="5" cols="20" style="width:100%; border:none" placeholder="일정 내용을 입력해주세요"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="tdTitle" align="center" height="35">
					<input type="button" class="button" id="calendarInsert"name="calendarInsert"value="일정등록"/>
					<input type="button" class="button" id="calendarDelete"name="calendarDelete"value="일정삭제"/>
					<input type="button" class="button" id="calendarReset" name="calendarReset" value="초기화"/>
					<input type="button" class="button" id="popupClose"	name="popupClose" 	 value="닫기"/>
					</td>
				</tr>		
			</table>
		</form>
		
		<div id="adminnotice" style="color:red; font-size:6px;">
				※회사 전체 일정을 전 사원의 캘린더에 표기하기 위해서는<br>
				카테고리의 공지해야할 회사일정으로 선택 후 입력하면 됩니다.				
		</div>
	</body>
</html>