<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  request.setCharacterEncoding("EUC-KR"); %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>멤버 테이블 팝업</title>
		
		<style type="text/css">
			.required{color:red;}
			input[type="text"]{width:200px;}
			input[type="password"]{width:200px;}
		</style>
		 
		<!-- <script type="text/javascript" src="../lib/jquery-1.11.0.min.js"></script> -->
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		
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
		
		<script type="text/javascript">
			$(document).ready(function(){
				
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
				
				$("#reset").click(function(){
					alert('reset 버튼');
					$('#kid').prop('readonly',false);
					$('#kid').val("");
					$('#kpw').prop('readonly', false);
					$('#kpw').val("");
					$('#kpw_').prop('readonly', false);
					$('#kpw_').val("");
				});
				
				alert('member_pop.jsp 진입');
				var mode = "${mode}";
				if(mode=="insert"){
					alert('insert 진입');
					$('#insertData').attr('disabled', false);
					$('#updateData').attr('disabled', true);
					$('#deleteData').attr('disabled', true);
					$('#kid').attr('readonly', false);
					$('#kmem').attr('readonly', true);
				}else if(mode=="update"){
					alert('update 진입');
					$('#insertData').attr('disabled', true);
					$('#updateData').attr('disabled', false);
					$('#deleteData').attr('disabled', false);
					$('#kid').attr('readonly', true);
					$('#kmem').attr('readonly', true);
				}
				
				$("#insertData").click(function(){
					alert('insertData 진입');
					var kIdVal = $("#kid").val();
					var kPwVal = $("#kpw").val();
					var kPw_Val = $("#kpw_").val();
					var kNameVal = $("#kname").val();
					var kHpVal = $("#khp").val();
					var kBdayVal = $("#kbirth").val();
					var kEmailID = $("#kmail").val();
					var kPostVal = $("#kpostno").val();
					var kAddrVal = $("#kjuso").val();
					var kAddrVal1 = $("#kjuso1").val();
					var fileName = $("#fileName").val();
					
					if(!validateForm()) return;
					if($("#kid").val()=="") $("#kid").val(0);
					if(confirm('등록을 진행할까요?')){
						$("#memberForm").attr("enctype", "multipart/form-data");
						$("#memberForm").attr("method", "POST");
						$("#memberForm").attr("action", "/member/insertMember.kyj");
						$("#memberForm").submit();
					}
				});
				
				$("#updateData").click(function(){
					alert('updateData 진입');
					if(!validateForm()) return;
					if(confirm('수정을 진행할까요?')){
						$("#memberForm").attr("enctype", "multipart/form-data");
						$("#memberForm").attr("method", "POST");
						$("#memberForm").attr("action", "/member/updateMember.kyj");
						$("#memberForm").submit();
					}
				});
				
				$("#deleteData").click(function(){
					alert('deleteData 진입');
					if(confirm('삭제를 진행할까요?')){
						$("#memberForm").attr("enctype", "multipart/form-data");
						$("#memberForm").attr("method", "POST");
						$("#memberForm").attr("action", "/member/deleteMember.kyj");
						$("#memberForm").submit();
					}
				});
				
				$("#closeWindow").click(function(){
					alert('closeWindow 진입');
					window.close();
				});	
				
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
		 				$("#kpw").prop("readonly", true);
		 				$("#kpw_").prop("readonly", true);
		 				$("#kname").focus();
		 			}else{
		 				alert('비밀번호와 비밀번호 확인에 입력문자가 다릅니다.');
		 				console.log("비밀번호랑 비밀번호확인랑 다름.");
		 				$("#kpw").val('');
		 				$("#kpw_").val('');
		 				$("#kpw").focus();
		 			}
		 		}); // end of password check
		 		
			});
			
			function callAjax(){
				alert("callAjax 함수");
				$.ajax({					
					type : "POST",
					url  : "/member/idValueCkeck.kyj",
					contentType   : "application/x-www-form-urlencoded",
					data : {					
			 		kid   : $('#kid').val()
			 		},
					success : whenSuccess,
					error   : whenError
				});
					function whenSuccess(data){
					alert("whenSuccess 함수");
					console.log(data);
					var boolVal = $(data).find("resultid").text();
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
				
					} //function data 끝
					
					function whenError(e){
						alert("whenError 함수 ㅎㅎㅎ ");
						console.log(e);
					}
			} // call aJax function 끝
			
			function validateForm(){
				if($("#kid").val().replace(/\s/g,"")==""){
					alert('아이디를 입력해주세요.');
					return false;
				}
				if($("#kpw").val().replace(/\s/g,"")==""){
					alert('비밀번호를 입력해주세요.');
					return false;
				}
				if($("#kpw_").val().replace(/\s/g,"")==""){
					alert('비밀번호를 입력해주세요.');
					return false;
				}
				return true;
			}
			
		</script>
	</head>
	<body>
		<p></p>
		<div>
			<form id="memberForm" name="memberForm">
	<!-- 	<input type="hidden" id="memFormHidden" name="memFormHidden"/>  -->
				<table border="1">
					<thead>
						<tr>
							<td colspan="2" align="center">
								<h4>멤버 테이블 팝업창</h4>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>회원번호</th>
							<td><input type="text" id="kmem" name="kmem" value="${memberVO.kmem}"/></td>
						</tr>
						<tr>
							<th><span class="required">*</span>아이디</th>
							<td><input type="text" id="kid" name="kid" value="${memberVO.kid}"/>
							<input type="button" id="kIdCheck" value="아이디중복확인"></td>
						</tr>
						<tr>
							<th><span class="required">*</span>비밀번호</th>
							<td><input type="password" id="kpw" name="kpw" value="${memberVO.kpw}"/></td>
						</tr>
						<tr>
							<th><span class="required">*</span>비밀번호<br>확인</th>
							<td><input type="password" id="kpw_" name="kpw_"/>&nbsp;<input type="button" id="kPwCheck" value="비밀번호확인"></td>
						</tr>
						<tr>
							<th><span class="required">*</span>이름</th>
							<td><input type="text" id="kname" name="kname" value="${memberVO.kname}"/></td>
						</tr>
						<tr>
							<th>휴대폰번호</th>
							<td><input type="text" id="khp" name="khp" value="${memberVO.khp}"/></td>
						</tr>
						<tr>
							<th>생일</th>
							<td><input type="text" id="kbirth" name="kbirth" value="${memberVO.kbirth}"/></td>
						</tr>
						<tr>
							<th>이메일</th>
							<td><input type="text" id="kemail" name="kemail" value="${memberVO.kemail}"/></td>
						</tr>
						<tr>
							<th>우편번호</th>
							<td><input type="text" id="kpostno" name="kpostno" value="${memberVO.kpostno}"/>
							<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호찾기" id="kaddricon"></td>
						</tr>
						<tr>
							<th>주소</th>
							<td><input type="text" id="kjuso" name="kjuso" value="${memberVO.kjuso}"/></td>
						</tr>
						<tr>
							<th>상세주소</th>
							<td><input type="text" id="kjuso1" name="kjuso1" value="${memberVO.kjuso1}"/></td>
						</tr>
						<tr>
							<th>내사진</th>
							<td><input type="file" id="kimage" name="kimage" value="${memberVO.kimage}"/></td>
						</tr>
						<tr>
							<td colspan="3" style="border:0;"></td>
						</tr>
						
					</tbody>
				</table>
			</form>
		</div>
		<div>
		<p></p>
			<table border="0" align="center">
				<tr align="center">
					<td>
						<input type="button" id="insertData" value="등록"/>
					</td>
					<td>
						<input type="button" id="updateData" value="수정"/>
					</td>
					<td>
						<input type="button" id="deleteData" value="삭제"/>
					</td>
					<td>
						<input type="button" id="closeWindow" value="닫기"/>
					</td>
					<td>
						<input type="button" id="reset" value="리셋"/>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>