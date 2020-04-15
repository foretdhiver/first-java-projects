<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.spring.ge.vo.EaVO" %>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>
<%@ page import="com.spring.ge.vo.BDVO"%>
<%@ page import="com.spring.ge.vo.BNVO"%>
<%@ page import="com.spring.ge.vo.CalendarVO"%>

<%  request.setCharacterEncoding("EUC-KR"); %>

<%
Object obj = session.getAttribute("eminfo");
EmInfoVO emvo = null;
emvo = new EmInfoVO();
emvo = (EmInfoVO)obj;
String emno = emvo.getEmno();
System.out.println("접속자 사번  : " + emno);

Object obj0 = request.getAttribute("eaListRJ");
Object obj1 = request.getAttribute("eaListALL");
Object obj2 = request.getAttribute("eaListFN");
Object obj3 = request.getAttribute("eaListPG");
Object obj4 = request.getAttribute("eaListAP");
Object stpobj = request.getAttribute("ea_stepno");

//************************************** 추가

Object objDeptList = request.getAttribute("deptList");
List<BDVO> deptList = null;
deptList = (List<BDVO>)objDeptList;
System.out.println("deptList >> " + deptList.size());

Object objNoticeList = request.getAttribute("noticeList");
List<BNVO> noticeList = null;
noticeList = (List<BNVO>)objNoticeList;
System.out.println("noticeList >> " + noticeList.size());

//************************************** 추가

String eastepno = stpobj.toString();

List<EaVO> eaListRJ = null;
eaListRJ = (List<EaVO>)obj0;
System.out.println("eaListRJ : " + eaListRJ.size());

List<EaVO> eaListALL = null;
eaListALL = (List<EaVO>)obj1;
System.out.println("eaListALL : " + eaListALL.size());

List<EaVO> eaListFN = null;
eaListFN = (List<EaVO>)obj2;
System.out.println("eaListFN : " + eaListFN.size());

List<EaVO> eaListPG = null;
eaListPG = (List<EaVO>)obj3;
System.out.println("eaListPG : " + eaListPG.size());

List<EaVO> eaListAP = null;
eaListAP = (List<EaVO>)obj4;
System.out.println("eaListAP : " + eaListAP.size());

//*************************************** 출/퇴근 추가
Object obj99 = request.getAttribute("ctList");
ArrayList<EmInfoVO> ctList = null;
ctList = (ArrayList<EmInfoVO>)obj99;
System.out.println("ctList.size() >>> : " + ctList.size());

EmInfoVO eVO = null;
eVO = new EmInfoVO();

//*************************************** 관리자 일정 추가
Object objadminCalendarList = request.getAttribute("adminCalendarList");
ArrayList<CalendarVO> adminCalendarList = null;
adminCalendarList = (ArrayList<CalendarVO>)objadminCalendarList;

