<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%  request.setCharacterEncoding("EUC-KR"); %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertKyjMember.jsp</title>
		<!-- 
		<script type="text/javascript"
			    src="../js/jquery-1.11.0.min.js">
		</script>
		 -->
		<script type="text/javascript"
				src="https://code.jquery.com/jquery-1.11.0.min.js">
		</script>
		
		
		<%--아래는 다음 주소 api 입니다 --%>
		<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
		<script>
		    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
		    function sample4_execDaumPostcode() {
		        new daum.Postcode({
		            oncomplete: function(data) {
		                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
		
		                // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
		                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
		                var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
		                var extraRoadAddr = ''; // 도로명 조합형 주소 변수
		
		                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
		                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
		                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                    extraRoadAddr += data.bname;
		                }
		                // 건물명이 있고, 공동주택일 경우 추가한다.
		                if(data.buildingName !== '' && data.apartment === 'Y'){
		                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                }
		                // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
		                if(extraRoadAddr !== ''){
		                    extraRoadAddr = ' (' + extraRoadAddr + ')';
		                }
		                // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
		                if(fullRoadAddr !== ''){
		                    fullRoadAddr += extraRoadAddr;
		                }
		
		                // 우편번호와 주소 정보를 해당 필드에 넣는다.
		                document.getElementById('kpostno').value = data.zonecode; //5자리 새우편번호 사용
		                document.getElementById('kjuso').value = fullRoadAddr;
		                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
		                if(data.autoRoadAddress) {
		                    //예상되는 도로명 주소에 조합형 주소를 추가한다.
		                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
		                    document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
		
		                } else {
		                    document.getElementById('guide').innerHTML = '';
		                }
		            }
		        }).open();
		    }
		</script>	
	
		
		
		<%-- 회원가입 함수입니다. --%>
		<script type="text/javascript">
			$(document).ready(function(){
				alert("회원가입 페이지입니다");
				
				$("#reset").click(function(){
					$('#kid').prop('readonly', false);
				});
				

				
				$("#kIdCheck").click(function(){
					var id = $("#kid").val();
					var idlength = id.length;
					alert(idlength);
					alert("id >> " + id);
					if(idlength==0){
						alert('아이디를 제대로 입력하세요');
						$("#kid").focus();
						return false;
					}else{
						alert('kIdCheck function');
						callAjax();
					}
		 			
					
										
				});
				
				// delete
				$("#D").click(function(){
					alert("delete!");
					$("#ISUD_TYPE").attr("value", "D");
					$("#memberForm")
					//http://localhost:8088/kyj/MemberControllerServlet
					.attr("action", "/kyj/MemberControllerServlet")
					.submit();
				});
				
				// update
				$("#U").click(function(){
					alert("update!");
					$("#ISUD_TYPE").attr("value", "U");
					$("#memberForm")
					//http://localhost:8088/kyj/MemberControllerServlet
					.attr("action", "/kyj/MemberControllerServlet")
					.submit();
				});
				
				// select
				$("#S").click(function(){
					alert("select ");
					$("#ISUD_TYPE").attr("value", "S");
					$("#memberForm")
					//http://localhost:8088/kyj/MemberControllerServlet
					.attr("action", "/kyj/MemberControllerServlet")
					.submit();
				});
				
				// all select
				$("#A").click(function(){
					alert("select all!");
					$("#ISUD_TYPE").attr("value", "A");
					$("#memberForm")
					//http://localhost:8088/kyj/MemberControllerServlet
					.attr("action", "/kyj/MemberControllerServlet")
					.submit();
				});
				
				// insert
				$("#I").click(function(){
					alert("insert!");

					var kIdVal = $("#kid").val();
					var kPwVal = $("#kpw").val();
					var kPw_Val = $("#kpw_").val();
					var kNameVal = $("#kname").val();
					var kHpVal = $("#khp").val();
					var kBdayVal = $("#kbirth").val();
					var kEmailID = $("#kmail").val();
					var kEmailVal = $("#kEmailAdd").val();
					var kPostVal = $("#kpostno").val();
					var kAddrVal = $("#kjuso").val();
					var kAddrVal1 = $("#kjuso1").val();
					var fileName1 = $("#fileName1").val();
					
					console.log(kEmailID +", "+ kEmailVal + ", " + fileName1);
					
					if(!kIdVal){
			 			alert("아이디를 입력하세요!");
			 			$("#kid").focus();
			 			return false;
			 		}
		 			if(!kPwVal){
			 			alert("비밀번호를 입력하세요!");
			 			$("#kpw").focus();
			 			return false;
			 		}
		 			if(!kPw_Val){
			 			alert("비밀번호확인을 입력하세요!");
			 			$("#kpw_").focus();
			 			return false;
			 		}
		 				 			
					alert("잠깐만~");
					$("#ISUD_TYPE").attr("value", "I");
					$("#memberForm")
					//http://localhost:8088/kyj/MemberControllerServlet
					.attr("action", "/kyj/MemberControllerServlet")
					.submit();
				}); //회원가입
			
		 		// password check
		 		$("#kPwCheck").click(function(){
		 			var kPwVal = $("#kpw").val();
		 			var kPw_Val = $("#kpw_").val();
		 			// alert(kPwVal + " : " + kPw_Val);
		 			console.log(kPwVal + " : " + kPw_Val);
		 			
		 			// 입력여부검사
		 			if(!kPwVal){
		 			//	alert('if >>> : ' + kPwVal);
		 				console.log('if >>> : ' + kPwVal);
		 				alert("비밀번호를 입력하세요.");
		 				$("#kpw").focus();
		 				return false;
		 			}else{
		 			//	alert('else >>> : ' + kPwVal);
		 				console.log('else >>> : ' + kPwVal);
		 			//	alert("비밀번호 입력이 잘되어있음. (확인용)");
		 				console.log("비밀번호 입력이 잘되어있음.");
		 			}
		 			
		 			if(!kPw_Val){
		 				alert("비밀번호 확인을 입력하세요!!!");
		 				$("#kpw_").focus();
		 				return false;
		 			}else{
		 			//	alert('else >>> : ' + kPw_Val);
		 				console.log('else >>> : ' + kPw_Val);
		 			//	alert("비밀번호확인 입력이 잘되어있음. (확인용)");
		 				console.log("비밀번호확인 입력이 잘되어있음.");
		 			}
		 			
		 			if(kPwVal==kPw_Val){
		 				alert('비밀번호랑 비밀번호확인랑 같음.');
		 				console.log("확인 완료");
		 				$("#kPw").prop("readonly", true);
		 				$("#kPw_").prop("readonly", true);
		 				$("#kname").focus();
		 			}else{
		 				alert('비밀번호와 비밀번호 확인에 입력문자가 다릅니다.');
		 				console.log("비밀번호랑 비밀번호확인랑 다름.");
		 				$("#kpw").val('');
		 				$("#kpw_").val('');
		 				$("#kpw").focus();
		 			}
		 		}); // end of password check
			}); // end of document.ready(function())
			
			
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
						$("#kid").prop("readonly",true);
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
					<td colspan="2" align="center">회원가입</td>
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
						<input type="reset" id="reset" value="리셋">
					</td>
				</tr>
			</table>
		</form>
		
		<!--
		<form name="imageForm" 
		 	  id="imageForm" 
			  method="POST"
			  enctype="multipart/form-data">
				<table border="1" align="center">
				<tr>
					<td align="center">프로필 사진</td>
					<td><input type="file" name="fileName1" id="fileName1"></td>
				</tr>
				</table>
		</form>
		-->
	</body>
</html>