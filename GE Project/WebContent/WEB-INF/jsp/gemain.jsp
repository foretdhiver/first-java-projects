<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.spring.ge.vo.EaVO" %>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>
<%@ page import="com.spring.ge.vo.BDVO"%>
<%@ page import="com.spring.ge.vo.BNVO"%>

<%  request.setCharacterEncoding("EUC-KR"); %> 
<%
Object obj = session.getAttribute("eminfo");
EmInfoVO emvo = null;
emvo = new EmInfoVO();
emvo = (EmInfoVO)obj;
String emno = emvo.getEmno();
String empic = emvo.getEmpic();
// String emno = request.getParameter("emno");
System.out.println("������ ���  : " + emno);

Object obj0 = request.getAttribute("eaListRJ");
Object obj1 = request.getAttribute("eaListALL");
Object obj2 = request.getAttribute("eaListFN");
Object obj3 = request.getAttribute("eaListPG");
Object obj4 = request.getAttribute("eaListAP");
Object stpobj = request.getAttribute("ea_stepno");

//************************************** �߰�

Object objDeptList = request.getAttribute("deptList");
List<BDVO> deptList = null;
deptList = (List<BDVO>)objDeptList;
System.out.println("deptList >> " + deptList.size());

Object objNoticeList = request.getAttribute("noticeList");
List<BNVO> noticeList = null;
noticeList = (List<BNVO>)objNoticeList;
System.out.println("noticeList >> " + noticeList.size());

//************************************** �߰�

String eastepno = stpobj.toString();

List<EaVO> eaListRJ = null;
eaListRJ = (List<EaVO>)obj0;
System.out.println("eaListRJ : " + eaListRJ.size());

List<EaVO> eaListALL = null;
eaListALL = (List<EaVO>)obj1;
System.out.println("eaListALL : " + eaListALL.size());

List<EaVO> eaListFN = null;
eaListFN = (List<EaVO>)obj2;
System.out.println("eaListFN : " + eaListFN.size());

List<EaVO> eaListPG = null;
eaListPG = (List<EaVO>)obj3;
System.out.println("eaListPG : " + eaListPG.size());

List<EaVO> eaListAP = null;
eaListAP = (List<EaVO>)obj4;
System.out.println("eaListAP : " + eaListAP.size());

//*************************************** ��/��� �߰�
Object obj99 = request.getAttribute("ctList");
ArrayList<EmInfoVO> ctList = null;
ctList = (ArrayList<EmInfoVO>)obj99;
System.out.println("ctList.size() >>> : " + ctList.size());