%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>관리자 메인 페이지</title>
		<link rel="stylesheet" type="text/css" href="/css/admin_common.css" />
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="/js/clock.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$('.mainframe .loader').hide();
				
				setInterval("dpTime()",0);
				
				$('#logout').click(function(){
					if(confirm('로그아웃 하시겠습니까?')){
						loading();
						location.href='/ea/geLogOut.ge';
					}else{
					}
				});
				
			});
			
			function loading(){
				setTimeout($('.mainframe .loader').show(), 1000);
			}

		</script>
		<style type="text/css">
		.aa{
		text-align:left;
		}
		
		</style>
	</head>
	<body>
		<div id="ge_menudiv">
			<div class="topinfo">
				<div class="myname"><%=emvo.getEmname()%> 님 안녕하세요.</div>
				<div class="logout" id="logout">logout</div>
			</div>
		</div>
		<div class="ge_maindiv">
			<form name="ge_main" id="ge_main">
				<div class="topmenu">
						<div class="p">
						<a href="/board/bNList.ge">공지사항</a>｜
						<a href="/board/calendar.ge">일정관리</a>｜
						<a href="/em/emAllSelect.ge">사원관리</a>｜
						<a href="/em/ctAllSelect.ge">근태관리</a>｜
						<a href="#">교육사항</a>｜
						<a href="#">인사평가</a>
						</div>
				</div>
				<div class="topdiv">
				</div>
				<div class="outertable">
					<div class="ge_sidemenu">
						<div class="ge_myinfo">
							<div class="imgbox">
                    		 	<img class="myimg" src="/em_Image/<%=emvo.getEmpic()%>" />
                     		</div>
                     		<br>
							<div class="itsme"><%=emvo.getEmname()%><br>(<%=emvo.getDeptname() %>/<%=emvo.getJobname() %>)</div>
						</div>
						
						<div class="clock" style="border:1px solid #cccccc; border-radius:1px; background:#D8D8D8; float:left; margin:10px 0 10px 0px; padding:5px 0 5px 0">
						<div align="center" style="font-size:20pt ;color:white; "class="dpTime" id="dpTime"></div>
						</div>	
					</div>
						<div class="mainframe">
							<div class="ge_admin_menu1">
								<div class="maintb_1">
									<div class="getitle"><div class="t">공지사항</div></div>
									<table class="inner_menu" width="100%">
										<colgroup>
											<col width="300px">
											<col width="130px">
											<col width="70px">
										</colgroup>
										<tr>
											<th class="et" >제목</th>
											<th class="et" >작성일</th>
											<th class="et" >작성자</th>
										</tr>
<% 
	if(noticeList.size()>0){
		for(int i=0; i<noticeList.size(); i++){
			BNVO bnvo = null;
			bnvo = new BNVO();
			bnvo = noticeList.get(i);	
%>		
										<tr data-num="<%=bnvo.getBnno() %>">
											<td><span class="goNoticeDetail"><%=bnvo.getBnsubject() %></span></td>
											<td><%=bnvo.getBninsertdate() %></td>
											<td><%=bnvo.getEmname() %></td>
										</tr>
<%
		}//end of for
	}else if(noticeList.size()<1){
%>									    <tr>
											<td colspan="3"> no data </td>
										<tr>
<%
	}
%>
									</table>
								</div>
							</div>
							<div class="ge_admin_menu2">
								<div class="maintb_2">
									<div class="getitle"><div class="t">회사일정</div></div>
									<table class="inner_menu" width="100%">
										<colgroup>
											<col width="100px">
											<col width="200px">
											<col width="100px">
											<col width="100px">
										</colgroup>
										<tr>
											<th class="et" >일정제목</th>
											<th class="et" >일정내용</th>
											<th class="et" >시작시간</th>
											<th class="et" >끝나는시간</th>
										</tr>
<% 
	if(adminCalendarList.size()>0){
		for(int i=0; i<adminCalendarList.size(); i++){
			CalendarVO cvo = null;
			cvo = new CalendarVO();
			cvo = adminCalendarList.get(i);
%>		
										<tr>
											<td align="left">&nbsp;&nbsp;&nbsp;<%=cvo.getCalsubject() %></td>
											<td class="aa"><%=cvo.getCalcontent() %></td>
											<td align="center"><%=cvo.getCalstarttime() %></td>
											<td align="center"><%=cvo.getCalendtime() %></td>
										</tr>
<%
		}//end of for
	}else{
%>									    <tr>
											<td colspan="3"> no data </td>
										<tr>
<%
	}
%>
									</table>
								</div>
							</div>
						</div>	
				</div>
			</form>	
			<div class="bottom">
			<div class="bottominfo">
				GE Project ｜ 서울시 서초구 강남대로 459 (서초동, 백암빌딩)<br>
				TEL : 02-1234-5678 / FAX : 02-1234-5678<br>
				Copyright ⓒ GE Project. All Rights Reserved.
			</div>
			</div>
		</div>
	</body>
</html>