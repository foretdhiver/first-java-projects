<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="bitcamp.java142.ch5.kyjjf.dao.KyjMemberDAOImpl" %>
<%@ page import="bitcamp.java142.ch5.kyjjf.dao.KyjMemberDAO" %> 
<%@ page import="bitcamp.java142.ch5.kyjjf.vo.KyjMemberVO" %> 

<%@ page import="java.util.ArrayList" %>

<% request.setCharacterEncoding("EUC-KR"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<%
			String kname = request.getParameter("kname");
			KyjMemberDAO kdao = new KyjMemberDAOImpl();
			KyjMemberVO _kvo = null;
			_kvo = new KyjMemberVO();
			_kvo.setKname(kname);
			ArrayList<KyjMemberVO> aList = kdao.searchKyjMember(_kvo);
			if(aList.size()==0){
		%>
			<script>
					alert("존재하지 않는 회원입니다!");
					location.href="/babyWeb/member.html";
			</script>
		<% 
			}else{ 
		%>
		<table border="1">
			<tr>
				<td colspan="12" align="center"><h3>회원정보</h3></td>
			</tr>
			<tr>
				<td align="center">회원번호</td>
				<td align="center">아이디</td>
				<td align="center">비밀번호</td>
				<td align="center">이름</td>
				<td align="center">생년월일</td>
				<td align="center">휴대폰번호</td>
				<td align="center">이메일</td>
				<td align="center">우편번호</td>
				<td align="center">도로명주소</td>
				<td align="center">삭제여부</td>
				<td align="center">입력일</td>
				<td align="center">수정일</td>
			</tr>
		<% 
			for(int i=0;i<aList.size();i++){
				KyjMemberVO kvo = aList.get(i);
		%>
			<tr>
				<td><%=kvo.getKnumm() %></td>
				<td><%=kvo.getKid() %></td>
				<td><%=kvo.getKpw() %></td>
				<td><%=kvo.getKname() %></td>
				<td><%=kvo.getKbirth() %></td>
				<td><%=kvo.getKhp() %></td>
				<td><%=kvo.getKemail() %></td>
				<td><%=kvo.getKpostno() %></td>
				<td><%=kvo.getKjuso() %></td>
				<td align="center"><%=kvo.getKdeleteyn() %></td>
				<td><%=kvo.getKinsertdate() %></td>
				<td><%=kvo.getKupdatedate() %></td>
			</tr>
		<%
				}
			}
		%>
	
	</body>
</html>