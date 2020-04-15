<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "com.spring.ge.controller.AnBoardController" %>         
<%@ page import = "com.spring.ge.vo.AnBoardVO" %> 
<%@ page import = "com.spring.ge.vo.ReplyVO" %>    

     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	Object obj = request.getAttribute("detail");
	AnBoardVO bvo = new AnBoardVO();
	bvo = (AnBoardVO)obj;
	String bno = bvo.getAbno();
	System.out.println("	bno >>> " + bvo.getAbno()  );
	
	ReplyVO rvo = new ReplyVO();
	rvo.setR_no(bno);
	System.out.println("	abno >>> " + rvo.getR_no());
	String abno = rvo.getR_no();
%>	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<title>Reply</title>
		<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		
		<style type="text/css">
		
		#replyContainer {
		  	width : 900px; 
			 margin: auto;
			 margin-top : 0px;
			 align : center;
			 border: hidden;
			 border-spacing: 0px;
			 font-size : small;
		  }
/* 		#replyContainer{ */
/* 			 border : 1px solid blue; */
/* 		  	 width : 900px;  */
/* 			 margin-top : 0px; */
/* 			 align : center; */
/* 			 border: hidden; */
/* 			 border-spacing: 0px; */
/* 			 font-size : small; */
	 
/* 		  } */
		
		
		table {
	        width: 100%;
	        border-top: 1px solid #444444;
	        border-collapse: collapse;
	
	      }
	      th, td {
/* 		        border-bottom: 1px solid #444444; */
		        padding:9px;
		        text-align: left;
	      }
	      th {
		    background-color: #d9d9d9;
		  }
		textarea{   
		  width: 85%;  
/* 		  width: 450px;  */
		  outline: none; 
		  resize : none;
		  margin-top : 5px;
		  vertical-align: middle;
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
			//alert("댓글댓글");
			
			//기본 덧글 목록 불러오기
			var abno = "<%=abno %>";
			listAll(abno); //ooo
			
			//댓글내용 저장
			$("#replyInsert").click(function(){
				//alert("replyInsert >>> ");//oo
				
				//입력여부 검사
// 				if(!chkSubmit($("#r_content"),"내용을")) return; //oo
// 				else{
					var insertUrl = "/replies/replyInsert.ge";
					
					//ajax
					$.ajax({
						url : insertUrl,
						type : "POST",
						headers : {
							"Content-Type":"application/json",
							"X-HTTP-Method-Override":"POST"
							},
							dataType:"text",
							data : JSON.stringify({
								abno:abno,
								r_pw:$("#r_pw").val(),
								r_content:$("#r_content").val()
							}),
							error : function(){
								alert("시스템 오류 >>> ");
							},//error oooo
							success : function(resultData){
								if(resultData=="SUCCESS"){
									alert("댓글 등록 완료");
									dataReset();
									listAll(abno);
								}//if 
							}//seccess
					});//ajax 
// 				}//else	
			});//replyInsert
			
			//수정버튼 클릭 시 수정 폼 출력
			$(document).on("click",".update_form",function(){
				$(".reset_btn").click();
				//alert("수정버튼 클릭 >>> ");
				
				var conText = $(this).parents("li").children().eq(1).html();
				
				console.log("conText : " + conText);
				$(this).parents("li").find("input[type='button']").hide();
				$(this).parents("li").children().eq(0).html();
				var conArea = $(this).parents("li").children().eq(1);
				
				conArea.html("");
				var data="<textarea name='content' id='content'>" + conText + "</textarea>";
				data+="<input type='button' class='update_btn button' value='수정완료'>";
				data+="<input type='button' class='reset_btn button' value='수정취소'>";
				conArea.html(data);
					
			});//$(document).on("click",".update_form" ooo
			
			//초기화버튼
			$(document).on("click",".reset_btn",function(){
				var conText = $(this).parents("li").find("textarea").html();
				$(this).parents("li").find("input[type='button']").show();
				var conArea=$(this).parents("li").children().eq(1);
				conArea.html(conText);
			});//reset_btn	
			
			//글 수정 ajax
			$(document).on("click",".update_btn",function(){
				var r_no=$(this).parents("li").attr("data-num");
				var r_content = $("#content").val();
				if(!chkSubmit($("#content"),"댓글 내용을")) return;
				else{
					$.ajax({
						url : '/replies/' + r_no + ".ge",
						type : 'put',
						headers : {"Content-Type":"application/json",
									"X-HTTP-Method-Override":"PUT"
									},
								data:JSON.stringify({r_content : r_content}),
								dataType:'text',
								success : function(result){
									console.log("result >>> " + result);
									if(result=='SUCCESS'){
										alert("수정 성공 >>> ");
										listAll(abno);
									}//if
								}//success : function
						});//ajax
					}//else
			});//$(document).on("click",".update_btn oo
					
			//글삭제 ajax
			$(document).on("click",".delete_btn",function(){
				
				alert("삭제버튼 클릭 >>>");
				
				var r_no = $(this).parents("li").attr("data-num");
				console.log("r_no >>> " + r_no);
				
				if(confirm("선택하신 댓글을 삭제하시겠습니까?")){
					$.ajax({
						type : "delete",
						url : "/replies/"+r_no+".ge",
						headers : {"Content-Type":"application/json",
								"X-HTTP-Method-Override" : "DELETE"},
						dataType : 'text',
						success : function(result){
							console.log("result >>> " + result);
							if(result=='SUCCESS'){
								alert("삭제되었습니다.");
								listAll(abno);
							}
						}//success
					
					});//ajax
				}//confirm
			});//$(document).on("click", ".delete_btn"		
			
		});//$(function(){
			
