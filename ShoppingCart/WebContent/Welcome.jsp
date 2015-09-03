<%@page import="model.Userprofile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Store</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style>
h1 {
	text-align: left;
	color: black;
	font-size: 80px;
	font-family: "Edwardian Script ITC";
}

body {
	font-family: "Time New Roman";
	color: black;
	background-image: url("image2.jpg");
    background-size: cover; 
    background-color: black; 
	font-size: 20px;
}

navbar {
	font-family: "Edwardian Script ITC";
	color: red;
	;
}
  .panel-transparent {
        background: none;
    }
</style>
</head>
<nav class="navbar navbar-inverse" style=font-size:30px>
<div class="container-fluid">
	<div class="navbar-brand" style=color:red>EvilCorp-Gulp Web Store</div>
</div>
</nav>
<body>
<a href="AdminLogin.jsp" class="btn pull-right btn-primary btn-sm">Admin</a><br></br>
	<h1 ><b>Welcome!! 
		<br> Explore our amazing collection</b>
	</h1>
	<br>
				<form action="ExploreProducts" method="post">
					 <input type="submit"
						class="btn pull-left btn-danger btn-lg" value="Explore"></input><br></br>
				</form>
				<%
					Userprofile user = (Userprofile) session.getAttribute("user");
					if (user == null) {
				%>
				<form action="UserProfile.jsp" >
					 <input type="submit"
						class="btn pull-left btn-primary btn-lg" value="Sign In"></input><br></br>
				</form>
								<%
					}
				%>
				
				</body>
</html>