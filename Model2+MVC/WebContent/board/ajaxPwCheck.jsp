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
			
			var status = {
				kno : false,        //id Ȯ�ΰ�
                kpw : false        //��й�ȣ Ȯ�ΰ�
           	};
			
			$(document).ready(function(){
				alert('asdfasdf');
				$("#kPwCheck").click(function(){
					alert('kPwCheck function');
					callAjax();
				});
			});
			
			function callAjax(){
				alert("callAjax �Լ�");
				$.ajax({					
					type : "POST",
				//	url  : "/kyj/board/ajxServlet.jsp",
					url  : "/kyj/HelloBoardControllerServlet",
					contentType   : "application/x-www-form-urlencoded",
					data : {					
			 		kpw   : $('#kpw').val(),
			 		kno	  : $('#kno').val(),
			 		memberType : "kPwCheck",
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
						alert(" ��й�ȣ�� �����ϴ� ");
						status.kpw = true;
						$("#kpw").prop("disabled",true);
					}else{
						alert(" ��й�ȣ�� �ٸ��ϴ�");
						$("#kpw").val('');
						$("#kpw").focus();
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
					<td align="center">�Խñ۹�ȣ</td>
					<td>&nbsp;<input type="text" id="kno" name="kno"><br>
					<font size="1">  </font></td>
				</tr>
				<tr>
					<td align="center">��й�ȣ</td>
					<td>&nbsp;<input type="password" id="kpw" name="kpw"><br>
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