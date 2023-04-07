<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>themgiaovien</title>
<script type="text/javascript">         
	    function validateForm()  {
	    	var x = document.forms["createGVForm"]["tenGiaoVien"].value;
	    	var y = document.forms["createGVForm"]["sdt"].value;
	    	var z = document.forms["createGVForm"]["cccd"].value;
	    	var t = document.forms["createGVForm"]["diaChi"].value;
	    	var h = document.forms["createGVForm"]["ngayKyKet"].value;
	    	var k=document.forms["createGVForm"]["chuyenmon"].value;
	    	  if (x == "") {
	    	    alert("Vui lòng nhập họ tên giáo viên");
	    	    return false;
	    	  }
	    	  if (y == "") {
		    	    alert("Vui lòng nhập số điện thoại");
		    	    return false;
		    	 }
	    	  if (z == "") {
		    	    alert("Vui lòng nhập số căn cước");
		    	    return false;
		    	 }
	    	  if (t == "") {
		    	    alert("Vui lòng nhập địa chỉ");
		    	    return false;
		    	 }
	    	  if (h == "") {
		    	    alert("Vui lòng nhập ngày ký kết");
		    	    return false;
		    	 }
	    	 else
	    		  return true;
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
            
            <ul class="nav-bar-vertical" style="color: black !important;margin-top: -213px;">
        
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
            <h3>Thêm Giáo Viên Mới</h3>
            <br>
           
            
               <p style="color: red;">${errorString}</p>
 
         <form method="POST" action="${pageContext.request.contextPath}/add_teacher"
          name="createGVForm" onsubmit="return validateForm()" >
						          <h4 style="margin:30px; margin-left:80%">Môn chuyên</h4>
						            <div style="margin:30px; margin-left:80%">
											<select name="chuyenmon">
						            <optgroup label="Tiếng Việt">
						      
						      <option value="PM001">Tiếng Việt lớp 1</option>
						      <option value="PM002">Tiếng Việt lớp 2</option>
						      <option value="PM003">Tiếng Việt lớp 3</option>
						      <option value="PM004">Tiếng Việt lớp 4</option>
						      <option value="PM005">Tiếng Việt lớp 5</option>
						      
						    </optgroup>
						
						    <optgroup label="Toán">
						     <option value="PM006">Toán lớp 1</option>
						      <option value="PM007">Toán lớp 2</option>
						      <option value="PM008">Toán lớp 3</option>
						      <option value="PM009">Toán lớp 4</option>
						      <option value="PM010">Toán lớp 5</option> 
						      <option value="PM020">Toán lớp 6</option>
						      <option value="PM021">Toán lớp 7</option>
						      <option value="PM022">Toán lớp 8</option>
						      <option value="PM023">Toán lớp 9</option> 
						    </optgroup>
						
						   <optgroup label="Tiếng Anh">
						     <option value="PM011">Tiếng Anh lớp 1</option>
						      <option value="PM012">Tiếng Anh lớp 2</option>
						      <option value="PM013">Tiếng Anh lớp 3</option>
						      <option value="PM014">Tiếng Anh lớp 4</option>
						      <option value="PM015">Tiếng Anh lớp 5</option>
						      <option value="PM024">Tiếng Anh lớp 6</option>
						      <option value="PM025">Tiếng Anh lớp 7</option>
						      <option value="PM026">Tiếng Anh lớp 8</option>
						      <option value="PM027">Tiếng Anh lớp 9</option> 
						    </optgroup>
						     <optgroup label="Ngữ Văn">
						     <option value="PM016">Ngữ Văn lớp 6</option>
						      <option value="PM017">Ngữ Văn lớp 7</option>
						      <option value="PM018">Ngữ Văn lớp 8</option>
						      <option value="PM019">Ngữ Văn lớp 9</option>    
						    </optgroup>
						    <optgroup label="Vật Lý">
						     <option value="PM028">Vật Lý lớp 6</option>
						      <option value="PM029">Vật Lý lớp 7</option>
						      <option value="PM030">Vật Lý lớp 8</option>
						      <option value="PM031">Vật Lý lớp 9</option>    
						    </optgroup>
						    <optgroup label="Sinh Học">
						     <option value="PM032">Sinh Học lớp 6</option>
						      <option value="PM033">Sinh Học lớp 7</option>
						      <option value="PM034">Sinh Học lớp 8</option>
						      <option value="PM035">Sinh Học lớp 9</option>    
						    </optgroup>
						    <optgroup label="Sinh Học">
						     <option value="PM036">Hóa Học lớp 8</option>
						      <option value="PM037">Hóa Học lớp 9</option>   
						    </optgroup>
			               </select>
			               </div>

                       <table border="0" style="width:50%" >
                          <tr>
                             <td  font size="1">Mã Giáo Viên</td>
                             <td><input class="form-control" type="text" value="${maGiaoVien}" readonly/></td>
                          </tr>
                          <tr>
                             <td>Tên Giáo Viên</td>
                             <td><input class="form-control" type="text" name="tenGiaoVien" on />
                          </tr>
                          <tr>
                             <td>Số Điện Thoại</td>
                             <td><input class="form-control" type="text" name="sdt"/></td>
                          </tr>
                          <tr>
                             <td>Email</td>
                             <td><input class="form-control" type="text" name="email"/></td>
                          </tr>
                          <tr>
                           <td>Căn Cước Công Dân</td>
                           <td><input class="form-control"type="text" name="cccd"/></td>
                           
                        </tr>
                        <tr>
                           <td>Địa Chỉ</td>
                           <td><input class="form-control" type="text" name="diaChi"/></td>
                        </tr>
                        <tr>
                           <td>Ngày Ký Kết</td>
                           <td> <input type='date' class="form-control" name ="ngayKyKet" /></td>
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
                  
                 <br>
                 <br>
           </center>
          
        <!-- Hết phần thêm giáo viên -->


<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>