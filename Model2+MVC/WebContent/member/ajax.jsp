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
			$(document).ready(function(){
				alert('asdfasdf');
				$("#kIdCheck").click(function(){
					alert('kIdCheck function');
					callAjax();
				});
			});
			
			function callAjax(){
				alert("callAjax 함수");
				$.ajax({					
					type : "POST",
				//	url  : "/kyj/member/ajxservlet.jsp",
					url  : "/kyj/MemberControllerServlet",
					contentType   : "application/x-www-form-urlencoded",
					data : {					
			 		kid   : $('#kid').val(),
			 		memberType : "kIdCheck",
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
						alert(" 사용 할 수 있는 아이디 입니다. ");
						$("#kpw").focus();
					}else{
						alert(" 사용 할 수 없는 아이디 입니다. ");
						$("#kid").val('');
						$("#kid").focus();
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
					<td width="100" align="center">회원번호</td>
					<td width="250">
					&nbsp;<input type="text" id=knumm disabled><br>
					</td>
				</tr>
				<tr>
					<td align="center">아이디</td>
					<td>&nbsp;<input type="text" id="kid" name="kid" >
					<input type="hidden" id="idCheck" name="idCheck">
					<input type="button" id="kIdCheck" value="아이디중복확인">
					<br>
					<font size="1"> *필수입력  검색과 수정할 때 쓰입니다  </font>
					</td>
				</tr>
				<tr>
					<td align="center">비밀번호</td>
					<td>&nbsp;<input type="text" id="kpw" name="kpw"><br>
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
					<td align="center">이름</td>
					<td>&nbsp;<input type="text" id="kname" name="kname"><br>
					<font size="1"> *필수입력</font></td>
				</tr>
					<tr>
					<td align="center">휴대폰번호</td>
					<td>&nbsp;<input type="text" id="khp" name="khp"></td>
				</tr>
				<tr>
					<td align="center">생년월일</td>
					<td>&nbsp;<input type="text" id="kbirth" name="kbirth"></td>
				</tr>
				<tr>
					<td align="center">이메일</td>
					<td>
						&nbsp;<input type="text" id="kmail" name="kmail" size="20">
						<select name='kEmailAdd' id='kEmailAdd'>
							<option value="">직접입력</option>
							<option value="@naver.com">@naver.com</option>
							<option value="@gmail.com">@gmail.com</option>
							<option value="@hanmail.com">@hanmail.com</option>
							<option value="@nate.com">@nate.com</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">우편번호</td>
					<td>&nbsp;<input type="text" id="kpostno" name="kpostno"> <input type="button" onclick="sample4_execDaumPostcode()" value="우편번호찾기" id="kaddricon"></td>
				</tr>
				<tr>
					<td align="center">주소</td> 
					<td>&nbsp;<input type="text" id="kjuso" name="kjuso">
					<br>&nbsp;<input type="text" id="kjuso1" name="kjuso1"><font size="1"> *상세주소입력 </font>
					<span id="guide" style="color:#999"></span>
					</td>
				</tr>
				<tr>
					<td align="center">삭제여부</td>
					<td>&nbsp;<input type="text" id="kdeleteyn" disabled><br>
				</tr>
				<tr>
					<td align="center">입력일</td>
					<td>&nbsp;<input type="text" id="kinsertdate" disabled><br>
				</tr>
				<tr>
					<td align="center">수정일</td>
					<td>&nbsp;<input type="text" id="kupdatedate" disabled><br>
				</tr>
				<tr>
					<td align="center">프로필 사진</td>
					<td><input type="file" name="fileName1" id="fileName1"></td>
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