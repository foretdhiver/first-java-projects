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
	String filepath = "";
	if (obj !=null){
		aList = (ArrayList<KyjMemberVO>)obj;
		filepath = FilePath.DOWNLOAD_FILEPATH;
		System.out.println("aList.size() >>> : " + aList.size());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>update.jsp</title>
		<script>
			alert("������Ʈ ����!");
		</script>
		<!-- 
		<script type="text/javascript"
			    src="../js/jquery-1.11.0.min.js">
		</script>
		 -->
		<script type="text/javascript"
				src="https://code.jquery.com/jquery-1.11.0.min.js">
		</script>
		<script type="text/javascript">
		alert('select');
		$(document).ready(function(){
				
			// delete
			$("#D").click(function(){
				alert("delete!");
				$("#ISUD_TYPE").attr("value", "D");
				$("#selectForm")
				//http://localhost:8088/kyj/MemberControllerServlet
				.attr("action", "/kyj/MemberControllerServlet")
				.submit();
			});
			
			// update
			$("#U").click(function(){
				alert("update!");
				$("#ISUD_TYPE").attr("value", "U");
				$("#selectForm")
				//http://localhost:8088/kyj/MemberControllerServlet
				.attr("action", "/kyj/MemberControllerServlet")
				.submit();
			});
		});
		</script>
	</head>
	<body>
		<div align="center">
		<form name="selectForm" 
		 	  id="selectForm" 
			  method="POST"
			  enctype="multipart/form-data">
			  <% 
				for(int i=0;i<aList.size();i++){
					KyjMemberVO kvo = aList.get(i);
			%>
			<table border="1" width="450px"align="center">
				<tr>
					<td colspan="2" align="center">�� ����</td>
				</tr>
				<tr>
					<td width="100" align="center">ȸ����ȣ</td>
					<td width="250">
					&nbsp;<input type="text" id=knumm value="<%=kvo.getKnumm() %>" disabled><br>
					</td>
				</tr>
				<tr>
					<td align="center">���̵�</td>
					<td>&nbsp;<input type="text" id="kid" name="kid" value="<%=kvo.getKid() %>" readonly>
					</td>
				</tr>
				<tr>
					<td align="center">��й�ȣ</td>
					<td>&nbsp;<input type="text" id="kpw" name="kpw" value="<%=kvo.getKpw() %>"><br>
				</tr>
				<tr>
					<td align="center">��й�ȣȮ��</td>
					<td>
						&nbsp;<input type="password" name="kpw_" id="kpw_" size="20">
						<input type="button" id="kPwCheck" value="��й�ȣȮ��">
					</td>
				</tr>
				<tr>
					<td align="center">�̸�</td>
					<td>&nbsp;<input type="text" id="kname" name="kname" value="<%=kvo.getKname() %>"><br>
				</tr>
					<tr>
					<td align="center">�޴�����ȣ</td>
					<td>&nbsp;<input type="text" id="khp" name="khp" value="<%=kvo.getKhp() %>"></td>
				</tr>
				<tr>
					<td align="center">�������</td>
					<td>&nbsp;<input type="text" id="kbirth" name="kbirth" value="<%=kvo.getKbirth() %>"></td>
				</tr>
				<tr>
					<td align="center">�̸���</td>
					<td>
						&nbsp;<input type="text" id="kmail" name="kmail" size="20" value="<%=kvo.getKemail() %>">
						<select name='kEmailAdd' id='kEmailAdd'>
							<option value="">�����Է�</option>
							<option value="@naver.com">@naver.com</option>
							<option value="@gmail.com">@gmail.com</option>
							<option value="@hanmail.com">@hanmail.com</option>
							<option value="@nate.com">@nate.com</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">�����ȣ</td>
					<td>&nbsp;<input type="text" id="kpostno" name="kpostno" value="<%=kvo.getKpostno() %>"> <input type="button" onclick="sample4_execDaumPostcode()" value="�����ȣã��" id="kaddricon"></td>
				</tr>
				<tr>
					<td align="center">�ּ�</td> 
					<td>&nbsp;<input type="text" id="kjuso" name="kjuso" value="<%=kvo.getKjuso() %>">
					<br>&nbsp;<input type="text" id="kjuso1" name="kjuso1" value="<%=kvo.getKjuso1() %>"><font size="1"> *���ּ��Է� </font>
					<span id="guide" style="color:#999"></span>
					</td>
				</tr>
				<tr>
					<td align="center">��������</td>
					<td>&nbsp;<input type="text" id="kdeleteyn" disabled><br>
				</tr>
				<tr>
					<td align="center">�Է���</td>
					<td>&nbsp;<input type="text" id="kinsertdate" disabled value="<%=kvo.getKinsertdate() %>"><br>
				</tr>
				<tr>
					<td align="center">������</td>
					<td>&nbsp;<input type="text" id="kupdatedate" disabled value="<%=kvo.getKupdatedate() %>"><br>
				</tr>
				<tr>
					<td align="center">�� ����</td>
					<td>
						<input type="file" name="fileName1" id="fileName1" value="<%=kvo.getKimage() %>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<center><img src="/<%=kvo.getKimage() %>" border=0 width="200px" height="200px"></center>
					</td> 
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="hidden" name ="ISUD_TYPE" id ="ISUD_TYPE">
						<input type="button" value="����" id="U">
						<input type="button" value="Ż��" id="D">
					</td>
				</tr>
			</table>
			<%
				}
			}
			%>
		</form>
		</div>
		<div align="center">
		<a href="/kyj/index.jsp">��������</a>
		</div>
	</body>
</html>