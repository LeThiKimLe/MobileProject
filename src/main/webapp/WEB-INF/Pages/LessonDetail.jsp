<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/lessonDetail.css">
<script type="text/javascript">
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
	
	function CloseComment(element)
	{
		 var parent = element.parentNode.parentNode.parentNode.parentNode;
		 console.log(parent);
		 parent.remove();
	}

	function repComment(parent)
	{
		
		parent= parent.parentNode.parentNode.parentNode;
		console.log(parent);
		var repContain= parent.querySelector("ul");
		if (repContain==null)
		{
			repContain= document.createElement("ul");
			parent.appendChild(repContain);
			parent.classList.add('parent_Comment');
			repContain.classList.add('child_Comment');
			var style;
		    style = getComputedStyle(parent).marginLeft;
		    var len= style.length;
		    var marginNum= (parseInt(style.slice(0,len-2))+20) + "px"
		    repContain.style.marginLeft = marginNum;
		}
		var newitem = document.createElement('li');
		repContain.appendChild(newitem);
		newitem.innerHTML = `
							<div class="comment-list">
							<div class="ma-rep" style="display:none">`+parent.id+`</div>
				            <div class="single-comment justify-content-between d-flex">
				                <div class="user justify-content-between d-flex" style="flex:1">
				                    <div class="thumb">
				                        <img src="${pageContext.request.contextPath}/resources/img/blog/c4.jpg" alt="">
				                    </div>
				                    <div class="desc comment_Box">
				                        <h5 style="margin-bottom: 5px;">`+getCookie("userID") +`</h5>
				                        <p class="date"></p>
				                        <input type="text" class="form-control rep" placeholder="Subject" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Subject'">
				                    </div>
				                </div>
				                <div class="reply-btn" style="margin: 23px 10px; text-align: center;">
				                       <a href="#" class="btn-reply text-uppercase" style="border: 1px solid #ccc; border-radius:10px;" onclick="postRep(this)">Gửi</a> 
				                       <div onclick="CloseComment(this)" style="margin-top:10px;; cursor:pointer" class="closeRep">Hủy</div>
				                </div>
				            </div>
				        </div> `;
	}
	
	function showrepComment(maRep, nguoiGui, noiDung, thoiGian, maBinhLuan)
	{
		var parent= document.getElementById(maRep)

		var repContain= parent.querySelector("ul");
		if (repContain==null)
		{
			repContain= document.createElement("ul");
			parent.appendChild(repContain);
			parent.classList.add('parent_Comment');
			repContain.classList.add('child_Comment');
			var style;
		    style = getComputedStyle(parent).marginLeft;
		    var len= style.length;
		    var marginNum= (parseInt(style.slice(0,len-2))+20) + "px"
		    repContain.style.marginLeft = marginNum;
		}
		var newitem = document.createElement('li');
		repContain.appendChild(newitem);
		newitem.innerHTML = `
							<div class="comment-list" id=`+maBinhLuan+`>
				            <div class="single-comment justify-content-between d-flex">
				                <div class="user justify-content-between d-flex">
				                    <div class="thumb">
				                        <img src="${pageContext.request.contextPath}/resources/img/blog/c4.jpg" alt="">
				                    </div>
				                    <div class="desc comment_Box">
				                        <h5><a href="#">`+ nguoiGui +`</a></h5>
				                        <p class="date"> `+ thoiGian +` </p>
				                        <p class="comment">`+ noiDung +` </p>  
				                    </div>
				                </div>
				                <div class="reply-btn">
				                       <a href="#" class="btn-reply text-uppercase" style="border: 1px solid #ccc; border-radius:10px; cursor:pointer" onclick="repComment(this)">Phản hồi</a> 
				                </div>
				            </div>
				        </div> `;
	}
	
	function postRep(element)
	{
		
		var parent= element.parentNode.parentNode.parentNode;
		
		var today = new Date();
		var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
		var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
		var dateTime = date+' '+time;
		
		var getMaRep= (parent.querySelector(".ma-rep")).innerHTML;
		var getComment= parent.querySelector(".rep").value;
		var maBaiHoc = document.getElementById("maBaiHoc").innerHTML;
		var nguoiGui= getCookie('userID')
		
		if (getComment=="")
		{
			showNotice("Vui lòng nhập nội dung bình luận")
			return false;
		}
		
		var today = new Date();
		var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
		var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
		var dateTime = date+' '+time;
		
		window.location.href="lessonDetail?action=repComment&maBaiHoc="+maBaiHoc+"&nguoiGui="+nguoiGui+"&noiDung="+getComment+"&thoiGian="+dateTime+"&maRep="+getMaRep;		
		
	}
	
	function addComment()
	{
		var bigContainer= document.querySelector(".comments-area")
		var getSubject= document.getElementById("subject").value;
		var getComment= document.getElementById("comment-message").value;
		var maBaiHoc = document.getElementById("maBaiHoc").innerHTML;
		var nguoiGui= getCookie('userID')
		if (getSubject=="")
		{
			showNotice("Vui lòng nhập tiêu đề")
			return false;
		}
		if (getComment=="")
		{
			showNotice("Vui lòng nhập nội dung bình luận")
			return false;
		}
		
		var today = new Date();
		var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
		var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
		var dateTime = date+' '+time;
		
		window.location.href="lessonDetail?action=addComment&maBaiHoc="+maBaiHoc+"&nguoiGui="+nguoiGui+"&tieuDe="+getSubject+"&noiDung="+getComment+"&thoiGian="+dateTime;		
		
	}
	function activeBinhLuan()
	{
		var tab1= document.querySelector(".tab1")
		var tab3= document.querySelector(".tab3")
		tab1.classList.remove('active')
		tab3.classList.add('active')
		
	}
	
	function activeBaiTap()
	{
		var tab1= document.querySelector(".tab1")
		var tab2= document.querySelector(".tab2")
		tab1.classList.remove('active')
		tab2.classList.add('active')
		
	}
	
	function showBaiGiangBox()
	{
		var contain= document.querySelector(".editBaiGiang-area")
		contain.classList.remove('hide');
		
	}
	function closeBaiGiangBox()
	{
		var contain= document.querySelector(".editBaiGiang-area")
		contain.classList.add('hide');
	}
	
	
	function showLyThuyetBox()
	{
		var contain= document.querySelector(".editLyThuyet-area")
		var text= document.querySelector(".lyThuyetText")
		text.classList.add('hide');
		contain.classList.remove('hide');
	}
	
	function closeLyThuyetBox()
	{
		var contain= document.querySelector(".editLyThuyet-area")
		var text= document.querySelector(".lyThuyetText")
		text.classList.remove('hide');
		contain.classList.add('hide');
	}
	
	function showBaiTapBox()
	{
		var contain= document.querySelector(".editBaiTap-area")
		var text= document.querySelector(".baiTapText")
		text.classList.add('hide');
		contain.classList.remove('hide');
	}
	
	function closeBaiTapBox()
	{
		var contain= document.querySelector(".editBaiTap-area")
		var text= document.querySelector(".baiTapText")
		text.classList.remove('hide');
		contain.classList.add('hide');
	}
	

