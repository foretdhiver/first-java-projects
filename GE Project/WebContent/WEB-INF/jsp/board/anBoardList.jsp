<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>    

<%@ page import = "com.spring.ge.vo.CommonVO" %>
<%@ page import = "com.spring.ge.common.PagingTag" %>
<%@ page import = "com.spring.ge.controller.AnBoardController" %>         
<%@ page import = "com.spring.ge.vo.AnBoardVO" %> 
<%@ page import = "com.spring.ge.vo.EmInfoVO" %>  
<%@ page import = "java.util.ArrayList" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	Object obj = session.getAttribute("eminfo");
	EmInfoVO evo = new EmInfoVO();
	evo = (EmInfoVO)obj;

	ArrayList<AnBoardVO> aList= (ArrayList<AnBoardVO>)request.getAttribute("anBoardList");
	AnBoardVO bvo_= (AnBoardVO)request.getAttribute("data");
	int pageCount = 0;
	
	if(aList.size() >0){
		
		AnBoardVO nvo_ = aList.get(0);
	    String totalCount =aList.get(0).getTotalcount_();
	    int pagelistSize = (int)request.getAttribute("listsize_");
	    double dval = (double)pagelistSize;
	    pageCount = (int)Math.ceil(Integer.parseInt(totalCount)/dval); //pageCount 변수 사용
	    
	    System.out.println("jsp에서 pageCount>>>:"+ pageCount); //1
	    System.out.println("jsp에서 totalCount>>>:"+ totalCount); //10
	    System.out.println("jsp에서 pagelistSize>>>:"+ pagelistSize);
	    System.out.println("jsp에서 totalCount>>>:"+ totalCount);
	}
	
