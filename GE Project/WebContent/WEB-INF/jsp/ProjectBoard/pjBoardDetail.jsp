<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.ProjectBoardVO" %>  
<%@ page import="com.spring.ge.vo.EmInfoVO" %>  
<%@ page import="com.spring.ge.common.Session" %>
<% System.out.println("(log)pjboardDetail.jsp ���Դ� >>> "); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	Object obj1 = session.getAttribute("eminfo");
	EmInfoVO evo = (EmInfoVO)obj1;

	Object obj = request.getAttribute("pjDetail");
	ProjectBoardVO pjvo = (ProjectBoardVO)obj;

	if(obj != null){
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
					
				$("#boardUpdate").click(function(){
					var pbno = $("#pbno").val();
					$("#detailForm").attr("action","/pjboard/pjUpdateForm.ge").submit();
				});//boardUpdate
				
				$("#boardDelete").click(function(){
					$("#detailForm").attr("action","/pjboard/pjBoardDelete.ge").submit();
				});//boardList
	
				$("#boardList").click(function(){
					location.href="/pjboard/pjBoardList.ge";
				});//boardList
				
				$('#logout').click(function(){
					if(confirm('�α׾ƿ� �Ͻðڽ��ϱ�?')){
						loading();
						location.href='/ea/geLogOut.ge';
					}else{
					}
				});		
				
			});//document
			
			//���ٱ���
			function deptcdCheck1(check){
				var checkDept = check;
				var userDept = "<%=evo.getDeptcd() %>";
				if(checkDept != userDept){
					alert("���� ������ �������� �ʽ��ϴ�.");
					return;
				}
				loading();
				$("#ge_main").attr("action","/board/bDList.ge").submit();	
			}
			function deptcdCheck2(check){
				var checkDept = check;
				var userDept = "<%=evo.getDeptcd() %>";
				if(checkDept != userDept){
					alert("���� ������ �������� �ʽ��ϴ�.");
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
<!--============== css ===============-->
		<div id="ge_menudiv">
			<div class="topinfo">
				<div class="myname"><%=evo.getEmname()%>�� �ȳ��ϼ���.</div>
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
					<a href="/ea/gemain.ge">����</a>��
					<a href="/ea/eamain.ge">���ڰ���</a>��
					<a href="#">���ڸ���</a>��
					<a href="#">������</a>��
					<a href="/board/boardMain.ge" style="color:#615F8D">�μ��Խ���</a>��
					<a href="/board/calendar.ge">��������</a>��
					<a href="#">������</a>��
					<a href="/em/myPageInfo.ge">����������</a>
				</div><!-- <div class="p"> -->
				</div>
			</div><!-- <div class="topmenu"> -->
			<div class="topdiv">
			</div>
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
						<div class="menubtn"><div class="mbtneff"></div><a href="/board/bNList.ge" onclick="">��������</a></div>
						<br>
						<div class="menubtn"><div class="mbtneff"></div><a href="#" onclick="deptcdCheck1(20); return false;">������ �Խ���</a></div>
						<a href="javascript:deptcdCheck2(20);" style="color: black">&nbsp;&nbsp;�������Խ���</a><br><br>
						<div class="menubtn"><div class="mbtneff"></div><a href="#" onclick="deptcdCheck1(30); return false;">���������� �Խ���</a></div>
						<a href="javascript:deptcdCheck2(30);" style="color: black">&nbsp;&nbsp;�������Խ��� </a><br><br>
						<div class="menubtn"><div class="mbtneff"></div><a href="#" onclick="deptcdCheck1(40); return false;">������ �Խ���</a></div>
						<a href="javascript:deptcdCheck2(40);" style="color: black">&nbsp;&nbsp;�������Խ��� </a><br><br>
						<div class="menubtn"><div class="mbtneff"></div>
						<a href="#" onclick="deptcdCheck1(50); return false;">��������� �Խ���</a>
						</div>
						<a href="javascript:deptcdCheck2(50);" style="color: black">&nbsp;&nbsp;�������Խ��� </a><br><br>
						<div class="menubtn"><div class="mbtneff"></div><a href="/pjboard/pjBoardList.ge" onclick="">������Ʈ�Խ���</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/anmsBoard/anBoardList.ge" onclick="">�͸�Խ���</a></div>						
						<div class="clock" style="border:1px solid #cccccc; border-radius:1px; background:#D8D8D8; float:left; margin:10px 0 10px 0px; padding:5px 0 5px 0">
						<div align="center" style="font-size:20pt ;color:white; "class="dpTime" id="dpTime"></div>
						</div>	
					</div><!-- ge_sidemenu -->
				<div class="mainframe">
<!--============ �Խ��� ===================-->							
					<div id="detailpj">
					<div id="boardTit11"></div>
					<div id="boardTit"><h2><%=pjvo.getPbsubject() %></h2></div>
						<form name="b_data" id="b_data" method="POST">
							<input type="hidden" name="pbno1" value="<%=pjvo.getPbno() %>" />
						</form>
					
						<form name="detailForm" id="detailForm" align="right" style=" margin-right : 65px;">
							<input type="button" value="���" class="but" id="boardList" />
							<input type="button" value="����" class="but" id="boardUpdate" />
							<input type="button" value="����" class="but" id="boardDelete" />	
							<input type="hidden" name="pbno" id="pbno" value="<%=pjvo.getPbno()%>" />
							<br><br>
						<table>
							<colgroup>
								<col width="15%">
					            <col width="35%">
					            <col width="15%">
					            <col width="*">
							</colgroup>
							<thead>
								<tr>
									<th>������Ʈ��</th>
									<td><%=pjvo.getPjname() %></td>
									<th>��ȸ��</th>
		                			<td><%=pjvo.getPbcvcnt()%></td>
								</tr>
								<tr>
									<th>�μ�</th>
									<td><%=pjvo.getDeptname() %></td>
									<th>�����ڵ�</th>
		                			<td><%=pjvo.getJobcd()%></td>
								</tr>
								<tr>
									<th>�ۼ���</th>
									<td><%=pjvo.getEmname() %></td>
									<th>÷������</th>
		                			<td>
<%
					if(pjvo.getPbfilepath().equals("null")){
%>
							÷�������� �����ϴ�.</td>
<%
					}else{
%>
								<p><%=pjvo.getPbfilepath()%>&nbsp;&nbsp;&nbsp;
								<a href="/pjboard/pjDownload.ge?filename=<%=pjvo.getPbfilepath()%>" style="color:blue;" >�ٿ�ε�</a></p></td>
<%	
					}
%>
								</tr>
							</thead>
							<tbody>
								<tr style="height : 200px;">
									<td colspan="4" class="content" ><%=pjvo.getPbcontent() %></td>
								</tr>
							
							</tbody>
						</table>
					</form>	
					
						
<%
				}//if
%>			
				<jsp:include page="PbReply.jsp"></jsp:include>
				</div><!-- detailpj -->							
				</div>  <!-- mainframe -->
			</div><!-- outertable -->
			<div class="bottom">
			<div class="bottominfo">
				GE Project �� ����� ���ʱ� ������� 459 (���ʵ�, ��Ϻ���)<br>
				TEL : 02-1234-5678 / FAX : 02-1234-5678<br>
				Copyright �� GE Project. All Rights Reserved.
			</div>
			</div>
		
		</div><!-- ge_maindiv -->
	</body>
</html>