<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmInfoVO"%>
<%@ page import="com.spring.ge.vo.BTVO"%>
<%@ page import="com.spring.ge.common.Chaebun"%>
<%@ page import="com.spring.ge.common.ChangeName"%>
<%@ page import="java.util.ArrayList" %>
<%
	request.setCharacterEncoding("EUC-KR");
%>
<%
Object obj = session.getAttribute("eminfo");
EmInfoVO evo = null;
evo = new EmInfoVO();
evo = (EmInfoVO)obj;

/* ����¡�� ���� �κ� */
int pageSize = 10;
int groupSize = 10;
int curPage = 1;
int totalCount = 0;
int pageNo = 0;
String aa = ""; //Search
String bb = ""; //Keyword

//Object obj2 = request.getAttribute("groupSize");
Object obj3 = request.getAttribute("curPage");
Object obj4 = request.getAttribute("pageNo");
Object obj5 = request.getAttribute("totalCount");
Object obj6 = request.getAttribute("serchData");
BTVO searchData = null;
searchData = new BTVO();
searchData = (BTVO)obj6;
aa = searchData.getSearch();
bb = searchData.getKeyword();
System.out.println("BTlist.jsp aa search>>> " + aa);
System.out.println("BTlist.jsp bb keyword>>> " + bb);

String curPage1 = obj3.toString();
//String groupSize1 = obj2.toString();
String pageNo1 = obj4.toString();
String totalCount1 = obj5.toString();

//groupSize = Integer.parseInt(groupSize1);
curPage = Integer.parseInt(curPage1);
pageNo = Integer.parseInt(pageNo1);
totalCount = Integer.parseInt(totalCount1);

