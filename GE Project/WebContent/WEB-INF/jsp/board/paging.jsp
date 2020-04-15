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
	String search = null;
	String ctstdate = null;
	String cteddate = null;
	
	url = request.getParameter("url");
	System.out.println("url : " + url);
	str = request.getParameter("str");
	System.out.println("str : " + str);
	String groupSize1 = request.getParameter("groupSize");
	System.out.println("groupSize1 : " + groupSize1);
	//
	search = request.getParameter("search");
	System.out.println("search : " + search);
	ctstdate = request.getParameter("ctstdate");
	System.out.println("ctstdate : " + ctstdate);
	cteddate = request.getParameter("cteddate");
	System.out.println("cteddate : " + cteddate);
	
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
	}
	
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
	
	int loopCount = groupSize;
	// �׷���������� ������ ��ũ����.
	while((loopCount > 0) && (linkPage <= pageCount))
	{
		if(linkPage == curPage)
		{
%>
	<%=linkPage%>
<%
		}
		else
		{
%>
	[<a href="<%=url%>?<%=str%>curPage=<%=linkPage%>&search=<%=search%>&ctstdate=<%=ctstdate%>&cteddate=<%=cteddate%>"><%=linkPage%></a>]&nbsp;
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