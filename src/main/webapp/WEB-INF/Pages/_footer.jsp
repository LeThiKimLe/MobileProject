<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		</style>
			<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
			<link rel="stylesheet" href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">

</head>
<body>

<footer class="footer-area section-gap">
                <!-- <div id="logo footer_logo" style="display: inline" >
                    <a href="index.html" class="text-uppercase" ><i class="fa fa-graduation-cap"></i> <i style="margin-left: 8px"></i> Internal Moon</a>
                  </div> -->
				<div class="container">
					<div class="row">
						<div class="col-lg-4 col-md-6 col-sm-6" style="border-right: 1px solid rgb(127, 119, 119);">
							<div class="single-footer-widget">
								<h4>
									Nhóm sinh viên thực hiện
                                </h4>
								<ul>
									<li>Lê Thị Kim Lệ _ 20110248</li>
									<li>Nguyễn Thị Cẩm Nguyên _ 20110315</li>
									<li>Nguyễn Thị Thùy Trang _ 20110753</li>
									<li>Ngô Vũ Nhật Nguyên _ 20110268</li>
								</ul>								
							</div>
						</div>
						
						<div class="col-lg-4 col-md-6 col-sm-6" style="border-right: 1px solid rgb(127, 119, 119);">
							<div class="single-footer-widget">
								<h4>Thông tin sản phẩm</h4>
								<ul>
									<li>
										<i class="fa fa-caret-right" style="margin-right:10px "></i>
										<a href="#">Giới thiệu</a>
									</li>
									<li>
										<i class="fa fa-caret-right" style="margin-right:10px "></i>
										<a href="#">Chính sách mua khóa học</a>
									</li>
									<li>
										<i class="fa fa-caret-right" style="margin-right:10px "></i>
										<a href="#">Chính sách bảo mật</a>
									</li>
									<li>
										<i class="fa fa-caret-right" style="margin-right:10px "></i>
										<a href="#">Hình thức thanh toán</a>
									</li>
								</ul>								
							</div>
						</div>

						<div class="col-lg-4 col-md-6 col-sm-6">
							<div class="single-footer-widget">
								<h4>Thông tin liên hệ</h4>
								<ul>
									<li>
										<i class="fa fa-phone" style="margin-right:10px "></i>
										<a href="#">Tel: 0842258275</a>
									</li>
									<li>
										<i class="fa fa-envelope" style="margin-right:10px "></i>
										<a href="#">Email: internal@moon.edu.vn</a>
									</li>
									<li>
										<i class="fa fa-location-arrow" style="margin-right:10px "></i>
										<a href="#">Trụ sở: Số 1, Võ Văn Ngân, Phường Linh Chiểu, Thành phố Hồ Chí Minh</a>
									</li>
									
								</ul>								
							</div>
						</div>
																						
																
					</div>
					<div class="footer-bottom row align-items-center justify-content-between">
						<p class="footer-text m-0 col-lg-6 col-md-12"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | Made by <i class="fa fa-heart-o" aria-hidden="true"></i> <a href="https://colorlib.com" target="_blank"></a> &amp; distributed by <a href="https://themewagon.com" target="_blank">ThemeWagon</a>
</p>
					<p class="footer-text m-0 col-lg-6 col-md-12" style="text-align: right"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					Chỉnh sửa và Vận hành bởi <a href="https://themewagon.com" target="_blank">Nhóm 17</a>
					</p>
					</div>						
				</div>
			</footer>	
			
		
			<div class="modal js-modal notice">
		        <div class="modal-container js-modal-container">
				    <div class="modal-close js-modal-close">
		                <i class="fa-regular fa-xmark"></i>
		            </div>
		            <header class="modal-header">
		                <i class="fa fa-badge-check"></i>
		                Thông báo
		            </header>
		            <div class="modal-body">
		                <p id="modal-content"></p>
		                <button id="buy-ticket" onclick="hideNotice()">
		                    OK
		                </button>
		            </div>
		        </div>
		    </div>
			
			<!-- End footer Area -->	
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/vendor/jquery-2.2.4.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/vendor/bootstrap.min.js"></script>			
			<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhOdIF3Y9382fqJYt5I_sswSrEw5eihAA"></script>
  			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easing.min.js"></script>			
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/hoverIntent.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/superfish.min.js"></script>	
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.ajaxchimp.min.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.magnific-popup.min.js"></script>	
    		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.tabs.min.js"></script>						
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.nice-select.min.js"></script>	
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>									
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mail-script.js"></script>	
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/notice.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/login-script.js"></script>					

</body>
</html>