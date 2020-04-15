<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List" %>
<%@ page import="com.spring.ge.vo.EaVO_" %>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>
<%  request.setCharacterEncoding("EUC-KR"); %> 
<%
Object obj = session.getAttribute("eminfo");
EmInfoVO emvo = null;
emvo = new EmInfoVO();
emvo = (EmInfoVO)obj;
String emno = emvo.getEmno();
String empic = emvo.getEmpic();
// String emno = request.getParameter("emno");
System.out.println("접속자 사번  : " + emno);

Object obj1 = request.getAttribute("appList");
Object obj2 = request.getAttribute("rjList");
Object obj3 = request.getAttribute("pgList");
Object obj4 = request.getAttribute("stList");

List<EaVO_> appList = null;
appList = (List<EaVO_>)obj1;
System.out.println("appList : " + appList.size());

List<EaVO_> rjList = null;
rjList = (List<EaVO_>)obj2;
System.out.println("rjList : " + rjList.size());

List<EaVO_> pgList = null;
pgList = (List<EaVO_>)obj3;
System.out.println("pgList : " + pgList.size());

List<EaVO_> stList = null;
stList = (List<EaVO_>)obj4;
System.out.println("stList : " + stList.size());

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>::: GE PROJECT :::</title>
	<link rel="stylesheet" type="text/css" href="/css/common.css" />
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/js/clock.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$('.mainframe .loader').hide(); 
		//	alert($('#ea_stepno').val());
			setInterval("dpTime()",0);
			
			$('.goDetail').click(function(){
				var r_val = $(this).parents("tr").attr("data-number");
//					alert('r_val : ' + r_val);
					var r_arr = r_val.split(',');
//					alert("r_arr[0] : " + r_arr[0]);
//					alert("r_arr[1] : " + r_arr[1]);
					$("#ea_no").val(r_arr[0]);
					$("#ea_stepno").val(r_arr[1]);
					loading();
					$("#ea_main").attr("action", "/ea_/eaReadDetail.ge");
					$("#ea_main").submit();
			});
			
			$('.goWriteDetail').click(function(){
				var r_val = $(this).parents("tr").attr("data-number");
//					alert('r_val : ' + r_val);
					var r_arr = r_val.split(',');
//					alert("r_arr[0] : " + r_arr[0]);
//					alert("r_arr[1] : " + r_arr[1]);
					$("#ea_no").val(r_arr[0]);
					$("#ea_stepno").val(r_arr[1]);
					loading();
					$("#ea_main").attr("action", "/ea_/eaWriteDetail.ge");
					$("#ea_main").submit();
			});
			
			$('#logout').click(function(){
				if(confirm('로그아웃 하시겠습니까?')){
					loading();
					location.href='/';
				}else{
				}
			});
		});
		
		

		function newEa(){
			// alert('newEa 함수');
			loading();
			window.open("", "pop", "width=700, height=200");
			
			$("#ea_main").attr("target", "pop");
			$("#ea_main").attr("action", '/ea_/gotoInsert.ge');
			$("#ea_main").submit();
		}
		
		function loading(){
			setTimeout($('.mainframe .loader').show(), 1000);
		}

	</script>
	</head>
	<body>
		<div id="ge_menudiv">
			<div class="topinfo">
				<div class="myname"><%=emvo.getEmname()%> 님 안녕하세요.</div>
				<div class="logout" id="logout">logout</div>
			</div>
		</div>
		<div class="ge_maindiv">
			<form name="ea_main" id="ea_main" method="post" accept-charset="EUC-KR">
				<input type="hidden" name="ea_stepno" id="ea_stepno">
				<input type="hidden" name="ea_no" id="ea_no" >
				<div class="topmenu">
					<div class="p">
					<a href="#">메인</a>｜
					<a href="/ea/eamain.ge" style="color:#615F8D">전자</a>
					<a href="/ea_/eamain.ge" style="color:#615F8D">결재</a>｜
					<a href="#">전자메일</a>｜
					<a href="#">조직도</a>｜
					<a href="/board/boardMain.ge">부서게시판</a>｜
					<a href="/board/calendar.ge">일정관리</a>｜
					<a href="#">쪽지함</a>｜
					<a href="/em/myPageInfo.ge">마이페이지</a></div>
				</div>
				<div class="topdiv">
				</div>
				<div class="outertable">
					<div class="ge_sidemenu">
						<div class="ge_myinfo">
							<div class="imgbox">
							<img class="myimg" src="/em_Image<%=empic%>" />
							</div>
							<br>
							<div class="itsme"><%=emvo.getEmname()%><br>(<%=emvo.getDeptname() %>/<%=emvo.getJobname() %>)</div>
						</div>
						<div class="menubtn"><div class="mbtneff"></div><a href="#" onclick="newEa();">새 결재</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaListAll.ge">전체 결재요청함</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaWriteAll.ge">내 전체결재함</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaWritePG.ge">내 진행결재함</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaWriteRJ.ge">내 반려결재함</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaWriteST.ge">내 대기결재함</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaWriteEnd.ge">내 완료결재함</a></div>
						<div class="clock" style="border:1px solid #cccccc; border-radius:1px; background:#D8D8D8; float:left; margin:10px 0 10px 0px; padding:5px 0 5px 0">
						<div align="center" style="font-size:20pt ;color:white; "class="dpTime" id="dpTime"></div>
						</div>			
					</div>
						<div class="mainframe">
							<div class="ge_menu1">
								<div class="maintb_1">
									<div class="getitle"><div class="t">결재요청함</div></div>
									<table class="inner_menu" width="100%">
										<colgroup>
											<col width="300px">
											<col width="130px">
											<col width="70px">
										</colgroup>
										<tr>
											<th class="et" >제목</th>
											<th class="et" >작성일</th>
											<th class="et" >상태</th>
										</tr>
