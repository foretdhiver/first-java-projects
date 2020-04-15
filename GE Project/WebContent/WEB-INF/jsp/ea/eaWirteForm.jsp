<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List"%>
<%@ page import="com.spring.ge.vo.EaVO_" %>
<%@ page import="com.spring.ge.vo.EmInfoVO" %>
<%
	Object object = session.getAttribute("eminfo");
	EmInfoVO em_info = (EmInfoVO) object;
	
	String emname = em_info.getEmname();
	String emno = em_info.getEmno();
	String deptname = em_info.getDeptname();
	String jobname = em_info.getJobname();
	
	Object obj = request.getAttribute("ea_lineno");
	Object obj1 = request.getAttribute("ea_stepno");
	Object obj2 = request.getAttribute("ea_empno1list");
	Object obj3 = request.getAttribute("ea_empno2list");
	
	List<EmInfoVO> ealist = (List<EmInfoVO>)obj2;
	List<EmInfoVO> ealist1 = (List<EmInfoVO>)obj3;
	
	String emname1 = "";
	String emname2 = "";
	String jobname1 = "";
	String jobname2 = "";
	String deptname1 = "";
	String deptname2 = "";
	String emno1 = "";
	String emno2 = "";
	
	if(ealist.size()>0){
		int listCnt = ealist.size();
		System.out.println("listCnt : " + listCnt);
	for(int i=0;i<listCnt;i++)
	{ EmInfoVO emvo1 = (EmInfoVO)ealist.get(i);
		emname1 = emvo1.getEmname();
		jobname1 = emvo1.getJobname();
		deptname1 = emvo1.getDeptname();
		emno1 = emvo1.getEmno();
	}
	}
	if(ealist1.size()>0){
		int listCnt = ealist1.size();
		System.out.println("listCnt : " + listCnt);
	for(int i=0;i<listCnt;i++)
	{ 
		EmInfoVO emvo2 = (EmInfoVO)ealist1.get(i);
		emname2 = emvo2.getEmname();
		jobname2 = emvo2.getJobname();
		deptname2 = emvo2.getDeptname();
		emno2 = emvo2.getEmno();
	}
	}
	
	System.out.println("ea_deptname,1,2 : " + deptname + ", " + deptname1 + "," +deptname2);
	System.out.println("ea_emname,1,2 : " + emname + ", " + emname1 + "," +emname2);
	System.out.println("ea_jobname,1,2 : " + jobname + ", " + jobname1 + "," +jobname2);
	
	System.out.println("obj : " + obj);
	List<EaVO_> list = null;
	if(obj!=null){
		String ea_lineno = obj.toString();
		String ea_stepno = obj1.toString();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>eawriteform.jsp</title>
		<!-- <script type="text/javascript" src="../lib/jquery-1.11.0.min.js"></script> -->
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			
			if($("#eadoccd_chk").val()=="H"){
				$("#project").hide();
			}
			if($("#eadoccd_chk").val()=="P"){
				$("#rest").hide();
			}
			
			$(".js-sms-content").keyup(function(){
				cut_1000(this);
			});
			
		});		
		function insertEa(ea_lineno, ea_stepno, ea_empno){
			alert('insertEa 함수 진입');
			var ea_lineno = ea_lineno;
			var ea_stepno = ea_stepno;
			var eadoccd_chk = $('input[name="eadoccd_chk"]:checked').val();
			$("#ea_doccd").val(eadoccd_chk);
			$("#eatableinsertform").attr("action", "/ea_/eainsert.ge");
			$("#eatableinsertform").submit();
		}
		
		function getTextLength(str){
			var len = 0;
			for (var i=0; i < str.length; i++){
				if(escape(str.charAt(i)).length == 6){
					len++;
				}
				len++;
			}
			return len;
		};
		
		function cut_1000(obj){
			var text = $(obj).val();
			var leng = text.length;
			while(getTextLength(text) > 1000){
				leng--;
				text = text.substring(0, leng);
			}
			$(obj).val(text);
			$(".bytes").text(getTextLength(text));
		};
		
		</script>
	</head>
	<body>
	<div>
	<form id="eatableinsertform" name="eatableinsertform" method="post">
		<input type="hidden" value="<%=ea_lineno%>" id="ea_lineno" name="ea_lineno" >
		<input type="hidden" value="<%=ea_stepno%>" id="ea_stepno" name="ea_stepno" >
		<input type="hidden" value="<%=emno%>"  id="ea_empno"  name="ea_empno" >
		<input type="hidden" value="<%=emno1%>" id="ea_empno1" name="ea_empno1" >
		<input type="hidden" value="<%=emno2%>" id="ea_empno2" name="ea_empno2" >
		<input type="hidden" value="01" id="ea_status" name="ea_status" >
		<input type="hidden" value="" id="ea_doccd" name="ea_doccd" >

		<table id="outlinetable" border="0" align="center" width="800px">
			<tr><td>결재정보</td></tr>
			<tr>
				<td>
					<table border="1" align="center" width="100%" >
						<colgroup>
							<col width="170px" />
							<col width="630px" />
						</colgroup>
						<tr>
							<td>문서제목</td>
							<td><input type="text" id="ea_subject" name="ea_subject"/></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td>결재라인</td></tr>
			<tr>
				<td>
					<table border="1" align="center"  width="100%" >
					<colgroup>
					<col width="170px" />
					<col width="210px" />
					<col width="210px" />
					<col width="210px" />
					</colgroup>
						<tr>
							<td>구분</td>
							<td>작성자</td>
							<td>결재자</td>
							<td>결재자</td>
						</tr>
						<tr>
							<td>사번</td>
							<td><input type="text" value="<%=emno%>" disabled></td>
							<td><input type="text" value="<%=emno1%>" disabled></td>
							<td><input type="text" value="<%=emno2%>" disabled></td>
						</tr>
						<tr>
							<td>성명</td>
							<td><input type="text" value="<%=emname%>" disabled></td>
							<td><input type="text" value="<%=emname1%>" disabled></td>
							<td><input type="text" value="<%=emname2%>" disabled></td>
						</tr>
						<tr>
							<td>소속부서</td>
							<td><input type="text" value="<%=deptname %>" disabled></td>
							<td><input type="text" value="<%=deptname1 %>" disabled></td>
							<td><input type="text" value="<%=deptname2 %>" disabled></td>
						</tr>
						<tr>
							<td>직위</td>
							<td><input type="text" value="<%=jobname %>" disabled></td>
							<td><input type="text" value="<%=jobname1 %>" disabled></td>
							<td><input type="text" value="<%=jobname2 %>" disabled></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td>결재구분</td></tr>
			<tr>
				<td>
					<table border="1" width="100%">
						<tr>
							<td width="25%"><input type="radio" name="eadoccd_chk" value="H">휴가계</td>
							<td width="25%"><input type="radio" name="eadoccd_chk" value="P">프로젝트기안서</td>
							<td width="25%"><input type="radio" name="eadoccd_chk" value="R">품위서</td>
							<td width="25%"><input type="radio" name="eadoccd_chk" value="R">기술지원보고서</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td>문서내용</td></tr>
			<tr id="rest">
				<th>
					연차 종류
				</th>
				<td>
					<input type="radio" id="eacno" name="eacno" value="53"> 반차
					<input type="radio" id="eacno" name="eacno" value="54"> 연차
					<input type="radio" id="eacno" name="eacno" value="55"> 특별휴가
				</td>
			</tr>
			<tr id="project">
				<th>
					기안내용
				</th>
				<td>
					<textarea id="eadraftcontant" name="eadraftcontant" cols="50" rows="10"
					class="js-sms-content"></textarea>
					<div class="bytes-wrapper">
						<span class="bytes"> 0 </span> bytes
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table border="1" width="100%" height="400px">
						<tr>
							<td><textarea id="ea_memo" name="ea_memo" rows="27" cols="106" placeholder="내용을 적어주세요"></textarea></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td id="eabutton" colspan="2" align="center">
					<input type="button" onclick="insertEa('<%=ea_lineno%>', '<%=ea_stepno%>', '<%=emno%>');" value="상신" >
				</td>
			</tr>
		</table>
	</form>
	</div>
<%
	}
%>
	</body>
</html>