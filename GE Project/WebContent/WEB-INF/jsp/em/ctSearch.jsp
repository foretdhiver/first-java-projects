<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>    
<%@ page import="java.util.ArrayList" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
<%
/* 사용자 정보 */
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

// 검색
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
		
/* 페이징을 위한 부분 */
// 한페이지에 보여질 게시물의 수 
int pageSize = 10;
// 그룹의 크기
int groupSize = 10;
// 현재 페이지
int curPage = 1;
// 전체 게시물의 개수
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
		<title>근태 목록</title>
		<link rel="stylesheet" type="text/css" href="/css/ctAllSelect.css" />
		<script type="text/javascript" src="/js/clock.js"></script>
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> 
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
		<script type="text/javascript">
			$(document).ready(function(){
				//alert("document.ready 함수 시작");
				$('.mainframe .loader').hide();
				
				setInterval("dpTime()",0);
				
				$('#logout').click(function(){
					if(confirm('로그아웃 하시겠습니까?')){
						loading();
						location.href='/ea/geLogOut.ge';
					}else{
					}
				});
				
				$("#nextBt").click(function(){
					//alert("로그 테이블로 넘기자");
					$(location).attr('href','/em/autoTask.ge');
				});
				$("#searchBt").click(function(){
					//alert("서치서치서ㅣㅊ");
					$("#searchForm").attr("action", "/em/ctSearch.ge");						
					$("#searchForm").submit();
					
				});
				$("#goToList").click(function(){
					//alert("서치서치서ㅣㅊ");
					$("#searchForm").attr("action", "/em/ctAllSelect.ge");						
					$("#searchForm").submit();
					
				});
				
				/* 날짜 search */
				$("#datesearch").click(function(){
					var st_date = $('#ctstdate').val();
					var ed_date = $('#cteddate').val();
					ctstdate = ctstdate.replace("-","");
					ctstdate = ctstdate.replace("-","");
					cteddate = cteddate.replace("-","");
					cteddate = cteddate.replace("-","");
					
					alert("시작일 : "+ctstdate+"// 종료일 : "+cteddate);  // 2020-03-12 이런형태 ->replace 후 20200312
				
					$('#ctstdate').val(ctstdate);
					$('#cteddate').val(cteddate);
					
					$("#searchForm").attr({
						"method":"POST",
						"action":"/em/ctSearch.ge"
					});
					$("#searchForm").submit();
				});
				
				/* 캘린더 */
				//datepicker 한국어로 사용하기 위한 언어설정
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
				<div class="myname"><%=emvo.getEmname()%> 님 안녕하세요.</div>
				<div class="logout" id="logout">logout</div>
			</div>	<!-- end of div-topinfo -->
		</div>	<!-- end of div-ge_menudiv -->
		<div class="ge_maindiv">
			<div class="topmenu">
				<div class="p">
					<a href="/board/bNList.ge">공지사항</a>｜
					<a href="/board/calendar.ge">일정관리</a>｜
					<a href="/em/emAllSelect.ge">사원관리</a>｜
					<a href="/em/ctAllSelect.ge">근태관리</a>｜
					<a href="#">교육사항</a>｜
					<a href="#">인사평가</a>
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
					<div class="tit"><div class="title">&nbsp;근태목록</div></div>
					<div class="cal_container">
					<div class="searchBox">
						    <ul class="searchDate">
			                    <li>
			                        <span class="chkbox2">
			                            <input type="radio" name="dateType" id="dateType1" onclick="setSearchDate('0d')"/>
			                            <label for="dateType1">당일</label>
			                        </span>
			                    </li>
			                    <li>
			                        <span class="chkbox2">
			                            <input type="radio" name="dateType" id="dateType2" onclick="setSearchDate('3d')"/>
			                            <label for="dateType2">3일</label>
			                        </span>
			                    </li>
			                    <li>
			                        <span class="chkbox2">
			                            <input type="radio" name="dateType" id="dateType3" onclick="setSearchDate('1w')"/>
			                            <label for="dateType3">1주</label>
			                        </span>
			                    </li>
			                    <li>
			                        <span class="chkbox2">
			                            <input type="radio" name="dateType" id="dateType4" onclick="setSearchDate('2w')"/>
			                            <label for="dateType4">2주</label>
			                        </span>
			                    </li>
			                    <li>
			                        <span class="chkbox2">
			                            <input type="radio" name="dateType" id="dateType5" onclick="setSearchDate('1m')"/>
			                            <label for="dateType5">1개월</label>
			                        </span>
			                    </li>
			                    <li>
			                        <span class="chkbox2">
			                            <input type="radio" name="dateType" id="dateType6" onclick="setSearchDate('3m')"/>
			                            <label for="dateType6">3개월</label>
			                        </span>
			                    </li>
			                    <li>
			                        <span class="chkbox2">
			                            <input type="radio" name="dateType" id="dateType7" onclick="setSearchDate('6m')"/>
			                            <label for="dateType7">6개월</label>
			                        </span>
			                    </li>
			                </ul>
			         	</div>	<!-- end of div-searchBox --> 
						
					<div class="datesearch">
						<!-- select -->
							<select id="search" name="search">
								<option value="all">선택</option>
								<option value="dateSearch" selected="selected">기간</option>
							</select>
						<!-- search + " / " + ctstdate + " / " + cteddate -->
						<!-- 시작일 -->
						<span class="dset">
							<input type="text" class="datepicker inpType" id="ctstdate" name="ctstdate" value="<%=ctstdate%>">
							<a href="#none" class="btncalendar dateclick">달력</a>
						</span>
						<span class="demi">~</span>
						<!-- 종료일 -->
              			<span class="dset">
							<input type="text" class="datepicker inpType" id="cteddate" name="cteddate" value="<%=cteddate%>">
							<a href="#none" class="btncalendar dateclick">달력</a>
						</span>
							&nbsp;&nbsp;
		                	<input type="button" class="button" name="searchBt" id="searchBt" value="검색" />
		                	&nbsp;
		                	<input type="reset" class="button" name="datereset" id="datereset" value="리셋" />
		                	&nbsp;
		                	<input type="button" class="button" name="goToList" id="goToList" value="목록" />
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
							<th>근무일</th>
							<th>이름</th>
							<th>부서</th>
							<th>직위</th>
							<th>출근시간</th>
							<th>출근상태</th>
							<th>퇴근시간</th>
							<th>퇴근상태</th>
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
					출근
<%
				}	// end of if(evo.getCtincd().equals("51"))
%>								
<%
				if(evo.getCtincd().equals("52")){
%>
					지각
<%
				}	// end of if(evo.getCtincd().equals("52"))
%>								
<%
				if(evo.getCtincd().equals("53")){
%>
					오전반차
<%
				}	// end of if(evo.getCtincd().equals("53"))
%>								
<%
				if(evo.getCtincd().equals("54")){
%>
					연차
<%
				}	// end of if(evo.getCtincd().equals("54"))
%>								
<%
				if(evo.getCtincd().equals("55")){
%>
					특별휴가
<%
				}	// end of if(evo.getCtincd().equals("55"))
%>								
<%
				if(evo.getCtincd().equals("58")){
%>
					결근
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
					퇴근
<%
				}	// end of if(evo.getCtincd().equals("56"))
%>
<%
				if(evo.getCtoutcd().equals("57")){
%>
					연장근무
<%
				}	// end of if(evo.getCtincd().equals("56"))
%>
<%
				if(evo.getCtoutcd().equals("59")){
%>
					조퇴
<%
				}	// end of if(evo.getCtincd().equals("59"))
%>
<%
				if(evo.getCtoutcd().equals("60")){
%>
					오후반차
<%
				}	// end of if(evo.getCtincd().equals("60"))
%>
<%
				if(evo.getCtoutcd().equals("54")){
%>
					연차
<%
				}	// end of if(evo.getCtincd().equals("54"))
%>								
<%
				if(evo.getCtoutcd().equals("55")){
%>
					특별휴가
<%
				}	// end of if(evo.getCtincd().equals("55"))
%>								
<%
				if(evo.getCtoutcd().equals("58")){
%>
					결근
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
				GE Project ｜ 서울시 서초구 강남대로 459 (서초동, 백암빌딩)<br>
				TEL : 02-1234-5678 / FAX : 02-1234-5678<br>
				Copyright ⓒ GE Project. All Rights Reserved.
				</div>	<!-- end of div-bottominfo -->
			</div>	<!-- end of div-bottom -->
		</div>	<!-- end of div-"ge_maindiv" -->
	</body>
</html>