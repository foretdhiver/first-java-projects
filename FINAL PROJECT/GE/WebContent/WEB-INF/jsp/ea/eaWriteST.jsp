<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List" %>
<%@ page import="com.spring.ge.vo.EaVO_" %>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>
<%  request.setCharacterEncoding("EUC-KR"); %> 
<%
// 세션에서 사원정보 불러오기
Object obj = session.getAttribute("eminfo");
EmInfoVO emvo = null;
emvo = new EmInfoVO();
emvo = (EmInfoVO)obj;
String emno = emvo.getEmno();
String empic = emvo.getEmpic();
System.out.println("접속자 사번  : " + emno);

//eaWriteST 정보
Object object = request.getAttribute("eaWriteST");
List<EaVO_> eaWriteST = (List<EaVO_>) object;
System.out.println("eaWriteST : " + eaWriteST);
System.out.println("eaWriteST.size() : " + eaWriteST.size());

// 페이징
int pageSize = 10;
int groupSize = 10;
int curPage = 1;
int totalCount = 0;
int pageNo = 0;

//Object obj2 = request.getAttribute("groupSize");
Object obj3 = request.getAttribute("curPage");
Object obj4 = request.getAttribute("pageNo");
Object obj5 = request.getAttribute("totalCount");

//String groupSize1 = obj2.toString();
String curPage1 = obj3.toString();
String pageNo1 = obj4.toString();
String totalCount1 = obj5.toString();

//groupSize = Integer.parseInt(groupSize1);
curPage = Integer.parseInt(curPage1);
pageNo = Integer.parseInt(pageNo1);
totalCount = Integer.parseInt(totalCount1);

//System.out.println("groupSize : " + groupSize);
System.out.println("curPage : " + curPage);
System.out.println("pageNo : " + pageNo);
System.out.println("totalCount : " + totalCount);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>::: GE PROJECT :::</title>
	<link rel="stylesheet" type="text/css" href="/css/eaList.css" />
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
			
			$('.goWriteDetail').click(function(){
				var r_val = $(this).parents("tr").attr("data-number");
//					alert('r_val : ' + r_val);
					var r_arr = r_val.split(',');
//					alert("r_arr[0] : " + r_arr[0]);
//					alert("r_arr[1] : " + r_arr[1]);
					$("#ea_no").val(r_arr[0]);
					$("#ea_stepno").val(r_arr[1]);
					loading();
					$("#eadetailSend").attr("action", "/ea_/eaWriteDetail.ge");
					$("#eadetailSend").submit();
			});
       		
		});

		function newEa(){
			// alert('newEa 함수');
			loading();
			window.open("", "pop", "width=700, height=200");
			
			$("#ge_main").attr("target", "pop");
			$("#ge_main").attr("action", '/ea_/gotoInsert.ge');
			$("#ge_main").submit();
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
			<form name="eadetailSend" id="eadetailSend" method="post">
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
					<a href="/board/boardMain.ge"  onclick="loading();">부서게시판</a>｜
					<a href="/board/calendar.ge"  onclick="loading();">일정관리</a>｜
					<a href="#">쪽지함</a>｜
					<a href="/em/myPageInfo.ge">마이페이지</a></div>
				</div>
				<div class="topdiv"></div>
				<div class="outertable">
					<div class="ge_sidemenu">
						<div class="ge_myinfo1">
							<div class="imgbox">
							<img class="myimg" src="/em_Image<%=empic%>" />
							</div> <!-- imgbox -->
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
							<div class="tit1"><div class="title">&nbsp;내 대기 문서리스트</div></div> <!-- tit1 -->
							<div class="tableContainer1">
				           		<table class="ea_mylist1">
					            	<colgroup>
										<col width="12%">
						           		<col width="25%">
						           		<col width="5%">
						           		<col width="5%">
						           		<col width="5%">
						           		<col width="5%">
									</colgroup>
									<tr>
										<th>문서번호</th>
										<th>제목</th>
										<th>결재상태</th>
										<th>작성자명</th>
										<th>작성일</th>
										<th>결재일</th>
									</tr>
									<%
	if(eaWriteST.size() > 0){
		for(int i=0; i < eaWriteST.size(); i++){
			EaVO_ evo = (EaVO_)eaWriteST.get(i);
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
								<tr data-number="<%=evo.getEa_no()%>,<%=evo.getEa_stepno()%>">
									<td class="val" id="ea_no"><%=evo.getEa_no() %></td>
									<td class="val" id="ea_subject" align="center"><span class="goWriteDetail"><%=evo.getEa_subject() %></span></td>
									<td class="val" id="ea_status" align="center"><%=status%></td>
									<td class="val" id="emname" align="center"><%=emvo.getEmname() %></td>
									<td class="val" id="ea_insertdate"><%=evo.getEa_insertdate() %></td>
									<td class="val" id="ea_finaldate"><%=evo.getEa_finaldate() %></td>
								</tr>
<%		}
	}else{
%>
								<tr>
									<td colspan ="6" align="center">작성한 결재문서가 없습니다.</td>
								</tr>
<%
	}
%>
 								</table> <!-- ea_mylist -->
					            <div class="paging_container" style="position: botton:0px; margin:middle;">
									<jsp:include page="paging.jsp" flush="true" >
									<jsp:param name="url" value="/ea_/eaWriteRJ.ge"/>
									<jsp:param name="str" value=""/>
									<jsp:param name="pageSize" value="<%=String.valueOf(pageSize) %>"/>
									<jsp:param name="groupSize" value="<%=String.valueOf(groupSize)%>"/>
									<jsp:param name="curPage" value="<%=String.valueOf(curPage) %>"/>
									<jsp:param name="totalCount" value="<%=String.valueOf(totalCount)%>"/>
									</jsp:include>
								</div> <!-- paging_container -->
							</div><!-- tableContainer1 -->
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