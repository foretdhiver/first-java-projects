<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmInfoVO"%>
<%@ page import="com.spring.ge.vo.BTVO"%>
<%
	request.setCharacterEncoding("EUC-KR");
%>
<%
Object obj = session.getAttribute("eminfo");
EmInfoVO evo = null;
evo = new EmInfoVO();
evo = (EmInfoVO)obj;

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title></title>
		<link rel="stylesheet" type="text/css" href="/css/common.css" />
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="/js/clock.js"></script>
		<script type="text/javascript" src="/include/js/HuskyEZCreator.js" charset="EUC-KR"></script>
		<script type="text/javascript">	
			var oEditors = [];
			$(function(){
				$('.mainframe .loader').hide();
				setInterval("dpTime()",0);
				
				nhn.husky.EZCreator.createInIFrame({
					 oAppRef: oEditors,
					 elPlaceHolder: "btcontent",
					 sSkinURI: "/include/SmartEditor2Skin.html",
					 htParams : {
						 bUseToolBar : true,
						 bUseVerticalResize : true,
						 bUseModeChanger : true,
						 fOnBeforeUnload : function(){
							 
						 }
					 },
					 fOnAppLoad : function(){
						 oEditors.getById["btcontent"].exec("PASTE_HTML", [ "" ]);
					 },
					 fCreator: "createSEditor2"
					});
				
				$("#BTInsert").click(function(){
					var subject = $("#btsubject").val();
					if(!subject){
						if(!confirm("제목 없이 입력하시겠습니까?")){
							return;
						}
					}
					
					oEditors.getById["btcontent"].exec("UPDATE_CONTENTS_FIELD", []);
					
					$("#ge_main").attr({
						"method" : "POST",
						"action" : "/board/bTWriteInsert.ge"
					});
					loading();
					$("#ge_main").submit();	
					
				});
				
				/* 목록 버튼 클릭 시 처리 이벤트 */
				$("#BTList").click(function(){
					loading();
					location.href="/board/bTList.ge"
					
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
			</div><!-- <div class="topinfo"> -->
		</div><!-- <div id="ge_menudiv"> -->
		<div class="ge_maindiv">
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
						<div id="noticebar" style="height:50px; width:10px; background:#D8D8D8; margin:20px 0px 0px 20px; float:left"></div>
						<div style="margin:20px; float:left; height:50px; width:400px; margin:20px 0px 0px 0px;"><h1>&nbsp;글쓰기</h1></div>
						<form name="ge_main" id="ge_main">
						<div style="margin:100px 0px 0px 20px; float:bottom">
						<table width="950px" style="margin:20px 0px 0px 10px; border-collapse: collapse; border:0px;">
							<tr>
								<td>
								<br>
								<br>
								<br>
									<table id="BTWrite" width="900px" height="430px" style="border-collapse: collapse; border:0px;">
										<colgroup>
											<col width="15%">
											<col width="35%">
											<col width="20%">
											<col width="20%">
										</colgroup>
										<tr height="50px" style="background:#D8D8D8; text-align: center;">
											<th>작성자</th>
											<th><%=evo.getEmid() %> ( <%=evo.getEmname() %> )</th>
											<th>부서</th>
											<th><%=evo.getDeptname() %> ( <%=evo.getJobname() %> )</th>
											
										</tr>
										<tr height="50x">
											<th style="background:#D8D8D8; text-align: center;">제목</th>
											<td><input type="text" name="btsubject" id="btsubject" style="width:100%; height:100%; border:0px;" placeholder="   제목을 입력해주세요"></td>
											<th style="background:#D8D8D8; text-align: center;">업무여부선택</th>
											<td>
												<select id="bttaskyn" name="bttaskyn" style="width:100%;">
											    <option value="Y">업무</option>
											    <option value="Y">업무외선택불가</option>
												</select>	
											</td>
										</tr>
										<tr height="300px">
											<th style="background:#D8D8D8; text-align:center;">내용</th>
											<td colspan="3" style="width:100%; height:100%">
												<textarea name="btcontent" id="btcontent"
												rows="10" cols="100" style="width:100%; height:300px; color:#666666;" > 내용 없음 </textarea>					
											</td>
										</tr>
										<tr height="50px">
											<td colspan="4" align="right">
												<div id="BTButton">
													<input type="button" class="button" value="저장" name="BTInsert" id="BTInsert">
													<input type="button" class="button" value="목록" name="BTList" id="BTList">			
												</div>												
											</td>
										</tr>
									</table>		
								</td>
							</tr>
						</table><!-- <table width="950px" style="margin:20px 0px 0px 10px; border-collapse: collapse; border:0px;"> -->
						</div><!-- <div  style="margin:100px 0px 0px 20px; float:bottom"> -->
						</form><!-- <form name="ge_main" id="ge_main"> -->
					</div><!-- <div class="mainframe"> -->
			</div><!-- <div class="outertable"> -->
			<div class="bottom">
			<div class="bottominfo">
			GE Project ｜ 서울시 서초구 강남대로 459 (서초동, 백암빌딩)<br>
			TEL : 02-1234-5678 / FAX : 02-1234-5678<br>
			Copyright ⓒ GE Project. All Rights Reserved.
			</div><!-- <div class="bottominfo"> -->
			</div><!-- <div class="bottom"> -->
		</div><!-- <div class="ge_maindiv"> -->
	</body>
</html>