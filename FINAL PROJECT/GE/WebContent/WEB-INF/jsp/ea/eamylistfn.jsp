<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.spring.ge.vo.EaVO" %>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>
<%@ page import="java.util.Iterator"%>
<%  request.setCharacterEncoding("EUC-KR"); %>  
<%
	String eastatus = "";
	String keyword ="";
	String ed_date = "";
	String st_date = "";
	
	Object obj0 = request.getAttribute("param");
	EaVO aaa = null;
	aaa = new EaVO();
	aaa = (EaVO)obj0;
	
	if(obj0==null){
		System.out.println("no param value");
	}else{
			keyword = aaa.getKeyword();
			ed_date = aaa.getEd_date();
			st_date = aaa.getSt_date();
			System.out.println("jsp keyword >> " + keyword);
			System.out.println("jsp ed_date >> " + ed_date);
			System.out.println("jsp st_date >> " + st_date);
	}
	/* ����� ���� */
	Object obj1 = session.getAttribute("eminfo");
	EmInfoVO emvo = null;
	emvo = new EmInfoVO();
	emvo = (EmInfoVO)obj1;
	String empic = emvo.getEmpic();
	
	/* controller���� �޾� �� list selectall */
	Object obj = request.getAttribute("eaList");
	//System.out.println("obj : " + obj);
	List<EaVO> ealist = null;
	ealist = (List<EaVO>)obj;
	System.out.println("ealist : " + ealist.size());
	/* ����¡�� ���� �κ� */
	int pageSize = 10;
	int groupSize = 10;
	int curPage = 1;
	int totalCount = 0;
	int pageNo = 0;
	
//	Object obj2 = request.getAttribute("groupSize");
	Object obj3 = request.getAttribute("curPage");
	Object obj4 = request.getAttribute("pageNo");
	Object obj5 = request.getAttribute("totalCount");
	
	String curPage1 = obj3.toString();
//	String groupSize1 = obj2.toString();
	String pageNo1 = obj4.toString();
	String totalCount1 = obj5.toString();
	
