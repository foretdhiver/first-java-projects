<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.PjCalendarVO" %>
<%@ page import="java.util.ArrayList" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	Object obj = request.getAttribute("clist");

	//데이터뽑기
	ArrayList<PjCalendarVO> aListC = (ArrayList<PjCalendarVO>) obj; 
		if(obj != null){
			PjCalendarVO avo = aListC.get(0);


%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>::: GE PROJECT :::</title>
		
		<!-- 풀캘린더 불러오기 -->
	    <link href="/../fullcalendar-2.9.1/fullcalendar.css" rel="stylesheet"/>
        <link href="/../fullcalendar-2.9.1/fullcalendar.print.css" rel="stylesheet" media="print"/>
       
        <script type="text/javascript" src="/../fullcalendar-2.9.1/lib/moment.min.js" ></script>
        <script type="text/javascript" src="/../fullcalendar-2.9.1/lib/jquery.min.js"></script>
        <script type="text/javascript" src="/../fullcalendar-2.9.1/fullcalendar.js"></script>
        <script type="text/javascript" src="/../fullcalendar-2.9.1/lang/ko2.js"></script>
		<script type="text/javascript">
		
		$(document).ready(function(){
			
			var pcsub = "<%=avo.getPcsub()%>";
			var pcno = "<%=avo.getPcno()%>";
			var id = "<%=avo.getPcno()%>";
			var emname = "<%=avo.getEmname()%>";
			var pcsdate = "<%=avo.getPcsdate()%>";
			var pcedate = "<%=avo.getPcedate()%>";
			var deptcd = "<%=avo.getDeptcd()%>";
			var jobcd = "<%=avo.getJobcd()%>";
			var deptname = "<%=avo.getDeptname()%>";
			var jobname = "<%=avo.getJobname()%>";
			
			var events = [];

				$.ajax({
					url:"/pjCalendar/calList.ge",							
					headers:{
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override":"post"
					},
					dataType:'json',							
					success:function(data){		
						$.each(data, function (i,callist) {
				            events.push({
				                title    : callist.pcsub,
				                start    : callist.pcsdate,
				                end	     : callist.pcedate,
				                id 		 : callist.pcno,
				                pcno	 : callist.pcno,
				                deptname : callist.deptname,
				                jobname  : callist.jobname
				                
				            });//push
				        });//$.each(
					}//success:function
				});//ajax	

				//풀캘린더 함수			
				$("#calendar").fullCalendar({
					header: {
		                        left   :   "prev,next today",
		                        center :   "title",
		                        right  :    "month,agendaWeek,agendaDay,listMonth"
                             },
                    defaultDate  :   "2020-03-25",
                    locale       :   "ko",
                    editable     :   true,
                    eventLimit   :   true,
     				fixedWeekCount : false,
//      				aspectRatio: 2,
			    	dayClick  :  function(date){
			    		window.open("","calendarPopup","width=600, height=320, left=100, top=50");
			    		$("#calDetail").attr("target","calendarPopup");
	                    $("#calDetail").attr("action","/pjCalendar/calPopup.ge").submit(); 
	             	},			
                 	events: {
                    	url : "/pjCalendar/calList.ge",                   		         	
                    	events : [
<%
	if(aListC.size() > 0){

				for(int i = 0; i < aListC.size(); i++){
					PjCalendarVO cvo2 = aListC.get(i);
%>
							{
								title  : "<%=cvo2.getPcsub()%>",
								start  : "<%=cvo2.getPcsdate()%>",
								end    : "<%=cvo2.getPcedate()%>",
								pcno   : "<%=cvo2.getPcno()%>",
								pcsub : "<%=cvo2.getPcsub()%>",
								deptcd : "<%=cvo2.getDeptcd()%>",
								jobcd : "<%=cvo2.getJobcd()%>",
								deptname : "<%=cvo2.getDeptname()%>",
								jobname : "<%=cvo2.getJobname()%>",
								emname : "<%=cvo2.getEmname()%>",
								pcmemo : "<%=cvo2.getPcmemo()%>"
							},
<%
				}//for
		
%>				
              		]

                    	,error : function(){
                    	}//error
                    },//event
                    eventClick : function(event){
                    	if (events) {
                    		$("#pcno").val(event.pcno);
	        				var bb = $("#pcno").val();
                    		//alert('Event: ' + event.title);
                    		//alert('Id:' + bb);
                    		console.log("bb >>> " + bb);
                    		console.log("pcno >>> " + pcno);
                   	      	window.open("","calDetail","width=400, height=300, left=100, top=50"); 
		    				$("#calDetail").attr("target","calDetail");
	                    	$("#calDetail").attr("action","/pjCalendar/calDetail.ge").submit(); 
	  	                    	}
                    },             
				});//end of fullCalendar
			});//$(document).ready
		</script>
	</head>
	<body>
		<!-- 풀캘린더 -->
		<div id="calendar" style="width: 600px; margin : auto; display: inline-block;"></div>
		<div>
			<form id="calDetail" name="calDetail" method="post"
				encType="application/x-www-form-urlencoded">
				<input type="hidden" id="pcsdate" name="pcsdate" value="<%=avo.getPcsdate()%>"> 
				<input type="hidden" id="pcedate" name="pcedate" value="<%=avo.getPcedate()%>">
				<input type="hidden" id="pcno" name="pcno" value="<%=avo.getPcno()%>">
				<input type="hidden" id="pcsub" name="pcsub" value="<%=avo.getPcsub()%>">
				<input type="hidden" id="emname" name="emname" value="<%=avo.getEmname()%>">
				<input type="hidden" id="pcmemo" name="pcmemo" value="<%=avo.getPcmemo()%>">
				<input type="hidden" id="deptname" name="deptname" value="<%=avo.getDeptname()%>">
				<input type="hidden" id="jobname" name="jobname" value="<%=avo.getJobname()%>">
			</form>
		</div>
<%
				}
		}
%>		
	</body>
</html>