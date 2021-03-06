<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<% ArrayList<HashMap<String,Object>> list =(ArrayList<HashMap<String,Object>>)request.getAttribute("list"); %>

<!DOCTYPE html>

<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<%-- <%@ page session="false" %> --%>

<html>
<head>
<meta charset="UTF-8">
  
<title>투자 페이지</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- <script src="<%=request.getContextPath()%>/js/common/scroll.js"></script> -->
	<script src="../js/common/scroll.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<style>

	
	<link rel="stylesheet" href="../css/common.css"/>
	<link rel="stylesheet" href="../css/unit.css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/2.0.1/TweenMax.min.js"></script>
	
	<script>
	
	(function() {
	var container;
	var current = 0;
	var max = 0;
	
	function init(){
		container = jQuery(".slide ul");
		max=container.children().length;
		events();
	}
	
	function events(){
		jQuery("button.prev").on("click",prev);
		jQuery("button.next").on("click",next);
	}
	
	
	function prev( e ){
		current--;
		if(current < 0 ) current = 0;
		animate();
	}
	function next( e ){
		current++;
		if(current > max-1) current = max-1;
		animate();
	}
	function animate(){
		var moveX = current * 600;
		TweenMax.to(container,0.8,{marginLeft:-moveX, ease:Expo.easeOut});
	}
	jQuery(document).ready(init);
})();
	</script>
	*{margin:0;padding:0;}
	body{
		background:#f5f5f5;
	}
	
	.slide{
		width:1200px;
		overflow:hidden;
		margin : 5px auto;
		position:relative;
		background:#f5f5f5;
	}

	.sort{
		margin:0 auto;
		width:100%;
		height:50px;
		background:#f5f5f5;
	}
	.sort div.box1 select{
		position:absolute;
		margin:0 auto;
		margin-top:10px;
		width:200px;
		left:76.5%;
		font-size:15pt;
	}
	.sort div.box2 select{
		position:absolute;
		margin:0 auto;
		width:200px;
		left:61.5%;
		font-size:15pt;
		margin-top:10px;
	}
	#header{
		height:120px;
		line-height:100px;
		font-size:30px;
		border-bottom:3px solid #000;
		text-align:center;
		background:white;
		}
	#footer{
		border-top:1px solid #666;
		height:70px;
		line-height:70px;
		text-align:center;
	}
	#productArea{
		width:1200px;
		height:3000px;
		margin:0 auto;
	}
	.input li{
		width:20%;
		height:300px;
		margin:10px;
		margin-right:15px;
		margin-left:15px;
		display: inline-block;
		text-align:center;
		border: 1px solid green;
		
	}
	.thumbnailArea {
		width:760px;
		height:550px;
		margin-left:auto;
		margin-right:auto;
	}
	.searchArea {
		width:420px;
		margin-left:auto;
		margin-right:auto;
	}
	.thumb-list {
		width:220px;
		boder:1px solid white;
		display:inline-block;
		margin:10px;
		align:center;
	}
	.thumb-list:hover {
		cursor:pointer;
		opacity:0.8;
	}
	#mainpic{
		width:100%;
		height:230px;
	}
	#lim{
		width:100%; 
		height:60%;
	}
	#intro{
		font-size : 10pt;
		font-weight : bold;
	}
	#maindi{
		width:100%;
		height:80px;
		background:#fff;
		text-align:center;
		font-weight: 400;
		font-size: 1.75em;
	}
	.impic{
		background:#fff;
	
	}
	#mainsp{
		position: absolute;
    	left: 520px;
    	top: 330px;
	}
	#sort{
		width:40px;
		background-color:#038dc7;
		color:#fff;
		text-align:center;
		margin-top:10px;
		margin-left : 250px;
	}
	#camount{
		margin-left : 120px;
	}
</style>
</head>
<body>
	
	
	<%@ include file="../common/headBar.jsp"%>
	
	<br><br><br>
	<div class="impic">
		<img id="mainpic" src="images/common/img_media_news1.jpg">
	</div>
		
	<div id="maindi">
		
		<span id="mainsp">성장 유망 기업의 투자자가 되어보세요.</span>
	</div>
<div class="productArea">
 	<br>
 	
 	
 	<ul class="input" id="p_list" align="center">
 	<% for(int i = 0; i <list.size(); i++){
 			HashMap<String,Object> hmap = list.get(i);
 		%>
					<li class="p_list2" id="p<%=i%>">
						<img src="/sp/thumbnail_uploadFiles/<%=hmap.get("changeName")%>" id = "lim">
						<hr>
						<input type="hidden" value="<%=hmap.get("pId")%>">
						<%-- <span id="intro"><%=hmap.get("intro")%></span><br> --%>
						
						<% String str = (String)hmap.get("intro"); %>
							
							<%-- <% if(str.length() > 20){%>
						<span id="intro">제목길어서 짤림</span><br>
						
						<% else { %>
						
						<% } %> --%>
						
						
						<% if(str.length() > 25) { %>
							<% String str2 = str.substring(0,24); %>
						
							<span id="intro"><%= str2 %> ... </span><br>
						<% }else{ %>
						<span id="intro"><%=hmap.get("intro")%></span><br>
						
						<% } %>
						<div id="sort">채권</div>
						
						<span id="camount">목표금액 \<%=hmap.get("closingAmount")%></span>
					</li>
					 
 		<% } %>	
 	</ul>
</div>
<br><br>
<% if(loginUser != null ) {  %>
	<input type="hidden" id="loginId" name="loginId" value="<%=loginUser.getUserId()%>"> 
<% }else{ %>
	<input type="hidden" id="loginId" name="loginId" value="-1"> 
<% } %>
<script>
	 $(function(){
		$(".p_list2").click(function(){
			/* var num = $(this).children().children().eq(1).val(); */
			
			var user = document.getElementById('loginId').value;
			
			if(user == -1){
				alert("로그인이 필요한 기능입니다.");
			}else{
				var num = $(this).children().eq(2).val();
				location.href="<%=request.getContextPath()%>/SelectNews.pm?num=" + num;	
			}
		});
	});
</script>
<%@ include file="/views/common/footer.jsp" %>
</body>

</html>
<!-- <div class="sort">
		<div class="box1">
			<select style=height:40px;>
				<option value="test1">선택1</option>
				<option value="test2">선택2</option>
				<option value="test3">선택3</option>
			</select>
		</div>
		<div class="box2">
			<select style=height:40px;>
				<option value="1">인기순</option>
				<option value="2">달성률</option>
				<option value="3">최신순</option>
			</select>
		</div>			
	</div> -->