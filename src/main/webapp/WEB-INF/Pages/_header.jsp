<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Mobile Specific Meta -->
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<!-- Author Meta -->
		<meta name="author" content="colorlib">
		<!-- Meta Description -->
		<meta name="description" content="">
		<!-- Meta Keyword -->
		<meta name="keywords" content="">
		<!-- meta character set -->
		<meta charset="UTF-8">
		<!-- Site Title -->
		<title>Education</title>

		<link href="https://fonts.googleapis.com/css?family=Poppins:100,200,400,300,500,600,700" rel="stylesheet"> 
			<!--
			CSS
			============================================= -->
		<style type="text/css">
			<%@include file="/resources/css/bootstrap.css"%>
			<%@include file="/resources/css/magnific-popup.css"%>
			<%@include file="/resources/css/nice-select.css"%>							
			<%@include file="/resources/css/animate.min.css"%>
			<%@include file="/resources/css/owl.carousel.css"%>			
			<%@include file="/resources/css/jquery-ui.css"%>			
			<%@include file="/resources/css/main.css"%>
			<%@include file="/resources/css/cart_css.css"%>
		</style>
			<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
			<link rel="stylesheet" href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">

</head>
<body>

 <header id="header" id="home">
    <div class="header-top">
      <div class="container">
        <div class="row">
          <div class="col-lg-6 col-sm-6 col-8 header-top-left no-padding">
            <ul>
              <li><a href="#"><i class="fa fa-facebook"></i></a></li>
              <li><a href="#"><i class="fa fa-twitter"></i></a></li>
              <li><a href="#"><i class="fa fa-youtube"></i></a></li>
            </ul>			
          </div>
          <div class="col-lg-6 col-sm-6 col-4 header-top-right no-padding">
            <a href="tel:+08347569175"><span class="lnr lnr-phone-handset"></span> <span class="text">+08347569175</span></a>
            <a href="mailto:internal@moon.edu.vn"><span class="lnr lnr-envelope"></span> <span class="text">internal@moon.edu.vn</span></a>			
          </div>
        </div>			  					
      </div>
    </div>
    <div class="container main-menu">
      <div class="row align-items-center justify-content-between d-flex">
        <div id="logo">
        <a href="index.html" class="text-uppercase" style="font-size: 20px;" ><i class="fa fa-graduation-cap"></i> <i style="margin-left: 8px"></i> Internal Moon</a>
        </div>
        <nav id="nav-menu-container">
        <ul class="nav-menu">
          <li><a href="home">Trang chủ</a></li>
          <li><a href="courses">Các khóa học</a></li>
          	<%
			//allow access only if session exists
			String user = "Guest";
			if(session.getAttribute("username") == null)
			{
				user="Guest";
			}
			else
				user = (String)session.getAttribute("username");
			%>
			
			<script type="text/javascript">
				function login_request()
		  		{
		  			var usern= '<%=user%>'
		  			if (usern=="Guest")
		  			{
		  				alert("Vui lòng đăng nhập để thực hiện chức năng này")
		  				return false;
		  			}
		  			return true;
		  		}
			</script>
          <li style="margin-left: 50px">
           <a href="cart">
	           <div class="cart_shopping">
	          		<i class="fa fa-shopping-cart" style="margin: 0 5px; font-size: 20px;"></i> 
						<c:if test="${sessionScope.cart!=null && sessionScope.cart.size()!=0}">
							<div class="cart_number">${sessionScope.cart.size()}</div>
						</c:if>
	          </div>
          </a>
          </li>
          <li id="privatepage" onclick="return login_request()"><a href="mypage"> <i class="fa fa-user" style="font-size: 20px;"></i> <b style="margin-left: 5px"><%=user%></b></a></li>
          
          <li style="margin:0; border-right: 1px solid #ccc;" class="login"><a href="login" class="text-uppercase" style="text-decoration: underline;font-size: 10px;">Đăng nhập</a></li>
          <li style="margin: 0;"><a href="signup" class="text-uppercase login" style="text-decoration: underline;font-size: 10px;">Đăng ký</a></li>
          <li style="margin: 0;"><a href="signout" class="text-uppercase logout hide" style="text-decoration: underline;font-size: 10px;">Đăng xuất</a></li>
        </ul>
        </nav><!-- #nav-menu-container --> 		
      </div>
    </div>
    </header><!-- #header -->

    <!-- start banner Area -->
			<section class="banner-area relative about-banner" id="home" style="background: url(${pageContext.request.contextPath}/resources/img/top-banner.jpg) right no-repeat;">	
				<div class="overlay overlay-bg"></div>
				<div class="container">				
					<div class="row d-flex align-items-center justify-content-center">
						<div class="about-content col-lg-12">
							<!--  Phần này là các file masterpage con, dùng include trong jsp để add vô -->
              <h1 class="text-uppercase"  style="color: white">
								Internal Moon: Học để trưởng thành
							</h1>
							<p class="pt-10 pb-10">
							</p>
							</div>	
					</div>
				</div>
			</section>
			<!-- End banner Area -->
			
	<div style="display: none;">  </div>
	 <script>
	        const login_item= document.querySelectorAll('.login')
	  		const logout_item= document.querySelector('.logout')
	  		var username = '<%=user%>'
	  		if (username!="Guest")
	  		{
				for(const item of login_item)
		        {
		            item.classList.add('hide')
		        }
		        logout_item.classList.remove('hide')
	  		}
	  		else
	  		{
	  			for(const item of login_item)
		        {
		            item.classList.remove('hide')
		        }
		        logout_item.classList.add('hide')
	  		}
	  		
	  		const privatepage = document.getElementById('privatepage')
	  		privatepage.onlick= "return login_request()"
	  		
        </script>
	
</body>
</html>