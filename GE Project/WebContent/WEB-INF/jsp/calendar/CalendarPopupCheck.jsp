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

if(calendarListOne.size() > 0){
	for(int i=0; i<calendarListOne.size(); i++){
		clvo = calendarListOne.get(i);
		System.out.println("[CalendarPopup.jsp] clvo time >>>" + clvo.getCalstarttime());	
		System.out.println("�ۼ��� ���  >>"  + clvo.getEmno());
		

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
				var emno1 = "<%=clvo.getEmno()%>"
				var emno2 = "<%=evo.getEmno()%>"
				
				if(emno1!=emno2){
					$("#modibutton").hide();
					$("#closebutton").show();
				}
				if(emno1==emno2){
					$("#modibutton").show();
					$("#closebutton").hide();
				}
				
				$("#calendarUpdate").click(function(){
					$("#calendarPopupForm").attr("action", "/calendar/calendarUpdateForm.ge").submit();
				});
				$("#calendarDelete").click(function(){
					$("#calendarPopupForm").attr("action", "/calendar/calendarDelete.ge").submit();
				});
				$("#popupClose").click(function(){
					window.close();
				});
				$("#popupClose1").click(function(){
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
			height:50px;
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
	     	width:100px;		
		}
		.tdContent{
			font-size: 15px;
			border-radius: 2px;
	     	color: #393939;		
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
			<table>
				<tr>
					<th class="thTitle" colspan="2">����Ȯ��</th>
				</tr>
				<tr>
					<td class="tdTitle" height="30">���� �̸�
					<input type="hidden" id="emno" name="emno" value="<%=clvo.getEmno() %>" />
					<input type="hidden" id="calno" name="calno" value="<%=clvo.getCalno() %>" />
					</td>
					<td class="tdContent"><%=clvo.getCalsubject() %></td>
				</tr>
				<tr>
					<td class="tdTitle">���� ���۽ð�</td>
					<td class="tdContent"><%=clvo.getCalstarttime() %></td>
				</tr>
				<tr>
					<td class="tdTitle">���� �����½ð�</td>
					<td class="tdContent"><%=clvo.getCalendtime() %></td>
				</tr>
				<tr>
					<td class="tdTitle" height="30">ī�װ�</td>
					<td class="tdContent">
<%
					if(clvo.getCalcategory().equals("00")){
%>
						�����ؾ��� ȸ������					
<%						
					}
					if(clvo.getCalcategory().equals(evo.getDeptcd())){
%>
						<%=evo.getDeptname() %> ����				
<%								
					}
					if(clvo.getCalcategory().equals("11")){
%>						
					���� ����
<%						
					}
					if(clvo.getCalcategory().equals("22")){
%>						
					ȸ����ü ����(���ε��)
<%								
					}
%>					
							
					</td>
				</tr>
				<tr>
					<td class="tdTitle">���� ����</td>
					<td class="tdContent"><%=clvo.getCalcontent() %></td>
				</tr>	
<%		
	}//end of for
}//end of if
%>				
				<tr>
					<td colspan="2" class="tdTitle" align="center" height="35">
					<div id="modibutton">
						<input type="button" class="button" id="calendarUpdate" name="calendarUpdate" value="��������"/>
						<input type="button" class="button" id="calendarDelete" name="calendarDelete" value="��������"/>
						<input type="button" class="button" id="popupClose" name="popupClose" value="�ݱ�"/>
					</div>
					<div id="closebutton">
						<input type="button" class="button" id="popupClose1" name="popupClose1" value="�ݱ�"/>
					</div>
					</td>
				</tr>	
			</table>	
		</form>
	</body>
</html>