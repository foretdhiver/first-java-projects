<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>평가 등록</title>
		<script type="text/javascript"
				src=https://code.jquery.com/jquery-1.11.0.min.js>;
		</script>
		<script type="text/javascript">
			$(document).ready(function(){
				alert("document.ready 함수");
				$("#emnoBt").click(function(){
					alert("사원 조회 버튼 클릭");
					window.open("/em/evPopup.ge", "pop", "width=500, height=300");
				});
				$("#insert").click(function(){
					alert("inset 함수")
					$("#insertForm").attr("action", "/em/evInsert.ge");
					$("#insertForm").submit();
				});
			});
		</script>
	</head>
	<body>
		<form name="insertForm"
		      id="insertForm"
		      method="get"
		      enctype="application/x-www-form-urlencoded">
			<table border="1">
				<tr>
					<td colspan="2" align="center">
						<center>평가 등록</center>
					</td>
				</tr>
				<tr>
					<td width="100" align="center">사번</td>
					<td width="300">
						<input type="text" name="emno" id="emno">
						<input type="button" value="사번조회" name="emnoBt" id="emnoBt">
					</td>
				</tr>
				<tr>
					<td align="center">이름</td>
					<td>
						<input type="text" name="emname" id="emname">
					</td>
				</tr>
				<tr>
					<td align="center">부서</td>
					<td>
						<input type="text" name="deptcd" id="deptcd">
					</td>
				</tr>
				<tr>
					<td align="center">직위</td>
					<td>
						<input type="text" name="jobcd" id="jobcd">
					</td>
				</tr>
				<tr>
					<td align="center">평가연도</td>
					<td>
						<input type="text" name="evalyear" id="evalyear">
					</td>
				</tr>
				<tr>
					<td align="center">평가등급</td>
					<td>
						<select name="evalgrade" id="evalgrade">
							<option value="A">A</option>
							<option value="B">B</option>
							<option value="C">C</option>
							<option value="D">D</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">비고</td>
					<td>
						<textarea rows="8" cols="50" name="trnote"></textarea>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="등록" id="insert" name="insert">
					</td>
				</tr>
			</table>      
		</form>
	</body>
</html>