<% 
	if(appList.size()>0){
		int appListSize = appList.size();
		System.out.println("appListSize : " + appListSize);
		for(int i=0; i < appListSize; i++)
		{ EaVO_ evo = (EaVO_)appList.get(i);
		  String status = evo.getEa_status();
		  if("01".equals(status)){
			  status = "품의";
		  }else if("02".equals(status)){
			  status = "승인";
		  }else if("03".equals(status)){
			  status = "최종승인";
		  }else if("04".equals(status)){
			  status = "반려";
		  }else if("05".equals(status)){
			  status = "대기";
		  }else if("06".equals(status)){
			  status = "대결";
		  }else if("07".equals(status)){
			  status = "전결";
		  }
		
%>		
										
										<tr data-number="<%=evo.getEa_no() %>,<%=evo.getEa_stepno() %>">
											<td><span class="goDetail"><%=evo.getEa_subject() %></span></td>
											<td><%=evo.getEa_insertdate() %></td>
											<td><%=status%></td>
										</tr>
<%
		}
	}else{
%>									    <tr>
											<td colspan="3"> no data </td>
										<tr>
<%
	}
%>
									</table>
								</div> <!-- maintb_1 -->
							</div> <!-- ge_menu1 -->
							<div class="ge_menu2">
								<div class="maintb_2">
									<div class="getitle"><div class="t">내 결재함</div></div>
									<table class="inner_menu" width="100%">
										<colgroup>
											<col width="300px">
											<col width="130px">
											<col width="70px">
										</colgroup>
										<tr>
											<th class="et" >제목</th>
											<th class="et" >작성일</th>
											<th class="et" >상태</th>
										</tr>
<% 
	if(pgList.size()>0){
		int pgListSize = pgList.size();
		System.out.println("pgListSize : " + pgListSize);
		for(int i=0; i < pgListSize; i++){ 
		  EaVO_ evo = (EaVO_)pgList.get(i);
		  String status = evo.getEa_status();
		  if("01".equals(status)){
			  status = "품의";
		  }else if("02".equals(status)){
			  status = "승인";
		  }else if("03".equals(status)){
			  status = "최종승인";
		  }else if("04".equals(status)){
			  status = "반려";
		  }else if("05".equals(status)){
			  status = "대기";
		  }else if("06".equals(status)){
			  status = "대결";
		  }else if("07".equals(status)){
			  status = "전결";
		  }
%>		
										<tr data-number="<%=evo.getEa_no() %>,<%=evo.getEa_stepno() %>">
											<td><span class="goWriteDetail"><%=evo.getEa_subject() %></span></td>
											<td><%=evo.getEa_insertdate() %></td>
											<td><%=status%></td>
										</tr>
<%
		}
	}else{
%>									    <tr>
											<td colspan="3"> no data </td>
										<tr>
<%
	}