%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>::: GE PROJECT :::</title>
		<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<!-- css -->
		<link rel="stylesheet" type="text/css" href="/../css/common.css" />
		<script type="text/javascript" src="/js/clock.js"></script>
		
		 <style type="text/css">
		  
		  .mainframe {
		  	border : hidden;
			}
			
		  #boardList {
		  	border : hidden;
		    width  : 1028px; 
			height : 500px; 
			margin : auto;
		 	align  : center;
		   	padding: 0;
		   	text-align : center;
		  }
		 #abBoardSearch	{
			 width : 900px; 
			 margin: auto;
			 margin-top : 10px;
			 align : center;
			 border: hidden;
			 border-spacing: 0px;
			 
		  }
		#abBoardList{
		  	 width : 900px; 
			 margin: auto;
			 margin-top : 0px;
			 align : center;
			 border: hidden;
			 border-spacing: 0px;
			 font-size : small;
	 
		  }
		#abBoardTit{
 			margin: auto;
			 margin-top : 40px;
			 margin-left : 40px;
			 align : left;
			 font-size : small;
		  }
		#abBoardTit11{
		  	height:50px;  
 		  	width:10px;  
		  	background:#D8D8D8;
 		  	margin:20px 0px 0px 20px; 
 		  	float:left;
		  }
		
		
		  table {
	        width: 100%;
	        border-top: 1px solid #444444;
	        border-collapse: collapse;
	
	      }
	      th, td {
		        border-bottom: 1px solid #444444;
		        padding:9px;
		        text-align: center;
	      }
	      th {
		    background-color: #d9d9d9;
		  }
	    </style>
	
		<script type="text/javascript">
	
		$(document).ready(function(){
			$('.mainframe .loader').hide();
			setInterval("dpTime()",0);
			
			$(function(){
				
				//검색 후 검색 대상&단어 출력
				if(value="<%= bvo_.getKeyword()%>" !=""){
					$("#keyword").val(value="<%= bvo_.getKeyword() %>");
					$("#search").val(value="<%= bvo_.getSearch() %>");
				}
				
				//한 페이지에 보여줄 레코드 수 조회&유지 설정 
<% 
				if(bvo_.getPagesize_() != "" ){
%>
					$("#PageSize").val("<%=bvo_.getPagesize_()%>");
<%
				}
%>	
				//검색 대상이 변경될 때마다 처리 이벤트
				$("#search").change(function(){
					//alert("검색대상 변경 >>>");
					
					if($("#search").val()=="all"){
						$("#keyword").val("전체데이터 조회합니다.");
					}else if($("#search").val()!="all"){
						$("#keyword").val("");
						$("#keyword").focus();
					}//if-else
						
				});//$("#search").change(function()
				
				//검색 버튼 클릭 시
				$("#searchData").click(function(){
					//alert("searchData>>> ");
					goPage(1);
				});//$("#searchData")
						
				//한 페이지에 보여줄 레코드 수 변경시마다 처리 이벤트
				$("#listsize_").change(function(){
					gopage(1);
				});//pageSize		
						
				//글쓰기 버튼 클릭 시
				$("#writeForm").click(function(){
					//alert("writeForm >>> ");
					$("#boardList").attr("action","/anmsBoard/writeForm.ge");
					$("#boardList").submit();
				});//writeForm
				
				//제목 클릭 시 상세페이지 이동
				$(".goDetail").click(function(){
					//alert("상세페이지로 이동 >>>");
					
					var pbno = $(this).parents("tr").attr("data-num");
					//alert("pbno >> " + pbno);
					$("#abno").val(abno);
					//alert("#pbno >> " +$("#pbno"));
					//alert("번호확인 1" + $("#abno").val());
					$("#abno").val(pbno);
					//alert("번호확인 2" + pbno);
// 					//우선 에러가 나면 에러 페이지를 자세하게 보시는게 좋아요
// 					어디 부분에서 에러가 났는지 확인을 해야하는데
// 					그게 보는게 어려우면 이페이지가 우선 뭘 사용하는건지를 알아야 해요
// 					뭘 사용하는건지 알아야 사용하는게 없어서 에러가 난것인지,
// 					사용하는 데이터가 안 들어오고 다른 데이터가 들어와서 에러가 난 것인지,
// 					아니면 사용하는 데이터는 잘 들어왔는데, 다른 부분에 오류가 있어서 에러가 난 것인지 확인이 가능해요
// 					아까 에러 페이지 보면  reply.jsp하고 밑에  null 익센셥이라고 표시되어 있었어요
// 					그럼 우선 reply.jsp가 어떤 방식으로 돌아가는지 알아야 다음 일을 유추하기가 쉬워요
// 					리플은 글번호를 가지고 해당 글번호에 해당하는 댓글 목록을 불러오는 형태로 되어있어요
// 					글번호가 null이기 때문에 reply.jsp가 오류가 난 것이에요
// 					그럼 글번호가 null일때, 없어서 null 인지 데이터가 잘못들어와서 =null인지 확인을 해야해요
// 					//에러 페이지 보면 null익셉션 있었어요
					
					
					//상세 페이지로 이동하기 위해 form 추가
					$("#detailForm").attr({"method":"POST","action":"/anmsBoard/anBoardDetail.ge"});
					$("#detailForm").submit();
				});//goDetail
				
				//페이지 버튼 클릭시 함수
				$(".pageNobut").click(function(){
					////alert("페이지버튼 클릭");
					var pageNo = $(this).val();
					//alert(pageno);
			         $("#pageno_").val(pageNo);
			         ////alert(pageNo);
			         $("#boardList").attr("action","/anmsBoard/anBoardList.ge");
			         ////alert("1");
			         $("#boardList").submit();
			         ////alert("2");
					
				});//$(".pageNobut")
				
				//검색,페이징처리 실질 함수
				function goPage(page){
					////alert(">>>");
					if($("#search").val=="all"){
						$("keyword").val("");
					}//if($("#search"
					////alert(">>dddd>");	
					$("#page").val(page);
					$("#boardList").attr({"method":"GET","action":"/anmsBoard/anBoardList.ge"});
					$("#boardList").submit();
				}//function goPage
			});//function()	
			
			$('#logout').click(function(){
				if(confirm('로그아웃 하시겠습니까?')){
					loading();
					location.href='/ea/geLogOut.ge';
				}else{
				}
				
			});
		});//$(document).ready
		
		//접근권한
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
		<!-- css -->
		<div id="ge_menudiv">
			<div class="topinfo">
				<div class="myname"><%=evo.getEmname() %> 님 안녕하세요.</div>
				<div class="logout" id="logout">logout</div>
			</div>
		</div>
		<div class="ge_maindiv">
			<form name="ge_main" id="ge_main">
				<input type="hidden" name="ea_stepno" id="ea_stepno"> 
				<input type="hidden" name="ea_no" id="ea_no">
			</form>
			<div class="topmenu">
				<div class="p">
					<a href="/ea/gemain.ge">메인</a>｜
					<a href="/ea/eamain.ge">전자결재</a>｜
					<a href="#">전자메일</a>｜
					<a href="#">조직도</a>｜
					<a href="/board/boardMain.ge" style="color:#615F8D">부서게시판</a>｜
					<a href="/board/calendar.ge">일정관리</a>｜
					<a href="#">쪽지함</a>｜
					<a href="/em/myPageInfo.ge">마이페이지</a>
				</div><!-- <div class="p"> -->
			</div><!-- topmenu -->
		</div><!-- ge_maindiv -->
			<div class="topdiv"></div>
			<div class="outertable">
				<div class="ge_sidemenu">
					<div class="ge_myinfo">
						<div class="imgbox">
                     		<img class="myimg" src="/em_Image<%=evo.getEmpic()%>" />
                     	</div>
                     <br>
						<div class="itsme">
							<%=evo.getEmname() %><br>(<%=evo.getDeptname() %>/<%=evo.getJobname() %>)
						</div>
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
				</div>
				<div class="mainframe">
				
