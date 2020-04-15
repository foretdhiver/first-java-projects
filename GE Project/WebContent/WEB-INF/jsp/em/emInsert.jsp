<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmPrInfoVO" %>    
<%@ page import="com.spring.ge.vo.CommonVO" %>    
<%@ page import="java.util.ArrayList" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>������</title>
		
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
		                document.getElementById('emprpostno').value = data.zonecode; //5�ڸ� �������ȣ ���
		                document.getElementById('empradd').value = fullRoadAddr;
		                document.getElementById('sample4_jibunAddress').value = data.jibunAddress;
		
		                // ����ڰ� '���� ����'�� Ŭ���� ���, ���� �ּҶ�� ǥ�ø� ���ش�.
		                if(data.autoRoadAddress) {
		                    //����Ǵ� ���θ� �ּҿ� ������ �ּҸ� �߰��Ѵ�.
		                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
		                    document.getElementById('guide').innerHTML = '(���� ���θ� �ּ� : ' + expRoadAddr + ')';
		
		                } else if(data.autoJibunAddress) {
		                    var expJibunAddr = data.autoJibunAddress;
		                    document.getElementById('guide').innerHTML = '(���� ���� �ּ� : ' + expJibunAddr + ')';
		
		                } else {
		                    document.getElementById('guide').innerHTML = '';
		                }
		            }
		        }).open();
		    }
		</script>
		<script type="text/javascript"
				src="https://code.jquery.com/jquery-1.11.0.min.js">
		</script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<script type="text/javascript">
			$(document).ready(function(){
				$("#insert").click(function(){
					alert("insert �Լ�");
					$("#insertForm").attr("action", "/em/emInsert.ge");						
					$("#insertForm").submit();
				});
				$("#update").click(function(){
					alert("update �Լ�");
					$("#insertForm").attr("action", "/em/emUpdatest.ge");						
					$("#insertForm").submit();
				});
				$("#delete").click(function(){
					alert("delete �Լ�");
					$("#insertForm").attr("action", "/em/emDelete.ge");						
					$("#insertForm").submit();
				});
			});
			$(function(){
				$("#emhiredate").datepicker({
					dateFormat: 'yymmdd',
				    prevText: '���� ��',
				    nextText: '���� ��',
				    monthNames: ['1��','2��','3��','4��','5��','6��','7��','8��','9��','10��','11��','12��'],
				    monthNamesShort: ['1��','2��','3��','4��','5��','6��','7��','8��','9��','10��','11��','12��'],
				    dayNames: ['��','��','ȭ','��','��','��','��'],
				    dayNamesShort: ['��','��','ȭ','��','��','��','��'],
				    dayNamesMin: ['��','��','ȭ','��','��','��','��'],
				    showMonthAfterYear: true,
				    changeMonth: true,
				    changeYear: true,
				    yearSuffix: '��'
				});
			});
		</script>
	</head>
	<body>
		<form name="insertForm"
			  id = "insertForm"
			  method = "POST"
			  enctype = "multipart/form-data">
			<table border = "1" align = "center">
				<tr>
					<td colspan="2" align="center">
						<center>������</center>
					</td>
				</tr>
				<tr>
					<td width="200" align="center">���</td>
					<td width="500">
						<input type="text" name="emno" id="emno" readonly>
					</td>
				</tr>
				<tr>
					<td align="center">�ѱ��̸�</td>
					<td><input type="text" name="emname" id="emname"></td>
				</tr>
				<tr>
					<td align="center">���̵�</td>
					<td>
						<input type="text" name="emid" id="emid">
						<input type="button" name="idCheck" id="idCheck" value="�ߺ�üũ">
					</td>
				</tr>
				<tr>
					<td align="center">��й�ȣ</td>
					<td><input type="text" name="empw" id="empw"></td>
				</tr>
				<tr>
					<td align="center">�Ի���</td>
					<td><input type="text" name="emhiredate" id="emhiredate"></td>
				</tr>
				<tr>
					<td align="center">�μ�</td>
					<td>
						<select name="deptcd" id="deptcd">
							<option value="20">������</option>
							<option value="30">����������</option>
							<option value="40">������</option>
							<option value="50">���������</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">����</td>
					<td>
						<select name="jobcd" id="jobcd">
								<option value="07">���</option>
								<option value="06">�븮</option>
								<option value="05">����</option>
								<option value="04">����</option>
								<option value="03">����</option>
						</select>
					</td>	
				</tr>
				<tr>
					<td align="center">�̸���</td>
					<td>
						<input type="text" name="ememailid" id="ememailid">
						@
						<select name="ememailadd" id="ememailadd">
								<option value="naver.com">naver.com</option>
								<option value="gmail.com">gmail.com</option>
								<option value="hanmail.net">hanmail.net</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">�ڵ�����ȣ</td>
					<td>
						<select name="emfhp" id="emfhp">
							<option value="010" selected="selected">010</option>
							<option value="011">011</option>
						</select>
						-
						<input type="text" name="emshp" id="emshp">
						-
						<input type="text" name="emlhp" id="emlhp">
					</td>
				</tr>
				<tr>
					<td align="center">������ȣ</td>
					<td><input type="text" name="emexno" id="emexno"></td>
				</tr>
				<tr>
					<td align="center">�����ȣ</td>
					<td>
						<select name="emfdino" id="emfdino">
							<option value="02" selected="selected">02</option>
							<option value="031">031</option>
							<option value="032">032</option>
						</select>
						-
						<input type="text" name="emsdino" id="emsdino">
						-
						<input type="text" name="emldino" id="emldino">
					</td>
				</tr>
				<tr>
					<td align="center">����</td>
					<td>
						<input type="file" name="emsign" id="emsign">
						<input type="reset" value="�ٽ�" id="re">	
					
					</td>
				</tr>
				<tr>
					<td align="center">�������</td>
					<td><input type="text" name="emprbirth" id="emprbirth"></td>
				</tr>
				<tr>
					<td align="center">����</td>
					<td>
						<select name="emprsex" id="emprsex">
								<option value="01">����</option>
								<option value="02">����</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">ȥ�ο���</td>
					<td>
						<select name="emprmarital" id="emprmarital">
								<option value="61">��ȥ</option>
								<option value="62">��ȥ</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">�����ȣ</td>
					<td>
						<input type="text" name="emprpostno" id="emprpostno">
						<input type="button" name="noSearch" id="noSearch" value="�����ȣ ã��" onclick="sample4_execDaumPostcode()">
					</td>
				</tr>
				<tr>
					<td align="center">�ּ�</td>
					<td>
						<input type="text" name="empradd" id="empradd">
					</td>
				</tr>
				<tr>
					<td align="center">���ּ�</td>
					<td>
						<input type="text" name="emprdetailadd" id="emprdetailadd">
					</td>
				</tr>
				<tr>
					<td align="center">�з�</td>
					<td>
						<select name="empreducd" id="empreducd">		
							<option value="31">����</option>
							<option value="32">�ʴ���</option>
							<option value="33">����</option>
							<option value="34">������</option>
							<option value="35">�ڻ���</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">�����б���</td>
					<td><input type="text" name="empreduname" id="empreduname"></td>
				</tr>
				<tr>
					<td align="center">����</td>
					<td>
						<input type="file" name="empic" id="empic">
						<input type="reset" value="�ٽ�" id="re">					
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="�Է�" id="insert" name="insert">
						<input type="button" value="���" id="select" name="select">
						<input type="reset" value="�ٽ�">
					</td>
				</tr>
			</table>
		</form>	
	</body>
</html>