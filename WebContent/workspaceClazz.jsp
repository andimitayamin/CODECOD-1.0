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
							class="icon-large icon-edit"></i> <span>Workspace</span></a></li>
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
								<h1>${microtaskClazz.methodName}</h1>							
									<pre class="line-numbers"><code class="language-java">${microtaskClazz.classBody}</code></pre>
							</div>
						</div>
					</div>


				  <div class="span4">
					<div class="widget">
					  <div class="widget-content">
						<div class="span3">
								<h3>Code Smell Detected </h3>
								<p>	<h3><button class="btn btn-info" onclick="addLargeClass();"><i  class="icon-plus"></i> </button>  Large Class </h3>
								<p>	<h3><button class="btn btn-info" onclick="addPrimitiveObs();"><i  class="icon-plus"></i> </button>  Primitive Obsession </h3>
								<p>	<h3><button class="btn btn-info" onclick="addLazyClass();"><i  class="icon-plus"></i> </button>  Lazy Class</h3>
								<p>	<h3><button class="btn btn-info" onclick="addDataClass();"><i  class="icon-plus"></i> </button>  Data Class</h3>	
								<p>	<h3><button class="btn btn-info" onclick="addSpeculativeGen();"><i  class="icon-plus"></i> </button>  Speculative Generality </h3>
								<p> <h3><button class="btn btn-info" onclick="addLongParameter();"><i  class="icon-plus"></i> </button>  Long Parameter </h3>
								<p>	<h3><button class="btn btn-info" onclick="addTempField();"><i  class="icon-plus"></i> </button>  Temporary Field </h3>		
								<p>	<h3><button class="btn btn-info" onclick="addBadComment();"><i  class="icon-plus"></i> </button>  Bad Comment </h3>
								<p>	<h3><button class="btn btn-info" onclick="addSwitch();"><i  class="icon-plus"></i> </button>  Switch Statement </h3>							
						</div>						
					  </div>
						
						<div class="widget-content">
							<div class="span3">
																
								<form action = "../answeredMicrotask"  method="post" id="selectSmell">
												
									<div id="addLargeClass">Large Class on line :	</div>
									<div id="addPrimitiveObs">Primitive Obsession on line :</div>
									<div id="addLazyClass">Lazy Class on line :	</div>
									<div id="addDataClass">Data Class on line :	</div>
									<div id="addSpeculativeGen">Speculative Generality on line :	</div>
									<div id="addLongParameter">Long Parameter on line :	</div>
									<div id="addTempField">Temporary Field on line :	</div>
									<div id="addBadComment">Bad Comment on line :	</div>
									<div id="addSwitchStatement">Switch statement on line :	</div>	
									<p>
									<h3>Suggested Refactoring</h3>
									<textarea id ="suggestedRef" name="suggestedRefactoring" class="form-control" column=20 rows=10 style="resize : none;" placeholder="Give your suggested refactoring"></textarea>
																												
									<input type = "hidden" name="methodId" value="${microtaskClazz.methodName}">							
									
										<input type = "hidden" name="hiddenSmellIni" id="IDhiddenSmell"/>
										<input type = "hidden" name="hiddenSmellLine" id="IDhiddenSmellLine"/>
										
										<input type = "hidden" name="microtaskID" id="microtaskID" value="${microtaskClazz.microtaskID}"/>
									<p>	<input type = "button" value="send" onclick="sendValue();" />
									
								</form>	
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

				</div>

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

	
	<script>
		var fileId=0;
	
		function addLargeClass(){
			fileId++;
			var longMethod = '<input type="hidden" name="largeClass" value="Large Class" id="smell-'+fileId+'" />';
			var lineId = '"line-'+fileId+'"';
// 			var lineLongMethod = '<input type="text" name="lineLargeClass" id="line-'+fileId+'" style="width:20px;"/> <i class="icon-large icon-remove" onCLick="removeField('+lineId+');"></i>';
	        var lineLongMethod = '<input type="text" name="lineLargeClass" id="line-'+fileId+'" style="width:20px;"/>';
	        addElement('addLargeClass', 'p', 'file-' + fileId, longMethod);
			addElement('addLargeClass','p','part-'+fileId, lineLongMethod);
		}
		
		function addPrimitiveObs(){
			fileId++;
			var primitiveObs = '<input type="hidden" name="primitiveObs" value="Primitive Obsession" id="smell-'+fileId+'" />';
			var linePrimitiveObs = '<input type="text" name="linePrimitiveObs" id="line-'+fileId+'" style="width:20px;"/>'
	        addElement('addPrimitiveObs', 'p', 'file-' + fileId, primitiveObs);
			addElement('addPrimitiveObs','p','part-'+fileId, linePrimitiveObs);
		}
		
		function addLazyClass(){
			fileId++;
			var lazyClass = '<input type="hidden" name="lazyClass" value="Lazy Class" id="smell-'+fileId+'" />';
			var lineLazyClass = '<input type="text" name="lineLazyClass" id="line-'+fileId+'" style="width:20px;" />'
	        addElement('addLazyClass', 'p', 'file-' + fileId, lazyClass);
			addElement('addLazyClass','p','part-'+fileId, lineLazyClass);
		}
		
		function addDataClass(){
			fileId++;
			var dataClass = '<input type="hidden" name="dataClass" value="Data Class" id="smell-'+fileId+'" />';
			var lineDataClass = '<input type="text" name="lineDataClass" id="line-'+fileId+'" style="width:20px;" />'
	        addElement('addDataClass', 'p', 'file-' + fileId, dataClass);
			addElement('addDataClass','p','part-'+fileId, lineDataClass);
		}
		
		function addSpeculativeGen(){
			fileId++;
			var speculativeGen = '<input type="hidden" name="speculativeGen" value="Speculative Generality" id="smell-'+fileId+'" />';
			var lineSpeculativeGen = '<input type="text" name="lineSpeculativeGen" id="line-'+fileId+'" style="width:20px;" />'
	        addElement('addSpeculativeGen', 'p', 'file-' + fileId, speculativeGen);
			addElement('addSpeculativeGen','p','part-'+fileId, lineSpeculativeGen);
		}
		
		function addLongParameter(){
			fileId++;
			var longParameter = '<input type="hidden" name="longParameter" value="Long Parameter" id="smell-'+fileId+'" />';
			var lineLongParameter = '<input type="text" name="lineLongParameter" id="line-'+fileId+'" style="width:20px;"/>'
	        addElement('addLongParameter', 'p', 'file-' + fileId, longParameter);
			addElement('addLongParameter','p','part-'+fileId, lineLongParameter);
		}
		
		function addTempField(){
			fileId++;
			var tempField = '<input type="hidden" name="tempField" value="Temporary Field" id="smell-'+fileId+'" />';
			var lineTempField = '<input type="text" name="lineTempField" id="line-'+fileId+'" style="width:20px;" />'
	        addElement('addTempField', 'p', 'file-' + fileId, tempField);
			addElement('addTempField','p','part-'+fileId, lineTempField);
		}

		function addBadComment(){
			fileId++;
			var badComment = '<input type="hidden" name="badComment" value="Bad Comment" id="smell-'+fileId+'" />';
			var lineBadComment = '<input type="text" name="lineBadComment" id="line-'+fileId+'" style="width:20px;" />'
	        addElement('addBadComment', 'p', 'file-' + fileId, badComment);
			addElement('addBadComment','p','part-'+fileId, lineBadComment);
		}
		
		function addSwitch(){
			fileId++;
			var switchStatement = '<input type="hidden" name="switchStatement" value="Switch Statement" id="smell-'+fileId+'" />';
			var lineSwitchStatement= '<input type="text" name="lineSwitchStatement" id="line-'+fileId+'" style="width:20px;" />'
	        addElement('addSwitchStatement', 'p', 'file-' + fileId, switchStatement);
			addElement('addSwitchStatement','p','part-'+fileId, lineSwitchStatement);
		}
		
		
	    function addElement(parentId, elementTag, elementId, html) {
	        // Adds an element to the document
	        var p = document.getElementById(parentId);
	        var newElement = document.createElement(elementTag);
	        newElement.setAttribute('id', elementId);
	        newElement.innerHTML = html;
	        p.appendChild(newElement);
	    }
	    
	    function removeField(elementId){
	        var element = document.getElementById(elementId);
	        element.parentNode.removeChild(element);
	    }
		
	    function sendValue(){
	    	var smells=[];
	    	var lines=[];
	    	
	    	for(var i=1; i<=fileId; i++){
	    		smells.push(document.getElementById("smell-"+[i]).value);
	    	}
	    	
	    	for(var j=1; j<=fileId; j++){
	    		lines.push(document.getElementById('line-'+[j]).value);
	    	}
	    	
	    	var detectedSmell = document.getElementById("IDhiddenSmell");
	    	detectedSmell.value = smells.join(",");
	    	
	    	var LineOfDetectedSmell = document.getElementById("IDhiddenSmellLine");
	    	LineOfDetectedSmell.value = lines;
	    	
	    	var suggestedRefactoring = document.getElementById("suggestedRef").value;
	    	
	    	var form = document.getElementById("selectSmell");
	    	form.submit();
	    	
	    	alert("Thank you for your answer");
	    }

	</script> 

</html>