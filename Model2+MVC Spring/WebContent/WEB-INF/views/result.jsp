<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>결과 화면</title>
		<script type="text/javascript">
		alert("${result}");
		if("${result}".indexOf("문제")>-1){
			history.go(-1);
		}else{
			opener.listSearch();
			window.close();
		}
		</script>
	</head>
	<body>
	
	</body>
</html>