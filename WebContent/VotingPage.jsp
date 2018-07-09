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

<link rel="stylesheet" href="../highlight/styles/default.css">
	<script type="text/javascript" src="../js/prism.js"></script>
	<script  type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
	<script  type="text/javascript" src="../js/bootstrap.js"></script>
	<script type="text/javascript">

	function vote(id,smell,line,type){
		var vote_status;
		$.ajax({		
			type : "POST",
			url : "../voting",
			data : "id="+id+"&smell="+smell+"&line="+line+"&type="+type,
			success : function(status){
				
					if(type == "up"){
						 $("#ThumbsUp").attr("disabled", true);
					}
					else{
						$("#ThumbsDown").attr("disabled", true);
					}
			}
		});
	}
	</script>
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
					<li><a href="../CodeMarket"> <i
							class="icon-list-alt"></i> <span>Market</span>
					</a></li>
					<li class="active"> <a href="#"><i 
							class="icon-thumbs-up"></i><i class="icon-thumbs-down"></i><span>Vote</span></a></li>
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
					<div class="span8">
						<div class="widget">
							<div class="widget-content">
								<h1>${microtask.methodName}</h1>							
									<pre class="line-numbers"><code class="language-java">${microtask.declaration}${microtask.methodBody}</code></pre>
							</div>
						</div>
					</div>


				  <div class="span4">
					<div class="widget">
					  <div class="widget-content">
						<div class="span3">
							<h3>Code Smell Detected </h3>
							<table class="table table-striped table-bordered">
								<thead>
									<tr>
										<th>Smell's Name</th>
										<th>Position (line)</th>
										<th>Vote Up</th>
										<th>Vote Down</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="vote" items="${majvot}">
									<tr>
										<td><c:out value = "${vote.smell}" /></td>
										<td><c:out value = "${vote.line}" /></td>
										<td><a href="#"  OnClick="vote('<c:out value = "${vote.microtaskID}" />','<c:out value = "${vote.smell}" />','<c:out value = "${vote.line}" />','up');">
											<i class="icon-large icon-thumbs-up" id="ThumbsUp" value=1 ></i>
											</a></td>
										<td><a href="#" OnClick="vote('<c:out value = "${vote.microtaskID}" />','<c:out value = "${vote.smell}" />','<c:out value = "${vote.line}" />','down');">
											<i class="icon-large icon-thumbs-down" id="ThumbsDown" value=-1></i>
											</a></td>
<%-- 											<td><button id="ThumbsUp" value = "up" OnClick="vote('<c:out value = "${vote.microtaskID}" />','<c:out value = "${vote.smell}" />','<c:out value = "${vote.line}" />','up');"></button></td> --%>
<%-- 											<td><button id="ThumbsDown" value = "down" OnClick="vote('<c:out value = "${vote.microtaskID}" />','<c:out value = "${vote.smell}" />','<c:out value = "${vote.line}" />','down');"></button></td> --%>
									</tr>
									</c:forEach>
								</tbody>				
							</table>
						</div>						
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
</html>