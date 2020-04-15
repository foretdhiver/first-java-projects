<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List" %>
<%@ page import="com.spring.ge.vo.EaVO_" %>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>
<%  request.setCharacterEncoding("EUC-KR"); %> 
<%
Object obj = session.getAttribute("eminfo");
EmInfoVO emvo = null;
emvo = new EmInfoVO();
emvo = (EmInfoVO)obj;
String emno = emvo.getEmno();
String empic = emvo.getEmpic();
// String emno = request.getParameter("emno");
System.out.println("������ ���  : " + emno);

String keyword = null;
String st_date = null;
String ed_date = null;
// String datesearch = null;

Object key = request.getAttribute("keyword");
Object st = request.getAttribute("st_date");
Object ed = request.getAttribute("ed_date");
// Object search = request.getParameter("datesearch");

if(key == null){
	System.out.println("Ű ����");
}else{
	System.out.println("Ű ��");
	keyword = String.valueOf(key);
	System.out.println("Ű Ȯ�� : " + keyword);
}

if(st == null){
	System.out.println("���۽ð� ����");
}else{
	System.out.println("���۽ð� ��");
	st_date = String.valueOf(st);
	System.out.println("���۽ð� Ȯ�� : " + st_date);
}

if(ed == null){
	System.out.println("����ð� ����");
}else{
	System.out.println("����ð� ��");
	ed_date = String.valueOf(ed);
	System.out.println("����ð� Ȯ�� : " + ed_date);
}

//if(search == null){
//	System.out.println("�˻�x");
//}else{
//	System.out.println("if ���ǹ� ����");
//	ed_date = String.valueOf(search);
//	System.out.println("if ���ǹ� Ȯ�� : " + datesearch);
//}

// selectAll	
Object obj0 = request.getAttribute("eaList");
List<EaVO_> eaList = (List<EaVO_>) obj0;
System.out.println("eaList" + eaList);
System.out.println("eaList ������" + eaList.size());

// ����¡
int pageSize = 10;
int groupSize = 10;
int curPage = 1;
int totalCount = 0;
int pageNo = 0;

//Object obj2 = request.getAttribute("groupSize");
Object obj3 = request.getAttribute("curPage");
Object obj4 = request.getAttribute("pageNo");
Object obj5 = request.getAttribute("totalCount");

//String groupSize1 = obj2.toString();
String curPage1 = obj3.toString();
String pageNo1 = obj4.toString();
String totalCount1 = obj5.toString();

//groupSize = Integer.parseInt(groupSize1);
curPage = Integer.parseInt(curPage1);
pageNo = Integer.parseInt(pageNo1);
totalCount = Integer.parseInt(totalCount1);

