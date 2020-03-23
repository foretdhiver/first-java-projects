<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  request.setCharacterEncoding("EUC-KR"); %>    
<%@ page import="com.kyjspring.board.common.Filepath" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.util.List" %>
<%@ page import="com.kyjspring.board.vo.BoardVO" %>
<% request.setCharacterEncoding("EUC-KR"); %>
<%
	Object obj = request.getAttribute("boardList");
	List<BoardVO> list = null;
	if(obj!=null){
		list = (List<BoardVO>)obj;	
		String filepath = Filepath.DOWNLOAD_FILEPATH;
		System.out.println("board.jsp.list.size : " + list.size());
		int listCnt = list.size();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>boardUpdate.jsp</title>
		<!-- <script type="text/javascript" src="../js/jquery-1.11.0.min.js"></script> -->
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript">
		 $(document).ready(function(){
		 		var mode = "${mode}";
		 		alert('mode : ' + mode);
				if(mode=="update"){
					alert('update ����');
					$('#updateData').attr('disabled', true);
					$('#deleteData').attr('disabled', true);
			//		$('#kname').attr('readonly', true);
					$('#kno').attr('readonly', true);
				}
		 		

				$("#updateData").click(function(){
					alert('updateData ����');
					if(!validateForm()) return;
			//		if(!callAjax()) return;
					// ajax ȣ��
			//		alert('kpwCheck function');
			//		callAjax();
					// ajax ��
					if(confirm('������ �����ұ��?')){
						$("#boardForm").attr("enctype", "multipart/form-data");
						$("#boardForm").attr("method", "POST");
						$("#boardForm").attr("action", "/board/updateBoard.kyj");
						$("#boardForm").submit();
					}
				});
				
				$("#deleteData").click(function(){
					alert('deleteData ����');
					if(confirm('������ �����ұ��?')){
						$("#boardForm").attr("enctype", "multipart/form-data");
						$("#boardForm").attr("method", "POST");
						$("#boardForm").attr("action", "/board/deleteBoard.kyj");
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
		 				$('#updateData').attr('disabled', false);
		 				$('#deleteData').attr('disabled', false);
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
			// ���Ȯ��
			 function callAjax(){
				alert("callAjax �Լ�");
				$.ajax({					
					type : "POST",
					url  : "/board/pwValueCheck.kyj",
					contentType   : "application/x-www-form-urlencoded",
					data : {					
			 		kpw   : $('#kpw').val(),
			 		kno	  : $('#kno').val()
					},
					success : whenSuccess,
					error   : whenError
				});
					function whenSuccess(data){
					alert("whenSuccess �Լ�");
					console.log(data);
					var boolVal = $(data).find("resultpw").text();
					alert(" boolVal >>> : " + boolVal);
					console.log(boolVal);
					
					var result = eval(boolVal);
					alert(" result >>> : " + result);
					console.log(result);
					if(result){
						alert(" ��й�ȣ�� �����ϴ� ");
						status.kpw = true;
						$("#kpw").prop("readonly",true);
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
			 
			 function validateForm(){
					if($("#kpw_").val().replace(/\s/g,"")==""){
						alert('��й�ȣ�� �Է����ּ���.');
						return false;
					}
					return true;
				}
			 
		 </script>
	</head>
	<body>
		<form name="boardForm" id="boardForm" >
			<div>
				<table border="1" width="800px" align="center">
				<% 
				for(int i=0;i<listCnt;i++)
				{ BoardVO kvo = (BoardVO)list.get(i);
				%>
					<tr>
						<td colspan="2" align="center">�Խ���</td>
					</tr>
					<tr>
						<td align="center">�۹�ȣ</td>
						<td><%= kvo.getKno() %></td>
					</tr>
					<tr>
						<td align="center">����</td>
						<td><%= kvo.getKsubject() %></td>
					</tr>
					<tr>
						<td align="center">�ۼ���</td>
						<td><%= kvo.getKname() %></td>
					</tr>
					<tr>
						<td align="center">�̹���</td>
						<td><img src="/<%= kvo.getKimage() %>" width="300px"></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><%= kvo.getKmemo() %></td>
					</tr>
				<%} 
				}%>
				</table>
			</div>
		</form>
		<div>
		<p></p>
			<table border="0" align="center">
				<tr align="center">
					<td>
						<input type="button" id="updateData" value="����"/>
					</td>
					<td>
						<input type="button" id="deleteData" value="����"/>
					</td>

				</tr>
			</table>
		</div>
	</body>
</html>