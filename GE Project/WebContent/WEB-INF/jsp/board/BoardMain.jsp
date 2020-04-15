<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmInfoVO"%>
<%@ page import="com.spring.ge.vo.BDVO"%>
<%@ page import="java.util.ArrayList" %>
<%
	request.setCharacterEncoding("EUC-KR");
%>
<%
Object obj = session.getAttribute("eminfo");
EmInfoVO evo = null;
evo = new EmInfoVO();
evo = (EmInfoVO)obj;

String userid = evo.getEmid();
String deptcd = evo.getDeptcd();

Object objExecutive = request.getAttribute("executive");
ArrayList<BDVO> executive = null;
executive = (ArrayList<BDVO>)objExecutive;

Object objSalesSupport = request.getAttribute("salesSupport");
ArrayList<BDVO> salesSupport = null;
salesSupport = (ArrayList<BDVO>)objSalesSupport;

Object objSales = request.getAttribute("sales");
ArrayList<BDVO> sales = null;
sales = (ArrayList<BDVO>)objSales;

Object objTechnicalSupport = request.getAttribute("technicalSupport");
ArrayList<BDVO> technicalSupport = null;
technicalSupport = (ArrayList<BDVO>)objTechnicalSupport;

%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="/css/common.css" />
		<title>업무게시판 게시글 목록</title>
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="/js/clock.js" charset="EUC-KR"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$('.mainframe .loader').hide();
				setInterval("dpTime()",0);
				
				$("#ExecutiveDept").click(function(){
					var deptcd = "<%=deptcd%>";

					if(deptcd != "20"){
						alert("접근 권한이 존재하지 않습니다.");
						return;
					}
					loading();
					$("#ge_main").attr("action","/board/bDList.ge").submit();					
				});				
				$("#SalesSupportDept").click(function(){
					var deptcd = "<%=deptcd%>";
					
					if(deptcd != "30"){
						alert("접근 권한이 존재하지 않습니다.");
						return;
					}
					loading();
					$("#ge_main").attr("action","/board/bDList.ge").submit();					
				});
				$("#SalesDept").click(function(){
					var deptcd = "<%=deptcd%>";
					
					if(deptcd != "40"){
						alert("접근 권한이 존재하지 않습니다.");
						return;
					}
					loading();
					$("#ge_main").attr("action","/board/bDList.ge").submit();					
				});				
				$("#TechnicalSupportDept").click(function(){
					var deptcd = "<%=deptcd%>";
					
					if(deptcd != "50"){
						alert("접근 권한이 존재하지 않습니다.");
						return;
					}
					loading();
					$("#ge_main").attr("action","/board/bDList.ge").submit();					
				});
				
				
				$(".goDetail").click(function(){
					var deptcdUser = "<%=evo.getDeptcd()%>";
					var data_All = $(this).parents("tr").attr("data-num");
					var data_arr = data_All.split(',');
					var bdno = data_arr[0];
					var deptcdList = data_arr[1];
					console.log(bdno);
					console.log(deptcdList);
					if(<%=evo.getDeptcd()%> != deptcdList){
						alert("접근 권한이 존재하지 않습니다.");
						return;
					}
					
					$("#bdno").val(bdno);
					//상세 페이지로 이동하기 위해 form 추가
					$("#ge_main").attr({
						"method" : "POST",
						"action" : "/board/bDDetail.ge"
					});
					loading();
					$("#ge_main").submit();
					
				});
				
				$('#logout').click(function(){
					if(confirm('로그아웃 하시겠습니까?')){
						loading();
						location.href='/ea/geLogOut.ge';
					}else{
					}
					
				});
				
			});
			
			function deptcdCheck1(check){
				var checkDept = check;
				var userDept = "<%=evo.getDeptcd() %>";
				if(checkDept != userDept){
					alert("접근 권한이 존재하지 않습니다.");
					return;
				}
				loading();
				$("#ge_main").attr("action","/board/bDList.ge").submit();	
			}
			function deptcdCheck2(check){
				var checkDept = check;
				var userDept = "<%=evo.getDeptcd() %>";
				if(checkDept != userDept){
					alert("접근 권한이 존재하지 않습니다.");
					return;
				}
				loading();
				$("#ge_main").attr("action","/board/bTList.ge").submit();	
			}
			
			function loading(){
				setTimeout($('.mainframe .loader').show(), 1000);
			}
			
		</script>
	</head>
	<body>
		<div id="ge_menudiv">
			<div class="topinfo">
				<div class="myname"><%=evo.getEmname()%> 님 안녕하세요.</div>
				<div class="logout" id="logout">logout</div>
			</div>
		</div>
		<div class="ge_maindiv">
			<form name="ge_main" id="ge_main">
			<input type="hidden" name="bdno" id="bdno">
			<div class="topmenu">
				<div class="p">
					<a href="/ea/gemain.ge">메인</a>｜
					<a href="/ea/eamain.ge">전자 결재</a>｜
					<a href="#">전자메일</a>｜
					<a href="#">조직도</a>｜
					<a href="/board/boardMain.ge" style="color:#615F8D">부서게시판</a>｜
					<a href="/board/calendar.ge">일정관리</a>｜
					<a href="#">쪽지함</a>｜
					<a href="/em/myPageInfo.ge">마이페이지</a></div>
				</div><!-- <div class="p"> -->
			</div><!-- <div class="topmenu"> -->
				<div class="topdiv">
				</div><!-- <div class="topdiv"> -->
				<div class="outertable">
					<div class="ge_sidemenu">
						<div class="ge_myinfo">
							<div class="imgbox">
							<img class="myimg" src="/em_Image<%=evo.getEmpic()%>" />
							</div>
							<br>	
							<div class="itsme"><%=evo.getEmname()%><br>(<%=evo.getDeptname() %>/<%=evo.getJobname() %>)</div>
						</div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/board/bNList.ge" onclick="">공지사항</a></div>
						<br>
						<div class="menubtn"><div class="mbtneff"></div><a href="#" onclick="deptcdCheck1(20); return false;">관리부 게시판</a></div>
						<a href="javascript:deptcdCheck2(20);" style="color: black">&nbsp;&nbsp;ㄴ업무게시판</a><br><br>
						<div class="menubtn"><div class="mbtneff"></div><a href="#" onclick="deptcdCheck1(30); return false;">영업지원부 게시판</a></div>
						<a href="javascript:deptcdCheck2(30);" style="color: black">&nbsp;&nbsp;ㄴ업무게시판 </a><br><br>
						<div class="menubtn"><div class="mbtneff"></div><a href="#" onclick="deptcdCheck1(40); return false;">영업부 게시판</a></div>
						<a href="javascript:deptcdCheck2(40);" style="color: black">&nbsp;&nbsp;ㄴ업무게시판 </a><br><br>
						<div class="menubtn"><div class="mbtneff"></div>
						<a href="#" onclick="deptcdCheck1(50); return false;">기술지원부 게시판</a>
						</div>
						<a href="javascript:deptcdCheck2(50);" style="color: black">&nbsp;&nbsp;ㄴ업무게시판 </a><br><br>
						<div class="menubtn"><div class="mbtneff"></div><a href="/pjboard/pjBoardList.ge" onclick="">프로젝트게시판</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/anmsBoard/anBoardList.ge" onclick="">익명게시판</a></div>						
						<div class="clock" style="border:1px solid #cccccc; border-radius:1px; background:#D8D8D8; float:left; margin:10px 0 10px 0px; padding:5px 0 5px 0">
						<div align="center" style="font-size:20pt ;color:white; "class="dpTime" id="dpTime"></div>
						</div>
	
					</div><!-- <div class="ge_sidemenu"> -->
						<div class="mainframe">
							<div class="ge_menu1">
								<div class="maintb_1">
									<div class="getitle"><div class="t">관리부</div></div>
									<table class="inner_menu" width="100%">
										<colgroup>
											<col width="300px">
											<col width="100px">
											<col width="100px">
										</colgroup>
										<tr>
											<th class="et" >제목</th>
											<th class="et" >작성자</th>
											<th class="et" >작성일</th>
										</tr>		
				<%
				if(executive.size() == 0){
					for(int i=0; i<8; i++){
				%>
										<tr>
											<td><br></td><!-- 제목 -->
											<td><br></td><!-- 작성자 -->
											<td><br></td><!-- 작성일 -->
										</tr>
				
				<%			
					}//end of for
				}else{
					for(int i=0; i<executive.size(); i++){
						BDVO bdvo = new BDVO();
						bdvo = executive.get(i);
				%>
										<tr data-num="<%=bdvo.getBdno() %>,<%=bdvo.getDeptcd() %>">
											<td><span class="goDetail"><%=bdvo.getBdsubject() %></span></td><!-- 제목 -->
											<td><%=bdvo.getEmname() %></td><!-- 작성자 -->
											<td><%=bdvo.getBdinsertdate() %></td><!-- 작성일 -->
										</tr>				
				<%		
						
					}//end of for
					if(executive.size() < 8){
						for(int i=0; i<8-executive.size(); i++){
				%>
										<tr>
											<td><br></td><!-- 제목 -->
											<td><br></td><!-- 작성자 -->
											<td><br></td><!-- 작성일 -->
										</tr>
				<%					
						}//end of for
					}//end of if	
				}//end of if-else
				%>															    
									</table>
								</div>
							</div>
							<div class="ge_menu2">
								<div class="maintb_2">
									<div class="getitle"><div class="t">영업지원부</div></div>
									<table class="inner_menu" width="100%">
										<colgroup>
											<col width="300px">
											<col width="100px">
											<col width="100px">
										</colgroup>
										<tr>
											<th class="et" >제목</th>
											<th class="et" >작성자</th>
											<th class="et" >작성일</th>
										</tr>
				<%
				if(salesSupport.size() == 0){
					for(int i=0; i<8; i++){
				%>
										<tr>
											<td><br></td><!-- 제목 -->
											<td><br></td><!-- 작성자 -->
											<td><br></td><!-- 작성일 -->
										</tr>
				
				<%			
					}//end of for
				}else{
					for(int i=0; i<salesSupport.size(); i++){
						BDVO bdvo = new BDVO();
						bdvo = salesSupport.get(i);
				%>
										<tr data-num="<%=bdvo.getBdno() %>,<%=bdvo.getDeptcd() %>">
											<td><span class="goDetail"><%=bdvo.getBdsubject() %></span></td><!-- 제목 -->
											<td><%=bdvo.getEmname() %></td><!-- 작성자 -->
											<td><%=bdvo.getBdinsertdate() %></td><!-- 작성일 -->
										</tr>				
				<%		
						
					}//end of for
					if(salesSupport.size() < 8){
						for(int i=0; i<8-salesSupport.size(); i++){
				%>
										<tr>
											<td><br></td><!-- 제목 -->
											<td><br></td><!-- 작성자 -->
											<td><br></td><!-- 작성일 -->
										</tr>
				<%					
						}//end of for
					}//end of if	
				}//end of if-else
				%>

									</table>
								</div>
							</div>
							<div class="ge_menu3">
								<div class="maintb_3">
									<div class="getitle"><div class="t">영업부</div></div>
									<table class="inner_menu" width="100%">
										<colgroup>
											<col width="300px">
											<col width="100px">
											<col width="100px">
										</colgroup>
										<tr>
											<th class="et" >제목</th>
											<th class="et" >작성자</th>
											<th class="et" >작성일</th>
										</tr>
				<%
				if(sales.size() == 0){
					for(int i=0; i<8; i++){
				%>
										<tr>
											<td><br></td><!-- 제목 -->
											<td><br></td><!-- 작성자 -->
											<td><br></td><!-- 작성일 -->
										</tr>
				
				<%			
					}//end of for
				}else{
					for(int i=0; i<sales.size(); i++){
						BDVO bdvo = new BDVO();
						bdvo = sales.get(i);
				%>
										<tr data-num="<%=bdvo.getBdno() %>,<%=bdvo.getDeptcd() %>">
											<td><span class="goDetail"><%=bdvo.getBdsubject() %></span></td><!-- 제목 -->
											<td><%=bdvo.getEmname() %></td><!-- 작성자 -->
											<td><%=bdvo.getBdinsertdate() %></td><!-- 작성일 -->
										</tr>				
				<%		
						
					}//end of for
					if(sales.size() < 8){
						for(int i=0; i<8-sales.size(); i++){
				%>
										<tr>
											<td><br></td><!-- 제목 -->
											<td><br></td><!-- 작성자 -->
											<td><br></td><!-- 작성일 -->
										</tr>
				<%					
						}//end of for
					}//end of if	
				}//end of if-else
				%>								    
									</table>
								</div>
							</div>
							<div class="ge_menu4">
							<div class="maintb_4">
								<div class="getitle"><div class="t">기술지원부</div></div>
								<table class="inner_menu" width="100%">
									<colgroup>
										<col width="300px">
										<col width="100px">
										<col width="100px">
									</colgroup>
									<tr>
										<th class="et" >제목</th>
										<th class="et" >작성자</th>
										<th class="et" >작성일</th>
									</tr>								    
				<%
				if(technicalSupport.size() == 0){
					for(int i=0; i<8; i++){
				%>
										<tr>
											<td><br></td><!-- 제목 -->
											<td><br></td><!-- 작성자 -->
											<td><br></td><!-- 작성일 -->
										</tr>
				
				<%			
					}//end of for
				}else{
					for(int i=0; i<technicalSupport.size(); i++){
						BDVO bdvo = new BDVO();
						bdvo = technicalSupport.get(i);
				%>
										<tr data-num="<%=bdvo.getBdno() %>,<%=bdvo.getDeptcd() %>">
											<td><span class="goDetail"><%=bdvo.getBdsubject() %></span></td><!-- 제목 -->
											<td><%=bdvo.getEmname() %></td><!-- 작성자 -->
											<td><%=bdvo.getBdinsertdate() %></td><!-- 작성일 -->
										</tr>				
				<%		
						
					}//end of for
					if(technicalSupport.size() < 8){
						for(int i=0; i<8-technicalSupport.size(); i++){
				%>
										<tr>
											<td><br></td><!-- 제목 -->
											<td><br></td><!-- 작성자 -->
											<td><br></td><!-- 작성일 -->
										</tr>
				<%					
						}//end of for
					}//end of if	
				}//end of if-else
				%>
									

								</table>
							</div>
							</div>
						</div>  <!-- mainframe -->
					</div><!-- <div class="outertable"> -->
				</form>
				<div class="bottom">
				<div class="bottominfo">
				GE Project ｜ 서울시 서초구 강남대로 459 (서초동, 백암빌딩)<br>
				TEL : 02-1234-5678 / FAX : 02-1234-5678<br>
				Copyright ⓒ GE Project. All Rights Reserved.
				</div>
				</div>
			</div><!-- <div class="ge_maindiv"> -->
	</body>
</html>