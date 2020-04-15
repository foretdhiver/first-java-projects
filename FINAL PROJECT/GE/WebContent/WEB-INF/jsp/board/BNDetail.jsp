<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="com.spring.ge.vo.EmInfoVO"%>
<%@ page import="com.spring.ge.vo.BNVO"%>
<%@ page import="com.spring.ge.common.ChangeName"%>
<%
	request.setCharacterEncoding("EUC-KR");
%>
<%
Object obj = session.getAttribute("eminfo");
EmInfoVO evo = null;
evo = new EmInfoVO();
evo = (EmInfoVO)obj;

String jobCdCheck = evo.getJobcd();
System.out.println("jobCdCheck >> " + jobCdCheck);

Object objDetail = request.getAttribute("boardNoticeDetail");
BNVO bnvo = null;
bnvo = new BNVO();
bnvo = (BNVO)objDetail;	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>��</title>
		<link rel="stylesheet" type="text/css" href="/css/common.css" />
		<style type="text/css">
	
		.pagingAlink a{
		color: black; text-decoration: none;
		}
		
		th, td {
	    padding: 3px;
	 	 }
	  table {
	    width: 900px;
	  	}
		
	  
		</style>
		<script type="text/javascript" src="/js/clock.js"></script>
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript">
		$(function(){
			
			$("#admin").hide();
			$("#adminbutton").hide();
			$("#default").show();
			$("#defaultbutton").show();
			
			if(<%=jobCdCheck%> == 01){
				$("#default").hide();
				$("#defaultbutton").hide();
				$("#admin").show();
				$("#adminbutton").show();				
			}
			
			$('.mainframe .loader').hide();
			setInterval("dpTime()",0);
			
			var check1 = "<%=evo.getEmno() %>";
			var check2 = "<%=bnvo.getEmno() %>";
			$("#UpDelbutton").hide();
			
			if(check1 == check2){
				
				$("#UpDelbutton").show();
			}
				
			
			/* ÷������ �̹��� �����ֱ� ���� �Ӽ� �߰� */
			var fileImage = '<%=bnvo.getBnfilepath() %>';
			console.log("fileImage >> " + fileImage);
			if(fileImage != null){
				$("#fileImage").attr({
					src 	: "/uploadStorage/"+fileImage,
					width 	: "300px",
					height	: "250px"
				});
			}
			
			/* �Խñ� ���� */
			$("#BNInsert").click(function(){
				loading();
				location.href="/board/bNWrite.ge";			
			});			
			/* �Խñ� �����ϱ� */
			$("#BNUForm").click(function(){
				loading();
				$("#BNDetail").attr("action","/board/bNUForm.ge").submit();
				
			});
			
			/* �Խñ� �����ϱ� */
			$("#BNDelete").click(function(){				
				var confirmText = confirm("�����Ͻðڽ��ϱ�?");
				if(confirmText == true){
					loading();
					$("#BNDetail").attr("action","/board/bNDelete.ge").submit();
				}				
			});
			
			/* ������� ���ư��� */
			$("#BNList").click(function(){		
				loading();
				$("#BNDetail").attr("action","/board/bNList.ge").submit();
								
			});
			
			$('#logout').click(function(){
				if(confirm('�α׾ƿ� �Ͻðڽ��ϱ�?')){
					loading();
					location.href='/ea/geLogOut.ge';
				}else{
				}
				
			});
				
			
		});
		
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
		<div id="ge_menudiv">
			<div class="topinfo">
				<div class="myname"><%=evo.getEmname()%> �� �ȳ��ϼ���.</div>
				<div class="logout" id="logout">logout</div>
			</div><!-- <div class="topinfo"> -->
		</div><!-- <div id="ge_menudiv"> -->
		<div class="ge_maindiv">
			<div class="topmenu" id="default">
				<div class="p">
					<a href="/ea/gemain.ge">����</a>��
					<a href="/ea/eamain.ge">���� ����</a>��
					<a href="#">���ڸ���</a>��
					<a href="#">������</a>��
					<a href="/board/boardMain.ge" style="color:#615F8D">�μ��Խ���</a>��
					<a href="/board/calendar.ge">��������</a>��
					<a href="#">������</a>��
					<a href="/em/myPageInfo.ge">����������</a></div>
				</div><!-- <div class="p"> -->
			</div><!-- <div class="topmenu"> -->
			<div class="topmenu" id="admin">
                  <div class="p">
	                  <a href="/board/bNList.ge">��������</a>��
	                  <a href="/board/calendar.ge">��������</a>��
	                  <a href="/em/emAllSelect.ge">�������</a>��
	                  <a href="/em/ctAllSelect.ge">���°���</a>��
	                  <a href="#">��������</a>��
	                  <a href="#">�λ���</a>
                  </div><!--  <div class="p"> -->
            </div><!-- <div class="topmenu"> -->
			<div class="topdiv">
			</div><!-- <div class="topdiv"> -->
			<div class="outertable">
				<div class="ge_sidemenu">
					<div class="ge_myinfo">
						<div class="imgbox">
						<img class="myimg" src="/em_Image<%=evo.getEmpic()%>" />
						</div>
						<br>
						<div class="itsme"><%=evo.getEmname()%><br>(<%=evo.getDeptname() %>/<%=evo.getJobname() %>)</div>
					</div>
					
					<div id="defaultbutton">
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
							</div><!-- <div id = defaultbutton> -->
							<div id="adminbutton">
							<div class="menubtn"><div class="mbtneff"></div><a href="/board/bNList.ge" >��������</a></div>
							<br><br>
							<br><br>
							<br><br>
							<br><br>
							<br><br>
							<br><br>
							<br><br>
							<br>
							</div><!-- <div id = admintbutton> -->

						<div class="clock" style="border:1px solid #cccccc; border-radius:1px; background:#D8D8D8; float:left; margin:10px 0 10px 0px; padding:5px 0 5px 0">
						<div align="center" style="font-size:20pt ;color:white; "class="dpTime" id="dpTime"></div>
						</div>
						
					</div><!-- <div class="ge_sidemenu"> -->
					<div class="mainframe">
						<div id="noticebar" style="height:50px; width:10px; background:#D8D8D8; margin:20px 0px 0px 20px; float:left"></div>
						<div style="margin:20px; float:left; height:50px; width:400px; margin:20px 0px 0px 0px;"><h1>&nbsp;�󼼺���</h1></div>
						<form id="BNDetailForm" name="BNDetailForm">		
							<input type="hidden" id="deptcd" name="deptcd" 	value=<%=bnvo.getDeptcd() %>>				
						</form>
						<form id="BNDetail" name="BNDetail">		
							<input type="hidden" id="bnno"	 name="bnno"  value=<%=bnvo.getBnno() %>>	
						</form>
						<form name="ge_main" id="ge_main">
						<div style="margin:100px 0px 0px 20px; float:bottom">
						<table width="950px" style="margin:20px 0px 0px 10px; border-collapse: collapse; border:0px;">
							<tr>
								<td>
									<div id="UpDelbutton" style="margin:0px 0px 0px 0px;">
										<table border="0px">
											<tr>
												<td align="right">
												<input type="button" class="button" value="�ۼ����ϱ�" id="BNUForm" name="BNUForm">
												<input type="button" class="button" value="�ۻ����ϱ�" id="BNDelete" name="BNDelete">
												</td>
											</tr>	
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td><!--  style="border-collapse: collapse; -->
								<table id="BNWrite" width="900px" height="430px" style="border-collapse: collapse; border:0px; ">
									<colgroup>
											<col width="10%">
											<col width="20%">
											<col width="15%">
											<col width="15%">
											<col width="15%">
											<col width="15%">
									</colgroup>
									<tbody>
										<tr height="40px" style="background:#D8D8D8; text-align: center;">
											<th style="font-size: 12px;">����</th>
											<th style="font-size: 12px;" colspan="5"><%=bnvo.getBnsubject() %></th>
										</tr>
										<tr height="40px" style="background:#D8D8D8; text-align:center; font-size:12px;">
											<th>�ۼ���</th>
					<%
										String jobcd = "";
										jobcd = bnvo.getJobcd();
										jobcd = ChangeName.jobcdName(jobcd);
					%>
											<th><%=bnvo.getEmname() %> ( <%=jobcd %> )</th>
											<th>�ۼ���</th>
											<th><%=bnvo.getBninsertdate() %></th>
											<th>������</th>
											<th><%=bnvo.getBnupdatedate() %></th>
										</tr>
										<tr>
											<th style="background:#D8D8D8; text-align: center; height:100px;" rowspan="2">����</th>
											<td colspan="5" align="center" style="font-size: 12px; color:#525252;">
											<img src="<%=bnvo.getBnfilepath() %>" />
											</td>
										</tr>
										<tr>
											<td colspan="6" height="200px" style="background:#F9FAF7;" ><%=bnvo.getBncontent() %>
											</td>
										</tr>
										<tr height="10px">
											<td align="left">
												<input type="button" class="button" value="�۾���" id="BNInsert" name="BNInsert">
											</td>
											<td align="right" colspan="5">
												<input type="button" class="button" value="������� ���ư���" id="BNList" name="BNList">
											</td>
										</tr>
									</tbody>
								</table>						
								</td>
							</tr>
						</table><!-- <table width="950px" style="margin:20px 0px 0px 10px; border-collapse: collapse; border:0px;"> -->
						</div><!-- <div  style="margin:100px 0px 0px 20px; float:bottom"> -->
						</form><!-- <form name="ge_main" id="ge_main"> -->
					</div><!-- <div class="mainframe"> -->
					</div><!-- <div class="outertable"> -->
					<div class="bottom">
					<div class="bottominfo">
					GE Project �� ����� ���ʱ� ������� 459 (���ʵ�, ��Ϻ���)<br>
					TEL : 02-1234-5678 / FAX : 02-1234-5678<br>
					Copyright �� GE Project. All Rights Reserved.
					</div><!-- <div class="bottominfo"> -->
					</div><!-- <div class="bottom"> -->
				</div><!-- <div class="ge_maindiv"> -->
	</body>
</html>