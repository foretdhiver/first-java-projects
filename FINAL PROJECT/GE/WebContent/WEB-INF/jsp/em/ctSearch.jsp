<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>    
<%@ page import="java.util.ArrayList" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
<%
/* ����� ���� */
Object obj1 = session.getAttribute("eminfo");
EmInfoVO emvo = null;
emvo = new EmInfoVO();
emvo = (EmInfoVO)obj1;
	
Object obj = request.getAttribute("sList");
ArrayList<EmInfoVO> list = null;
list = (ArrayList<EmInfoVO>)obj;
System.out.println("list.size() >>> : " + list.size());

//Object obj6 = request.getAttribute("sList");
//ArrayList<EmInfoVO> sList = null;
//sList = (ArrayList<EmInfoVO>)obj6;
//System.out.println("sList.size() >>> : " + sList.size());
	
int listSize = list.size();
//System.out.println("(log) listSize >>> : " + listSize);

// �˻�
String search = "";
String ctstdate = "";
String cteddate = "";

Object obj2 = request.getAttribute("eVO");
EmInfoVO eVO = null;
eVO = new EmInfoVO();
eVO = (EmInfoVO)obj2;

search = eVO.getSearch();
ctstdate = eVO.getCtstdate();
cteddate = eVO.getCteddate();
System.out.println("search / ctstdate / cteddate >>> : " + search + " / " + ctstdate + " / " + cteddate);

		 
//		EmInfoVO evo = new EmInfoVO();
		
/* ����¡�� ���� �κ� */
// ���������� ������ �Խù��� �� 
int pageSize = 10;
// �׷��� ũ��
int groupSize = 10;
// ���� ������
int curPage = 1;
// ��ü �Խù��� ����
int totalCount = 0;
int pageNo = 0;
		
Object obj3 = request.getAttribute("curPage");
Object obj4 = request.getAttribute("pageNo");
Object obj5 = request.getAttribute("totalCount");
		
String curPage1 = obj3.toString();
String pageNo1 = obj4.toString();
String totalCount1 = obj5.toString();
		
curPage = Integer.parseInt(curPage1);
pageNo = Integer.parseInt(pageNo1);
totalCount = Integer.parseInt(totalCount1);
		
System.out.println(curPage + " / " + pageNo + " / " + totalCount);
		
