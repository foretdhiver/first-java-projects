<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>    

<%@ page import = "com.spring.ge.vo.CommonVO" %>
<%@ page import = "com.spring.ge.common.PagingTag" %>
<%@ page import = "com.spring.ge.controller.AnBoardController" %>         
<%@ page import = "com.spring.ge.vo.AnBoardVO" %> 
<%@ page import = "com.spring.ge.vo.EmInfoVO" %>  
<%@ page import = "java.util.ArrayList" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	Object obj = session.getAttribute("eminfo");
	EmInfoVO evo = new EmInfoVO();
	evo = (EmInfoVO)obj;

	ArrayList<AnBoardVO> aList= (ArrayList<AnBoardVO>)request.getAttribute("anBoardList");
	AnBoardVO bvo_= (AnBoardVO)request.getAttribute("data");
	int pageCount = 0;
	
	if(aList.size() >0){
		
		AnBoardVO nvo_ = aList.get(0);
	    String totalCount =aList.get(0).getTotalcount_();
	    int pagelistSize = (int)request.getAttribute("listsize_");
	    double dval = (double)pagelistSize;
	    pageCount = (int)Math.ceil(Integer.parseInt(totalCount)/dval); //pageCount ���� ���
	    
	    System.out.println("jsp���� pageCount>>>:"+ pageCount); //1
	    System.out.println("jsp���� totalCount>>>:"+ totalCount); //10
	    System.out.println("jsp���� pagelistSize>>>:"+ pagelistSize);
	    System.out.println("jsp���� totalCount>>>:"+ totalCount);
	}
	
