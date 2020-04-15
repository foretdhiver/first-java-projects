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
System.out.println("접속자 사번  : " + emno);

Object line = request.getAttribute("ea_lineno");
Object step = request.getAttribute("ea_stepno");
Object empno = request.getAttribute("ea_empno");
Object keyword = request.getAttribute("send");

String ea_lineno = (String) line;
String ea_stepno = (String) step;
String ea_empno = (String) empno;
String send = (String) keyword;

if(line != null && step != null && empno != null){
	System.out.println("[log] ea_lineno : " + ea_lineno);
	System.out.println("[log] ea_stepno : " + ea_stepno);
	System.out.println("[log] ea_empno : " + ea_empno);
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>::: GE PROJECT :::</title>
	<link rel="stylesheet" type="text/css" href="/css/eaForm.css" />
	<script type="text/javascript" src="/js/clock.js"></script>
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> 
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
	<script type="text/javascript">
		$(document).ready(function(){
			$('.mainframe1 .loader').hide();
		//	alert($('#ea_stepno').val());
			setInterval("dpTime()",0);
			
			$('#logout').click(function(){
				if(confirm('로그아웃 하시겠습니까?')){
					loading();
					location.href='/';
				}else{
				}
			});
       		
		});
		
		function insertHclick(){
//			alert("휴가계 결재 신청");
			$("#ea_status").val("01");
//			alert("ea_status : " + $("#ea_status").val());
			if($('#ea_subject').val()==""){
				alert('제목을 입력해주세요');
				return false;
			}
			if($('#ea_memo').val()==""){
				alert('결재문서에 대한 내용을 작성하세요');
				return false;
			}
			if(confirm('휴가계를 작성하시겠습니까?')){
				$("#insertH").attr("action", "/ea_/eaInsertEa.ge").submit();
			}else{
				
			}
			
		}
		
		function insertPclick(){
//			alert("프로젝트 기안서 결재 신청");
			$("#ea_status").val("01");
//			alert("ea_status : " + $("#ea_status").val());
			if($('#ea_subject').val()==""){
				alert('제목을 입력해주세요');
				return false;
			}
			if($('#eadraftcontant').val()==""){
				alert('기안내용을 입력해주세요');
				return false;
			}
			if($('#ea_memo').val()==""){
				alert('결재문서에 대한 내용을 작성하세요');
				return false;
			}
			
			if(confirm('프로젝트 기안서를 작성하시겠습니까?')){
				$("#insertP").attr("action", "/ea_/eaInsertEa.ge").submit();
			}else{
				
			}
			
		}

		function newEa(){
			// alert('newEa 함수');
			loading();
			window.open("", "pop", "width=550, height=200");
			
			$("#ea_main").attr("target", "pop");
			$("#ea_main").attr("action", '/ea_/gotoInsert.ge');
			$("#ea_main").submit();
		}
		
		function loading(){
			setTimeout($('.mainframe1 .loader').show(), 1000);
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
			</form>
				<div class="topmenu">
					<div class="p">
					<a href="/ea/eamain.ge" style="color:#615F8D">전자</a>
					<a href="/ea_/eamain.ge" style="color:#615F8D">결재</a>｜
					<a href="#">전자메일</a>｜
					<a href="#">조직도</a>｜
					<a href="#">부서게시판</a>｜
					<a href="#">일정관리</a>｜
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
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaListStand.ge">대기 결재요청함</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaWriteAll.ge">내 전체결재함</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaWritePG.ge">내 진행결재함</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaWriteRJ.ge">내 반려결재함</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaWriteST.ge">내 대기결재함</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaWriteEnd.ge">내 완료결재함</a></div>
						<div class="clock" style="border:1px solid #cccccc; border-radius:1px; background:#D8D8D8; float:left; margin:10px 0 10px 0px; padding:5px 0 5px 0">
						<div align="center" style="font-size:20pt ;color:white; "class="dpTime" id="dpTime"></div>
						</div>	
					</div>
						<div class="mainframe1">
							<div class="tit1"><div class="title">&nbsp;결재문서 작성</div></div>
<%
	if("H".equals(send.toUpperCase())){
%>
		<form id="insertH" name="insertH" method="POST">
			<input type="text" id="ea_lineno" name="ea_lineno" value="<%=ea_lineno%>">
			<input type="text" id="ea_stepno" name="ea_stepno" value="<%=ea_stepno%>" readonly>
			<table class="docform">
				<tr>
					<th>작성자</th>
					<td>
						<input type="text" id="ea_empno" name="ea_empno" value="<%=ea_empno %>" readonly>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>
						<input type="text" id="ea_subject" name="ea_subject">
					</td>
				</tr>
				<tr>
					<th>문서이름</th>
					<td>
						<input type="hidden" id="ea_doccd" name="ea_doccd" value="H"> 휴가계
					</td>
				</tr>
				<tr>
					<th>연차 종류</th>
					<td>
						<input type="radio" id="eacno" name="eacno" value="53"> 반차
						<input type="radio" id="eacno" name="eacno" value="54"> 연차
						<input type="radio" id="eacno" name="eacno" value="55"> 특별휴가
					</td>
				</tr>
				<tr>
					<th>결재상태</th>
					<td>
						<input type="text" id="ea_status" name="ea_status" value="품의" readonly>
					</td>
				</tr>
				<tr>
					<th>메모</th>
					<td>
						<textarea id="ea_memo" name="ea_memo" cols="30" rows="10"
						class="js-sms-content"></textarea><div class="bytes-wrapper">
							<span class="bytes"> 0 </span> bytes
						</div>
					</td>
				</tr>
				<div class="ealieformbuttonplace">
					<tr>
						<td colspan="2" align="center">
							<input type="hidden" id="ea_logno" name="ea_logno">
							<input type="hidden" id="ea_no" name="ea_no">
							<input type="button" class="button" onclick="insertHclick()" value="전송">
						</td>
					</tr>
				</div>
			</table>
		</form>
<%
	}else if("P".equals(send.toUpperCase())){
%>
		<form id="insertP" name="insertP" method="POST">
			<input type="hidden" id="ea_lineno" name="ea_lineno" value="<%=ea_lineno%>">
			<input type="hidden" id="ea_stepno" name="ea_stepno" value="<%=ea_stepno%>">
			<input type="hidden" id="ea_empno" name="ea_empno" value="<%=ea_empno %>">
			<div class="tbborder">
			<table id="outlinetable" class="eawritetable">
				<table class="eawritetable1">
				<tr>
					<th colspan="2">결재문서 양식</th>
				</tr>
				<tr>
					<th>작성자</th>
					<td>
						<%=emvo.getEmname()%> 
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>
						<input type="text" id="ea_subject" name="ea_subject">
					</td>
				</tr>
				<tr>
					<th>문서이름</th>
					<td>
						<input type="hidden" id="ea_doccd" name="ea_doccd" value="P"> 프로젝트 기안서
					</td>
				</tr>
				<tr>
					<th>결재상태</th>
					<td>
						<input type="text" id="ea_status" name="ea_status" value="품의" readonly>
					</td>
				</tr>
				<tr>
					<th>기안내용</th>
					<td>
						<textarea id="eadraftcontant" name="eadraftcontant" cols="50" rows="10"></textarea>
					</td>
				</tr>
				<tr>
					<th>메모</th>
					<td>
						<textarea id="ea_memo" name="ea_memo" cols="50" rows="10"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="hidden" id="ea_logno" name="ea_logno">
						<input type="hidden" id="ea_no" name="ea_no">
						<input type="button" class="button" onclick="insertPclick()" value="전송">
					</td>
				</tr>
				</table>
				</table>
			</div>
		</form>
<%
	}else{
		System.out.println("send에 담긴 게 아무것도 없어서 폼 못 보여줌");
	}
%>
							<div class="loader">
						     	<img src="/ge/loader.gif">
						    </div> <!-- loader -->
						</div>  <!-- mainframe -->
					</div> <!-- outertable -->
				<div class="bottom">
				<div class="bottominfo">
				GE Project ｜ 서울시 서초구 강남대로 459 (서초동, 백암빌딩)<br>
				TEL : 02-1234-5678 / FAX : 02-1234-5678<br>
				Copyright ⓒ GE Project. All Rights Reserved.
				</div> <!-- bottominfo -->
			</div> <!-- bottom -->
		</div> <!-- ge_maindiv -->
	</body>
</html>