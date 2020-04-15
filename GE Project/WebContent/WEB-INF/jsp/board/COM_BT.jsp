<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.BTVO"%>
<%@ page import="com.spring.ge.vo.BTComVO"%>
<%@ page import="com.spring.ge.vo.EmInfoVO"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%
	request.setCharacterEncoding("EUC-KR");
%>
<%
Object obj = session.getAttribute("eminfo");
EmInfoVO evo = null;
evo = new EmInfoVO();
evo = (EmInfoVO)obj;

Object objDetail = request.getAttribute("boardTaskDetail");
BTVO btvo = null;
btvo = new BTVO();
btvo = (BTVO)objDetail;	
String btno = btvo.getBtno();

BTComVO btc = null;
btc = new BTComVO();
btc.setBtno(btno);

btno = btc.getBtno();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>���</title>
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="/include/js/common.js"></script>
		<script type="text/javascript" src="/include/js/byteCheck.js"></script>
		<style type="text/css">
     	td{
		border-bottom:1px solid #CCCCCC;
		}
		ul.comment{
		padding: 5px 2px 5px 5px;
    	margin-bottom: 5px;
    	border:hidden;
    	border-bottom: 1px solid #efefef;
    	font-size: 12px;
		}
		.button {
				background-color: #D8D8D8; /* Green */
				border: none;
				color: white;
				padding: 5px 10px;
				text-align: center;
				text-decoration: none;
				display: inline-block;
				font-size: 12px;
				border-radius:1px;
		}
		.button:hover{
				background-color: #ededed;
				cursor:pointer;
		}
		</style>
		<script type="text/javascript">
		$(function(){
			var btno = "<%=btno%>";
			listAll("<%=btno%>");
				
			/* ��� ��� */
			$("#BTCInsert").click(function(){				
				var comcd = $("#comcd").val();
				if(comcd == "new"){
					com = "01";
					$("#comcd").val(com);
				}else{
					$("#comcd").val("02");
				}
	
				var insertUrl = "/comment/insertCommentT.ge";
				$.ajax({
					url		: insertUrl,
					type	: "post",
					headers	: {
						"Content-Type"			:"application/json",
						"X-HTTP-Method-Override":"POST"						
							  },
					dataType: "text",
					data	:	JSON.stringify({
						emname		:$("#emname").val(),
						btno		:$("#btno").val(),
						btcomcontent:$("#btcomcontent").val(),
						comcd		:$("#comcd").val(),
						comname		:$("#comname").val(),
						emno		:$("#emno").val(),
						jobcd		:$("#jobcd").val(),
						deptcd		:$("#deptcd").val(),
						
					}),
					error	: function(){
						alert("�ý��� ���� �Դϴ�. ����� �ٽ� ����� �ּ���.");
					},
					success	: function(result){
						if(result == "SUCCESS"){
							alert("����� ��ϵǾ����ϴ�.");
							dataReset();
							listAll(btno);						
						}//end of if
					}//end of success
				});//end of ajax					
			});//end of insertCommentT			
			/* ��� ���� */
			$(document).on("click",".delete_btn", function(){
				var btcomno = $(this).parents("li").attr("data-num");
				console.log("��� ���� :: btcomno >>> " + btcomno);
				
				var deleteUrl = "/comment/" + btcomno + "_dT.ge";
				if("�����Ͻ� ����� �����Ͻðڽ��ϱ�?"){
					$.ajax({
						url		: deleteUrl,
						type	: "put",
						headers : {
							"Content-Type"			:"application/json",
							"X-HTTP-Method-Override":"PUT"						
								  },
						dataType: "text",
						data	:	JSON.stringify({
							btno		:$("#btno").val(),
							btcomno		:$("#btcomno").val()
							
						}),
						error	: function(){
							alert("�ý��� ���� �Դϴ�. ����� �ٽ� �������ּ���");
						},
						success	: function(result){
							if(result == "SUCCESS"){
								alert("����� ���� �Ǿ����ϴ�.");
								listAll(btno);						
							}//end of if
						}//end of success
					});//end of ajax
				}//end of if
			});//end of delete_btn
			/* ��� ���� �� */
			$(document).on("click", ".update_form", function(){
				$(".reset_btn").click();
				var conText = $(this).parents("li").children().eq(1).html();
				console.log("conText: " + conText);
				$(this).parents("li").find("input[type='button']").hide();
				$(this).parents("li").children().eq(0).html();
				var conArea = $(this).parents("li").children().eq(1);
				
				conArea.html("");
				var data="<textarea name='content' id='content'>" + conText + "</textarea>";
				data += "<input type='button' class='update_btn' value='�����Ϸ�'>";
				data += "<input type='button' class='reset_btn' value='�������'>";
				conArea.html(data);
			});//update_form
			/* �ʱ�ȭ ��ư */
			$(document).on("click", ".reset_btn",function(){
				var conText = $(this).parents("li").find("textarea").html();
				$(this).parents("li").find("input[type='button']").show();
				var conArea = $(this).parents("li").children().eq(1);
				conArea.html(conText);					
			});//end of reset_btn
			/* ��� ���� */
			$(document).on("click",".update_btn", function(){
				var btcomno = $(this).parents("li").attr("data-num");
				console.log("btcomno >>> " + btcomno);
				
				var updateUrl = "/comment/" + btcomno + "_uT.ge";
				if("�����Ͻðڽ��ϱ�?"){
					$.ajax({
						url		: updateUrl,
						type	: "put",
						headers : {
							"Content-Type"			:"application/json",
							"X-HTTP-Method-Override":"PUT"						
								  },
						dataType: "text",
						data	:	JSON.stringify({
							btno		:$("#btno").val(),
							emno		:$("#emno").val(),
							btcomno		:$("#btcomno").val(),
							btcomcontent:$("#content").val(),
							
						}),
						error	: function(){
							alert("�ý��� ���� �Դϴ�. ����� �ٽ� �������ּ���");
						},
						success	: function(result){
							if(result == "SUCCESS"){
								alert("����� ���� �Ǿ����ϴ�.");
								listAll(btno);						
							}//end of if
						}//end of success
					});//end of ajax
				}//end of if
			});//end of update_btn
			
		});//end of function
		function listAll(<%=btno%>){
			var btno = "<%=btno%>";
			$("#comment_list").html('');
			var url = "/comment/allT/" + btno + ".ge";
			console.log("url >> " + url);
			$.getJSON(url, function(data){
				console.log(data.length);
				
				$(data).each(function(){
					var btcomno = this.btcomno;
					var emname = this.emname;
					var btcomemno = this.emno;
					var btcomcontent = this.btcomcontent;
					var btcominsertdate = this.btcominsertdate;
					var btcomupdatedate = this.btcomupdatedate;
					addNewItem(btcomno,emname,btcomemno,btcomcontent,btcominsertdate,btcomupdatedate);
				});//end of data-each				
			}).fail(function(){
				alert("��� ����� �ҷ����� ���Ͽ����ϴ�.");
			});//end of getJSON - fail			
		}//end of listAll
		
		function addNewItem(btcomno,emname,btcomemno,btcomcontent,btcominsertdate,btcomupdatedate){
			var new_li = $("<li>");
			new_li.attr("data-num", btcomno);
			new_li.addClass("comment_item");
			
			var writer_p = $("<p>");
			writer_p.addClass("writer")
			
			var name_span = $("<span>");
			name_span.addClass("name");
			name_span.html(emname+"��");
			
			var emno_hidden = $("<input>");
			emno_hidden.attr({"type":"hidden", "id":"btcomemno", "value":btcomemno});
			
			var insertdate_span = $("<span>");
			insertdate_span.html("/�ۼ��� :"+btcominsertdate+" ");
			
			var updatedate_span = $("<span>");
			updatedate_span.html("/������ :"+btcomupdatedate+" ");
			
			var content_p = $("<p>");
			content_p.addClass("con");
			content_p.html(btcomcontent);
			
			//������ư
			var up_input = $("<input>");
			up_input.attr({"type":"button", "value":"�����ϱ�"});
			up_input.addClass("update_form");
			
			//������ư
			var del_input = $("<input>");
			del_input.attr({"type":"button", "value":"�����ϱ�"});
			del_input.addClass("delete_btn");

			var check1 = "<%=evo.getEmno()%>";
			var check2 = $("#btcomemno").val();

			if(check1==btcomemno){
				writer_p.append(name_span).append(insertdate_span).append(updatedate_span).append(emno_hidden).append(up_input).append(del_input);
				new_li.append(writer_p).append(content_p);
			}else{
				writer_p.append(name_span).append(insertdate_span).append(updatedate_span).append(emno_hidden);
				new_li.append(writer_p).append(content_p);
			}			
			$("#comment_list").append(new_li);		
		}
		/*INPUT �±׵鿡 ���� �ʱ�ȭ �Լ�*/
		function dataReset(){
			$("#btcomcontent").val("");				
		}
		</script>
	</head>
	<body>
		<form>
			<table>
				<tr>
					<td>�ۼ���</td>
					<td><input type="text" id="emname" name="emname" value=<%=evo.getEmname() %> readonly></td>
				
				</tr>
				<tr>
					<td>���� : </td>
					<td>
					<textarea id="btcomcontent" name="btcomcontent" cols="60" rows="4" placeholder="������ �Է��� �ּ���"></textarea>
					<input type="button" id="BTCInsert" name="BTCInsert" value="��� ����ϱ�">
					<input type="hidden" id="comcd" name="comcd" value="new">
					</td>
				</tr>
			</table>
		</form>
		<ul id="comment_list" class="comment">
				<!-- ���⿡ ���� ���� ��Ұ� ���� �˴ϴ�. -->
		</ul>	
	</body>
</html>