%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>::: GE PROJECT :::</title>
		<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<!-- css -->
		<link rel="stylesheet" type="text/css" href="/../css/common.css" />
		<script type="text/javascript" src="/js/clock.js"></script>
		
		 <style type="text/css">
		  
		  .mainframe {
		  	border : hidden;
			}
			
		  #boardList {
		  	border : hidden;
		    width  : 1028px; 
			height : 500px; 
			margin : auto;
		 	align  : center;
		   	padding: 0;
		   	text-align : center;
		  }
		 #abBoardSearch	{
			 width : 900px; 
			 margin: auto;
			 margin-top : 10px;
			 align : center;
			 border: hidden;
			 border-spacing: 0px;
			 
		  }
		#abBoardList{
		  	 width : 900px; 
			 margin: auto;
			 margin-top : 0px;
			 align : center;
			 border: hidden;
			 border-spacing: 0px;
			 font-size : small;
	 
		  }
		#abBoardTit{
 			margin: auto;
			 margin-top : 40px;
			 margin-left : 40px;
			 align : left;
			 font-size : small;
		  }
		#abBoardTit11{
		  	height:50px;  
 		  	width:10px;  
		  	background:#D8D8D8;
 		  	margin:20px 0px 0px 20px; 
 		  	float:left;
		  }
		
		
		  table {
	        width: 100%;
	        border-top: 1px solid #444444;
	        border-collapse: collapse;
	
	      }
	      th, td {
		        border-bottom: 1px solid #444444;
		        padding:9px;
		        text-align: center;
	      }
	      th {
		    background-color: #d9d9d9;
		  }
	    </style>
	
		<script type="text/javascript">
	
		$(document).ready(function(){
			$('.mainframe .loader').hide();
			setInterval("dpTime()",0);
			
			$(function(){
				
				//�˻� �� �˻� ���&�ܾ� ���
				if(value="<%= bvo_.getKeyword()%>" !=""){
					$("#keyword").val(value="<%= bvo_.getKeyword() %>");
					$("#search").val(value="<%= bvo_.getSearch() %>");
				}
				
				//�� �������� ������ ���ڵ� �� ��ȸ&���� ���� 
<% 
				if(bvo_.getPagesize_() != "" ){
%>
					$("#PageSize").val("<%=bvo_.getPagesize_()%>");
<%
				}
%>	
				//�˻� ����� ����� ������ ó�� �̺�Ʈ
				$("#search").change(function(){
					//alert("�˻���� ���� >>>");
					
					if($("#search").val()=="all"){
						$("#keyword").val("��ü������ ��ȸ�մϴ�.");
					}else if($("#search").val()!="all"){
						$("#keyword").val("");
						$("#keyword").focus();
					}//if-else
						
				});//$("#search").change(function()
				
				//�˻� ��ư Ŭ�� ��
				$("#searchData").click(function(){
					//alert("searchData>>> ");
					goPage(1);
				});//$("#searchData")
						
				//�� �������� ������ ���ڵ� �� ����ø��� ó�� �̺�Ʈ
				$("#listsize_").change(function(){
					gopage(1);
				});//pageSize		
						
				//�۾��� ��ư Ŭ�� ��
				$("#writeForm").click(function(){
					//alert("writeForm >>> ");
					$("#boardList").attr("action","/anmsBoard/writeForm.ge");
					$("#boardList").submit();
				});//writeForm
				
				//���� Ŭ�� �� �������� �̵�
				$(".goDetail").click(function(){
					//alert("���������� �̵� >>>");
					
					var pbno = $(this).parents("tr").attr("data-num");
					//alert("pbno >> " + pbno);
					$("#abno").val(abno);
					//alert("#pbno >> " +$("#pbno"));
					//alert("��ȣȮ�� 1" + $("#abno").val());
					$("#abno").val(pbno);
					//alert("��ȣȮ�� 2" + pbno);
// 					//�켱 ������ ���� ���� �������� �ڼ��ϰ� ���ô°� ���ƿ�
// 					��� �κп��� ������ ������ Ȯ���� �ؾ��ϴµ�
// 					�װ� ���°� ������ ���������� �켱 �� ����ϴ°����� �˾ƾ� �ؿ�
// 					�� ����ϴ°��� �˾ƾ� ����ϴ°� ��� ������ ��������,
// 					����ϴ� �����Ͱ� �� ������ �ٸ� �����Ͱ� ���ͼ� ������ �� ������,
// 					�ƴϸ� ����ϴ� �����ʹ� �� ���Դµ�, �ٸ� �κп� ������ �־ ������ �� ������ Ȯ���� �����ؿ�
// 					�Ʊ� ���� ������ ����  reply.jsp�ϰ� �ؿ�  null �ͼ����̶�� ǥ�õǾ� �־����
// 					�׷� �켱 reply.jsp�� � ������� ���ư����� �˾ƾ� ���� ���� �����ϱⰡ ������
// 					������ �۹�ȣ�� ������ �ش� �۹�ȣ�� �ش��ϴ� ��� ����� �ҷ����� ���·� �Ǿ��־��
// 					�۹�ȣ�� null�̱� ������ reply.jsp�� ������ �� ���̿���
// 					�׷� �۹�ȣ�� null�϶�, ��� null ���� �����Ͱ� �߸����ͼ� =null���� Ȯ���� �ؾ��ؿ�
// 					//���� ������ ���� null�ͼ��� �־����
					
					
					//�� �������� �̵��ϱ� ���� form �߰�
					$("#detailForm").attr({"method":"POST","action":"/anmsBoard/anBoardDetail.ge"});
					$("#detailForm").submit();
				});//goDetail
				
				//������ ��ư Ŭ���� �Լ�
				$(".pageNobut").click(function(){
					////alert("��������ư Ŭ��");
					var pageNo = $(this).val();
					//alert(pageno);
			         $("#pageno_").val(pageNo);
			         ////alert(pageNo);
			         $("#boardList").attr("action","/anmsBoard/anBoardList.ge");
			         ////alert("1");
			         $("#boardList").submit();
			         ////alert("2");
					
				});//$(".pageNobut")
				
				//�˻�,����¡ó�� ���� �Լ�
				function goPage(page){
					////alert(">>>");
					if($("#search").val=="all"){
						$("keyword").val("");
					}//if($("#search"
					////alert(">>dddd>");	
					$("#page").val(page);
					$("#boardList").attr({"method":"GET","action":"/anmsBoard/anBoardList.ge"});
					$("#boardList").submit();
				}//function goPage
			});//function()	
			
			$('#logout').click(function(){
				if(confirm('�α׾ƿ� �Ͻðڽ��ϱ�?')){
					loading();
					location.href='/ea/geLogOut.ge';
				}else{
				}
				
			});
		});//$(document).ready
		
		//���ٱ���
		function deptcdCheck1(check){
			var checkDept = check;
			var userDept = "<%=evo.getDeptcd() %>";
			if(checkDept != userDept){
				alert("���� ������ �������� �ʽ��ϴ�.");
				return;
			}
			loading();
			$("#ge_main").attr("action","/board/bDList.ge").submit();	
		}
		function deptcdCheck2(check){
			var checkDept = check;
			var userDept = "<%=evo.getDeptcd() %>";
			if(checkDept != userDept){
				alert("���� ������ �������� �ʽ��ϴ�.");
				return;
			}
			loading();
			$("#ge_main").attr("action","/board/bTList.ge").submit();	
		}
		
		function loading(){
			setTimeout($('.mainframe .loader').show(), 1000);
		}
		
		</script>
	</head>
	<body>
		<!-- css -->
		<div id="ge_menudiv">
			<div class="topinfo">
				<div class="myname"><%=evo.getEmname() %> �� �ȳ��ϼ���.</div>
				<div class="logout" id="logout">logout</div>
			</div>
		</div>
		<div class="ge_maindiv">
			<form name="ge_main" id="ge_main">
				<input type="hidden" name="ea_stepno" id="ea_stepno"> 
				<input type="hidden" name="ea_no" id="ea_no">
			</form>
			<div class="topmenu">
				<div class="p">
					<a href="/ea/gemain.ge">����</a>��
					<a href="/ea/eamain.ge">���ڰ���</a>��
					<a href="#">���ڸ���</a>��
					<a href="#">������</a>��
					<a href="/board/boardMain.ge" style="color:#615F8D">�μ��Խ���</a>��
					<a href="/board/calendar.ge">��������</a>��
					<a href="#">������</a>��
					<a href="/em/myPageInfo.ge">����������</a>
				</div><!-- <div class="p"> -->
			</div><!-- topmenu -->
		</div><!-- ge_maindiv -->
			<div class="topdiv"></div>
			<div class="outertable">
				<div class="ge_sidemenu">
					<div class="ge_myinfo">
						<div class="imgbox">
                     		<img class="myimg" src="/em_Image<%=evo.getEmpic()%>" />
                     	</div>
                     <br>
						<div class="itsme">
							<%=evo.getEmname() %><br>(<%=evo.getDeptname() %>/<%=evo.getJobname() %>)
						</div>
					</div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/board/bNList.ge" onclick="">��������</a></div>
						<br>
						<div class="menubtn"><div class="mbtneff"></div><a href="#" onclick="deptcdCheck1(20); return false;">������ �Խ���</a></div>
						<a href="javascript:deptcdCheck2(20);" style="color: black">&nbsp;&nbsp;�������Խ���</a><br><br>
						<div class="menubtn"><div class="mbtneff"></div><a href="#" onclick="deptcdCheck1(30); return false;">���������� �Խ���</a></div>
						<a href="javascript:deptcdCheck2(30);" style="color: black">&nbsp;&nbsp;�������Խ��� </a><br><br>
						<div class="menubtn"><div class="mbtneff"></div><a href="#" onclick="deptcdCheck1(40); return false;">������ �Խ���</a></div>
						<a href="javascript:deptcdCheck2(40);" style="color: black">&nbsp;&nbsp;�������Խ��� </a><br><br>
						<div class="menubtn"><div class="mbtneff"></div>
						<a href="#" onclick="deptcdCheck1(50); return false;">��������� �Խ���</a>
						</div>
						<a href="javascript:deptcdCheck2(50);" style="color: black">&nbsp;&nbsp;�������Խ��� </a><br><br>
						<div class="menubtn"><div class="mbtneff"></div><a href="/pjboard/pjBoardList.ge" onclick="">������Ʈ�Խ���</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/anmsBoard/anBoardList.ge" onclick="">�͸�Խ���</a></div>						
						<div class="clock" style="border:1px solid #cccccc; border-radius:1px; background:#D8D8D8; float:left; margin:10px 0 10px 0px; padding:5px 0 5px 0">
						<div align="center" style="font-size:20pt ;color:white; "class="dpTime" id="dpTime"></div>
						</div>
				</div>
				<div class="mainframe">
				
