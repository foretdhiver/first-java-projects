<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="bitcamp.java142.ch5.kyjjf.dao.KyjMemberDAO" %>
<%@ page import="bitcamp.java142.ch5.kyjjf.dao.KyjMemberDAOImpl" %>
<%@ page import="bitcamp.java142.ch5.kyjjf.vo.KyjMemberVO" %>

<%@ page import="java.util.ArrayList" %>

<% request.setCharacterEncoding("EUC-KR"); %>

<% 
	// String knumm;
	String kid = request.getParameter("kid");
	String kpw = request.getParameter("kpw");
	String kname = request.getParameter("kname");
	String khp = request.getParameter("khp");
	String kbirth = request.getParameter("kbirth");
	String kmail = request.getParameter("kmail");
	String kpostno = request.getParameter("kpostno");
	String kjuso = request.getParameter("kjuso");
	// String kdeleteyn;
	// String kinsertdate;
	// String kupdatedate;
	
	KyjMemberDAO kdao = new KyjMemberDAOImpl();
	KyjMemberVO kvo = null;
	kvo = new KyjMemberVO();
	kvo.setKid(kid);
	kvo.setKpw(kpw);
	kvo.setKname(kname);
	kvo.setKbirth(kbirth);
	kvo.setKhp(khp);
	kvo.setKemail(kmail);
	kvo.setKpostno(kpostno);
	kvo.setKjuso(kjuso);
	
	boolean bFlag = kdao.insertKyjMember(kvo);
	
	if(bFlag){
		ArrayList<KyjMemberVO> aList = kdao.searchKyjMember(kvo);
		int aListSize=aList.size();
		if(aListSize>0){
%>	
	<script>
		alert("�Է� ����!");
		
	</script>
	<table border="1">
			<tr>
				<td colspan="12" align="center"><h3>�� ����</h3></td>
			</tr>
			<tr>
				<td align="center">ȸ����ȣ</td>
				<td align="center">���̵�</td>
				<td align="center">��й�ȣ</td>
				<td align="center">�̸�</td>
				<td align="center">�������</td>
				<td align="center">�޴�����ȣ</td>
				<td align="center">�̸���</td>
				<td align="center">�����ȣ</td>
				<td align="center">���θ��ּ�</td>
				<td align="center">��������</td>
				<td align="center">�Է���</td>
				<td align="center">������</td>
			</tr>
		<% 
			for(int i=0;i<aList.size();i++){
				KyjMemberVO _kvo = aList.get(i);
		%>
			<tr>
				<td><%=_kvo.getKnumm() %></td>
				<td><%=_kvo.getKid() %></td>
				<td><%=_kvo.getKpw() %></td>
				<td><%=_kvo.getKname() %></td>
				<td><%=_kvo.getKbirth() %></td>
				<td><%=_kvo.getKhp() %></td>
				<td><%=_kvo.getKemail() %></td>
				<td><%=_kvo.getKpostno() %></td>
				<td><%=_kvo.getKjuso() %></td>
				<td align="center"><%=_kvo.getKdeleteyn() %></td>
				<td><%=_kvo.getKinsertdate() %></td>
				<td><%=_kvo.getKupdatedate() %></td>
			</tr>
	<% }
		}
		}else{%>
		<script>
		alert("�Է� ����!");
		location.href="/babyWeb/member.jsp";
	</script>
	<% } %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

</body>
</html>