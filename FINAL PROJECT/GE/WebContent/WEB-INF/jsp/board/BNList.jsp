<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmInfoVO"%>
<%@ page import="com.spring.ge.vo.BNVO"%>
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

String jobCdCheck = evo.getJobcd();
System.out.println("jobCdCheck >> " + jobCdCheck);

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
BNVO searchData = null;
searchData = new BNVO();
searchData = (BNVO)obj6;
aa = searchData.getSearch();
bb = searchData.getKeyword();
System.out.println("[BNlist.jsp] search>>> " + aa);
System.out.println("[BNlist.jsp] keyword>>> " + bb);

String curPage1 = obj3.toString();
//String groupSize1 = obj2.toString();
String pageNo1 = obj4.toString();
String totalCount1 = obj5.toString();

//groupSize = Integer.parseInt(groupSize1);
curPage = Integer.parseInt(curPage1);
pageNo = Integer.parseInt(pageNo1);
totalCount = Integer.parseInt(totalCount1);

System.out.println("[BNlist.jsp] curPage : " + curPage);
System.out.println("[BNlist.jsp] groupSize : " + groupSize);
System.out.println("[BNlist.jsp] pageNo : " + pageNo);
System.out.println("[BNlist.jsp] totalCount : " + totalCount);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="/css/common.css" />
		<title>��������</title>
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
			
			setInterval("dpTime1()",0);
			
			
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
			$("#BNInsert").click(function(){
				var check = "<%=evo.getJobcd()%>";
				console.log(check);
				if(check != 01){
					alert("���� �� ������ �������� �ʽ��ϴ�.");
					return;
				}
				loading();
				location.href="/board/bNWrite.ge";	
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
				var bnno = $(this).parents("tr").attr("data-num");
				$("#bnno").val(bnno);
				//�� �������� �̵��ϱ� ���� form �߰�
				$("#ge_main").attr({
					"method" : "POST",
					"action" : "/board/bNDetail.ge"
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
				location.href="/board/bNList.ge";
			});
			
		});
		/*�˻��� �� �������� ������ ���ڵ� �� ó�� �� ����¡�� ���� �������� ó�� �Լ�*/
		function pageResult(){

			$("#searchDetail").attr({
				"method" : "get",
				"action" : "/board/bNList.ge"
			});
			
			$("#searchDetail").submit();				
		}
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
					<div style="margin:20px; float:left; height:50px; width:400px; margin:20px 0px 0px 0px;"><h1>&nbsp;��������</h1></div>
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
					<input type="button" class="button" value="�� ����" id="BNInsert" name="BNInsert">
					<br>
					<br>
				</td>
			</tr>
			<tr style="background:#D8D8D8; text-align:center;">
				<th>�۹�ȣ
				<input type="hidden" id="bnno" name="bnno">
				</th>
				<th>����</th>
				<th>�ۼ���<br>(����)</th>
				<th>�ۼ���</th>					
				<th>��ȸ��</th>
			</tr>
<%
Object objList = request.getAttribute("boardNoticeList");
ArrayList<BNVO> boardNoticeList = null;
	if(objList != null){
		boardNoticeList = (ArrayList<BNVO>)objList;
		int listSize = boardNoticeList.size();
		
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
				BNVO bnvo = new BNVO();
				bnvo = boardNoticeList.get(i);
				int a = boardNoticeList.size();
%>					
		<tbody height="500px">	
			<tr data-num=<%=bnvo.getBnno() %>>
				<td>&nbsp;&nbsp;<%=bnvo.getBnno() %></td>
<%
				if(bnvo.getBnsubject().length() >= 13){
%>
				<td><span class="goDetail"><%=bnvo.getBnsubject().substring(0,12)+"......" %><br></span></td>

<%					
				}else{
%>
				<td><span class="goDetail"><%=bnvo.getBnsubject() %></span><br></td>
<%					
				}
				String jobcd = "";
				jobcd = bnvo.getJobcd();
				jobcd = ChangeName.jobcdName(jobcd);
%>				
				<td style="text-align: center;"><%=bnvo.getEmname() %><br>(<%=jobcd %>)</td>
				<td style="text-align: center;"><%=bnvo.getBninsertdate() %><br></td>
				<td style="text-align: center;"><%=bnvo.getBnvcnt() %><br></td>
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
		</table>
<%						
		}//end of if-else
	}//end of if
%>
		</div><!-- <div style="margin:100px 0px 0px 20px; float:bottom"> �̰� �߰��� -->		
		</form>
					<div id="qq" style="display:table; margin:10px; float:left">
						<!-- �˻���ư -->
						<div id="�˻���ư ����" style="display:table-cell;">
							<form id="searchDetail">
								<table style="padding:0px 0px 0px 16px">
									<tr>
										<td>
											<select id="search" name="search" style="height:26px; width:51px;">
											<option value="bnsubject">����</option>
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
											<jsp:param name="url" value="/board/bNList.ge"/>
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