%>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>���� ���</title>
		<link rel="stylesheet" type="text/css" href="/css/ctAllSelect.css" />
		<script type="text/javascript" src="/js/clock.js"></script>
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> 
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
		<script type="text/javascript">
			$(document).ready(function(){
				//alert("document.ready �Լ� ����");
				$('.mainframe .loader').hide();
				
				setInterval("dpTime()",0);
				
				$('#logout').click(function(){
					if(confirm('�α׾ƿ� �Ͻðڽ��ϱ�?')){
						loading();
						location.href='/ea/geLogOut.ge';
					}else{
					}
				});
				
				$("#nextBt").click(function(){
					//alert("�α� ���̺�� �ѱ���");
					$(location).attr('href','/em/autoTask.ge');
				});
				$("#searchBt").click(function(){
					//alert("��ġ��ġ���Ӥ�");
					$("#searchForm").attr("action", "/em/ctSearch.ge");						
					$("#searchForm").submit();
					
				});
				$("#goToList").click(function(){
					//alert("��ġ��ġ���Ӥ�");
					$("#searchForm").attr("action", "/em/ctAllSelect.ge");						
					$("#searchForm").submit();
					
				});
				
				/* ��¥ search */
				$("#datesearch").click(function(){
					var st_date = $('#ctstdate').val();
					var ed_date = $('#cteddate').val();
					ctstdate = ctstdate.replace("-","");
					ctstdate = ctstdate.replace("-","");
					cteddate = cteddate.replace("-","");
					cteddate = cteddate.replace("-","");
					
					alert("������ : "+ctstdate+"// ������ : "+cteddate);  // 2020-03-12 �̷����� ->replace �� 20200312
				
					$('#ctstdate').val(ctstdate);
					$('#cteddate').val(cteddate);
					
					$("#searchForm").attr({
						"method":"POST",
						"action":"/em/ctSearch.ge"
					});
					$("#searchForm").submit();
				});
				
				/* Ķ���� */
				//datepicker �ѱ���� ����ϱ� ���� ����
	            $.datepicker.setDefaults($.datepicker.regional['ko']);     
	        
	            // Datepicker            
	            $(".datepicker").datepicker({
	                showButtonPanel: true,
	                dateFormat: "yy-mm-dd",
	                onClose : function ( selectedDate ) {
	                
	                    var eleId = $(this).attr("id");
	                    var optionName = "";

	                    if(eleId.indexOf("StartDate") > 0) {
	                        eleId = eleId.replace("StartDate", "EndDate");
	                        optionName = "minDate";
	                    } else {
	                        eleId = eleId.replace("EndDate", "StartDate");
	                        optionName = "maxDate";
	                    }

	                    $("#"+eleId).datepicker( "option", optionName, selectedDate );        
	                    $(".searchDate").find(".chkbox2").removeClass("on"); 
	                }
	            }); 
	            
	            $(".dateclick").dateclick();    // DateClick
	            $(".searchDate").schDate();
			});	// end of document.ready
			
			function loading(){
				setTimeout($('.mainframe .loader').show(), 1000);
			}
			
			// DateClick
	        jQuery.fn.dateclick = function(){
	            var $obj = $(this);
	            $obj.click(function(){
	                $(this).parent().find("input").focus();
	            });
	        }   
			
			function ctDetailFunction(emno){
				alert(emno);
				$("#emno").val(emno);
				$("#detailForm").attr("action", "/em/ctSelect.ge");
				$("#detailForm").submit();
			}
		</script>
	</head>
	<body>
		<div id="ge_menudiv">
			<div class="topinfo">
				<div class="myname"><%=emvo.getEmname()%> �� �ȳ��ϼ���.</div>
				<div class="logout" id="logout">logout</div>
			</div>	<!-- end of div-topinfo -->
		</div>	<!-- end of div-ge_menudiv -->
		<div class="ge_maindiv">
			<div class="topmenu">
				<div class="p">
					<a href="/board/bNList.ge">��������</a>��
					<a href="/board/calendar.ge">��������</a>��
					<a href="/em/emAllSelect.ge">�������</a>��
					<a href="/em/ctAllSelect.ge">���°���</a>��
					<a href="#">��������</a>��
					<a href="#">�λ���</a>
				</div>	<!-- end of div-p -->
			</div>	<!-- end of div-topmenu -->
			<div class="topdiv">
			</div>	<!-- end of div-topdiv -->
			<div class="outertable">
				<div id="scroll" style="position:absolute;">
					<div class="ge_sidemenu">
						<div class="ge_myinfo">
							<div class="imgbox">
                    		 	<img class="myimg" src="/em_Image/<%=emvo.getEmpic()%>" />
                     		</div>
                     		<br>
							<div class="itsme"><%=emvo.getEmname()%><br>(<%=emvo.getDeptname() %>/<%=emvo.getJobname() %>)</div>
						</div>	<!-- end of div-ge_myinfo -->
						
						<div class="clock" style="border:1px solid #cccccc; border-radius:1px; background:#D8D8D8; float:left; margin:10px 0 10px 0px; padding:5px 0 5px 0">
						<div align="center" style="font-size:20pt ;color:white; "class="dpTime" id="dpTime"></div>
						</div>	
						
					</div>	<!-- end of div-ge_sidemenu -->
				</div>	<!-- end of div-scroll -->
				
				<div class="mainframe">
					<form id="searchForm" name="searchForm">
						<input type="hidden" name="emno" id="emno">
					<div class="tit"><div class="title">&nbsp;���¸��</div></div>
					<div class="cal_container">
					<div class="searchBox">
						    <ul class="searchDate">
			                    <li>
			                        <span class="chkbox2">
			                            <input type="radio" name="dateType" id="dateType1" onclick="setSearchDate('0d')"/>
			                            <label for="dateType1">����</label>
			                        </span>
			                    </li>
			                    <li>
			                        <span class="chkbox2">
			                            <input type="radio" name="dateType" id="dateType2" onclick="setSearchDate('3d')"/>
			                            <label for="dateType2">3��</label>
			                        </span>
			                    </li>
			                    <li>
			                        <span class="chkbox2">
			                            <input type="radio" name="dateType" id="dateType3" onclick="setSearchDate('1w')"/>
			                            <label for="dateType3">1��</label>
			                        </span>
			                    </li>
			                    <li>
			                        <span class="chkbox2">
			                            <input type="radio" name="dateType" id="dateType4" onclick="setSearchDate('2w')"/>
			                            <label for="dateType4">2��</label>
			                        </span>
			                    </li>
			                    <li>
			                        <span class="chkbox2">
			                            <input type="radio" name="dateType" id="dateType5" onclick="setSearchDate('1m')"/>
			                            <label for="dateType5">1����</label>
			                        </span>
			                    </li>
			                    <li>
			                        <span class="chkbox2">
			                            <input type="radio" name="dateType" id="dateType6" onclick="setSearchDate('3m')"/>
			                            <label for="dateType6">3����</label>
			                        </span>
			                    </li>
			                    <li>
			                        <span class="chkbox2">
			                            <input type="radio" name="dateType" id="dateType7" onclick="setSearchDate('6m')"/>
			                            <label for="dateType7">6����</label>
			                        </span>
			                    </li>
			                </ul>
			         	</div>	<!-- end of div-searchBox --> 
						
					<div class="datesearch">
						<!-- select -->
							<select id="search" name="search">
								<option value="all">����</option>
								<option value="dateSearch" selected="selected">�Ⱓ</option>
							</select>
						<!-- search + " / " + ctstdate + " / " + cteddate -->
						<!-- ������ -->
						<span class="dset">
							<input type="text" class="datepicker inpType" id="ctstdate" name="ctstdate" value="<%=ctstdate%>">
							<a href="#none" class="btncalendar dateclick">�޷�</a>
						</span>
						<span class="demi">~</span>
						<!-- ������ -->
              			<span class="dset">
							<input type="text" class="datepicker inpType" id="cteddate" name="cteddate" value="<%=cteddate%>">
							<a href="#none" class="btncalendar dateclick">�޷�</a>
						</span>
							&nbsp;&nbsp;
		                	<input type="button" class="button" name="searchBt" id="searchBt" value="�˻�" />
		                	&nbsp;
		                	<input type="reset" class="button" name="datereset" id="datereset" value="����" />
		                	&nbsp;
		                	<input type="button" class="button" name="goToList" id="goToList" value="���" />
					</div>	<!-- end of div-datesearch -->
					</div>	<!-- end of div-cal_container -->
		                	
					<div class="formsearch">
						
				    </div>
					<div id="tableContainer">
					<table class="ct_List">
						<colgroup>
							<col width="15%">
							<col width="12%">
							<col width="12%">
							<col width="12%">
							<col width="14%">
							<col width="13%">
							<col width="14%">
							<col width="14%">
						</colgroup>
						<tr>
							<th>�ٹ���</th>
							<th>�̸�</th>
							<th>�μ�</th>
							<th>����</th>
							<th>��ٽð�</th>
							<th>��ٻ���</th>
							<th>��ٽð�</th>
							<th>��ٻ���</th>
						</tr>