EmInfoVO eVO = null;
eVO = new EmInfoVO();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>::: GE PROJECT :::</title>
	<link rel="stylesheet" type="text/css" href="/css/common.css" />
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/js/clock.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$('.mainframe .loader').hide();
		//	alert($('#ea_stepno').val());
			setInterval("dpTime()",0);
			$('.goDetailApprove').click(function(){
				var r_val = $(this).parents("tr").attr("data-number");
//					alert('r_val : ' + r_val);
					var r_arr = r_val.split(',');
//					alert("r_arr[0] : " + r_arr[0]);
//					alert("r_arr[1] : " + r_arr[1]);
					$("#ea_no").val(r_arr[0]);
					$("#ea_stepno").val(r_arr[1]);
				 
				loading();
				 
				$("#ge_main").attr({
					"method":"post",
					"action":"/ea/eaapprove.ge"
				});
				
				$("#ge_main").submit();
			});
			$('#logout').click(function(){
				if(confirm('�α׾ƿ� �Ͻðڽ��ϱ�?')){
					loading();
					location.href='/ea/geLogOut.ge';
				}else{
				}
				
			});

			
			$('.goDetail').click(function(){
				var r_val = $(this).parents("tr").attr("data-number");
//					alert('r_val : ' + r_val);
				var r_arr = r_val.split(',');
//					alert("r_arr[0] : " + r_arr[0]);
//					alert("r_arr[1] : " + r_arr[1]);
				$("#ea_no").val(r_arr[0]);
				$("#ea_stepno").val(r_arr[1]);
					
				loading();	
				
				$("#ge_main").attr({
					"method":"post",
					"action":"/ea/eadetail.ge"
				});
				
				$("#ge_main").submit();
			});
			
			$('.goDetail1').click(function(){
				var r_val = $(this).parents("tr").attr("data-number");
//					alert('r_val : ' + r_val);
				var r_arr = r_val.split(',');
//					alert("r_arr[0] : " + r_arr[0]);
//					alert("r_arr[1] : " + r_arr[1]);
				$("#ea_no").val(r_arr[0]);
				$("#ea_stepno").val(r_arr[1]);
				
				loading();
				
				$("#ge_main").attr({
					"method":"post",
					"action":"/ea/eaapprove.ge"
				});
				
				$("#ge_main").submit();
			});
			
			$(".goDeptDetail").click(function(){
				
				var bdno = $(this).parents("tr").attr("data-num");
								
				$("#bdno").val(bdno);
				
				$("#ge_main").attr({
					"method" : "POST",
					"action" : "/board/bDDetail.ge"
				});
				$("#ge_main").submit();
				
			});
			
			$(".goNoticeDetail").click(function(){
				
				var bnno = $(this).parents("tr").attr("data-num");
				
				$("#bnno").val(bnno);
				
				$("#ge_main").attr({
					"method" : "POST",
					"action" : "/board/bNDetail.ge"
				});
				$("#ge_main").submit();
				
			});
			
			$("#ctin").click(function(){
<%
if(ctList.size()>=1){
%>
				if(confirm("��� �Ͻðڽ��ϱ�?")){
					$("#ge_main").attr("action", "/em/ctInUpdate.ge");						
					$("#ge_main").submit();
				}else{
					return fasle;
				}	// end of if(confirm("��� �Ͻðڽ��ϱ�?"))
<%
}else{
%>
				alert("���� ����� �ʿ��մϴ�.");
<%
}	// end of if(ctList.size()>=1)
%>				
			});	// end of ctin.click function
			
			$("#ctout").click(function(){
<%
if(ctList.size()>=1){
%>
				if(confirm("��� �Ͻðڽ��ϱ�?")){
					$("#ge_main").attr("action", "/em/ctOutUpdate.ge");						
					$("#ge_main").submit();
				}else{
					return fasle;
				}	// end of if(confirm("��� �Ͻðڽ��ϱ�?"))
<%
}else{
%>
				alert("���� ����� �ʿ��մϴ�.");
<%
}	// end of if(ctList.size()>=1)
%>				
			});	// end of ctin.click function
			
			
		});	// end of document.ready
		
		

		function newEa(){
			// alert('newEa �Լ�');
			loading();
//			window.open("", "pop", "width=550, height=200");
			$("#ge_main").attr("action", '/ea/eadoccdform.ge');
			$("#ge_main").attr("enctype", "multipart/form-data");
			$("#ge_main").attr("method", "POST");
//			$("#ge_main").attr("target", "pop");
			$("#ge_main").submit();
		}
		
		function eaDocLib(){
			loading();
			// alert('eaDocLib �Լ�');
//			window.open("", "pop", "width=450, height=200");
			$("#ge_main").attr("action", '/ea/eadoclib.ge');
			$("#ge_main").attr("enctype", "multipart/form-data");
			$("#ge_main").attr("method", "POST");
//			$("#ge_main").attr("target", "pop");
			$("#ge_main").submit();
		}
		
		function loading(){
			setTimeout($('.mainframe .loader').show(), 1000);
		}

	</script>
	</head>
	<body>
		<div id="ge_menudiv">
			<div class="topinfo">
				<div class="myname"><%=emvo.getEmname()%> �� �ȳ��ϼ���.</div>
				<div class="logout" id="logout">logout</div>
			</div>
		</div>
		<div class="ge_maindiv">
			<form name="ge_main" id="ge_main">
				<input type="hidden" name="ea_stepno" id="ea_stepno">
				<input type="hidden" name="ea_no" id="ea_no" >
				<input type="hidden" name="bdno" id="bdno" />
				<input type="hidden" name="bnno" id="bnno" />
				<input type="hidden" name="emno" id="emno" value="<%=emvo.getEmno() %>" />
				<div class="topmenu">
					<div class="p">
					<a href="/ea/eamain.ge" onclick="loading();">���� ����</a>��
					<a href="#">���ڸ���</a>��
					<a href="#">������</a>��
					<a href="/board/boardMain.ge">�μ��Խ���</a>��
					<a href="/board/calendar.ge">��������</a>��
					<a href="#">������</a>��
					<a href="/em/myPageInfo.ge">����������</a></div>
				</div>
				<div class="topdiv">
				</div>
				<div class="outertable">
					<div class="ge_sidemenu">
						<div class="ge_myinfo">
							<div class="imgbox">
							<img class="myimg" src="/em_Image<%=empic%>" />
							</div>
							<br>
							<div class="itsme"><%=emvo.getEmname()%><br>(<%=emvo.getDeptname() %>/<%=emvo.getJobname() %>)</div>
							<div class="clocktime">
								<table class="clocktimeT">
									<tr align="center">
										<td align="center"><input type="button" class="button1" value="���" id="ctin" name="ctin"></td>
										<td>
