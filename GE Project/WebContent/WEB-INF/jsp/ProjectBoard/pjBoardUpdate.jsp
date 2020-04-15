<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.ProjectBoardVO" %>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>
<% System.out.println("pj 업데이트하러 jsp 왔다 >>>> "); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	Object obj1 = session.getAttribute("eminfo");
	EmInfoVO evo = (EmInfoVO)obj1;


	Object obj = request.getAttribute("pjUpdateForm");
	ProjectBoardVO pjvo = (ProjectBoardVO)obj;
	System.out.println("(log)pjboardUpdate obj >>> "+obj);
	
	if(obj!=null){
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
 		  	margin:20px 0px 0px 20px; 
 		  	float:left; 
		  }
			
		table {
	        width: 90%;
	        border-top: 1px solid #444444;
	        border-collapse: collapse;
	        margin : auto;
	
	      }
	    th, td {
		        border-bottom: 1px solid #444444;
		        padding:9px;
		        text-align: center;
	      }
	    th {
		    background-color: #d9d9d9;
		  }
		.aa{
			width : 350px;
		
		} 
		input[type=text] {
		  border: none;

		}
		</style>	
			
		<script type="text/javascript">
		
			$(document).ready(function(){
				$('.mainframe .loader').hide();
				setInterval("dpTime()",0);
				
				$("#boardUpdate").click(function(){
					//alert("수정버튼 클릭>>> "); //업데이트하러 가자
					$("#pj_updateForm")
						.attr("action","/pjboard/pjBoardUpdate.ge")
						.submit();
					
				});//$(document).click
				$("#boardList").click(function(){
					//alert(">>>");
					location.href="/pjboard/pjBoardList.ge";
				});
				
				$('#logout').click(function(){
					if(confirm('로그아웃 하시겠습니까?')){
						loading();
						location.href='/ea/geLogOut.ge';
					}else{
					}
				});	
			});//$(document).click(function()
			
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
<!-- =============css================= -->
		<div id="ge_menudiv">
			<div class="topinfo">
				<div class="myname"><%=evo.getEmname()%> 님 안녕하세요.</div>
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
					<a href="/ea/eamain.ge">전자결재</a>｜
					<a href="#">전자메일</a>｜
					<a href="#">조직도</a>｜
					<a href="/board/boardMain.ge" style="color:#615F8D">부서게시판</a>｜
					<a href="/board/calendar.ge">일정관리</a>｜
					<a href="#">쪽지함</a>｜
					<a href="/em/myPageInfo.ge">마이페이지</a>
				</div><!-- <div class="p"> -->
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
						
<!-- ============글수정================= -->
				<div id="boardTit11"></div>
				<div id="boardTit"><h2>글수정</h2></div>
				<form name="pj_updateForm" id="pj_updateForm" method="POST" enctype="multipart/form-data">
					<input type="hidden" name="pbno" value="<%=pjvo.getPbno()%>"/>
					<input type="hidden" name = "pbfilepath" id="pbfilepath" />
					<div id="boardBut" align="right" style=" margin-right : 65px;">
						<input type="button" value="수정" class="but" id="boardUpdate" />
						<input type="button" value="목록" class="but" id="boardList" />
					</div>
					<br>
					<table>
						<colgroup>
							<col width="30%">
				            <col width="35%">           
						</colgroup>
						<thead>
							<tr>
								<th>프로젝트명</th>
								<td colspan="4"><input type="text" name="pjname" id="pjname" value="<%=pjvo.getPjname()%>"
									style="width:800px;" /></td>
							</tr>
							<tr>
								<th>글제목</th>
								<td colspan="4"><input type="text" name="pbsubject" id="pbsubject" value="<%=pjvo.getPbsubject()%>"
									style="width:800px;" /></td>
							</tr>
							<tr>
								<th>부서</th>
								<td><input type="text" name="deptcd" class="aa" id="deptcd" value="<%=pjvo.getDeptcd()%>" /></td>
								<th>직위</th>
	                			<td><input type="text" name="jobcd" class="aa" id="jobcd" value="<%=pjvo.getJobcd()%>" /></td>
							</tr>
							<tr>
								<th>작성자</th>
								<td><input type="text" name="emname" class="aa" id="emname" value="<%=pjvo.getEmname()%>" /></td>
								<th>첨부파일</th>
	                			<td><input type="file" name="pbfilepath" id="pbfilepath"/></td>
							</tr>
						</thead>
						<tbody>
							<tr style="height : 250px;">
								<th>내용</th>
									<td colspan="4" class="content" >
									<input type="text" name="pbcontent" id="pbcontent" value="<%=pjvo.getPbcontent() %>" 
									style="width:800px;height:200px;"/></td>
							</tr>
						
						</tbody>
					</table>
			 
						
					
		<%
				}//if(obj!=null)
		%>		
				</form>
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
			</div><!-- <div class="ge_maindiv"> -->
						</div>	<!-- ge_maindiv -->
		

	</body>
</html>