<%
	if(listSize > 0){
		for(int i=0; i<listSize; i++){
			EmInfoVO evo = list.get(i);
%>						
						<tr>
							<td class="val" align="center"><%=evo.getCtupdatedate().substring(0, 10) %></td>
							<td class="val" align="center">
								<a href="#" onclick="ctDetailFunction('<%=evo.getEmno() %>')"><%=evo.getEmname() %></a>
							</td>
							<td class="val" align="center"><%=evo.getDeptname() %></td>
							<td class="val" align="center"><%=evo.getJobname() %></td>
							<td class="val" align="center">
<%
				if(evo.getCtintime()==null){
%>
					00:00
<%					
				}else{
%>							
					<%=evo.getCtintime() %>
<%
				}
%>
							</td>
							<td class="val" align="center">
<%
				if(evo.getCtincd().equals("51")){
%>
					���
<%
				}	// end of if(evo.getCtincd().equals("51"))
%>								
<%
				if(evo.getCtincd().equals("52")){
%>
					����
<%
				}	// end of if(evo.getCtincd().equals("52"))
%>								
<%
				if(evo.getCtincd().equals("53")){
%>
					��������
<%
				}	// end of if(evo.getCtincd().equals("53"))
%>								
<%
				if(evo.getCtincd().equals("54")){
%>
					����
<%
				}	// end of if(evo.getCtincd().equals("54"))
%>								
<%
				if(evo.getCtincd().equals("55")){
%>
					Ư���ް�
<%
				}	// end of if(evo.getCtincd().equals("55"))
%>								
<%
				if(evo.getCtincd().equals("58")){
%>
					���
<%
				}	// end of if(evo.getCtincd().equals("58"))
%>								
							</td>
							<td class="val" align="center">
<%
				if(evo.getCtouttime()==null){
%>
					00:00
<%					
				}else{
%>							
					<%=evo.getCtouttime() %>
<%
				}
%>
							</td>
							<td class="val" align="center">
<%
				if(evo.getCtoutcd().equals("56")){
%>
					���
<%
				}	// end of if(evo.getCtincd().equals("56"))
%>
<%
				if(evo.getCtoutcd().equals("57")){
%>
					����ٹ�
<%
				}	// end of if(evo.getCtincd().equals("56"))
%>
<%
				if(evo.getCtoutcd().equals("59")){
%>
					����
<%
				}	// end of if(evo.getCtincd().equals("59"))
%>
<%
				if(evo.getCtoutcd().equals("60")){
%>
					���Ĺ���
<%
				}	// end of if(evo.getCtincd().equals("60"))
%>
<%
				if(evo.getCtoutcd().equals("54")){
%>
					����
<%
				}	// end of if(evo.getCtincd().equals("54"))
%>								
<%
				if(evo.getCtoutcd().equals("55")){
%>
					Ư���ް�
<%
				}	// end of if(evo.getCtincd().equals("55"))
%>								
<%
				if(evo.getCtoutcd().equals("58")){
%>
					���
<%
				}	// end of if(evo.getCtincd().equals("58"))
%>		
							</td>
						<tr>
<%
		}	// end of for
	}	// end of if(listSize > 0)
