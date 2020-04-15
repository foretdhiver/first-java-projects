<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>
<%@ page import="java.util.ArrayList" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
		Object obj = session.getAttribute("eminfo");
		EmInfoVO evo = null;
		evo = new EmInfoVO();
		evo = (EmInfoVO)obj;
		
		String emname = evo.getEmname();
		String deptcd = evo.getDeptcd();
		String jobcd = evo.getJobcd();
		String emno = evo.getEmno();
		
		Object obj2 = request.getAttribute("list");
		ArrayList<EmInfoVO> list = (ArrayList<EmInfoVO>)obj2;
		
		EmInfoVO evo2 = new EmInfoVO();
		
	//	int listSize = list.size();
	//	System.out.println("(log)listSize >>> : " + listSize);
		
%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>����������</title>
		<script type="text/javascript"
				src="https://code.jquery.com/jquery-1.11.0.min.js">
		</script>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#ctin").click(function(){
<%
					if(list.size()>=1){
%>
						if(confirm("��� �Ͻðڽ��ϱ�?")){
							$("#ctForm").attr("action", "/em/ctInUpdate.ge");						
							$("#ctForm").submit();
						}else{
							return false;
						}	// end of if(confirm("��� �Ͻðڽ��ϱ�?"))
						
<%						
					}else{
%>
						alert("���� ����� �ʿ��մϴ�.");
<%
					}	// end of if(listSize>=1)
%>						
				});
				$("#ctout").click(function(){
<%
					if(list.size()==0){
%>					
						alert("���� ����� �ʿ��մϴ�.");
<%						
					}	// end of if(listSize==0)
%>
<%
					if(list.size()>=1){
%>					
						if(confirm("��� �Ͻðڽ��ϱ�?")){
							$("#ctForm").attr("action", "/em/ctOutUpdate.ge");						
							$("#ctForm").submit();
						}else{
							return false;
						}	// end of if(confirm("��� �Ͻðڽ��ϱ�?"))
<%						
					}	// end of if(listSize>=1)
%>							
				});
				
			});
		</script>
	</head>
	<body>
<%
		if(list.size() >=0){
%>	
		<a href="/em/myPageInfo.ge">����������</a>
		<form id="ctForm"
		      name="ctForm"
		      method="post"
		      enctype = "application/x-www-form-urlencoded">
		      <input type="hidden" name="emname" id="emname" value="<%=evo.getEmname() %>">
		      <input type="hidden" name="deptcd" id="deptcd" value="<%=evo.getDeptcd() %>">
		      <input type="hidden" name="jobcd" id="jobcd" value="<%=evo.getJobcd() %>">
		      <input type="hidden" name="emno" id="emno" value="<%=evo.getEmno() %>">
		      <table border="1" align="left">
		      	<tr>
		      		<td colspan="2">
		      			
		      		</td>
		      	</tr>
		      	<tr>
		      		<td>���</td>
		      		<td><input type="button" value="���" id="ctin" name="ctin"></td>
		      		<td>
<%
				for(int i=0; i<list.size(); i++){
					evo2 = list.get(i);
%>		      
<%
						if(evo2.getCtincd().equals("51")){
%>
							���
							<%=evo2.getCtintime()%>							
<%
						}
%>		      		
<%
						if(evo2.getCtincd().equals("52")){
%>
							����
							<%=evo2.getCtintime()%>
<%
						}
%>		      		
<%
						if(evo2.getCtincd().equals("53")){
%>
							��������
<%
						}
%>		      		
<%
						if(evo2.getCtincd().equals("58")){
%>
							�����
<%
						}
%>		      		
<%
				}	// end of for
%>		      		
<%
						if(list.size()==0){
%>
							�̵��
<%
						}
%>
		      		</td>
		      	</tr>
		      	<tr>
		      		<td>���</td>
		      		<td><input type="button" value="���" id="ctout" name="ctout"></td>
		      		<td>
<%
				for(int i=0; i<list.size(); i++){
					evo2 = list.get(i);
%>		      		
<%
						if(evo2.getCtoutcd().equals("56")){
%>
							���
							<%=evo2.getCtouttime()%>
<%
						}
%>	
<%
						if(evo2.getCtoutcd().equals("57")){
%>
							����ٹ�
							<%=evo2.getCtouttime()%>
<%
						}
%>	
<%
						if(evo2.getCtoutcd().equals("59")){
%>
							����
							<%=evo2.getCtouttime()%>
<%
						}
%>	
<%
						if(evo2.getCtoutcd().equals("60")){
%>
							���Ĺ���
<%
						}
%>	
<%
						if(evo2.getCtoutcd().equals("58")){
%>
							�����
<%
						}
%>
<%
				}	// end of for
%>		      	
<%
						if(list.size()==0){
%>
							�̵��
<%
						}
%>
		      		</td>
		      	</tr>
		      </table>
		 </form>
<%
		}	// end of if(listSize >0)
%>		 
	</body>
</html>