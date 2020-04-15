<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List"%>
<%@ page import="com.spring.ge.vo.EaVO_" %>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>
<%  request.setCharacterEncoding("EUC-KR"); %>  
<% 

	Object object = session.getAttribute("eminfo");
	EmInfoVO emvo = new EmInfoVO();
	emvo = (EmInfoVO)object;

	Object obj = request.getAttribute("ea_lineno");
	Object obj1 = request.getAttribute("send");

	List<EaVO_> list = null;
	if(obj != null){
		String ea_lineno = obj.toString();
		String send = obj1.toString();
		
		System.out.println("[log] ea_lineno " + ea_lineno);
		System.out.println("[log] send " + send);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>���罺�� �ۼ� ������</title>
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/docform.css" />
		<script type="text/javascript">
		
		$(document).ready(function(){
			 $('#emjob').hide();
			 $('#emname').hide();
			 
			 $.ajax({
					url:"/ea_/getDeptList.ge",
					type:"POST",
					dataType:"json",
					error:function(){
						alert('��� ���� error');
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
						url:"/ea_/getJobList.ge",
						type:"POST",
						dataType:"json",
						data:{"deptcd":t},
						error:function(){
							alert('���� ���� error');
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
						url:"/ea_/getEmnameList.ge",
						type:"POST",
						dataType:"json",
						data:{"jobcd":t, "deptcd":s},
						error:function(){
							alert('ã��;� error');
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
		});
		
		function insertStep(ea_lineno){
			//	alert('insertEastep �Լ� ����');
				$("#ea_lineno").val(ea_lineno);
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
						$("#stepForm").attr("action", "/ea_/eaInsertStep.ge");
						$("#stepForm").submit();
					}else{
						// �ϰ͵�����
					}
				}
			}
		</script>
	</head>
	<body>
		<div class="fitincenter">
		<form id="stepForm" name="stepForm" method="POST" accept-charset="EUC-KR">
			<table class="docform">
				<div class="tit"><div class="title">���缱 ����</div></div>
				<div class="docform">
				<tr>
					<th>
						<div class="stepselectbox">
						<select name="emdept" id="emdept"></select>
						<select name="emjob" id="emjob"></select>
						<select name="emname" id="emname"></select>
						</div>
					</th>
				</tr>
				<tr>
					<td>
						<input type="text" id="emname_input" name="emname_input" placeholder="ù ��° ������"
						value="<%=emvo.getEmname()%>(<%=emvo.getEmno()%>)" disabled>
						<input type="text" id="emname1_input" name="emname1_input" placeholder="�� ��° ������">
						<input type="text" id="emname2_input" name="emname2_input" placeholder="�� ��° ������">
						<input type="hidden" id="ea_lineno" name="ea_lineno" value=<%=ea_lineno %>>
						<input type="hidden" id="ea_empno" name="ea_empno" value=<%=emvo.getEmno() %>>
						<input type="hidden" id="ea_empno1" name="ea_empno1">
						<input type="hidden" id="ea_empno2" name="ea_empno2">
						<input type="hidden" id="ea_name" name="ea_name" value="<%=emvo.getEmname() %>">
						<input type="hidden" id="send" name="send" value=<%=send %>>
					</td>
				</tr>
				<tr>
					<td align="center">
						<input type="button" class="button" onclick="insertStep('<%=ea_lineno %>');" value="����"/>
						<input type="reset" class="button" id="stepReset" value="�ٽ�" onclick=""/>
					</td>
				</tr>
			</table>
		</form>
		</div>
		</div>
<%
	}else{
		System.out.println("obj �ȿ� �ƹ��͵� ����");
}
%>
	</body>
</html>