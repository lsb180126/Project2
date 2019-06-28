<%@page import="poly.dto.BoardDTO"%>
<%@page import="poly.dto.UserDTO"%>
<%@page import="poly.dto.AirplaneDTO"%>
<%@page import="poly.util.CmmUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/SessionValue.jsp"%>
<%
	UserDTO uDTO = (UserDTO) request.getAttribute("uDTO");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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


<script type="text/javascript">
	function doSubmit(url) {

		//폼을 객체로 가져옴
		var f = document.getElementById("form1");
		f.action = url;
		f.submit();

	}
</script>

<style>
img {
	max-width: 52%;
}



.form-control {
	width: 25%;
	display: inline;
}


* {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}

.buttons {
    margin: 10%;
    text-align: center;
}

.btn-hover {
    width: 300px;
    font-size: 16px;
    font-weight: 600;
    color: #fff;
    cursor: pointer;
    margin: 20px;
    height: 55px;
    text-align:center;
    border: none;
    background-size: 300% 100%;

    
    moz-transition: all .4s ease-in-out;
    -o-transition: all .4s ease-in-out;
    -webkit-transition: all .4s ease-in-out;
    transition: all .4s ease-in-out;
}

.btn-hover:hover {
    background-position: 100% 0;
    moz-transition: all .4s ease-in-out;
    -o-transition: all .4s ease-in-out;
    -webkit-transition: all .4s ease-in-out;
    transition: all .4s ease-in-out;
}

.btn-hover:focus {
    outline: none;
}

.btn-hover.color-1 {
    background-image: linear-gradient(to right, #25aae1, #40e495, #30dd8a, #2bb673);
    box-shadow: 0 4px 15px 0 rgba(49, 196, 190, 0.75);
    
    height: 90px;
    font-size: 30px;
    
}

.register-login-area a {

	    height: 53px;

}

.site-blocks-cover, .site-blocks-cover .row {
    
    height: calc(75vh);
}
.bg-light {
    background-color: #fff!important;
}





</style>






</head>
<body>

	<!-- 페이지 이동용(기본은 마이페이지) -->
	<form name="form1" id="form1" method="POST" action="mypage.do"
		style="display: none;">
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
               		 	<h2><a href="index.do" style="color: rgba(0, 0, 0, 0.9);">AirCheck</a></h2>
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
	
	<div style="height: 113px;"></div>
    <div class="slide-one-item home-slider owl-carousel">
      
      

      <div class="site-blocks-cover" style="background-image: url(/reborn/images/e.jpg); " data-aos="fade">
        <div class="container">
          <div class="row align-items-center justify-content-center">
            <div class="col-md-7 text-center" data-aos="fade">
              <h1 style="font-size:30px;">항공 운항 정보</h1>
              <p class="font-weight-normal"></p>
            </div>
          </div>
        </div>
      </div> 
    </div>

	<!-- ##### Blog Area Start ##### -->
	
	 
	
	<br/><br/>
	
	
	
	<div class="site-section site-block-feature bg-light">
      <div class="container">
        <div class="d-block d-md-flex">
          <div class="text-center p-4 item border-right border-top border-left border-bottom" style="border-right: 1px solid #dee2e6!important; ">
            <span class="flaticon-paper-plane display-3 mb-3 d-block text-primary"></span>
            <h2 class="h5 text-uppercase">인천공항</h2>
            <p></p>
            <p><a href="InCheonAirplaneOperation.do">Read More <span class="icon-arrow-right small"></span></a></p>
          </div>
          <div class="text-center p-4 item border-right border-top border-left border-bottom" style="border-right: 1px solid #dee2e6!important; border-bottom: 1px solid #dee2e6!important;">
            <span class="flaticon-paper-plane display-3 mb-3 d-block text-primary"></span>
            <h2 class="h5 text-uppercase">그 외</h2>
            <p></p>
            <p><a href="AirplaneOperation.do">Read More <span class="icon-arrow-right small"></span></a></p>
          </div>
        </div>
      </div>
    </div>
	
	<br/><br/>
	
	<!-- ##### Footer Area Start ##### -->
	
	<!-- ##### All Javascript Script ##### -->

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
		
	<!-- ##### Footer Area End ##### -->


</body>
</html>