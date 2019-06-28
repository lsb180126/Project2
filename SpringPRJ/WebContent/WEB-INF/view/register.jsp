<%@page import="poly.dto.UserDTO"%>
<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/view/SessionValue.jsp" %>
<% UserDTO uDTO = (UserDTO)request.getAttribute("uDTO");%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
        <!-- Title -->
	    <title>AirCheck</title>
        
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Amatic+SC:400,700|Work+Sans:300,400,700" rel="stylesheet">
    <link rel="stylesheet" href="/reborn/fonts/icomoon/style.css">

    <link rel="stylesheet" href="/reborn/css/bootstrap.min.css">
    <link rel="stylesheet" href="/reborn/css/magnific-popup.css">
    <link rel="stylesheet" href="/reborn/css/jquery-ui.css">
    <link rel="stylesheet" href="/reborn/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/reborn/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="/reborn/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="/reborn/css/animate.css">
    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/mediaelementplayer.min.css">
    
    
    
    <link rel="stylesheet" href="/reborn/fonts/flaticon/font/flaticon.css">
  
    <link rel="stylesheet" href="/reborn/css/aos.css">

    <link rel="stylesheet" href="/reborn/css/style.css">
    
    <link rel="stylesheet" href="/example/style.css" />
        
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">   

	
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
        
       <style>
       
     body {
    padding-top: 90px;
}
.panel-login {
	border-color: #ccc;
	-webkit-box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
	-moz-box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
	box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
}
.panel-login>.panel-heading {
	color: #00415d;
	background-color: #fff;
	border-color: #fff;
	text-align:center;
}
.panel-login>.panel-heading a{
	text-decoration: none;
	color: #666;
	font-weight: bold;
	font-size: 15px;
	-webkit-transition: all 0.1s linear;
	-moz-transition: all 0.1s linear;
	transition: all 0.1s linear;
}
.panel-login>.panel-heading a.active{
	color: #029f5b;
	font-size: 18px;
}
.panel-login>.panel-heading hr{
	margin-top: 10px;
	margin-bottom: 0px;
	clear: both;
	border: 0;
	height: 1px;
	background-image: -webkit-linear-gradient(left,rgba(0, 0, 0, 0),rgba(0, 0, 0, 0.15),rgba(0, 0, 0, 0));
	background-image: -moz-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
	background-image: -ms-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
	background-image: -o-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
}
.panel-login input[type="text"],.panel-login input[type="email"],.panel-login input[type="password"]{
	height: 45px;
	border: 1px solid #ddd;
	font-size: 16px;
	-webkit-transition: all 0.1s linear;
	-moz-transition: all 0.1s linear;
	transition: all 0.1s linear;
}
.panel-login input:hover,
.panel-login input:focus {
	outline:none;
	-webkit-box-shadow: none;
	-moz-box-shadow: none;
	box-shadow: none;
	border-color: #ccc;
}
     
    .btn-register {
	background-color: #1CB94E;
	outline: none;
	color: #fff;
	font-size: 14px;
	height: auto;
	font-weight: normal;
	padding: 14px 0;
	text-transform: uppercase;
	border-color: #1CB94A;
	}
	.btn-register:hover,
	.btn-register:focus {
		color: #fff;
		background-color: #1CA347;
		border-color: #1CA347;
	} 
	
	.site-blocks-cover, .site-blocks-cover .row {
    
    height: calc(75vh);
	}
        
      </style>
        
        
        
        
        <script type="text/javascript">
      
        
  $(function() {
      		
      		var check = false;
      		 
      		$("#check").click(function(){
      			var id  = $("#idcheck").val();
      			var idregExp = /^[a-z0-9_]*$/;
      			
      			
      			$.ajax({
      				data:{"id":id},
      				url:"/ajaxTest.do",
      				type:"POST",
      				dataType:"json",
      				success:function(data) {
      					
      					if(data==0) {
      						
      						if(id == "") {
      		      				alert("아이디를 입력해주세요");
      		      				return;
      		      			}else if(id.length <5) {
      		      				alert("id를 5자리이상 입력해주세요");
      		      				return;
      		      			}
      		      			
      		      			if(!id.match(idregExp)) {
      		      				alert("아이디를 올바른 형식으로 입력해주세요");
      		      				return;
      		      			}
      						
      						
      						alert("사용 가능한 아이디입니다.");
      						
      						document.getElementById("idchk").value = id;
      						
      						
      						check = true;
      						
      						
      						
      					}
      					else {
      						alert("중복되는 아이디입니다.");
      						
      						
      					}
      					
      					
      					
      				},
      				error:function(error) {
      					console.log(error);
      				}
      				
      			})
      				
      			
      		})
      		
      	})	 
      
      $(function() {
      		
      		var check=false;
      		 
      		$("#ok").click(function(event){
      			var id  = $("#idcheck").val();
      			var idchk  = document.getElementById("idchk").value 
      			var password  = $("#password").val();
      			var passwordcheck  = $("#passwordcheck").val();
      			var name  = $("#name").val();
      			var gender = $("#gender option:selected").val();
      			var email  = $("#email").val();
      			var phone = $("#phone").val();
      			
      			var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      			
    			  // 검증에 사용할 정규식 변수 regExp에 저장
    			var passwordregExp = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
    			  
    			var idregExp = /^[a-z0-9_]*$/;

    			var phoneregExp = /^[0-9]+$/;
      				
      			if(id == "") {
      				alert("아이디를 입력해주세요");
      				return;
      			}else if(id.length <5) {
      				alert("id를 5자리이상 입력해주세요");
      				return;
      			}
      			
      			if(!id.match(idregExp)) {
      				alert("아이디를 올바른 형식으로 입력해주세요");
      				return;
      			}
      			
      			if(idchk != id) {
      				alert("아이디중복체크 해주세요");
      				return;
      			} 
      			
      			if(password == "") {
      				alert("비밀번호를 입력해주세요");
      				return;
      			}else if(password.length <2) {
      				alert("비밀번호를 2자리 이상 입력해주세요");
      				return;
      			}
      			
      			if(!password.match(passwordregExp) ) {
      				alert("비밀번호를 8~15자리 이내의 숫자와 특수 문자를 포함해야합니다");
      				return;
      			}
      			
      			if(passwordcheck == "") {
      				alert("비밀번호를 확인해주세요");
      				return;
      			} else if(passwordcheck != password) {
      				alert("비밀번호가 맞지 않습니다.");
      				return;
      			} 
      			
      			if(name == "") {
      				alert("이름을 입력해주세요");
      				return;
      			}
      			
      			if(gender == "") {
      				alert("성별을 선택해주세요");
      				return;
      			}
      			
      			if(email == "") {
      				alert("이메일을 입력해주세요");
      				return;
      			}else if(!email.match(regExp)) {
      				alert("이메일 형식에 맞추어 입력해주세요");
      				return;
      			}
      			
      			if(phone == "" || phone.length < 7) {
      				alert("핸드폰번호를 제대로 입력해주세요");
      				return;
      			} else if(!phone.match(phoneregExp)) {
      				alert("숫자만 입력해주세요");
      				return;
      			}
      				
      			$("#form").submit();	
      		})
      		
      })
          		
          
        </script>
	    
	</head>
	<body>
	
	<!-- 페이지 이동용(기본은 마이페이지) -->
	<form name="form1" id="form1" method="POST" action="mypage.do" style="display: none;">
	<input type="hidden" name="userNo" value="<%=userNo%>" />
	</form>
	
	<div class="site-wrap">

    <div class="site-mobile-menu">
      <div class="site-mobile-menu-header">
        <div class="site-mobile-menu-close mt-3">
          <span class="icon-close2 js-menu-toggle"></span>
        </div>
      </div>
      <div class="site-mobile-menu-body"></div>
    </div> 
    <!-- .site-mobile-menu -->

    <!-- ##### Header Area Start ##### -->
	<div class="site-navbar-wrap js-site-navbar bg-white2">
      
     
              <div class="col-10">
                <nav class="site-navigation text-center" role="navigation">
                
                  <div class="container">
        			
        			
        
        			<div class="nav_wrapper"> 
					  <!--<a class="menu-link" href="#menu"></a>-->
					  
					  <br/>
					  <div class="">
               		 	<h2><a href="index.do" style="color: rgba(0, 0, 0, 0.9); font-size: 30px;">AirCheck</a></h2>
              		  </div>
					  <br/>
					  <div class="spinner-master">
					    <input type="checkbox" id="spinner-form" />
					    <label for="spinner-form" class="spinner-spin">
					    <div class="spinner diagonal part-1"> </div>
					    <div class="spinner horizontal"> </div>
					    <div class="spinner diagonal part-2"> </div>
					    </label>
					  </div>
					  
					  <nav id="menu" class="menu">
					    <ul class="dropdown">
					    <%if(id.equals("admin")) { %> 
					      <li class="b"><a href="index.do" title="Link">Home</a></li>
					      <%if("".equals(id)) { %>
					      <%}else {%>
					      <li><a href="UserList.do" title="Link">회원관리</a></li>
					      <%} %>
					      <li><a href="AirplaneSelect.do" title="Link">항공 운항 정보 관리</a></li>
					      <li><a href="ParkingLot.do" title="Link">공항 주차장 정보 관리</a></li>
					      <li><a href="BoardList.do" title="Link">커뮤니티 관리</a></li>
					      <%if("".equals(id) || id == null) { %>
					      <li class="b"><a href="register.do" title="Link">Register</a></li>
					      <li class="b"><a href="login.do" title="Link">Login</a></li>
					      <%} else { %>
					      <li class="b"><a href="register.do" title="Link">Register</a></li>
					      <li class="b"><a href="logout.do" title="Link"><%="관리자님 환영합니다." %> &nbsp; Logout</a></li>
					      <%  }  %>
					      
					      <%} else { %>
					      <li class="b"><a href="index.do" title="Link">Home</a></li>
					      <%if("".equals(id)) { %>
					      <%}else {%>
					      <li><a href="javascript:doSubmit('mypage.do');" title="Link">마이페이지</a></li>
					      <%} %>
					      <li><a href="AirplaneSelect.do" title="Link">항공 운항 정보</a></li>
					      <li><a href="ParkingLot.do" title="Link">공항 주차장 정보</a></li>
					      <li><a href="BoardList.do" title="Link">커뮤니티</a></li>
					      <%if("".equals(id) || id == null) { %>
					      <li class="b"><a href="register.do" title="Link">Register</a></li>
					      <li class="b"><a href="login.do" title="Link">Login</a></li>
					      <%} else { %>
					      <li class="b"><a href="register.do" title="Link">Register</a></li>
					      <li class="b"><a href="logout.do" title="Link"><%=id + "님 환영합니다." %> &nbsp; Logout</a></li>
					      <%  }  %>
					      <%  }  %>
					    </ul>
					  </nav>
					  
					</div>
        			
        
        
        
        </div>
        </nav>
        </div>
        </div>
        </div>
        		
          
       
	<!-- ##### Header Area End ##### -->
	
	 <!--================Home Banner Area =================-->
    <div style="height: 113px;"></div>
    <div class="slide-one-item home-slider owl-carousel">
      
      

      <div class="site-blocks-cover" style="background-image: url(/reborn/images/e.jpg);" data-aos="fade">
        <div class="container">
          <div class="row align-items-center justify-content-center">
            <div class="col-md-7 text-center" data-aos="fade">
              <h1>회원가입</h1>
              <p class="font-weight-normal"></p>
            </div>
          </div>
        </div>
      </div> 
    </div>
