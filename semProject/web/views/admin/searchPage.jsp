<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.kh.sp.member.model.vo.*, 
	com.kh.sp.admin.model.vo.*"%>
<%
	
ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("list");
PageInfo pi = (PageInfo)request.getAttribute("pi");
int listCount = pi.getListCount();
int currentPage = pi.getCurrentPage();
int maxPage = pi.getMaxPage();
int startPage = pi.getStartPage();
int endPage = pi.getEndPage();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Do+Hyeon"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Do+Hyeon|Nanum+Gothic+Coding"
	rel="stylesheet">
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/css/admin/admin.css">

<style>
html {
	    margin-top: 86px;
}
#sortBtn {
	width: 60px;
}

#search2 {
	float: right;
	margin-top: 9px;
	width: 250px;
}

.container, .row {
	width: 300px;
}
.row{
    margin-left: -75px;
	margin-regih:-15px;
}

.search {
	padding: 5px 0;
	width: 230px;
	height: 30px;
	position: relative;
	left: 10px;
	float: left;
	line-height: 22px;
}

.search input {
	position: absolute;
	width: 0px;
	float: Left;
	margin-left: 210px;
	-webkit-transition: all 0.7s ease-in-out;
	-moz-transition: all 0.7s ease-in-out;
	-o-transition: all 0.7s ease-in-out;
	transition: all 0.7s ease-in-out;
	height: 30px;
	line-height: 18px;
	padding: 0 2px 0 2px;
	border-radius: 1px;
}

.search:hover input, .search input:focus {
	width: 200px;
	margin-left: 0px;
}

.btn {
	height: 30px;
	position: absolute;
	right: 0;
	top: 5px;
	border-radius: 1px;
}

td {
	text-align: center;
}

td a {
	text-decoration: none;
	color: black;
}

td a:hover {
	cursor: pointer;
}

#web-font {
	font-family: 'Do Hyeon', sans-serif;
	font-size: 32px;
}

table {
	width: 100%;
	border: 1px solid #444444;
	border-collapse: collapse;
}

th, td {
	font-family: 'Nanum Gothic Coding', monospace;
	border: 1px solid #444444;
	color: black;
	background: white;
	text-align: center;
}
/* 페이징 처리 CSS부분*/
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
/* #box {
	position: absolute;
	display: inline;
	margin-left:auto;
	margin-right:auto;
	}
 */
/* #sortBox {
	position: absolute;

	display: inline;
	width: 30%;
	height: 30%;
}
 */
#text {
	margin-top: 3px;
	background-color: white;
	padding: 20px;
	width: 60%;
	height: 530px;
	float: left;
	border: 2px solid;
	border-color: #d6d6d6;
	background-size: 95%;
}

#searchBox {
	position: absolute;
	display: inline;
	width: 400px;
	height: 50px;
}

/*
 나중에 지워야 할 것
 */
html {
	background-color: white;
}

element {
	background: white;
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
	<%@ include file="../common/sideMenu2.jsp" %>
	<%-- <%@ include file="../views/sideMenu.jsp" %> --%>
	<div>
		<div id="text">
			<h2 id="web-font">
				<b>회원 관리 > 회원 조회</b>
			</h2>
			<br>
			<!-- 여기는 정렬 form  -->
			<form action="<%=request.getContextPath()%>/sortMember.adm"
				method="get">
				<div id="box">
					<div id="sortBox" border=1>
						<select name="sort">
							<option value="user_id">아이디순</option>
							<option value="user_class">회원구분</option>
							<option value="user_name">이름</option>
							<option value="nickname">별명</option>
							<option value="email">이메일</option>
							<option value="phone">연락처</option>
						</select>
						<button id="sortBtn" width="300px" type="submit">정렬하기</button>
					</div>
				</div>
			</form>
			<!-- 여기는 검색 form   -->
			<form action="<%=request.getContextPath() %>/searchMember.adm"
				method="get">

				<div id="searchBox">
					<div class="container">
						<div class="row">
							<div name="searchCondition" id="search" value="search"
								class="search">
								<!-- 여기가 입력창 -->
								<input type="text" class="form-control input-sm" maxlength="64"
									placeholder="Search" id="search" name="searchValue"
									value="search" />
								<button type="submit" class="btn btn-primary btn-sm">검색하기</button>
							</div>
						</div>
					</div>
				</div>

			</form>

			<br> <br> <br>
			<table align="center" border="1">
				<thead>
					<tr>
						<th>아이디</th>
						<th>회원구분</th>
						<th>이름</th>
						<th>별명</th>
						<th>이메일</th>
						<th>연락처</th>
					</tr>

				</thead>
				<% for(Member m : list){ %>
				<tr>
					<td><%= m.getUserId() %></td>
					<td><%= m.getUserClass() %></td>
					<td><%= m.getUserName() %></td>
					<td><%= m.getNickName() %></td>
					<td><%= m.getEmail() %></td>
					<td><%= m.getPhone() %></td>
				</tr>
				<% } %>
			</table>

	  <%-- 페이지처리 --%>

        <div class="pageArea" id="datePaging" align="center">
			<a onclick="location.href='<%=request.getContextPath() %>/searchMember.adm?searchValue=<%=request.getParameter("searchValue")%>&currentPage=1'" class="link_fst">
			<span class="fa fa-angle-double-left" aria-hidden="true"><<</span></a>&#160;
			<% if (currentPage <= 1) { %>
				<a disabled class="link_prev"><</a>&#160;
			<% } else { %>
				<a onclick="location.href='<%=request.getContextPath() %>/searchMember.adm?searchValue=<%=request.getParameter("searchValue")%>&currentPage=<%=currentPage -1 %>'" class="link_prev"><</a>&#160;
			<% } %>
			
			<% for(int p = startPage;p<= endPage;p++) { 
					if(p==currentPage) { %>
						<a disabled class="link_page" style="background:lightgray;"><%= p %></a>
			<% 		} else { %>
						<a onclick="location.href='<%=request.getContextPath()%>/searchMember.adm?searchValue=<%=request.getParameter("searchValue")%>&currentPage=<%=p %>'" class="link_page"><%= p %></a>
			<%  	} %>
			<% } %>
			
			<% if(currentPage >= maxPage) { %>
				&#160;<a disabled class="link_next">></a>&#160;
			<% } else { %>
				&#160;<a onclick="location.href='<%=request.getContextPath()%>/searchMember.adm?searchValue=<%=request.getParameter("searchValue")%>&currentPage=<%=currentPage + 1%>'" class="link_next">></a>&#160;
			<% } %>
			<a onclick="location.href='<%=request.getContextPath()%>/searchMember.adm?searchValue=<%=request.getParameter("searchValue")%>&currentPage=<%=maxPage%>'" class="link_lst">>></a>
		</div>
        

</body>
</html>