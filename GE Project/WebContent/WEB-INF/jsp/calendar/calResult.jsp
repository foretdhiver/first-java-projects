<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>CalResult</title>
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript">
			
		if("${result}".indexOf("����")>-1){
			history.go(-1);
		}else{
			alert("�۾����࿡ �����߽��ϴ�.");
			opener.location.reload();
			window.close();
		}
		</script>
		
	</head>
	<body>
		
	</body>
</html>