<!--================== 메인내용이 들어갈위치 ============================ -->
				<!-- 디테일폼 -->
				<form name="detailForm" id="detailForm">
					<input type="hidden" name="abno" id="abno">
				</form>
				<div id="abBoardTit11"></div>
				<div id="abBoardTit"><h2>익명 게시판</h2></div>
				<!-- 버튼 -->
				<div id="boardBut" align="right" style=" margin-right : 65px;">
					<input type="button" id="writeForm" value="글쓰기">	
				</div>
				<br>
				
				<!-- 글목록 시작  -->
				<form id="boardList" >
					
					<!-- 검색기능 시작 -->
					<div id="abBoardSearch">
						<input type="hidden" id="page" name="page" value="1" />
						<table summary="검색">
							<colgroup>
								<col width="70%"></col>
								<col width="30%"></col>
							</colgroup>
							<tr>
								<td id="btd1">
								<label>검색조건</label> 
									<select id="search" name="search">
										<option value="all">전체</option>
										<option value="absubject">제목</option>										
										<option value="abcontent">내용</option>										
									</select> 
									<input type="text" name="keyword" id="keyword" value="검색어를 입력하세요" /> 
									<input type="button" value="검색" id="searchData" /></td>
								<td id="btd2">한 페이지에 
									<select id="listsize_" name="listsize_">
										<option value="10">10줄</option>
										<option value="20">20줄</option>
										<option value="30">30줄</option>
										<option value="50">50줄</option>
										<option value="70">70줄</option>
										<option value="100">100줄</option>
									</select>
								</td>
							</tr>
						</table>
					</div>
					
					<!-- 리스트 -->
					<div id="abBoardList" align="center"> 
						<!-- 		<form name="boardList" id="boardList"> -->
						<table summary="익명게시판 리스트">
							<colgroup>
								<col width="10%" />								
								<col width="35%" />
								<col width="15%" />								
								<col width="10%" />
							</colgroup>
							<thead>
								<tr>
									<th>글번호</th>									
									<th>글제목</th>
									<th>작성일</th>									
									<th>조회수</th>
								</tr>
							</thead>
							<tbody>
<%
							if(aList.size()!=0){
								AnBoardVO bvo = aList.get(0);
%>
							<!-- 데이터 출력 -->
<%
							for(int i = 0; i < aList.size(); i++){
								AnBoardVO bvo2 = aList.get(i);
%>
								<tr align="center" data-num="<%=bvo2.getAbno()%>">
									<td><%=bvo2.getAbno()%></td>
									<td align="left"><span class="goDetail"><%=bvo2.getAbsubject()%></span></td>
									<td><%=bvo2.getAbinsertdate()%></td>
									<td><%=bvo2.getAbcvcnt()%></td>
								</tr>
<%
							}//for
%>
			<!--         </form> -->

<%
					}//if(aListP.size()!=0
						else{
%>
								<tr>
									<td colspan="6" align="center">검색 결과가 존재하지 않습니다.</td>
								</tr>
<%
							}//else
%>
							</tbody>
						</table>
					</div>
					<br><br>
					<div style=" width : 900px; margin: auto;"align="center">
					<input type="hidden" id="pageno_" name="pageno_" value="<%=bvo_.getPageno_()%>">
<%
					for(int i=1 ; i<=pageCount;i++){
%>
					<input type="button" class="pageNobut" id="pageNobut<%=i%>"
						name="pageNobut<%=i%>" value="<%=i%>">

<%            
            		}//for
%>
				</div>
	
				</form>
			</div><!-- mainframe -->
			</div><!-- outertable -->
			</div><!-- ge_maindiv -->
<!--====================== mainframe ================================-->
		<div class="bottom">
		<div class="bottominfo">
			GE Project ｜ 서울시 서초구 강남대로 459 (서초동, 백암빌딩)<br>
			TEL : 02-1234-5678 / FAX : 02-1234-5678<br>
			Copyright ⓒ GE Project. All Rights Reserved.
		</div>
		</div>
	</body>
</html>