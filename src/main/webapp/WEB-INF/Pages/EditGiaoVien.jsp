<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>editgiaovien</title>

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

        <!-- Phần thêm giáo viên -->

        <center>
 			<br>
            <h3>Sửa thông tin giáo viên</h3>
            <br>
           
            
               <p style="color: red;">${errorString}</p>
 		<c:if test="${not empty giaovien}">
          <form method="POST" action="${pageContext.request.contextPath}/edit_teacher">
          	<input type="hidden" name="maGiaoVien" value="${giaovien.maGiaoVien}" />
                       <table border="0" style="width:50%" >
                          <tr>
                             <td  font size="1">Mã Giáo Viên</td>
                             <td style="color:red;">${giaovien.maGiaoVien} </td>
                             
                          </tr>
                          <tr>
                             <td>Tên Giáo Viên</td>
                             <td><input class="form-control" name ="tenGiaoVien" type="text" value="${giaovien.tenGiaoVien}" /></td>
                          </tr>
                          <tr>
                             <td>Số Điện Thoại</td>
                             <td><input class="form-control" name="sdt" type="text" value="${giaovien.sdt}"/></td>
                          </tr>
                          <tr>
                           <td>Căn Cước Công Dân</td>
                           <td><input class="form-control" name="cccd" type="text" value="${giaovien.cccd}"/></td>
                           
                        </tr>
                        <tr>
                           <td>Địa Chỉ</td>
                           <td><input class="form-control" name="diaChi" type="text" value="${giaovien.diaChi}"/></td>
                        </tr>
                        <tr>
                           <td>Ngày Ký Kết</td>
                           <td> <input type='date' class="form-control" name="ngayKyKet" value="${giaovien.ngayKyKet}"/></td>
                        </tr>
                          <tr>
                             
                          </tr>
                       </table>
                       
                       <br>
                  <br>
                 <button type="submit" value="submit" style="background-color:coral" >Lưu Thông Tin</button>
                 <br>
                 <br>
                  <button  style="background-color:coral" ><a href="${pageContext.request.contextPath}/QLGV" style="color:black">Hủy Bỏ</a></button>
                 
                    </form>   
                  </c:if>
                 <br>
                 <br>
           </center>
          
        <!-- Hết phần thêm giáo viên -->


<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>