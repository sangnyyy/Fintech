<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<link rel="icon" href="resources/logo.ico">

<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<style>
.header-signup {
	background-color: #32BEBE;
	height: 150px;
	margin-top: 50px;
}

<!--
상단 영역 -->.navbar-collapse.collapse {
	width: 1200px;
}

.navbar-brand {
	font-size: 25px;
	color: blue;
}

.form-group {
	font-size: 20px;
	font-weight: normal;
}
</style>

<title>회원가입</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/smuasset/main">SMU ASSET</a>
			</div>

			<c:choose>
				<c:when test="${sessionScope.name ne null }">
				<div id="navbar" class="navbar-collapse collapse">
					<form class="navbar-form navbar-right" style="height:30px;">
						<span class="navbar-brand" style="padding-top:7px;">${sessionScope.name }님 환영합니다.</span>
							<button type="submit" class="btn btn-success">로그아웃</button>
					</form>					
				</div>
				</c:when>
				<c:otherwise>
					<div id="navbar" class="navbar-collapse collapse">
						<form class="navbar-form navbar-right" action="/smuasset/login"
							method="POST">
							<div class="form-group">
								<input type="text" placeholder="Email" class="form-control"
									name="email">
							</div>
							<div class="form-group">
								<input type="password" placeholder="Password"
									class="form-control" name="password">
							</div>
							<button type="submit" class="btn btn-success">로그인</button>
							<input type="button" value="회원가입" class="btn btn-success"
								onclick="location.href='/smuasset/signup' ">
							</button>
						</form>
					</div>
				</c:otherwise>
			</c:choose>
			<!--/.navbar-collapse -->
		</div>

	</nav>
	<div class="jumbotron">
		<div class="container" id="upperContainer"
			style="text-align: center; padding-top: 5px;">
			<br /> <br />
			<h1>
				<b>회원가입</b>
			</h1>
			<br /> <br />
			<!--<p><a class="btn btn-primary btn-lg" href="#" role="button">자세히 보기 &raquo;</a></p>-->
		</div>
	</div>
 	<spring:hasBindErrors name="memberRegReq" />
	<form method="POST"
		style="margin: 0 auto; width: 250px; margin-top: 30px">
		<div class="form-group">
			<label for="userName">이름</label> <input type="text"
				class="form-control" id="userName" aria-describedby="nameHelp"
				placeholder="Enter name" style="width: 250px" name="name" value = "${memberRegReq.name}">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">이메일</label> <input type="email"
				class="form-control" id="exampleInputEmail1"
				aria-describedby="emailHelp" placeholder="Enter email"
				style="width: 250px" name="email" value="${memberRegReq.email }">

		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">비밀번호</label> <input
				type="password" class="form-control" id="exampleInputPassword1"
				placeholder="Password" style="width: 250px" name="password" value="${memberRegReq.password }">
			<small id="passwordHelp" class="form-text text-muted"
				style="font-size: 8px">길이는 6자 이상이어야 합니다.</small>
		</div>
		<div class="form-group">
			<label for="exampleInputPassword2">비밀번호 재확인</label> <input
				type="password" class="form-control" id="exampleInputPassword1"
				placeholder="Password" style="width: 250px">
		</div>
		<button type="submit" class="btn btn-primary">가입하기</button>
	</form>
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>