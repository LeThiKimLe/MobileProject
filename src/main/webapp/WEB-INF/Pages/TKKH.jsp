<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
    

        <!-- Phần thống kê khóa học -->

        <BR>
        </BR>
                <center><h3>THỐNG KÊ KHÓA HỌC</h3>
                   
            <br>
            <h4><label for="exampleFormControlSelect1">Chọn Năm</label></h4>
            <select class="form-control" id="exampleFormControlSelect1" style="width:100px;">
              <option>2018</option>
              <option>2019</option>
              <option>2020</option>
              <option>2021</option>
              <option>2022</option>
            </select>
            <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
          
            <body>
            <div id="myChart" style="width:100%; max-width:1300px; height:500px;"></div>
            
            <script>
            google.charts.load('current',{packages:['corechart']});
            google.charts.setOnLoadCallback(drawChart);
            
            function drawChart() {
            // Set Data
            var data = google.visualization.arrayToDataTable([
              ['Price', 'Size'],
              [1,7],[2,8],[3,8],[4,9],[5,9],
              [6,9],[7,10],[8,11],
              [9,14],[10,14],[11,15],[12,7]
            ]);
            // Set Options
            var options = {
              title: 'Học Viên Mua Khóa Học Trong Năm',
              hAxis: {title: 'Tháng'},
              vAxis: {title: ' Số Học Viên'},
              legend: 'none'
            };
            
            // Draw
            var chart = new google.visualization.LineChart(document.getElementById('myChart'));
            chart.draw(data, options);
            }
            
            </script>  
          <img src="bieudo1.png" alt="" style="margin-left: 70px;">
          <img src="bieudo2.png" alt="" style="margin-left: 120px;">
            </body>
            
        </center>  
        
        <!-- Hết phần thống kê khóa học -->

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>