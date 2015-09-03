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
	text-align: center;
	color: black;
	font-size: 50px;
	font-family: "Edwardian Script ITC";
}

body {
	font-family: "Time New Roman";
	color: Black;
	background-color: url('image.jpg');
	background-size: cover;
	font-size: 20px;
}
</style>

</head>

<body>
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-brand">EvilCorp-Gulp Web Store</div>
		<div>
			<ul class="nav navbar-nav">
				<li><a href="Welcome.jsp" style="color: white"><b>Home</b></a></li>
				<%
					Userprofile user = (Userprofile) session.getAttribute("user");
					if (user != null) {
						request.setAttribute("userName", user.getUserName());
				%>
				<li><a href="Cart" style="color: white"><b>${userName}'s
							Cart</b></a></li>
				<li><a href="ExploreProducts?logout=y" style="color: white"><b>Logout</b></a></li>
				<%
					}
				%>
			</ul>
		</div>
	</div>
	</nav>
	<a href="ExploreProducts?goto=y"
		class="btn pull-right btn-primary btn-lg">Go Back</a>
	<br>
	<br>
	<h1 align=center>
		<b>Confirm your order</b><br> <br>
	</h1>
	<form action="OrderConfirmation" method="post">
		<div>${message}</div>
		<div class="panel panel-primary col-sm-6 col-sm">
			<div class="panel-heading">Payment</div>
			<div class="panel-body">

				<div class="form-group">
					<input type="radio" name="paymentType" value="1" checked>Credit
					Card<br> <input type="radio" name="paymentType" value="2">Debit
					Card<br>
				</div>
				<br>
				<div class="form-group">
					<label style="width: 40%">Card Number: </label><input type="text"
						name="cardNo" class="cardDetails"></input><br> <label
						style="width: 40%">Expiry Date <br> (MM/YYYY): </label><input type="text"
						name="expiryDate" required></input><br> <label
						style="width: 40%">CVV #: </label><input type="text" name="cvv"
						required></input><br>
				</div>
				<%
					if (request.getAttribute("error") != null
							&& request.getAttribute("error").toString().length() > 0) {
				%>

				<div class="alert alert-danger">
					<p>${error}</p>
				</div>
				<%
					}
				%>
				<div class="form-group">
					<br> <br> <label>Shipping Address</label><br> <label
						style="width: 20%">Street Address</label><input type="text"
						name="shipaddress" style="width: 50%" class="ShipAddress"></input><br>
					<label style="width: 20%" required>City</label><input type="text"
						name="shipcity" style="width: 30%"></input><br> <label
						style="width: 20%" required>State</label><input type="text"
						name="shipstate" style="width: 20%"></input><br> <label
						style="width: 20%">Postal Code</label><input type="text"
						name="shipzipcode" style="width: 20%" required></input><br>
				</div>
				<br> <label>Billing Address</label><br> <label
					style="width: 20%">Street Address</label><input type="text"
					name="billaddress" style="width: 50%" required></input><br> <label
					style="width: 20%">City</label><input type="text" name="billcity"
					style="width: 30%" required></input><br> <label
					style="width: 20%">State</label><input type="text" name="billstate"
					style="width: 20%" required></input><br> <label
					style="width: 20%">Postal Code</label><input type="text"
					name="billzipcode" style="width: 20%" required></input><br>
			</div>
		</div>
	</form>

</body>
</html>