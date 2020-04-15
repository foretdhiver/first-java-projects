<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>    
<%@ page import="java.util.ArrayList" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>팝업</title>
		<link rel="stylesheet" type="text/css" href="/css/ctSelect.css" />
		<script type="text/javascript"
				src="https://code.jquery.com/jquery-1.11.0.min.js">
		</script>
		<script type="text/javascript">
		$(document).ready(function(){
			$("#update").click(function(){
				//alert("update 함수");
				window.opener.name="ctAllSelect";
				$("#detailForm").attr("action", "/em/ctAdminUpdate.ge");
				$("#detailForm").attr("target", "ctAllSelect")
				$("#detailForm").submit();
				window.open("","_self").close();
			});
		});
		// window.opener.name="부모창이름";
		// 내가 보낼 폼 .attr action 컨트롤러 탈 곳
		// .attr("target", "부모창이름")
		</script>
		<title>근태 상세 조회</title>
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
			  	  	  	  <center>근태 상세 조회</center>
			  	  	  </td>
			  	  </tr>
			  	  <tr>
			  	  	  <td width="100" align="center">근무일</td>
			  	  	  <td width="300">
			  	  	  	  <%=evo.getCtupdatedate() %>
			  	  	  </td>
			  	  </tr>
			  	  <tr>
			  	  	  <td align="center">사번</td>
			  	  	  <td>
			  	  	  	  <input type="hidden" id="emno" name="emno" value="<%=evo.getEmno() %>">
			  	  	  	 <%=evo.getEmno() %>
			  	  	  </td>
			  	  </tr>  
			  	  <tr>
			  	  	  <td align="center">이름</td>
			  	  	  <td>
			  	  	  	  <%=evo.getEmname() %>	
			  	  	  </td>
			  	  </tr>  
			  	  <tr>
			  	  	  <td align="center">부서</td>
			  	  	  <td>
			  	  	  	  <%=evo.getDeptname() %>
			  	  	  </td>
			  	  </tr>  
			  	  <tr>
			  	  	  <td align="center">직위</td>
			  	  	  <td>
			  	  	  	  <%=evo.getJobname() %>
			  	  	  </td>
			  	  </tr>  
			  	  <tr>
			  	  	  <td align="center">출근상태</td>
			  	  	  <td>
<%
									if(evo.getCtincd().equals("51")){
%>
										출근
<%
									}	// end of if(evo.getCtincd().equals("51"))
%>								
<%
									if(evo.getCtincd().equals("52")){
%>
										지각
<%
									}	// end of if(evo.getCtincd().equals("52"))
%>								
<%
									if(evo.getCtincd().equals("53")){
%>
										오전반차
<%
									}	// end of if(evo.getCtincd().equals("53"))
%>								
<%
									if(evo.getCtincd().equals("54")){
%>
										연차
<%
									}	// end of if(evo.getCtincd().equals("54"))
%>								
<%
									if(evo.getCtincd().equals("55")){
%>
										특별휴가
<%
									}	// end of if(evo.getCtincd().equals("55"))
%>								
<%
									if(evo.getCtincd().equals("58")){
%>
										결근
<%
									}	// end of if(evo.getCtincd().equals("58"))
%>		
						<select name="ctincd" id="ctincd">
							<option value="<%=evo.getCtincd() %>">출근상태</option>
							<option value="51">출근</option>
							<option value="51">지각</option>
							<option value="51">오전반차</option>
							<option value="54">연차</option>
							<option value="55">특별휴가</option>
							<option value="58">결근</option>
						</select>	  	  	  
			  	  	  </td>
			  	  </tr>  
			  	  <tr>
			  	  	  <td align="center">퇴근상태</td>
			  	  	  <td>
<%
									if(evo.getCtoutcd().equals("56")){
%>
										퇴근
<%
									}	// end of if(evo.getCtincd().equals("56"))
%>
<%
									if(evo.getCtoutcd().equals("57")){
%>
										연장근무
<%
									}	// end of if(evo.getCtincd().equals("56"))
%>
<%
									if(evo.getCtoutcd().equals("59")){
%>
										조퇴
<%
									}	// end of if(evo.getCtincd().equals("59"))
%>
<%
									if(evo.getCtoutcd().equals("60")){
%>
										오후반차
<%
									}	// end of if(evo.getCtincd().equals("60"))
%>
<%
									if(evo.getCtoutcd().equals("54")){
%>
										연차
<%
									}	// end of if(evo.getCtincd().equals("54"))
%>								
<%
									if(evo.getCtoutcd().equals("55")){
%>
										특별휴가
<%
									}	// end of if(evo.getCtincd().equals("55"))
%>								
<%
									if(evo.getCtoutcd().equals("58")){
%>
										결근
<%
									}	// end of if(evo.getCtincd().equals("58"))
%>		
				  	  	  <select name="ctoutcd" id="ctoutcd">
							<option value="<%=evo.getCtoutcd() %>">퇴근상태</option>
							<option value="56">퇴근</option>
							<option value="57">연장근무</option>
							<option value="59">조퇴</option>
							<option value="60">오후반차</option>
							<option value="54">연차</option>
							<option value="55">특별휴가</option>
							<option value="58">결근</option>
						</select>
			  	  	  </td>
			  	  </tr>  
			  	   <tr>
			  	  	  <td align="center">비고</td>
			  	  	  <td>
			  	  	  	  <textarea rows="2" cols="50" name="ctnote"></textarea>
			  	  	  </td>
			  	  </tr>
			  	  <tr>
			  	  	  <td colspan="2" align="center">
			  	  	  	  <input type="button" class="button1" value="수정" id="update" name="ctnote">
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