<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmPrInfoVO" %>    
<%@ page import="com.spring.ge.vo.EmInfoVO" %>
<%@ page import="com.spring.ge.vo.CommonVO" %>    
<%@ page import="java.util.ArrayList" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
<%
/* 사용자 정보 */
Object obj1 = session.getAttribute("eminfo");
EmInfoVO emvo = null;
emvo = new EmInfoVO();
emvo = (EmInfoVO)obj1;

Object obj2 = request.getAttribute("sList");
ArrayList<EmPrInfoVO> sList = null;
sList = (ArrayList<EmPrInfoVO>)obj2;
int sListSize = sList.size();
System.out.println("(log) sListSize >>> : " + sListSize);

/* 상세정보란이기 때문에 페이징 처리 X */
%>	
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>사원등록</title>
		<link rel="stylesheet" type="text/css" href="/css/emDetail.css" />
		<script type="text/javascript" src="/js/clock.js"></script>
		<script type="text/javascript"
				src="https://code.jquery.com/jquery-1.11.0.min.js">
		</script>
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
		<script type="text/javascript">
			$(document).ready(function(){
				$('.mainframe .loader').hide();
				
				setInterval("dpTime()",0);
				
				$('#logout').click(function(){
					if(confirm('로그아웃 하시겠습니까?')){
						loading();
						location.href='/ea/geLogOut.ge';
					}else{
					}
				});
				
				$("#update").click(function(){
					//alert("update 함수");
					$("#detailForm").attr("action", "/em/emUpdate.ge");						
					$("#detailForm").submit();
				});
				$("#delete").click(function(){
					//alert("delete 함수");
					$("#detailForm").attr("action", "/em/emDelete.ge");						
					$("#detailForm").submit();
				});
				$("#ctInsert").click(function(){
					//alert("ctInsert 함수");
					$("#detailForm").attr("action", "/em/ctInsert.ge");						
					$("#detailForm").submit();
				});
			});	// end of document.ready
			
			function loading(){
				setTimeout($('.mainframe .loader').show(), 1000);
			}
		</script>
	</head>
	<body>
		<div id="ge_menudiv">
			<div class="topinfo">
				<div class="myname"><%=emvo.getEmname()%> 님 안녕하세요.</div>
				<div class="logout" id="logout">logout</div>
			</div>	<!-- end of div-topinfo -->
		</div>	<!-- end of div-ge_menudiv -->
		<div class="ge_maindiv">
			<div class="topmenu">
				<div class="p">
					<a href="/board/bNList.ge">공지사항</a>｜
					<a href="/board/calendar.ge">일정관리</a>｜
					<a href="/em/emAllSelect.ge" style="color:#615F8D">사원관리</a>｜
					<a href="/em/ctAllSelect.ge">근태관리</a>｜
					<a href="#">교육사항</a>｜
					<a href="#">인사평가</a>
				</div>	<!-- end of div-p -->
			</div>	<!-- end of div-topmenu -->
			<div class="topdiv">
			</div>	<!-- end of div-topdiv -->
			<div class="outertable">
				<div id="scroll" style="position:absolute;">
					<div class="ge_sidemenu">
						<div class="ge_myinfo">
							<div class="imgbox">
                    		 	<img class="myimg" src="/em_Image/<%=emvo.getEmpic()%>" />
                     		</div>
                     		<br>
							<div class="itsme"><%=emvo.getEmname()%><br>(<%=emvo.getDeptname() %>/<%=emvo.getJobname() %>)</div>
						</div>	<!-- end of div-ge_myinfo -->
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/em/insertForm.ge">사원등록</a><br>
						</div>
						
						<div class="clock" style="border:1px solid #cccccc; border-radius:1px; background:#D8D8D8; float:left; margin:10px 0 10px 0px; padding:5px 0 5px 0">
						<div align="center" style="font-size:20pt ;color:white; "class="dpTime" id="dpTime"></div>
						</div>	
					</div>	<!-- end of div-ge_sidemenu -->
				</div>	<!-- end of div-scroll -->
				
				<div class="mainframe">
