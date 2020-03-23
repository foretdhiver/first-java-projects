<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>멤버 테이블</title>
		<!-- <script type="text/javascript" src="../lib/jquery-1.11.0.min.js"></script> -->
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript">
			
			alert('member.jsp 페이지 입니다');
			function insertPopup(){
				alert('insertPopup 함수 진입');
				$("#kid").val("");
				window.open("", "pop", "width=480, height=500");
				$("#popupForm").attr("action", "/member/selectMember.kyj");
				$("#popupForm").attr("enctype", "multipart/form-data");
				$("#popupForm").attr("method", "POST");
				$("#popupForm").attr("target", "pop");
				$("#popupForm").submit();
			}	
			
			function updatePopup(kid){
				alert('updatePopup 함수 진입');
				$("#kid").val(kid);
				window.open("", "pop", "width=480, height=500");
				$("#popupForm").attr("action", "/member/selectMember.kyj");
				$("#popupForm").attr("enctype", "application/x-www-form-urlencoded");
				$("#popupForm").attr("method", "POST");
				$("#popupForm").attr("target", "pop");
				$("#popupForm").submit();
			}

			function listSearch(){
				alert('listSearch 진입');
				$("#searchForm").attr("action", "/member/listMember.kyj");
				$("#popupForm").attr("enctype", "application/x-www-form-urlencoded");
				$("#popupForm").attr("method", "POST");
				$("#searchForm").submit();
			}
		</script>
		
		<style type="text/css">
			table{width:400px}
			#searchForm table th{width:80x}
			#searchForm table td{width:100px; text-align:center;}
		</style>
		
	</head>
	<body>

		<form id="popupForm" name="popupForm">
			<input type="hidden" name="kmem" id="kmem" value="${row.kmem}" />
			<input type="hidden" name="kid" id="kid" value="${row.kid}" />
			<input type="hidden" name="kpw" id="kpw" value="${row.kpw}" />
		</form>

		<div align="center">
			<table border="0">
				<tr><td colspan="2" align="center"><h3>멤버 테이블</h3></td></tr>
				<tr>
			<!-- 	<td align="left"><input type="button" style="width:70px;" onclick="window.location.reload()" value="****" /></td>  -->
					<td colspan="1" align="right"><input type="button" style="width:70px;" onclick="insertPopup();" value="회원가입" /></td>
				</tr>
			</table>
		</div>
		<div align="center"><!-- search -->
			<form id="searchForm" name="searchForm">
				<table border="1" cellpadding="1" cellspacing="1">
					<tr>
						<th>회원 아이디</th>
						<td>
							<input type="text" id="kid" name="kid" />
						</td>
						<td>
							<input type="button" onclick="listSearch()" style="width:100%" value="검색" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<br>
		
		<div align="center">
			<c:forEach items="${memberList}" var="row">
			<table border="1" >
				<c:if test="${empty memberList}">
					<tr>
						<td colspan="13" align="center">
							등록된 회원 정보가 존재하지 않습니다.
						</td>
					</tr>
				</c:if>
				
				<tr>
					<th>회원번호</th>
					<td>${row.kmem}</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td>${row.kid}</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>${row.kpw}</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>${row.kname}</td>
				</tr>
				<tr>
					<th>휴대폰번호</th>
					<td>${row.khp}</td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td>${row.kbirth}</td>
				</tr>
				<tr>
					<th>이메일주소</th>
					<td>${row.kemail}</td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td>${row.kpostno}</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>${row.kjuso}</td>
				</tr>
				<tr>
					<th>상세주소</th>
					<td>${row.kjuso1}</td>
				</tr>
				<tr>
					<th>이미지</th>
					<td align="center"><img src="/${row.kimage}" alt="이미지가 없습니다" border="0" width="200px" height="200px"></td>
				</tr>
				<tr>
					<th>가입일</th>
					<td>${row.kinsertdate}</td>
				</tr>
				<tr>
					<th>수정일</th>
					<td>${row.kupdatedate}</td>
				</tr>
				<tr>
					<th>상세보기</th>
					<td><input type="button" style="width:100%" value="수정/삭제" onclick="updatePopup('${row.kid}')"/></td>
				</tr>
			</table>
				<br>
			</c:forEach>
		</div>
	</body>
</html>