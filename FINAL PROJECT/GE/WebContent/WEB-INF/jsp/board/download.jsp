<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collection" %>

<%
	/**********************************
	�����ؾ� �� ����
	***********************************/
	System.out.println("paging.jsp �������Դϴ� ");
	String	url = null;
	String	str = null;
	String search = "";
	String keyword = "";
	
	url = request.getParameter("url");
	System.out.println("url : " + url);
	str = request.getParameter("str");
	System.out.println("str : " + str);
	String groupSize1 = request.getParameter("groupSize");
	System.out.println("groupSize1 : " + groupSize1);
	search = request.getParameter("search");
	keyword = request.getParameter("keyword");
	System.out.println("paging >> search >>> " + search);
	System.out.println("paging >>  keyword >>> " + keyword);
	
	if(str != null)
	{
		str = str + "&";
	}
%>

<%
	/**********************************
	������ �׺���̼� ���� ����
	***********************************/
	// ���������� ������ �Խù��� ��
	int pageSize = 0;
	
	// �׷��� ũ��
	int groupSize = 0;
	
	// ��ü �Խù��� ����
	int totalCount = 0;
	
	//���� ������
	int curPage = 0;
	
	// ��ü �������� ����
	int pageCount = 0;
	
	if(request.getParameter("pageSize") != null)
	{
		pageSize = Integer.parseInt(request.getParameter("pageSize"));
	}
	
	if(request.getParameter("groupSize") != null)
	{
		groupSize = Integer.parseInt(request.getParameter("groupSize"));
	}
	
	if(request.getParameter("curPage") != null)
	{
		curPage = Integer.parseInt(request.getParameter("curPage"));
		System.out.println("curPagecurPage"+curPage);
	}
	System.out.println("curPagecurPageasdf"+curPage);
	if(request.getParameter("totalCount") != null)
	{
		totalCount = Integer.parseInt(request.getParameter("totalCount"));
	}
	
	// ��ü�Խù����� ������ũ�⸦ ������ ��ü ������ ������ �����.
	// �Ҽ����� ���� ������ ������ ������ �Ѱ���.
	pageCount = (int)Math.ceil(totalCount / (pageSize+0.0));
	
	// ����׷� ����
	int curGroup = (curPage-1) / groupSize;
	int linkPage = curGroup * groupSize;
	System.out.println("����¡ ��ũ curGroup >> " + curGroup);
	System.out.println("����¡ ��ũ groupSize >> " + groupSize);
	System.out.println("����¡ ��ũ ������ >> " + linkPage);
%>
<p align="right">
<%
	// ù��° �׷��� �ƴѰ��
	if(curGroup > 0) {
%>
	<a href="<%=url%>?<%=str%>curPage=1">����</a>&nbsp;&nbsp;&nbsp;
	<a href="<%=url%>?<%=str%>curPage=<%=linkPage%>">��</a>&nbsp;&nbsp;&nbsp;
<%
	}
	else
	{
%>
����&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;
<%
	}
	
	// ���� ��ũ�� ���� ������Ŵ
	linkPage++;
	System.out.println("linkPage >>> " + linkPage);
	
	int loopCount = groupSize;
	System.out.println("groupSize" + groupSize);
	System.out.println("loopCount" + loopCount);
	System.out.println("pageCount" + pageCount);
	pageCount = totalCount/groupSize;
	System.out.println("pageCount >>> " + pageCount);
	if(pageCount==0){
		pageCount = 1;
	}

	// �׷���������� ������ ��ũ����.
	while((loopCount > 0) && (linkPage <= pageCount))
	{
		System.out.println("while >>> ");
		if(linkPage == curPage)
		{
%>
	<%=linkPage%>
<%
		}
		else
		{
%>
	[<a href="<%=url%>?<%=str%>curPage=<%=linkPage%>&search=<%=search%>&keyword=<%=keyword%>"><%=linkPage%></a>]&nbsp;
<%
		}
		
		linkPage++;
		loopCount--;
	}
	
	// �����׷��� �ִ� ���
	if(linkPage <= pageCount)
	{
%>
	<a href="<%=url%>?<%=str%>curPage=<%=linkPage%>">��</a>&nbsp;&nbsp;&nbsp;
	<a href="<%=url%>?<%=str%>curPage=<%=pageCount%>">����</a>&nbsp;&nbsp;&nbsp;
<%
	}
	else
	{
%>
	��&nbsp;&nbsp;&nbsp;����&nbsp;&nbsp;&nbsp;
<%
	}
%>
</p>