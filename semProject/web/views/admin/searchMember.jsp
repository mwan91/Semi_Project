<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kh.sp.member.model.vo.*"%>
<%
	Member m = (Member) request.getAttribute("m");
%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/admin/admin.css">

<style>
html {
	margin-top: 86px;
}

.adminBtn {
	background: #5bc0de;
	heignt: 50px;
	color: white;
	font-size: 8px;
	padding: 10px 16px;
	font-size: 18px;
	line-height: 1.3333333;
	border-radius: 6px;
	border: 1px solid transparent;
	white-space: nowrap;
}

table {
	width: 100%;
	border: 1px solid #444444;
	border-collapse: collapse;
}

th, td {
	border: 1px solid white;
	text-align: center;
}

#box {
	display: inline;
}

#sortBox {
	display: inline;
	width: 50%;
	height: 30%;
}

#text {
	margin-top: 13px;
	background-color: white;
	padding: 20px;
	width: 60%;
	height: 530px;
	float: left;
	border: 2px solid;
	border-color: #d6d6d6;
}

#searchBox {
	display: inline;
	width: 50%;
	height: 30%;
}

.btn-box {
	width: 350px;
	margin-left: auto;
	margin-right: auto;
}

#blackText {
	width: 600px;
	height: 200px;
}
</style>
</head>
<body>
	<%@ include file="../common/headBar.jsp"%>
	<header class="head_banner" style="margin-bottom: 65px;">
		<div class="hero">
			<img src="<%=request.getContextPath()%>/images/common/admin.jpg"
				style="width: 100%;" alt="공지사항 배경 이미지 입니다." class="img_rwd">
		</div>
		<div class="layer">
			<!-- <h1 class="tit_comm">Manager</h1>
							<p class="txt_comm">관리자 페이지</p> -->
		</div>
	</header>
	<%@ include file="../common/sideMenu2.jsp"%>
	<div id="text">
		<h3>회원관리 > 회원조회 > 상세조회</h3>
		<br>
		<form action="" method="get">

			<table align="center" border="1">
				<thead>
					<tr>
						<th>아이디</th>
						<th>회원구분</th>
						<th>이름</th>
						<th>별명</th>
					</tr>
				</thead>
				<tr>

					<td><span><%=m.getUserId()%></span></td>
					<td><span><%=m.getUserClass()%></span></td>
					<td><span><%=m.getUserName()%></span></td>
					<td><span><%=m.getNickName()%></span></td>
				</tr>
			</table>
			<br> <br> <br> <br> <br>
			<table align="center" border="1">
				<thead>
					<tr>
						<th width="220px">이메일</th>

						<th rowspan="2">연락처</th>
						<th rowspan="2">가입일</th>
					</tr>
				</thead>

				<tr>

					<td><span><%=m.getEmail()%></span></td>
					<td><span><%=m.getPhone()%></span></td>
					<td><span><%=m.getEnrollDate()%></span></td>
				</tr>
			</table>
		</form>
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br>
		<div class="btn-box">
			<button class="adminBtn"
				onclick="location.href='admin/managerPage.jsp'">돌아가기</button>
			&nbsp;
			<button type="button" id="blackBtn" class="adminBtn"
				data-toggle="modal" data-target="#myModal">블랙리스트 추가하기</button>

		</div>
	</div>
<body>

	<div class="container">
		<!-- Trigger the modal with a button -->

		<!-- Modal -->
		<form id="insertBlackForm"
			action="<%=request.getContextPath()%>/insertBlackList.adm"
			method="get">
			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">블랙리스트로 추가 하시겠습니까?</h4>
						</div>
						<div class="modal-body">
							<p>블랙리스트 사유를 입력해 주세요.</p>
							<input type="text" name="text" id="blackText">
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
						onclick="insertBlackList();"  id="plusBtn"
								data-dismiss="modal">추가하기</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">취소하기</button>
							<input type="hidden"  id="userId" name="user_id"
								value="<%=m.getUserId()%>">
						</div>

					</div>
				</div>
			</div>
		</form>
	</div>


	<script>
	function insertBlackList() {
			var user_id =<%=m.getUserId()%>;
			
			console.log(user_id);
			$("#userId").val(user_id);

			
			$("#insertBlackForm").submit();
 		};

		/* function insertBlackList(){
		$("#userId2").val($("#userId").val());
		$("#insertBlackForm").submit();
		} */
	</script>
</body>
</html>
