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
					alert('update 진입');
					$('#updateData').attr('disabled', true);
					$('#deleteData').attr('disabled', true);
			//		$('#kname').attr('readonly', true);
					$('#kno').attr('readonly', true);
				}
		 		

				$("#updateData").click(function(){
					alert('updateData 진입');
					if(!validateForm()) return;
			//		if(!callAjax()) return;
					// ajax 호출
			//		alert('kpwCheck function');
			//		callAjax();
					// ajax 끝
					if(confirm('수정을 진행할까요?')){
						$("#boardForm").attr("enctype", "multipart/form-data");
						$("#boardForm").attr("method", "POST");
						$("#boardForm").attr("action", "/board/updateBoard.kyj");
						$("#boardForm").submit();
					}
				});
				
				$("#deleteData").click(function(){
					alert('deleteData 진입');
					if(confirm('삭제를 진행할까요?')){
						$("#boardForm").attr("enctype", "multipart/form-data");
						$("#boardForm").attr("method", "POST");
						$("#boardForm").attr("action", "/board/deleteBoard.kyj");
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
		 				$('#updateData').attr('disabled', false);
		 				$('#deleteData').attr('disabled', false);
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
			// 비번확인
			 function callAjax(){
				alert("callAjax 함수");
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
					alert("whenSuccess 함수");
					console.log(data);
					var boolVal = $(data).find("resultpw").text();
					alert(" boolVal >>> : " + boolVal);
					console.log(boolVal);
					
					var result = eval(boolVal);
					alert(" result >>> : " + result);
					console.log(result);
					if(result){
						alert(" 비밀번호가 같습니다 ");
						status.kpw = true;
						$("#kpw").prop("readonly",true);
					}else{
						alert(" 비밀번호가 다릅니다");
						$("#kpw").val('');
						$("#kpw").focus();
					}
				
					}; //function data 끝
					function whenError(){
						alert("whenError 함수 ㅎㅎㅎ ");
					}
			} // call aJax function 끝
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
			 
			 function validateForm(){
					if($("#kpw_").val().replace(/\s/g,"")==""){
						alert('비밀번호를 입력해주세요.');
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
						<td colspan="2" align="center">게시판</td>
					</tr>
					<tr>
						<td align="center">글번호</td>
						<td><%= kvo.getKno() %></td>
					</tr>
					<tr>
						<td align="center">제목</td>
						<td><%= kvo.getKsubject() %></td>
					</tr>
					<tr>
						<td align="center">작성자</td>
						<td><%= kvo.getKname() %></td>
					</tr>
					<tr>
						<td align="center">이미지</td>
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
						<input type="button" id="updateData" value="수정"/>
					</td>
					<td>
						<input type="button" id="deleteData" value="삭제"/>
					</td>

				</tr>
			</table>
		</div>
	</body>
</html>