<%
	if(ctList.size()>0){
		for(int i=0; i<ctList.size(); i++){
			eVO = ctList.get(i);
			if(eVO.getCtincd().equals("58")){
%>
												�����
<%				
			}else{
%>
												<%=eVO.getCtintime() %>
<%				
			}
%>
<%
		}
	}
%>
<%
	if(ctList.size()==0){
%>
												�̵��
<%
	}	// end of if(ctList.size()==0) 
%>
										</td>
									</tr>	<!-- end of ��� tr -->
									<tr>
										<td><input type="button" class="button1" value="���" id="ctout" name="ctout"></td>
										<td>
<%
	if(ctList.size()>0){
		for(int i=0; i<ctList.size(); i++){
			eVO = ctList.get(i);
			if(eVO.getCtoutcd().equals("58")){
%>
												�����
<%				
			}else{
%>
												<%=eVO.getCtouttime() %>
<%				
			}	// end of if(eVO.getCtincd().equals("58"))
%>
<%
		}
	}
%>												
<%
	if(ctList.size()==0){
%>
												�̵��
<%
	}	// end of if(ctList.size()==0) 
%>
								</table>
							</div>
						</div>
						<div class="menubtn"><div class="mbtneff"></div><a href="#" onclick="newEa();">�� ����</a></div>
						<div class="clock" style="border:1px solid #cccccc; border-radius:1px; background:#D8D8D8; float:left; margin:10px 0 10px 0px; padding:5px 0 5px 0">
						<div align="center" style="font-size:20pt ;color:white; "class="dpTime" id="dpTime"></div>
						</div>		
					</div>
						<div class="mainframe">
							<div class="ge_menu1">
								<div class="maintb_1">
									<div class="getitle"><div class="t">��������</div></div>
									<table class="inner_menu" width="100%">
										<colgroup>
											<col width="300px">
											<col width="130px">
											<col width="70px">
										</colgroup>
										<tr>
											<th class="et">����</th>
											<th class="et">�ۼ���</th>
											<th class="et">�ۼ���</th>
										</tr>
<% 
	if(noticeList.size()>0){
		for(int i=0; i<noticeList.size(); i++){
			BNVO bnvo = null;
			bnvo = new BNVO();
			bnvo = noticeList.get(i);
%>		
										<tr data-num="<%=bnvo.getBnno() %>">
											<td><span class="goNoticeDetail"><%=bnvo.getBnsubject() %></span></td>
											<td><%=bnvo.getBninsertdate() %></td>
											<td><%=bnvo.getEmname() %></td>
										</tr>
<%
			}//end of for
	}else if(noticeList.size()<1){
%>									    <tr>
											<td colspan="3"> no data </td>
										<tr>
<%
	}
%>
									</table>
								</div>
							</div>
							<div class="ge_menu2">
								<div class="maintb_2">
									<div class="getitle"><div class="t">���� ����</div></div>
									<table class="inner_menu" width="100%">
										<colgroup>
											<col width="300px">
											<col width="130px">
											<col width="70px">
										</colgroup>
										<tr>
											<th class="et" >����</th>
											<th class="et" >�ۼ���</th>
											<th class="et" >����</th>
										</tr>
