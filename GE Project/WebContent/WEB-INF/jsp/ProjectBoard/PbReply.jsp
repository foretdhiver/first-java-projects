<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "com.spring.ge.controller.ProjectBoardController" %>         
<%@ page import = "com.spring.ge.vo.ProjectBoardVO" %> 
<%@ page import = "com.spring.ge.vo.PbReplyVO" %>  
<% System.out.println("(log)PbReply.jsp ���Դ� >>> "); %>  

     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	Object obj = request.getAttribute("pjDetail");
	ProjectBoardVO pbvo = new ProjectBoardVO();
	pbvo = (ProjectBoardVO)obj;
	String pbno = pbvo.getPbno();
	System.out.println("	(jsp)getPbno >>> " + pbvo.getPbno()  );
	
	PbReplyVO prvo = new PbReplyVO();
	prvo.setPbr_no(pbno);
	System.out.println("	(jsp)pbno >>> " + pbvo.getPbno());
	String pbno_ = prvo.getPbno();
%>	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<title>PbReply</title>
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

	   </style>
		
		
		
		<script type="text/javascript">
		
		$(function(){
			////alert("��۴��");
			
			//�⺻ ���� ��� �ҷ�����
			var pbno = "<%=pbno %>";
			listAll(pbno); //ooo
			console.log("	(jsp)pbno >>> " + pbno);
			
			//��۳��� ����
			$("#replyInsert").click(function(){
				//alert("replyInsert >>> ");//oo
									
					var insertUrl = "/pjReply/pbReplyInsert.ge";
					
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
								pbno:pbno,
								pbr_content:$("#pbr_content").val()
							}),
							error : function(){
								//alert(" (ajax)�ý��� ���� >>> ");
							},//error oooo
							success : function(resultData){
								if(resultData=="SUCCESS"){
									//alert("��� ��� �Ϸ�");
									dataReset();
									listAll(pbno);
								}//if 
							}//seccess
					});//ajax 
// 				}//else	
			});//replyInsert
			
			//������ư Ŭ�� �� ���� �� ���
			$(document).on("click",".update_form",function(){
				$(".reset_btn").click();
				var conText = $(this).parents("li").children().eq(1).html();
				console.log("conText : " + conText);
				$(this).parents("li").find("input[type='button']").hide();
				$(this).parents("li").children().eq(0).html();
				var conArea = $(this).parents("li").children().eq(1);
				
				conArea.html("");
				var data="<textarea name='content' id='content'>"+conText+"</textarea>";
				data+="<input type='button' class='update_btn' value='�����Ϸ�'>";
				data+="<input type='button' class='reset_btn' value='�������'>";
				conArea.html(data);
					
			});//$(document).on("click",".update_form",
			
			//�ʱ�ȭ��ư
			$(document).on("click",".reset_btn",function(){
				var conText = $(this).parents("li").find("textarea").html();
				$(this).parents("li").find("input[type='button']").show();
				var conArea=$(this).parents("li").children().eq(1);
				conArea.html(conText);
			});//reset_btn	
			
			//�� ���� ajax
			$(document).on("click",".update_btn",function(){
				var pbr_no=$(this).parents("li").attr("data-num");
				var pbr_content = $("#content").val();
				if(!chkSubmit($("#content"),"��� ������")) return; //oo
				else{
					$.ajax({
						url : '/pjReply/' + pbr_no + ".ge",
						type : 'put',
						headers : {"Content-Type":"application/json",
									"X-HTTP-Method-Override":"PUT"
									},
								data:JSON.stringify({pbr_content : pbr_content}),
								dataType:'text',
								success : function(result){
									console.log("	(jsp)result >>> " + result);
									if(result=='SUCCESS'){
										//alert("���� ���� >>> ");
										listAll(pbno);
									}//if
								}//success : function
						});//ajax
					}//else
			});//$(document).on("click",".update_btn oo
					
			//�ۻ��� ajax
			$(document).on("click",".delete_btn",function(){
				
				//alert("������ư Ŭ�� >>>");
				
				var pbr_no = $(this).parents("li").attr("data-num");
				console.log("	(jsp)pbr_no >>> " + pbr_no);
				
				if(confirm("�����Ͻ� ����� �����Ͻðڽ��ϱ�?")){
					$.ajax({
						type : 'delete',
						url : '/pjReply/'+pbr_no+'.ge',
						headers : {"Content-Type":"application/json",
								"X-HTTP-Method-Override" : "DELETE"},
						dataType : 'text',
						success : function(result){
							console.log("result >>> " + result);
							if(result=='SUCCESS'){
								//alert("�����Ǿ����ϴ�.");
								listAll(pbno);
							}
						}//success
					
					});//ajax
				}//confirm
			});//$(document).on("click", ".delete_btn"		
				
			
		});//$(function(){
			
