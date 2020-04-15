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
	Object obj = request.getAttribute("eaList");
	System.out.println("obj : " + obj);
	List<EaVO> ealist = null;
	ealist = (List<EaVO>)obj;
	
	session.setAttribute("eaList", obj);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>eadetail.jsp</title>
		<link rel="stylesheet" type="text/css" href="/css/detailform.css" />
		<script type="text/javascript" src="/js/clock.js"></script>
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> 
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
		<script type="text/javascript">
		$(document).ready(function(){
			$('.mainframe .loader').hide();
			$("#eabutton").hide();
			$("#eafileupbutton").hide();
			setInterval("dpTime()",0);
			
			$('#logout').click(function(){
				if(confirm('로그아웃 하시겠습니까?')){
					loading();
					location.href='/ea/geLogOut.ge';
				}else{
				}
				
			});
			
			$('#download').click(function(){
				loading();
				location.href='/ea/geLogOut.ge';
			});
			
			$('#tomain').click(function(){
				loading();
				location.href='/ea/eamain.ge';
			});
			$('.filedown').click(function(){
				var r_val = $(this).parents("tr").attr("data-value");
		//		alert('r_val : ' + r_val);
				var r_arr = r_val.split(',');
		//		alert("r_arr[0] : " + r_arr[0]);
		//		alert("r_arr[1] : " + r_arr[1]);
		//		alert("r_arr[2] : " + r_arr[2]);
				$("#ea_file").val(r_arr[0]);
				$("#ea_file1").val(r_arr[1]);
				$("#ea_file2").val(r_arr[2]);
				
				if(confirm('해당 파일을 다운로드 하시겠습니까?')){
					loading();
					$("#eafiledown").attr({
						"method":"POST",
						"action":"/ea/eafiledownload.ge"
					});	
					$("#eafiledown").submit();
				}else{
				}
			});
		});
	
		function eaback(){
			loading();
			history.go(-1);
		}
		
		function newEa(){
			loading();
			// alert('newEa 함수');
//			window.open("", "pop", "width=550, height=200");
			$("#ge_main").attr("action", '/ea/eadoccdform.ge');
			$("#ge_main").attr("enctype", "multipart/form-data");
			$("#ge_main").attr("method", "POST");
//			$("#ge_main").attr("target", "pop");
			$("#ge_main").submit();
		}
		
		function eaDocLib(){
			loading();
			// alert('eaDocLib 함수');
//			window.open("", "pop", "width=450, height=200");
			$("#ge_main").attr("action", '/ea/eadoclib.ge');
			$("#ge_main").attr("enctype", "multipart/form-data");
			$("#ge_main").attr("method", "POST");
//			$("#ge_main").attr("target", "pop");
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
					<a href="/ea/gemain.ge">메인</a>｜
					<a href="/ea/eamain.ge" style="color:#615F8D">전자</a>
					<a href=""/ea_/eamain.ge"" style="color:#615F8D">결재</a>｜
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
					<div id="scroll" style="position:absolute;">
					<div class="ge_sidemenu">
						<div class="ge_myinfo">
							<div class="imgbox">
							<img class="myimg" src="/em_Image<%=empic%>" />
							</div>
							<br>
							<div class="itsme"><%=emvo.getEmname()%><br>(<%=emvo.getDeptname() %>/<%=emvo.getJobname() %>)</div>
						</div>
						<div class="menubtn"><div class="mbtneff"></div><a href="#" onclick="newEa();">새 결재</a></div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamyapplist.ge">결재요청문서</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamylistall.ge">전체</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamylistpg.ge">진행</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamylistsb.ge">대기</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamylistfn.ge">완료</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamylistrt.ge">반려</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eadoclib.ge">양식자료실</a><br>
						</div>
						
						<div class="clock" style="border:1px solid #cccccc; border-radius:1px; background:#D8D8D8; float:left; margin:10px 0 10px 0px; padding:5px 0 5px 0">
						<div align="center" style="font-size:20pt ;color:white; "class="dpTime" id="dpTime"></div>
						</div>	
					</div>
					</div>
					<div class="mainframe">
<% 
	if(ealist.size()>0){
		int listCnt = ealist.size();
		System.out.println("listCnt : " + listCnt);
	for(int i=0;i<listCnt;i++)
	{ EaVO evo = (EaVO)ealist.get(i);
%>		
		<form name="eafiledown" id="eafiledown">
			<input type="hidden" id="ea_sign" name="ea_sign"/>
			<input type="hidden" id="ea_sign1" name="ea_sign1"/>
			<input type="hidden" id="ea_sign2" name="ea_sign2"/>
			<input type="hidden" id="ea_file" name="ea_file" value="<%=evo.getEa_file() %>" />
			<input type="hidden" id="ea_file1" name="ea_file1" />
			<input type="hidden" id="ea_file2" name="ea_file2" />
		</form>

		<div class="fixincenter">
		<div class="tit"><div class="title">&nbsp;결재문서 정보</div></div>
			<div class="docinfo">
			<br>
			결재문서 디테일 페이지 입니다.
			</div>
		
		<div class="ealinebuttonarray">
			<div class="ealineformback">
				<input type="button" class="button" value="이전" onclick="eaback();"/>	
			</div>
			<!-- 
			<div class="doccdformbuttonplace1">
				<input type="button" class="button"  value="상신" >
			</div>
			 -->
		</div>
		<div class="tbborder">
		<table id="outlinetable" class="eawritetable">
			<tr>
				<td>
					<table class="eawritetable1">
						<tr>
							<th colspan="2">결재 문서 작성</th>
						</tr>
						<tr>
							<td>문서번호</td>
							<td><%=evo.getEa_no() %></td>
						</tr>
						<tr>
							<td>문서제목</td>
							<td><%=evo.getEa_subject() %></td>
						</tr>
						<tr>
							<td>구분</td>
							<td>
								<%
							System.out.println("doccd : " + evo.getEa_doccd());
							if(evo.getEa_doccd().equals("H")){
								%>휴가계<%
							}else if(evo.getEa_doccd().equals("P")){
								%>프로젝트기안서<%
							}else if(evo.getEa_doccd().equals("R")){
								%>품의서<%
							}else if(evo.getEa_doccd().equals("S")){
								%>기술지원보고서<%
							}%>
							</td>
						</tr>
						<tr>
							<td>작성일</td>
							<td><%=evo.getEa_insertdate() %></td>
						</tr>
						<tr data-value="<%=evo.getEa_file() %>,<%=evo.getEa_file1() %>,<%=evo.getEa_file2() %>">
							<td>결재문서</td>
							<td id="ea_file">
								<span class="goDetail">
									<%=evo.getEa_file() %>
								</span>&nbsp;
								<img class="filedown" src="/ge/download.png"/>
							</td>
						</tr>
						<tr id="eafileupbutton">
							<td>결재문서 첨부</td>
							<td><input type="file" name="eafileupload1" id="eafileupload1" ></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="eawritetable2">
						<tr><th colspan="4">결재라인</th></tr>
						<tr>
							<td>구분</td>
							<td>작성자</td>
							<td>결재자</td>
							<td>결재자</td>
						</tr>
						<tr>
							<td>성명</td>
							<td><%=evo.getEmname() %></td>
							<td><%=evo.getEmname1() %></td>
							<td><%=evo.getEmname2() %></td>
						</tr>
						<tr>
							<td>소속부서</td>
							<td><%=evo.getDeptname() %></td>
							<td><%=evo.getDeptname1() %></td>
							<td><%=evo.getDeptname2() %></td>
						</tr>
						<tr>
							<td>직위</td>
							<td><%=evo.getJobname() %></td>
							<td><%=evo.getJobname1() %></td>
							<td><%=evo.getJobname2() %></td>
						</tr>
						<tr>
							<td>서명</td>
							<td><img src="/em_Image<%=evo.getEa_sign() %>" class="myimg" onError="this.src='/ea_Img/noimg.png'"  /></td>
							<td><img src="/em_Image<%=evo.getEa_sign1() %>" class="myimg" onError="this.src='/ea_Img/noimg.png'" /></td>
							<td><img src="/em_Image<%=evo.getEa_sign2() %>" class="myimg" onError="this.src='/ea_Img/noimg.png'" /></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="eawritetable3">
						<tr><th>비고</th></tr>
						<tr>
							<td>
								<%=evo.getEa_memo() %>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td id="eabutton" colspan="2" align="center">
					<input type="button" id="eaapprove" name="eaapprove" value="승인">
					<input type="button" id="eareject" name="eareject" value="반려" >	
					<input type="button" id="eastand" name="eastand" value="대기" >
				</td>
			</tr>
		</table>
		</div>
		</div>		
	
		
		
<%
	}
	}else{
%>
		<table class="eawritetable3">
				<tr>
					<td colspan="2">조회된 문서가 없습니다.</td>
				</tr>
		</table>
<%
	}
%>				
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
</html>