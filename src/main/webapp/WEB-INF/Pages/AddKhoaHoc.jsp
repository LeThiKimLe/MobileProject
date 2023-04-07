<%@page import="bean.GiaoVien"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="utils.DBUntilQLKH" %>
<!DOCTYPE html>
<script type="text/javascript">         
	    function validateForm()  {
	    	var x = document.forms["createGVForm"]["tenKhoaHoc"].value;
	    	var y = document.forms["createGVForm"]["moTa"].value;
	    	var z = document.forms["createGVForm"]["giaTien"].value;
	    	var t = document.forms["createGVForm"]["ngayCapNhat"].value;
	    	
	    	  if (x == "") {
	    	    alert("Vui lòng nhập họ tên khóa học");
	    	    return false;
	    	  }
	    	  if (y == "") {
		    	    alert("Vui lòng nhập mô tả");
		    	    return false;
		    	 }
	    	  if (z == "") {
		    	    alert("Vui lòng nhập giá tiền");
		    	    return false;
		    	 }
	    	  if (t == "") {
		    	    alert("Vui lòng nhập ngày cập nhật");
		    	    return false;
		    	 }

	    	 else
	    		  return true;
	   }  
      </script>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script  type="text/javascript"> 
	
	function addcon(parent,magiaovien, tengiaovien)
	{
		var newitem = document.createElement('option');
		parent.appendChild(newitem);
		newitem.value=magiaovien;
		newitem.innerHTML = tengiaovien;
		
	}

    function change()
    {
    	var x = document.forms["createGVForm"]["phanMon"].value;
    	var parent = document.querySelector(".form-control.gv-contain")
    	var ele = document.querySelectorAll(".giaovien")
    	parent.replaceChildren();
    	var name, magv;
		for (var i=0; i<ele.length; i++)
    	{
            var item = ele[i];
            if (item.querySelector(".mapm").innerHTML==x)
            {
				name=ele[i].querySelector(".name").innerHTML
				magv=ele[i].querySelector(".id").innerHTML
				addcon(parent, magv, name);
            	
            }
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

 <h3>Thêm Khóa Học Mới</h3>
 <br><br>
 <p style="color: red;">${errorString}</p>
 
         <form  method="POST" action="${pageContext.request.contextPath}/add_course"
          name="createGVForm" onsubmit="return validateForm()" >
          
            <table style="width:50%" >
               <tr>
                  <td  font size="1">Mã Khóa Học</td>
                  <td><input class="form-control" type="text" name="maKhoaHoc"/></td>
               </tr>
               <tr>     
                <td>Phân Môn</td>
                <td>
                <select class="form-control" name="phanMon" onclick="change()">
                <c:forEach items= "${phanmon}" var= "pm">
               	 						      
					<option value="${pm.maPhanMon}">${pm.tenPhanMon}</option>					
				 </c:forEach>
				 </select>
				 </td>
               </tr>
             
               <tr>
                <td>Giáo Viên</td>
                
				<td>
                <select class="form-control gv-contain" name="giaoVien">
	                <c:forEach items= "${giaovien}" var= "gv"> 
	                <c:if test="${gv.chuyenmon=='PM001'}">
						<option value="${gv.maGiaoVien}">${gv.tenGiaoVien}</option>
					</c:if>
					 </c:forEach>
				 </select>
				 </td>

               </tr>
               <tr>
                  <td>Tên Khóa Học</td>
                  <td><input class="form-control" type="text" name="tenKhoaHoc" /></td>
               </tr>
               <tr>
                  <td>Mô Tả Khóa Học</td>
                  <td><input class="form-control" type="text" name="moTa"/></td>
               </tr>
             <tr>
                <td>Giá Tiền</td>
                <td><input class="form-control" type="text" name="giaTien"/></td>
             </tr>
             <tr>
                <td>Ngày Cập Nhật</td>
                <td> <input type='date' class="form-control" name="ngayCapNhat"/></td>
             </tr>
      
            </table> 
            <br>
       <br>
       <table style="width:50%;display :none" >
       			
                <c:forEach items= "${giaovien}" var= "gv"> 
                <tr class="giaovien">
                <td style="width:10%" class="id">${gv.maGiaoVien}</td>
		        <td class="name">${gv.tenGiaoVien}</td>
		        <td class="mapm">${gv.chuyenmon}</td>
		        </tr>
				 </c:forEach>
               
       </table>
      <button type="submit" style="background-color:coral" >Lưu Thông Tin</button>  
      <br>
      
         </form> 
         <br>
         
        <button  style="background-color:coral" ><a href="${pageContext.request.contextPath}/QLKH" style="color:black">Hủy Bỏ</a></button>
      <br>
      <br>
      
    </center>
           
        <!-- Hết phần thêm khóa học -->

<jsp:include page="_footer.jsp"></jsp:include>


</body>
</html>