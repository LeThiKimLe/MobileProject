<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<!-- Phần My Page -->

        <div class="container">
            <p style="text-align: center; font-size: 30px; margin: 50px 0; color:rgb(229, 129, 16)">  <i class="fa fa-star"></i> THÔNG TIN GIÁO VIÊN <i class="fa fa-star"></i> </p>
            <div class="row" style="margin: 30px 0px;">
                <div class="col-lg-3">
                    <img class="img-fluid" src="${pageContext.request.contextPath}/resources/img/myicon.png" alt="">     
                </div>
                <div class="col-lg-9">
                    <div style="min-width: 200px; display: inline-block; margin-left: 135px"><a style="margin-top:20px; margin-bottom: 10px; font-size: 20px;"><b>Tên giáo viên </b></div> </a> <input style="min-width: 300px; margin-left: 50px; border: none;" type="text" name="" id="" value="${GiaoVien.tenGiaoVien}" readonly>
                    <p></p>
                    <div style="min-width: 200px; display: inline-block; margin-left: 135px"><a style="margin-top:20px; margin-bottom: 10px; font-size: 20px;"><b>Số điện thoại </b></div> </a> <input style="min-width: 300px; margin-left: 50px; border: none;" type="text" name="" id="" value="${GiaoVien.sdt}" readonly>
                    <p></p>
                    <div style="min-width: 200px; display: inline-block; margin-left: 135px"><a style="margin-top:20px; margin-bottom: 10px; font-size: 20px;"><b>CCCD </b></a></div>  <input style="min-width: 300px; margin-left: 50px; border: none;"  type="text" name="" id="" value="${GiaoVien.cccd}" readonly>
                    <p></p>
                    <div style="min-width: 200px; display: inline-block; margin-left: 135px"><a style="margin-top:20px; margin-bottom: 10px; font-size: 20px;"><b>Địa chỉ </b></a></div>  <input style="min-width: 300px; margin-left: 50px; border: none;"  type="text" name="" id="" value="${GiaoVien.diaChi}" readonly>
                    <p></p>
                    <div style="min-width: 200px; display: inline-block; margin-left: 135px"><a style="margin-top:20px; margin-bottom: 10px; font-size: 20px;"><b>Username</b></a></div>  <input style="min-width: 300px; margin-left: 50px; border: none;" type="text" name="" id="" value=<%=(String)session.getAttribute("username") %> readonly>
                    <p></p>
                    <div style="min-width: 200px; display: inline-block; margin-left: 135px"><a style="margin-top:20px; margin-bottom: 10px; font-size: 20px;"><b>Ngày ký kết</b></a></div>  <input style="min-width: 300px; margin-left: 50px; border: none;" type="text" name="" id="" value="${GiaoVien.ngayKyKet}" readonly>
                    <p></p>
                </div>
            </div>
            
            <p style="text-align: center; font-size: 30px; margin: 50px 0; color:rgb(229, 129, 16)">  <i class="fa fa-star"></i> CÁC KHÓA HỌC TÔI PHỤ TRÁCH <i class="fa fa-star"></i> </p>
            
           <c:forEach items="${sessionScope.teaching_course}" var="item">
	            <div class="row" style="margin: 30px 0px; border-bottom: 1px solid #ccc; padding-bottom: 10px;">
	                <div class="single-popular-carusel col-lg-4">   
	                    <div class="thumb-wrap relative">
	                        <div class="thumb relative">
	                            <div class="overlay overlay-bg"></div>	
	                            <img class="img-fluid" src="${item.hinhAnhMoTa}" alt="">
	                        </div>									
	                    </div>
	                </div> 
	                    <div class="details col-lg-8">
	                        <a href="course-detail?maKhoaHoc=${item.maKhoaHoc}">
	                            <h4>
	                                ${item.tenKhoaHoc}
	                            </h4>
	                        </a>
	                        <p>
	                        	${item.moTa}
	                        </p>
	                    </div>
	            </div>
            </c:forEach>
        </div>
        <!-- Hết phần my Page -->

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>