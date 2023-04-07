<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="bean.*" %>
<%@ page import="utils.DBUtils" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	
	function viewdetail(element)
	{
		var state= element.textContent;
		if (state=="View Details")
		{
			element.innerHTML="Hide Details"
			var next= element.parentNode.nextElementSibling;
			next.classList.remove('hide')
		}
		else
		{
			element.innerHTML="View Details"
			var next= element.parentNode.nextElementSibling;
			next.classList.add('hide')
		}
	} 
	
	function getCookie(cname)
	{
		  let name = cname + "=";
		  let decodedCookie = decodeURIComponent(document.cookie);
		  let ca = decodedCookie.split(';');
		  for(let i = 0; i < ca.length; i++)
		  {
		    let c = ca[i];
		    while (c.charAt(0) == ' ')
		    {
		      c = c.substring(1);
		    }
		    if (c.indexOf(name) == 0)
		    {
		      return c.substring(name.length, c.length);
		    }
		  }
		  return "";
	}
	
	function validateForm()
	{
		var userID = getCookie("userID");
		if (userID == "")
		{
			showNotice("Bạn cần đăng nhập để thực hiện chức năng này nha")
			return false;
		}
		var roleConfirm = document.getElementById("roleConf");
		if (roleConfirm.innerHTML!='VÀO HỌC')
		{
			showNotice("Chỉ có học viên đang học khóa này mới có thể bình luận")
			return false;
		}	
		
		var x = document.forms["FeedbackForm"]["rate"].value;
		var y = document.forms["FeedbackForm"]["feedback"].value;
		if (x=="")
			{
				showNotice("Vui lòng đánh giá sao");
				return false;
			}
		if (y=="")
			{
				showNotice("Vui lòng nhập nội dung feedback");
				return false;
			}
		return true;	
	}
	
	function show_choose(element)
	{
		var num = parseInt(element.id);
		var prev_element;
		var kq= document.getElementById("chosen");
		var numrate = document.getElementById("rateid");
		numrate.value=num;
		var rate;
		for (let i = 1; i <= num; i++)
		{
			 prev_element=document.getElementById(i.toString());
			 prev_element.classList.add("checked");
		}
		rate = window.getComputedStyle(element, '::after').content;
		kq.innerHTML=rate;
		for (let i = 5; i > num; i--)
		{
			 prev_element=document.getElementById(i.toString());
			 prev_element.classList.remove("checked");
		}
	}
	
	


</script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/star.css">

</head>

<body>

<jsp:include page="_header.jsp"></jsp:include>

