<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "com.spring.ge.controller.AnBoardController" %>         
<%@ page import = "com.spring.ge.vo.AnBoardVO" %>    
<%@ page import= "com.spring.ge.vo.EmInfoVO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	Object obj1 = session.getAttribute("eminfo");
	EmInfoVO evo = (EmInfoVO)obj1;

	Object obj = request.getAttribute("detail"); //detail로 넘긴 객체를 받아와용
	AnBoardVO bvo = (AnBoardVO)obj;
	
	if(obj!=null){
		//if(aList.size()==0){
			//for(int i=0; i<aList.size(); i++){
				//AnBoardVO bvo = aList.get(i);
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
			#detailForm {
			  	width : 900px; 
				 margin: auto;
				 margin-top : 0px;
				 align : center;
				 border: hidden;
				 border-spacing: 0px;
				 font-size : small;
			  }
			#boardTit{
	 			 margin: auto;
				 margin-top : 40px;
				 margin-left : 40px;
				 align : left;
				 font-size : small;
			  }
			#boardTit11{
			  	height:50px;  
	 		  	width:10px;  
			  	background:#D8D8D8;
	 		  	margin:0px 0px 0px 20px; 
	 		  	float:left; 
			  }
			table {
		        width: 100%;
		        border-top: 1px solid #444444;
		        border-collapse: collapse;
		
		      }
		      th,td {
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
				
			$("#boardUpdate").click(function(){
					//alert("수정하기 >>> ");
					$("#detailForm").attr("action","/anmsBoard/updateForm.ge").submit();
				});//boardList
				
				$("#boardDelete").click(function(){
					//alert("삭제하기 >>> ");
					$("#detailForm").attr("action","/anmsBoard/anBoardDelete.ge").submit();
				});//boardList

				$("#boardList").click(function(){
					//alert("목록으로 >>> ");
					location.href="/anmsBoard/anBoardList.ge";
				});//boardList
				
				$('#logout').click(function(){
					if(confirm('로그아웃 하시겠습니까?')){
						loading();
						location.href='/ea/geLogOut.ge';
					}else{
					}
					
				});
			});//$(function()
		
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
<!-- ==============css================ -->	
	<div id="ge_menudiv">
		<div class="topinfo">
			<div class="myname">사용자이름 님 안녕하세요.</div>
			<div class="logout" id="logout">logout</div>
		</div>
	</div>
	<div class="ge_maindiv">
		<form name="ge_main" id="ge_main">
			<input type="hidden" name="ea_stepno" id="ea_stepno">
			<input type="hidden" name="ea_no" id="ea_no" >
		</form>
		<div class="topmenu">
			<div class="p"><a href="/ea/eamain.ge">전자결재</a>｜
			<a href="#">전자메일</a>｜
			<a href="#">조직도</a>｜
			<a href="#">부서게시판</a>｜
			<a href="#">일정관리</a>｜
			<a href="#">쪽지함</a>｜
			<a href="#">관리자</a></div>
		</div>
		<div class="topdiv">
		</div>
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
						<div class="clock" style="border:1px solid #000000; border-radius:1px; background:#808080; float:left; margin:10px 0 10px 0px; padding:5px 0 5px 0">
						<div align="center" style="font-size:20pt ;color:white; "class="dpTime" id="dpTime"></div>
						</div>
			</div>
			<div class="mainframe">



<!-- ==============css================ -->	
<!-- ==============게시판=============== -->
	<div id="detailan">
	<div id="boardTit11"></div>
	<div id="boardTit"><h2><%=bvo.getAbsubject() %></h2></div>
		<form name="b_data" id="b_data" method="POST">
			<input type="hidden" name="h_abno" value="<%=bvo.getAbno()%>" />
		</form>
			
		<form name="detailForm" id="detailForm" method="POST" align="right" style=" margin-right : 65px;">
				<input type="button" value="목록" class="but" id="boardList" />
				<input type="button" value="수정" class="but" id="boardUpdate" />
				<input type="button" value="삭제" class="but" id="boardDelete" />	
				<input type="hidden" id="abno" name="abno" value="<%=bvo.getAbno()%>"  />
				<input type="hidden" id="abfile" name="abfile" value="<%=bvo.getAbfile()%>"  />
				<br><br>
			<table>
				<colgroup>
					<col width="25%" />
					<col width="25%" />
				</colgroup>
				<thead>
					<tr>
						<th>제목</th>
						<td><%=bvo.getAbsubject() %></td>
						<th>조회수</th>
						<td><%=bvo.getAbcvcnt() %></td>
						
					</tr>
					<tr>
						<th>작성일</th>
						<td><%=bvo.getAbinsertdate() %></td>
						<th>첨부파일</th>
		                <td>              
								<p><%=bvo.getAbfile()%>&nbsp;&nbsp;&nbsp;
								<a href="/pjboard/pjDownload.ge?filename=<%=bvo.getAbfile()%>" style="color:blue;" >다운로드</a></p></td>
					</tr>
				</thead>
				<tbody>
					<tr style="height : 200px;">
						<td colspan="4" class="content"><%=bvo.getAbcontent() %></td>
					</tr>						
				</tbody>
			</table>
		
		</form>
<%
			}//for(int
		//}//else
	//}//if(obj!=null)
%>		
		<jsp:include page="reply.jsp"></jsp:include>		
			<div class="loader">
                 <img src="/ge/loader.gif">
             </div>	
        </div>  <!-- mainframe -->
		</div><!-- outertable -->
		<div class="bottom">
		<div class="bottominfo">
			GE Project ｜ 서울시 서초구 강남대로 459 (서초동, 백암빌딩)<br>
			TEL : 02-1234-5678 / FAX : 02-1234-5678<br>
			Copyright ⓒ GE Project. All Rights Reserved.
		</div>
		</div>
	
	</div><!-- ge_maindiv -->     			

</body>
			
</html>