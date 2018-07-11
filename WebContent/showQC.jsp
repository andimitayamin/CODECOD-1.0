<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<title>Dashboard - Bootstrap Admin Template</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/bootstrap-responsive.min.css" rel="stylesheet">
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600"
	rel="stylesheet">
<link href="../css/prism.css" rel="stylesheet">
<link href="../css/font-awesome.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">
<link href="css/pages/dashboard.css" rel="stylesheet">
<link rel="stylesheet" href="../highlight/styles/default.css">
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->

<title>Insert title here</title>
</head>
<body>
	<div class="navbar navbar-fixed-top">

		<div class="navbar-inner">

			<div class="container">

				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="index.html"> Bootstrap Admin Template </a>

				<div class="nav-collapse">
					<ul class="nav pull-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-cog"></i> Account <b	class="caret"></b>
						</a>

							<ul class="dropdown-menu">
							</ul></li>

						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i>
								EGrappler.com <b class="caret"></b>
						</a>

							<ul class="dropdown-menu">
<!-- 								<li><a href="javascript:;">Profile</a></li> -->
<!-- 								<li><a href="javascript:;">Logout</a></li> -->
							</ul></li>
					</ul>

					<form class="navbar-search pull-right">
						<input type="text" class="search-query" placeholder="Search">
					</form>

				</div>
				<!-- /.nav-collapse -->
			</div>
			<!-- /container -->

		</div>
		<!-- /navbar-inner -->

	</div>
	<!-- /navbar -->





	<div class="subnavbar">
		<div class="subnavbar-inner">
			<div class="container">
				<ul class="mainnav">
				<li>
					<a href="dashboard.jsp">
						<i class="icon-dashboard"></i>
						<span>Dashboard</span>
					</a>	    				
				</li>
			
				<li>
					<a href="../list_of_task">
						<i class="icon-tasks"></i>
						<span>List of Task</span> 
					</a> 				
				</li>
				
				<li class="active">
					<a href="#">
						<i class="icon-check"></i>
						<span>Quality Control</span> 
					</a> 				
				</li>
				</ul>
			</div>
			<!-- /container -->
		</div>
		<!-- /subnavbar-inner -->
	</div>
	<!-- /subnavbar -->



	<div class="main">
		<div class="main-inner">
			<div class="container">
				<div class="row">
					<div class="span6">
						<div class="widget">
							<div class="widget-content">
								<h1>Output Agreement</h1>							
									<table class="table table-striped table-bordered">
					               	<thead>
					               		<tr>
					               			<th> Smell </th>
					               			<th> Line </th>
					               			<th> Vote </th>
					               		</tr>
					               	</thead>
					               	<tbody>
					               	 <c:forEach var="OA" items="${OutAgree}">
					               		<tr>
					               			<td><c:out value = "${OA.smell}" /></td>
					               			<td><c:out value = "${OA.line}" /></td>
					               			<td><c:out value = "${OA.agreement}" /></td>
					               		</tr>
					               	 </c:forEach>
					               	</tbody>
					               </table>		
							</div>
						</div>
					</div>


				  <div class="span6">
					<div class="widget">
					  <div class="widget-content">
								<h3>Majority Vote </h3>
					               <table class="table table-striped table-bordered">
					               	<thead>
					               		<tr>
					               			<th> Smell </th>
					               			<th> Line </th>
					               			<th> Vote </th>
					               		</tr>
					               	</thead>
					               	<tbody>
					               	 <c:forEach var="MV" items="${MajVot}">
					               		<tr>
					               			<td><c:out value = "${MV.smell}" /></td>
					               			<td><c:out value = "${MV.line}" /></td>
					               			<td><c:out value = "${MV.vote_up}" /></td>
					               			<td><c:out value = "${MV.vote_down}" /></td>
					               		</tr>
					               	 </c:forEach>
					               	</tbody>
					               </table>					
					  </div>						
					</div>
				  </div>
				</div>
			</div>
			<!-- /container -->
		</div>
		<!-- /main-inner -->
	</div>
	<!-- /main -->

	<div class="footer">

		<div class="footer-inner">

			<div class="container">

				<div class="row">

					<div class="span12">
						&copy; 2013 <a href="#">Bootstrap Responsive Admin Template</a>.
					</div>
					<!-- /span12  -->

				</div>
				<!-- /row -->

			</div>
			<!-- /container -->

		</div>
		<!-- /footer-inner -->

	</div>
	<!-- /footer -->
	
</body>
	<script type="text/javascript" src="../js/prism.js"></script>
	<script src="../js/jquery-1.7.2.min.js"></script>
	<script src="../js/bootstrap.js"></script>

</html>