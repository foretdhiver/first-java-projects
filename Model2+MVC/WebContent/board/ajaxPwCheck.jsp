<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<!-- 
		<script type="text/javascript"
			    src="../js/jquery-1.11.0.min.js">
		</script>
		 -->
		<script type="text/javascript"
				src="https://code.jquery.com/jquery-1.11.0.min.js">
		</script>
		<script type="text/javascript">
			// 아이디 체크 클릭이벤트
			// 레디펑션
			
			var status = {
				kno : false,        //id 확인값
                kpw : false        //비밀번호 확인값
           	};
			
			$(document).ready(function(){
				alert('asdfasdf');
				$("#kPwCheck").click(function(){
					alert('kPwCheck function');
					callAjax();
				});
			});
			
			function callAjax(){
				alert("callAjax 함수");
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
					alert("whenSuccess 함수");
					console.log(data);
					var boolVal = $(data).find("result").text();
					alert(" boolVal >>> : " + boolVal);
					console.log(boolVal);
					
					var result = eval(boolVal);
					alert(" result >>> : " + result);
					console.log(result);
					if(result){
						alert(" 비밀번호가 같습니다 ");
						status.kpw = true;
						$("#kpw").prop("disabled",true);
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
					<td colspan="2" align="center"><h3>AJAX 테스트</h3></td>
				</tr>
				<tr>
					<td align="center">게시글번호</td>
					<td>&nbsp;<input type="text" id="kno" name="kno"><br>
					<font size="1">  </font></td>
				</tr>
				<tr>
					<td align="center">비밀번호</td>
					<td>&nbsp;<input type="password" id="kpw" name="kpw"><br>
					<font size="1"> *필수입력 </font></td>
				</tr>
				<tr>
					<td align="center">비밀번호확인</td>
					<td>
						&nbsp;<input type="password" name="kpw_" id="kpw_" size="20">
						<input type="button" id="kPwCheck" value="비밀번호확인">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="hidden" name ="ISUD_TYPE" id ="ISUD_TYPE">
						<input type="button" value="회원가입" id="I">
						<input type="button" value="조회" id="S">
						<input type="button" value="수정" id="U">
						<input type="button" value="탈퇴" id="D">
						<input type="button" value="전체회원보기" id="A">
						<input type="reset" value="리셋">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>