<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmPrInfoVO" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>���������� ��������</title>
		<script type="text/javascript"
				src="https://code.jquery.com/jquery-1.11.0.min.js">
		</script>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#update").click(function(){
					alert("update �Լ�");
					$("#myForm").attr("action", "/em/pwUpdate.ge");						
					$("#myForm").submit();
				});
			});
		</script>
	</head>
	<body>
		<a href="/em/myPageTr.ge">����������-��������(validation ���ؼ� ���� ������ null���Ϳ�~~)</a>
<%
		Object obj = request.getAttribute("myList");

		ArrayList<EmPrInfoVO> mList = null;
		mList = (ArrayList<EmPrInfoVO>)obj;
		
		System.out.println("(log) mList.size()" + mList.size());
		
		if(mList.size() > 0){
			for(int i=0; i<mList.size(); i++){
				EmPrInfoVO epvo = (EmPrInfoVO)mList.get(i);
%>
		<form name="myForm"
			  id="myForm"
			  method="GET"
			  enctype="application/x-www-form-urlencoded">
			<table border="1" align="center">
				<tr>
					<td colspan="2" align="center">
						<center>���� ����</center>
					</td>
				</tr>
				<tr>
					<td width="200" align="center">���</td>
					<td width="500">
						<%=epvo.getEmno()%>
					</td>
				</tr>
				<tr>
					<td align="center">�̸�</td>
					<td><%=epvo.getEmname()%></td>
				</tr>
				<tr>
					<td align="center">���̵�</td>
					<td><%=epvo.getEmid()%></td>
				</tr>
				<tr>
					<td align="center">��й�ȣ</td>
					<td><input type="text" name="empw" id="empw"></td>
				</tr>
				<tr>
					<td align="center">�μ�</td>
					<td><%=epvo.getDeptname() %></td>
				</tr>
				<tr>
					<td align="center">����</td>
					<td><%=epvo.getJobname()%></td>
				</tr>
				<tr>
					<td align="center">�̸���</td>
					<td><%=epvo.getEmemail() %></td>
				</tr>
				<tr>
					<td align="center">�ڵ�����ȣ</td>
					<td><%=epvo.getEmhp() %></td>
				</tr>
				<tr>
					<td align="center">������ȣ</td>
					<td><%=epvo.getEmexno() %></td>
				</tr>
				<tr>
					<td align="center">�����ȣ</td>
					<td><%=epvo.getEmdino() %></td>
				</tr>
				<tr>
					<td align="center">�������</td>
					<td><%=epvo.getEmprbirth() %></td>
				</tr>
				<tr>
					<td align="center">ȥ�ο���</td>
					<td>
<%					
						if(epvo.getEmprmarital().equals("61")){
%>							
							��ȥ
<%
						}
						if(epvo.getEmprmarital().equals("62")){
%>
							��ȥ
<%							
						}
%>					
					</td>
				</tr>
				<tr>
					<td align="center">�����ȣ</td>
					<td><%=epvo.getEmprpostno() %></td>
				</tr>
				<tr>
					<td align="center">�ּ�</td>
					<td><%=epvo.getEmpradd() %></td>
				</tr>
				<tr>
					<td align="center">���ּ�</td>
					<td><%=epvo.getEmprdetailadd() %></td>
				</tr>
				<tr>
					<td align="center">�з�</td>
					<td>
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
				</tr>
				<tr>
					<td align="center">�����б���</td>
					<td><%=epvo.getEmpreduname() %></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="����" id="update" name="update">
					</td>
				</tr>
			</table>
		</form>
<%
			}	// end of for in if(mList.size() > 0)
		}	// end of if(mList.size() > 0)
%>	
	</body>
</html>