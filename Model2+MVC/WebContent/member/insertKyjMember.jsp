<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%  request.setCharacterEncoding("EUC-KR"); %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertKyjMember.jsp</title>
		<!-- 
		<script type="text/javascript"
			    src="../js/jquery-1.11.0.min.js">
		</script>
		 -->
		<script type="text/javascript"
				src="https://code.jquery.com/jquery-1.11.0.min.js">
		</script>
		
		
		<%--�Ʒ��� ���� �ּ� api �Դϴ� --%>
		<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
		<script>
		    //�� ���������� ���θ� �ּ� ǥ�� ��Ŀ� ���� ���ɿ� ����, �������� �����͸� �����Ͽ� �ùٸ� �ּҸ� �����ϴ� ����� �����մϴ�.
		    function sample4_execDaumPostcode() {
		        new daum.Postcode({
		            oncomplete: function(data) {
		                // �˾����� �˻���� �׸��� Ŭ�������� ������ �ڵ带 �ۼ��ϴ� �κ�.
		
		                // ���θ� �ּ��� ���� ��Ģ�� ���� �ּҸ� �����Ѵ�.
		                // �������� ������ ���� ���� ��쿣 ����('')���� �����Ƿ�, �̸� �����Ͽ� �б� �Ѵ�.
		                var fullRoadAddr = data.roadAddress; // ���θ� �ּ� ����
		                var extraRoadAddr = ''; // ���θ� ������ �ּ� ����
		
		                // ���������� ���� ��� �߰��Ѵ�. (�������� ����)
		                // �������� ��� ������ ���ڰ� "��/��/��"�� ������.
		                if(data.bname !== '' && /[��|��|��]$/g.test(data.bname)){
		                    extraRoadAddr += data.bname;
		                }
		                // �ǹ����� �ְ�, ���������� ��� �߰��Ѵ�.
		                if(data.buildingName !== '' && data.apartment === 'Y'){
		                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                }
		                // ���θ�, ���� ������ �ּҰ� ���� ���, ��ȣ���� �߰��� ���� ���ڿ��� �����.
		                if(extraRoadAddr !== ''){
		                    extraRoadAddr = ' (' + extraRoadAddr + ')';
		                }
		                // ���θ�, ���� �ּ��� ������ ���� �ش� ������ �ּҸ� �߰��Ѵ�.
		                if(fullRoadAddr !== ''){
		                    fullRoadAddr += extraRoadAddr;
		                }
		
		                // �����ȣ�� �ּ� ������ �ش� �ʵ忡 �ִ´�.
		                document.getElementById('kpostno').value = data.zonecode; //5�ڸ� �������ȣ ���
		                document.getElementById('kjuso').value = fullRoadAddr;
		                // ����ڰ� '���� ����'�� Ŭ���� ���, ���� �ּҶ�� ǥ�ø� ���ش�.
		                if(data.autoRoadAddress) {
		                    //����Ǵ� ���θ� �ּҿ� ������ �ּҸ� �߰��Ѵ�.
		                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
		                    document.getElementById('guide').innerHTML = '(���� ���θ� �ּ� : ' + expRoadAddr + ')';
		
		                } else {
		                    document.getElementById('guide').innerHTML = '';
		                }
		            }
		        }).open();
		    }
		</script>	
	
		
		
		<%-- ȸ������ �Լ��Դϴ�. --%>
		<script type="text/javascript">
			$(document).ready(function(){
				alert("ȸ������ �������Դϴ�");
				
				$("#reset").click(function(){
					$('#kid').prop('readonly', false);
				});
				

				
				$("#kIdCheck").click(function(){
					var id = $("#kid").val();
					var idlength = id.length;
					alert(idlength);
					alert("id >> " + id);
					if(idlength==0){
						alert('���̵� ����� �Է��ϼ���');
						$("#kid").focus();
						return false;
					}else{
						alert('kIdCheck function');
						callAjax();
					}
		 			
					
										
				});
				
				// delete
				$("#D").click(function(){
					alert("delete!");
					$("#ISUD_TYPE").attr("value", "D");
					$("#memberForm")
					//http://localhost:8088/kyj/MemberControllerServlet
					.attr("action", "/kyj/MemberControllerServlet")
					.submit();
				});
				
				// update
				$("#U").click(function(){
					alert("update!");
					$("#ISUD_TYPE").attr("value", "U");
					$("#memberForm")
					//http://localhost:8088/kyj/MemberControllerServlet
					.attr("action", "/kyj/MemberControllerServlet")
					.submit();
				});
				
				// select
				$("#S").click(function(){
					alert("select ");
					$("#ISUD_TYPE").attr("value", "S");
					$("#memberForm")
					//http://localhost:8088/kyj/MemberControllerServlet
					.attr("action", "/kyj/MemberControllerServlet")
					.submit();
				});
				
				// all select
				$("#A").click(function(){
					alert("select all!");
					$("#ISUD_TYPE").attr("value", "A");
					$("#memberForm")
					//http://localhost:8088/kyj/MemberControllerServlet
					.attr("action", "/kyj/MemberControllerServlet")
					.submit();
				});
				
				// insert
				$("#I").click(function(){
					alert("insert!");

					var kIdVal = $("#kid").val();
					var kPwVal = $("#kpw").val();
					var kPw_Val = $("#kpw_").val();
					var kNameVal = $("#kname").val();
					var kHpVal = $("#khp").val();
					var kBdayVal = $("#kbirth").val();
					var kEmailID = $("#kmail").val();
					var kEmailVal = $("#kEmailAdd").val();
					var kPostVal = $("#kpostno").val();
					var kAddrVal = $("#kjuso").val();
					var kAddrVal1 = $("#kjuso1").val();
					var fileName1 = $("#fileName1").val();
					
					console.log(kEmailID +", "+ kEmailVal + ", " + fileName1);
					
					if(!kIdVal){
			 			alert("���̵� �Է��ϼ���!");
			 			$("#kid").focus();
			 			return false;
			 		}
		 			if(!kPwVal){
			 			alert("��й�ȣ�� �Է��ϼ���!");
			 			$("#kpw").focus();
			 			return false;
			 		}
		 			if(!kPw_Val){
			 			alert("��й�ȣȮ���� �Է��ϼ���!");
			 			$("#kpw_").focus();
			 			return false;
			 		}
		 				 			
					alert("���~");
					$("#ISUD_TYPE").attr("value", "I");
					$("#memberForm")
					//http://localhost:8088/kyj/MemberControllerServlet
					.attr("action", "/kyj/MemberControllerServlet")
					.submit();
				}); //ȸ������
			
		 		// password check
		 		$("#kPwCheck").click(function(){
		 			var kPwVal = $("#kpw").val();
		 			var kPw_Val = $("#kpw_").val();
		 			// alert(kPwVal + " : " + kPw_Val);
		 			console.log(kPwVal + " : " + kPw_Val);
		 			
		 			// �Է¿��ΰ˻�
		 			if(!kPwVal){
		 			//	alert('if >>> : ' + kPwVal);
		 				console.log('if >>> : ' + kPwVal);
		 				alert("��й�ȣ�� �Է��ϼ���.");
		 				$("#kpw").focus();
		 				return false;
		 			}else{
		 			//	alert('else >>> : ' + kPwVal);
		 				console.log('else >>> : ' + kPwVal);
		 			//	alert("��й�ȣ �Է��� �ߵǾ�����. (Ȯ�ο�)");
		 				console.log("��й�ȣ �Է��� �ߵǾ�����.");
		 			}
		 			
		 			if(!kPw_Val){
		 				alert("��й�ȣ Ȯ���� �Է��ϼ���!!!");
		 				$("#kpw_").focus();
		 				return false;
		 			}else{
		 			//	alert('else >>> : ' + kPw_Val);
		 				console.log('else >>> : ' + kPw_Val);
		 			//	alert("��й�ȣȮ�� �Է��� �ߵǾ�����. (Ȯ�ο�)");
		 				console.log("��й�ȣȮ�� �Է��� �ߵǾ�����.");
		 			}
		 			
		 			if(kPwVal==kPw_Val){
		 				alert('��й�ȣ�� ��й�ȣȮ�ζ� ����.');
		 				console.log("Ȯ�� �Ϸ�");
		 				$("#kPw").prop("readonly", true);
		 				$("#kPw_").prop("readonly", true);
		 				$("#kname").focus();
		 			}else{
		 				alert('��й�ȣ�� ��й�ȣ Ȯ�ο� �Է¹��ڰ� �ٸ��ϴ�.');
		 				console.log("��й�ȣ�� ��й�ȣȮ�ζ� �ٸ�.");
		 				$("#kpw").val('');
		 				$("#kpw_").val('');
		 				$("#kpw").focus();
		 			}
		 		}); // end of password check
			}); // end of document.ready(function())
			
			
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
						$("#kid").prop("readonly",true);
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
					<td colspan="2" align="center">ȸ������</td>
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
						<input type="reset" id="reset" value="����">
					</td>
				</tr>
			</table>
		</form>
		
		<!--
		<form name="imageForm" 
		 	  id="imageForm" 
			  method="POST"
			  enctype="multipart/form-data">
				<table border="1" align="center">
				<tr>
					<td align="center">������ ����</td>
					<td><input type="file" name="fileName1" id="fileName1"></td>
				</tr>
				</table>
		</form>
		-->
	</body>
</html>