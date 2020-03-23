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
					System.out.println(" 삭제성공 페이지로 보내기 ");
		%>
					<script>
						alert("게시판 글 삭제 성공 !!!");
						location.href="/babyWeb/board/kyjHelloBoardSelectAll.jsp";
					</script>
		<%
				}
			}else{ %>
					<script>
						alert("삭제할 게시글의 체크박스 선택이 안되었습니다!");
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
		<title>게시판 글 삭제</title>
	</head>
	<body>
	</body>
</html>