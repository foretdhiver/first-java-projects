<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmHrVO" %>    
<%@ page import="java.util.ArrayList" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<script type="text/javascript"
				src="https://code.jquery.com/jquery-1.11.0.min.js">
		</script>
		<script type="text/javascript">
		$(document).ready(function(){
			alert("ready");
			$("#update").click(function(){
				alert("update 함수");
				$("#detailForm").attr("action", "/em/evUpdate.ge");						
				$("#detailForm").submit();
			});
		});
		</script>
		<title>평가 상세 조회</title>
	</head>
	<body>
<%
		Object obj = request.getAttribute("myEvList");

		ArrayList<EmHrVO> myEvList = null;
		myEvList = (ArrayList<EmHrVO>)obj;
		
		System.out.println("(log) myEvList.size() >>> : " + myEvList.size());
		
		if(myEvList.size() > 0){
			for(int i=0; i<myEvList.size(); i++){
				EmHrVO ehvo = (EmHrVO)myEvList.get(i);
%>	
		<form name="detailForm"
			  id="detailForm"
			  method="get"
			  enctype="application/x-www-form-urlencoded">
			<table border="1">
				<tr>
					<td colspan="2" align="center">
						<center>평가 조회</center>
					</td>
				</tr>
				<tr>
					<td width="100" align="center">평가번호</td>
					<td width="300">
						<%=ehvo.getEvno() %>
					</td>
				</tr>
				<tr>
					<td align="center">사번</td>
					<td>
						<%=ehvo.getEmno() %>
					</td>
				</tr>
				<tr>
					<td align="center">이름</td>
					<td>
						<%=ehvo.getEmname() %>
					</td>
				</tr>
				<tr>
					<td align="center">부서</td>
					<td>
						<%=ehvo.getDeptname() %>
					</td>
				</tr>
				<tr>
					<td align="center">직위</td>
					<td>
						<%=ehvo.getJobname() %>
					</td>
				</tr>
				<tr>
					<td align="center">평가연도</td>
					<td>
						<%=ehvo.getEvalyear() %>
					</td>
				</tr>
				<tr>
					<td align="center">평가등급</td>
					<td>
						<%=ehvo.getEvalgrade() %>
						<select name="evalgrade" id="evalgrade">
							<option value="<%=ehvo.evalgrade %>">평가등급</option>
							<option value="A">A</option>
							<option value="B">B</option>
							<option value="C">C</option>
							<option value="D">D</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">비고</td>
					<td>
						<textarea rows="8" cols="50" name="evalnote"><%=ehvo.getEvalnote() %></textarea>
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