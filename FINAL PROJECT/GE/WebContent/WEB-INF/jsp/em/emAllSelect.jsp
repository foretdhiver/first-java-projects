<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmPrInfoVO" %> 
<%@ page import="com.spring.ge.vo.EmInfoVO" %>   
<%@ page import="java.util.ArrayList" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
<%
/* 사용자 정보 */
Object obj1 = session.getAttribute("eminfo");
EmInfoVO emvo = null;
emvo = new EmInfoVO();
emvo = (EmInfoVO)obj1;

Object obj = request.getAttribute("emList");
ArrayList<EmPrInfoVO> list = null;
list = (ArrayList<EmPrInfoVO>)obj;
int listSize = list.size();
System.out.println("(log) listSize >>> : " + listSize);

//EmPrInfoVO epvo = new EmPrInfoVO();


/* 페이징을 위한 부분 */
// 한페이지에 보여질 게시물의 수 
int pageSize = 10;
// 그룹의 크기
int groupSize = 10;
// 현재 페이지
int curPage = 1;
// 전체 게시물의 개수
int totalCount = 0;
//
int pageNo = 0;

Object obj3 = request.getAttribute("curPage");
Object obj4 = request.getAttribute("pageNo");
Object obj5 = request.getAttribute("totalCount");

String curPage1 = obj3.toString();
String pageNo1 = obj4.toString();
String totalCount1 = obj5.toString();

curPage = Integer.parseInt(curPage1);
pageNo = Integer.parseInt(pageNo1);
totalCount = Integer.parseInt(totalCount1);

System.out.println(curPage + " / " + pageNo + " / " + totalCount);
%>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>사원목록</title>
		<link rel="stylesheet" type="text/css" href="/css/emAllSelect.css" />
		<script type="text/javascript" src="/js/clock.js"></script>
		<script type="text/javascript"
				src=https://code.jquery.com/jquery-1.11.0.min.js>
		</script>
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
				
				var emno = emno;
				//alert(emno);
				$("#insert").click(function insert(){
					//alert("회원가입폼 이동");
					$("#insert").attr("action", "/em/emInsert.ge");
					$("#insert").submit();
				});
				$("#searchBt").click(function(){
					//alert("사원 검색으로 이동");
					$("#detailForm").attr("action", "/em/emSearch.ge");
					$("#detailForm").submit();
				});
				
			});	// end of document.ready
			
			function loading(){
				setTimeout($('.mainframe .loader').show(), 1000);
			}
			
			function emDetailFunction(emno){
				//alert(emno);
				$("#emno").val(emno);
				$("#detailForm").attr("action","/em/emDetail.ge").submit();
			}
			
		</script>
		<style type="text/css">
			::placeholder {
			  color: gray;
			}
		</style>
	</head>
	<body>
		<div id="ge_menudiv">
			<div class="topinfo">
				<div class="myname"><%=emvo.getEmname()%> 님 안녕하세요.</div>
				<div class="logout" id="logout">logout</div>
			</div>	<!-- end of div-topinfo -->
		</div>	<!-- end of div-ge_menudiv -->
		<div class="ge_maindiv">
			<div class="topmenu">
				<div class="p">
					<a href="/board/bNList.ge">공지사항</a>｜
					<a href="/board/calendar.ge">일정관리</a>｜
					<a href="/em/emAllSelect.ge">사원관리</a>｜
					<a href="/em/ctAllSelect.ge">근태관리</a>｜
					<a href="#">교육사항</a>｜
					<a href="#">인사평가</a>
				</div>	<!-- end of div-p -->
			</div>	<!-- end of div-topmenu -->
			<div class="topdiv">
			</div>	<!-- end of div-topdiv -->
			<div class="outertable">
				<div id="scroll" style="position:absolute;">
					<div class="ge_sidemenu">
						<div class="ge_myinfo">
							<div class="imgbox">
                    		 	<img class="myimg" src="/em_Image/<%=emvo.getEmpic()%>" />
                     		</div>
                     		<br>
							<div class="itsme"><%=emvo.getEmname()%><br>(<%=emvo.getDeptname() %>/<%=emvo.getJobname() %>)</div>
						</div>	<!-- end of div-ge_myinfo -->
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/em/insertForm.ge">사원등록</a><br>
						</div>
						
						<div class="clock" style="border:1px solid #cccccc; border-radius:1px; background:#D8D8D8; float:left; margin:10px 0 10px 0px; padding:5px 0 5px 0">
						<div align="center" style="font-size:20pt ;color:white; "class="dpTime" id="dpTime"></div>
						</div>	
					</div>	<!-- end of div-ge_sidemenu -->
				</div>	<!-- end of div-scroll -->
				
				<div class="mainframe">