<% 
	if(eaListAP.size()>0){
		int ealistAPCnt = eaListAP.size();
		System.out.println("listCnt : " + ealistAPCnt);
	for(int i=0;i<ealistAPCnt;i++)
	{ EaVO evo = (EaVO)eaListAP.get(i);
%>		
										<tr data-number="<%=evo.getEa_no() %>,<%=evo.getEa_stepno() %>">
											<td><span class="goDetail1"><%=evo.getEa_subject() %></span></td>
											<td><%=evo.getEa_insertdate() %></td>
											<td>
												<%if(evo.getEa_status().equals("01")){
													%>ǰ��<%
												}else if(evo.getEa_status().equals("02")){
													%>����<%
												}else if(evo.getEa_status().equals("03")){
													%>��������<%
												}else if(evo.getEa_status().equals("04")){
													%>�ݷ�<%
												}else if(evo.getEa_status().equals("05")){
													%>���<%
												}else if(evo.getEa_status().equals("06")){
													%>���<%
												}else if(evo.getEa_status().equals("07")){
													%>����<%
												} %>
											</td>
										</tr>
<%
	}
	}else{
%>									    <tr>
											<td colspan="3"> no data </td>
										<tr>
<%
	}
%>
									</table>
								</div>
							</div>
							<div class="ge_menu3">
								<div class="maintb_3">
									<div class="getitle"><div class="t">�μ��� �Խ���</div></div>
									<table class="inner_menu" width="100%">
										<colgroup>
											<col width="300px">
											<col width="130px">
											<col width="70px">
										</colgroup>
										<tr>
											<th class="et" >����</th>
											<th class="et" >��¥</th>
											<th class="et" >�ۼ���</th>
										</tr>
<% 
	if(deptList.size()>0){
		for(int i=0; i<deptList.size(); i++){
			BDVO bdvo = null;
			bdvo = new BDVO();
			bdvo = deptList.get(i);
								
								
%>		
										<tr data-num="<%=bdvo.getBdno() %>">
											<td><span class="goDeptDetail"><%=bdvo.getBdsubject() %></span></td>
											<td><%=bdvo.getBdinsertdate() %></td>
											<td><%=bdvo.getEmname() %></td>
										</tr>
<%
		}//end of for

	}else{
%>									    
										<tr>
											<td colspan="3"> no data </td>
										<tr>
<%
	}
%>
									</table>
								</div>
							</div>
							<div class="ge_menu4">
							<div class="maintb_4">
								<div class="getitle"><div class="t">�� ������</div></div>
								<table class="inner_menu" width="100%">
									<colgroup>
										<col width="300px">
										<col width="130px">
										<col width="70px">
									</colgroup>
									<tr>
										<th class="et" >����</th>
										<th class="et" >�ۼ���</th>
										<th class="et" >����</th>
									</tr>
<% 
	if(eaListALL.size()>0){
		int eaListALLCnt = eaListALL.size();
		System.out.println("listCnt : " + eaListALLCnt);
	for(int i=0;i<eaListALLCnt;i++)
	{ EaVO evo = (EaVO)eaListALL.get(i);
%>		
									<tr data-number="<%=evo.getEa_no() %>,<%=evo.getEa_stepno() %>">
										<td><span class="goDetail"><%=evo.getEa_subject() %></span></td>
										<td><%=evo.getEa_insertdate() %></td>
										<td>
											<%if(evo.getEa_status().equals("01")){
												%>ǰ��<%
											}else if(evo.getEa_status().equals("02")){
												%>����<%
											}else if(evo.getEa_status().equals("03")){
												%>��������<%
											}else if(evo.getEa_status().equals("04")){
												%>�ݷ�<%
											}else if(evo.getEa_status().equals("05")){
												%>���<%
											}else if(evo.getEa_status().equals("06")){
												%>���<%
											}else if(evo.getEa_status().equals("07")){
												%>����<%
											} %>
										</td>
									</tr>
<%
	}
	}else{
%>									    <tr>
										<td colspan="3"> no data </td>
									<tr>
<%
	}
%>
								</table>
							</div>
							</div> <!-- ge4 -->
							<div class="loader">
						     	<img src="/ge/loader.gif">
						    </div>
						</div>  <!-- maintable -->
					</div>
				</form>
				<div class="bottom">
				<div class="bottominfo">
				GE Project �� ����� ���ʱ� ������� 459 (���ʵ�, ��Ϻ���)<br>
				TEL : 02-1234-5678 / FAX : 02-1234-5678<br>
				Copyright �� GE Project. All Rights Reserved.
				</div>
				</div>
			</div>
	</body>
</html>