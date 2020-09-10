<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextRoot" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>online Shopping - ${title}</title>

<!-- Bootstrap Core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap readable themes -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="${css}/shop-homepage.css" rel="stylesheet">

<script>
	window.menu = '${title}'
</script>


</head>

<body>
<div class="wrapper">
	<!-- Navigation -->
	<%@include file="./shared/navbar.jsp"%>

<div class="content">

	<!-- Page Content -->
	<c:if test="${userClickHome == true }">
		<%@include file="home.jsp"%>

	</c:if>

	<!-- Load only user click contact page -->
	<c:if test="${userClickContact == true }">
		<%@include file="contact.jsp"%>

	</c:if>


	<!-- Load only user click about page -->
	<c:if test="${userClickAbout == true }">
		<%@include file="about.jsp"%>

	</c:if>

</div>

	<!-- Footer -->

	<%@include file="./shared/footer.jsp"%>

	<!-- jQuery -->
	<script src="${js}/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="${js}/bootstrap.min.js"></script>


	<script src="${js}/myapp.js"></script>
</div>
</body>

</html>
