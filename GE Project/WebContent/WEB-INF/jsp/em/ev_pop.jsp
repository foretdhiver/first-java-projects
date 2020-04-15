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
				/* �˻� ��ư Ŭ�� �� ó�� �̺�Ʈ */
				$("#searchBt").click(function(){
					alert("��� �˻����� �̵�");
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
		<!--  �˻� ��� -->
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
								<option value="all">��ü</option>
								<option value="emname">�̸�</option>
								<option value="deptname">�μ�</option>
							</select>
							<input type="text" name="keyword" id="keyword" value="�Է�" />
							<input type="button" value="�˻�" id="searchBt" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- ��� ��� -->		
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
								<th>�̸�</th>
								<th>�μ�</th>
								<th>����</th>
								<th>���</th>
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
