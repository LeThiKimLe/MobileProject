<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<script type="text/javascript">
	function validateForm()
	{
		var a = document.forms["SignInForm"]["name"].value;
		var b = document.forms["SignInForm"]["email"].value;
		var c = document.forms["SignInForm"]["username"].value;
		var y = document.forms["SignInForm"]["password"].value;
		var x = document.forms["SignInForm"]["passagain"].value;

		
		if (a=="")
		{
			showNotice("Vui lòng nhập tên");
			return false;
		}
		if (b=="")
		{
			showNotice("Vui lòng nhập email");
			return false;
		}
		if (c=="")
		{
			showNotice("Vui lòng nhập tên đăng nhập");
			return false;
		}
		if (y=="")
			{
				showNotice("Vui lòng nhập mật khẩu");
				return false;
			}
		if (x=="")
		{
			showNotice("Vui lòng nhập lại mật khẩu");
			return false;
		}
		if (x!="" && y!="" && x!=y)
		{
			showNotice("Mật khẩu không khớp. Vui lòng nhập lại");
			return false;
		}
		
		return true;
	}
	function showVerify()
	{
		formverify.classList.add('open')
	}

	function hideVerify()
	{
		formverify.classList.remove('open')
	}

	function XacThuc()
	{	
		var code= document.querySelector('.verify-code').value;
		if (code=="")
			showNotice("Vui lòng nhập mã")
		else
			window.location.href="signup?action=verify&code="+code;
	}
</script>

<body>

<jsp:include page="_header.jsp"></jsp:include>
<!-- Phần đăng ký -->
<form method="POST" action="${pageContext.request.contextPath}/signup" name="SignInForm" onsubmit="return validateForm()">
       <div style="min-height: 400px;">
           <p style="text-align: center; font-size: 35px; margin: 50px 0; color:rgb(229, 129, 16)">  <i class="fa fa-star"></i>  CHÀO MỪNG ĐẾN VỚI HỆ THỐNG CỦA INTERNAL MOON  <i class="fa fa-star"></i> </p>
           <section class="container" >
               <div class="col-lg-3"></div>
               <div class="col-lg-6" style="max-width: 100%;">
                   <div style="min-width: 250px; display: inline-block; margin-left: 135px"><a style="margin-top:20px; margin-bottom: 10px; font-size: 20px;"><b>Tên học viên (*)</b></div> </a> <input style="min-width: 400px; margin-left: 50px;" placeholder="Hãy điền tên của bạn nhé" type="text" name="name" id="" value="${sessionScope.signing_hv.tenHocVien}">
                   <p></p>
                   <div style="min-width: 250px; display: inline-block; margin-left: 135px"><a style="margin-top:20px; margin-bottom: 10px; font-size: 20px;"><b>Số điện thoại </b></a></div>  <input style="min-width: 400px; margin-left: 50px" placeholder="Số điện thoại của bạn" type="text" name="sdt" id="" value="${sessionScope.signing_hv.sdt}">
                   <p></p>
                   <div style="min-width: 250px; display: inline-block; margin-left: 135px"><a style="margin-top:20px; margin-bottom: 10px; font-size: 20px;"><b>Email(*) </b></a></div>  <input style="min-width: 400px; margin-left: 50px"  placeholder="Email của bạn (nếu có)" type="email" name="email" id="" value="${sessionScope.signing_hv.email}">
                   <p></p>
                    <div style="min-width: 250px; display: inline-block; margin-left: 135px"><a style="margin-top:20px; margin-bottom: 10px; font-size: 20px;"><b>Ngày sinh </b></a></div>  <input style="min-width: 400px; margin-left: 50px"  placeholder="Ngày sinh của bạn (nếu có)" type='date' name="ngaysinh" id="" value="${sessionScope.signing_hv.ngaySinh}">
                   <p></p>
                   <div style="min-width: 250px; display: inline-block; margin-left: 135px"><a style="margin-top:20px; margin-bottom: 10px; font-size: 20px;"><b>Tên đăng nhập (*) </b></a></div>  <input style="min-width: 400px; margin-left: 50px" placeholder="Tên đăng nhập bạn thích" type="text" name="username" id="" value="${sessionScope.signing_account.username}">
                   <p></p>
                   <div style="min-width: 250px; display: inline-block; margin-left: 135px"><a style="margin-top:20px; margin-bottom: 10px; font-size: 20px;"><b>Nhập mật khẩu (*) </b></a></div>  <input style="min-width: 400px; margin-left: 50px" placeholder="Mật khẩu" type="password" name="password" id="" value="${sessionScope.signing_account.password}">
                   <p></p>
                   <div style="min-width: 250px; display: inline-block; margin-left: 135px"><a style="margin-top:20px; margin-bottom: 10px; font-size: 20px;"><b>Nhập lại mật khẩu (*) </b></a></div>  <input style="min-width: 400px; margin-left: 50px" placeholder="Hãy nhập lại chính xác nhé" type="text" name="passagain" id="">
                   <p></p>
                   </div>
               <div class="col-lg-12" style="text-align: center"> <i style="color: red;">${errorStringUsername}</i> </div>
               <div style="text-align: center"><input class="primary-btn text-uppercase" style="margin: 20px 0;min-width: 320px; text-align: center;" type="submit" value="Đăng ký"></div>
           </section>
       </div>
</form>
        <!-- Hết phần đăng ký -->
        
<!-- Phần form mã xác thực -->
        <div class="modal js-modal verify">
        <div class="modal-container js-modal-container">
            <div class="modal-close js-modal-close">
                <i class="ti-close"></i>
            </div>
            <header class="modal-header">
                <i class="modal-head-icon ti-bag"></i>
                Mã xác thực đã được gửi đến email của bạn
            </header>
            <div class="modal-body" style="margin: 20px; text-align: center;"> 
            	<i>Nhập mã xác thực được gửi đến email bạn đã đăng ký vào đây</i>
            	<br>
                <div style="font-size: 15; font-weight: 600; display: inline-block;">Mã xác thực:</div> <input class="verify-code" style="margin-left: 30px; min-width: 200px; display: inline-block;" placeholder="Nhập mã xác thực gồm 6 ký tự"/>
            </div>
            <i style="color: red; margin-left: 347px;">${errorString}</i>
            <div class="modal-body">
	            <button class="primanry-btn submitbtn" style="margin-top: 40px; background-color: #50a8a0; cursor:pointer;" onclick="XacThuc()">
	            Xác thực
	             </button>
	              <button style="margin-top: 40px; margin-left: 20px; cursor:pointer;" class="bill-close" onclick="hideVerify()">
	                  Hủy
	              </button>
             </div>
        </div>
    </div>
    <!-- Hết phần Mã xác thực -->	

<jsp:include page="_footer.jsp"></jsp:include>

<script type="text/javascript">
	const formverify= document.querySelector('.js-modal.verify');
</script>

 <c:set var="check" value="${sendEmail}"/>
		<c:if test="${check == 'ok'}">
			<script> showVerify()</script>
		</c:if>
		
</body>
</html>