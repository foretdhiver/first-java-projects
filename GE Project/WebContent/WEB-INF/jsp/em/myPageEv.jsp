<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmHrVO" %>    
<%@ page import="java.util.ArrayList" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
<%
		Object obj = request.getAttribute("evList");
		ArrayList<EmHrVO> list = (ArrayList<EmHrVO>)obj;
		
		EmHrVO ehvo = new EmHrVO();
		
	//	int listSize = list.size();
	//	System.out.println("(log) listSize >>> : " + listSize);
%>	
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>�������׸��</title>
		<script type="text/javascript"
				src=https://code.jquery.com/jquery-1.11.0.min.js>;
		</script>
		<script type="text/javascript">
			$(document).ready(function(){
				alert("document.ready �Լ�");
				var trno = evno;
					alert(evno);
			});
				function trDetailFunction(evno){
					$("#evno").val(evno);
					$("#detailForm").attr("action","/em/myPageEvDetail.ge").submit();
				}
		</script>
	</head>
	<body>
			<div id="evContainer">
				<div id="trTit"><h3>�� ���</h3></div>
				
				<!-- �������� ��� -->
				<div id="ev_List">
					<form name="detailForm" id="detailForm">
						<input type="text" name="evno" id="evno">
					</form>
					<table border="1" width="600">
						<thead>
							<tr>
								<th>��ȣ</th>
								<th>�̸�</th>
								<th>���</th>
								<th>�μ�</th>
								<th>����</th>
							</tr>
						</thead>
						<tbody>
<%
						if(obj!=null){
%>						
<%
							for(int i=0; i<list.size(); i++){
								ehvo = list.get(i);
%>
							<tr>
								<td align="center">
									<%=ehvo.getEvno().substring(ehvo.getEvno().length()-3, ehvo.getEvno().length()) %>
								</td>
								<td align="center">
									<a href="#" onclick="trDetailFunction('<%=ehvo.getEvno() %>')"><%=ehvo.getEmname() %></a>
								</td>
								<td align="center"><%=ehvo.getEmno() %></td>
								<td align="center"><%=ehvo.getDeptname() %></td>
								<td align="center"><%=ehvo.getJobname() %></td>
							</tr>
<%
							}	// end of for
						}	// end of if(obj!=null)
%>									
						</tbody>
<%
						if(obj==null){
%>		
							<tr>
								<td colspan="6" align="center">
									���������� �������� �ʽ��ϴ�.
								</td>
							</tr>	
<%						
						}	// end of if(obj==null)
%>													
					</table>
				</div>
			</div>	
	</body>
</html>