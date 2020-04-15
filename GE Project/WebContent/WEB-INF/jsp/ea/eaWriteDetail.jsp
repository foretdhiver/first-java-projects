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

Object object = request.getAttribute("eaDetail");
List<EaVO_> eaDetail = (List<EaVO_>) object;
Object str = request.getAttribute("doccd");
Object str1 = request.getAttribute("emno");
String doccd = str.toString();

System.out.println("[log] emvo : " + emvo);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>::: GE PROJECT :::</title>
	<link rel="stylesheet" type="text/css" href="/css/eadetail.css" />
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/js/clock.js"></script>
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
			</div> <!-- topinfo -->
		</div> <!-- ge_menudiv -->
		<div class="ge_maindiv">
			<form name="ea_main" id="ea_main" method="post">
				<input type="hidden" name="ea_stepno" id="ea_stepno">
				<input type="hidden" name="ea_no" id="ea_no" >
			</form>
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
				</div> <!-- topmenu -->
				<div class="topdiv"></div> <!-- topdiv -->
				<div class="outertable">
					<div class="ge_sidemenu">
						<div class="ge_myinfo1">
							<div class="imgbox">
							<img class="myimg" src="/em_Image<%=empic%>" />
							</div>
							<br>
							<div class="itsme"><%=emvo.getEmname()%><br>(<%=emvo.getDeptname() %>/<%=emvo.getJobname() %>)</div>
						</div> <!-- ge_myinfo -->
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
					</div> <!-- ge_sidemenu -->
						<div class="mainframe1">
							
