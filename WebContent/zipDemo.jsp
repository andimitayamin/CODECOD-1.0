<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="tes_decomposer" method="post" enctype="multipart/form-data" role="form" class="contactForm">
            <div class="form-group">
              <input type="file" name="uploadJar" />
              <div class="validation"></div>
            </div>
            <button type="submit" value="Upload" class="btn btn-theme btn-lg btn-block">Upload and Decompose</button>
 </form>
</body>
</html>