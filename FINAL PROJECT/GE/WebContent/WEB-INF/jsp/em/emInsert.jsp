<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmPrInfoVO" %>    
<%@ page import="com.spring.ge.vo.CommonVO" %>    
<%@ page import="java.util.ArrayList" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>사원등록</title>
		
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
		                document.getElementById('emprpostno').value = data.zonecode; //5자리 새우편번호 사용
		                document.getElementById('empradd').value = fullRoadAddr;
		                document.getElementById('sample4_jibunAddress').value = data.jibunAddress;
		
		                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
		                if(data.autoRoadAddress) {
		                    //예상되는 도로명 주소에 조합형 주소를 추가한다.
		                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
		                    document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
		
		                } else if(data.autoJibunAddress) {
		                    var expJibunAddr = data.autoJibunAddress;
		                    document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
		
		                } else {
		                    document.getElementById('guide').innerHTML = '';
		                }
		            }
		        }).open();
		    }
		</script>
		<script type="text/javascript"
				src="https://code.jquery.com/jquery-1.11.0.min.js">
		</script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<script type="text/javascript">
			$(document).ready(function(){
				$("#insert").click(function(){
					alert("insert 함수");
					$("#insertForm").attr("action", "/em/emInsert.ge");						
					$("#insertForm").submit();
				});
				$("#update").click(function(){
					alert("update 함수");
					$("#insertForm").attr("action", "/em/emUpdatest.ge");						
					$("#insertForm").submit();
				});
				$("#delete").click(function(){
					alert("delete 함수");
					$("#insertForm").attr("action", "/em/emDelete.ge");						
					$("#insertForm").submit();
				});
			});
			$(function(){
				$("#emhiredate").datepicker({
					dateFormat: 'yymmdd',
				    prevText: '이전 달',
				    nextText: '다음 달',
				    monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				    dayNames: ['일','월','화','수','목','금','토'],
				    dayNamesShort: ['일','월','화','수','목','금','토'],
				    dayNamesMin: ['일','월','화','수','목','금','토'],
				    showMonthAfterYear: true,
				    changeMonth: true,
				    changeYear: true,
				    yearSuffix: '년'
				});
			});
		</script>
	</head>
	<body>
		<form name="insertForm"
			  id = "insertForm"
			  method = "POST"
			  enctype = "multipart/form-data">
			<table border = "1" align = "center">
				<tr>
					<td colspan="2" align="center">
						<center>사원등록</center>
					</td>
				</tr>
				<tr>
					<td width="200" align="center">사번</td>
					<td width="500">
						<input type="text" name="emno" id="emno" readonly>
					</td>
				</tr>
				<tr>
					<td align="center">한글이름</td>
					<td><input type="text" name="emname" id="emname"></td>
				</tr>
				<tr>
					<td align="center">아이디</td>
					<td>
						<input type="text" name="emid" id="emid">
						<input type="button" name="idCheck" id="idCheck" value="중복체크">
					</td>
				</tr>
				<tr>
					<td align="center">비밀번호</td>
					<td><input type="text" name="empw" id="empw"></td>
				</tr>
				<tr>
					<td align="center">입사일</td>
					<td><input type="text" name="emhiredate" id="emhiredate"></td>
				</tr>
				<tr>
					<td align="center">부서</td>
					<td>
						<select name="deptcd" id="deptcd">
							<option value="20">관리부</option>
							<option value="30">영업지원부</option>
							<option value="40">영업부</option>
							<option value="50">기술지원부</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">직위</td>
					<td>
						<select name="jobcd" id="jobcd">
								<option value="07">사원</option>
								<option value="06">대리</option>
								<option value="05">과장</option>
								<option value="04">차장</option>
								<option value="03">부장</option>
						</select>
					</td>	
				</tr>
				<tr>
					<td align="center">이메일</td>
					<td>
						<input type="text" name="ememailid" id="ememailid">
						@
						<select name="ememailadd" id="ememailadd">
								<option value="naver.com">naver.com</option>
								<option value="gmail.com">gmail.com</option>
								<option value="hanmail.net">hanmail.net</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">핸드폰번호</td>
					<td>
						<select name="emfhp" id="emfhp">
							<option value="010" selected="selected">010</option>
							<option value="011">011</option>
						</select>
						-
						<input type="text" name="emshp" id="emshp">
						-
						<input type="text" name="emlhp" id="emlhp">
					</td>
				</tr>
				<tr>
					<td align="center">내선번호</td>
					<td><input type="text" name="emexno" id="emexno"></td>
				</tr>
				<tr>
					<td align="center">직통번호</td>
					<td>
						<select name="emfdino" id="emfdino">
							<option value="02" selected="selected">02</option>
							<option value="031">031</option>
							<option value="032">032</option>
						</select>
						-
						<input type="text" name="emsdino" id="emsdino">
						-
						<input type="text" name="emldino" id="emldino">
					</td>
				</tr>
				<tr>
					<td align="center">서명</td>
					<td>
						<input type="file" name="emsign" id="emsign">
						<input type="reset" value="다시" id="re">	
					
					</td>
				</tr>
				<tr>
					<td align="center">생년월일</td>
					<td><input type="text" name="emprbirth" id="emprbirth"></td>
				</tr>
				<tr>
					<td align="center">성별</td>
					<td>
						<select name="emprsex" id="emprsex">
								<option value="01">여성</option>
								<option value="02">남성</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">혼인여부</td>
					<td>
						<select name="emprmarital" id="emprmarital">
								<option value="61">미혼</option>
								<option value="62">기혼</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">우편번호</td>
					<td>
						<input type="text" name="emprpostno" id="emprpostno">
						<input type="button" name="noSearch" id="noSearch" value="우편번호 찾기" onclick="sample4_execDaumPostcode()">
					</td>
				</tr>
				<tr>
					<td align="center">주소</td>
					<td>
						<input type="text" name="empradd" id="empradd">
					</td>
				</tr>
				<tr>
					<td align="center">상세주소</td>
					<td>
						<input type="text" name="emprdetailadd" id="emprdetailadd">
					</td>
				</tr>
				<tr>
					<td align="center">학력</td>
					<td>
						<select name="empreducd" id="empreducd">		
							<option value="31">고졸</option>
							<option value="32">초대졸</option>
							<option value="33">대졸</option>
							<option value="34">석사졸</option>
							<option value="35">박사졸</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">최종학교명</td>
					<td><input type="text" name="empreduname" id="empreduname"></td>
				</tr>
				<tr>
					<td align="center">사진</td>
					<td>
						<input type="file" name="empic" id="empic">
						<input type="reset" value="다시" id="re">					
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="입력" id="insert" name="insert">
						<input type="button" value="목록" id="select" name="select">
						<input type="reset" value="다시">
					</td>
				</tr>
			</table>
		</form>	
	</body>
</html>