//--------------------------------------------------
		function listAll(abno){
			//alert(" function listAll(abno) >>>> ");
			$("#comment_list").html("");
			var url="/replies/all/"+abno+".ge";
			$.getJSON(url,function(data){
				//alert("getJSON 들어가나?");
				console.log(data);
				console.log(data.length);
				
				$(data).each(function(){
					var r_no=this.r_no;
					var r_content=this.r_content;
					var r_date=this.r_date;
					addNewItem(r_no,r_content,r_date);
					
				});//$(data).each(function
				
				})//$.getJSON
				.fail(function(){
					alert("댓글 불러오기 실패");
				
			});//.fail(function ooo
		}// function listAll
				
		//addNewItem
		function addNewItem(r_no,r_content,r_date){
			
			
			//새로운 글이 추가될때 li태그 객체
			var new_li = $("<li>");
			new_li.attr("data-num",r_no);
			new_li.addClass("comment_item");
			
			//작성자 정보가 지정될 <p>태그
			var writer_p = $("<p>");
			writer_p.addClass("writer");
			
			//작성일시
			var date_span=$("<span>");
			date_span.html("/"+r_date+" ");
			
			//수정하기 버튼
			var up_input = $("<input>");
			up_input.attr({"type":"button","class":"button", "value":"수정하기"});
			up_input.addClass("update_form");
			
			//삭제하기 버튼
			var del_input = $("<input>");
			del_input.attr({"type":"button","class":"button","value":"삭제하기"});
			del_input.addClass("delete_btn");
			
			//내용
			var content_p = $("<p>");
			content_p.addClass("con");
			content_p.html(r_content);
			
			//조립하기
	         writer_p.append(date_span).append(up_input).append(del_input);
	         new_li.append(writer_p).append(content_p);
	         $("#comment_list").append(new_li);
	         
		}//function addNewItem
		
		//input태그들에 대한 초기화 함수
		function dataReset(){
			$("#r_pw").val("");
			$("#r_content").val("");
		}//function
			
		</script>
	</head>
	<body>
		<div id="replyContainer">
			<h1></h1>
			<div id="comment_write">
				<form id="comment_form">
					<div>
						<label for="r_name">비밀번호</label>
						<input type="password" name="r_pw" id="r_pw" />
						<input type="button" class="button" name="replyInsert" id="replyInsert" value="저장하기"/>
					</div>
					<div>
						<label for="r_content">덧글 내용</label>
						<textarea name="r_content" id="r_content"></textarea>
					</div>
				
				</form>
			</div>
			<ul id="comment_list"></ul>
		</div>
		
		
	</body>
<%
	//}//if
%>
</html>