System.out.println("(BTlist.jsp)curPage : " + curPage);
System.out.println("(BTList.jsp)groupSize : " + groupSize);
System.out.println("(BTList.jsp)pageNo : " + pageNo);
System.out.println("(BTList.jsp)totalCount : " + totalCount);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="/css/common.css" />
		<title><%=evo.getDeptname() %> �μ��Խ���</title>
	</head>
	<style type="text/css">
	
		.pagingAlink a{
		color: black; text-decoration: none;
		}
	    td{
		border-bottom:1px solid #D8D8D8;
		}
	
	</style>
	<script type="text/javascript" src="/js/clock.js"></script>
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$('.mainframe .loader').hide();
			setInterval("dpTime()",0);
			
			/* �˻��� �ʿ��� ������ Ȯ���ϱ� */
			var data1 = "<%=aa%>";
			var data2= "<%=bb%>";
			console.log(data1);
			console.log(data2);
			
			/* �˻� �� �˻� ���� �˻� �ܾ� ��� */
			if("<%=aa%>" != "null"){
				$("#keyword").val("<%=bb%>");
				$("#search").val("<%=aa%>");
			}
			
			/* �۾��� ��ư Ŭ�� �� ó�� �̺�Ʈ */
			$("#BTInsert").click(function(){
				location.href="/board/bTWrite.ge";	
			});
			
			$('#logout').click(function(){
				if(confirm('�α׾ƿ� �Ͻðڽ��ϱ�?')){
					loading();
					location.href='/ea/geLogOut.ge';
				}else{
				}
				
			});
						
			/* ���� Ŭ���� �� ������ �̵��� ���� ó�� �̺�Ʈ */
			$(".goDetail").click(function(){
				var btno = $(this).parents("tr").attr("data-num");
				$("#btno").val(btno);
				//�� �������� �̵��ϱ� ���� form �߰�
				$("#ge_main").attr({
					"method" : "POST",
					"action" : "/board/bTDetail.ge"
				});
				loading();
				$("#ge_main").submit();
				
			});
			
			/* �˻� ��ư Ŭ�� �� ó�� �̺�Ʈ */
			$("#searchData").click(function(){
				var searchData = $("#keyword").val();
				if(!searchData){
					alert("�˻�� �Է��ϼ���");
					return;
				}
				pageResult();
			});
			
			/* ��ü���� */			
			$("#allList").click(function(){
				loading();
				location.href="/board/bTList.ge";
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
		
		/* �˻��� �� �������� ������ ���ڵ� �� ó�� �� ����¡�� ���� �������� ó�� �Լ� */
		function pageResult(){

			$("#searchDetail").attr({
				"method" : "get",
				"action" : "/board/bTList.ge"
			});
			
			$("#searchDetail").submit();				
		}
		
		function loading(){
			setTimeout($('.mainframe .loader').show(), 1000);
		}
		
	</script>
	<body>
		<div id="ge_menudiv">
			<div class="topinfo">
				<div class="myname"><%=evo.getEmname()%> �� �ȳ��ϼ���.</div>
				<div class="logout" id="logout">logout</div>
			</div>
		</div>
		<div class="ge_maindiv">
			<div class="topmenu">
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
					</div><!-- <div class="ge_sidemenu"> -->
					<div class="mainframe">
					<div id="noticebar" style="height:50px; width:10px; background:#D8D8D8; margin:20px 0px 0px 20px; float:left"></div>
					<div style="margin:20px; float:left; height:50px; width:400px; margin:20px 0px 0px 0px;"><h1>&nbsp;���� �Խ���</h1></div>
					<form name="ge_main" id="ge_main">
						<div  style="margin:100px 0px 0px 20px; float:bottom">
						<table width="950px" style="margin:20px 0px 0px 10px; border-collapse: collapse; border:0px;">
						<colgroup>
							<col width="20%">
							<col width="40%">
							<col width="15%">
							<col width="15%">
							<col width="10%">
						</colgroup>
						<tr>
							<td colspan="5" align="right" >
								<input type="button" class="button" value="�� ����" id="BTInsert" name="BTInsert">
								<br>
								<br>
							</td>
						</tr>
						<tr style="background:#D8D8D8; text-align: center;">
							<th>�۹�ȣ
							<input type="hidden" id="btno" name="btno">
							</th>
							<th>����</th>
							<th>�ۼ���<br>(����)</th>
							<th>�ۼ���</th>					
							<th>��ȸ��</th>
						</tr>
<%
Object objList = request.getAttribute("boardTaskList");
ArrayList<BTVO> boardTaskList = null;
	if(objList != null){
		boardTaskList = (ArrayList<BTVO>)objList;
		int listSize = boardTaskList.size();
		
		if(listSize == 0){
%>
		<tbody class="noData">
			<tr>
				<td colspan="5" align="center">
				<br>
				��ϵ� �Խù��� �������� �ʽ��ϴ�. ù��° �Խñ��� �Է����ּ���!	
				</td>
			</tr>
<%
			for(int i=0; i<9; i++){
%>
			<tr>
				<td colspan="5">
				<br>
				<br>		
				</td>
			</tr>				
<%				
			}//end of for
%>			
			
		</tbody>		
<%		
		}else{
			for(int i=0; i<listSize; i++){
				BTVO btvo = new BTVO();
				btvo = boardTaskList.get(i);
%>					
		<tbody class="notNotice">	
			<tr data-num=<%=btvo.getBtno() %>>
				<td>&nbsp;&nbsp;<%=btvo.getBtno() %></td>	
<%
			if(btvo.getBtinsertdate().equals(Chaebun.ymdFormat2())){
				if(btvo.getBtsubject().length() >= 13 ){
%>
				<td><span class="goDetail"><%=btvo.getBtsubject().substring(0,12)+"......" %> NEW</span></td>

<%					
				}else{
%>
				<td><span class="goDetail"><%=btvo.getBtsubject() %> NEW</span></td>
<%					
				}
					
			}else{
				
				if(btvo.getBtsubject().length() >= 13 ){
%>
				<td><span class="goDetail"><%=btvo.getBtsubject().substring(0,12)+"......" %></span></td>

<%					
				}else{
%>
				<td><span class="goDetail"><%=btvo.getBtsubject() %></span></td>
<%					
				}
			}
				
				String jobcd = "";
				jobcd = btvo.getJobcd();
				jobcd = ChangeName.jobcdName(jobcd);
%>				
				<td style="text-align: center;"><%=btvo.getEmname() %><br>(<%=jobcd %>)</td>
				<td style="text-align: center;"><%=btvo.getBtinsertdate() %><br></td>
				<td style="text-align: center;"><%=btvo.getBtvcnt() %><br></td>
			</tr>
		</tbody>
<%		
			}//end of for
			if(listSize < 10){
				for(int i=0; i<10-listSize; i++){
%>				
					<tbody height="500px">	
						<tr>
							<td><br><br></td>
							<td><br><br></td>
							<td><br><br></td>
							<td><br><br></td>
							<td><br><br></td>
						</tr>
					</tbody>					
<%									
				}//end of for			
			}//end of if
%>
		</table><!-- <table width="950px" style="margin:20px 0px 0px 10px; border-collapse: collapse; border:0px;"> -->
<%						
		}//end of if-else
	}//end of if
%>								
						</div><!-- <div  style="margin:100px 0px 0px 20px; float:bottom"> -->
					</form><!-- <form name="ge_main" id="ge_main">	 -->
					<div id="qq" style="display:table; margin:10px; float:left">
					<!-- �˻���ư -->
					<div id="�˻���ư ����" style="display:table-cell;">
						<form id="searchDetail">
						<table style="padding:0px 0px 0px 16px">
							<tr>
								<td>
									<select id="search" name="search" style="height:26px; width:51px">
									<option value="btsubject">����</option>
									</select>
								</td>
								<td>
									<input type="text" id="keyword" name="keyword" placeholder="�˻�� �Է��ϼ���" style="height:20px;"/>
								</td>
								<td>
									<input type="button" class="button" id="searchData" name="searchData" value="�˻�" style="height:26px; width:43px;" />
								</td>
								<td>
									<input type="button" class="button" id="allList" name="allList" value="��ü����" style="height:26px; width:70px;" />
								</td>
								<td align="right" style="width:600px;">
									<div class="pagingAlink">
									<jsp:include page="paging.jsp" flush="true" >
									<jsp:param name="url" value="/board/bTList.ge"/>
									<jsp:param name="str" value=""/>
									<jsp:param name="search" value="<%=aa%>"/>
									<jsp:param name="keyword" value="<%=bb%>"/>
									<jsp:param name="pageSize" value="<%=String.valueOf(pageSize) %>"/>
									<jsp:param name="groupSize" value="<%=String.valueOf(groupSize)%>"/>
									<jsp:param name="curPage" value="<%=String.valueOf(curPage) %>"/>
									<jsp:param name="totalCount" value="<%=String.valueOf(totalCount)%>"/>
									</jsp:include>
									</div><!-- <div class="pagingAlink"> -->
								</td>
							</tr>
						</table>
						</form><!-- <form id="searchDetail"> -->
						</div><!-- <div id="�˻���ư ����" style="display:table-cell;"> -->
					</div><!-- <div id="qq" style="display:table; margin:10px; float:left"> -->				
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