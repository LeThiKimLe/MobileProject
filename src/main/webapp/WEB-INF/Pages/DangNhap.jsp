<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function validateForm()
	{
		var x = document.forms["LoginForm"]["username"].value;
		var y = document.forms["LoginForm"]["password"].value;
		if (x=="")
			{
				showNotice("Vui lòng nhập tên đăng nhập");
				return false;
			}
		if (y=="")
			{
				showNotice("Vui lòng nhập mật khẩu");
				return false;
			}
		return true;	
	}
	
	function triggerMe()
	{
		var x = document.forms["LoginForm"]["username"].value;
		if (x=="")
			{
				showNotice("Vui lòng nhập tên đăng nhập");
				return false;
			}
		else
			window.location.href="login?action=getPass&username="+x;	
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
		var newpass= document.querySelector('.new-pass').value;
		if (code=="")
			showNotice("Vui lòng nhập mã")
		if (newpass=="")
			showNotice("Vui lòng nhập mật khẩu mới")
		else
			window.location.href="login?action=verify&code="+code+"&newpass="+newpass;
	}

</script>

</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
		
<!-- Phần đăng nhập -->
<form method="POST" action="${pageContext.request.contextPath}/login" name="LoginForm" onsubmit="return validateForm()">
        <div style="min-height: 400px;">
            <p style="text-align: center; font-size: 40px; margin: 50px 0; color:rgb(229, 129, 16)">  <i class="fa fa-star"></i>  ĐĂNG NHẬP VÀO HỆ THỐNG CỦA INTERNAL MOON  <i class="fa fa-star"></i> </p>
            <section class="container" >
                <div class="col-lg-3"></div>
                <div class="col-lg-6" style="text-align: center; max-width: 100%;">
                    <p style="margin-top:20px; margin-bottom: 10px; font-size: 20px;"><b>Tên đăng nhập</b> </p> <input style="min-width: 400px;" type="text" name="username" id="" value="${username}">
                    <p style="margin-top:20px; margin-bottom: 10px; font-size: 20px;"><b>Mật khẩu </b></p> <input style="min-width: 400px;" type="text" name="password" id="">
                    <p></p>
                </div>
                <div class="col-lg-3"></div>
                <div class="col-lg-6" style="max-width: 100%; text-align: center;">
                    <input class="primary-btn text-uppercase" style="margin: 20px 0; min-width: 320px; text-align: center;" type="submit" value="Đăng nhập">
                    <p style="color: red;">${errorString}</p>
                    <br>
                    Bạn quên mật khẩu? <a href="#" style="margin-left: 10px" onclick="return triggerMe();">Lấy lại mật khẩu</a>
                    <br>
                    Bạn chưa có tài khoản? <a href="signup" class="text-uppercase" style="margin-left: 10px">Đăng ký</a>
                </div>
            </section>
        </div>
</form>
        <!-- Hết phần đăng nhập -->
        
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
            	<i>Chúng tôi đã gửi mã xác thực đến email bạn đã đăng ký. Hãy nhập mã xác thực tại đây để đổi mật khẩu</i>
            	<br>
                <div style="font-size: 15; font-weight: 600; display: inline-block;">Mã xác thực:</div> <input class="verify-code" style="margin-left: 30px; min-width: 200px; display: inline-block;" placeholder="Nhập mã xác thực gồm 6 ký tự"/>
           		<br><br>
                <div style="font-size: 15; font-weight: 600; display: inline-block;">Nhập mật khẩu mới:</div> <input class="new-pass" style="margin-left: 30px; min-width: 200px; display: inline-block;" placeholder="Nhập mật khẩu mới"/>
            </div>
            <i style="color: red; margin-left: 347px;">${errorString2}</i>
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
<c:set var="check" value="${verifyCode}"/>
		<c:if test="${check == 'ok'}">
			<script> showNotice("Bạn đã tạo tài khoản thành công. Vui lòng đăng nhập")</script>
		</c:if>
		
<c:set var="check" value="${getPass}"/>
		<c:if test="${check == 'ok'}">
			<script> showNotice("Bạn đã đổi mật khẩu thành công. Vui lòng đăng nhập lại")</script>
		</c:if>

 <c:set var="check" value="${sendEmail}"/>
		<c:if test="${check == 'ok'}">
			<script> showVerify()</script>
		</c:if>
		
</body>
</html>