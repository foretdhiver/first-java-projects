<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Result</title>
		<script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
		<script type="text/javascript">
			if("${result}".indexOf("실패")>-1){
				history.go(-1);
			}else{
				alert("작업수행에 성공했습니다.");
				location.href='/anmsBoard/anBoardList.ge';
			}
		</script>
	</head>
	<body>
		</body>
</html>