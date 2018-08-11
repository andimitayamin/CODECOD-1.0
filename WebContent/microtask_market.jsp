<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
	<title>Dashboard - Bootstrap Admin Template</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600"
	        rel="stylesheet">
	<link href="css/font-awesome.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<link href="css/pages/dashboard.css" rel="stylesheet">
	<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	    <![endif]-->
<title>Insert title here</title>
</head>
<body>
 <div class="navbar navbar-fixed-top">
	
	<div class="navbar-inner">
		
		<div class="container">
			
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</a>
			
			<a class="brand" href="index.html">
				Bootstrap Admin Template				
			</a>		
			
			<div class="nav-collapse">
				<ul class="nav pull-right">
					<li class="dropdown">						
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<i class="icon-cog"></i>
							Account
							<b class="caret"></b>
						</a>
						
						<ul class="dropdown-menu">
							<li><a href="javascript:;">Settings</a></li>
							<li><a href="javascript:;">Help</a></li>
						</ul>						
					</li>
			
					<li class="dropdown">						
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<i class="icon-user"></i> 
							EGrappler.com
							<b class="caret"></b>
						</a>
						
						<ul class="dropdown-menu">
							<li><a href="javascript:;">Profile</a></li>
							<li><a href="javascript:;">Logout</a></li>
						</ul>						
					</li>
				</ul>
			
				<form class="navbar-search pull-right">
					<input type="text" class="search-query" placeholder="Search">
				</form>
				
			</div><!--/.nav-collapse -->	
	
		</div> <!-- /container -->
		
	</div> <!-- /navbar-inner -->
	
</div> <!-- /navbar -->
    



    
<div class="subnavbar">

	<div class="subnavbar-inner">
	
		<div class="container">

			<ul class="mainnav">				
				<li class="active">
					<a href="Market.html">
						<i class="icon-list-alt"></i>
						<span>Market</span>
					</a>    				
				</li>
				<li>
					<a href="resume">
						<i class="icon-list-alt"></i>
						<span>Resume</span>
					</a>    				
				</li>
			
			</ul>

		</div> <!-- /container -->
	
	</div> <!-- /subnavbar-inner -->

</div> <!-- /subnavbar -->
    

    
<div class="main">
	
	<div class="main-inner">

	    <div class="container">
	    	
	     <div class="row">
	      	
	      	<div class="span4">
			<!-- /widget MICROTASK CLASS -->
	          <div class="widget widget-nopad">
	            <div class="widget-header"> <i class="icon-list-alt"></i>
	              <h3> Single Class</h3>
	            </div>
	            <div class="widget-content">
	              <ul class="news-items">
	              <c:forEach var="classes" items="${classList}">
	                <li>
	                  <div class="news-item-date"> <span class="news-item-day">50</span> <span class="news-item-month">pts</span> </div>
	                  <div class="news-item-detail"> <h3><a href="/CODECOD-1.0/microtask/<c:out value="${classes.clazzID}"/>" class="news-item-title" target="_blank"><c:out value = "${classes.clazzName}"/></a></h3>
<%-- 	                    <p class="news-item-preview"> <c:out value = "${classes.path}" /> </p> --%>
							<p class="news-item-preview"> <a href="microtask/<c:out value = "${classes.taskName}" />" target ="_blank"> download full file</a> </p>	
	                  </div>
	                  
	                </li>
	               </c:forEach>
	              </ul>
	            </div>
	          </div>
         	</div>
         	
         	
         	<div class="span4">
			<!-- /widget MICROTASK : METHOD -->
	          <div class="widget widget-nopad">
	            <div class="widget-header"> <i class="icon-list-alt"></i>
	              <h3> Single Method</h3>
	            </div>
	            <div class="widget-content">
	              <ul class="news-items">
	              <c:forEach var="microtask" items="${microtaskList}">
	                <li>
	                  
	                  <div class="news-item-date"> <span class="news-item-day">50</span> <span class="news-item-month">pts</span> </div>
	                  <div class="news-item-detail"> 
	                  		<p class="news-item-preview"> <c:out value = "${microtask.pathFile}" /> </p>
	                  		<a href="/CODECOD-1.0/microtask/<c:out value="${microtask.microtaskID}"/>" class="news-item-title" target="_blank"><c:out value = "${microtask.methodName}"/></a>
	                  </div>
	                  
	                </li>
	               </c:forEach>
	              </ul>
	            </div>
	          </div>
         	</div>
         	
         	<div class="span4">
			<!-- /widget MAJORITY VOTE -->
	          <div class="widget widget-nopad">
	            <div class="widget-header"> <i class="icon-list-alt"></i>
	              <h3> Majority Vote</h3>
	            </div>
	            <div class="widget-content">
	              <ul class="news-items">
	              <c:forEach var="taskMV" items="${listVote}">
	                <li>
<!-- 	                  <div class="news-item-date"> <span class="news-item-day"></span> <span class="news-item-month">Vote for</span> </div> -->
	                  <div class="news-item-detail">
	                  	 <a href="/CODECOD-1.0/vote/<c:out value="${taskMV.microtaskID}"/>" class="news-item-title" target="_blank"><c:out value = "${taskMV.microtaskName}"/></a>
	                  </div>
	                  
	                </li>
	               </c:forEach>
	              </ul>
	            </div>
	          </div>
         	</div>
			
			
         </div>      
	      	
    
	    </div> <!-- /container -->
	    
	</div> <!-- /main-inner -->
    
</div> <!-- /main -->
        
    
<div class="footer">
	
	<div class="footer-inner">
		
		<div class="container">
			
			<div class="row">
				
    			<div class="span12">
    				&copy; 2013 <a href="#">Bootstrap Responsive Admin Template</a>.
    			</div> <!-- /span12 -->
    			
    		</div> <!-- /row -->
    		
		</div> <!-- /container -->
		
	</div> <!-- /footer-inner -->
	
</div> <!-- /footer -->
    

<script src="js/jquery-1.7.2.min.js"></script>
<script src="js/excanvas.min.js"></script>
<script src="js/chart.min.js" type="text/javascript"></script>
<script src="js/bootstrap.js"></script>
<script src="js/base.js"></script>

</body>
</html>