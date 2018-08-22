<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset= "UTF-8">
 <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<style>
html {
	    margin-top: 165px;
}
.modal {
        text-align: center;
}
 
@media screen and (min-width: 768px) { 
        .modal:before {
                display: inline-block;
                vertical-align: middle;
                content: " ";
                height: 100%;
        }
}
 
.modal-dialog {
        display: inline-block;
        text-align: left;
        vertical-align: middle;
}


th{
background-color:#66CDAA;

}
thead {
background-color:#66CDAA;
	}

  table {
    width: 100%;
    border: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #444444;
    text-align:center;
    
  }
  
  table tr td a {
  text-decoration : none;
	color: black;
  }
#text {
	background-color: white;
	padding: 20px;
	width: 60%;
	height: 530px;
	float: left;
	border: 2px solid;
	border-color: darkgray;
}
</style>
</head>
<body>
	<%@ include file="../common/headBar.jsp" %>
	
	<%@ include file="../common/sideMenu2.jsp" %>
<div id="text">
				<h2>회원관리 > 투자자 등급 심사</h2><br>
				<table align="center">
				<thead>
					<tr>
						<th width="80%" colspan="5">투자자List</th><th width="22%"rowspan="2">승인여부</th>
					</tr>
					<tr>
						<th>No</th><th>투자자ID</th><th>현재 등급</th><th>변경 요청한 등급</th><th>증빙서류</th>
					</tr>
				</thead>
				<tr>
						<td>1</td>
						<td>wjdxo918</td>
						<td>일반투자자</td>
						<td>적격투자자</td>
						<td><button>파일 열람</button></td>
						<td class="text-center"><a class='btn btn-info btn-xs' data-target="#myModal" data-toggle="modal" href="#"><span class="glyphicon glyphicon-edit"></span>승인</a> <a href="#" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span>미승인</a></td>
			
				</tr>
				</table>
	
	</div>


<div class="container">

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modal Header</h4>
        </div>
        <div class="modal-body">
          <p>This is a small modal.</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>