<%
if(eaDetail != null){
	for(int i=0; i < eaDetail.size(); i++){
		EaVO_ vo = eaDetail.get(i);
%>	
			<form id="eaUpdateForm" name="eaUpdateForm" method="POST">
				<input type="hidden" id="ea_lineno" name="ea_lineno" value="<%=vo.getEa_lineno() %>">
				<input type="hidden" id="ea_stepno" name="ea_stepno" value="<%=vo.getEa_stepno() %>">
				<input type="hidden" id="ea_no" name="ea_no" value="<%=vo.getEa_no() %>">
				<input type="hidden" id="ea_status" name="ea_status" value="<%=vo.getEa_status()%>">
				<input type="hidden" id="ea_sign" name="ea_sign" value="<%=vo.getEa_sign() %>">
				<input type="hidden" id="ea_sign1" name="ea_sign1" value="<%=vo.getEa_sign1() %>">
				<input type="hidden" id="ea_sign2" name="ea_sign2" value="<%=vo.getEa_sign2() %>">
				<input type="hidden" id="ea_empno1" name="ea_empno1" value="<%=vo.getEa_empno1() %>">
				<input type="hidden" id="ea_empno2" name="ea_empno2" value="<%=vo.getEa_empno2() %>">
			</form>
			<form id="eaDetailForm" name="eaDetailForm" method="POST">
			<div class="fixincenter1">
				<div class="tit1"><div class="title"> 결재문서 정보</div></div>
<%
	if("H".equals(doccd)){
		for(int k=0; i < eaDetail.size(); i++){
			EaVO_ evo = eaDetail.get(k);
			
			String docname = evo.getEa_doccd();
			if("H".equals(docname)){
				docname = "휴가계";
			}
			if("P".equals(docname)){
				docname = "프로젝트 기안서";
			}
			String eacno = evo.getEacno();
			if("53".equals(eacno)){
				eacno = "오전반차";
			}
			if("54".equals(eacno)){
				eacno = "연차";
			}
			if("55".equals(eacno)){
				eacno = "특별휴가";
			}
			
			String status = evo.getEa_status();
			if("01".equals(status)){
				status = "품의";
			}
			if("02".equals(status)){
				status = "승인";
			}
			if("03".equals(status)){
				status = "최종승인";
			}
			if("04".equals(status)){
				status = "반려";
			}
			if("05".equals(status)){
				status = "대기";
			}
			if("06".equals(status)){
				status = "대결";
			}
			if("07".equals(status)){
				status = "전결";
			}
%>
		<table id="outlinetable" class="eawritetable">
			<tr>
				<td>
					<table class="eawritetable1">
								<tr>
									<th>결재번호</th>
									<td><%=evo.getEa_no() %></td>
								</tr>
								<tr>
									<th>제목</th>
									<td><%=evo.getEa_subject() %></td>
								</tr>
								<tr>
									<th>결재신청일</th>
									<td><%=evo.getEa_insertdate() %></td>
								</tr>
								<tr>
									<th> 결재상태 </th>
									<td><%=status%>
								</tr>
								<tr>
									<th>문서 종류</th>
									<td><%=docname%></td>
								</tr>
								<tr>
									<th> 근태 </th>
									<td><%=eacno%></td>
								</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="eawritetable2">
							<tr><th>결재라인</th></tr>
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
									<td><img src="/em_Image<%=evo.getEa_sign() %>" onError="this.src='/ea_Img/noimg.png'" width="100px" height="100px"/></td>
									<td><img src="/em_Image<%=evo.getEa_sign1() %>" onError="this.src='/ea_Img/noimg.png'" width="100px" height="100px"/></td>
									<td><img src="/em_Image<%=evo.getEa_sign2() %>" onError="this.src='/ea_Img/noimg.png'" width="100px" height="100px"/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th>문서내용</th>
					</tr>
					<tr>
						<td>
							<table border="1" width="100%">
								<tr>
									<td id="ea_memo"><%=evo.getEa_memo() %></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td id="eabutton" colspan="2" align="center">
							<input type="button" class="button" id="app" name="app" value="승인">
							<input type="button" class="button" id="rj" name="rj" value="반려">
							<input type="button" class="button" id="st" name="st" value="대기">
						</td>
					</tr>
				</table>
<%
		}
	}else if("P".equals(doccd)){
		for(int j=0; i < eaDetail.size(); i++){
			EaVO_ evo = eaDetail.get(j);
			String docname = evo.getEa_doccd();
			if("H".equals(docname)){
				docname = "휴가계";
			}
			if("P".equals(docname)){
				docname = "프로젝트 기안서";
			}
			
			String status = evo.getEa_status();
			if("01".equals(status)){
				status = "품의";
			}
			if("02".equals(status)){
				status = "승인";
			}
			if("03".equals(status)){
				status = "최종승인";
			}
			if("04".equals(status)){
				status = "반려";
			}
			if("05".equals(status)){
				status = "대기";
			}
			if("06".equals(status)){
				status = "대결";
			}
			if("07".equals(status)){
				status = "전결";
			}

%>
		<table id="outlinetable" class="eawritetable">
			<tr>
				<td>
					<table class="eawritetable1">
								<tr>
									<th colspan="2"> 결재 문서 </th>
								</tr>
								<tr>
									<td>결재번호</td>
									<td><%=evo.getEa_no() %></td>
								</tr>
								<tr>
									<td>제목</td>
									<td><%=evo.getEa_subject() %></td>
								</tr>
								<tr>
									<td>결재신청일</td>
									<td><%=evo.getEa_insertdate() %></td>
								</tr>
								<tr>
									<td> 결재상태 </td>
									<td><%=status%>
								</tr>
								<tr>
									<td>문서 종류</td>
									<td><%=docname%></td>
								</tr>
								<tr>
									<td> 기안내용 </td>
									<td><%=evo.getEadraftcontant() %></td>
								</tr>
							</table>
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
										<td><img src="/em_Image<%=evo.getEa_sign() %>" class="myimg" onError="this.src='/ea_Img/noimg.png'"/></td>
										<td><img src="/em_Image<%=evo.getEa_sign1() %>" class="myimg" onError="this.src='/ea_Img/noimg.png'"/></td>
										<td><img src="/em_Image<%=evo.getEa_sign2() %>" class="myimg" onError="this.src='/ea_Img/noimg.png'"/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table class="eawritetable3">
							<tr><th>기타</th></tr>
							<tr>
								<td><%=evo.getEa_memo() %></td>
							</tr>
							</table>
						</td>
					</tr>
				</table>
				</div>
			</form>

<%
		}
	}else{

%>
		<table class="eawritetable3">
			<tr>
				<td colspan="2">결재할 문서가 없습니다.</td>
			</tr>
		</table>
<%
	}
	}
}
%>
									<div class="loader">
						     	<img src="/ge/loader.gif">
						    		</div>
							</div> <!-- maintable -->
						</div> <!-- outertable1 -->
				</form>
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