<%
if(sList.size()>0){
	for(int i=0; i<sList.size(); i++){
		EmPrInfoVO epvo = (EmPrInfoVO)sList.get(i);		
%>						
<!--고쳐야돼-->		<form id="detailForm"
						  name="detailForm"
						  method = "POST"
						  enctype = "multipart/form-data">
<!--form태그-->			<input type="hidden" name="emno" id="emno" value="<%=epvo.getEmno()%>">
<!--form태그-->			<input type="hidden" name="emname" id="emname" value="<%=epvo.getEmname()%>">
<!--form태그-->			<input type="hidden" name="deptcd" id="deptcd" value="<%=epvo.getDeptcd()%>">
<!--form태그-->			<input type="hidden" name="jobcd" id="jobcd" value="<%=epvo.getJobcd()%>">
						<div class="tit"><div class="title">&nbsp;사원상세정보</div></div>
						<div class="formsearch">
					    </div>
						<div id="tableContainer">
							<table class="ct_List">
								<tr>
									<td colspan="4" align="center">
										<center>상세정보</center>
									</td>
								</tr>
								<tr>
									<td rowspan="7" colspan="2" align="center">
										<img src="/em_Image/<%=epvo.getEmpic() %>" width="130" height="170">
									</td>
									<td align="center">사번</td>
									<td align="center"><%=epvo.getEmno()%></td>
								<tr>
								<tr>
									<td align="center">이름</td>
									<td colspan="2" align="center"><%=epvo.getEmname()%></td>
								</tr>
								<tr>
									<td align="center">생년월일</td>
									<td colspan="2" align="center">
										<%=epvo.getEmprbirth().substring(0, 4)%>-<%=epvo.getEmprbirth().substring(4, 6)%>-<%=epvo.getEmprbirth().substring(6) %>
									</td>
								</tr>
								<tr>
									<td align="center">부서</td>
									<td colspan="2" align="center"><%=epvo.getDeptname() %></td>
								</tr>
								<tr>
									<td align="center">직위</td>
									<td colspan="2" align="center"><%=epvo.getJobname() %></td>
								</tr>
								<tr>
									<td align="center">입사일</td>
									<td colspan="2" align="center">
										<%=epvo.getEmhiredate().substring(0, 4)%>-<%=epvo.getEmhiredate().substring(4, 6)%>-<%=epvo.getEmhiredate().substring(6)%>
									</td>
								</tr>
								<tr>
									<td align="center">내선번호</td>
									<td align="center"><%=epvo.getEmexno() %></td>
									<td align="center">직통번호</td>
									<td align="center">
										<%=epvo.getEmdino().substring(0, 2)%>-<%=epvo.getEmdino().substring(2, 5)%>-<%=epvo.getEmdino().substring(5)%>
									</td>
								</tr>
								<tr>
									<td colspan="2"  align="center">핸드폰번호</td>
									<td colspan="2" align="center">
										<%=epvo.getEmhp().substring(0, 3)%>-<%=epvo.getEmhp().substring(3, 7)%>-<%=epvo.getEmhp().substring(7)%>
									</td>
								</tr>
								<tr>
									<td colspan="2"  align="center">이메일주소</td>
									<td colspan="2" align="center"><%=epvo.getEmemail() %></td>
								</tr>
								<tr>
									<td  align="center">학력</td>
									<td align="center">
<%					
						if(epvo.getEmpreducd().equals("31")){
%>							
							고졸
<%
						}
						if(epvo.getEmpreducd().equals("32")){
%>
							초대졸
<%							
						}
						if(epvo.getEmpreducd().equals("33")){
%>
							대졸
<%							
						}
						if(epvo.getEmpreducd().equals("34")){
%>
							석사졸
<%							
						}
						if(epvo.getEmpreducd().equals("35")){
%>
							초대졸
<%							
						}
%>										
									</td>
									<td  align="center">최종학교명</td>
									<td align="center"><%=epvo.getEmpreduname() %></td>
								</tr>
								<tr>
									<td rowspan="4"  align="center">주소</td>
									<td colspan="3" style="align:left; padding:2px 20px;"><%=epvo.getEmprpostno() %></td>
								<tr>
								<tr>
									<td colspan="3" style="alian:left; padding:2px 20px;"><%=epvo.getEmpradd() %></td>
								</tr>
								<tr>
									<td colspan="3" style="alian:left; padding:2px 20px;"><%=epvo.getEmprdetailadd() %></td>
								</tr>
								<tr>
									<td colspan="4" align="center" style="padding:5px 10px 0px 2px;">
										<input type="button" class="button1" value="근태등록" id="ctInsert" name="ctInsert">
										<input type="button" class="button1" value="수정" id="update" name="update">
										<input type="button" class="button1" value="퇴사" id="delete" name="delete">
									</td>								
								</tr>
							</table>	<!-- end of table-ctlist -->
						</div>	<!-- end of div-tableContainer -->
					</form>	<!-- end of detailForm -->
<%
	}	// end of for
}	// end of if(sList.size()>0)
%>					
				</div>	<!-- end of div-mainframe -->
			</div>	<!-- end of div-outertable -->
			<div class="bottom">
				<div class="bottominfo">
				GE Project ｜ 서울시 서초구 강남대로 459 (서초동, 백암빌딩)<br>
				TEL : 02-1234-5678 / FAX : 02-1234-5678<br>
				Copyright ⓒ GE Project. All Rights Reserved.
				</div>	<!-- end of div-bottominfo -->
			</div>	<!-- end of div-bottom -->
		</div>	<!-- end of div-"ge_maindiv" -->
	</body>
</html>