<!-- Start course-details Area -->
			<section class="course-details-area pt-120">
				<div class="container">
					<div class="row">
						<div class="col-lg-8 left-contents">
							<div class="main-image">
								<img class="img-fluid" src="${khoahoc.hinhAnhMoTa}" alt="">
							</div>
							<div class="jq-tab-wrapper" id="horizontalTab">
	                            <div class="jq-tab-menu">
	                                <div class="jq-tab-title active" data-tab="1">Giới thiệu</div>
	                                <div class="jq-tab-title" data-tab="2">Thông tin Giáo viên</div>
	                                <div class="jq-tab-title" data-tab="3">Nội dung bài học</div>
	                                <div class="jq-tab-title" data-tab="4">Nhận xét</div>
	                            </div>
	                            <div class="jq-tab-content-wrapper">
	                                <div class="jq-tab-content active" data-tab="1">
	                                    ${khoahoc.tenKhoaHoc}
										<br>
										<br>
										${khoahoc.moTa}
	                                </div>
	                                <div class="jq-tab-content" data-tab="2">
	                                	Giáo viên phụ trách: ${giaovien.tenGiaoVien}
	                                    <br>
										<br>
										Số điện thoại liên hệ: ${giaovien.sdt}
	                                 </div>
	                                <div class="jq-tab-content" data-tab="3">
										<ul class="course-list">
											<c:forEach items="${list_baihoc}" var="baihoc">
												<li class="justify-content-between d-flex" style="margin-bottom: 10px">
													<p><b>${baihoc.tenBaiHoc}</b></p>
													<span class="primary-btn viewcontent" href="#" onclick=viewdetail(this)>View Details</span>
												</li>
												<div class="lession-content hide" style="left:0">${baihoc.moTaNoiDung}</div>
											</c:forEach>
										</ul>
	                                </div>
	                                <div class="jq-tab-content" data-tab="4">	
	                                	<div class="review-top row pt-40">
	                                		<div class="col-lg-12">
	                                			<div class="avg-review">
	                                				Trung bình <br>
	                                				<span>${avgStar}</span> <i class="fa fa-star checked" style="color: yellow"></i> <br>
	                                			</div>
	                                		</div>
	                                	</div>
	                                	<form method="POST" action="${pageContext.request.contextPath}/feedback" name="FeedbackForm" onsubmit="return validateForm()">
		                                	<div class="review-top row pt-40">
		                                		<div class="col-lg-9">
		                                			<h4 class="mb-20">Hãy gửi đánh giá của bạn</h4>
		                                			<div class="d-flex flex-row reviews" style="font-size: 20px;">
														<div class="star" style="width: 100%">
															<i class="fa fa-star star_show star_show_1" onclick="show_choose(this)" id="1"></i>
															<i class="fa fa-star star_show star_show_2" onclick="show_choose(this)" id="2"></i>
															<i class="fa fa-star star_show star_show_3" onclick="show_choose(this)" id="3"></i>
															<i class="fa fa-star star_show star_show_4" onclick="show_choose(this)" id="4"></i>
															<i class="fa fa-star star_show star_show_5" onclick="show_choose(this)" id="5"></i>
														</div>
														<input style="display: none" name="rate" id="rateid"></input>
														<span id="chosen" style="width: 100%; z-index:2"></span>
		                                			</div>
		                                		</div>
			                                	<div class="feedeback col-lg-12">
			                                		<h4 class="pb-20" style="margin-top: 20px;">Feedback của bạn</h4>
			                                		<textarea name="feedback" class="form-control" cols="10" rows="10"></textarea>
			                                		<input class="mt-20 primary-btn text-right text-uppercase" type="submit" value="Gửi đánh giá"></input>
			                                	</div>
		                                	</div>
		                                </form>
		                                
						                <div class="comments-area mb-30">
						                	<div style="margin: 20px 0; font-size: 25px; font-weight: 600;">CÁC ĐÁNH GIÁ TRƯỚC ĐÓ</div>
						                	<c:forEach items="${listPhanHoi}" var="phanhoi">
							                    <div class="comment-list">
							                        <div class="single-comment single-reviews justify-content-between d-flex">
							                            <div class="user justify-content-between d-flex">
							                                <div class="thumb">
							                                    <img src="${pageContext.request.contextPath}/resources/img/blog/c1.jpg" alt="">
							                                </div>
							                                <div class="desc">
							                                    <h5><a href="#">${phanhoi.tenHocVienString}</a>
																<div class="star">
																	<c:forEach var="i" begin="1" end="${phanhoi.rate}">
																		<span class="fa fa-star checked"></span>
																	</c:forEach>
																	<c:forEach var="i" begin="${phanhoi.rate}" end="4">
																		<span class="fa fa-star"></span>
																	</c:forEach>
																</div>
							                                    </h5>
							                                    <p class="comment">
							                                    	${phanhoi.feedbackString}
							                                    </p>
							                                </div>
							                            </div>
							                        </div>
							                    </div>
						                    </c:forEach>	                                                                      
						                </div>	                                	
	                                </div>                                
	                            </div>
	                        </div>
						</div>
						<div class="col-lg-4 right-contents">
							<ul>
								<li>
									<a class="justify-content-between d-flex" href="#">
										<p>Giáo viên hướng dẫn</p> 
										<span class="or">${giaovien.tenGiaoVien}</span>
									</a>
								</li>
								<li>
									<a class="justify-content-between d-flex" href="#">
										<p>Học Phí</p>
										<span>${khoahoc.giaTien} VND</span>
									</a>
								</li>
								<li>
									<a class="justify-content-between d-flex" href="#">
										<p>Số lượng bài học</p>
										<span>${khoahoc.soBaiHoc}</span>
									</a>
								</li>
							</ul>
						<c:choose>
						<c:when test="${sessionScope.role=='HV' && DBUtils.isExisting(khoahoc.maKhoaHoc, sessionScope.registed_course)!=-1}">
							<a href="${pageContext.request.contextPath}/lession" class="primary-btn text-uppercase" id="roleConf">VÀO HỌC</a>
						</c:when>
						<c:when test="${sessionScope.role=='GV' && DBUtils.isExisting(khoahoc.maKhoaHoc, sessionScope.teaching_course)!=-1}">
							<a href="${pageContext.request.contextPath}/lession" class="primary-btn text-uppercase">QUẢN LÝ BÀI HỌC</a>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath}/cart?&action=buy&maKhoaHoc=${khoahoc.maKhoaHoc}" class="primary-btn text-uppercase">Thêm vào giỏ hàng</a>
						</c:otherwise>
						</c:choose>
						</div>
					</div>
				</div>	
			</section>
			<!-- End course-details Area -->

	<jsp:include page="_footer.jsp"></jsp:include>
   	<c:set var="checkstate" value="${state}"/>

	<c:if test="${checkstate == 'success'}">
	     <script type="text/javascript">
				showNotice("Đã thêm khóa học vào giỏ hàng");
		</script>
	</c:if>
	
	<c:if test="${checkstate == 'fail'}">
	     <script type="text/javascript">
				showNotice("Khóa học đã có trong giỏ hàng");
		</script>
	</c:if>
	
	<c:if test="${sent == 'ok'}">
		<script> showNotice("Cảm ơn bạn đã feedback")</script>
	</c:if>	

</body>
</html>