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
	color: black;
	background-image: url('image4.jpg');
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
		<div>
		<ul class="nav navbar-nav">
			<li><a href="Welcome.jsp" style="color: white"><b>Home</b></a></li>
		</ul>
	</div>
</div>
</nav>
<body>
	<h1 ><b>Administrator Login !! </b>
	</h1>
	<br>
	<br>
	<br>


	<div class="panel-group panel-primary col-sm-3 col-sm-offset-4" style=width:40%>
			<div class ="panel-heading">
			Administrator Login
		</div>
		<div class="panel panel-inverse">
			<div class="panel-body">
				<form action="Admin" method=post>
					<label>Email:</label> <input type="text"
						name="email"></input><br></br>
						<% if (request.getAttribute("error") != null && request.getAttribute("error").toString().length()>0) { %>
								
								<div class="alert alert-danger"><p>  ${error}</p></div>
							<% } %>	
					<label>Password:</label> <input type="password"
						name="pwd"></input><br></br>
						${message}
					 <input type="submit"
						class="btn pull-left btn-primary btn-lg" value="Admin Login"></input>
				</form>
			</div>
		</div>


	</div>

</body>
</html>