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
		<title>���缱 �ۼ� ������</title>
		<link rel="stylesheet" type="text/css" href="/css/docform.css" />
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
//				alert("[log] ���缱 ����ϴ� ������");
				$("#selectChk").click(function(){
//					alert($("#selectList").val());
					var sendVal = $("#selectList").val();
					$("#send").val(sendVal);
//					alert($("#send").val());
//					alert($("#ea_linename").val());
					if($('#ea_linename').val()==""){
						alert('���缱 �̸��� �Է����ּ���');
						return false;
					}
					if(confirm('���缱�� �����Ͻðڽ��ϱ�?')){
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
				<div class="tit"><div class="title">���缱 �Է�</div></div>
				<div class="docform">
				<tr>
					<th> ���繮�����</th>
					<th> ���缱 �̸� </th>
				</tr>
				<tr>
					<td align="center">
						<select id="selectList" name="selectList">
							<option value="H"> �ް��� </option>
							<option value="P"> ������Ʈ ��ȼ� </option>
						</select>
						<input type="hidden" id="send" name="send">
					</td>
					<td>
						<input type="text" id="ea_linename" name="ea_linename">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" class="button" value="����" id="selectChk" name="selectChk">
					</td>
				</tr>
				</div>
			</table>
		</form>
	</body>
</html>