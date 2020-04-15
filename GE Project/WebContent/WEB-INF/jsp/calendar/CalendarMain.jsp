<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmInfoVO"%>

<%
	request.setCharacterEncoding("EUC-KR");
%>
<%
Object obj = session.getAttribute("eminfo");
EmInfoVO evo = null;
evo = new EmInfoVO();
evo = (EmInfoVO)obj;
System.out.println("jsp id ?? "  + session.getId());

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	   	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	    <title>�������� </title>
		<link href="/fullcalendar-2.9.1/fullcalendar.css" rel="stylesheet"/>
		<link href="/fullcalendar-2.9.1/fullcalendar.print.css" rel="stylesheet" media="print"/>
		<script type="text/javascript" src="/fullcalendar-2.9.1/lib/moment.min.js" ></script>
		<script type="text/javascript" src="/fullcalendar-2.9.1/lib/jquery.min.js"></script>
		<script type="text/javascript" src="/fullcalendar-2.9.1/fullcalendar.js"></script>
		<script type="text/javascript" src="/fullcalendar-2.9.1/lang/ko.js" charset="euc-kr"></script>
		<link rel="stylesheet" type="text/css" href="/css/calcommon.css" />
		<script type="text/javascript" src="/js/clock.js"></script>		    
		<script type="text/javascript">
		    $(document).ready(function() {
		    	$('.mainframe .loader').hide();
				setInterval("dpTime()",0);
				
		    	var deptcd = "<%=evo.getDeptcd()%>";
		    	var emno = "<%=evo.getEmno()%>";
		    	console.log("emno >>> " + emno);		    	
		    	var url = "/calendar/all/"+emno+".ge";
		    	console.log("url >>> " + url);	
		    	
		    	var events = [];
	
	        	$.ajax({
					url:url,							
					headers:{
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override":"post"
					},
					dataType:'json',							
					success:function(data){				
						$.each(data, function (i, list) {
							console.log(list.calsubject);
							console.log(list.calstarttime);
							if(list.calcategory == "<%=evo.getDeptcd() %>"){
								list.calcategory = "#ed8069";
							}
							if(list.calcategory == "11"){
								list.calcategory = "#a4a555";
							}
							if(list.calcategory == "22"){
								list.calcategory = "#b1deb5";
							}
							if(list.calcategory == "00"){
								list.calcategory = "#fcb24e";
							}

		                    events.push({
		                        title    : list.calsubject,
		                        start    : list.calstarttime,
		                        end	     : list.calendtime,
		                        no	     : list.calno,
		                        backgroundColor  : list.calcategory,
		                        emno	 : list.emno,
		                        pw	 	 : list.calpw,
		                        id       : list.calid
		                    });
		                });
						
						$("#calendar").fullCalendar({
							contentHeight: 500,
							fixedWeekCount:false,
				        	header: {
				        		left	:	"prev,next today",
				                center	:	"title",
				                right	: 	"month,agendaWeek,agendaDay,listMonth"
				                	},
				            defaultDate	:	"2020-03-11",
				            locale		:	"ko",
				            editable	:	false,
				            eventLimit	:	true,
				            dayClick	:	function(date) {
				            	loading();
				        		window.open("", "calendarPopup", "width=400, height=700, left=100, top=50");
				        		$("#dateTest").attr("value", date.format());
				        		var aa = $("#dateTest").val();
				        		$("#ge_main").attr("target","calendarPopup");
								$("#ge_main").attr("action","/calendar/calendarPopup.ge").submit();	
				        			},
		        			eventClick : function(info){
		        				loading();
		        				window.open("", "calendarPopup", "width=400, height=700, left=100, top=50");
		        				$("#calno").val(info.no);
		        				var bb = $("#calno").val();
		        				$("#ge_main").attr("target","calendarPopup");
		        				$("#ge_main").attr("action","/calendar/calendarPopupCheck.ge").submit();	
		        			        }, 	
		   			        events		:	events

				        });//end of fullCalendar
	
					}//end of success
				}); //end of ajax 
				
				$("#board").click(function(){
					loading();
					location.href="/board/boardMain.ge";		
				});
				$("#calendarMain").click(function(){
					loading();
					location.href="/board/calendar.ge";	
				});
				$('#logout').click(function(){
					if(confirm('�α׾ƿ� �Ͻðڽ��ϱ�?')){
						loading();
						location.href='/ea/geLogOut.ge';
					}else{
					}
					
				});

		    });
		    function insertPopup(){
		    	loading();
		    	window.open("", "calendarPopup", "width=380, height=700, left=100, top=50");
        		$("#dateTest").attr("value", "");
        		$("#ge_main").attr("target","calendarPopup");
				$("#ge_main").attr("action","/calendar/calendarPopup.ge").submit();
		    	
		    	
		    }
		    function loading(){
				setTimeout($('.mainframe .loader').show(), 1000);
			}
		    
		</script>
	</head>
		<body>
			<div id="ge_menudiv">
				<div class="topinfo">
					<div class="myname"><%=evo.getEmname() %>�� �ȳ��ϼ���.</div>
					<div class="logout" id="logout">logout</div>
				</div>
			</div>
			<div class="ge_maindiv">
			<form name="ge_main" id="ge_main">
				<div class="topmenu">
					<div class="p">
						<a href="/ea/gemain.ge">����</a>��
						<a href="/ea/eamain.ge">���� ����</a> ��
						<a href="#">���ڸ���</a>��
						<a href="#">������</a>��
						<a href="/board/boardMain.ge">�μ��Խ���</a>��
						<a href="/board/calendar.ge" style="color:#615F8D">��������</a>��
						<a href="#">������</a>��
						<a href="/em/myPageInfo.ge">����������</a>
					</div>
				</div><!-- <div class="topmenu"> -->
				<div class="topdiv">
				</div>
				<div class="outertable">
					<div class="ge_sidemenu">
						<div class="ge_myinfo">
							<div class="imgbox">
							<img class="myimg" src="/em_Image<%=evo.getEmpic()%>" />
							</div>
							<br>
							<div class="itsme"><%=evo.getEmname()%><br>(<%=evo.getDeptname() %>/<%=evo.getJobname() %>)</div>
						</div>
						<div class="menubtn"><div class="mbtneff"></div><a href="#" onclick="insertPopup(); return false;">������ ����ϱ�</a></div>
						<div class="clock" style="border:1px solid #cccccc; border-radius:1px; background:#D8D8D8; float:left; margin:10px 0 10px 0px; padding:5px 0 5px 0">
						<div align="center" style="font-size:20pt ;color:white; "class="dpTime" id="dpTime"></div>
						</div>		
					</div><!-- <div class="ge_sidemenu"> -->
						<div class="mainframe">
						
							<div class="calframe">
							<br>
								������ �ش��ϴ� ���� ��ĭ�� �����Ͻø� ������ ����� �� �ֽ��ϴ�.
							<br><br>
							</div>									
							<div style="width:900px; padding: 20px 0px 20px 70px;" id="calendar" align="center"></div>
						
								    
							    <input type="hidden" id="dateTest" name="dateTest">
							    <input type="hidden" id="emno" name="emno">
							    <input type="hidden" id="calno" name="calno">
						    
							
						</div>  <!-- mainframe -->
					</div>
				</form>
			</div>
			
			<div class="bottom">
			<div class="bottominfo">
			GE Project �� ����� ���ʱ� ������� 459 (���ʵ�, ��Ϻ���)<br>
			TEL : 02-1234-5678 / FAX : 02-1234-5678<br>
			Copyright �� GE Project. All Rights Reserved.
			</div>
			</div>
		</body>
</html>