<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmPrInfoVO" %>    
<%@ page import="com.spring.ge.vo.EmInfoVO" %>
<%@ page import="com.spring.ge.vo.CommonVO" %>    
<%@ page import="java.util.ArrayList" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
<%
/* ����� ���� */
Object obj1 = session.getAttribute("eminfo");
EmInfoVO emvo = null;
emvo = new EmInfoVO();
emvo = (EmInfoVO)obj1;

Object obj2 = request.getAttribute("sList");
ArrayList<EmPrInfoVO> sList = null;
sList = (ArrayList<EmPrInfoVO>)obj2;
int sListSize = sList.size();
System.out.println("(log) sListSize >>> : " + sListSize);

/* ���������̱� ������ ����¡ ó�� X */
%>	
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>������</title>
		<link rel="stylesheet" type="text/css" href="/css/emDetail.css" />
		<script type="text/javascript" src="/js/clock.js"></script>
		<script type="text/javascript"
				src="https://code.jquery.com/jquery-1.11.0.min.js">
		</script>
		<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
		<script>
		    //�� ���������� ���θ� �ּ� ǥ�� ��Ŀ� ���� ���ɿ� ����, �������� �����͸� �����Ͽ� �ùٸ� �ּҸ� �����ϴ� ����� �����մϴ�.
		    function sample4_execDaumPostcode() {
		        new daum.Postcode({
		            oncomplete: function(data) {
		                // �˾����� �˻���� �׸��� Ŭ�������� ������ �ڵ带 �ۼ��ϴ� �κ�.
		
		                // ���θ� �ּ��� ���� ��Ģ�� ���� �ּҸ� �����Ѵ�.
		                // �������� ������ ���� ���� ��쿣 ����('')���� �����Ƿ�, �̸� �����Ͽ� �б� �Ѵ�.
		                var fullRoadAddr = data.roadAddress; // ���θ� �ּ� ����
		                var extraRoadAddr = ''; // ���θ� ������ �ּ� ����
		
		                // ���������� ���� ��� �߰��Ѵ�. (�������� ����)
		                // �������� ��� ������ ���ڰ� "��/��/��"�� ������.
		                if(data.bname !== '' && /[��|��|��]$/g.test(data.bname)){
		                    extraRoadAddr += data.bname;
		                }
		                // �ǹ����� �ְ�, ���������� ��� �߰��Ѵ�.
		                if(data.buildingName !== '' && data.apartment === 'Y'){
		                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                }
		                // ���θ�, ���� ������ �ּҰ� ���� ���, ��ȣ���� �߰��� ���� ���ڿ��� �����.
		                if(extraRoadAddr !== ''){
		                    extraRoadAddr = ' (' + extraRoadAddr + ')';
		                }
		                // ���θ�, ���� �ּ��� ������ ���� �ش� ������ �ּҸ� �߰��Ѵ�.
		                if(fullRoadAddr !== ''){
		                    fullRoadAddr += extraRoadAddr;
		                }
		
		                // �����ȣ�� �ּ� ������ �ش� �ʵ忡 �ִ´�.
		                document.getElementById('emprpostno').value = data.zonecode; //5�ڸ� �������ȣ ���
		                document.getElementById('empradd').value = fullRoadAddr;
		                document.getElementById('sample4_jibunAddress').value = data.jibunAddress;
		
		                // ����ڰ� '���� ����'�� Ŭ���� ���, ���� �ּҶ�� ǥ�ø� ���ش�.
		                if(data.autoRoadAddress) {
		                    //����Ǵ� ���θ� �ּҿ� ������ �ּҸ� �߰��Ѵ�.
		                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
		                    document.getElementById('guide').innerHTML = '(���� ���θ� �ּ� : ' + expRoadAddr + ')';
		
		                } else if(data.autoJibunAddress) {
		                    var expJibunAddr = data.autoJibunAddress;
		                    document.getElementById('guide').innerHTML = '(���� ���� �ּ� : ' + expJibunAddr + ')';
		
		                } else {
		                    document.getElementById('guide').innerHTML = '';
		                }
		            }
		        }).open();
		    }
		</script>
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
				
				$("#update").click(function(){
					//alert("update �Լ�");
					$("#detailForm").attr("action", "/em/emUpdate.ge");						
					$("#detailForm").submit();
				});
				$("#delete").click(function(){
					//alert("delete �Լ�");
					$("#detailForm").attr("action", "/em/emDelete.ge");						
					$("#detailForm").submit();
				});
				$("#ctInsert").click(function(){
					//alert("ctInsert �Լ�");
					$("#detailForm").attr("action", "/em/ctInsert.ge");						
					$("#detailForm").submit();
				});
			});	// end of document.ready
			
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
			</div>	<!-- end of div-topinfo -->
		</div>	<!-- end of div-ge_menudiv -->
		<div class="ge_maindiv">
			<div class="topmenu">
				<div class="p">
					<a href="/board/bNList.ge">��������</a>��
					<a href="/board/calendar.ge">��������</a>��
					<a href="/em/emAllSelect.ge" style="color:#615F8D">�������</a>��
					<a href="/em/ctAllSelect.ge">���°���</a>��
					<a href="#">��������</a>��
					<a href="#">�λ���</a>
				</div>	<!-- end of div-p -->
			</div>	<!-- end of div-topmenu -->
			<div class="topdiv">
			</div>	<!-- end of div-topdiv -->
			<div class="outertable">
				<div id="scroll" style="position:absolute;">
					<div class="ge_sidemenu">
						<div class="ge_myinfo">
							<div class="imgbox">
                    		 	<img class="myimg" src="/em_Image/<%=emvo.getEmpic()%>" />
                     		</div>
                     		<br>
							<div class="itsme"><%=emvo.getEmname()%><br>(<%=emvo.getDeptname() %>/<%=emvo.getJobname() %>)</div>
						</div>	<!-- end of div-ge_myinfo -->
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/em/insertForm.ge">������</a><br>
						</div>
						
						<div class="clock" style="border:1px solid #cccccc; border-radius:1px; background:#D8D8D8; float:left; margin:10px 0 10px 0px; padding:5px 0 5px 0">
						<div align="center" style="font-size:20pt ;color:white; "class="dpTime" id="dpTime"></div>
						</div>	
					</div>	<!-- end of div-ge_sidemenu -->
				</div>	<!-- end of div-scroll -->
				
				<div class="mainframe">
