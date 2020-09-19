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
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<title>online Shopping - ${title}</title>

<!-- Bootstrap Core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap readable themes -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- dataTables.bootstrap.css -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="${css}/myapp.css" rel="stylesheet">

<script>
	window.menu = '${title}'
	window.contextRoot = '${contextRoot}'
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

			<!-- Load only user click view product page -->
			<c:if
				test="${userClickAllProducts == true or userClickCategoryProducts == true }">
				<%@include file="listProduct.jsp"%>

			</c:if>

			<!-- Loading single product page page -->
			<c:if test="${userClickShowProducts == true }">
				<%@include file="singleProduct.jsp"%>

			</c:if>

			<!-- Loading manage product product page -->
			<c:if test="${userClickManageProduct == true }">
				<%@include file="manageProducts.jsp"%>

			</c:if>
			
			
			<!-- Loading cart page -->
			<c:if test="${userClickShowCart == true }">
				<%@include file="cart.jsp"%>

			</c:if>




		</div>

		<!-- Footer -->

		<%@include file="./shared/footer.jsp"%>

		<!-- jQuery -->
		<script src="${js}/jquery.js"></script>
		
		<!-- jquery validate-->
        <script src="${js}/jquery.validate.js"></script>
		

		<!-- Bootstrap Core JavaScript -->
		<script src="${js}/bootstrap.min.js"></script>

		<!-- jquery.dataTables -->
		<script src="${js}/jquery.dataTables.js"></script>

		<!-- dataTables.bootstrap -->
		<script src="${js}/dataTables.bootstrap.js"></script>
		
		<!-- bootbox-->
		<script src="${js}/bootbox.min.js"></script> 
		
		
		<!-- custom js -->
		<script src="${js}/myapp.js"></script>
		
		
        
	</div>
</body>

</html>
