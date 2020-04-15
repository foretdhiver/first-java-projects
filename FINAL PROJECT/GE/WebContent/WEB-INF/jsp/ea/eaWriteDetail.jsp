<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List" %>
<%@ page import="com.spring.ge.vo.EaVO_" %>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>
<%  request.setCharacterEncoding("EUC-KR"); %> 
<%
Object obj = session.getAttribute("eminfo");
EmInfoVO emvo = null;
emvo = new EmInfoVO();
emvo = (EmInfoVO)obj;
String emno = emvo.getEmno();
String empic = emvo.getEmpic();
System.out.println("������ ���  : " + emno);

Object object = request.getAttribute("eaDetail");
List<EaVO_> eaDetail = (List<EaVO_>) object;
Object str = request.getAttribute("doccd");
Object str1 = request.getAttribute("emno");
String doccd = str.toString();

System.out.println("[log] emvo : " + emvo);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>::: GE PROJECT :::</title>
	<link rel="stylesheet" type="text/css" href="/css/eadetail.css" />
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/js/clock.js"></script>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
	<script type="text/javascript">
		$(document).ready(function(){
			$('.mainframe1 .loader').hide();
		//	alert($('#ea_stepno').val());
			setInterval("dpTime()",0);
		
			$('#logout').click(function(){
				if(confirm('�α׾ƿ� �Ͻðڽ��ϱ�?')){
					loading();
					location.href='/';
				}else{
				}
			});
		});
		
		function newEa(){
			// alert('newEa �Լ�');
			loading();
			window.open("", "pop", "width=550, height=200");
			
			$("#ea_main").attr("target", "pop");
			$("#ea_main").attr("action", '/ea_/gotoInsert.ge');
			$("#ea_main").submit();
		}
		
		function loading(){
			setTimeout($('.mainframe1 .loader').show(), 1000);
		}

	</script>
	</head>
	<body>
		<div id="ge_menudiv">
			<div class="topinfo">
				<div class="myname"><%=emvo.getEmname()%> �� �ȳ��ϼ���.</div>
				<div class="logout" id="logout">logout</div>
			</div> <!-- topinfo -->
		</div> <!-- ge_menudiv -->
		<div class="ge_maindiv">
			<form name="ea_main" id="ea_main" method="post">
				<input type="hidden" name="ea_stepno" id="ea_stepno">
				<input type="hidden" name="ea_no" id="ea_no" >
			</form>
				<div class="topmenu">
					<div class="p">
					<a href="#">����</a>��
					<a href="/ea/eamain.ge" style="color:#615F8D">����</a>
					<a href="/ea_/eamain.ge" style="color:#615F8D">����</a>��
					<a href="#">���ڸ���</a>��
					<a href="#">������</a>��
					<a href="/board/boardMain.ge">�μ��Խ���</a>��
					<a href="/board/calendar.ge">��������</a>��
					<a href="#">������</a>��
					<a href="/em/myPageInfo.ge">����������</a></div>
				</div> <!-- topmenu -->
				<div class="topdiv"></div> <!-- topdiv -->
				<div class="outertable">
					<div class="ge_sidemenu">
						<div class="ge_myinfo1">
							<div class="imgbox">
							<img class="myimg" src="/em_Image<%=empic%>" />
							</div>
							<br>
							<div class="itsme"><%=emvo.getEmname()%><br>(<%=emvo.getDeptname() %>/<%=emvo.getJobname() %>)</div>
						</div> <!-- ge_myinfo -->
						<div class="menubtn"><div class="mbtneff"></div><a href="#" onclick="newEa();">�� ����</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaListAll.ge">��ü �����û��</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaWriteAll.ge">�� ��ü������</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaWritePG.ge">�� ���������</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaWriteRJ.ge">�� �ݷ�������</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaWriteST.ge">�� ��������</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaWriteEnd.ge">�� �Ϸ������</a></div>
						<div class="clock" style="border:1px solid #cccccc; border-radius:1px; background:#D8D8D8; float:left; margin:10px 0 10px 0px; padding:5px 0 5px 0">
						<div align="center" style="font-size:20pt ;color:white; "class="dpTime" id="dpTime"></div>
						</div>		
					</div> <!-- ge_sidemenu -->
						<div class="mainframe1">
							