//	groupSize = Integer.parseInt(groupSize1);
	curPage = Integer.parseInt(curPage1);
	pageNo = Integer.parseInt(pageNo1);
	totalCount = Integer.parseInt(totalCount1);
	
	System.out.println("(eamylistfn.jsp)curPage : " + curPage);
	System.out.println("(eamylistfn.jsp)groupSize : " + groupSize);
	System.out.println("(eamylistfn.jsp)pageNo : " + pageNo);
	System.out.println("(eamylistfn.jsp)totalCount : " + totalCount);	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>eamylistfn.jsp</title>
		<link rel="stylesheet" type="text/css" href="/css/eamylistall.css" />
		<script type="text/javascript" src="/js/clock.js"></script>
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> 
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
		<script type="text/javascript">
			$(document).ready(function(){
				$('.mainframe .loader').hide(); 
//				$('#ea_mylist td').click(function(){
//					alert($(this).text());
//				});
				setInterval("dpTime()",0);

				$('#logout').click(function(){
					if(confirm('�α׾ƿ� �Ͻðڽ��ϱ�?')){
						loading();	
						location.href='/ea/geLogOut.ge';
					}else{
					}
					
				});
				
				$('#tomain').click(function(){
					loading();	
					location.href='/ea/eamain.ge';
				});
				$('.goDetail').click(function(){
					var r_val = $(this).parents("tr").attr("data-number");
//					alert('r_val : ' + r_val);
					var r_arr = r_val.split(',');	
//					alert("r_arr[0] : " + r_arr[0]);
//					alert("r_arr[1] : " + r_arr[1]);
					$("#ea_no").val(r_arr[0]);
					$("#ea_stepno").val(r_arr[1]);
					
					loading();	
					
					$("#ea_detailsend").attr({
						"method":"post",
						"action":"/ea/eadetail.ge"
					});
					
					$("#ea_detailsend").submit();
				});
				
				/* ��¥ search */
				$("#datesearch").click(function(){
					var st_date = $('#searchStartDate').val();
					var ed_date = $('#searchEndDate').val();
					st_date = st_date.replace("-","");
					st_date = st_date.replace("-","");
					ed_date = ed_date.replace("-","");
					ed_date = ed_date.replace("-","");
					
					alert("������ : "+st_date+"// ������ : "+ed_date);  // 2020-03-12 �̷����� ->replace �� 20200312
				
					$('#st_date').val(st_date);
					$('#ed_date').val(ed_date);
					$('#keyword').val("<%=keyword%>");
					
					loading();	
					
					$("#ea_mylistform").attr({
						"method":"POST",
						"action":"/ea/eamylistfnsearch.ge"
					});
					$("#ea_mylistform").submit();
				});
				
				/* ��Ӵٿ�ڽ� search */
				$("#search").change(function(){
					var search = $('#search option:selected').val();
					alert(search);
					
					$('#st_date').val("<%=st_date%>");
					$('#ed_date').val("<%=ed_date%>");
					$('#keyword').val(search);
					
					loading();	
					
					$("#ea_mylistform").attr({
						"method":"post",
						"action":"/ea/eamylistfnsearch.ge"
					});
					$("#ea_mylistform").submit();
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
			});
			
			function newEa(){
				loading();	
				// alert('newEa �Լ�');
//				window.open("", "pop", "width=550, height=200");
				$("#ea_detailsend").attr("action", '/ea/eadoccdform.ge');
				$("#ea_detailsend").attr("enctype", "multipart/form-data");
				$("#ea_detailsend").attr("method", "POST");
//				$("#ea_detailsend").attr("target", "pop");
				$("#ea_detailsend").submit();
			}
			
			function eaDocLib(){
				loading();	
				// alert('eaDocLib �Լ�');
//				window.open("", "pop", "width=450, height=200");
				$("#ea_detailsend").attr("action", '/ea/eadoclib.ge');
				$("#ea_detailsend").attr("enctype", "multipart/form-data");
				$("#ea_detailsend").attr("method", "POST");
//				$("#ea_detailsend").attr("target", "pop");
				$("#ea_detailsend").submit();
			}
			
			function loading(){
				setTimeout($('.mainframe .loader').show(), 1000);
			}
			
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
		<!-- <form name="ge_main" id="ge_main">
				<input type="hidden" name="ea_stepno" id="ea_stepno">
				<input type="hidden" name="ea_no" id="ea_no" >
			</form> -->
				<div class="topmenu">
				<div class="p">
					<a href="/ea/gemain.ge" onclick="loading();">����</a>��
					<a href="/ea/eamain.ge" style="color:#615F8D" onclick="loading();">����</a>
					<a href="/ea_/eamain.ge" style="color:#615F8D" onclick="loading();">����</a>��
					<a href="#">���ڸ���</a>��
					<a href="#">������</a>��
					<a href="/board/boardMain.ge"  onclick="loading();">�μ��Խ���</a>��
					<a href="/board/calendar.ge"  onclick="loading();">��������</a>��
					<a href="#" >������</a>��
					<a href="/em/myPageInfo.ge"  onclick="loading();">����������</a></div>
				</div>
				<div class="topdiv">
				</div>
				<div class="outertable">
					<div id="scroll" style="position:absolute;">
					<div class="ge_sidemenu">
						<div class="ge_myinfo">
							<div class="imgbox">
							<img class="myimg" src="/em_Image<%=empic%>" />
							</div>
							<br>
							<div class="itsme"><%=emvo.getEmname()%><br>(<%=emvo.getDeptname() %>/<%=emvo.getJobname() %>)</div>
						
						</div>
						<div class="menubtn"><div class="mbtneff"></div><a href="#" onclick="newEa();">�� ����</a></div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamyapplist.ge" onclick="loading();">�����û����</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamylistall.ge" onclick="loading();">��ü</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamylistpg.ge" onclick="loading();">����</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamylistsb.ge" onclick="loading();">���</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamylistfn.ge"  onclick="loading();"style="color:#615F8D">�Ϸ�</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eamylistrt.ge" onclick="loading();">�ݷ�</a><br>
						</div>
						
						<div class="menubtn">
						<div class="mbtneff"></div>
						<a href="/ea/eadoclib.ge" onclick="loading();">����ڷ��</a><br>
						</div>
						
						<div class="clock" style="border:1px solid #cccccc; border-radius:1px; background:#D8D8D8; float:left; margin:10px 0 10px 0px; padding:5px 0 5px 0">
						<div align="center" style="font-size:20pt ;color:white; "class="dpTime" id="dpTime"></div>
						</div>	
					</div>
					</div>
						<div class="mainframe">
	<form id="ea_detailsend" name="ea_detailsend">
		<input type="hidden" name="ea_no" id="ea_no">
		<input type="hidden" name="ea_stepno" id="ea_stepno">
	</form>
		<form id="ea_mylistform" name="ea_mylistform">
			<div class="tit"><div class="title">&nbsp;MY LIST : �Ϸ�</div></div>
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
                </ul>
	        </div>
	        <div class="datesearch">
                <!-- ������ -->
                <span class="dset">
                    <input type="text" class="datepicker inpType" name="searchStartDate" id="searchStartDate" >
                    <a href="#none" class="btncalendar dateclick">�޷�</a>
                </span>
                <span class="demi">~</span>
                <!-- ������ -->
                <span class="dset">
                    <input type="text" class="datepicker inpType" name="searchEndDate" id="searchEndDate" >
                    <a href="#none" class="btncalendar dateclick">�޷�</a>
                </span>
                	&nbsp;&nbsp;
                	<input type="button" class="button" name="datesearch" id="datesearch" value="�˻�" />
                	&nbsp;
                	<input type="reset" class="button" name="datereset" id="datereset" value="����" />
            </div> <!-- end of datesearch  --> 
            </div>
            <div class="formsearch">
				<select id="search" name="search">
					<option>������İ˻�</option>
					<option value="H">�ް���</option>
					<option value="P">������Ʈ��ȼ�</option>
					<option value="R">ǰ�Ǽ�</option>
					<option value="S">�����������</option>
				</select>
				<input type="hidden" name="keyword" id="keyword">
				<input type="hidden" name="st_date" id="st_date">
				<input type="hidden" name="ed_date" id="ed_date">
		    </div>
		    <!-- <div class="tomain" id="tomain">main</div>  -->
            <div class="tableContainer">
			<table class="ea_mylist">
				<colgroup>
					<col width="12%">
					<col width="10%">
					<col width="25%">
					<col width="5%">
					<col width="10%">
					<col width="5%">
				</colgroup>
				<tr>
					<th>N0</th>
					<th>FORM</th>
					<th>SUBJECT</th>
					<th>NAME</th>
					<th>DATE</th>
					<th>STATUS</th>
					
				</tr>
<% 
	if(ealist.size()>0){
		int listCnt = ealist.size();
		System.out.println("listCnt : " + listCnt);
	for(int i=0;i<listCnt;i++)
	{ EaVO evo = (EaVO)ealist.get(i);
	eastatus = evo.getEa_status();
%>
				<tr data-number="<%=evo.getEa_no() %>,<%=evo.getEa_stepno() %>">
					<td class="val" id="ea_no" align="center"><%=evo.getEa_no() %></td>
					<td class="val" id="ea_doccd" align="center">
						<%if(evo.getEa_doccd().equals("H")){
							%>�ް���<%
						}else if(evo.getEa_doccd().equals("P")){
							%>������Ʈ��ȼ�<%
						}else if(evo.getEa_doccd().equals("R")){
							%>ǰ�Ǽ�<%
						}else if(evo.getEa_doccd().equals("S")){
							%>�����������<%
						} %>
					</td>
					<td class="val" id="ea_subject"><span class="goDetail"><%=evo.getEa_subject() %></span></td>
					<td class="val" id="emname" align="center"><%=emvo.getEmname() %></td>
					<td class="val" id="ea_insertdate"><%=evo.getEa_insertdate() %></td>
					<td class="val" id="ea_status" align="center">
						<%if(evo.getEa_status().equals("01")){
							%>ǰ��<%
						}else if(evo.getEa_status().equals("02")){
							%>����<%
						}else if(evo.getEa_status().equals("03")){
							%>��������<%
						}else if(evo.getEa_status().equals("04")){
							%>�ݷ�<%
						}else if(evo.getEa_status().equals("05")){
							%>���<%
						}else if(evo.getEa_status().equals("06")){
							%>���<%
						}else if(evo.getEa_status().equals("07")){
							%>����<%
						} %>
					</td>
				</tr>				
<%
	}
	}else{
%>
				<tr>
					<td colspan="6">��ȸ�� ������ �����ϴ�.</td>
				</tr>

<%
	}
%>		
				
			</table>
			<div class="paging_container" style="position: botton:0px; margin:middle;">
				<jsp:include page="paging.jsp" flush="true" >
				<jsp:param name="url" value="/ea/eamylistall.ge"/>
				<jsp:param name="str" value=""/>
				<jsp:param name="pageSize" value="<%=String.valueOf(pageSize) %>"/>
				<jsp:param name="groupSize" value="<%=String.valueOf(groupSize)%>"/>
				<jsp:param name="curPage" value="<%=String.valueOf(curPage) %>"/>
				<jsp:param name="totalCount" value="<%=String.valueOf(totalCount)%>"/>
				</jsp:include>
			</div>
			</div> <!-- end of tableContainer ���⳻���� ����־��.. -->
		</form>
				<div class="loader">
						<img src="/ge/loader.gif">
					</div>
				</div>  <!-- mainframe -->
			</div>
				
			<div class="bottom">
			<div class="bottominfo">
			GE Project �� ����� ���ʱ� ������� 459 (���ʵ�, ��Ϻ���)<br>
			TEL : 02-1234-5678 / FAX : 02-1234-5678<br>
			Copyright �� GE Project. All Rights Reserved.
			</div>
			</div>
		</div>
	</body>
</html>