<%
if(sList.size()>0){
	for(int i=0; i<sList.size(); i++){
		EmPrInfoVO epvo = (EmPrInfoVO)sList.get(i);		
%>						
<!--���ľߵ�-->		<form id="detailForm"
						  name="detailForm"
						  method = "POST"
						  enctype = "multipart/form-data">
<!--form�±�-->			<input type="hidden" name="emno" id="emno" value="<%=epvo.getEmno()%>">
<!--form�±�-->			<input type="hidden" name="emname" id="emname" value="<%=epvo.getEmname()%>">
<!--form�±�-->			<input type="hidden" name="deptcd" id="deptcd" value="<%=epvo.getDeptcd()%>">
<!--form�±�-->			<input type="hidden" name="jobcd" id="jobcd" value="<%=epvo.getJobcd()%>">
						<div class="tit"><div class="title">&nbsp;���������</div></div>
						<div class="formsearch">
					    </div>
						<div id="tableContainer">
							<table class="ct_List">
								<tr>
									<td colspan="4" align="center">
										<center>������</center>
									</td>
								</tr>
								<tr>
									<td rowspan="7" colspan="2" align="center">
										<img src="/em_Image/<%=epvo.getEmpic() %>" width="130" height="170">
									</td>
									<td align="center">���</td>
									<td align="center"><%=epvo.getEmno()%></td>
								<tr>
								<tr>
									<td align="center">�̸�</td>
									<td colspan="2" align="center"><%=epvo.getEmname()%></td>
								</tr>
								<tr>
									<td align="center">�������</td>
									<td colspan="2" align="center">
										<%=epvo.getEmprbirth().substring(0, 4)%>-<%=epvo.getEmprbirth().substring(4, 6)%>-<%=epvo.getEmprbirth().substring(6) %>
									</td>
								</tr>
								<tr>
									<td align="center">�μ�</td>
									<td colspan="2" align="center"><%=epvo.getDeptname() %></td>
								</tr>
								<tr>
									<td align="center">����</td>
									<td colspan="2" align="center"><%=epvo.getJobname() %></td>
								</tr>
								<tr>
									<td align="center">�Ի���</td>
									<td colspan="2" align="center">
										<%=epvo.getEmhiredate().substring(0, 4)%>-<%=epvo.getEmhiredate().substring(4, 6)%>-<%=epvo.getEmhiredate().substring(6)%>
									</td>
								</tr>
								<tr>
									<td align="center">������ȣ</td>
									<td align="center"><%=epvo.getEmexno() %></td>
									<td align="center">�����ȣ</td>
									<td align="center">
										<%=epvo.getEmdino().substring(0, 2)%>-<%=epvo.getEmdino().substring(2, 5)%>-<%=epvo.getEmdino().substring(5)%>
									</td>
								</tr>
								<tr>
									<td colspan="2"  align="center">�ڵ�����ȣ</td>
									<td colspan="2" align="center">
										<%=epvo.getEmhp().substring(0, 3)%>-<%=epvo.getEmhp().substring(3, 7)%>-<%=epvo.getEmhp().substring(7)%>
									</td>
								</tr>
								<tr>
									<td colspan="2"  align="center">�̸����ּ�</td>
									<td colspan="2" align="center"><%=epvo.getEmemail() %></td>
								</tr>
								<tr>
									<td  align="center">�з�</td>
									<td align="center">
<%					
						if(epvo.getEmpreducd().equals("31")){
%>							
							����
<%
						}
						if(epvo.getEmpreducd().equals("32")){
%>
							�ʴ���
<%							
						}
						if(epvo.getEmpreducd().equals("33")){
%>
							����
<%							
						}
						if(epvo.getEmpreducd().equals("34")){
%>
							������
<%							
						}
						if(epvo.getEmpreducd().equals("35")){
%>
							�ʴ���
<%							
						}
%>										
									</td>
									<td  align="center">�����б���</td>
									<td align="center"><%=epvo.getEmpreduname() %></td>
								</tr>
								<tr>
									<td rowspan="4"  align="center">�ּ�</td>
									<td colspan="3" style="align:left; padding:2px 20px;"><%=epvo.getEmprpostno() %></td>
								<tr>
								<tr>
									<td colspan="3" style="alian:left; padding:2px 20px;"><%=epvo.getEmpradd() %></td>
								</tr>
								<tr>
									<td colspan="3" style="alian:left; padding:2px 20px;"><%=epvo.getEmprdetailadd() %></td>
								</tr>
								<tr>
									<td colspan="4" align="center" style="padding:5px 10px 0px 2px;">
										<input type="button" class="button1" value="���µ��" id="ctInsert" name="ctInsert">
										<input type="button" class="button1" value="����" id="update" name="update">
										<input type="button" class="button1" value="���" id="delete" name="delete">
									</td>								
								</tr>
							</table>	<!-- end of table-ctlist -->
						</div>	<!-- end of div-tableContainer -->
					</form>	<!-- end of detailForm -->
<%
	}	// end of for
}	// end of if(sList.size()>0)
%>					
				</div>	<!-- end of div-mainframe -->
			</div>	<!-- end of div-outertable -->
			<div class="bottom">
				<div class="bottominfo">
				GE Project �� ����� ���ʱ� ������� 459 (���ʵ�, ��Ϻ���)<br>
				TEL : 02-1234-5678 / FAX : 02-1234-5678<br>
				Copyright �� GE Project. All Rights Reserved.
				</div>	<!-- end of div-bottominfo -->
			</div>	<!-- end of div-bottom -->
		</div>	<!-- end of div-"ge_maindiv" -->
	</body>
</html>