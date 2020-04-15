<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List"%>
<%@ page import="com.spring.ge.vo.EaVO_" %>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>
<%  request.setCharacterEncoding("EUC-KR"); %>  
<%
	Object object = session.getAttribute("eminfo");
	EmInfoVO emvo = new EmInfoVO();
	emvo = (EmInfoVO)object;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>결재선 작성 페이지</title>
		<link rel="stylesheet" type="text/css" href="/css/docform.css" />
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
//				alert("[log] 결재선 등록하는 페이지");
				$("#selectChk").click(function(){
//					alert($("#selectList").val());
					var sendVal = $("#selectList").val();
					$("#send").val(sendVal);
//					alert($("#send").val());
//					alert($("#ea_linename").val());
					if($('#ea_linename').val()==""){
						alert('결재선 이름을 입력해주세요');
						return false;
					}
					if(confirm('결재선을 제출하시겠습니까?')){
						$("#lineForm").attr("action","/ea_/eaInsertLine.ge").submit();
					}else{
						
					}
					
				});
			});
		</script>
	</head>
	<body>
		<form id="lineForm" name="lineForm" method="post" accept-charset="EUC-KR">
			<table class="docform">
				<div class="tit"><div class="title">결재선 입력</div></div>
				<div class="docform">
				<tr>
					<th> 결재문서양식</th>
					<th> 결재선 이름 </th>
				</tr>
				<tr>
					<td align="center">
						<select id="selectList" name="selectList">
							<option value="H"> 휴가계 </option>
							<option value="P"> 프로젝트 기안서 </option>
						</select>
						<input type="hidden" id="send" name="send">
					</td>
					<td>
						<input type="text" id="ea_linename" name="ea_linename">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" class="button" value="전송" id="selectChk" name="selectChk">
					</td>
				</tr>
				</div>
			</table>
		</form>
	</body>
</html>