<!--고쳐야돼-->		<form id="detailForm" name="detailForm">
<!--form태그-->			<input type="hidden" name="emno" id="emno">
					<div class="tit"><div class="title">&nbsp;사원목록</div></div>
					
					<div class="formsearch">
				    </div>
					
					<div class="cal_container">
						<div class="keywordSearch">
							<!-- select -->
							<select id="search" name="search">
								<option value="all">선택</option>
								<option value="emname">이름</option>
								<option value="deptname">부서</option>
							</select>
							<input type="text" name="keyword" placeholder="검색어를 입력하세요" style="line-height:18px">
							<input type="button" class="button" value="검색" id="searchBt">
						</div>	<!-- end of div-datesearch -->
					</div>	<!-- end of div-cal_container -->
		                	
					<div class="formsearch">
				    </div>
				    
					<div id="tableContainer">
					<table class="ct_List">
						<colgroup>
							<col width="25%">
							<col width="25%">
							<col width="25%">
							<col width="25%">
						</colgroup>
						<tr>
							<th>이름</th>
							<th>부서</th>
							<th>직위</th>
							<th>사번</th>
						</tr>
<%
if(listSize > 0){
	for(int i=0; i<listSize; i++){
		EmPrInfoVO epvo = list.get(i);
%>						
						<tr>
							<td class="val" align="center">
								<a href="#" onclick="emDetailFunction('<%=epvo.getEmno() %>')"><%=epvo.getEmname() %></a>
							</td>
							<td class="val" align="center"><%=epvo.getDeptname() %></td>
							<td class="val" align="center"><%=epvo.getJobname() %></td>
							<td class="val" align="center"><%=epvo.getEmno() %></td>
						<tr>
<%
		}	// end of for
	}	// end of if(listSize > 0)
%>			
					</table>	<!-- end of table-ctlist -->
						<div class="paging_container" style="position: botton:0px; margin:middle;">
							<jsp:include page="emPaging.jsp" flush="true" >
							<jsp:param name="url" value="/em/emAllSelect.ge"/>
							<jsp:param name="str" value=""/>
							<jsp:param name="pageSize" value="<%=String.valueOf(pageSize) %>"/>
							<jsp:param name="groupSize" value="<%=String.valueOf(groupSize)%>"/>
							<jsp:param name="curPage" value="<%=String.valueOf(curPage) %>"/>
							<jsp:param name="totalCount" value="<%=String.valueOf(totalCount)%>"/>
							</jsp:include>
						</div>	<!-- end of div-paging_container -->
					</div>	<!-- end of div-tableContainer -->
					</form>
				</div>	<!-- end of div-mainframe -->
			</div>	<!-- end of div-outertable -->
			<div class="bottom">
				<div class="bottominfo">
				GE Project ｜ 서울시 서초구 강남대로 459 (서초동, 백암빌딩)<br>
				TEL : 02-1234-5678 / FAX : 02-1234-5678<br>
				Copyright ⓒ GE Project. All Rights Reserved.
				</div>	<!-- end of div-bottominfo -->
			</div>	<!-- end of div-bottom -->
		</div>	<!-- end of div-"ge_maindiv" -->
	</body>
</html>