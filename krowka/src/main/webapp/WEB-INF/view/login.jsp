<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pl">

<head>

	<link rel="apple-touch-icon" sizes="57x57" href="resources/img/favicon/apple-icon-57x57.png">
	<link rel="apple-touch-icon" sizes="60x60" href="resources/img/favicon/apple-icon-60x60.png">
	<link rel="apple-touch-icon" sizes="72x72" href="resources/img/favicon/apple-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="76x76" href="resources/img/favicon/apple-icon-76x76.png">
	<link rel="apple-touch-icon" sizes="114x114" href="resources/img/favicon/apple-icon-114x114.png">
	<link rel="apple-touch-icon" sizes="120x120" href="resources/img/favicon/apple-icon-120x120.png">
	<link rel="apple-touch-icon" sizes="144x144" href="resources/img/favicon/apple-icon-144x144.png">
	<link rel="apple-touch-icon" sizes="152x152" href="resources/img/favicon/apple-icon-152x152.png">
	<link rel="apple-touch-icon" sizes="180x180" href="resources/img/favicon/apple-icon-180x180.png">
	<link rel="icon" type="image/png" sizes="192x192"  href="resources/img/favicon/android-icon-192x192.png">
	<link rel="icon" type="image/png" sizes="32x32" href="resources/img/favicon/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="96x96" href="resources/img/favicon/favicon-96x96.png">
	<link rel="icon" type="image/png" sizes="16x16" href="resources/img/favicon/favicon-16x16.png">
	<link rel="manifest" href="resources/img/favicon/manifest.json">
	<meta name="msapplication-TileColor" content="#ffffff">
	<meta name="msapplication-TileImage" content="resources/img/favicon/ms-icon-144x144.png">
	<meta name="theme-color" content="#ffffff">

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><spring:message code="app.name" /></title>

	<!-- Bootstrap -->
	<link href="<c:url value="/resources/bower/bootstrap/dist/css/bootstrap.min.css" />" rel="stylesheet">

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->

	<script src="<c:url value="/resources/bower/react/react.min.js" />"></script>
	<script src="<c:url value="/resources/bower/react/react-dom.min.js" />"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.23/browser.min.js"></script>

	<script src="<c:url value="/resources/bower/angular/angular.min.js" />"></script>

	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

</head>

<body>

	<div class="container">
		<form class="form-signin" action="<c:url value="/login" />" method="post">
			<h2 class="form-signin-heading"><spring:message code="login.title" /></h2>
			<label for="username" class="sr-only"><spring:message code="model.user.username" /></label>
			<input type="text" name="username" class="form-control" placeholder="<spring:message code="model.user.username" />" required autofocus />
			<label for="password" class="sr-only"><spring:message code="model.user.password" /></label>
			<input type="password" name="password" class="form-control" placeholder="<spring:message code="model.user.password" />" required />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<c:if test="${not empty error}">
				<div class="alert alert-danger" role="alert"><spring:message code="${error}" /></div>
			</c:if>
			<button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="login.action" /></button>
		</form>
	</div>

	<footer class="footer">
		<div class="container">
			<a href="?lang=en"><img src="<c:url value="/resources/img/flag/gb.png" />" alt="English" class="language gb"/></a>
			<a href="?lang=pl"><img src="<c:url value="/resources/img/flag/pl.png" />" alt="Polski" class="language pl"/></a>
		</div>
	</footer>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="<c:url value="/resources/bower/jquery/dist/jquery.min.js" />"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value="/resources/bower/bootstrap/dist/js/bootstrap.min.js" />"></script>

</body>

</html>
