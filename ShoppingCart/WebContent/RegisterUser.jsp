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
	text-align: center;
	color: black;
	font-size: 80px;
	font-family: "Edwardian Script ITC";
}

body {
	font-family: "Time New Roman";
	color: Black;
	background-image: url('image4.jpg');
    background-size: cover; 
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
	<div class= "panel panel-primary col-sm-6 col-sm-offset-3" >
		<div class ="panel-heading">
			Register User
		</div>
		<div class="panel-body">
			<form action=RegisterUser method = "POST">
				<div class="form-group">
					<label for="name">Name:</label>
					<input type="text" class="form-control" name="name"/>
				</div>
				<div class="form-group">
					<label for="email">Email:</label>
					<input type="email" class="form-control" name="email"/>
				</div>
				<div class="form-group">
					<label for="pwd">Password:</label>
					<input type="password" class="form-control" name="pwd"/>
				</div>
				<% if (request.getAttribute("error") != null && request.getAttribute("error").toString().length()>0) { %>
					
					<div class="alert alert-danger"><p>  ${error}</p></div>
				<% } %>	
				<div class="form-group">
					<label for="zipcode">ZipCode:</label>
					<input type="text" class="form-control" name="zipcode"/>
				</div>

				<button type="submit" value = "submit" class= "button btn-primary form-comtrol">Register</button>
			
			</form>
		</div>
	</div>
</body>
</html>