<!--================== ���γ����� ����ġ ============================ -->
				<!-- �������� -->
				<form name="detailForm" id="detailForm">
					<input type="hidden" name="abno" id="abno">
				</form>
				<div id="abBoardTit11"></div>
				<div id="abBoardTit"><h2>�͸� �Խ���</h2></div>
				<!-- ��ư -->
				<div id="boardBut" align="right" style=" margin-right : 65px;">
					<input type="button" id="writeForm" value="�۾���">	
				</div>
				<br>
				
				<!-- �۸�� ����  -->
				<form id="boardList" >
					
					<!-- �˻���� ���� -->
					<div id="abBoardSearch">
						<input type="hidden" id="page" name="page" value="1" />
						<table summary="�˻�">
							<colgroup>
								<col width="70%"></col>
								<col width="30%"></col>
							</colgroup>
							<tr>
								<td id="btd1">
								<label>�˻�����</label> 
									<select id="search" name="search">
										<option value="all">��ü</option>
										<option value="absubject">����</option>										
										<option value="abcontent">����</option>										
									</select> 
									<input type="text" name="keyword" id="keyword" value="�˻�� �Է��ϼ���" /> 
									<input type="button" value="�˻�" id="searchData" /></td>
								<td id="btd2">�� �������� 
									<select id="listsize_" name="listsize_">
										<option value="10">10��</option>
										<option value="20">20��</option>
										<option value="30">30��</option>
										<option value="50">50��</option>
										<option value="70">70��</option>
										<option value="100">100��</option>
									</select>
								</td>
							</tr>
						</table>
					</div>
					
					<!-- ����Ʈ -->
					<div id="abBoardList" align="center"> 
						<!-- 		<form name="boardList" id="boardList"> -->
						<table summary="�͸�Խ��� ����Ʈ">
							<colgroup>
								<col width="10%" />								
								<col width="35%" />
								<col width="15%" />								
								<col width="10%" />
							</colgroup>
							<thead>
								<tr>
									<th>�۹�ȣ</th>									
									<th>������</th>
									<th>�ۼ���</th>									
									<th>��ȸ��</th>
								</tr>
							</thead>
							<tbody>
