<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
            
            <ul class="nav-bar-vertical" style="color: black !important;margin-top: -213px;">
                <nav id="nav-menu-container">
                    <ul class="nav-menu">               
                <li class="nav-bar-vertical-item" ><a href="QLGV" ><h4 style="color: white" >QUẢN LÝ GIÁO VIÊN</h4></a> </li>                  
                <li class="nav-bar-vertical-item"><a href="QLKH" ><h4 style="color: white">QUẢN LÝ KHÓA HỌC</h4></a> </li>
                <li class="nav-bar-vertical-item"><a href="TKKH"><h4 style="color: white">THỐNG KÊ KHÓA HỌC</h4></a></li>
               </ul>  
        </div>
    </div>
    </section>
    <!-- End banner Area -->
    

        <!-- Phần thêm giáo viên -->

        <br>

<center>

 <h3>Sửa thông tin khóa học</h3>
 <br><br>
       
      <p style="color: red;">${errorString}</p>
 		<c:if test="${not empty khoahoc}">
          <form method="POST" action="${pageContext.request.contextPath}/edit_course">
          	<input type="hidden" name="maKhoaHoc" value="${khoahoc.maKhoaHoc}" />
          	
                       <table border="0" style="width:50%" >
                          <tr>
                             <td  font size="1">Mã khóa học</td>
                             <td style="color:red;">${khoahoc.maKhoaHoc} </td>
                             
                          </tr>
                          <tr>
                             <td>Tên khóa học</td>
                             <td><input class="form-control" name ="tenKhoaHoc" type="text" value="${khoahoc.tenKhoaHoc}" /></td>
                          </tr>
                          <tr>
                             <td>Mô tả</td>
                             <td><input class="form-control" name="moTa" type="text" value="${khoahoc.moTa}"/></td>
                          </tr>
                          <td>Giá tiền</td>
                           <td><input class="form-control" name="giaTien" type="text" value="${khoahoc.giaTien}"/></td>
                           
                        </tr>
                        <tr>
                           <td>Ngày cập nhật</td>
                           <td><input class="form-control" name="ngayCapNhat" type='date' value="${khoahoc.ngayCapNhat}"/></td>
                        </tr>
                        <tr>
                           <td>Phân môn</td>
                           <td><input class="form-control" name="phanMon" type="text" value="${khoahoc.phanMon}"readonly/></td>
                        </tr>
                        <tr>
                           <td>Giáo viên</td>
                           <td>
                           <select class="form-control " name="giaoVien"  >
	                	<c:forEach items= "${giaovien}" var= "gv"> 
	                	
						<option value="${gv.maGiaoVien}">${gv.tenGiaoVien}</option>
						
					 </c:forEach>
				 </select>
				 </td>
                        </tr>
                       </table>
                       <br>
                  <br>
                 <button type="submit" value="submit" style="background-color:coral" >Lưu Thông Tin</button>
                 <br>
                 <br>
                  <button  style="background-color:coral" ><a href="${pageContext.request.contextPath}/QLKH" style="color:black">Hủy Bỏ</a></button>
                 
                    </form>   
                  </c:if>
                 <br>
                 <br>
      
      
    </center>
           
        <!-- Hết phần sửa khóa học -->

<jsp:include page="_footer.jsp"></jsp:include>


</body>
</html>