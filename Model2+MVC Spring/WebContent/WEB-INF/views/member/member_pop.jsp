<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  request.setCharacterEncoding("EUC-KR"); %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>��� ���̺� �˾�</title>
		
		<style type="text/css">
			.required{color:red;}
			input[type="text"]{width:200px;}
			input[type="password"]{width:200px;}
		</style>
		 
		<!-- <script type="text/javascript" src="../lib/jquery-1.11.0.min.js"></script> -->
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		
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
		
		<script type="text/javascript">
			$(document).ready(function(){
				
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
				
				$("#reset").click(function(){
					alert('reset ��ư');
					$('#kid').prop('readonly',false);
					$('#kid').val("");
					$('#kpw').prop('readonly', false);
					$('#kpw').val("");
					$('#kpw_').prop('readonly', false);
					$('#kpw_').val("");
				});
				
				alert('member_pop.jsp ����');
				var mode = "${mode}";
				if(mode=="insert"){
					alert('insert ����');
					$('#insertData').attr('disabled', false);
					$('#updateData').attr('disabled', true);
					$('#deleteData').attr('disabled', true);
					$('#kid').attr('readonly', false);
					$('#kmem').attr('readonly', true);
				}else if(mode=="update"){
					alert('update ����');
					$('#insertData').attr('disabled', true);
					$('#updateData').attr('disabled', false);
					$('#deleteData').attr('disabled', false);
					$('#kid').attr('readonly', true);
					$('#kmem').attr('readonly', true);
				}
				
				$("#insertData").click(function(){
					alert('insertData ����');
					var kIdVal = $("#kid").val();
					var kPwVal = $("#kpw").val();
					var kPw_Val = $("#kpw_").val();
					var kNameVal = $("#kname").val();
					var kHpVal = $("#khp").val();
					var kBdayVal = $("#kbirth").val();
					var kEmailID = $("#kmail").val();
					var kPostVal = $("#kpostno").val();
					var kAddrVal = $("#kjuso").val();
					var kAddrVal1 = $("#kjuso1").val();
					var fileName = $("#fileName").val();
					
					if(!validateForm()) return;
					if($("#kid").val()=="") $("#kid").val(0);
					if(confirm('����� �����ұ��?')){
						$("#memberForm").attr("enctype", "multipart/form-data");
						$("#memberForm").attr("method", "POST");
						$("#memberForm").attr("action", "/member/insertMember.kyj");
						$("#memberForm").submit();
					}
				});
				
				$("#updateData").click(function(){
					alert('updateData ����');
					if(!validateForm()) return;
					if(confirm('������ �����ұ��?')){
						$("#memberForm").attr("enctype", "multipart/form-data");
						$("#memberForm").attr("method", "POST");
						$("#memberForm").attr("action", "/member/updateMember.kyj");
						$("#memberForm").submit();
					}
				});
				
				$("#deleteData").click(function(){
					alert('deleteData ����');
					if(confirm('������ �����ұ��?')){
						$("#memberForm").attr("enctype", "multipart/form-data");
						$("#memberForm").attr("method", "POST");
						$("#memberForm").attr("action", "/member/deleteMember.kyj");
						$("#memberForm").submit();
					}
				});
				
				$("#closeWindow").click(function(){
					alert('closeWindow ����');
					window.close();
				});	
				
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
		 				$("#kpw").prop("readonly", true);
		 				$("#kpw_").prop("readonly", true);
		 				$("#kname").focus();
		 			}else{
		 				alert('��й�ȣ�� ��й�ȣ Ȯ�ο� �Է¹��ڰ� �ٸ��ϴ�.');
		 				console.log("��й�ȣ�� ��й�ȣȮ�ζ� �ٸ�.");
		 				$("#kpw").val('');
		 				$("#kpw_").val('');
		 				$("#kpw").focus();
		 			}
		 		}); // end of password check
		 		
			});
			
			function callAjax(){
				alert("callAjax �Լ�");
				$.ajax({					
					type : "POST",
					url  : "/member/idValueCkeck.kyj",
					contentType   : "application/x-www-form-urlencoded",
					data : {					
			 		kid   : $('#kid').val()
			 		},
					success : whenSuccess,
					error   : whenError
				});
					function whenSuccess(data){
					alert("whenSuccess �Լ�");
					console.log(data);
					var boolVal = $(data).find("resultid").text();
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
				
					} //function data ��
					
					function whenError(e){
						alert("whenError �Լ� ������ ");
						console.log(e);
					}
			} // call aJax function ��
			
			function validateForm(){
				if($("#kid").val().replace(/\s/g,"")==""){
					alert('���̵� �Է����ּ���.');
					return false;
				}
				if($("#kpw").val().replace(/\s/g,"")==""){
					alert('��й�ȣ�� �Է����ּ���.');
					return false;
				}
				if($("#kpw_").val().replace(/\s/g,"")==""){
					alert('��й�ȣ�� �Է����ּ���.');
					return false;
				}
				return true;
			}
			
		</script>
	</head>
	<body>
		<p></p>
		<div>
			<form id="memberForm" name="memberForm">
	<!-- 	<input type="hidden" id="memFormHidden" name="memFormHidden"/>  -->
				<table border="1">
					<thead>
						<tr>
							<td colspan="2" align="center">
								<h4>��� ���̺� �˾�â</h4>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ȸ����ȣ</th>
							<td><input type="text" id="kmem" name="kmem" value="${memberVO.kmem}"/></td>
						</tr>
						<tr>
							<th><span class="required">*</span>���̵�</th>
							<td><input type="text" id="kid" name="kid" value="${memberVO.kid}"/>
							<input type="button" id="kIdCheck" value="���̵��ߺ�Ȯ��"></td>
						</tr>
						<tr>
							<th><span class="required">*</span>��й�ȣ</th>
							<td><input type="password" id="kpw" name="kpw" value="${memberVO.kpw}"/></td>
						</tr>
						<tr>
							<th><span class="required">*</span>��й�ȣ<br>Ȯ��</th>
							<td><input type="password" id="kpw_" name="kpw_"/>&nbsp;<input type="button" id="kPwCheck" value="��й�ȣȮ��"></td>
						</tr>
						<tr>
							<th><span class="required">*</span>�̸�</th>
							<td><input type="text" id="kname" name="kname" value="${memberVO.kname}"/></td>
						</tr>
						<tr>
							<th>�޴�����ȣ</th>
							<td><input type="text" id="khp" name="khp" value="${memberVO.khp}"/></td>
						</tr>
						<tr>
							<th>����</th>
							<td><input type="text" id="kbirth" name="kbirth" value="${memberVO.kbirth}"/></td>
						</tr>
						<tr>
							<th>�̸���</th>
							<td><input type="text" id="kemail" name="kemail" value="${memberVO.kemail}"/></td>
						</tr>
						<tr>
							<th>�����ȣ</th>
							<td><input type="text" id="kpostno" name="kpostno" value="${memberVO.kpostno}"/>
							<input type="button" onclick="sample4_execDaumPostcode()" value="�����ȣã��" id="kaddricon"></td>
						</tr>
						<tr>
							<th>�ּ�</th>
							<td><input type="text" id="kjuso" name="kjuso" value="${memberVO.kjuso}"/></td>
						</tr>
						<tr>
							<th>���ּ�</th>
							<td><input type="text" id="kjuso1" name="kjuso1" value="${memberVO.kjuso1}"/></td>
						</tr>
						<tr>
							<th>������</th>
							<td><input type="file" id="kimage" name="kimage" value="${memberVO.kimage}"/></td>
						</tr>
						<tr>
							<td colspan="3" style="border:0;"></td>
						</tr>
						
					</tbody>
				</table>
			</form>
		</div>
		<div>
		<p></p>
			<table border="0" align="center">
				<tr align="center">
					<td>
						<input type="button" id="insertData" value="���"/>
					</td>
					<td>
						<input type="button" id="updateData" value="����"/>
					</td>
					<td>
						<input type="button" id="deleteData" value="����"/>
					</td>
					<td>
						<input type="button" id="closeWindow" value="�ݱ�"/>
					</td>
					<td>
						<input type="button" id="reset" value="����"/>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>