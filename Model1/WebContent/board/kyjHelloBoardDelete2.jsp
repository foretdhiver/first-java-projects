<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="bitcamp.java142.board.dao.KyjHelloBoardDAOImpl" %>
<%@ page import="bitcamp.java142.board.dao.KyjHelloBoardDAO" %> 
<%@ page import="bitcamp.java142.board.vo.KyjHelloBoardVO" %> 
<%@ page import="java.util.ArrayList" %>

<% request.setCharacterEncoding("EUC-KR"); %>

<% 
	String isudType = request.getParameter("ISUD_TYPE");
	String chkInKno	= request.getParameter("chkInKno"); 
	
	if(isudType!=null){
		System.out.println("isudType >>> : " + isudType);
		boolean bool = "D".equals(isudType.toUpperCase());
		
		if(bool){
			KyjHelloBoardDAO kdao = new KyjHelloBoardDAOImpl();
			System.out.println("kdao >>> : " + kdao);
			
			KyjHelloBoardVO kvo = null;
			kvo = new KyjHelloBoardVO();
			kvo.setKno(chkInKno);
			
			ArrayList<KyjHelloBoardVO> aList = kdao.selectKyjBoard(kvo);
			int nCnt = aList.size();
			
			if(nCnt == 1){
			
			int nCntD = kdao.deleteKyjHelloBoard(kvo);
		// delete logic
								
				
				if (nCntD == 1){
					System.out.println(" �������� �������� ������ ");
		%>
					<script>
						alert("�Խ��� �� ���� ���� !!!");
						location.href="/babyWeb/board/kyjHelloBoardSelectAll.jsp";
					</script>
		<%
				}
			}else{ %>
					<script>
						alert("������ �Խñ��� üũ�ڽ� ������ �ȵǾ����ϴ�!");
						location.href="/babyWeb/board/kyjHelloBoardSelectAll.jsp";
					</script>
			      <% }
		}
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>�Խ��� �� ����</title>
	</head>
	<body>
	</body>
</html>