<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%  request.setCharacterEncoding("EUC-KR"); %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>index.jsp</title>
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
        <script type="text/javascript">
         $(document).ready(function(){
        	 
        	 $('.login .loader').hide();
        	 
            var placeholderTarget = $('.textbox input[type="text"], .textbox input[type="password"]');
            
            //포커스시
            placeholderTarget.on('focus', function(){
              $(this).siblings('label').fadeOut('fast');
            });

            //포커스아웃시
            placeholderTarget.on('focusout', function(){
              if($(this).val() == ''){
                $(this).siblings('label').fadeIn('fast');
              }
            });
            
            $('#emid, #empw').bind({
            	"focus": function(){
            		$(this).addClass("active");
            	}
            });
            
            $("#ea_send").bind("click", function(){
            	
            	if(!$('#emid').val()){
            		$('#emid').siblings('label').hide();
            		$('#emid').attr('placeholder','아이디를 입력하세요.');
            		return false;
            	}
            	if(!$('#empw').val()){
            		$('#empw').siblings('label').hide();
            		$('#empw').attr('placeholder','비밀번호를 입력하세요.');
            		return false;
            	}
            	
            	$('.login .loader').show();
            	
            	
                
                setTimeout(function(){
                	$("#loginForm").attr("method", "POST");
                    $("#loginForm").attr("action","/ea/loginCheck.ge").submit();
                    $('#login .loader').hide();
                }, 1000);
                
                

            });
            
         });
        </script>
        <style type="text/css">	
        body{font-family:"Malgun Gothic","맑은 고딕",Dotum,"돋움",Arial,sans-serif}
        
        .active{
        	border: 1px solid #cccccc !important;
        	backtround-color: #cccccc;
        }
        
        .outline{
        		width:600px;
        		height:auto;
        		padding: 0;
        		margin: auto;
        }
        .wrap {
        		margin: auto;
        		margin-top:35%;
        		padding: 15px; 
        		width:350px;
        		border: 1px solid #cccccc;
        		position: relative;
        		}
        
		.sendbox {
				position: relative; 
				float: right;
				margin-top:-50px;
				margin-left:-40px;
				}
		.logo{
			width:300px;
			hegiht:auto;
			margin-left:-120px;
			
		}	
		.textbox {position: relative; width: 200px; margin:10px; float: left;}
		.textbox label {
		  position: absolute;
		  top: 1px;  /* input 요소의 border-top 설정값 만큼 */
		  left: 1px;  /* input 요소의 border-left 설정값 만큼 */
		  padding: .5em .5em;  /* input 요소의 padding 값 만큼 */
		  color: #D8D8D8;
		  cursor: text;
		}
		
		.textbox input[type="text"],
		.textbox input[type="password"] {
		  width: 100%;  /* 원하는 너비 설정 */ 
		  height: auto;  /* 높이값 초기화 */
		  line-height : normal;  /* line-height 초기화 */
		  padding: .8em .5em; /* 원하는 여백 설정, 상하단 여백으로 높이를 조절 */
		  border: 1px solid #D8D8D8;
		  border-radius: 0;  /* iSO 둥근모서리 제거 */
		  outline-style: none;  /* 포커스시 발생하는 효과 제거를 원한다면 */
		  -webkit-appearance: none;  /* 브라우저별 기본 스타일링 제거 */
		  -moz-appearance: none;
		  appearance: none;
		}		
		
		.sendbtn{
		  width:215px;
		  height:30px;
		  border:2px solid #D8D8D8;
		  float:left;
		  text-align:center;
		  cursor:pointer;
		  position:relative;
		  box-sizing:border-box;
		  overflow:hidden;
		  margin:0 0 30px 10px;
		}
		.sendbtn a{
		  font-family:arial;
		  font-size:16px;
		  color:#D8D8D8;
		  text-decoration:none;
		  line-height:30px;
		  transition:all .5s ease;
		  z-index:2;
		  position:relative;
		}
		.btneff{
		  width:215px;
		  height:30px;
		  bottom:-30px;
		  background:#D8D8D8;
		  position:absolute;
		  transition:all .5s ease;
		  z-index:1;
		}
		.sendbtn:hover .btneff{
		  bottom:0;
		}
		.sendbtn:hover a{
		  color:#fff;
		}	
		
		.login {
        		width: 350px; 
        		height: 200px; 
        		position: relative; 
        		top:50%; 
        		left:50%;
        		margin:30px 0 0 -120px; 
        		text-align:center;
        		}	
        .login fildset .loader{
        		position:absolute;
        		width:100%;
        		height: 100%;
        		background-color: rgba(0, 0, 0, 0.3);
        		display:none;
        }
		.login .loader img{
				position: absolute;
				left: 50%;
				right: 50%;
				margin-left:-170px;
				margin-top:0;
				
		}
		
        </style>
	</head>
	<body>
		<div class="outline">
		<div class="wrap">
		<div class="login">
		<form id="loginForm" name="loginForm">
		<div class="textbox">
			<label for="emid">ID</label>
			<input type="text" id="emid" name="emid">
		</div>
		
		<div class="textbox">
			<label for="empw">PASSWORD</label>
			<input type="password" id="empw" name="empw">
		</div>
		</form>
		<div class="sendbtn">
		    <div class="btneff"></div>
		    <a href="#" id="ea_send"> LOGIN </a>
		</div>
	    <div class="loader">
	     	<img src="/ge/loader.gif">
	    </div>
    	</div>
    	</div>
    	</div>
	</body>
</html>