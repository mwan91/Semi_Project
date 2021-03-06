<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.kh.sp.admin.model.vo.*"%>
<!DOCTYPE html>
<%
ArrayList<SalesStatistics> list =
    (ArrayList<SalesStatistics>)request.getAttribute("list");
String num = (String)request.getAttribute("num");
PageInfo pi = (PageInfo)request.getAttribute("pi");
	  int listCount = pi.getListCount();
	  int currentPage = pi.getCurrentPage();
	  int maxPage = pi.getMaxPage();
	  int startPage = pi.getStartPage();
	  int endPage = pi.getEndPage();
%>
<html>
<head>
<script type = "text/javascript" src = "https://www.gstatic.com/charts/loader.js">
      </script>
      <script type = "text/javascript">
         google.charts.load('current', {packages: ['corechart','line']});  
      </script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta charset= "UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/css/admin/admin.css">

<style>
html {
	    margin-top: 86px;
}
body {
    background-color:#F7F7F7;
}

 .table {
    width: 90%;
    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
background-color:#428BCA;
    color:white;
    margin-left:auto;
    margin-right:auto;
  } 
th, td {
font-size:14px;
   text-align: center;
}
td{
    background-color:white;
    color:black;
}

select{
font-size:14px;
    color:black;
}
 
#text {
	background-color: white;
	padding: 20px;
	width: 70%;
	height: 530px;
	float: left;
	border: 1px solid #d6d6d6;
	margin-left:2%;
	margin-top:-30px;
	margin-bottom:50px;
}
.pageArea a {
display: inline-block;
    width: 32px;
    height: 32px;
    margin: 0 2px;
    border: 1px solid #d6d6d6;
    font-size: .75em;
    line-height: 32px;
    color: #999; 
    text-align: center;
    vertical-align: top;
    cursor:pointer;
}
/* .pageArea {
    margin: 40px 0;
    text-align: center;
    margin-top: 200px;
} */
.pageArea a:hover{
          color: #999; 
       }

</style>
</head>
<body>
		<%@ include file="../common/headBar.jsp" %>
	<header class="head_banner" style="margin-bottom: 65px;">
						<div class="hero"><img src="<%=request.getContextPath()%>/images/common/admin.jpg" style="width:100%;"alt="공지사항 배경 이미지 입니다." class="img_rwd"></div>
						<div class="layer">
							<!-- <h1 class="tit_comm">Manager</h1>
							<p class="txt_comm">관리자 페이지</p> -->
						</div>
					</header>
	<%@ include file="../common/sideMenu.jsp" %>
	