</script>


</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<a href="${pageContext.request.contextPath}/lession" class="primary-btn" style="background-color:#59a470; border-radius:15px; margin: 10px"><i class="fa fa-arrow-left" style="margin-right:5px;"></i>Quay lại</a>
<div style="text-align: center; font-size: 30px; font-weight: 600; margin: 40px 0;">${sessionScope.cur_lesson.tenBaiHoc}</div>
<div style="display:none" id="maBaiHoc">${sessionScope.cur_lesson.maBaiHoc}</div>
<section class="course-details-area pt-10">
				<div class="container">
					<div class="row">
						<div class="col-lg-12 left-contents">
							<div class="jq-tab-wrapper" id="horizontalTab">
	                            <div class="jq-tab-menu" style="text-align: center; margin: auto;">
	                                <div class="jq-tab-title active" style="font-size: 20px !important; font-weight:500 !important" data-tab="1">Lý thuyết</div>
	                                <div class="jq-tab-title" style="font-size: 20px !important; font-weight:500 !important" data-tab="2">Bài tập</div>
	                                <div class="jq-tab-title" style="font-size: 20px !important; font-weight:500 !important" data-tab="3">Thảo luận</div>
	                            </div>
	                            <div class="jq-tab-content-wrapper">
	                                <div class="jq-tab-content active tab1" data-tab="1">
		                                <div class="title">1. Video bài giảng</div>
		                                <c:if test="${sessionScope.role=='GV'}">
		                                <div>
		                                	<button onclick="showBaiGiangBox()" class="detail-btn">Sửa bài giảng</button>
		                                	<form class="editBaiGiang-area hide" method="POST" action="${pageContext.request.contextPath}/lessonDetail?action=editBaiGiang&maBaiHoc=${sessionScope.cur_lesson.maBaiHoc}" name="LoginForm">
		                                	 <textarea rows="3" style="width:100%; margin-top: 10px; border-radius:15px;" name="link" placeholder="Copy link video bài giảng vào đây" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Copy link video bài giảng vào đây'" required=""></textarea>
		                                	 <div style="text-align:right;">
			                                	  <input class="primary-btn" style="background-color:#1aa52f; border-radius:15px;" type="submit" value="Load" />
			                                	  <span class="primary-btn" onclick="closeBaiGiangBox()" style="margin-left: 5px; border-radius:15px"> Hủy </span>
		                                	 </div>
		                                	</form>
		                                </div>	
		                                </c:if>
		                                <div style="text-align:center">
											<iframe width="630" height="473"
											src="${sessionScope.cur_lesson.taiNguyen.baiGiang}">
											</iframe>							
										</div>
		                                <div class="title">2. Tóm tắt lý thuyết</div>
		                                <c:if test="${sessionScope.role=='GV'}">
		                                <div>
		                                	<button onclick="showLyThuyetBox()" class="detail-btn">Sửa lý thuyết</button>
		                                	<form class="editLyThuyet-area hide" method="POST" action="${pageContext.request.contextPath}/lessonDetail?action=editLyThuyet&maBaiHoc=${sessionScope.cur_lesson.maBaiHoc}" name="LoginForm">
		                                	 <textarea rows="10" style="width:100%; margin-top: 10px; border-radius:15px;" name="lyThuyet" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Ghi nội dung lý thuyết'" required="">${sessionScope.cur_lesson.taiNguyen.lythuyet}</textarea>
		                                	 <div style="text-align:right;">
			                                	  <input class="primary-btn" style="background-color:#1aa52f; border-radius:15px;" type="submit" value="Load" />
			                                	  <span class="primary-btn" onclick="closeLyThuyetBox()" style="margin-left: 5px; border-radius:15px"> Hủy </span>
		                                	 </div>
		                                	</form>
		                                </div>	
		                                </c:if>
	                                   	<div class="lyThuyetText" style="margin-top: 15px">
	                                   	${sessionScope.cur_lesson.taiNguyen.lythuyet}
	                                   	</div>
	                                </div>
	                                <div class="jq-tab-content tab2" data-tab="2">
	                                	<c:if test="${sessionScope.role=='GV'}">
			                                <div>
			                                	<button onclick="showBaiTapBox()" class="detail-btn">Sửa bài tập</button>
			                                	<form class="editBaiTap-area hide" method="POST" action="${pageContext.request.contextPath}/lessonDetail?action=editBaiTap&maBaiHoc=${sessionScope.cur_lesson.maBaiHoc}" name="LoginForm">
			                                	 <textarea rows="10" style="width:100%; margin-top: 10px; border-radius:15px;" name="baiTap" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Ghi nội dung bài tập'" required="">${sessionScope.cur_lesson.taiNguyen.baiTap}</textarea>
			                                	 <div style="text-align:right;">
				                                	  <input class="primary-btn" style="background-color:#1aa52f; border-radius:15px;" type="submit" value="Load" />
				                                	  <span class="primary-btn" onclick="closeBaiTapBox()" style="margin-left: 5px; border-radius:15px"> Hủy </span>
			                                	 </div>
			                                	</form>
			                                </div>	
		                                </c:if>
	                                    <br>
										<br>
										<div class="baiTapText" style="margin-top: 15px">${sessionScope.cur_lesson.taiNguyen.baiTap}</div>
	                                 </div>
	                                
	                                <div class="jq-tab-content comment-wrap tab3" data-tab="3">
						                <div class="comments-area">
						                <c:forEach items="${sessionScope.cur_lesson.listBinhLuans}" var="binhluan">
						                    <div class="comment-list" id="${binhluan.maBinhLuan}">
						                        <div class="single-comment justify-content-between d-flex">
						                            <div class="user justify-content-between d-flex">
						                                <div class="thumb">
						                                    <img src="${pageContext.request.contextPath}/resources/img/blog/c1.jpg" alt="">
						                                </div>
						                                <div class="desc comment_Box">
						                                    <h5>${binhluan.nguoiGuiString}</h5>
						                                    <p class="date"> ${binhluan.ngayDangTimestamp} </p>
						                                    <p class="comment"> ${binhluan.tieuDeString}</p>
						                                    <p class="comment">
						                                        ${binhluan.noiDungString}
						                                    </p>
						                                </div>
						                            </div>
						                            <div class="reply-btn">
						                                   <div class="btn-reply text-uppercase" style="border: 1px solid #ccc; border-radius:10px; cursor:pointer" onclick="repComment(this)">Phản hồi</div> 
						                            </div>
						                        </div>
						                        <c:if test="${binhluan.repList!=null && binhluan.repList.size()!=0}">
						                        <c:set var ="node" value="${binhluan}" scope="request"/>
						                        	<jsp:include page="BinhLuan.jsp"/>
						                    	</c:if>
						                    </div>
						                  </c:forEach>                                              
						                </div>
						                <div class="comment-form">
						                    <h4>Để lại bình luận của bạn tại đây</h4>
						                    <form>
						                        <div class="form-group">
						                            <input type="text" class="form-control" id="subject" placeholder="Subject" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Subject'">
						                        </div>
						                        <div class="form-group">
						                            <textarea class="form-control mb-10" id="comment-message" rows="5" name="message" placeholder="Messege" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Messege'" required=""></textarea>
						                        </div>
						                        <a href="#" class="mt-40 text-uppercase genric-btn primary text-center" onclick="addComment()">Đăng</a> 
						                    </form>
						                </div>     						                
	                                </div>           
	                            </div>
	                        </div>
						</div>
					</div>
				</div>	
			</section>

<jsp:include page="_footer.jsp"></jsp:include>
<c:if test="${addComment=='ok'}">
	<script>activeBinhLuan()</script>
</c:if>

<c:if test="${editBaiTap=='ok'}">
	<script>activeBaiTap()</script>
</c:if>
</body>
</html>