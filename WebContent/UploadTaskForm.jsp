<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>CODECOD 1.0</title>
  <meta charset="utf-8" />
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- css -->
  <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
  <link href="css/style.css" rel="stylesheet" media="screen">
  <link href="color/default.css" rel="stylesheet" media="screen">
  <script src="js/modernizr.custom.js"></script>
</head>
<body>

  <section id="contact" class="home-section bg-white">   
  	<div class="container">
  	  <div class="row">
        <div class="col-md-offset-2 col-md-8">
          <form action="uploadFile" method="post" enctype="multipart/form-data" role="form" class="contactForm">
            <div class="form-group">
              <input type="file" name="uploadFile" />
              <div class="validation"></div>
            </div>
            <button type="submit" value="Upload" class="btn btn-theme btn-lg btn-block">Upload and Decompose</button>
          </form>
        </div>
      </div>
  	</div>
  </section>
</body>
</html>