<div id="text">
<div id="totalCon">
<div id="container2" class="con1">
   <ul class="nav nav-tabs">
				<li role="presentation" style="font-size: 14px;"><a href="<%= request.getContextPath() %>/salesSt.adm">&nbsp;&nbsp;전체 매출 통계&nbsp;&nbsp;</a></li>
				<li role="presentation" style="font-size: 14px;"><a href="<%= request.getContextPath() %>/salesSt.adm?type=t1">&nbsp;&nbsp;80%이상 성공 상품&nbsp;&nbsp;</a></li>
				<li role="presentation" class="active" style="font-size: 14px;"><a href="#">&nbsp;&nbsp;100%달성 마감 상품&nbsp;&nbsp;</a></li>
				<li role="presentation" style="font-size: 14px;"><a href="<%= request.getContextPath() %>/salesSt.adm?type=t3">&nbsp;&nbsp;100%초과 마감 상품&nbsp;&nbsp;</a></li>
                <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="chart" class="form-control btn btn-primary" style="width:100px;">차트 보기</button></li>
   </ul>
   <br>
   <div style="height:390px">
   <table class="table table-hover">
   <thead>
   <tr>
   <th>구분</th>
   <th>결제 건수</th>
   <th>결제 금액</th>
   <th>환불 건수</th>
   <th>실결제비율</th>
   <th>결제완료금액</th>
   <th>순매출액</th>
   </tr>
   <tr>
   <th>
   <select>
   <option id="month">월별</option>
   <option id="year">년도별</option>
   </select>
   </th>
   <th></th>
   <th></th>
   <th></th>
   <th></th>
   <th></th>
   <th>금액 (단위:만원)</th>
   </tr>
   </thead>
   <tbody>
         <% for(SalesStatistics s : list){ %>
				<tr>
					<td><%= s.getTerm() %></td>
					<td><%= s.getPaymentCount() %>건</td>
					<td><%= s.getPaymentPrice() %></td>
					<td><%= s.getRefundCount() %>건</td>
					<td><%= s.getPaymentPercentage() %>%</td>
					<td><%= s.getPaymentCompletePrice() %></td>
					<td><%= s.getNetSales() %></td>
				</tr>
				<% } %> 
   </tbody>
  </table>
  </div>
  <%-- 페이지처리 --%>
        
		<div class="pageArea" id="monthPaging" align="center">
			<a onclick="location.href='<%=request.getContextPath() %>/salesSt.adm?type=t2&currentPage=1'" class="link_fst">
			<span class="fa fa-angle-double-left" aria-hidden="true"><<</span></a>&#160;
			<% if (currentPage <= 1) { %>
				<a disabled class="link_prev"><</a>&#160;
			<% } else { %>
				<a onclick="location.href='<%=request.getContextPath() %>/salesSt.adm?type=t2&currentPage=<%=currentPage -1 %>'" class="link_prev"><</a>&#160;
			<% } %>
			
			<% for(int p = startPage;p<= endPage;p++) { 
					if(p==currentPage) { %>
						<a disabled class="link_page" style="background:lightgray;"><%= p %></a>
			<% 		} else { %>
						<a onclick="location.href='<%=request.getContextPath()%>/salesSt.adm?type=t2&currentPage=<%=p %>'" class="link_page"><%= p %></a>
			<%  	} %>
			<% } %>
			
			<% if(currentPage >= maxPage) { %>
				&#160;<a disabled class="link_next">></a>&#160;
			<% } else { %>
				&#160;<a onclick="location.href='<%=request.getContextPath()%>/salesSt.adm?type=t2&currentPage=<%=currentPage + 1%>'" class="link_next">></a>&#160;
			<% } %>
			<a onclick="location.href='<%=request.getContextPath()%>/salesSt.adm?type=t2&currentPage=<%=maxPage%>'" class="link_lst">>></a>
		</div>
		
		<div class="pageArea" id="yearPaging" align="center">
			<a onclick="location.href='<%=request.getContextPath() %>/salesSt.adm?term=year&type=t2&currentPage=1'" class="link_fst">
			<span class="fa fa-angle-double-left" aria-hidden="true"><<</span></a>&#160;
			<% if (currentPage <= 1) { %>
				<a disabled class="link_prev"><</a>&#160;
			<% } else { %>
				<a onclick="location.href='<%=request.getContextPath() %>/salesSt.adm?term=year&type=t2&currentPage=<%=currentPage -1 %>'" class="link_prev"><</a>&#160;
			<% } %>
			
			<% for(int p = startPage;p<= endPage;p++) { 
					if(p==currentPage) { %>
						<a disabled class="link_page" style="background:lightgray;"><%= p %></a>
			<% 		} else { %>
						<a onclick="location.href='<%=request.getContextPath()%>/salesSt.adm?term=year&type=t2&currentPage=<%=p %>'" class="link_page"><%= p %></a>
			<%  	} %>
			<% } %>
			
			<% if(currentPage >= maxPage) { %>
				&#160;<a disabled class="link_next">></a>&#160;
			<% } else { %>
				&#160;<a onclick="location.href='<%=request.getContextPath()%>/salesSt.adm?term=year&type=t2&currentPage=<%=currentPage + 1%>'" class="link_next">></a>&#160;
			<% } %>
			<a onclick="location.href='<%=request.getContextPath()%>/salesSt.adm?term=year&type=t2&currentPage=<%=maxPage%>'" class="link_lst">>></a>
		</div>
