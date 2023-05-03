<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>

<!-- start banner Area -->
     <section class="banner-area relative about-banner" id="home">	
        <div class="overlay overlay-bg"></div>
        <div class="container">				
            <div class="row d-flex align-items-center justify-content-center">
                <div class="about-content col-lg-12">
                    <h1 class="text-white" style="margin-top: -130px;">
                        Admin Acount		
                    </h1>
                </div>	
                <ul class="nav-bar-vertical" style="color: black !important; margin-top: -213px;">
        
                    <nav id="nav-menu-container">
                        <ul class="nav-menu">
                   <!--  <li class="nav-bar-vertical-item"><a href="QLGV.html">Quản Lý Giáo Viên</a> -->
                    
                    <li class="nav-bar-vertical-item" ><a href="QLGV" ><h4 style="color: white" >QUẢN LÝ GIÁO VIÊN</h4></a> </li>  
                       <li class="nav-bar-vertical-item"><a href="QLKH" ><h4 style="color: white">QUẢN LÝ KHÓA HỌC</h4></a> </li>
                    <li class="nav-bar-vertical-item"><a href="TKKH"><h4 style="color: white">THỐNG KÊ KHÓA HỌC</h4></a></li>
                </ul>  
            </div>
           
        </div>
    </section>
    <!-- End banner Area -->
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>