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
		 		alert('이 html페이지에는 function이 돌아가고 있다.');
	
		 		$("#I").click(function(){
		 			alert('I 진입');
		 			console.log("I 진입");
		 			
		 			var kSubjectVal = $("#ksubject").val();
		 			var kNameVal = $("#kname").val();
		 			var kPwVal = $("#kpw").val();
		 			var kpw_Val = $("#kpw_").val();
		 			var fileName1 = $("#fileName1").val();
		 			var kmemo = $("#kmemo").val();
		 			
		 			if(!kSubjectVal){
			 			alert("글제목을 입력하세요!");
			 			$("#kSubject").focus();
			 			return false;
			 		}
		 			if(!kNameVal){
			 			alert("글쓴이를 입력하세요!");
			 			$("#kname").focus();
			 			return false;
			 		}
		 			if(!kPwVal){
			 			alert("비밀번호를 입력하세요!");
			 			$("#kpw").focus();
			 			return false;
			 		}
		 			if(!kpw_Val){
			 			alert("비밀번호확인을 입력하세요!");
			 			$("#kpw_").focus();
			 			return false;
			 		}
		 			alert("입력된 정보 >> : "+kSubjectVal+" "+kNameVal+" "+kPwVal+" "+kpw_Val+" "+fileName1+" "+kmemo);
		 			console.log("입력된 정보 >> : "+kSubjectVal+" "+kNameVal+" "+kPwVal+" "+kpw_Val+" "+fileName1+" "+kmemo);
		 			alert("데이터가 잘 있음");
		 			$("#ISUD_TYPE").attr("value", "I");
		 			$("#boardForm").attr("action", "/kyj/HelloBoardControllerServlet").submit();
		 		});
		 		
		 		
		 		// byte 수 제한 함수
		 		$('.js-sms-content').keyup(function(){
		 			cut_600(this);
		 		});
		 		$('.js-send-sms').click(function(){
		 			if(getTextLength($('.js-sms-content').val()) > 600) {
		 				alert("문자는 600바이트 이하로 적어주세요.");
		 				return false;
		 			}else{
		 				alert("문자가 600바이트 이하입니다.");
		 			}
		 		});
		 		
		 		// 아이디 체크 클릭이벤트
		 		$("#kIdCheck").click(function(){
		 			// alert("kIdCheck 시작");
		 			console.log("kIdCheck 시작");
		 			var kIdVal = $("#kname").val();
		 			// alert(kIdVal);
		 			console.log(kIdVal);
		 			
		 			if(!kIdVal){
			 			alert("글쓴이를 입력하세요!");
			 			$("#kname").focus();
			 			return false;
			 		}
		 			// 아이디 중복 검사
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
		 						alert(" 사용 할 수 있는 아이디 입니다. ");
		 						$("#kpw").focus();
		 					}else{
		 						alert(" 사용 할 수 없는 아이디 입니다. ");
		 						$("#kname").val('');
		 						$("#kname").focus();
		 					}
		 			});
		 			
		 		});
		 		
		 		// 비밀번호체크
		 		$("#kPwCheck").click(function(){
		 			alert("kPwCheck function");
		 			var kPwVal = $("#kpw").val();
		 			var kpw_Val = $("#kpw_").val();
		 			// alert(kPwVal + " : " + kpw_Val);
		 			console.log(kPwVal + " : " + kpw_Val);
		 			
		 			// 입력여부검사
		 			if(!kPwVal){
		 			//	alert('if >>> : ' + kPwVal);
		 				console.log('if >>> : ' + kPwVal);
		 				alert("비밀번호를 입력하세요.");
		 				$("#kPwVal").focus();
		 				return false;
		 			}else{
		 			//	alert('else >>> : ' + kPwVal);
		 				console.log('else >>> : ' + kPwVal);
		 			//	alert("비밀번호 입력이 잘되어있음. (확인용)");
		 				console.log("비밀번호 입력이 잘되어있음.");
		 			}
		 			
		 			if(!kpw_Val){
		 				alert("비밀번호 확인을 입력하세요.");
		 				$("#kpw_Val").focus();
		 				return false;
		 			}else{
		 			//	alert('else >>> : ' + kpw_Val);
		 				console.log('else >>> : ' + kpw_Val);
		 			//	alert("비밀번호확인 입력이 잘되어있음. (확인용)");
		 				console.log("비밀번호확인 입력이 잘되어있음.");
		 			}
		 			
		 			if(kPwVal==kpw_Val){
		 				alert("비밀번호랑 비밀번호확인랑 같음.");
		 				console.log("확인 완료");
		 				$("#kmemo").focus();
		 			}else{
		 				alert("비밀번호와 비밀번호 확인에 입력문자가 다릅니다.");
		 				console.log("비밀번호랑 비밀번호확인랑 다름.");
		 				$("#kpw").val('');
		 				$("#kpw_").val('');
		 				$("#kPw").focus();
		 			}
		 		}); // 비밀번호 확인
	
		 		$("#A").click(function(){
		 			alert("S function 진입");
		 			$("#ISUD_TYPE").attr("value", "A");
		 			$("#boardForm").attr('action', '/kyj/HelloBoardControllerServlet').submit();
		 		});
		 		
		 		$("#USELECT").click(function(){
		 			alert("U function 진입");
		 			$("#ISUD_TYPE").attr("value", "USELECT");
		 			$("#boardForm").attr('action', '/kyj/board/boardUpdate.jsp').submit();
		 		});
		 		
		 		$("#D").click(function(){
		 			alert("D function 진입");
		 			$("#ISUD_TYPE").attr("value", "D");
		 			$("#boardForm").attr('action', '/kyj/HelloBoardControllerServlet').submit();
		 		});
		 });
		 
		 	/// byte 체크와 길이 넘어가면 자동으로 지우는거
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
						<td colspan="2" align="center"> 게시판</td>
					</tr>
					<tr>
						<td align="center">글번호</td>
						<td><input type="text" value="<%= kvo.getKno()%>" name="kno" id="kno" size="20" readonly></td>
					</tr>
					<tr>
						<td align="center">글제목</td>
						<td><input type="text" value="<%= kvo.getKsubject()%>" name="ksubject" id="ksubject" size="20" readonly></td>
					</tr>
					<tr>
						<td align="center">글쓴이</td>
						<td>
							<input type="text" value="<%= kvo.getKname()%>" name="kname" id="kname" size="20" readonly>
						</td>
					</tr>
					
					<tr>
						<td align="center">입력일</td>
						<td><input type="text" value="<%= kvo.getKinsertdate()%>" name="kinsertdate" id="kinsertdate" size="20" readonly></td>
					</tr>
					<tr>
						<td align="center">수정일</td>
						<td><input type="text" value="<%= kvo.getKupdatedate()%>" name="kupdatedate" id="kupdatedate" size="20" readonly></td>
					</tr>
					<tr>
						<td align="center" colspan="2"> 
						<center><img src="/<%=kvo.getKimage() %>" alt="이미지가 없습니다" border=0 width="300px" height=300px"></center>
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
						<!--<input type="button" class="js-send-sms" value="전송" id="SendForm"> -->
							<input type="button" value="글목록" id="A">
						<!--<input type="button" value="글수정" id="USELECT">						-->
						<!--<input type="button" value="글삭제" id="D">							-->
						<!--<input type="reset" value="리셋">  									-->
						</td>
					</tr>
			<%} 
			}%>
				</table>
			</div>
		</form>
	</body>
</html>