<%
							if(aList.size()!=0){
								AnBoardVO bvo = aList.get(0);
%>
							<!-- ������ ��� -->
<%
							for(int i = 0; i < aList.size(); i++){
								AnBoardVO bvo2 = aList.get(i);
%>
								<tr align="center" data-num="<%=bvo2.getAbno()%>">
									<td><%=bvo2.getAbno()%></td>
									<td align="left"><span class="goDetail"><%=bvo2.getAbsubject()%></span></td>
									<td><%=bvo2.getAbinsertdate()%></td>
									<td><%=bvo2.getAbcvcnt()%></td>
								</tr>
<%
							}//for
%>
			<!--         </form> -->

<%
					}//if(aListP.size()!=0
						else{
%>
								<tr>
									<td colspan="6" align="center">�˻� ����� �������� �ʽ��ϴ�.</td>
								</tr>
<%
							}//else
%>
							</tbody>
						</table>
					</div>
					<br><br>
					<div style=" width : 900px; margin: auto;"align="center">
					<input type="hidden" id="pageno_" name="pageno_" value="<%=bvo_.getPageno_()%>">
<%
					for(int i=1 ; i<=pageCount;i++){
%>
					<input type="button" class="pageNobut" id="pageNobut<%=i%>"
						name="pageNobut<%=i%>" value="<%=i%>">

<%            
            		}//for
%>
				</div>
	
				</form>
			</div><!-- mainframe -->
			</div><!-- outertable -->
			</div><!-- ge_maindiv -->
<!--====================== mainframe ================================-->
		<div class="bottom">
		<div class="bottominfo">
			GE Project �� ����� ���ʱ� ������� 459 (���ʵ�, ��Ϻ���)<br>
			TEL : 02-1234-5678 / FAX : 02-1234-5678<br>
			Copyright �� GE Project. All Rights Reserved.
		</div>
		</div>
	</body>
</html>