%>
									</table>
								</div> <!-- maintb_2 -->
							</div> <!-- ge_menu2 -->
							<div class="ge_menu3">
								<div class="maintb_3">
									<div class="getitle"><div class="t">내 반려결재함</div></div>
									<table class="inner_menu" width="100%">
										<colgroup>
											<col width="300px">
											<col width="130px">
											<col width="70px">
										</colgroup>
										<tr>
											<th class="et" >제목</th>
											<th class="et" >날짜</th>
											<th class="et" >작성자</th>
										</tr>
<% 
	if(rjList.size()>0){
		int rjListSize = rjList.size();
		System.out.println("rjListSize : " + rjListSize);
		for(int i=0; i < rjListSize; i++)
		{ EaVO_ evo = (EaVO_)rjList.get(i);
		  String status = evo.getEa_status();
		  if("01".equals(status)){
			  status = "품의";
		  }else if("02".equals(status)){
			  status = "승인";
		  }else if("03".equals(status)){
			  status = "최종승인";
		  }else if("04".equals(status)){
			  status = "반려";
		  }else if("05".equals(status)){
			  status = "대기";
		  }else if("06".equals(status)){
			  status = "대결";
		  }else if("07".equals(status)){
			  status = "전결";
		  }

%>		
										<tr data-number="<%=evo.getEa_no() %>,<%=evo.getEa_stepno() %>">
											<td><span class="goWriteDetail"><%=evo.getEa_subject() %></span></td>
											<td><%=evo.getEa_insertdate() %></td>
											<td><%=status%></td>
										</tr>
<%
		}
	}else{
%>									    <tr>
											<td colspan="3"> no data </td>
										<tr>
<%
	}
%>
									</table>
								</div> <!-- maintb_3 -->
							</div> <!-- ge_menu3 -->
							<div class="ge_menu4">
								<div class="maintb_4">
									<div class="getitle"><div class="t">내 대기결재함</div></div>
									<table class="inner_menu" width="100%">
										<colgroup>
											<col width="300px">
											<col width="130px">
											<col width="70px">
										</colgroup>
										<tr>
											<th class="et" >제목</th>
											<th class="et" >작성일</th>
											<th class="et" >상태</th>
										</tr>
<% 
	if(stList.size()>0){
	for(int i=0; i < stList.size(); i++){ 
  	  EaVO_ evo = (EaVO_)stList.get(i);
	  String status = evo.getEa_status();
	  if("01".equals(status)){
		  status = "품의";
	  }else if("02".equals(status)){
		  status = "승인";
	  }else if("03".equals(status)){
		  status = "최종승인";
	  }else if("04".equals(status)){
		  status = "반려";
	  }else if("05".equals(status)){
		  status = "대기";
	  }else if("06".equals(status)){
		  status = "대결";
	  }else if("07".equals(status)){
		  status = "전결";
	  }
%>		
									<tr data-number="<%=evo.getEa_no() %>,<%=evo.getEa_stepno() %>">
										<td><span class="goWriteDetail"><%=evo.getEa_subject() %></span></td>
										<td><%=evo.getEa_insertdate() %></td>
										<td><%=status%></td>
									</tr>
<%
	}
	}else{
%>									    <tr>
										<td colspan="3"> no data </td>
									<tr>
<%
	}
%>
								</table>
							</div> <!-- maintb_4 -->
							</div> <!-- ge4 -->
							<div class="loader">
						     	<img src="/ge/loader.gif">
						    </div>
						</div>  <!-- maintable -->
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