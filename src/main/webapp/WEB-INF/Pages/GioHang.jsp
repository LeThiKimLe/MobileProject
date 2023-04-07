<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
			int sodu=0;
			List<String> listchoose=new ArrayList<String>();
			%>
			<script type="text/javascript" charset="UTF-8">
			
				function showBill()
		        {
		            bill.classList.add('open')
		        }
		        function hideBill()
		        {
		            bill.classList.remove('open')
		        }
	
				function setCookie(cname, cvalue, exdays) 
				{
					console.log("set cookies");
				  const d = new Date();
				  d.setTime(d.getTime() + (exdays*24*60*60*1000));
				  let expires = "expires="+ d.toUTCString();
				  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
				}
				
				function deleteCookie(cname) 
				{
				  document.cookie = cname + "=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;";
				}
			
				function updateHoaDon(element)
				{
					var codee = "."+ element.id + " .cost"
				   	var cost= parseInt(document.querySelector(codee).innerText)
				   	var nowcost = parseInt(totall.value)
				   	var sl = parseInt(soluong.value)
				  	if (element.checked == true)
				  	{
				   		nowcost = nowcost + cost
				   		sl=sl+1
				  	}
					else
					 { 
						nowcost = nowcost - cost
						sl=sl-1
					 }
					totall.value= nowcost
					soluong.value=sl
					console.log("set tại đây")
					setCookie("total_cost", nowcost, 30)
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
				
					function addItemtoBill(parent,count, itemName, itemCost)
					{
						var newitem = document.createElement('tr');
						parent.appendChild(newitem);
						newitem.innerHTML = `
								<td style="padding: 10px"><i>`+ count+ `</i></td>
		                        <td>` + itemName +`</td>
		                        <td>`+ itemCost + `VND</td>`;
					}
					
					function loadBill(parent)
					{
						var count=1
						var name, cost;
						var ele = document.querySelectorAll(".course")
						console.log(ele.length)
						for (var i=0; i<ele.length; i++)
			        	{
				            var item = ele[i];
				            if (item.checked==true)
				            {
								name=document.querySelector("."+item.id+ " .name")
								cost=document.querySelector("."+item.id+ " .cost")
				            	setCookie(item.id,count.toString(), 30)
				     			addItemtoBill(parent, count, name.innerHTML, cost.innerHTML)
				            	count=count+1
				            }
				            else
				            {
				            	deleteCookie(item.id)
				            }
			        	}
					}
					
					function ResetBill()
					{	
						billItem.replaceChildren();
						var table_item = document.createElement('div');
						billItem.appendChild(table_item);
						table_item.innerHTML = `
						<div style="font-size: 23px;margin-bottom: 20px;">Các khóa học sẽ thanh toán</div>
		                <table style="width: 100%" class="bill-item">
							<tr style="line-height: 60px;">
			                        <th class="rowheader" style="width: 10%"><i>STT</i></th>
			                        <th class="rowheader" style="width: 50%; font-weight: 500">Tên khóa học</th>
			                        <th class="rowheader" style="width: 40%">Giá tiền</th>
			                    </tr>

			                    
		                </table>`;
		                var table_contain=document.querySelector(".bill-item")
		                loadBill(table_contain)              
							
		               
		                var	tong_cong = document.createElement('tr');
		               	table_contain.appendChild(tong_cong);
		               	tong_cong.innerHTML=`  
		                        <td style="border-top: 1px solid #ccc; padding: 15px; text-align: left; font-weight: 600;" colspan="2">TỔNG THANH TOÁN </td>
		                        <td style="border-top: 1px solid #ccc;">` + getCookie("total_cost") +`VND</td>`
		                
		                var	vi_tien = document.createElement('tr');
		               	table_contain.appendChild(vi_tien);
		               	vi_tien.innerHTML=`     
		                        <td style="border-top: 1px solid #ccc; padding: 15px; text-align: left; font-weight: 600;" colspan="2">VÍ THANH TOÁN </td>
		                        <td style="border-top: 1px solid #ccc;">` + getCookie("soduvi") + `VND</td>`;
					}
					function checkThanhToan()
					{
						var sodutaikhoan=parseInt(getCookie("soduvi"));
						var username = getCookie("userID");
						  if (username != "")
						{
							  if (parseInt(soluong.value)!=0)
								  if (sodutaikhoan<totall.value)
									showNotice("Số dư ví không đủ. Vui lòng nạp thêm để tiếp tục thanh toán")
									else
									{
										ResetBill()
										showBill()
									}
							  else
								  showNotice("Vui lòng tick chọn khóa học muốn đăng ký")
						  }
						  else
						  {
						    showNotice("Bạn cần đăng nhập để thực hiện chức năng này nha")
						  }
					}
					deleteCookie("total_cost")
			</script>		
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>

<!-- Phần My Cart -->
			<%
			//allow access only if session exists
			if(session.getAttribute("username") != null)
			{
				Cookie[] cookies = request.getCookies();
				if(cookies !=null)
				{
					for(Cookie cookie : cookies)
						if(cookie.getName().equals("soduvi"))
						{
								sodu=Integer.parseInt(cookie.getValue());
						}
				}
			}
			else
				sodu = 0;
			%>
        <div class="container">
             <div class="row align-items-center">
	        	<div class="col-lg-3 pb-30">
	                    <div >
	                        <img class="img-fluid" src="${pageContext.request.contextPath}/resources/img/myicon.png" alt="">     
	                    </div>
	                    <div style="font-size: 15px;">
	                        <div style="min-width: 90px; display: inline-block;"><i class="fa fa-dollar" style="margin-right: 10px; margin-left: 20px;"></i><b>Ví của bạn</b> </div><input style="margin-left: 5px; border: none; max-width: 130px;" type="text" name="" id="" value=<%=sodu%> readonly>
	                    </div>
	           </div>
            
	             <div class="col-lg-9 pb-30">
	                    <div style="border: 2px solid #ccc; border-radius: 16px; background-color: #f2f59d; text-align: center">
	                    	<div style="font-size: 25px; color: black; margin: 30px 0"> <b>HÓA ĐƠN</b> </div>
	                    	<div style="min-width: 250px; display: inline-block;"><a style="margin-top:20px; margin-bottom: 10px; font-size: 20px;"><b>Số khóa học đã chọn</b> </a> </div> <input style="min-width: 200px; margin-left: 30px; border: none" type="text" name="" id="quatity" value="0" readonly>
                    		<p></p>
                    		
                    		<div style="min-width: 250px; display: inline-block;"><a style="margin-top:20px; margin-bottom: 10px; font-size: 20px;"><b>Tổng tiền</b> </a> </div><input style="min-width: 200px; margin-left: 30px; border: none" type="text" name="" id="total" value="0" readonly>
                    		<p></p>
                    		
                    		<div class="primary-btn text-uppercase" style="margin: 20px 0; min-width: 200px; text-align: center;" onclick=checkThanhToan()>Thanh toán</div>
	                    </div>
	             </div>
           	</div>
           
           	<c:choose>
            <c:when test="${sessionScope.cart!=null && sessionScope.cart.size()!=0}">
           	<p style="text-align: center; font-size: 30px; margin: 50px 0; color:rgb(229, 129, 16)">  <i class="fa fa-star"></i> GIỎ HÀNG CỦA BẠN <i class="fa fa-star"></i> </p>
            <c:forEach items="${sessionScope.cart}" var="item">
	            <div class="row align-items-center ${item.item.maKhoaHoc}" style="margin: 30px 0px; border-bottom: 1px solid #ccc; padding-bottom: 10px;">
	                <div class="single-popular-carusel col-lg-4" style="max-width: 100%;">   
	                    <div class="thumb-wrap relative ">
	                        <div class="thumb relative">
	                            <div class="overlay overlay-bg"></div>	
	                            <img class="img-fluid" src="${item.item.hinhAnhMoTa}" alt="">
	                        </div>									
	                    </div>
	                </div> 
	                <div class="details col-lg-8" style="max-width: 100%; margin-top: 10px;">
	                    <a href="#">
	                        <h4 class="name">
	                           	${item.item.tenKhoaHoc}
	                        </h4>
	                    </a>
	                    <p>
	                    ${item.item.moTa}
	                    </p>
	                    <br>
	                    <span>Giá: </span> <span class="cost"> ${item.item.giaTien} </span> <span>VND</span>
	                </div>
	                <div class="col-lg-8"></div>
	                <div class="col-lg-2">
	                    <a href="#">Chọn mua</a> 
	                    <input class="course" style="height: 100%px;" type="checkbox" name="" id="${item.item.maKhoaHoc}" onclick=updateHoaDon(this)>
	                </div> 
	                <div class="col-lg-2">
	                    <a href="${pageContext.request.contextPath}/cart?action=remove&maKhoaHoc=${item.item.maKhoaHoc}" onclick="return confirm('Bạn có chắc chắn xóa khóa học khỏi giỏ hàng?')">Xóa bỏ</a> 
	                </div>  
	            </div>
            </c:forEach>
             </c:when>
              <c:otherwise>
             	<p style="text-align: center; font-size: 30px; margin: 50px 0; color:rgb(229, 129, 16)"> Giỏ hàng hiện đang trống. Hãy thêm khóa học vào giỏ hàng nhé</p>
             </c:otherwise>
            </c:choose>
            </div>

        <!-- Hết phần my Cart -->        
        
<jsp:include page="_footer.jsp"></jsp:include>

	<!-- Phần Hóa đơn -->
        <div class="modal js-modal bill">
        <div class="modal-container js-modal-container">
            <div class="modal-close js-modal-close">
                <i class="ti-close"></i>
            </div>
            <header class="modal-header">
                <i class="modal-head-icon ti-bag"></i>
                HÓA ĐƠN
            </header>
            <div class="modal-body" style="margin: 20px"> 

                <div style="min-width: 200px; font-size: 15; font-weight: 600; display: inline-block;">Học viên:</div> <div style="margin-left: 30px; min-width: 200px; display: inline-block;"> ${sessionScope.userFullName} </div>
                <br>
                <div style="margin-top: 20px;min-width: 200px; font-size: 15; font-weight: 600; display: inline-block;">Số điện thoại học viên:</div> <div style="margin-left: 30px; min-width: 200px; display: inline-block;"> ${sessionScope.userSDT} </div>

            </div>
            <div class="modal-body table_item" style="text-align: center;">
            </div>
            <div class="modal-body">
	            <button class="primanry-btn" style="margin-top: 40px; background-color: #50a8a0; cursor:pointer;" >
	                   <a style="text-decoration: none; color:black" href="${pageContext.request.contextPath}/pay">Xác nhận thanh toán</a>
	               </button>
	              <button style="margin-top: 40px; margin-left: 20px; cursor:pointer;" class="bill-close" onclick="hideBill()">
	                  Hủy
	              </button>
             </div>
        </div>
    </div>
    <!-- Hết phần Hóa đơn -->	
    <script type="text/javascript">
    
	    const totall= document.getElementById('total')
		const soluong = document.getElementById('quatity')
		const bill= document.querySelector('.js-modal.bill')
		const billClose=document.querySelector('.bill-close')
	    const billItem= document.querySelector('.modal-body.table_item')
	    
    </script>	
    
    <c:set var="checkstate" value="${state}"/>
		<c:if test="${checkstate == 'success'}">
			<script> showNotice("Đã thanh toán thành công. Vào trang cá nhân để xem các khóa học đã đăng ký")</script>
		</c:if>	
</body>
</html>