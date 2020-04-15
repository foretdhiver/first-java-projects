<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.spring.ge.vo.BDVO"%>
<%@ page import="com.spring.ge.vo.BDComVO"%>
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

Object objDetail = request.getAttribute("boardDepartmentDetail");
BDVO bdvo = null;
bdvo = new BDVO();
bdvo = (BDVO)objDetail;	
String bdno = bdvo.getBdno();

BDComVO bdc = null;
bdc = new BDComVO();
bdc.setBdno(bdno);

bdno = bdc.getBdno();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>댓글</title>
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
			var bdno = "<%=bdno%>";
			listAll("<%=bdno%>");
				
			/* 댓글 등록 */
			$("#BDCInsert").click(function(){				
				var comcd = $("#comcd").val();
				if(comcd == "new"){
					com = "01";
					$("#comcd").val(com);
				}else{
					$("#comcd").val("02");
				}
	
				var insertUrl = "/comment/insertComment.ge";
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
						bdno		:$("#bdno").val(),
						bdcomcontent:$("#bdcomcontent").val(),
						comcd		:$("#comcd").val(),
						comname		:$("#comname").val(),
						emno		:$("#emno").val(),
						jobcd		:$("#jobcd").val(),
						deptcd		:$("#deptcd").val(),
						
					}),
					error	: function(){
						alert("시스템 오류 입니다. 잠시후 다시 등록해 주세요.");
					},
					success	: function(result){
						if(result == "SUCCESS"){
							alert("댓글이 등록되었습니다.");
							dataReset();
							listAll(bdno);						
						}//end of if
					}//end of success
				});//end of ajax					
			});//end of insertComment			
			/* 댓글 삭제 */
			$(document).on("click",".delete_btn", function(){
				var bdcomno = $(this).parents("li").attr("data-num");
				console.log("댓글 삭제 :: bdcomno >>> " + bdcomno);
				
				var deleteUrl = "/comment/" + bdcomno + "_d.ge";
				if("선택하신 댓글을 삭제하시겠습니까?"){
					$.ajax({
						url		: deleteUrl,
						type	: "put",
						headers : {
							"Content-Type"			:"application/json",
							"X-HTTP-Method-Override":"PUT"						
								  },
						dataType: "text",
						data	:	JSON.stringify({
							bdno		:$("#bdno").val(),
							bdcomno		:$("#bdcomno").val()
							
						}),
						error	: function(){
							alert("시스템 오류 입니다. 잠시후 다시 삭제해주세요");
						},
						success	: function(result){
							if(result == "SUCCESS"){
								alert("댓글이 삭제 되었습니다.");
								listAll(bdno);						
							}//end of if
						}//end of success
					});//end of ajax
				}//end of if
			});//end of delete_btn
			/* 댓글 수정 폼 */
			$(document).on("click", ".update_form", function(){
				$(".reset_btn").click();
				var conText = $(this).parents("li").children().eq(1).html();
				console.log("conText: " + conText);
				$(this).parents("li").find("input[type='button']").hide();
				$(this).parents("li").children().eq(0).html();
				var conArea = $(this).parents("li").children().eq(1);
				
				conArea.html("");
				var data="<textarea name='content' id='content'>" + conText + "</textarea>";
				data += "<input type='button' class='update_btn button' value='수정완료'>";
				data += "<input type='button' class='reset_btn button' value='수정취소'>";
				conArea.html(data);
			});//update_form
			/* 초기화 버튼 */
			$(document).on("click", ".reset_btn",function(){
				var conText = $(this).parents("li").find("textarea").html();
				$(this).parents("li").find("input[type='button']").show();
				var conArea = $(this).parents("li").children().eq(1);
				conArea.html(conText);					
			});//end of reset_btn
			/* 댓글 수정 */
			$(document).on("click",".update_btn", function(){
				var bdcomno = $(this).parents("li").attr("data-num");
				console.log("bdcomno >>> " + bdcomno);
				
				var updateUrl = "/comment/" + bdcomno + "_u.ge";
				if("선택하신 댓글을 삭제하시겠습니까?"){
					$.ajax({
						url		: updateUrl,
						type	: "put",
						headers : {
							"Content-Type"			:"application/json",
							"X-HTTP-Method-Override":"PUT"						
								  },
						dataType: "text",
						data	:	JSON.stringify({
							bdno		:$("#bdno").val(),
							emno		:$("#emno").val(),
							bdcomno		:$("#bdcomno").val(),
							bdcomcontent:$("#content").val(),
							
						}),
						error	: function(){
							alert("시스템 오류 입니다. 잠시후 다시 수정해주세요");
						},
						success	: function(result){
							if(result == "SUCCESS"){
								alert("댓글이 수정 되었습니다.");
								listAll(bdno);						
							}//end of if
						}//end of success
					});//end of ajax
				}//end of if
			});//end of update_btn
			
		});//end of function
		function listAll(<%=bdno%>){
			var bdno = "<%=bdno%>";
			$("#comment_list").html('');
			var url = "/comment/all/" + bdno + ".ge";
			console.log("url >> " + url);
			$.getJSON(url, function(data){
				console.log(data.length);
				
				$(data).each(function(){
					var bdcomno = this.bdcomno;
					var emname = this.emname;
					var bdcomemno = this.emno;
					var bdcomcontent = this.bdcomcontent;
					var bdcominsertdate = this.bdcominsertdate;
					var bdcomupdatedate = this.bdcomupdatedate;
					addNewItem(bdcomno,emname,bdcomemno,bdcomcontent,bdcominsertdate,bdcomupdatedate);
				});//end of data-each				
			}).fail(function(){
				alert("댓글 목록을 불러오지 못하였습니다.");
			});//end of getJSON - fail			
		}//end of listAll
		
		function addNewItem(bdcomno,emname,bdcomemno,bdcomcontent,bdcominsertdate,bdcomupdatedate){
			var new_li = $("<li>");
			new_li.attr("data-num", bdcomno);
			new_li.addClass("comment_item");
			
			var writer_p = $("<p>");
			writer_p.addClass("writer")
			
			var name_span = $("<span>");
			name_span.addClass("name");
			name_span.html(emname+"님");
			
			var emno_hidden = $("<input>");
			emno_hidden.attr({"type":"hidden", "id":"bdcomemno", "value":bdcomemno});
			
			var insertdate_span = $("<span>");
			insertdate_span.html("/작성일 :"+bdcominsertdate+" ");
			
			var updatedate_span = $("<span>");
			updatedate_span.html("/수정일 :"+bdcomupdatedate+" ");
			
			var content_p = $("<p>");
			content_p.addClass("con");
			content_p.html(bdcomcontent);
			
			//수정버튼
			var up_input = $("<input>");
			up_input.attr({"type":"button", "class":"button","value":"수정하기"});
			up_input.addClass("update_form");
			
			//삭제버튼
			var del_input = $("<input>");
			del_input.attr({"type":"button","class":"button", "value":"삭제하기"});
			del_input.addClass("delete_btn");

			var check1 = "<%=evo.getEmno()%>";
			var check2 = $("#bdcomemno").val();

			if(check1==bdcomemno){
				writer_p.append(name_span).append(insertdate_span).append(updatedate_span).append(emno_hidden).append(up_input).append(del_input);
				new_li.append(writer_p).append(content_p);
			}else{
				writer_p.append(name_span).append(insertdate_span).append(updatedate_span).append(emno_hidden);
				new_li.append(writer_p).append(content_p);
			}			
			$("#comment_list").append(new_li);		
		}
		/*INPUT 태그들에 대한 초기화 함수*/
		function dataReset(){
			$("#bdcomcontent").val("");				
		}
		</script>
	</head>
	<body>
		<form>
			<table>
				<tr>
					<td>작성자</td>
					<td><input type="text" id="emname" name="emname" value=<%=evo.getEmname() %> readonly></td>
				
				</tr>
				<tr>
					<td>내용 : </td>
					<td>
					<textarea id="bdcomcontent" name="bdcomcontent" cols="60" rows="4" placeholder="내용을 입력해 주세요"></textarea>
					<input type="button" class="button" id="BDCInsert" name="BDCInsert" value="댓글 등록하기">
					<input type="hidden" id="comcd" name="comcd" value="new">
					</td>
				</tr>
			</table>
		</form>
		<ul id="comment_list" class="comment">
				<!-- 여기에 동적 생성 요소가 들어가게 됩니다. -->
		</ul>	
	</body>
</html>