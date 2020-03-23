<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ page import="com.kyjspring.board.common.Filepath" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.util.List" %>
<%@ page import="com.kyjspring.board.vo.BoardVO" %>
<% request.setCharacterEncoding("EUC-KR"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>boardInsert.jsp</title>
		<!-- <script type="text/javascript" src="../js/jquery-1.11.0.min.js"></script> -->
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript">
		 $(document).ready(function(){
		 		alert('boardInsert.jsp');
		 		$('#insertData').attr('disabled', true);
				
				$("#insertData").click(function(){
					alert('insertData ����');
					var kname = $("#kname").val();
					var kpw = $("#kpw").val();
					var kpw_ = $("#kpw_").val();
					var kmemo = $("#kmemo").val();
					var fileName = $("#fileName").val();
					
					if($("#kno").val()=="") $("#kno").val(0);
					if(confirm('����� �����ұ��?')){
						$("#boardForm").attr("enctype", "multipart/form-data");
						$("#boardForm").attr("method", "POST");
						$("#boardForm").attr("action", "/board/insertBoard.kyj");
						$("#boardForm").submit();
					}
				});
		 		
				
		 		// byte �� ���� �Լ�
		 		$('.js-sms-content').keyup(function(){
		 			cut_600(this);
		 		});
		 		
		 		$('.js-send-sms').click(function(){
		 			if(getTextLength($('.js-sms-content').val()) > 600) {
		 				alert("���ڴ� 600����Ʈ ���Ϸ� �����ּ���.");
		 				return false;
		 			}else{
		 				alert("���ڰ� 600����Ʈ �����Դϴ�.");
		 			}
		 		});
		 		
		 		// ��й�ȣüũ
		 		$("#kPwCheck").click(function(){
		 			alert("kPwCheck function");
		 			var kPwVal = $("#kpw").val();
		 			var kpw_Val = $("#kpw_").val();
		 			// alert(kPwVal + " : " + kpw_Val);
		 			console.log(kPwVal + " : " + kpw_Val);
		 			
		 			// �Է¿��ΰ˻�
		 			if(!kPwVal){
		 			//	alert('if >>> : ' + kPwVal);
		 				console.log('if >>> : ' + kPwVal);
		 				alert("��й�ȣ�� �Է��ϼ���.");
		 				$("#kPwVal").focus();
		 				return false;
		 			}else{
		 			//	alert('else >>> : ' + kPwVal);
		 				console.log('else >>> : ' + kPwVal);
		 			//	alert("��й�ȣ �Է��� �ߵǾ�����. (Ȯ�ο�)");
		 				console.log("��й�ȣ �Է��� �ߵǾ�����.");
		 			}
		 			
		 			if(!kpw_Val){
		 				alert("��й�ȣ Ȯ���� �Է��ϼ���.");
		 				$("#kpw_Val").focus();
		 				return false;
		 			}else{
		 			//	alert('else >>> : ' + kpw_Val);
		 				console.log('else >>> : ' + kpw_Val);
		 			//	alert("��й�ȣȮ�� �Է��� �ߵǾ�����. (Ȯ�ο�)");
		 				console.log("��й�ȣȮ�� �Է��� �ߵǾ�����.");
		 			}
		 			
		 			if(kPwVal==kpw_Val){
		 				alert("��й�ȣ�� ��й�ȣȮ�ζ� ����.");
		 				console.log("Ȯ�� �Ϸ�");
		 				$('#kpw').prop('readonly', true);
		 				$('#kpw_').prop('readonly', true);
		 				$('#insertData').attr('disabled', false);
		 				$("#kmemo").focus();
		 			}else{
		 				alert("��й�ȣ�� ��й�ȣ Ȯ�ο� �Է¹��ڰ� �ٸ��ϴ�.");
		 				console.log("��й�ȣ�� ��й�ȣȮ�ζ� �ٸ�.");
		 				$("#kpw").val('');
		 				$("#kpw_").val('');
		 				$("#kPw").focus();
		 			}
		 		}); // ��й�ȣ Ȯ��
		 });
		 
		 	// byte üũ�� ���� �Ѿ�� �ڵ����� ����°�
			function getTextLength(str) {
			        var len = 0;
			        for (var i = 0; i < str.length; i++) {
			            if (escape(str.charAt(i)).length == 6) {
			                len++;
			            }
			            len++;
			        }
			        return len;
			 }
			 function cut_600(obj){
			        var text = $(obj).val();
			        var leng = text.length;
			        while(getTextLength(text) > 600){
			            leng--;
			            text = text.substring(0, leng);
			        }
			        $(obj).val(text);
			        $('.bytes').text(getTextLength(text));
			 }
			 
		 </script>
	</head>
	<body>
		<form name="boardForm" id="boardForm" >
			<div>
				<table border="1" width="800px" align="center">
					<tr>
						<td colspan="2" align="center">�Խ���</td>
					</tr>
					<tr>
						<td align="center">����</td>
						<td><input type="text" name="ksubject" id="ksubject"  size="20"></td>
					</tr>
					<tr>
						<td align="center">�ۼ���</td>
						<td>
							<input type="text" name="kname" id="kname"  size="20">
							
						</td>
					</tr>
					<tr>
						<td align="center">��й�ȣ</td>
						<td><input type="password" name="kpw" id="kpw"  size="20"></td>
					</tr>
					<tr>
						<td align="center">��й�ȣ Ȯ��</td>
						<td>
							<input type="password" name="kpw_" id="kpw_" size="20">
							<input type="button" id="kPwCheck" value="��й�ȣȮ��">
						</td>
					</tr>
					<tr>
						<td align="center">�̹��� ÷��</td>
						<td><input type="file" name="fileName1" id="fileName1"></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<textarea name="kmemo"  id="kmemo" rows="25" style="width:98%;" class="js-sms-content"></textarea>
							<div class="bytes-wrapper">
								<span class="bytes" >0</span>/600 bytes 
							</div>
							
						</td>
					</tr>
				</table>
			</div>
		</form>
		<div>
		<p></p>
			<table border="0" align="center">
				<tr align="center">
					<td>
						<input type="button" id="insertData" value="���"/>
					</td>
					<td>
						<input type="button" id="reset" value="����"/>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>