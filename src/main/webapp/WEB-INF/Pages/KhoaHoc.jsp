<%@page import="bean.KhoaHoc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style ><%@include file = "/resources/css/khoilop.css" %></style>
</head>
<body>


<jsp:include page="_header.jsp"></jsp:include>

<section>
		<br>
  	  	<div style="left = 10px;">KHỐI LỚP</div>
	  	<ul class = "Khoi">
	  		<c:forEach items="${khoilopList}" var="khoilop">
	  			<li class = "level1"><a class = "khoilop"  href= "searchbyKL?tenKhoi=${khoilop.tenKhoi}">${khoilop.tenKhoi}</a></li>
	  		</c:forEach>
	  	</ul>
	  	<br>
	  	  	
		<div class="single-sidebar-widget search-widget" style="top: 100px; float: right;">
			<br>
			<form class="search-form" action="search" method ="post =">
				<input placeholder="Search" name="search" type="text" onfocus="this.placeholder = ''" onblur="this.placeholder = 'search'" >
				<button type="submit"><i class="fa fa-search"></i></button>
			</form>
		</div>	
	</section>

	<section class="popular-courses-area section-gap courses-page">		
				<h1 style="color: black; text-align: center;">Các khóa học nổi bật của chúng tôi</h1><br>
					<div class="row">
					<c:forEach items="${khoahocList}" var="khoahoc">
						<div class="single-popular-carusel col-lg-3 col-md-6">
							<div class="thumb-wrap relative">
								<div class="thumb relative">
									<div class="overlay overlay-bg"></div>
									<img class="img-fluid" src="${khoahoc.hinhAnhMoTa} VND" alt="">
								</div>
								<div class="meta d-flex justify-content-between">
									<p><span class="lnr lnr-users"></span> 355 <span class="lnr lnr-bubble"></span>35</p>
									<h4>"${khoahoc.giaTien}"</h4>
								</div>									
							</div>
							<div class="details">
								<a href="course-detail?maKhoaHoc=${khoahoc.maKhoaHoc}">
									<h4>
										"${khoahoc.tenKhoaHoc}" - GV "${khoahoc.giaoVien}"
									</h4>
								</a>
								<p>
									"${khoahoc.moTa}"
								</p>
							</div>
						</div>	
					</c:forEach>
				</div>
			</section>
		

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>