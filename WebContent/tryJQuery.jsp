<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript"  src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
// 	    $(document).ready(function() {
// 	     $('#ibutton').click(function(e) {
// 		     e.preventDefault();
// // 		     var ajaxdata = $("#country").val();
// // 		     var value ='country='+ajaxdata;		     
// // 		     var id = $("#id").val();
// // 		     var idnya = 'id='+id;

		 
// 		     $.ajax({
// 			     "url": "voting",
// 			     "type": "post",
// 			     "data": {
// 			    	 country: $("#country").val(),
// 			    	 id: $("#id").val()
// 			     },
// 			     "cache": false,
// 			     success: function(data) {
// // 			     	$("#country").val('');
// // 			    	$("#message").html(data).slideDown('slow');
// 					alert(data);
// 			     }
// 		     });
// 		 });
// 		});
//--------------------------------------------------------------------------------

function DoAction(id,name){
	$.ajax({
		type : "POST",
		url : "saveIt",
		data : "id="+id+"&name="+name,
		success : function(msg){
			alert("Data Saved :"+msg);
		}
	
	});
}
	</script>
	<style>
		#country{
		border: 1px solid #8707c2;
		}
	</style>
</head>
<body>
	<center>
	 
	<font face="verdana" size="4">
	Submit Form With Out Page Refresh In Java/Servlets - jQuery<br><br>
	</font>
	 
<!-- 	   <form method="post" name="sub" action="voting" id="sub"> -->
<!-- 	     <font face="verdana" size="2">Country:</font> -->
<!-- 	     <input type="text" name="country" id="country" size="30" /> -->
<!-- 	     <input type="hidden" name="id" id="id" value="1" size="30" /> -->
<!-- 	     <input type="button" id="ibutton" value="Save"/> -->
<!-- 	   </form> -->

<a href="#" OnClick="DoAction(1,'Jose');" > Click </a>
<a href="#" OnClick="DoAction(2,'Juan');" > Click </a>
	 
	<br><font face="verdana" size="2"><div id="message"></div></font>
	 
	<br><br><img src="images/java4s.png">
	 
	</center>
</body>
</html>