//System.out.println("groupSize : " + groupSize);
System.out.println("curPage : " + curPage);
System.out.println("pageNo : " + pageNo);
System.out.println("totalCount : " + totalCount);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>::: GE PROJECT :::</title>
	<link rel="stylesheet" type="text/css" href="/css/eaList.css" />
	<script type="text/javascript" src="/js/clock.js"></script>
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> 
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
	<script type="text/javascript">
		$(document).ready(function(){
			$('.mainframe1 .loader').hide();
			//	alert($('#ea_stepno').val());
				setInterval("dpTime()",0);
				
				$('#logout').click(function(){
					if(confirm('�α׾ƿ� �Ͻðڽ��ϱ�?')){
						loading();
						location.href='/';
					}else{
					}
				});
				
				$(".goDetail").click(function(){
//	           		alert("�Խñ��� ��ȸ�մϴ�.");
	           		var r_val = $(this).parents("tr").attr("data-number");
//	           		alert(r_val);
	           		var r_arr = r_val.split(',');
//	           		alert(r_arr[0]);
//	           		alert(r_arr[1]);
	           		$("#ea_no").val(r_arr[0]);
	           		$("#ea_stepno").val(r_arr[1]);
	           		loading();
	           		$("#eadetailSend").attr("action", "/ea_/eaReadDetail.ge").submit();
	           	});
	       		
	       		/* ��¥ search */
				$("#datesearch").click(function(){
					var st_date = $('#searchStartDate').val();
					var ed_date = $('#searchEndDate').val();
					st_date = st_date.replace("-","");
					st_date = st_date.replace("-","");
					ed_date = ed_date.replace("-","");
					ed_date = ed_date.replace("-","");
					
//					alert("������ : "+st_date+"// ������ : "+ed_date);  // 2020-03-12 �̷����� ->replace �� 20200312
				
					$('#st_date').val(st_date);
					$('#ed_date').val(ed_date);
					$('#keyword').val("<%=keyword%>");
					
					$("#eaSearchForm").attr("action", "/ea_/eaListSearch.ge").submit();
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
	            $(".searchDate").schDate();        // searchDate
			});
			
			// Search Date
	        jQuery.fn.schDate = function(){
	            var $obj = $(this);
	            var $chk = $obj.find("input[type=radio]");
	            $chk.click(function(){                
	                $('input:not(:checked)').parent(".chkbox2").removeClass("on");
	                $('input:checked').parent(".chkbox2").addClass("on");                    
	            });
	        };

	        // DateClick
	        jQuery.fn.dateclick = function(){
	            var $obj = $(this);
	            $obj.click(function(){
	                $(this).parent().find("input").focus();
	            });
	        }    
	        
	        function setSearchDate(start){

	            var num = start.substring(0,1);
	            var str = start.substring(1,2);

	            var today = new Date();

	            //var year = today.getFullYear();
	            //var month = today.getMonth() + 1;
	            //var day = today.getDate();
	            
	            var endDate = $.datepicker.formatDate('yy-mm-dd', today);
	            $('#searchEndDate').val(endDate);
	            
	            if(str == 'd'){
	                today.setDate(today.getDate() - num);
	            }else if (str == 'w'){
	                today.setDate(today.getDate() - (num*7));
	            }else if (str == 'm'){
	                today.setMonth(today.getMonth() - num);
	                today.setDate(today.getDate() + 1);
	            }

	            var startDate = $.datepicker.formatDate('yy-mm-dd', today);
	            $('#searchStartDate').val(startDate);
	                    
	            // �������� ������ ���� ��¥ �������� ���ϵ��� ��Ȱ��ȭ
	            $("#searchEndDate").datepicker( "option", "minDate", startDate );
	            
	            // �������� ������ ���� ��¥ �������� ���ϵ��� ��Ȱ��ȭ
	            $("#searchStartDate").datepicker( "option", "maxDate", endDate );
	        }
			

			function newEa(){
				// alert('newEa �Լ�');
				loading();
				window.open("", "pop", "width=700, height=200");
				
				$("#ge_main").attr("target", "pop");
				$("#ge_main").attr("action", '/ea_/gotoInsert.ge');
				$("#ge_main").submit();
			}
			
			function loading(){
				setTimeout($('.mainframe1 .loader').show(), 1000);
			}

	</script>
	</head>
	<body>
		<div id="ge_menudiv">
			<div class="topinfo">
				<div class="myname"><%=emvo.getEmname()%> �� �ȳ��ϼ���.</div>
				<div class="logout" id="logout">logout</div>
			</div>
		</div>
		<div class="ge_maindiv">
			<form name="eadetailSend" id="eadetailSend" method="post">
				<input type="hidden" name="ea_stepno" id="ea_stepno">
				<input type="hidden" name="ea_no" id="ea_no" >
			</form>
				<div class="topmenu">
					<div class="p">
					<a href="#">����</a>��
					<a href="/ea/eamain.ge" style="color:#615F8D">����</a>
					<a href="/ea_/eamain.ge" style="color:#615F8D">����</a>��
					<a href="#">���ڸ���</a>��
					<a href="#">������</a>��
					<a href="/board/boardMain.ge"  onclick="loading();">�μ��Խ���</a>��
					<a href="/board/calendar.ge"  onclick="loading();">��������</a>��
					<a href="#">������</a>��
					<a href="/em/myPageInfo.ge">����������</a></div>
				</div> <!-- topmenu -->
				<div class="topdiv"></div> <!-- topdiv -->
				<div class="outertable">
					<div class="ge_sidemenu">
						<div class="ge_myinfo1">
							<div class="imgbox">
							<img class="myimg" src="/em_Image<%=empic%>" />
							</div> <!-- imgbox -->
							<br>
							<div class="itsme"><%=emvo.getEmname()%><br>(<%=emvo.getDeptname() %>/<%=emvo.getJobname() %>)</div>
						</div> <!-- ge_myinfo -->
						<div class="menubtn"><div class="mbtneff"></div><a href="#" onclick="newEa();">�� ����</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaListAll.ge">��ü �����û��</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaWriteAll.ge">�� ��ü������</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaWritePG.ge">�� ���������</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaWriteRJ.ge">�� �ݷ�������</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaWriteST.ge">�� ��������</a></div>
						<div class="menubtn"><div class="mbtneff"></div><a href="/ea_/eaWriteEnd.ge">�� �Ϸ������</a></div>
						<div class="clock" style="border:1px solid #cccccc; border-radius:1px; background:#D8D8D8; float:left; margin:10px 0 10px 0px; padding:5px 0 5px 0">
						<div align="center" style="font-size:20pt ;color:white; "class="dpTime" id="dpTime"></div>
						</div>	
					</div> <!-- ge_sidemenu -->
						<div class="mainframe1">
							<form id="eaSearchForm" name="eaSearchForm" method="POST">
								<input type="hidden" id="st_date" name="st_date">
								<input type="hidden" id="ed_date" name="ed_date">
								<input type="hidden" id="keyword" name="keyword">
							<div class="tit1"><div class="title">&nbsp;�����û ����Ʈ</div></div> <!-- tit -->
							<div class="cal_container">
							<div class="searchBox" >
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
				                </ul> <!-- searchDate -->
					        </div> <!-- searchBox -->

					        <div class="datesearch">
				                <!-- ������ -->
				                <span class="dset">
				                    <input type="text" class="datepicker inpType" name="searchStartDate" id="searchStartDate"">
				                    <a href="#none" class="btncalendar dateclick">�޷�</a>
				                </span>
				                <span class="demi">~</span>
				                <!-- ������ -->
				                <span class="dset">
				                    <input type="text" class="datepicker inpType" name="searchEndDate" id="searchEndDate">
				                    <a href="#none" class="btncalendar dateclick">�޷�</a>
				                </span>
				                	&nbsp;&nbsp;
				                	<input type="button" class="button" name="datesearch" id="datesearch" value="�˻�" />
				                	&nbsp;
				                	<input type="reset" class="button" name="datereset" id="datereset" value="����" />
				            </div> <!-- end of datesearch  --> 
				            </div> <!-- cal_container -->
				            <div class="tableContainer1">
				           		<table class="ea_mylist1">
					            	<colgroup>
										<col width="12%">
						           		<col width="10%">
						           		<col width="25%">
						           		<col width="5%">
						           		<col width="5%">
						           		<col width="10%">
									</colgroup>
									<tr>
										<th>������ȣ</th>
										<th>��������</th>
										<th>����</th>
										<th>�������</th>
										<th>�ۼ��ڸ�</th>
										<th>�ۼ���</th>
									</tr>
<%
	if(eaList.size() > 0){
		for(int i=0; i < eaList.size(); i++){
			EaVO_ evo = (EaVO_)eaList.get(i);
			String status = evo.getEa_status();
			if("01".equals(status)){
				status = "ǰ��";
			}else if("02".equals(status)){
				status = "����";
			}else if("03".equals(status)){
				status = "��������";
			}else if("04".equals(status)){
				status = "�ݷ�";
			}else if("05".equals(status)){
				status = "���";
			}else if("06".equals(status)){
				status = "���";
			}else if("07".equals(status)){
				status = "����";
			}
			
			String doccd = evo.getEa_doccd();
			if("P".equals(doccd)){
				doccd = "������Ʈ ��ȼ�";
			}else if("H".equals(doccd)){
				doccd = "�ް���";
			}else if("R".equals(doccd)){
				doccd = "ǰ�Ǽ�";
			}else if("S".equals(doccd)){
				doccd = "������� ����";
			}
			
%>
									<tr data-number="<%=evo.getEa_no()%>,<%=evo.getEa_stepno()%>">
										<td class="val" id="ea_no" align="center"><%=evo.getEa_no() %></td>
										<td class="val" id="ea_doccd"><%=doccd%></td>
										<td class="val" id="ea_subject" align="center"><span class="goDetail"><%=evo.getEa_subject() %></span></td>
										<td class="val" id="ea_status" align="center"><%=status%></td>
										<td class="val" id="emname" align="center"><%=evo.getEmname() %></td>
										<td class="val" id="ea_insertdate"><%=evo.getEa_insertdate() %></td>
									</tr>
<%
		}
	}else{
%>
									<tr>
										<td align="center" colspan="6"> ������ ������ �����ϴ�. </td>
									</tr>
<%
	}
%>
					            </table> <!-- ea_mylist -->
					            <div class="paging_container" style="position: botton:0px; margin:middle;">
									<jsp:include page="paging.jsp" flush="true" >
									<jsp:param name="url" value="/ea_/eaListAll.ge"/>
									<jsp:param name="str" value=""/>
									<jsp:param name="pageSize" value="<%=String.valueOf(pageSize) %>"/>
									<jsp:param name="groupSize" value="<%=String.valueOf(groupSize)%>"/>
									<jsp:param name="curPage" value="<%=String.valueOf(curPage) %>"/>
									<jsp:param name="totalCount" value="<%=String.valueOf(totalCount)%>"/>
									<jsp:param name="st_date" value="<%=String.valueOf(st_date)%>" />
									<jsp:param name="ed_date" value="<%=String.valueOf(ed_date)%>" />
									<jsp:param name="keyword" value="<%=String.valueOf(keyword)%>" />
									</jsp:include>
								</div> <!-- paging_container -->
							</div> <!-- tableContainer -->
							</form> <!-- eaSearchForm -->
							<div class="loader">
						     	<img src="/ge/loader.gif">
						    </div>
						</div>  <!-- mainframe -->
				</div> <!-- "outertable" -->
				<div class="bottom">
				<div class="bottominfo">
				GE Project �� ����� ���ʱ� ������� 459 (���ʵ�, ��Ϻ���)<br>
				TEL : 02-1234-5678 / FAX : 02-1234-5678<br>
				Copyright �� GE Project. All Rights Reserved.
				</div> <!-- bottominfo -->
				</div> <!-- bottom -->
		</div> <!-- ge_maindiv -->
	</body>
</html>