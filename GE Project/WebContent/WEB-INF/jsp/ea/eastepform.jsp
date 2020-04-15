<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.spring.ge.vo.EaVO" %>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>
<%@ page import="java.util.Iterator"%>
<%  request.setCharacterEncoding("EUC-KR"); %>
<%
	/* ����� ���� */
	Object obj1 = session.getAttribute("eminfo");
	EmInfoVO emvo = null;
	emvo = new EmInfoVO();
	emvo = (EmInfoVO)obj1;
	String empic = emvo.getEmpic();
	Object obj2 = request.getAttribute("eadoccd");
	String eadoccd = obj2.toString();
	System.out.println("eastepform ������ eadoccd : " + eadoccd);
	
	Object obj = request.getAttribute("ea_lineno");
	System.out.println("obj : " + obj);
	List<EaVO> list = null;
	if(obj!=null){
		String ea_lineno = obj.toString();

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>eastepform.jsp</title>
		<link rel="stylesheet" type="text/css" href="/css/docform.css" />
		<script type="text/javascript" src="/js/clock.js"></script>
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> 
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
		<script type="text/javascript">
		
		$(document).ready(function(){
			$('.loader').hide(); 
			 $('#emjob').hide();
			 $('#emname').hide();
			 
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
			 
			 $.ajax({
					url:"/ea/getDeptList.ge",
					type:"POST",
					dataType:"json",
					error:function(){
						alert('error');
					},
					success : function(list){ 	
						console.log("list : " + list);
						console.log(JSON.stringify(list));
						var listdata = $.extend(true, [], list);
						console.log(listdata);
						console.log(listdata[0]);
							
						var oat = $("<option>"+"�μ��� �������ּ���"+"</option>");
						$('#emdept').append(oat);
						
						for(var i=0; i<listdata.length;i++){
							console.log("for.listdata :" + listdata[i]);
							console.log("stringfy : " + (JSON.stringify(listdata[i])));
							var deptname = listdata[i]['DEPTNAME'];
							var deptcd = listdata[i]['DEPTCD'];
							console.log("for.deptname["+i+"] : " + deptname);
							console.log("for.deptcd["+i+"] : " + deptcd);
							var opt = $("<option value='"+deptcd+"'>"+deptname+"</option>");
							$('#emdept').append(opt);
						}	
					}
				});

			 $("#emdept").change(function(){
				$('#emname').hide();
				$('#emjob').hide();
				
				var t = $('#emdept option:selected').val();
				console.log("t : " + t);
				
				$.ajax({
					url:"/ea/getJobList.ge",
					type:"POST",
					dataType:"json",
					data:{"deptcd":t},
					error:function(){
						alert('error');
					},
					success : function(list){ 
						console.log("list : " + list);
						console.log(JSON.stringify(list));
						var listdata = $.extend(true, [], list);
						console.log(listdata);
						console.log(listdata[0]);
						
						var oat = $("<option>"+"��å�� �������ּ���"+"</option>");
						$('#emjob').append(oat);
						
						for(var i=0; i<listdata.length;i++){
							console.log("for.listdata :" + listdata[i]);
							console.log("stringfy : " + (JSON.stringify(listdata[i])));
							var jobname = listdata[i]['JOBNAME'];
							var jobcd = listdata[i]['JOBCD'];
							console.log("for.jobname["+i+"] : " + jobname);
							console.log("for.jobcd["+i+"] : " + jobcd);
							var opt = $("<option value='"+jobcd+"'>"+jobname+"</option>");
							$('#emjob').append(opt);
						}	
					}
				});
				
				$('#emjob').find("option").remove();
				$('#emname').find("option").remove();
				$('#emjob').show();
			});

			 $('#emjob').change(function(){
				var t = $('#emjob option:selected').val();
				var s = $('#emdept option:selected').val();
				console.log("t : " + t);
				console.log("s : " + s);
				
				$.ajax({
					url:"/ea/getEmnameList.ge",
					type:"POST",
					dataType:"json",
					data:{"jobcd":t, "deptcd":s},
					error:function(){
						alert('error');
						var result = 'fail';
					},
					success : function(list){ 
						var result = 'success';
						
						console.log("list : " + list);
						console.log(JSON.stringify(list));
						var listdata = $.extend(true, [], list);
						console.log(listdata);
						console.log(listdata[0]);
						
						var oat = $("<option>"+"������� �������ּ���"+"</option>");
						$('#emname').append(oat);
						
						for(var i=0; i<listdata.length;i++){
							console.log("for.listdata :" + listdata[i]);
							console.log("stringfy : " + (JSON.stringify(listdata[i])));
							var emname = listdata[i]['EMNAME'];
							var emno = listdata[i]['EMNO'];
							var deptname = listdata[i]['DEPTNAME'];
							var jobname = listdata[i]['JOBNAME'];
							var jobcd = listdata[i]['JOBCD'];
							var deptcd = listdata[i]['DEPTCD'];
							
							console.log("for.emname["+i+"] : " + emname);
							console.log("for.emno["+i+"] : " + emno);
							var opt = $("<option value='"+emno+"'>"+emname+"</option>");
							
							$('#emname').append(opt);
						}	
						$('#emname').show();
					}
				});
				$('#emname').find("option").remove();
			});
			 
			 $('#emname').change(function(){
				 $("#emname option:selected").val();
				    var emname = $("#emname option:selected").text();
				    console.log('emname : ' + emname);
				    var emno = $("#emname option:selected").val();
				    var eminfo = emname+"("+emno+")";
				    
					/* �����ڿ� ���� �߰��� �� ���� �ϴ� validation */
			 		var em_name = $('#em_name').val();
					console.log("em_name : " + em_name);
					
			 		if(em_name==emname){
			 			alert('�� �ڽ��� �߰��� �� �����ϴ�.');
			 			return false;
			 		}
				    ////////////////////////////////////
				    
					if($('#emname_input').val() == ''){
						console.log('1st.input.empty.if >>>'); 
						$('#emname_input').val(eminfo);
						console.log('1st.inputbox.value : ' + $('#emname_input').val());
						$("#emname_input").attr("disabled",true);
					}else if($('#emname1_input').val()== '' && $('#emname_input').val()!= null){
						console.log('1st.full&&2nd.input.empty.if >>>'); 
						$('#emname1_input').val(eminfo);
						console.log('2nd.inputbox.value : ' + $('#emname1_input').val());
						$("#emname1_input").attr("disabled",true);
					}else if($('#emname_input').val()!= null && $('#emname1_input').val()!= null){
						console.log('1st.full&&2nd.full.if >>>'); 
						$('#emname2_input').val(eminfo);
						console.log('3nd.inputbox.value : ' + $('#emname2_input').val());
						$("#emname2_input").attr("disabled",true);
					}
					
					var ev = $('#emname1_input').val();
			 		var rpv = ev.replace(")","");
			 		console.log("replace test : " + rpv);
			 		var spv = rpv.split("(");
			 		for ( var i in spv ) {
			 			console.log("split test["+i+"] : " + spv[i]);
			 	    }
			 		
			 		var ev1 = $('#emname2_input').val();
			 		var rpv1 = ev1.replace(")","");
			 		console.log("replace test : " + rpv1);
			 		var spv1 = rpv1.split("(");
			 		for ( var i in spv1 ) {
			 			console.log("split test["+i+"] : " + spv1[i]);
			 	    }
			 		
			 		var empval = spv[1];
			 		$('#ea_empno1').val(empval);
			 		var empval1 = spv1[1];
			 		$('#ea_empno2').val(empval1);
			 });
			 		
			 		
			 $('#insertstepbt').click(function(){

				 var el = $("#ea_lineno").val();
				 var ed = $("#ea_doccd").val();
				 
				 if($('#emname1_input').val()==""){
						alert('ù ��° �����ڸ� �Է����ּ���.');
						return false;
					}else if($('#emname2_input').val()==""){
						alert('�� ��° �����ڸ� �Է����ּ���.');
						return false;
					}else if($('#emname2_input').val()!=""){
						if(confirm('���缱�� �Է��Ͻðڽ��ϱ�?')){
							loading();
							$("#eastepForm").attr("action", "/ea/eastepinsert.ge");
							$("#eastepForm").attr("enctype", "application/x-www-form-urlencoded");
							$("#eastepForm").attr("method", "POST");
							$("#eastepForm").submit();
						}else{
							// �ϰ͵�����
						}
					}
			 });
		});
		
		function eaback(){
			loading();
			history.go(-1);
		}
		
		
		function newEa(){
			loading();
			// alert('newEa �Լ�');
//			window.open("", "pop", "width=550, height=200");
			$("#ge_main").attr("action", '/ea/eadoccdform.ge');
			$("#ge_main").attr("enctype", "multipart/form-data");
			$("#ge_main").attr("method", "POST");
//			$("#ge_main").attr("target", "pop");
			$("#ge_main").submit();
		}
		
		function eaDocLib(){
			loading();
			// alert('eaDocLib �Լ�');
//			window.open("", "pop", "width=450, height=200");
			$("#ge_main").attr("action", '/ea/eadoclib.ge');
			$("#ge_main").attr("enctype", "multipart/form-data");
			$("#ge_main").attr("method", "POST");
//			$("#ge_main").attr("target", "pop");
			$("#ge_main").submit();
		}
		
		function loading(){
			setTimeout($('.loader').show(), 1000);
		}
		
		function insertEastep(ea_lineno, eadoccd){
		//	alert('insertEastep �Լ� ����');
			var err = $('#insertstepbts').val();
			var er = err.split(',');
			
			$("#ea_lineno").val(er[0]);
			$("#ea_doccd").val(er[1]);
			
			alert($("#ea_doccd").val());
			alert($("#ea_lineno").val());
			
			console.log('ea_lineno :  ' + $("#ea_lineno").val());
			console.log('ea_empno :  ' + $("#ea_empno").val() );
			console.log('ea_empno1 :  ' + $("#ea_empno1").val() );
			console.log('ea_empno2 :  ' + $("#ea_empno2").val() );
			/* ��ĭ�� ��� �μ�Ʈ �ȵǰ� �ϱ� */
			if($('#emname1_input').val()==""){
				alert('ù ��° �����ڸ� �Է����ּ���.');
				return false;
			}else if($('#emname2_input').val()==""){
				alert('�� ��° �����ڸ� �Է����ּ���.');
				return false;
			}else if($('#emname2_input').val()!=""){
				if(confirm('���缱�� �Է��Ͻðڽ��ϱ�?')){
					loading();
					$("#eastepForm").attr("action", "/ea/eastepinsert.ge");
					$("#eastepForm").attr("enctype", "application/x-www-form-urlencoded");
					$("#eastepForm").attr("method", "POST");
					$("#eastepForm").submit();
				}else{
					// �ϰ͵�����
				}
			}
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
			<form name="ge_main" id="ge_main">
				<input type="hidden" name="ea_stepno" id="ea_stepno">
				<input type="hidden" name="ea_no" id="ea_no" >
			</form>
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
							<br>
						</div>
						<div class="menubtn"><div class="mbtneff"></div><a href="#" onclick="newEa();"   style="color:#615F8D">�� ����</a></div>
						
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
						<a href="/ea/eamylistfn.ge" onclick="loading();">�Ϸ�</a><br>
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
					<div class="fitincenter">
					<form name="eastepForm" id="eastepForm">
						<input type="hidden" value="<%=emvo.getEmno() %>" id="ea_empno" name="ea_empno" >
						<input type="hidden" value="<%=emvo.getEmname()%>" id="em_name" name="em_name">
						<input type="hidden" value="<%=eadoccd%>" id="ea_doccd" name="ea_doccd" >
						<div class="tit"><div class="title">&nbsp;�� ����</div></div>
						<div class="docinfo">
						<br>
						���� �ۼ��� ���� ���缱�� �Է����ּ���.
						</div>
						<div class="ealinebuttonarray">
						<div class="ealineformback">
							<input type="button" class="button" id="ealinereset" value="����" onclick="eaback();"/>	
						</div>
						<div class="ealieformbuttonplace">
							<input type="button" class="button" id="insertstepbt" name="insertstepbt" value="����"/>		
						</div>
						</div>
						<table class="docform" >
							<tr>
								<th>���缱 �Է�</th>
							</tr>
							<tr>
								<td>
								<div class="stepselectbox">
								<select name="emdept" id="emdept" >
								</select>
								 
								<select id="emjob" name="emjob">
								</select>
						
								<select id="emname" name="emname">
								</select>
								</div>
								</td>
							</tr>
							<tr>
								<td>
								<input type="text" name="emname_input" id="emname_input" placeholder="ù ��° �����ڸ�" value="<%=emvo.getEmname()%>(<%=emvo.getEmno()%>)" disabled>   <!-- ù��° �����ڴ� �� �ڽ���. -->
								<input type="text" name="emname1_input" id="emname1_input" placeholder="�� ��° �����ڸ�">
								<input type="text" name="emname2_input" id="emname2_input" placeholder="�� ��° �����ڸ�">
								<input type="hidden" value="<%=ea_lineno%>" id="ea_lineno" name="ea_lineno" >
								<input type="hidden" value="<%=emvo.getEmno() %>" id="ea_empno" name="ea_empno" >
								<input type="hidden" value="" id="ea_empno1" name="ea_empno1" >
								<input type="hidden" value="" id="ea_empno2" name="ea_empno2" >
								<input type="hidden" value="<%=emvo.getEmname()%>" id="em_name" name="em_name">
								</td>
							</tr>
						</table>
					</form>	
					</div>
					</div>
<%
	}else{
		System.out.println("obj�ȿ� �ƹ��͵� ����.");
	}
%>
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
	</body>
</html>