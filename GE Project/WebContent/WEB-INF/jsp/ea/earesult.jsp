<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.spring.ge.vo.EaVO" %>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>
<%@ page import="java.util.Iterator"%>
<%  request.setCharacterEncoding("EUC-KR"); %> 
<%
	Object obj1 = session.getAttribute("eminfo");
	EmInfoVO emvo = null;
	emvo = new EmInfoVO();
	emvo = (EmInfoVO)obj1;
	String empic = emvo.getEmpic();
	Object obj = request.getAttribute("ea_no");
	System.out.println("earesult.jsp.obj : " + obj);
	List<EaVO> list = null;
	if(obj!=null){
		String ea_no = obj.toString();
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>earesult.jsp</title>
		<link rel="stylesheet" type="text/css" href="/css/docform.css" />
		<script type="text/javascript" src="/js/clock.js"></script>
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> 
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
		<script type="text/javascript">
		$(document).ready(function(){
			$('.mainframe .loader').hide(); 
			setInterval("dpTime()",0);
			loading();
			setTimeout("location.href='/ea/eamain.ge'",2000);
			$('#logout').click(function(){
				if(confirm('로그아웃 하시겠습니까?')){
					loading();
					location.href='/ea/geLogOut.ge';
				}else{
				}
				
			});
			
			$('#tomain').click(function(){
				location.href='/ea/eamain.ge';
			});
			
			$('.docdown').click(function(){
				var ea_dcd = $(this).parents("tr").attr("doc-cd");
				var ea_fname = $(this).closest("td").attr("doc-url");
				alert(ea_dcd + " / " + ea_fname);
				
				$("#ea_doccd").val(ea_dcd);
				$("#ea_file").val(ea_fname);
				
				if(confirm('해당 파일을 다운로드 하시겠습니까?')){
					loading();
					$("#eadoclibform").attr({
						"method":"POST",
						"action":"/ea/eadocdownload.ge"
					});	
					$("#eadoclibform").submit();
				}else{
				}
				
			});
		});
		
		function newEa(){
			loading();
			// alert('newEa 함수');
		//	window.open("", "pop", "width=550, height=200");
			$("#ge_main").attr("action", '/ea/eadoccdform.ge');
			$("#ge_main").attr("enctype", "multipart/form-data");
			$("#ge_main").attr("method", "POST");
		//	$("#ge_main").attr("target", "pop");
			$("#ge_main").submit();
		}
		
		function eaDocLib(){
			loading();
			// alert('eaDocLib 함수');
	//		window.open("", "pop", "width=450, height=200");
			$("#ge_main").attr("action", '/ea/eadoclib.ge');
			$("#ge_main").attr("enctype", "multipart/form-data");
			$("#ge_main").attr("method", "POST");
	//		$("#ge_main").attr("target", "pop");
			$("#ge_main").submit();
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
			<form name="ge_main" id="ge_main">
				<input type="hidden" name="ea_stepno" id="ea_stepno">
				<input type="hidden" name="ea_no" id="ea_no" >
			</form>
				<div class="topmenu">
				<div class="p">
					<a href="/ea/gemain.ge" onclick="loading();">메인</a>｜
					<a href="/ea/eamain.ge" style="color:#615F8D" onclick="loading();">전자</a>
					<a href="/ea_/eamain.ge" style="color:#615F8D" onclick="loading();">결재</a>｜
					<a href="#">전자메일</a>｜
					<a href="#">조직도</a>｜
					<a href="/board/boardMain.ge"  onclick="loading();">부서게시판</a>｜
					<a href="/board/calendar.ge"  onclick="loading();">일정관리</a>｜
					<a href="#" >쪽지함</a>｜
					<a href="/em/myPageInfo.ge"  onclick="loading();">마이페이지</a></div>
				</div>
				<div class="topdiv">
				</div>
				<div class="outertable">
					<div id="scroll" style="position:absolute;">
					<div class="ge_sidemenu">
						<div class="ge_myinfo">
							<div class="imgbox">
							<img class="myimg" src="/em_Image<%=empic%>" />
							</div>
							<br>
							<div class="itsme"><%=emvo.getEmname()%><br>(<%=emvo.getDeptname() %>/<%=emvo.getJobname() %>)</div>
							<br>
						</div>
						<div class="menubtn"><div class="mbtneff"></div><a href="#" onclick="newEa();">새 결재</a></div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamyapplist.ge" onclick="loading();">결재요청문서</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamylistall.ge" onclick="loading();">전체</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamylistpg.ge" onclick="loading();">진행</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamylistsb.ge" onclick="loading();">대기</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamylistfn.ge" onclick="loading();">완료</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamylistrt.ge" onclick="loading();">반려</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff" ></div>
						<a href="/ea/eadoclib.ge"  onclick="loading();" style="color:#615F8D">양식자료실</a><br>
						</div>
						
						<div class="clock" style="border:1px solid #cccccc; border-radius:1px; background:#D8D8D8; float:left; margin:10px 0 10px 0px; padding:5px 0 5px 0">
						<div align="center" style="font-size:20pt ;color:white; "class="dpTime" id="dpTime"></div>
						</div>	
					</div>
					</div>
					<div class="mainframe">
					<div class="fitincenter">
					<form name="earesult" id="earesult">
						<table class="docform">
							<tr>
								<th colspan="2">완료</th>
							</tr>
							<tr>
								<td>문서 번호</td>
								<td><%=ea_no %></td>
							</tr>
							<!-- <tr>
								<td>작성자</td>
								<td><%=emvo.getEmname() %>(<%=emvo.getEmno() %>)</td>
							</tr>
							 -->
							<tr>
								<td colspan="2" align="center"> 잠시 후 메인페이지로 이동합니다.</td>
							</tr>
						</table>
					</form>	
					</div>			
					<div class="loader">
						<img src="/ge/loader.gif">
					</div>
					</div>  <!-- mainframe -->
					</div>
				
			<div class="bottom">
			<div class="bottominfo">
			GE Project ｜ 서울시 서초구 강남대로 459 (서초동, 백암빌딩)<br>
			TEL : 02-1234-5678 / FAX : 02-1234-5678<br>
			Copyright ⓒ GE Project. All Rights Reserved.
			</div>
			</div>
		</div>
	</body>
<%}%>
</html>