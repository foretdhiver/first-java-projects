<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>��� ���̺�</title>
		<!-- <script type="text/javascript" src="../lib/jquery-1.11.0.min.js"></script> -->
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript">
			
			alert('member.jsp ������ �Դϴ�');
			function insertPopup(){
				alert('insertPopup �Լ� ����');
				$("#kid").val("");
				window.open("", "pop", "width=480, height=500");
				$("#popupForm").attr("action", "/member/selectMember.kyj");
				$("#popupForm").attr("enctype", "multipart/form-data");
				$("#popupForm").attr("method", "POST");
				$("#popupForm").attr("target", "pop");
				$("#popupForm").submit();
			}	
			
			function updatePopup(kid){
				alert('updatePopup �Լ� ����');
				$("#kid").val(kid);
				window.open("", "pop", "width=480, height=500");
				$("#popupForm").attr("action", "/member/selectMember.kyj");
				$("#popupForm").attr("enctype", "application/x-www-form-urlencoded");
				$("#popupForm").attr("method", "POST");
				$("#popupForm").attr("target", "pop");
				$("#popupForm").submit();
			}

			function listSearch(){
				alert('listSearch ����');
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
				<tr><td colspan="2" align="center"><h3>��� ���̺�</h3></td></tr>
				<tr>
			<!-- 	<td align="left"><input type="button" style="width:70px;" onclick="window.location.reload()" value="****" /></td>  -->
					<td colspan="1" align="right"><input type="button" style="width:70px;" onclick="insertPopup();" value="ȸ������" /></td>
				</tr>
			</table>
		</div>
		<div align="center"><!-- search -->
			<form id="searchForm" name="searchForm">
				<table border="1" cellpadding="1" cellspacing="1">
					<tr>
						<th>ȸ�� ���̵�</th>
						<td>
							<input type="text" id="kid" name="kid" />
						</td>
						<td>
							<input type="button" onclick="listSearch()" style="width:100%" value="�˻�" />
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
							��ϵ� ȸ�� ������ �������� �ʽ��ϴ�.
						</td>
					</tr>
				</c:if>
				
				<tr>
					<th>ȸ����ȣ</th>
					<td>${row.kmem}</td>
				</tr>
				<tr>
					<th>���̵�</th>
					<td>${row.kid}</td>
				</tr>
				<tr>
					<th>��й�ȣ</th>
					<td>${row.kpw}</td>
				</tr>
				<tr>
					<th>�̸�</th>
					<td>${row.kname}</td>
				</tr>
				<tr>
					<th>�޴�����ȣ</th>
					<td>${row.khp}</td>
				</tr>
				<tr>
					<th>�������</th>
					<td>${row.kbirth}</td>
				</tr>
				<tr>
					<th>�̸����ּ�</th>
					<td>${row.kemail}</td>
				</tr>
				<tr>
					<th>�����ȣ</th>
					<td>${row.kpostno}</td>
				</tr>
				<tr>
					<th>�ּ�</th>
					<td>${row.kjuso}</td>
				</tr>
				<tr>
					<th>���ּ�</th>
					<td>${row.kjuso1}</td>
				</tr>
				<tr>
					<th>�̹���</th>
					<td align="center"><img src="/${row.kimage}" alt="�̹����� �����ϴ�" border="0" width="200px" height="200px"></td>
				</tr>
				<tr>
					<th>������</th>
					<td>${row.kinsertdate}</td>
				</tr>
				<tr>
					<th>������</th>
					<td>${row.kupdatedate}</td>
				</tr>
				<tr>
					<th>�󼼺���</th>
					<td><input type="button" style="width:100%" value="����/����" onclick="updatePopup('${row.kid}')"/></td>
				</tr>
			</table>
				<br>
			</c:forEach>
		</div>
	</body>
</html>