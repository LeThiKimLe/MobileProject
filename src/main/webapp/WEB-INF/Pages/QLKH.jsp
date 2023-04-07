<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
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
		                <li class="nav-bar-vertical-item" ><a href="QLGV" ><h4 style="color: white" >QUẢN LÝ GIÁO VIÊN</h4></a> </li>  
		                <li class="nav-bar-vertical-item"><a href="QLKH" ><h4 style="color: white">QUẢN LÝ KHÓA HỌC</h4></a> </li>                   
		                <li class="nav-bar-vertical-item"><a href="TKKH"><h4 style="color: white">THỐNG KÊ KHÓA HỌC</h4></a></li>
                	</ul>  
                </nav>
            </ul>
        </div>
    </div>
    </section>
    <!-- End banner Area -->
    

        <!-- Phần quản lý khóa học -->

        <div>
            <div class="container">
                <h2 style="margin: 20px; text-align: center;">QUẢN LÝ KHÓA HỌC</h2>
                <br> 
                
                <div class="table-responsive">
                  <table style="color:black" class="table table-bordered">
                   
                    <thead>
                      <tr>
                        
                        <th>Mã Khóa Học</th>
                        <th>Tên Khóa Học</th>
                        <th>Mô Tả Khóa Học</th>
                        <th>Giá Tiền</th>
                        <th>Ngày Cập Nhật</th>                    
                        <th>Phân Môn</th>
                        <th>Giáo Viên</th>
                        <th>Sửa Khóa Học</th>
    
                      </tr>
                    </thead>
                    <tbody>
                       <c:forEach items= "${QLKH}" var= "khoahoc" >
		          <tr>
		             <td style="width:10%">${khoahoc.maKhoaHoc}</td>
             
		            <td style="width:15%">${khoahoc.tenKhoaHoc}</td>
		             <td>${khoahoc.moTa}</td>
		             <td style="width:10%">${khoahoc.giaTien}</td>
		             <td style="width:10%">${khoahoc.ngayCapNhat}</td>
		             <td style="width:15%"> ${khoahoc.phanMon }</td>
		             <td>${khoahoc.giaoVien}</td>
		             <td>
		                <a href="edit_course?maKhoaHoc=${khoahoc.maKhoaHoc}">Edit</a>
		             </td>
		          </tr>
		       </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
              <div style="text-align: center;"><button type="button" style="background-color:coral" ><a href="add_course" style="color: black;">Thêm Khóa học</a></button>
              </div>
                <br>
              <br>
            </div>
        <!-- Hết phần quản lý khóa học -->

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>