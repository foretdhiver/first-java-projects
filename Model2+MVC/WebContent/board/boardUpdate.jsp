<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%  request.setCharacterEncoding("EUC-KR"); %>    
<%@ page import="kyj.member.common.FilePath" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kyj.board.vo.KyjHelloBoardVO" %>
<% request.setCharacterEncoding("EUC-KR"); %>
<%	
	Object obj = request.getAttribute("aList");
	ArrayList<KyjHelloBoardVO> aList = null;
	String filepath = "";
	
	if (obj !=null){
		aList = (ArrayList<KyjHelloBoardVO>)obj;
		filepath = FilePath.DOWNLOAD_FILEPATH;
		System.out.println("aList.size() >>> : " + aList.size());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Select</title>
		<!-- 
		<script type="text/javascript"
			    src="../js/jquery-1.11.0.min.js">
		</script>
		 -->
		<script type="text/javascript"
				src="https://code.jquery.com/jquery-1.11.0.min.js">
		</script>
		 <script type="text/javascript">
		 var status = {
					kno : false,        //id Ȯ�ΰ�
	                kpw : false        //��й�ȣ Ȯ�ΰ�
	           	};
		 $(document).ready(function(){
		 		alert('�� html���������� function�� ���ư��� �ִ�.');
		 		
		 		$("#reset").click(function(){
					$('#kpw').prop('readonly', false);
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
		 				callAjax();
		 			}
		 /*			
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
		 				$("#kmemo").focus();
		 			}else{
		 				alert("��й�ȣ�� ��й�ȣ Ȯ�ο� �Է¹��ڰ� �ٸ��ϴ�.");
		 				console.log("��й�ȣ�� ��й�ȣȮ�ζ� �ٸ�.");
		 				$("#kpw").val('');
		 				$("#kpw_").val('');
		 				$("#kPw").focus();
		 			}
		 */			
		 		}); // ��й�ȣ Ȯ��
		 		
		 		
	
		 		$("#A").click(function(){
		 			alert("S function ����");
		 			$("#ISUD_TYPE").attr("value", "A");
		 			$("#boardForm").attr('action', '/kyj/HelloBoardControllerServlet').submit();
		 		});
		 		
		 		$("#UOK").click(function(){
		 			alert("U function ����");
		 			if(!($("#kpw").val())){
		 				alert("��й�ȣ�� �Է��ϼ���.");
		 				$("#kPwVal").focus();
		 				return false;
		 			}
		 			else{
		 				$("#ISUD_TYPE").attr("value", "UOK");
			 			$("#boardForm").attr('action', '/kyj/HelloBoardControllerServlet').submit();	
		 			}
		 			
		 			
		 		});
		 		
		 		$("#D").click(function(){
		 			alert("D function ����");
		 			if(!($("#kpw").val())){
		 				alert("��й�ȣ�� �Է��ϼ���.");
		 				$("#kPwVal").focus();
		 				return false;
		 			}
		 			else{
			 			$("#ISUD_TYPE").attr("value", "D");
			 			$("#boardForm").attr('action', '/kyj/HelloBoardControllerServlet').submit();
		 			}
		 		});
		 });
		 	// ���Ȯ��
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
			
		 	/// byte üũ�� ���� �Ѿ�� �ڵ����� ����°�
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
		<form name="boardForm" 
			  id="boardForm" 
			  method="POST"
			  enctype="multipart/form-data">
			<div>
				<table border="1" width="450px" align="center">
			<% 
			for(int i=0;i<aList.size();i++)
			{ KyjHelloBoardVO kvo = (KyjHelloBoardVO)aList.get(i);
			%>
					<tr>
						<td colspan="2" align="center"> �Խ���</td>
					</tr>
					<tr>
						<td align="center">�۹�ȣ</td>
						<td><input type="text" value="<%= kvo.getKno()%>" name="kno" id="kno" size="20" readonly></td>
					</tr>
					<tr>
						<td align="center">������</td>
						<td><input type="text" value="<%= kvo.getKsubject()%>" name="ksubject" id="ksubject" size="20" ></td>
					</tr>
					<tr>
						<td align="center">�۾���</td>
						<td>
							<input type="text" value="<%= kvo.getKname()%>" name="kname" id="kname" size="20">
						</td>
					</tr>
					<tr>
						<td align="center">��й�ȣ</td>
						<td><input type="password" name="kpw" id="kpw" size="20" >
							<input type="button" id="kPwCheck" value="��й�ȣȮ��">
						</td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td align="center">�Է���</td>
						<td><input type="text" value="<%= kvo.getKinsertdate()%>" name="kinsertdate" id="kinsertdate" size="20" readonly></td>
					</tr>
					<tr>
						<td align="center">������</td>
						<td><input type="text" value="<%= kvo.getKupdatedate()%>" name="kupdatedate" id="kupdatedate" size="20" readonly></td>
					</tr>
					<tr>
						<td align="center">�̹��� ÷��</td>
						<td><input type="file" name="fileName1" id="fileName1"></td>
					</tr>
					<tr>
						<td align="center" colspan="2"> 
						<center><img src="/<%=kvo.getKimage() %>" alt="�̹����� �����ϴ�" border=0 width="300px" height=300px"></center>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<textarea name="kmemo" id="kmemo"  rows="25" cols="60" class="js-sms-content"><%= kvo.getKmemo()%></textarea>
							<div class="bytes-wrapper">
								<span class="bytes" >0</span>/600 bytes 
							</div>
							
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="hidden" name ="ISUD_TYPE" id ="ISUD_TYPE">
						<!--<input type="button" class="js-send-sms" value="����" id="SendForm"> -->
							<input type="button" value="�۸��" id="A">
							<input type="button" value="�ۼ���" id="UOK">
							<input type="button" value="�ۻ���" id="D">
							<input type="reset" id="reset" value="����">
						</td>
					</tr>
			<%} 
			}%>
				</table>
			</div>
		</form>
	</body>
</html>