%>			
					</table>	<!-- end of table-ctlist -->
						<div class="paging_container" style="position: botton:0px; margin:middle;">
							<jsp:include page="paging.jsp" flush="true" >
							<jsp:param name="url" value="/em/ctSearch.ge"/>
							<jsp:param name="str" value=""/>
							<jsp:param name="pageSize" value="<%=String.valueOf(pageSize) %>"/>
							<jsp:param name="groupSize" value="<%=String.valueOf(groupSize)%>"/>
							<jsp:param name="curPage" value="<%=String.valueOf(curPage) %>"/>
							<jsp:param name="totalCount" value="<%=String.valueOf(totalCount)%>"/>
							<jsp:param name="search" value="<%=String.valueOf(search) %>"/>
							<jsp:param name="ctstdate" value="<%=String.valueOf(ctstdate) %>"/>
							<jsp:param name="cteddate" value="<%=String.valueOf(cteddate) %>"/>
							</jsp:include>
						</div>	<!-- end of div-paging_container -->
					</div>	<!-- end of div-tableContainer -->
					</form>
				</div>	<!-- end of div-mainframe -->
			</div>	<!-- end of div-outertable -->
			<div class="bottom">
				<div class="bottominfo">
				GE Project �� ����� ���ʱ� ������� 459 (���ʵ�, ��Ϻ���)<br>
				TEL : 02-1234-5678 / FAX : 02-1234-5678<br>
				Copyright �� GE Project. All Rights Reserved.
				</div>	<!-- end of div-bottominfo -->
			</div>	<!-- end of div-bottom -->
		</div>	<!-- end of div-"ge_maindiv" -->
	</body>
</html>