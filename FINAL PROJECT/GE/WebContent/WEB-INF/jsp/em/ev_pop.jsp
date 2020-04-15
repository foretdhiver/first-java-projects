<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmPrInfoVO" %>    
<%@ page import="java.util.ArrayList" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
<%
		Object obj = request.getAttribute("emList");
		ArrayList<EmPrInfoVO> list = (ArrayList<EmPrInfoVO>)obj;
		
		EmPrInfoVO epvo = new EmPrInfoVO();
		
		int listSize = list.size();
		System.out.println("(log) listSize >>> : " + listSize);
%>	
	    <script type="text/javascript"
					src=https://code.jquery.com/jquery-1.11.0.min.js>;
		</script>
		<script type="text/javascript">
			$(document).ready(function(){
				/* 검색 버튼 클릭 시 처리 이벤트 */
				$("#searchBt").click(function(){
					alert("사원 검색으로 이동");
					$("#e_search").attr("action", "/em/emSearch.ge");
					$("#e_search").submit();
				});	
			});
			function emDetailFunction(a){
				var a = a;
				var arr = a.split(",");
				var emno = arr[0];
				var emname = arr[1];
				var deptcd = arr[2];
				var jobcd = arr[3];
				alert(emno);
				alert(emname);
				alert(jobcd);
				alert(deptcd);
				$("#emno").val(emno);
				$("#emname").val(emname);
				$("#jobcd").val(jobcd);
				$("#deptcd").val(deptcd);
				$(opener.document).find("#emno").val(emno);
				$(opener.document).find("#emname").val(emname);
				$(opener.document).find("#deptcd").val(deptcd);
				$(opener.document).find("#jobcd").val(jobcd);
				window.close;
			}
	    </script>
	</head>
	<body>
<%		
		if(listSize > 0){
%>	
		<!--  검색 기능 -->
		<div id="em_Search">
			<form id="e_search"
				  name="e_search"
				  method="get"
				  enctype="application/x-www-form-urlencoded">
				<table border="1">
					<colgroup>
						<col width="80%"></col>
						<col width="20%"></col>
					</colgroup>
					<tr>
						<td>
							<select id="search" name="search">
								<option value="all">전체</option>
								<option value="emname">이름</option>
								<option value="deptname">부서</option>
							</select>
							<input type="text" name="keyword" id="keyword" value="입력" />
							<input type="button" value="검색" id="searchBt" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 사원 목록 -->		
				<div id="em_List">	
					<form name="detailForm" id="detailForm">
						<input type="hidden" name="emname" id="emname" >
						<input type="hidden" name="deptcd" id="deptcd" >
						<input type="hidden" name="jobcd" id="jobcd">
						<input type="hidden" name="emno" id="emno">
					</form>
					<table border="1">
						<thead>
							<tr>
								<th>이름</th>
								<th>부서</th>
								<th>직위</th>
								<th>사번</th>
							</tr>
						</thead>
						<tbody>
<%
							for(int i=0; i<listSize; i++){
								epvo = list.get(i);
%>							
							<tr>
								<td align="center">
									<a href="#" onclick="emDetailFunction('<%=epvo.getEmno() %>,<%=epvo.getEmname() %>,<%=epvo.getDeptcd() %>,<%=epvo.getJobcd() %>')"><%=epvo.getEmname() %></a>
								</td>	
								<td align="center">
									<%=epvo.getDeptcd() %>
								</td>
								<td align="center">
									<%=epvo.getJobcd() %>
								</td>
								<td align="center">
									<%=epvo.getEmno() %>
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
