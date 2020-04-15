<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmPrInfoVO" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>마이페이지 개인정보</title>
		<script type="text/javascript"
				src="https://code.jquery.com/jquery-1.11.0.min.js">
		</script>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#update").click(function(){
					alert("update 함수");
					$("#myForm").attr("action", "/em/pwUpdate.ge");						
					$("#myForm").submit();
				});
			});
		</script>
	</head>
	<body>
		<a href="/em/myPageTr.ge">마이페이지-교육사항(validation 안해서 지금 누르면 null나와요~~)</a>
<%
		Object obj = request.getAttribute("myList");

		ArrayList<EmPrInfoVO> mList = null;
		mList = (ArrayList<EmPrInfoVO>)obj;
		
		System.out.println("(log) mList.size()" + mList.size());
		
		if(mList.size() > 0){
			for(int i=0; i<mList.size(); i++){
				EmPrInfoVO epvo = (EmPrInfoVO)mList.get(i);
%>
		<form name="myForm"
			  id="myForm"
			  method="GET"
			  enctype="application/x-www-form-urlencoded">
			<table border="1" align="center">
				<tr>
					<td colspan="2" align="center">
						<center>나의 정보</center>
					</td>
				</tr>
				<tr>
					<td width="200" align="center">사번</td>
					<td width="500">
						<%=epvo.getEmno()%>
					</td>
				</tr>
				<tr>
					<td align="center">이름</td>
					<td><%=epvo.getEmname()%></td>
				</tr>
				<tr>
					<td align="center">아이디</td>
					<td><%=epvo.getEmid()%></td>
				</tr>
				<tr>
					<td align="center">비밀번호</td>
					<td><input type="text" name="empw" id="empw"></td>
				</tr>
				<tr>
					<td align="center">부서</td>
					<td><%=epvo.getDeptname() %></td>
				</tr>
				<tr>
					<td align="center">직위</td>
					<td><%=epvo.getJobname()%></td>
				</tr>
				<tr>
					<td align="center">이메일</td>
					<td><%=epvo.getEmemail() %></td>
				</tr>
				<tr>
					<td align="center">핸드폰번호</td>
					<td><%=epvo.getEmhp() %></td>
				</tr>
				<tr>
					<td align="center">내선번호</td>
					<td><%=epvo.getEmexno() %></td>
				</tr>
				<tr>
					<td align="center">직통번호</td>
					<td><%=epvo.getEmdino() %></td>
				</tr>
				<tr>
					<td align="center">생년월일</td>
					<td><%=epvo.getEmprbirth() %></td>
				</tr>
				<tr>
					<td align="center">혼인여부</td>
					<td>
<%					
						if(epvo.getEmprmarital().equals("61")){
%>							
							미혼
<%
						}
						if(epvo.getEmprmarital().equals("62")){
%>
							기혼
<%							
						}
%>					
					</td>
				</tr>
				<tr>
					<td align="center">우편번호</td>
					<td><%=epvo.getEmprpostno() %></td>
				</tr>
				<tr>
					<td align="center">주소</td>
					<td><%=epvo.getEmpradd() %></td>
				</tr>
				<tr>
					<td align="center">상세주소</td>
					<td><%=epvo.getEmprdetailadd() %></td>
				</tr>
				<tr>
					<td align="center">학력</td>
					<td>
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
				</tr>
				<tr>
					<td align="center">최종학교명</td>
					<td><%=epvo.getEmpreduname() %></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="수정" id="update" name="update">
					</td>
				</tr>
			</table>
		</form>
<%
			}	// end of for in if(mList.size() > 0)
		}	// end of if(mList.size() > 0)
%>	
	</body>
</html>