<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <link rel="stylesheet" href="new.css"> -->
<link rel="stylesheet" type="text/css" href="/ccmReports/css/login.css">

<title>CCM.NET</title>
</head>
<body>

<div class="header">
  <center><h2>CCM.Nets Reports</h2></center>

</div>

<div class="topnav">
  <a href="login.jsp">HOME</a>
  <!-- <a href="contact.jsp">CONTACT </a> -->
</div>
<div id = "marquee">
<MARQUEE DIRECTION=LEFT>Welcome to CCM.Net Reports</MARQUEE> 



<!-- <form name="myform" action="loginController" style="border:1px solid #ccc" method="post" onsubmit="return(validate());"> -->
<form name="myform" action="http://10.122.31.13:8888/oam/server/auth_cred_submit" style="border:1px solid #ccc" method="post" onsubmit="return(validate());">

  <div class="container">
    <h1>Login Form</h1>
    
    <hr>
	
    <label for="Username"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="Username" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" required>
   
	
    <div class="clearfix">
      <input type="hidden" name="action" value="loginpage">
      <button type="submit" class="signupbtn">Login</button>
    </div>
  </div>
</form>
<!-- <form action="loginController">
  <table>
  <input type="text" placeholder="Enter Username" name="Username" required>
 <input type="password" placeholder="Enter Password" name="psw" required>
  <input type="hidden" name="action" value="List">
  <tr><td><input type="submit"></td></tr>
  </table>
  
</form> -->



<script src="/ccmReports/js/validate.js"></script>






<div class="footer">
  <p>@Copyright Tcs Internal</p>
</div>
</body>
</html>