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

Object objCalendarListOne = request.getAttribute("calendarListOne");
ArrayList<CalendarVO> calendarListOne = null;
calendarListOne = (ArrayList<CalendarVO>)objCalendarListOne;
CalendarVO clvo = null;
clvo = new CalendarVO();
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
				var originalStartDate = $("#calstarttime").val();
				var originalEndDate = $("#calendtime").val();
				var chageAllDay = originalStartDate;
				chageAllDay = moment(chageAllDay).format('YYYY/MM/DD');
				$("#allDay").change(function(){
			        if($("#allDay").is(":checked")){
			            $("#calstarttime").val(chageAllDay);
			            $("#calendtime").val(chageAllDay);
			            
			        }else{
			        	var reStartDay = originalStartDate;
			        	var reEndDay = originalEndDate;
			        	$("#calstarttime").val(reStartDay);
			        	$("#calendtime").val(reEndDay);
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
				
				$("#calendarUpdate").click(function(){
					alert("aa");
					if(confirm("일정을 수정하시겠습니까?")){
						$("#calendarPopupForm").attr("action", "/calendar/calendarUpdate.ge").submit();						
					}
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
		<form id="calendarPopupForm" name="calendarPopupForm">
		<br>
		<br>
		<br>
		<br>
			<table>
<%
if(calendarListOne.size() > 0){
	for(int i=0; i<calendarListOne.size(); i++){
		clvo = calendarListOne.get(i);
		System.out.println("[CalendarPopup.jsp] clvo time >>>" + clvo.getCalstarttime());
		
%>		
				<tr>
					<th class="thTitle" colspan="2">일정수정</th>
				</tr>
				<tr>
					<td class="tdTitle" height="30">일정 이름
					<input type="hidden" id="emno" name="emno" value="<%=clvo.getEmno() %>" />
					<input type="hidden" id="calno" name="calno" value="<%=clvo.getCalno() %>" />
					</td>
					<td class="tdContent"><input type="text" id="calsubject" name="calsubject" style="width:100%; border:none" value="<%=clvo.getCalsubject() %>"/>
					</td>
				</tr>
				<tr>
					<td class="tdTitle">일정 시작시간</td>
					<td>
					<input type="checkbox"	id="allDay" 		name="allDay" 		value="allDay" /> 하루종일 <br>
					<input type="text" 		id="calstarttime"	name="calstarttime" value="<%=clvo.getCalstarttime() %>" style="width:65%; border:none"><br>
					<input type="button"	id="startTime" 		name="startTime"	value="날짜" class="start-datepicker-btn button"/><br>
					</td>
				</tr>
				<tr>
					<td class="tdTitle">일정 끝나는시간</td>
					<td class="tdContent">
					<input type="text" id="calendtime" name="calendtime" placeholder="날짜를 선택해 주세요" style="width:65%; border:none" value="<%=clvo.getCalendtime() %>"><br>
					<input type="button" id="endTime" value="날짜" class="end-datepicker-btn button"/>
					</td>
				</tr>
				<tr>
					<td class="tdTitle">카테고리</td>
					<td class="tdContent">
						<select id="calcategory" name="calcategory">
<%
if(clvo.getJobcd().equals("01")){
	if(clvo.getCalcategory().equals(evo.getDeptcd())){
	%>		
								<option value=<%=evo.getDeptcd() %>><%=evo.getDeptname() %> 일정</option>
								<option value="11">개인 일정</option>
								<option value="22">회사전체 일정(개인등록)</option>
								<option value="00">공지해야할 회사일정</option>
		
	<%
	}
	if(clvo.getCalcategory().equals("11")){
	%>
								<option value="11">개인 일정</option>
								<option value=<%=evo.getDeptcd() %>><%=evo.getDeptname() %> 일정</option>
								<option value="22">회사전체 일정(개인등록)</option>
								<option value="00">공지해야할 회사일정</option>	
	<%	
	}
	if(clvo.getCalcategory().equals("22")){
	%>	
								<option value="22">회사전체 일정(개인등록)</option>
								<option value=<%=evo.getDeptcd() %>><%=evo.getDeptname() %> 일정</option>
								<option value="11">개인 일정</option>
								<option value="00">공지해야할 회사일정</option>
	<%	
	}
	if(clvo.getCalcategory().equals("00")){
	%>
								<option value="00">공지해야할 회사일정</option>
								<option value=<%=evo.getDeptcd() %>><%=evo.getDeptname() %> 일정</option>
								<option value="11">개인 일정</option>
								<option value="22">회사전체 일정(개인등록)</option>				
	<%	
	}//end of if
	
}else{

	if(clvo.getCalcategory().equals(evo.getDeptcd())){
	%>
								<option value=<%=evo.getDeptcd() %>><%=evo.getDeptname() %> 일정</option>
								<option value="11">개인 일정</option>
								<option value="22">회사전체 일정</option>	
	<%	
	}
	%>
	<%
	if(clvo.getCalcategory().equals("11")){
	%>
								<option value="11">개인 일정</option>
								<option value=<%=evo.getDeptcd() %>><%=evo.getDeptname() %> 일정</option>
								<option value="22">회사전체 일정</option>	
	<%	
	}
	%>
	<%
	if(clvo.getCalcategory().equals("22")){
	%>
								<option value="22">회사전체 일정</option>
								<option value=<%=evo.getDeptcd() %>><%=evo.getDeptname() %> 일정</option>
								<option value="11">개인 일정</option>	
	<%	
	}//end of if

}
%>														
						</select>
					</td>
				</tr>
				<tr>
					<td class="tdTitle">일정 내용</td>
					<td class="tdContent">
						<textarea id="calcontent" name="calcontent" rows="5" cols="20" style="width:100%; border:none" placeholder="일정 내용을 입력해주세요">
						<%=clvo.getCalcontent() %>
						</textarea>
					</td>
				</tr>	
<%		
	}//end of for
}//end of if
%>				
				<tr>
					<td colspan="2" class="tdTitle" align="center" height="35">
					<input type="button" class="button" id="calendarUpdate" name="calendarUpdate" value="일정수정"/>
					<input type="button" class="button" id="calendarDelete" name="calendarDelete" value="일정삭제"/>
					<input type="button" class="button" id="calendarReset" name="calendarReset" value="초기화"/>
					<input type="button" class="button" id="popupClose" name="popupClose" value="닫기"/>
					</td>
				</tr>	
			</table>
		</form>
	</body>
</html>