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
					alert('insertData 진입');
					var kname = $("#kname").val();
					var kpw = $("#kpw").val();
					var kpw_ = $("#kpw_").val();
					var kmemo = $("#kmemo").val();
					var fileName = $("#fileName").val();
					
					if($("#kno").val()=="") $("#kno").val(0);
					if(confirm('등록을 진행할까요?')){
						$("#boardForm").attr("enctype", "multipart/form-data");
						$("#boardForm").attr("method", "POST");
						$("#boardForm").attr("action", "/board/insertBoard.kyj");
						$("#boardForm").submit();
					}
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
		 				$('#kpw').prop('readonly', true);
		 				$('#kpw_').prop('readonly', true);
		 				$('#insertData').attr('disabled', false);
		 				$("#kmemo").focus();
		 			}else{
		 				alert("비밀번호와 비밀번호 확인에 입력문자가 다릅니다.");
		 				console.log("비밀번호랑 비밀번호확인랑 다름.");
		 				$("#kpw").val('');
		 				$("#kpw_").val('');
		 				$("#kPw").focus();
		 			}
		 		}); // 비밀번호 확인
		 });
		 
		 	// byte 체크와 길이 넘어가면 자동으로 지우는거
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
						<td colspan="2" align="center">게시판</td>
					</tr>
					<tr>
						<td align="center">제목</td>
						<td><input type="text" name="ksubject" id="ksubject"  size="20"></td>
					</tr>
					<tr>
						<td align="center">작성자</td>
						<td>
							<input type="text" name="kname" id="kname"  size="20">
							
						</td>
					</tr>
					<tr>
						<td align="center">비밀번호</td>
						<td><input type="password" name="kpw" id="kpw"  size="20"></td>
					</tr>
					<tr>
						<td align="center">비밀번호 확인</td>
						<td>
							<input type="password" name="kpw_" id="kpw_" size="20">
							<input type="button" id="kPwCheck" value="비밀번호확인">
						</td>
					</tr>
					<tr>
						<td align="center">이미지 첨부</td>
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
						<input type="button" id="insertData" value="등록"/>
					</td>
					<td>
						<input type="button" id="reset" value="리셋"/>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>