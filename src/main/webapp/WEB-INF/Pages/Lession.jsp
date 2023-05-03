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
			var x = document.forms["EditForm"]["title"].value;
			var y = document.forms["EditForm"]["content"].value;
			if (x=="")
				{
					showNotice("Vui lòng nhập tên bài học");
					return false;
				}
			if (y=="")
				{
					showNotice("Vui lòng nhập nội dung bài học");
					return false;
				}
			return true;	
		}
		
		function setContent(parent, content)
		{
			parent.replaceChildren();
			var editform = document.createElement('p');
			parent.appendChild(editform);
			editform.innerHTML = content;
		}
		
		function viewdetail(element)
		{
			var state= element.textContent;
			var content= element.nextElementSibling.nextElementSibling.textContent
			if (state=="View Details")
			{
				element.innerHTML="Hide Details"
				var next= element.parentNode.parentNode.nextElementSibling;
				setContent(next, content)
				next.classList.remove('hide')
			}
			else
			{
				element.innerHTML="View Details"
				var next= element.parentNode.parentNode.nextElementSibling;
				next.classList.add('hide')
			}
		} 
		
		function closeBox(element)
		{
			var parent= element.parentNode.parentNode.parentNode.parentNode;
			parent.classList.add('hide')
		}
		
		
		function editBox(parent, title, descript, id)
		{
			parent.replaceChildren();
			var editform = document.createElement('div');
			parent.appendChild(editform);
			editform.innerHTML = `
				<form method="POST" action="${pageContext.request.contextPath}/lession?action=edit" name="EditForm" onsubmit="return validateForm()" style="border: 1px solid #ccc;">
				<textarea style="display: none" name="id">`+ id +`</textarea>
				<div style="display: inline-block; margin-left: 135px; margin-top: 20px;">
				<a style="margin-top:20px; margin-bottom: 10px; font-size: 20px;"><b>Tên bài học</b></a> </div>
				<textarea style="min-width: 440px; margin-left: 50px; margin-bottom: -33px" type="text" name="title" id="">`+ title + ` </textarea>
		        <p></p>
		        <div style="display: inline-block; margin-left: 135px">
		        <a style="margin-top:20px; margin-bottom: 10px; font-size: 20px;"> <b>Mô tả </b></a> </div>
		        <p></p>
		        <textarea style="min-width: 600px; min-height: 100px;margin-left: 135px" type="text" name="content" id="">`+ descript + ` </textarea>
		        
		        <div style="max-width: 100%; text-align: right;">
		            <input class="primary-btn text-uppercase" style="margin: 20px 0; min-width: 50px; text-align: center; background-color:#20a781 " type="submit" value="Lưu">
		            <span class="primary-btn text-uppercase" style="margin-left: 10px; min-width: 50px; text-align: center; background-color: #ccc; margin-right: 20px;" onclick=closeBox(this)>Hủy </span>
		        </div>
			</form>`;
		}
		
		function showeditBox (element)
		{
			var title=element.previousElementSibling.previousElementSibling.textContent;
			var des= element.previousElementSibling.textContent;
			var id=element.nextElementSibling.textContent;
			var next= element.parentNode.parentNode.nextElementSibling;
			editBox(next, title , des, id);
			next.classList.remove('hide');
		}

</script>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<section class="course-details-area pt-120">
	<div class="container">
		<div class="row">
			<div class="col-lg-4 left-contents">
				<div class="main-image">
					<img class="img-fluid" src="${sessionScope.current_course.hinhAnhMoTa}" alt="">
				</div>
			</div>
			
			<div class="col-lg-8 right-contents">
				<ul>
					<li>
						<a class="justify-content-between d-flex" href="#" style="font-size: 22px; margin: 20px 0;font-weight: 450">
							<p>${sessionScope.current_course.tenKhoaHoc}</p>
						</a>
					</li>
				
					<li>
						<a class="justify-content-between d-flex" href="#">
							<p>Học Phí</p>
							<span>${sessionScope.current_course.giaTien} VND</span>
						</a>
					</li>
					<li>
						<a class="justify-content-between d-flex" href="#">
							<p>Số lượng bài học</p>
							<span>${sessionScope.current_course.soBaiHoc}</span>
						</a>
					</li>
					
				</ul>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12" style="margin:30px 0; font-size: 40px; text-align: center;">DANH SÁCH CÁC BÀI HỌC</div>
			<div class="col-lg-12">
				<ul class="course-list">
					<c:forEach items="${sessionScope.list_lession}" var="baihoc">
						<li class="justify-content-between d-flex" style="margin-bottom: 10px;">
							<p><b>${baihoc.tenBaiHoc}</b></p>
							<span>
								<span class="primary-btn viewcontent" onclick=viewdetail(this)>View Details</span>
								<span style="display:none;">${baihoc.tenBaiHoc}</span>
								<span style="display:none;">${baihoc.moTaNoiDung}</span>
								<c:if test="${sessionScope.role=='GV'}">
									<span class="primary-btn" onclick=showeditBox(this) style="background-color:#20a781; margin-left: 7px"> Sửa bài học </span>
								</c:if>
								<span style="display:none;">${baihoc.maBaiHoc}</span> 
							</span>
						</li>
						<div class="lession-content hide" style="left:0; margin-bottom: 10px;">
						<p>${baihoc.moTaNoiDung}</p>
						</div>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</section>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>