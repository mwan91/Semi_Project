<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.kh.sp.admin.model.vo.*"%>
<!DOCTYPE html>
<%
ArrayList<SuccessFundingStatistics> list =
    (ArrayList<SuccessFundingStatistics>)request.getAttribute("list");
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
    color:black;
    background-color:white;
}
select{
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
				<li role="presentation" style="font-size: 14px;"><a href="<%=request.getContextPath()%>/fundingSt.adm">&nbsp;&nbsp;&nbsp;&nbsp;펀딩 개설 통계&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
				<li role="presentation" class="active" style="font-size: 14px;"><a href="#">&nbsp;&nbsp;&nbsp;&nbsp;펀딩 성공 통계&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
                <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="chart" class="form-control btn btn-primary" style="width:100px;">차트 보기</button></li>
   </ul>
   <br>
   <div style="height:390px">
   <table class="table table-hover">
   <thead>
   <tr>
   <th>구분</th>
   <th>마감 건수</th>
   <th>성공 건수</th>
   <th>성공 비율</th>
   <th colspan="3">마감방식별 성공 비율</th>
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
   <th>80%이상 성공</th>
   <th>100%달성 마감</th>
   <th>100%초과 마감</th>
   </tr>
   </thead>
   <tbody>
   <% for(SuccessFundingStatistics f : list){ %>
				<tr>
					<td><%= f.getTerm() %></td>
					<td><%= f.getEndCount() %>건</td>
					<td><%= f.getSuccessCount() %>건</td>
					<td><%= f.getSuccessRate() %>%</td>
					<td><%= f.getType1SuccessRate() %>%</td>
					<td><%= f.getType2SuccessRate() %>%</td>
					<td><%= f.getType3SuccessRate() %>%</td>
				</tr>
				<% } %>
   </tbody>
  </table>
  </div>
  <%-- 페이지처리 --%>
        
		<div class="pageArea" id="monthPaging" align="center">
			<a onclick="location.href='<%=request.getContextPath() %>/fundingSt2.adm?currentPage=1'" class="link_fst">
			<span class="fa fa-angle-double-left" aria-hidden="true"><<</span></a>&#160;
			<% if (currentPage <= 1) { %>
				<a disabled class="link_prev"><</a>&#160;
			<% } else { %>
				<a onclick="location.href='<%=request.getContextPath() %>/fundingSt2.adm?currentPage=<%=currentPage -1 %>'" class="link_prev"><</a>&#160;
			<% } %>
			
			<% for(int p = startPage;p<= endPage;p++) { 
					if(p==currentPage) { %>
						<a disabled class="link_page" style="background:lightgray;"><%= p %></a>
			<% 		} else { %>
						<a onclick="location.href='<%=request.getContextPath()%>/fundingSt2.adm?currentPage=<%=p %>'" class="link_page"><%= p %></a>
			<%  	} %>
			<% } %>
			
			<% if(currentPage >= maxPage) { %>
				&#160;<a disabled class="link_next">></a>&#160;
			<% } else { %>
				&#160;<a onclick="location.href='<%=request.getContextPath()%>/fundingSt2.adm?currentPage=<%=currentPage + 1%>'" class="link_next">></a>&#160;
			<% } %>
			<a onclick="location.href='<%=request.getContextPath()%>/fundingSt2.adm?currentPage=<%=maxPage%>'" class="link_lst">>></a>
		</div>
		
		<div class="pageArea" id="yearPaging" align="center">
			<a onclick="location.href='<%=request.getContextPath() %>/fundingSt2.adm?term=year&currentPage=1'" class="link_fst">
			<span class="fa fa-angle-double-left" aria-hidden="true"><<</span></a>&#160;
			<% if (currentPage <= 1) { %>
				<a disabled class="link_prev"><</a>&#160;
			<% } else { %>
				<a onclick="location.href='<%=request.getContextPath() %>/fundingSt2.adm?term=year&currentPage=<%=currentPage -1 %>'" class="link_prev"><</a>&#160;
			<% } %>
			
			<% for(int p = startPage;p<= endPage;p++) { 
					if(p==currentPage) { %>
						<a disabled class="link_page" style="background:lightgray;"><%= p %></a>
			<% 		} else { %>
						<a onclick="location.href='<%=request.getContextPath()%>/fundingSt2.adm?term=year&currentPage=<%=p %>'" class="link_page" ><%= p %></a>
			<%  	} %>
			<% } %>
			
			<% if(currentPage >= maxPage) { %>
				&#160;<a disabled class="link_next">></a>&#160;
			<% } else { %>
				&#160;<a onclick="location.href='<%=request.getContextPath()%>/fundingSt2.adm?term=year&currentPage=<%=currentPage + 1%>'" class="link_next">></a>&#160;
			<% } %>
			<a onclick="location.href='<%=request.getContextPath()%>/fundingSt2.adm?term=year&currentPage=<%=maxPage%>'" class="link_lst">>></a>
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
	    	location.href = "<%= request.getContextPath() %>/fundingSt2.adm?term=year&currentPage=1";
	    }else if($("#month").is(':selected')){
	    	location.href = "<%= request.getContextPath() %>/fundingSt2.adm?currentPage=1";
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
               ['Term', '마감 건수', '성공 건수', '성공 비율'],
               <%for(int i = 0; i < list.size(); i++){
                   if(i == 0){%>
                  ['<%= list.get(i).getTerm() %>',<%= list.get(i).getEndCount() %>,
                  <%= list.get(i).getSuccessCount() %>, <%= list.get(i).getSuccessRate() %>]
                  <%}else{%>
                  ,['<%= list.get(i).getTerm() %>',<%= list.get(i).getEndCount() %>,
                      <%= list.get(i).getSuccessCount() %>,<%= list.get(i).getSuccessRate() %>]
                  <%}}%>   
            ]);
              
            // Set chart options
            var options = {
               title : '펀딩 성공 통계',
               vAxis: {title: 'Funding'},
               hAxis: {title: 'Term'},
               seriesType: 'bars',
               series: {2: {type: 'line'}}
            };

            // Instantiate and draw the chart.
            var chart = new google.visualization.ComboChart(document.getElementById('container'));
            chart.draw(data, options);
         }
         google.charts.setOnLoadCallback(drawChart);
    }
      </script>
<%--  <div><%@ include file="../common/footer.jsp" %></div> --%>

</body>
</html>
