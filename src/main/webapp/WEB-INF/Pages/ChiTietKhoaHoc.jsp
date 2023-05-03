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

</script>

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
	                                <div class="jq-tab-title" data-tab="4">Bình luận</div>
	                                <div class="jq-tab-title" data-tab="5">Nhận xét</div>
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
	                                <div class="jq-tab-content comment-wrap" data-tab="4">
						                <div class="comments-area">
						                    <h4>05 Comments</h4>
						                    <div class="comment-list">
						                        <div class="single-comment justify-content-between d-flex">
						                            <div class="user justify-content-between d-flex">
						                                <div class="thumb">
						                                    <img src="${pageContext.request.contextPath}/resources/img/blog/c1.jpg" alt="">
						                                </div>
						                                <div class="desc">
						                                    <h5><a href="#">Emilly Blunt</a></h5>
						                                    <p class="date">December 4, 2017 at 3:12 pm </p>
						                                    <p class="comment">
						                                        Never say goodbye till the end comes!
						                                    </p>
						                                </div>
						                            </div>
						                            <div class="reply-btn">
						                                   <a href="" class="btn-reply text-uppercase">reply</a> 
						                            </div>
						                        </div>
						                    </div>  
						                    <div class="comment-list left-padding">
						                        <div class="single-comment justify-content-between d-flex">
						                            <div class="user justify-content-between d-flex">
						                                <div class="thumb">
						                                    <img src="${pageContext.request.contextPath}/resources/img/blog/c2.jpg" alt="">
						                                </div>
						                                <div class="desc">
						                                    <h5><a href="#">Elsie Cunningham</a></h5>
						                                    <p class="date">December 4, 2017 at 3:12 pm </p>
						                                    <p class="comment">
						                                        Never say goodbye till the end comes!
						                                    </p>
						                                </div>
						                            </div>
						                            <div class="reply-btn">
						                                   <a href="" class="btn-reply text-uppercase">reply</a> 
						                            </div>
						                        </div>
						                    </div>   
						                    <div class="comment-list">
						                        <div class="single-comment justify-content-between d-flex">
						                            <div class="user justify-content-between d-flex">
						                                <div class="thumb">
						                                    <img src="${pageContext.request.contextPath}/resources/img/blog/c4.jpg" alt="">
						                                </div>
						                                <div class="desc">
						                                    <h5><a href="#">Maria Luna</a></h5>
						                                    <p class="date">December 4, 2017 at 3:12 pm </p>
						                                    <p class="comment">
						                                        Never say goodbye till the end comes!
						                                    </p>
						                                </div>
						                            </div>
						                            <div class="reply-btn">
						                                   <a href="" class="btn-reply text-uppercase">reply</a> 
						                            </div>
						                        </div>
						                    </div>                                                    
						                </div>
						                <div class="comment-form">
						                    <h4>Leave a Comment</h4>
						                    <form>
						                        <div class="form-group form-inline">
						                          <div class="form-group col-lg-6 col-md-12 name">
						                            <input type="text" class="form-control" id="name" placeholder="Enter Name" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter Name'">
						                          </div>
						                          <div class="form-group col-lg-6 col-md-12 email">
						                            <input type="email" class="form-control" id="email" placeholder="Enter email address" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter email address'">
						                          </div>                                        
						                        </div>
						                        <div class="form-group">
						                            <input type="text" class="form-control" id="subject" placeholder="Subject" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Subject'">
						                        </div>
						                        <div class="form-group">
						                            <textarea class="form-control mb-10" rows="5" name="message" placeholder="Messege" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Messege'" required=""></textarea>
						                        </div>
						                        <a href="#" class="mt-40 text-uppercase genric-btn primary text-center">Post Comment</a> 
						                    </form>
						                </div>     						                
	                                </div>
	                                <div class="jq-tab-content" data-tab="5">	
	                                	<div class="review-top row pt-40">
	                                		<div class="col-lg-3">
	                                			<div class="avg-review">
	                                				Average <br>
	                                				<span>5.0</span> <br>
	                                				(3 Ratings)
	                                			</div>
	                                		</div>
	                                		<div class="col-lg-9">
	                                			<h4 class="mb-20">Provide Your Rating</h4>
	                                			<div class="d-flex flex-row reviews">
	                                				<span>Quality</span>
													<div class="star">
														<i class="fa fa-star checked"></i>
														<i class="fa fa-star checked"></i>
														<i class="fa fa-star checked"></i>
														<i class="fa fa-star"></i>
														<i class="fa fa-star"></i>
													</div>
	                                				<span>Outstanding</span>
	                                			</div>
	                                			<div class="d-flex flex-row reviews">
	                                				<span>Puncuality</span>
													<div class="star">
														<i class="fa fa-star checked"></i>
														<i class="fa fa-star checked"></i>
														<i class="fa fa-star checked"></i>
														<i class="fa fa-star"></i>
														<i class="fa fa-star"></i>
													</div>
	                                				<span>Outstanding</span>
	                                			</div>
	                                			<div class="d-flex flex-row reviews">
	                                				<span>Quality</span>
													<div class="star">
														<i class="fa fa-star checked"></i>
														<i class="fa fa-star checked"></i>
														<i class="fa fa-star checked"></i>
														<i class="fa fa-star"></i>
														<i class="fa fa-star"></i>
													</div>
	                                				<span>Outstanding</span>
	                                			</div>
	                                		</div>
	                                	</div>
	                                	<div class="feedeback">
	                                		<h4 class="pb-20">Your Feedback</h4>
	                                		<textarea name="feedback" class="form-control" cols="10" rows="10"></textarea>
	                                		<a href="#" class="mt-20 primary-btn text-right text-uppercase">Submit</a>
	                                	</div>
						                <div class="comments-area mb-30">
						                    <div class="comment-list">
						                        <div class="single-comment single-reviews justify-content-between d-flex">
						                            <div class="user justify-content-between d-flex">
						                                <div class="thumb">
						                                    <img src="${pageContext.request.contextPath}/resources/img/blog/c1.jpg" alt="">
						                                </div>
						                                <div class="desc">
						                                    <h5><a href="#">Emilly Blunt</a>
															<div class="star">
																<span class="fa fa-star checked"></span>
																<span class="fa fa-star checked"></span>
																<span class="fa fa-star checked"></span>
																<span class="fa fa-star"></span>
																<span class="fa fa-star"></span>
															</div>
						                                    </h5>
						                                    <p class="comment">
						                                    	Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cum ut sed, dolorum asperiores perspiciatis provident, odit maxime doloremque aliquam.
						                                    </p>
						                                </div>
						                            </div>
						                        </div>
						                    </div>  
						                    <div class="comment-list">
						                        <div class="single-comment single-reviews justify-content-between d-flex">
						                            <div class="user justify-content-between d-flex">
						                                <div class="thumb">
						                                    <img src="${pageContext.request.contextPath}/resources/img/blog/c2.jpg" alt="">
						                                </div>
						                                <div class="desc">
						                                    <h5><a href="#">Elsie Cunningham</a>
															<div class="star">
																<span class="fa fa-star checked"></span>
																<span class="fa fa-star checked"></span>
																<span class="fa fa-star checked"></span>
																<span class="fa fa-star"></span>
																<span class="fa fa-star"></span>
															</div>
						                                    </h5>
						                                    <p class="comment">
						                                    	Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cum ut sed, dolorum asperiores perspiciatis provident, odit maxime doloremque aliquam.
						                                    </p>
						                                </div>
						                            </div>
						                        </div>
						                    </div>   
						                    <div class="comment-list">
						                        <div class="single-comment single-reviews justify-content-between d-flex">
						                            <div class="user justify-content-between d-flex">
						                                <div class="thumb">
						                                    <img src="${pageContext.request.contextPath}/resources/img/blog/c3.jpg" alt="">
						                                </div>
						                                <div class="desc">
						                                    <h5><a href="#">Maria Luna</a>
															<div class="star">
																<span class="fa fa-star checked"></span>
																<span class="fa fa-star checked"></span>
																<span class="fa fa-star checked"></span>
																<span class="fa fa-star"></span>
																<span class="fa fa-star"></span>
															</div>
						                                    </h5>
						                                    <p class="comment">
						                                    	Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cum ut sed, dolorum asperiores perspiciatis provident, odit maxime doloremque aliquam.
						                                    </p>
						                                </div>
						                            </div>
						                        </div>
						                    </div>  
						                    <div class="comment-list">
						                        <div class="single-comment single-reviews justify-content-between d-flex">
						                            <div class="user justify-content-between d-flex">
						                                <div class="thumb">
						                                    <img src="${pageContext.request.contextPath}/resources/img/blog/c4.jpg" alt="">
						                                </div>
						                                <div class="desc">
						                                    <h5><a href="#">Maria Luna</a>
															<div class="star">
																<span class="fa fa-star checked"></span>
																<span class="fa fa-star checked"></span>
																<span class="fa fa-star checked"></span>
																<span class="fa fa-star"></span>
																<span class="fa fa-star"></span>
															</div>
						                                    </h5>
						                                    <p class="comment">
						                                    	Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cum ut sed, dolorum asperiores perspiciatis provident, odit maxime doloremque aliquam.
						                                    </p>
						                                </div>
						                            </div>
						                        </div>
						                    </div>  						                                                                      
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
							<a href="${pageContext.request.contextPath}/lession" class="primary-btn text-uppercase">VÀO HỌC</a>
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

</body>
</html>