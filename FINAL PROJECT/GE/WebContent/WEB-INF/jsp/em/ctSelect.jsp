<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>    
<%@ page import="java.util.ArrayList" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>�˾�</title>
		<link rel="stylesheet" type="text/css" href="/css/ctSelect.css" />
		<script type="text/javascript"
				src="https://code.jquery.com/jquery-1.11.0.min.js">
		</script>
		<script type="text/javascript">
		$(document).ready(function(){
			$("#update").click(function(){
				//alert("update �Լ�");
				window.opener.name="ctAllSelect";
				$("#detailForm").attr("action", "/em/ctAdminUpdate.ge");
				$("#detailForm").attr("target", "ctAllSelect")
				$("#detailForm").submit();
				window.open("","_self").close();
			});
		});
		// window.opener.name="�θ�â�̸�";
		// ���� ���� �� .attr action ��Ʈ�ѷ� Ż ��
		// .attr("target", "�θ�â�̸�")
		</script>
		<title>���� �� ��ȸ</title>
	</head>
	<body>
<%
		Object obj = request.getAttribute("ctList");

		ArrayList<EmInfoVO> ctList = null;
		ctList = (ArrayList<EmInfoVO>)obj;
		
		System.out.println("(log) ctList.size() >>> : " + ctList.size());
		
		if(ctList.size() > 0){
			for(int i=0; i<ctList.size(); i++){
				EmInfoVO evo = (EmInfoVO)ctList.get(i);
%>	
		<form name="detailForm"
			  id="detailForm"
			  method="get"
			  enctype="application/x-www-form-urlencoded">
			  <table class="ct_List">
			  	  <tr>
			  	  	  <td colspan="2" align="center">
			  	  	  	  <center>���� �� ��ȸ</center>
			  	  	  </td>
			  	  </tr>
			  	  <tr>
			  	  	  <td width="100" align="center">�ٹ���</td>
			  	  	  <td width="300">
			  	  	  	  <%=evo.getCtupdatedate() %>
			  	  	  </td>
			  	  </tr>
			  	  <tr>
			  	  	  <td align="center">���</td>
			  	  	  <td>
			  	  	  	  <input type="hidden" id="emno" name="emno" value="<%=evo.getEmno() %>">
			  	  	  	 <%=evo.getEmno() %>
			  	  	  </td>
			  	  </tr>  
			  	  <tr>
			  	  	  <td align="center">�̸�</td>
			  	  	  <td>
			  	  	  	  <%=evo.getEmname() %>	
			  	  	  </td>
			  	  </tr>  
			  	  <tr>
			  	  	  <td align="center">�μ�</td>
			  	  	  <td>
			  	  	  	  <%=evo.getDeptname() %>
			  	  	  </td>
			  	  </tr>  
			  	  <tr>
			  	  	  <td align="center">����</td>
			  	  	  <td>
			  	  	  	  <%=evo.getJobname() %>
			  	  	  </td>
			  	  </tr>  
			  	  <tr>
			  	  	  <td align="center">��ٻ���</td>
			  	  	  <td>
<%
									if(evo.getCtincd().equals("51")){
%>
										���
<%
									}	// end of if(evo.getCtincd().equals("51"))
%>								
<%
									if(evo.getCtincd().equals("52")){
%>
										����
<%
									}	// end of if(evo.getCtincd().equals("52"))
%>								
<%
									if(evo.getCtincd().equals("53")){
%>
										��������
<%
									}	// end of if(evo.getCtincd().equals("53"))
%>								
<%
									if(evo.getCtincd().equals("54")){
%>
										����
<%
									}	// end of if(evo.getCtincd().equals("54"))
%>								
<%
									if(evo.getCtincd().equals("55")){
%>
										Ư���ް�
<%
									}	// end of if(evo.getCtincd().equals("55"))
%>								
<%
									if(evo.getCtincd().equals("58")){
%>
										���
<%
									}	// end of if(evo.getCtincd().equals("58"))
%>		
						<select name="ctincd" id="ctincd">
							<option value="<%=evo.getCtincd() %>">��ٻ���</option>
							<option value="51">���</option>
							<option value="51">����</option>
							<option value="51">��������</option>
							<option value="54">����</option>
							<option value="55">Ư���ް�</option>
							<option value="58">���</option>
						</select>	  	  	  
			  	  	  </td>
			  	  </tr>  
			  	  <tr>
			  	  	  <td align="center">��ٻ���</td>
			  	  	  <td>
<%
									if(evo.getCtoutcd().equals("56")){
%>
										���
<%
									}	// end of if(evo.getCtincd().equals("56"))
%>
<%
									if(evo.getCtoutcd().equals("57")){
%>
										����ٹ�
<%
									}	// end of if(evo.getCtincd().equals("56"))
%>
<%
									if(evo.getCtoutcd().equals("59")){
%>
										����
<%
									}	// end of if(evo.getCtincd().equals("59"))
%>
<%
									if(evo.getCtoutcd().equals("60")){
%>
										���Ĺ���
<%
									}	// end of if(evo.getCtincd().equals("60"))
%>
<%
									if(evo.getCtoutcd().equals("54")){
%>
										����
<%
									}	// end of if(evo.getCtincd().equals("54"))
%>								
<%
									if(evo.getCtoutcd().equals("55")){
%>
										Ư���ް�
<%
									}	// end of if(evo.getCtincd().equals("55"))
%>								
<%
									if(evo.getCtoutcd().equals("58")){
%>
										���
<%
									}	// end of if(evo.getCtincd().equals("58"))
%>		
				  	  	  <select name="ctoutcd" id="ctoutcd">
							<option value="<%=evo.getCtoutcd() %>">��ٻ���</option>
							<option value="56">���</option>
							<option value="57">����ٹ�</option>
							<option value="59">����</option>
							<option value="60">���Ĺ���</option>
							<option value="54">����</option>
							<option value="55">Ư���ް�</option>
							<option value="58">���</option>
						</select>
			  	  	  </td>
			  	  </tr>  
			  	   <tr>
			  	  	  <td align="center">���</td>
			  	  	  <td>
			  	  	  	  <textarea rows="2" cols="50" name="ctnote"></textarea>
			  	  	  </td>
			  	  </tr>
			  	  <tr>
			  	  	  <td colspan="2" align="center">
			  	  	  	  <input type="button" class="button1" value="����" id="update" name="ctnote">
			  	  	  </td>
			  	  </tr>
			  </table>
		</form>
<%
			}	// end of for
		}	// end of if
%>			
	</body>
</html>