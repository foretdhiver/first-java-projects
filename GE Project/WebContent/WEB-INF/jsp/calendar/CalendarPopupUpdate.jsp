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
		<title>�������</title>
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
						alert("���۽ð��� ����ð� ���� �ʰ� ������ �� �����ϴ�. \n�ð��� �ٽ� Ȯ�����ּ���");
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
						alert("���� �ð��� ���۽ð� ���� ���� ������ �� �����ϴ�. \n�ð��� �ٽ� Ȯ�����ּ���");
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
					if(confirm("������ �����Ͻðڽ��ϱ�?")){
						$("#calendarPopupForm").attr("action", "/calendar/calendarUpdate.ge").submit();						
					}
				});
				$("#calendarDelete").click(function(){
					if(confirm("������ �����Ͻðڽ��ϱ�?")){
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
					<th class="thTitle" colspan="2">��������</th>
				</tr>
				<tr>
					<td class="tdTitle" height="30">���� �̸�
					<input type="hidden" id="emno" name="emno" value="<%=clvo.getEmno() %>" />
					<input type="hidden" id="calno" name="calno" value="<%=clvo.getCalno() %>" />
					</td>
					<td class="tdContent"><input type="text" id="calsubject" name="calsubject" style="width:100%; border:none" value="<%=clvo.getCalsubject() %>"/>
					</td>
				</tr>
				<tr>
					<td class="tdTitle">���� ���۽ð�</td>
					<td>
					<input type="checkbox"	id="allDay" 		name="allDay" 		value="allDay" /> �Ϸ����� <br>
					<input type="text" 		id="calstarttime"	name="calstarttime" value="<%=clvo.getCalstarttime() %>" style="width:65%; border:none"><br>
					<input type="button"	id="startTime" 		name="startTime"	value="��¥" class="start-datepicker-btn button"/><br>
					</td>
				</tr>
				<tr>
					<td class="tdTitle">���� �����½ð�</td>
					<td class="tdContent">
					<input type="text" id="calendtime" name="calendtime" placeholder="��¥�� ������ �ּ���" style="width:65%; border:none" value="<%=clvo.getCalendtime() %>"><br>
					<input type="button" id="endTime" value="��¥" class="end-datepicker-btn button"/>
					</td>
				</tr>
				<tr>
					<td class="tdTitle">ī�װ�</td>
					<td class="tdContent">
						<select id="calcategory" name="calcategory">
<%
if(clvo.getJobcd().equals("01")){
	if(clvo.getCalcategory().equals(evo.getDeptcd())){
	%>		
								<option value=<%=evo.getDeptcd() %>><%=evo.getDeptname() %> ����</option>
								<option value="11">���� ����</option>
								<option value="22">ȸ����ü ����(���ε��)</option>
								<option value="00">�����ؾ��� ȸ������</option>
		
	<%
	}
	if(clvo.getCalcategory().equals("11")){
	%>
								<option value="11">���� ����</option>
								<option value=<%=evo.getDeptcd() %>><%=evo.getDeptname() %> ����</option>
								<option value="22">ȸ����ü ����(���ε��)</option>
								<option value="00">�����ؾ��� ȸ������</option>	
	<%	
	}
	if(clvo.getCalcategory().equals("22")){
	%>	
								<option value="22">ȸ����ü ����(���ε��)</option>
								<option value=<%=evo.getDeptcd() %>><%=evo.getDeptname() %> ����</option>
								<option value="11">���� ����</option>
								<option value="00">�����ؾ��� ȸ������</option>
	<%	
	}
	if(clvo.getCalcategory().equals("00")){
	%>
								<option value="00">�����ؾ��� ȸ������</option>
								<option value=<%=evo.getDeptcd() %>><%=evo.getDeptname() %> ����</option>
								<option value="11">���� ����</option>
								<option value="22">ȸ����ü ����(���ε��)</option>				
	<%	
	}//end of if
	
}else{

	if(clvo.getCalcategory().equals(evo.getDeptcd())){
	%>
								<option value=<%=evo.getDeptcd() %>><%=evo.getDeptname() %> ����</option>
								<option value="11">���� ����</option>
								<option value="22">ȸ����ü ����</option>	
	<%	
	}
	%>
	<%
	if(clvo.getCalcategory().equals("11")){
	%>
								<option value="11">���� ����</option>
								<option value=<%=evo.getDeptcd() %>><%=evo.getDeptname() %> ����</option>
								<option value="22">ȸ����ü ����</option>	
	<%	
	}
	%>
	<%
	if(clvo.getCalcategory().equals("22")){
	%>
								<option value="22">ȸ����ü ����</option>
								<option value=<%=evo.getDeptcd() %>><%=evo.getDeptname() %> ����</option>
								<option value="11">���� ����</option>	
	<%	
	}//end of if

}
%>														
						</select>
					</td>
				</tr>
				<tr>
					<td class="tdTitle">���� ����</td>
					<td class="tdContent">
						<textarea id="calcontent" name="calcontent" rows="5" cols="20" style="width:100%; border:none" placeholder="���� ������ �Է����ּ���">
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
					<input type="button" class="button" id="calendarUpdate" name="calendarUpdate" value="��������"/>
					<input type="button" class="button" id="calendarDelete" name="calendarDelete" value="��������"/>
					<input type="button" class="button" id="calendarReset" name="calendarReset" value="�ʱ�ȭ"/>
					<input type="button" class="button" id="popupClose" name="popupClose" value="�ݱ�"/>
					</td>
				</tr>	
			</table>
		</form>
	</body>
</html>