<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmHrVO" %>    
<%@ page import="java.util.ArrayList" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
		Object obj = request.getAttribute("mtList");
		ArrayList<EmHrVO> list = (ArrayList<EmHrVO>)obj;
		
		EmHrVO ehvo = new EmHrVO();
		
		int listSize = list.size();
		System.out.println("(log) listSize >>> : " + listSize);
%>	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>마이페이지 - 교육사항</title>
		<script type="text/javascript"
				src=https://code.jquery.com/jquery-1.11.0.min.js>;
		</script>
		<script type="text/javascript">
			$(document).ready(function(){
				alert("document.ready 함수");
				var trno = trno;
					alert(trno);
			});
				function trDetailFunction(trno){
					$("#trno").val(trno);
					$("#detailForm").attr("action","/em/myPageTrDetail.ge").submit();
				}
		</script>
	</head>
	<body>
<%
		if(listSize > 0){
%>		
			<div id="trContainer">
				<div id="trTit"><h3>교육사항목록</h3></div>
				
				<!-- 교육사항 목록 -->
				<div id="tr_List">
					<form name="detailForm" id="detailForm">
						<input type="hidden" name="trno" id="trno" value="<%=ehvo.getTrno() %>">
					</form>
					<table border="1">
						<thead>
							<tr>
								<th>번호</th>
								<th>이름</th>
								<th>사번</th>
								<th>부서</th>
								<th>직위</th>
								<th>교육명</th>
							</tr>
						</thead>
						<tbody>
<%
							for(int i=0; i<listSize; i++){
								ehvo = list.get(i);
%>
							<tr>
								<td align="center">
									<%=ehvo.getTrno().substring(ehvo.getTrno().length()-3, ehvo.getTrno().length()) %>
								</td>
								<td align="center"><%=ehvo.getEmname() %></td>
								<td align="center"><%=ehvo.getEmno() %></td>
								<td align="center"><%=ehvo.getDeptname() %></td>
								<td align="center"><%=ehvo.getJobname() %></td>
								<td align="center">
									<a href="#" onclick="trDetailFunction('<%=ehvo.getTrno() %>')"><%=ehvo.getTrname() %></a>
								</td>
							</tr>
<%
							}				
%>									
						</tbody>
					</table>
				</div>
			</div>	
<%
		}
%>		
	</body>
</html>