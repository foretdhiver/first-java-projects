<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<!-- 
		<script type="text/javascript"
			    src="../js/jquery-1.11.0.min.js">
		</script>
		 -->
		<script type="text/javascript"
				src="https://code.jquery.com/jquery-1.11.0.min.js">
		</script>
		<script type="text/javascript">
			// ���̵� üũ Ŭ���̺�Ʈ
			// �������
			$(document).ready(function(){
				alert('asdfasdf');
				$("#kIdCheck").click(function(){
					alert('kIdCheck function');
					callAjax();
				});
			});
			
			function callAjax(){
				alert("callAjax �Լ�");
				$.ajax({					
					type : "POST",
				//	url  : "/kyj/member/ajxservlet.jsp",
					url  : "/kyj/MemberControllerServlet",
					contentType   : "application/x-www-form-urlencoded",
					data : {					
			 		kid   : $('#kid').val(),
			 		memberType : "kIdCheck",
					},
					success : whenSuccess,
					error   : whenError
				});
					function whenSuccess(data){
					alert("whenSuccess �Լ�");
					console.log(data);
					var boolVal = $(data).find("result").text();
					alert(" boolVal >>> : " + boolVal);
					console.log(boolVal);
					
					var result = eval(boolVal);
					alert(" result >>> : " + result);
					console.log(result);
					if(result){
						alert(" ��� �� �� �ִ� ���̵� �Դϴ�. ");
						$("#kpw").focus();
					}else{
						alert(" ��� �� �� ���� ���̵� �Դϴ�. ");
						$("#kid").val('');
						$("#kid").focus();
					}
				
					}; //function data ��
					
					function whenError(){
						alert("whenError �Լ� ������ ");
					}
			} // call aJax function ��

			
		</script>
	</head>
	<body>
		<form name="memberForm" 
		 	  id="memberForm" 
			  method="POST"
			  enctype="multipart/form-data">
		<!--  enctype="application/x-www-form-urLencoded"> -->
		
			<table border="1" width="450px"align="center">
				<tr>
					<td colspan="2" align="center"><h3>AJAX �׽�Ʈ</h3></td>
				</tr>
				<tr>
					<td width="100" align="center">ȸ����ȣ</td>
					<td width="250">
					&nbsp;<input type="text" id=knumm disabled><br>
					</td>
				</tr>
				<tr>
					<td align="center">���̵�</td>
					<td>&nbsp;<input type="text" id="kid" name="kid" >
					<input type="hidden" id="idCheck" name="idCheck">
					<input type="button" id="kIdCheck" value="���̵��ߺ�Ȯ��">
					<br>
					<font size="1"> *�ʼ��Է�  �˻��� ������ �� ���Դϴ�  </font>
					</td>
				</tr>
				<tr>
					<td align="center">��й�ȣ</td>
					<td>&nbsp;<input type="text" id="kpw" name="kpw"><br>
					<font size="1"> *�ʼ��Է� </font></td>
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
					<td>&nbsp;<input type="text" id="kname" name="kname"><br>
					<font size="1"> *�ʼ��Է�</font></td>
				</tr>
					<tr>
					<td align="center">�޴�����ȣ</td>
					<td>&nbsp;<input type="text" id="khp" name="khp"></td>
				</tr>
				<tr>
					<td align="center">�������</td>
					<td>&nbsp;<input type="text" id="kbirth" name="kbirth"></td>
				</tr>
				<tr>
					<td align="center">�̸���</td>
					<td>
						&nbsp;<input type="text" id="kmail" name="kmail" size="20">
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
					<td>&nbsp;<input type="text" id="kpostno" name="kpostno"> <input type="button" onclick="sample4_execDaumPostcode()" value="�����ȣã��" id="kaddricon"></td>
				</tr>
				<tr>
					<td align="center">�ּ�</td> 
					<td>&nbsp;<input type="text" id="kjuso" name="kjuso">
					<br>&nbsp;<input type="text" id="kjuso1" name="kjuso1"><font size="1"> *���ּ��Է� </font>
					<span id="guide" style="color:#999"></span>
					</td>
				</tr>
				<tr>
					<td align="center">��������</td>
					<td>&nbsp;<input type="text" id="kdeleteyn" disabled><br>
				</tr>
				<tr>
					<td align="center">�Է���</td>
					<td>&nbsp;<input type="text" id="kinsertdate" disabled><br>
				</tr>
				<tr>
					<td align="center">������</td>
					<td>&nbsp;<input type="text" id="kupdatedate" disabled><br>
				</tr>
				<tr>
					<td align="center">������ ����</td>
					<td><input type="file" name="fileName1" id="fileName1"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="hidden" name ="ISUD_TYPE" id ="ISUD_TYPE">
						<input type="button" value="ȸ������" id="I">
						<input type="button" value="��ȸ" id="S">
						<input type="button" value="����" id="U">
						<input type="button" value="Ż��" id="D">
						<input type="button" value="��üȸ������" id="A">
						<input type="reset" value="����">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>