</div>
</div>
<div id="chartCon" style="display:none;">
<div align="right"><button id="list" class="form-control btn btn-primary" style="width:100px">표로 보기</button>&nbsp;&nbsp;&nbsp;</div>
<div id="container" class="con2" style="height:470px;">
</div>
</div>
</div>
<script>
$(function(){
	$("#yearPaging").hide();
	$("#monthPaging").show();
	

	if(<%= num %>=="0"){
		$("#month").attr("selected", "selected");
		$("#yearPaging").hide();
		$("#monthPaging").show();
	}else{
		$("#year").attr("selected", "selected");
		$("#yearPaging").show();
		$("#monthPaging").hide();
	}
	
	$('select').change(function(){
	    if($("#year").is(':selected')) {
	    	location.href = "<%= request.getContextPath() %>/salesSt.adm?term=year&type=t2&currentPage=1";
	    }else if($("#month").is(':selected')){
	    	location.href = "<%= request.getContextPath() %>/salesSt.adm?type=t2&currentPage=1";
	    }
	});
	
	$("#chart").click(function(){
		makeChart();
		$("#totalCon").hide();
		$("#chartCon").show();
	});
	$("#list").click(function(){
		$("#chartCon").hide();
		$("#totalCon").show();
	});
});
</script>
<script language = "JavaScript">
    function makeChart(){
         function drawChart() {
            // Define the chart to be drawn.
            
            var data = google.visualization.arrayToDataTable([
               ['Term', '결제금액', '결제완료금액', '순매출액'],
               <%for(int i = 0; i < list.size(); i++){
            	   String str1 = %><% list.get(i).getPaymentPrice()%><%;%>
            	   <%String str2 = %><% list.get(i).getPaymentCompletePrice()%><%;%>
            	   <%String str3 = %><% list.get(i).getNetSales()%><%;%>
            	   <%
            	     str1 = str1.trim();
            	     str2 = str2.trim();
            	     str3 = str3.trim();
            	     
            	     int count = 0;
            	     int price1 = 0;
            	     for(int j = 0; j < str1.length(); j++){
            	    	 if(str1.charAt(j) == ','){
            	    		 count++;
            	    	 }
            	     }
            	     if(count==0){
            	    	 price1 = Integer.parseInt(str1);
            	     }else{
            	    	 String num1 = str1.split(",")[0];
                	     String num2 = str1.split(",")[1];
                	     String plus = num1+num2;
                	     price1 = Integer.parseInt(plus);
            	     }
            	     
            	     count = 0;
            	     int price2 = 0;
            	     for(int j = 0; j < str2.length(); j++){
            	    	 if(str2.charAt(j) == ','){
            	    		 count++;
            	    	 }
            	     }
            	     if(count==0){
            	    	 price2 = Integer.parseInt(str2);
            	     }else{
            	    	 String num3 = str2.split(",")[0];
                	     String num4 = str2.split(",")[1];
                	     String plus2 = num3+num4;
                	     price2 = Integer.parseInt(plus2);
            	     }
            	     
            	     count = 0;
            	     int price3 = 0;
            	     for(int j = 0; j < str3.length(); j++){
            	    	 if(str3.charAt(j) == ','){
            	    		 count++;
            	    	 }
            	     }
            	     if(count==0){
            	    	 price3 = Integer.parseInt(str3);
            	     }else{
            	    	 String num5 = str3.split(",")[0];
                	     String num6 = str3.split(",")[1];
                	     String plus3 = num5+num6;
                	     price3 = Integer.parseInt(plus3);
            	     }%>
                   <%if(i == 0){%>
                  ['<%= list.get(i).getTerm() %>',<%=price1%>,<%=price2%>,<%=price3%>]
                  <%}else{%>
                  ,['<%= list.get(i).getTerm() %>',<%=price1%>,<%=price2%>,<%=price3%>]
                  <%}}%>  
            ]);

            var options = {title: '매출 통계(단위:만원)',
               hAxis: {title: 'Term', titleTextStyle: {color: '#333'}},
               vAxis: {minValue: 0}
            };  

            // Instantiate and draw the chart.
            var chart = new google.visualization.AreaChart(document.getElementById ('container'));
            chart.draw(data, options);
         }
         google.charts.setOnLoadCallback(drawChart);
    }
      </script>
<%--  <div><%@ include file="../common/footer.jsp" %></div> --%>

</body>
</html>