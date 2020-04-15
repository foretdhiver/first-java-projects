<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.spring.ge.vo.EaVO" %>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>
<%@ page import="java.util.Iterator"%>
<%  request.setCharacterEncoding("EUC-KR"); %>
<%
	Object eminfo = session.getAttribute("eminfo");
	EmInfoVO emvo = null;
	emvo = new EmInfoVO();
	emvo = (EmInfoVO)eminfo;
	String empic = emvo.getEmpic();
	String emname = emvo.getEmname();
	String emno = emvo.getEmno();
	String deptname = emvo.getDeptname();
	String jobname = emvo.getJobname();
	
	Object obj = request.getAttribute("ea_lineno");
	Object obj1 = request.getAttribute("ea_stepno");
	Object obj2 = request.getAttribute("ea_empno1list");
	Object obj3 = request.getAttribute("ea_empno2list");
	
	
	Object obj4 = request.getAttribute("eadoccd");
	String eadoccd = "";
	eadoccd = obj4.toString();
	String eadoccdname = "";
	if(eadoccd.equals("R")){
		eadoccdname = "ǰ�Ǽ�";
	}else{
		eadoccdname = "�����������";
	}
	
	List<EmInfoVO> ealist = (List<EmInfoVO>)obj2;
	List<EmInfoVO> ealist1 = (List<EmInfoVO>)obj3;
	
	String emname1 = "";
	String emname2 = "";
	String jobname1 = "";
	String jobname2 = "";
	String deptname1 = "";
	String deptname2 = "";
	String emno1 = "";
	String emno2 = "";
	
	if(ealist.size()>0){
		int listCnt = ealist.size();
		System.out.println("listCnt : " + listCnt);
	for(int i=0;i<listCnt;i++)
	{ EmInfoVO emvo1 = (EmInfoVO)ealist.get(i);
		emname1 = emvo1.getEmname();
		jobname1 = emvo1.getJobname();
		deptname1 = emvo1.getDeptname();
		emno1 = emvo1.getEmno();
	}
	}
	if(ealist1.size()>0){
		int listCnt = ealist1.size();
		System.out.println("listCnt : " + listCnt);
	for(int i=0;i<listCnt;i++)
	{ 
		EmInfoVO emvo2 = (EmInfoVO)ealist1.get(i);
		emname2 = emvo2.getEmname();
		jobname2 = emvo2.getJobname();
		deptname2 = emvo2.getDeptname();
		emno2 = emvo2.getEmno();
	}
	}
	
	System.out.println("ea_deptname,1,2 : " + deptname + ", " + deptname1 + "," +deptname2);
	System.out.println("ea_emname,1,2 : " + emname + ", " + emname1 + "," +emname2);
	System.out.println("ea_jobname,1,2 : " + jobname + ", " + jobname1 + "," +jobname2);
	
	System.out.println("obj : " + obj);
	List<EaVO> list = null;
	if(obj!=null){
		String ea_lineno = obj.toString();
		String ea_stepno = obj1.toString();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>eawriteform.jsp</title>
		<link rel="stylesheet" type="text/css" href="/css/docform.css" />
		<script type="text/javascript" src="/js/clock.js"></script>
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> 
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
		<script type="text/javascript">
		$(document).ready(function(){
			$('.mainframe .loader').hide(); 
			setInterval("dpTime()",0);
			
			$('#logout').click(function(){
				if(confirm('�α׾ƿ� �Ͻðڽ��ϱ�?')){
					loading();
					location.href='/ea/geLogOut.ge';
				}else{
				}
				
			});
			
			$('#tomain').click(function(){
				loading();
				location.href='/ea/eamain.ge';
			});
			
		});		
		function insertEa(ea_lineno, ea_stepno, ea_empno){
			//alert('insertEa �Լ� ����');
			var ea_lineno = ea_lineno;
			var ea_stepno = ea_stepno;
			var ea_doccd = $('#ea_doccdval').val();
//			alert(ea_doccd);
			$('#ea_doccd').val(ea_doccd);
			if(confirm('������ �ۼ��Ͻðڽ��ϱ�?')){
				loading();
				$("#eatableinsertform").attr("action", "/ea/eainsert.ge");
				$("#eatableinsertform").attr("enctype", "multipart/form-data");
				$("#eatableinsertform").attr("method", "POST");
				$("#eatableinsertform").submit();
			}else{
				// �ϰ͵�����
			}
		}

		function eaback(){
			loading();
			history.go(-1);
		}
		
		function loading(){
			setTimeout($('.mainframe .loader').show(), 1000);
		}
		
		function newEa(){
			loading();
			// alert('newEa �Լ�');
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
			</form>
				<div class="topmenu">
				<div class="p">
					<a href="/ea/gemain.ge">����</a>��
					<a href="/ea/eamain.ge" style="color:#615F8D" onclick="loading();">����</a>
					<a href="/ea_/eamain.ge" style="color:#615F8D" onclick="loading();">����</a>��
					<a href="#">���ڸ���</a>��
					<a href="#">������</a>��
					<a href="/board/boardMain.ge"  onclick="loading();">�μ��Խ���</a>��
					<a href="/board/calendar.ge"  onclick="loading();">��������</a>��
					<a href="#" >������</a>��
					<a href="/em/myPageInfo.ge"  onclick="loading();">����������</a></div>
				</div>
				<div class="topdiv">
				</div>
				<div class="outertable">
					<div id="scroll" style="position:absolute;">
					<div class="ge_sidemenu">
						<div class="ge_myinfo">
							<div class="imgbox">
							<img class="myimg" src="/em_Image<%=empic%>" />
							</div>
							<br>
							<div class="itsme"><%=emvo.getEmname()%><br>(<%=emvo.getDeptname() %>/<%=emvo.getJobname() %>)</div>
							<br>
						</div>
						<div class="menubtn"><div class="mbtneff"></div><a href="#" onclick="newEa();" style="color:#615F8D">�� ����</a></div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamyapplist.ge" onclick="loading();">�����û����</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamylistall.ge" onclick="loading();">��ü</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamylistpg.ge" onclick="loading();">����</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamylistsb.ge" onclick="loading();">���</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamylistfn.ge" onclick="loading();">�Ϸ�</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamylistrt.ge" onclick="loading();">�ݷ�</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eadoclib.ge" onclick="loading();">����ڷ��</a><br>
						</div>
						
						<div class="clock" style="border:1px solid #cccccc; border-radius:1px; background:#D8D8D8; float:left; margin:10px 0 10px 0px; padding:5px 0 5px 0">
						<div align="center" style="font-size:20pt ;color:white; "class="dpTime" id="dpTime"></div>
						</div>	
					</div>
					</div>
					<div class="mainframe">
						
					<form id="eatableinsertform" name="eatableinsertform">
						<input type="hidden" value="<%=ea_lineno%>" id="ea_lineno" name="ea_lineno" >
						<input type="hidden" value="<%=ea_stepno%>" id="ea_stepno" name="ea_stepno" >
						<input type="hidden" value="<%=emno%>"  id="ea_empno"  name="ea_empno" >
						<input type="hidden" value="<%=emno1%>" id="ea_empno1" name="ea_empno1" >
						<input type="hidden" value="<%=emno2%>" id="ea_empno2" name="ea_empno2" >
						<input type="hidden" value="01" id="ea_status" name="ea_status" >
						<input type="hidden" value="<%=eadoccd%>" id="ea_doccdval" name="ea_doccdval" >
						<input type="hidden" id="ea_doccd" name="ea_doccd">
	
	
						<div class="fixincenter">
						<div class="tit"><div class="title">&nbsp;�� ����</div></div>
							<div class="docinfo">
							<br>
							���繮���� ��Ŀ� ���� �ۼ����ּ���.
							</div>
						
						<div class="ealinebuttonarray">
							<div class="ealineformback">
								<input type="button" class="button" value="����" onclick="eaback();"/>	
							</div>
							<div class="doccdformbuttonplace1">
								<input type="button" class="button" onclick="insertEa('<%=ea_lineno%>', '<%=ea_stepno%>', '<%=emno%>');" value="���" >
							</div>
						</div>
						<div class="tbborder">
						<table id="outlinetable" class="eawritetable">
							<tr>
								<td>
									<table class="eawritetable1">
										<tr>
											<th colspan="2">���� ���� �ۼ�</th>
										</tr>
										<tr>
											<td>��������</td>
											<td><input type="text" id="ea_subject" name="ea_subject"/></td>
										</tr>
										<tr>
											<td>����</td>
											<td><input type="text" value="<%=eadoccdname %>" disabled></td>
										</tr>
										<tr id="eafileupbutton">
											<td>���繮�� ÷��</td>
											<td><input type="file" name="eafile" id="eafile" ></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table class="eawritetable2">
										<tr><th colspan="4">�������</th></tr>
										<tr>
											<td>����</td>
											<td>�ۼ���</td>
											<td>������</td>
											<td>������</td>
										</tr>
										<tr>
											<td>���</td>
											<td><input type="text" value="<%=emno%>" disabled></td>
											<td><input type="text" value="<%=emno1%>" disabled></td>
											<td><input type="text" value="<%=emno2%>" disabled></td>
										</tr>
										<tr>
											<td>����</td>
											<td><input type="text" value="<%=emname%>" disabled></td>
											<td><input type="text" value="<%=emname1%>" disabled></td>
											<td><input type="text" value="<%=emname2%>" disabled></td>
										</tr>
										<tr>
											<td>�ҼӺμ�</td>
											<td><input type="text" value="<%=deptname %>" disabled></td>
											<td><input type="text" value="<%=deptname1 %>" disabled></td>
											<td><input type="text" value="<%=deptname2 %>" disabled></td>
										</tr>
										<tr>
											<td>����</td>
											<td><input type="text" value="<%=jobname %>" disabled></td>
											<td><input type="text" value="<%=jobname1 %>" disabled></td>
											<td><input type="text" value="<%=jobname2 %>" disabled></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table class="eawritetable3">
										<tr><th>���</th></tr>
										<tr>
											<td><textarea id="ea_memo" name="ea_memo" rows="10" cols="94" placeholder="������ �����ּ���"></textarea></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						</div>
						</div>
				<%
					}
				%>
					</form>
					<div class="loader">
						<img src="/ge/loader.gif">
					</div>	
					</div>  <!-- mainframe -->
					</div>
				
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