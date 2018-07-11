<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
	<title>Dashboard - Bootstrap Admin Template</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/bootstrap2.min.css" rel="stylesheet">
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
<title>Upload Task</title>
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
		        
		<div class="main">
			
			<div class="main-inner">
		
			    <div class="container">
			    	
			     <div class="row">
			      	
			      	<div class="span12">
			      		          <!-- /widget --> 
			          <div class="widget widget-nopad">
			            <!-- /widget-header -->
				          <div class="widget">
				            <div class="widget-header"> <i class="icon-bookmark"></i>
				              <h3>Important Shortcuts</h3>
				            </div>
				            <!-- /widget-header -->
				            <div class="widget-content"><p>
				              <div class="shortcuts"> 
				              	  <a href="uploadTask.jsp" class="shortcut"> <i class="shortcut-icon icon-upload"></i><span class="shortcut-label">Upload Task</span> </a>
					              <a href="list_of_task" class="shortcut"> <i class="shortcut-icon icon-list-alt"></i> <span class="shortcut-label">List of Task</span> </a>
					              <p>
					              <a href="list" class="shortcut"> <i class="shortcut-icon icon-signal"></i> <span class="shortcut-label">Quality Control</span> </a>
					              <a href="/UserLogout" class="shortcut"> <i class="shortcut-icon icon-user"></i><span class="shortcut-label">Logout</span> </a>
					              <!-- /shortcuts --> 
				            </div>
				            <!-- /widget-content --> 
				          </div>
			            <!-- /widget-content --> 
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
</body>
</html>