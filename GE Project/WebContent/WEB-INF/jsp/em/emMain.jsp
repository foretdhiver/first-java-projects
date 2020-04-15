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
		<title>메인페이지</title>
		<script type="text/javascript"
				src="https://code.jquery.com/jquery-1.11.0.min.js">
		</script>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#ctin").click(function(){
<%
					if(list.size()>=1){
%>
						if(confirm("출근 하시겠습니까?")){
							$("#ctForm").attr("action", "/em/ctInUpdate.ge");						
							$("#ctForm").submit();
						}else{
							return false;
						}	// end of if(confirm("출근 하시겠습니까?"))
						
<%						
					}else{
%>
						alert("근태 등록이 필요합니다.");
<%
					}	// end of if(listSize>=1)
%>						
				});
				$("#ctout").click(function(){
<%
					if(list.size()==0){
%>					
						alert("근태 등록이 필요합니다.");
<%						
					}	// end of if(listSize==0)
%>
<%
					if(list.size()>=1){
%>					
						if(confirm("퇴근 하시겠습니까?")){
							$("#ctForm").attr("action", "/em/ctOutUpdate.ge");						
							$("#ctForm").submit();
						}else{
							return false;
						}	// end of if(confirm("퇴근 하시겠습니까?"))
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
		<a href="/em/myPageInfo.ge">마이페이지</a>
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
		      		<td>출근</td>
		      		<td><input type="button" value="출근" id="ctin" name="ctin"></td>
		      		<td>
<%
				for(int i=0; i<list.size(); i++){
					evo2 = list.get(i);
%>		      
<%
						if(evo2.getCtincd().equals("51")){
%>
							출근
							<%=evo2.getCtintime()%>							
<%
						}
%>		      		
<%
						if(evo2.getCtincd().equals("52")){
%>
							지각
							<%=evo2.getCtintime()%>
<%
						}
%>		      		
<%
						if(evo2.getCtincd().equals("53")){
%>
							오전반차
<%
						}
%>		      		
<%
						if(evo2.getCtincd().equals("58")){
%>
							미출근
<%
						}
%>		      		
<%
				}	// end of for
%>		      		
<%
						if(list.size()==0){
%>
							미등록
<%
						}
%>
		      		</td>
		      	</tr>
		      	<tr>
		      		<td>퇴근</td>
		      		<td><input type="button" value="퇴근" id="ctout" name="ctout"></td>
		      		<td>
<%
				for(int i=0; i<list.size(); i++){
					evo2 = list.get(i);
%>		      		
<%
						if(evo2.getCtoutcd().equals("56")){
%>
							퇴근
							<%=evo2.getCtouttime()%>
<%
						}
%>	
<%
						if(evo2.getCtoutcd().equals("57")){
%>
							연장근무
							<%=evo2.getCtouttime()%>
<%
						}
%>	
<%
						if(evo2.getCtoutcd().equals("59")){
%>
							조퇴
							<%=evo2.getCtouttime()%>
<%
						}
%>	
<%
						if(evo2.getCtoutcd().equals("60")){
%>
							오후반차
<%
						}
%>	
<%
						if(evo2.getCtoutcd().equals("58")){
%>
							미퇴근
<%
						}
%>
<%
				}	// end of for
%>		      	
<%
						if(list.size()==0){
%>
							미등록
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