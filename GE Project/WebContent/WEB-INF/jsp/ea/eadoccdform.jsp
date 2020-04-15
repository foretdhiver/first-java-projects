<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.spring.ge.vo.EaVO" %>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>
<%@ page import="java.util.Iterator"%>
<%  request.setCharacterEncoding("EUC-KR"); %>
<%
	/* ����� ���� */
	Object obj1 = session.getAttribute("eminfo");
	EmInfoVO emvo = null;
	emvo = new EmInfoVO();
	emvo = (EmInfoVO)obj1;
	String empic = emvo.getEmpic();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>eadoccdform.jsp</title>
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
			
			$('#eadoccdsend').click(function(){
				var eadoccd_chk = $('input[name="eadoccd_chk"]:checked').val();
				$('#ea_doccd').val(eadoccd_chk);
			//	alert(eadoccd_chk);

            	if(!$('#ea_doccd').val()){
            		alert('����� �������ּ���.');
            		return false;
            	}
            	
				if(confirm('�������� �Ѿ�ðڽ��ϱ�?')){
					loading();
					$("#eadoccdform").attr("action", "/ea/ealineform.ge");
					$("#eadoccdform").attr("enctype", "application/x-www-form-urlencoded");
					$("#eadoccdform").attr("method", "POST");
					$("#eadoccdform").submit();
				}else{
					// �ϰ͵�����
				}
			});
		});
		function newEa(){
			loading();
			// alert('newEa �Լ�');
		//	window.open("", "pop", "width=550, height=200");
			$("#ge_main").attr("action", '/ea/eadoccdform.ge');
			$("#ge_main").attr("enctype", "multipart/form-data");
			$("#ge_main").attr("method", "POST");
		//	$("#ge_main").attr("target", "pop");
			$("#ge_main").submit();
		}
		
		function eaDocLib(){
			loading();
			// alert('eaDocLib �Լ�');
	//		window.open("", "pop", "width=450, height=200");
			$("#ge_main").attr("action", '/ea/eadoclib.ge');
			$("#ge_main").attr("enctype", "multipart/form-data");
			$("#ge_main").attr("method", "POST");
	//		$("#ge_main").attr("target", "pop");
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
			</form>
				<div class="topmenu">
				<div class="p">
					<a href="/ea/gemain.ge" onclick="loading();">����</a>��
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
						<div class="menubtn"><div class="mbtneff"></div><a href="#" onclick="newEa();"   style="color:#615F8D">�� ����</a></div>
						
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
					<div class="fitincenter">
					<form name=eadoccdform id="eadoccdform">
						<input type="hidden" value="" id="ea_doccd" name="ea_doccd" >
						<div class="tit"><div class="title">&nbsp;�� ����</div></div>
							<div class="docinfo">
							<br>
							������ �ۼ��� �׸��� ������ �ּ���.
							</div>
						<div class="ealinebuttonarray">
						<div class="doccdformbuttonplace"><input type="button" class="button" id="eadoccdsend" name="eadoccdsend" value="����"></div>
						</div>
						<table class="docform" >
							<colgroup>
								<col width="25%">
								<col width="25%">
							</colgroup>
							<tr>
								<th>�׸� ����</th>
							</tr>
							<tr>
								<td><input type="radio" name="eadoccd_chk" value="R">ǰ�Ǽ�</td>
							</tr>
							<tr>
								<td><input type="radio" name="eadoccd_chk" value="S">�����������</td>
							</tr>
						</table>
					</form>
					</div>
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