<!--================End Home Banner Area =================-->

<br/><br/><br/><br/><br/><br/>

<!--================About  Area =================-->

<div class="container">
    	<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-12">
								<a href="register.do" class="active" id="register-form-link">Register</a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form action="/rc.do"  method="POST" id="form" role="form" style="display: block;">
									<div class="form-group">
										<input type="text" name="id" id="idcheck" tabindex="1" class="form-control" placeholder="아이디">
									</div>
									
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="button" name="check" id="check" onclick="checkId();" tabindex="4" class="form-control btn btn-register" value="중복확인">
												<input type="hidden" name="idchk" id="idchk" value="">
											</div>
										</div>
									</div>
									
									<div class="form-group">
										<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="비밀번호">
									</div>
									<div class="form-group">
										<input type="password" name="passwordcheck" id="passwordcheck" tabindex="2" class="form-control" placeholder="비밀번호 확인">
									</div>
									<div class="form-group">
										<input type="text" name="name" id="name" tabindex="1" class="form-control" placeholder="이름">
									</div>
									<div class="form-group">
										<select class="form-control" name="gender" id="gender" style="height:45px;font-size:16px;">
											<option value="">성별 선택</option>
											<option value="남성">남성</option>
											<option value="여성">여성</option>
										</select>
									</div>
									<div class="form-group">
										<input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="이메일">
									</div>
									
									<div class="form-group">
										<input type="text" name="phone" id="phone" tabindex="1" class="form-control" placeholder="휴대폰 번호">
									</div>
									
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="button" name="ok" id="ok" tabindex="4" class="form-control btn btn-register" value="Register">
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<br/><br/>

<!--================About Area End =================-->	

    <br/><br/><br/><br/><br/>
    
    <!-- ##### Footer Area End ##### -->
	
	<script src="/reborn/js/jquery-3.3.1.min.js"></script>
  <script src="/reborn/js/jquery-migrate-3.0.1.min.js"></script>
  <script src="/reborn/js/jquery-ui.js"></script>
  <script src="/reborn/js/popper.min.js"></script>
  <script src="/reborn/js/bootstrap.min.js"></script>
  <script src="/reborn/js/owl.carousel.min.js"></script>
  <script src="/reborn/js/jquery.stellar.min.js"></script>
  <script src="/reborn/js/jquery.countdown.min.js"></script>
  <script src="/reborn/js/jquery.magnific-popup.min.js"></script>
  <script src="/reborn/js/bootstrap-datepicker.min.js"></script>
  <script src="/reborn/js/aos.js"></script>

  
  <script src="/reborn/js/mediaelement-and-player.min.js"></script>

  <script src="/reborn/js/main.js"></script>
  
 <script src="http://code.jquery.com/jquery-2.1.3.min.js"></script> 
 <script src="/example/script.js"></script>
	

	
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

		
		
	</body>
</html>