<%
if(eaDetail != null){
	for(int i=0; i < eaDetail.size(); i++){
		EaVO_ vo = eaDetail.get(i);
%>	
			<form id="eaUpdateForm" name="eaUpdateForm" method="POST">
				<input type="hidden" id="ea_lineno" name="ea_lineno" value="<%=vo.getEa_lineno() %>">
				<input type="hidden" id="ea_stepno" name="ea_stepno" value="<%=vo.getEa_stepno() %>">
				<input type="hidden" id="ea_no" name="ea_no" value="<%=vo.getEa_no() %>">
				<input type="hidden" id="ea_status" name="ea_status" value="<%=vo.getEa_status()%>">
				<input type="hidden" id="ea_sign" name="ea_sign" value="<%=vo.getEa_sign() %>">
				<input type="hidden" id="ea_sign1" name="ea_sign1" value="<%=vo.getEa_sign1() %>">
				<input type="hidden" id="ea_sign2" name="ea_sign2" value="<%=vo.getEa_sign2() %>">
				<input type="hidden" id="ea_empno1" name="ea_empno1" value="<%=vo.getEa_empno1() %>">
				<input type="hidden" id="ea_empno2" name="ea_empno2" value="<%=vo.getEa_empno2() %>">
			</form>
			<form id="eaDetailForm" name="eaDetailForm" method="POST">
			<div class="fixincenter1">
				<div class="tit1"><div class="title"> ���繮�� ����</div></div>
<%
	if("H".equals(doccd)){
		for(int k=0; i < eaDetail.size(); i++){
			EaVO_ evo = eaDetail.get(k);
			
			String docname = evo.getEa_doccd();
			if("H".equals(docname)){
				docname = "�ް���";
			}
			if("P".equals(docname)){
				docname = "������Ʈ ��ȼ�";
			}
			String eacno = evo.getEacno();
			if("53".equals(eacno)){
				eacno = "��������";
			}
			if("54".equals(eacno)){
				eacno = "����";
			}
			if("55".equals(eacno)){
				eacno = "Ư���ް�";
			}
			
			String status = evo.getEa_status();
			if("01".equals(status)){
				status = "ǰ��";
			}
			if("02".equals(status)){
				status = "����";
			}
			if("03".equals(status)){
				status = "��������";
			}
			if("04".equals(status)){
				status = "�ݷ�";
			}
			if("05".equals(status)){
				status = "���";
			}
			if("06".equals(status)){
				status = "���";
			}
			if("07".equals(status)){
				status = "����";
			}
%>
		<table id="outlinetable" class="eawritetable">
			<tr>
				<td>
					<table class="eawritetable1">
								<tr>
									<th>�����ȣ</th>
									<td><%=evo.getEa_no() %></td>
								</tr>
								<tr>
									<th>����</th>
									<td><%=evo.getEa_subject() %></td>
								</tr>
								<tr>
									<th>�����û��</th>
									<td><%=evo.getEa_insertdate() %></td>
								</tr>
								<tr>
									<th> ������� </th>
									<td><%=status%>
								</tr>
								<tr>
									<th>���� ����</th>
									<td><%=docname%></td>
								</tr>
								<tr>
									<th> ���� </th>
									<td><%=eacno%></td>
								</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="eawritetable2">
							<tr><th>�������</th></tr>
								<tr>
									<td>����</td>
									<td>�ۼ���</td>
									<td>������</td>
									<td>������</td>
								</tr>
								<tr>
									<td>����</td>
									<td><%=evo.getEmname() %></td>
									<td><%=evo.getEmname1() %></td>
									<td><%=evo.getEmname2() %></td>
								</tr>
								<tr>
									<td>�ҼӺμ�</td>
									<td><%=evo.getDeptname() %></td>
									<td><%=evo.getDeptname1() %></td>
									<td><%=evo.getDeptname2() %></td>
								</tr>
								<tr>
									<td>����</td>
									<td><%=evo.getJobname() %></td>
									<td><%=evo.getJobname1() %></td>
									<td><%=evo.getJobname2() %></td>
								</tr>
								<tr>
									<td>����</td>
									<td><img src="/em_Image<%=evo.getEa_sign() %>" onError="this.src='/ea_Img/noimg.png'" width="100px" height="100px"/></td>
									<td><img src="/em_Image<%=evo.getEa_sign1() %>" onError="this.src='/ea_Img/noimg.png'" width="100px" height="100px"/></td>
									<td><img src="/em_Image<%=evo.getEa_sign2() %>" onError="this.src='/ea_Img/noimg.png'" width="100px" height="100px"/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th>��������</th>
					</tr>
					<tr>
						<td>
							<table border="1" width="100%">
								<tr>
									<td id="ea_memo"><%=evo.getEa_memo() %></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td id="eabutton" colspan="2" align="center">
							<input type="button" class="button" id="app" name="app" value="����">
							<input type="button" class="button" id="rj" name="rj" value="�ݷ�">
							<input type="button" class="button" id="st" name="st" value="���">
						</td>
					</tr>
				</table>
<%
		}
	}else if("P".equals(doccd)){
		for(int j=0; i < eaDetail.size(); i++){
			EaVO_ evo = eaDetail.get(j);
			String docname = evo.getEa_doccd();
			if("H".equals(docname)){
				docname = "�ް���";
			}
			if("P".equals(docname)){
				docname = "������Ʈ ��ȼ�";
			}
			
			String status = evo.getEa_status();
			if("01".equals(status)){
				status = "ǰ��";
			}
			if("02".equals(status)){
				status = "����";
			}
			if("03".equals(status)){
				status = "��������";
			}
			if("04".equals(status)){
				status = "�ݷ�";
			}
			if("05".equals(status)){
				status = "���";
			}
			if("06".equals(status)){
				status = "���";
			}
			if("07".equals(status)){
				status = "����";
			}

%>
		<table id="outlinetable" class="eawritetable">
			<tr>
				<td>
					<table class="eawritetable1">
								<tr>
									<th colspan="2"> ���� ���� </th>
								</tr>
								<tr>
									<td>�����ȣ</td>
									<td><%=evo.getEa_no() %></td>
								</tr>
								<tr>
									<td>����</td>
									<td><%=evo.getEa_subject() %></td>
								</tr>
								<tr>
									<td>�����û��</td>
									<td><%=evo.getEa_insertdate() %></td>
								</tr>
								<tr>
									<td> ������� </td>
									<td><%=status%>
								</tr>
								<tr>
									<td>���� ����</td>
									<td><%=docname%></td>
								</tr>
								<tr>
									<td> ��ȳ��� </td>
									<td><%=evo.getEadraftcontant() %></td>
								</tr>
							</table>
					<table class="eawritetable2">
						<tr><th colspan="4">�������</th></tr>
									<tr>
										<td>����</td>
										<td>�ۼ���</td>
										<td>������</td>
										<td>������</td>
									</tr>
									<tr>
										<td>����</td>
										<td><%=evo.getEmname() %></td>
										<td><%=evo.getEmname1() %></td>
										<td><%=evo.getEmname2() %></td>
									</tr>
									<tr>
										<td>�ҼӺμ�</td>
										<td><%=evo.getDeptname() %></td>
										<td><%=evo.getDeptname1() %></td>
										<td><%=evo.getDeptname2() %></td>
									</tr>
									<tr>
										<td>����</td>
										<td><%=evo.getJobname() %></td>
										<td><%=evo.getJobname1() %></td>
										<td><%=evo.getJobname2() %></td>
									</tr>
									<tr>
										<td>����</td>
										<td><img src="/em_Image<%=evo.getEa_sign() %>" class="myimg" onError="this.src='/ea_Img/noimg.png'"/></td>
										<td><img src="/em_Image<%=evo.getEa_sign1() %>" class="myimg" onError="this.src='/ea_Img/noimg.png'"/></td>
										<td><img src="/em_Image<%=evo.getEa_sign2() %>" class="myimg" onError="this.src='/ea_Img/noimg.png'"/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table class="eawritetable3">
							<tr><th>��Ÿ</th></tr>
							<tr>
								<td><%=evo.getEa_memo() %></td>
							</tr>
							</table>
						</td>
					</tr>
				</table>
				</div>
			</form>

<%
		}
	}else{

%>
		<table class="eawritetable3">
			<tr>
				<td colspan="2">������ ������ �����ϴ�.</td>
			</tr>
		</table>
<%
	}
	}
}
%>
									<div class="loader">
						     	<img src="/ge/loader.gif">
						    		</div>
							</div> <!-- maintable -->
						</div> <!-- outertable1 -->
				</form>
				<div class="bottom">
				<div class="bottominfo">
				GE Project �� ����� ���ʱ� ������� 459 (���ʵ�, ��Ϻ���)<br>
				TEL : 02-1234-5678 / FAX : 02-1234-5678<br>
				Copyright �� GE Project. All Rights Reserved.
				</div> <!-- bottominfo -->
			</div> <!-- bottom -->
		</div> <!-- ge_maindiv -->
	</body>
</html>