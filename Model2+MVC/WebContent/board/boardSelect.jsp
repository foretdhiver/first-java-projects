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
		 
		 $(document).ready(function(){
		 		alert('�� html���������� function�� ���ư��� �ִ�.');
	
		 		$("#I").click(function(){
		 			alert('I ����');
		 			console.log("I ����");
		 			
		 			var kSubjectVal = $("#ksubject").val();
		 			var kNameVal = $("#kname").val();
		 			var kPwVal = $("#kpw").val();
		 			var kpw_Val = $("#kpw_").val();
		 			var fileName1 = $("#fileName1").val();
		 			var kmemo = $("#kmemo").val();
		 			
		 			if(!kSubjectVal){
			 			alert("�������� �Է��ϼ���!");
			 			$("#kSubject").focus();
			 			return false;
			 		}
		 			if(!kNameVal){
			 			alert("�۾��̸� �Է��ϼ���!");
			 			$("#kname").focus();
			 			return false;
			 		}
		 			if(!kPwVal){
			 			alert("��й�ȣ�� �Է��ϼ���!");
			 			$("#kpw").focus();
			 			return false;
			 		}
		 			if(!kpw_Val){
			 			alert("��й�ȣȮ���� �Է��ϼ���!");
			 			$("#kpw_").focus();
			 			return false;
			 		}
		 			alert("�Էµ� ���� >> : "+kSubjectVal+" "+kNameVal+" "+kPwVal+" "+kpw_Val+" "+fileName1+" "+kmemo);
		 			console.log("�Էµ� ���� >> : "+kSubjectVal+" "+kNameVal+" "+kPwVal+" "+kpw_Val+" "+fileName1+" "+kmemo);
		 			alert("�����Ͱ� �� ����");
		 			$("#ISUD_TYPE").attr("value", "I");
		 			$("#boardForm").attr("action", "/kyj/HelloBoardControllerServlet").submit();
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
		 		
		 		// ���̵� üũ Ŭ���̺�Ʈ
		 		$("#kIdCheck").click(function(){
		 			// alert("kIdCheck ����");
		 			console.log("kIdCheck ����");
		 			var kIdVal = $("#kname").val();
		 			// alert(kIdVal);
		 			console.log(kIdVal);
		 			
		 			if(!kIdVal){
			 			alert("�۾��̸� �Է��ϼ���!");
			 			$("#kname").focus();
			 			return false;
			 		}
		 			// ���̵� �ߺ� �˻�
		 			var url="/kyj/member/ajxservlet.jsp";
		 			$.get(url, { "kname" : kNameVal },
		 					function(data){
		 					// alert(data);
		 					console.log(data);
		 					var boolVal = $(data).find("result").text();
		 					// alert(" boolVal >>> : " + boolVal);
		 					console.log(boolVal);
		 					
		 					var result = eval(boolVal);
		 					// alert(" result >>> : " + result);
		 					console.log(result);
		 					if(result){
		 						alert(" ��� �� �� �ִ� ���̵� �Դϴ�. ");
		 						$("#kpw").focus();
		 					}else{
		 						alert(" ��� �� �� ���� ���̵� �Դϴ�. ");
		 						$("#kname").val('');
		 						$("#kname").focus();
		 					}
		 			});
		 			
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
		 				$("#kmemo").focus();
		 			}else{
		 				alert("��й�ȣ�� ��й�ȣ Ȯ�ο� �Է¹��ڰ� �ٸ��ϴ�.");
		 				console.log("��й�ȣ�� ��й�ȣȮ�ζ� �ٸ�.");
		 				$("#kpw").val('');
		 				$("#kpw_").val('');
		 				$("#kPw").focus();
		 			}
		 		}); // ��й�ȣ Ȯ��
	
		 		$("#A").click(function(){
		 			alert("S function ����");
		 			$("#ISUD_TYPE").attr("value", "A");
		 			$("#boardForm").attr('action', '/kyj/HelloBoardControllerServlet').submit();
		 		});
		 		
		 		$("#USELECT").click(function(){
		 			alert("U function ����");
		 			$("#ISUD_TYPE").attr("value", "USELECT");
		 			$("#boardForm").attr('action', '/kyj/board/boardUpdate.jsp').submit();
		 		});
		 		
		 		$("#D").click(function(){
		 			alert("D function ����");
		 			$("#ISUD_TYPE").attr("value", "D");
		 			$("#boardForm").attr('action', '/kyj/HelloBoardControllerServlet').submit();
		 		});
		 });
		 
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
						<td><input type="text" value="<%= kvo.getKsubject()%>" name="ksubject" id="ksubject" size="20" readonly></td>
					</tr>
					<tr>
						<td align="center">�۾���</td>
						<td>
							<input type="text" value="<%= kvo.getKname()%>" name="kname" id="kname" size="20" readonly>
						</td>
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
						<td align="center" colspan="2"> 
						<center><img src="/<%=kvo.getKimage() %>" alt="�̹����� �����ϴ�" border=0 width="300px" height=300px"></center>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<textarea name="kmemo" id="kmemo"  rows="25" cols="60" class="js-sms-content" readonly><%= kvo.getKmemo()%></textarea>
							<div class="bytes-wrapper">
								<span class="bytes" >0</span>bytes 
							</div>
							
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="hidden" name ="ISUD_TYPE" id ="ISUD_TYPE">
						<!--<input type="button" class="js-send-sms" value="����" id="SendForm"> -->
							<input type="button" value="�۸��" id="A">
						<!--<input type="button" value="�ۼ���" id="USELECT">						-->
						<!--<input type="button" value="�ۻ���" id="D">							-->
						<!--<input type="reset" value="����">  									-->
						</td>
					</tr>
			<%} 
			}%>
				</table>
			</div>
		</form>
	</body>
</html>