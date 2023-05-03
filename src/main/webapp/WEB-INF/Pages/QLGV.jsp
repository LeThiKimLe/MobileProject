<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
    
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>Insert title here</title>
<script type="text/javascript">         
    function testConfirmDialog(maGiaoVien)  {
   	 
        var result = window.confirm("Bạn chắc chắn muốn xóa giáo viên này?");
      

        if(result)  {            	
      	  window.location.href= "del_GiaoVien?maGiaoVien="+maGiaoVien;
      	 
        } else {
            return false;
        }
   }  
 </script>
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
    
    
        <!-- Phần quản lý giáo viên -->
         

        <div>
            <div class="container" style="margin-top: 50px;" >
                <h2 style= "margin: 20px; text-align: center;" >QUẢN LÝ GIÁO VIÊN</h2>
                <br> 
                
                <div class="table-responsive">
                <p style="color: red;">${errorString}</p>
                  <table class="table table-bordered">
                    <thead>
                      <tr>
                        
                        <th>Mã Giáo Viên</th>
                        <th>Tên Giáo Viên</th>
                        <th>Số Điện Thoại</th>
                        <th>Căn Cước Công Dân</th>
                        <th>Địa Chỉ</th>
                        <th>Ngày Ký Kết</th>                   
                        <th>Sửa Giáo Viên</th>
                        <th>Xóa Giáo Viên</th>
                        
                      </tr>
                    </thead>
                    <tbody>
               <c:forEach items= "${QLGV}" var= "giaovien" >
		          <tr>
		             <td style="width:10%">${giaovien.maGiaoVien}</td>
		             
		            <td>${giaovien.tenGiaoVien}</td>
		             <td>${giaovien.sdt}</td>
		             <td>${giaovien.cccd}</td>
		             <td>${giaovien.diaChi}</td>
		             <td>${giaovien.ngayKyKet}</td>
		             <td>
		                <a href="edit_teacher?maGiaoVien=${giaovien.maGiaoVien}">Edit</a>
		             </td>
		             <td>             
		                <a href="#" onclick='testConfirmDialog("${giaovien.maGiaoVien}")'>Delete</a>
		             </td>
		          </tr>
		       </c:forEach>
                     
                    
                    </tbody>
                  </table>
                </div>
            </div>
              <div style="text-align: center;"><button type="button" style="background-color:coral" ><a href="add_teacher" style="color: black;">Thêm Giáo Viên</a></button>
              </div>
                <br>
              <br>
            </div>
        <!-- Hết phần quản lý giáo viên -->

<jsp:include page="_footer.jsp"></jsp:include>


</body>
</html>