//--------------------------------------------------
		function listAll(pbno){
			//alert(" function listAll(pbno) >>>> ");
			
			$("#comment_list").html("");
			var url="/pjReply/all/"+pbno+".ge";
			$.getJSON(url,function(data){
				////alert("getJSON ����?");
				console.log("	(jsp)data >>> " + data);
				console.log("	(jsp)data.length >>> " + data.length);
				
				$(data).each(function(){
					var pbr_no=this.pbr_no;
					var pbr_content=this.pbr_content;
					var pbr_date=this.pbr_date;
					addNewItem(pbr_no,pbr_content,pbr_date);
					
				});//$(data).each(function
				
				})//$.getJSON
				.fail(function(){
					//alert("��� �ҷ����� ����");
				
			});//.fail(function ooo
		}// function listAll
				
		//addNewItem
		function addNewItem(pbr_no,pbr_content,pbr_date){
			
			
			//���ο� ���� �߰��ɶ� li�±� ��ü
			var new_li = $("<li>");
			new_li.attr("data-num",pbr_no);
			new_li.addClass("comment_item");
			
			//�ۼ��� ������ ������ <p>�±�
			var writer_p = $("<p>");
			writer_p.addClass("writer");
			
			//�ۼ��Ͻ�
			var date_span=$("<span>");
			date_span.html("/"+pbr_date+" ");
			
			//�����ϱ� ��ư
			var up_input = $("<input>");
			up_input.attr({"type":"button","value":"�����ϱ�"});
			up_input.addClass("update_form");
			
			//�����ϱ� ��ư
			var del_input = $("<input>");
			del_input.attr({"type":"button","value":"�����ϱ�"});
			del_input.addClass("delete_btn");
			
			//����
			var content_p = $("<p>");
			content_p.addClass("con");
			content_p.html(pbr_content);
			
			//�����ϱ�
	         writer_p.append(date_span).append(up_input).append(del_input);
	         new_li.append(writer_p).append(content_p);
	         $("#comment_list").append(new_li);
	         
		}//function addNewItem
		
		//input�±׵鿡 ���� �ʱ�ȭ �Լ�
		function dataReset(){
			$("#pbr_pw").val("");
			$("#pbr_content").val("");
		}//function
			
		</script>
	</head>
	<body>
		<div id="replyContainer">
			<h1></h1>
			<div id="comment_write">
				<form id="comment_form">
					<div>
						<label for="pbr_content">���۳���
						<textarea name="pbr_content" id="pbr_content"></textarea></label>
					</div>	
					<div>
						<label for="r_name">��й�ȣ </label>
						<input type="password" name="pbr_pw" id="pbr_pw" />
						<input type="button" name="replyInsert" id="replyInsert" value="�����ϱ�"/>
						
					</div>
<!-- 					<div> -->
<!-- 						<label for="pbr_content">���� ����</label> -->
<!-- 						<textarea name="pbr_content" id="pbr_content"></textarea> -->
<!-- 					</div> -->
				
				</form>
			</div>
			<ul id="comment_list"></ul>
		</div>
		
		
	</body>
<%
	//}//if
%>
</html>