<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%  request.setCharacterEncoding("EUC-KR"); %>    
<%@ page import="kyj.member.common.FilePath" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kyj.member.vo.KyjMemberVO" %>
<% request.setCharacterEncoding("EUC-KR"); %>
<%
	Object obj = request.getAttribute("aList");
	ArrayList<KyjMemberVO> aList = null;
	
	if (obj !=null){
		aList = (ArrayList<KyjMemberVO>)obj;
		System.out.println("aList.size() >>> : " + aList.size());
		for(int i=0;i<aList.size();i++){
			KyjMemberVO kvo = aList.get(i);
			String kid = kvo.getKid();
			
%>       
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>ȸ�� �α��� �� index ������</title>
		<!-- 
		<script type="text/javascript"
			    src="../js/jquery-1.11.0.min.js">
		</script>
		 -->
		<script type="text/javascript"
				src="https://code.jquery.com/jquery-1.11.0.min.js">
		</script>
		<script type="text/javascript">
			alert('���index ������');
			$(document).ready(function(){
			//	alert("�� ��������ʹ�");
				// update
				$("#S").click(function(){
					alert("ȸ������ �˻� �Լ�");
					$("#kid").attr("value", "<%= kid%>");
					$("#ISUD_TYPE").attr("value", "S");
					$("#indexForm")
					//http://localhost:8088/kyj/MemberControllerServlet
					.attr("action", "/kyj/MemberControllerServlet")
					.submit();
				});
				// logout
				$("#O").click(function(){
					alert("�α׾ƿ��Լ�");
					$("#kid").attr("value", "<%= kid%>");
					$("#ISUD_TYPE").attr("value", "O");
					$("#indexForm")
					//http://localhost:8088/kyj/MemberControllerServlet
					.attr("action", "/kyj/MemberControllerServlet")
					.submit();
				});
				
				$("#B").click(function(){
					alert("�Խ��Ǻ��� �Լ�");
					$("#kid").attr("value", "<%= kid%>");
					$("#ISUD_TYPE").attr("value", "A");
					$("#indexForm")
					//http://localhost:8088/kyj/MemberControllerServlet
					.attr("action", "/kyj/HelloBoardControllerServlet")
					.submit();
				});
			});
		</script>
	</head>
	<body>
		<form name="indexForm" 
		 	  id="indexForm" 
			  method="POST"
			  enctype="multipart/form-data">
		<div>
			<table border="1" align="center">
				<tr>
					<input type="hidden" name="kid" id="kid">
					<td colspan="2" align="right"><%= kid%>�� �ȳ��ϼ���!</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><h3>�̰��� �����̰� ȸ�� �α��� �� �� ���̴� ���� �������Դϴ�</h3></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><h1>���� �̹����� �� �ڸ�</h1></td>
				</tr>
				<tr>
				<td colspan="2" align="center">
					<input type="hidden" name ="ISUD_TYPE" id ="ISUD_TYPE">
					<input type="button" value="�� ����" id="S">
					<input type="button" value="�Խ���" id="B">
		<!-- 		<input type="button" value="�α׾ƿ�" id="O"> -->
				</td>
			</table>
		</div>	
		</form>
	</body>
</html>
<%	
		}
	}
%>