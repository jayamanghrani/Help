<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/ccmReports/css/new.css">
<title>Enter date</title>
</head>
<body>

<!DOCTYPE html>
<html>
<body>
<% String ControllerName = (String)request.getAttribute("controllerName"); %>
</div>

<div class="topnav">
  <a href="homepage.jsp">HOME</a>
  <a href="contact.jsp">CONTACT </a>
  <a href="login.jsp" style="float: right;">LOGOUT </a>
</div>
<center><h1>Enter Date</h1></center>
<center>
<form name="myform" action="<%=ControllerName%>" onsubmit="return(compare());">
  <table>
  <tr><td>From:</td>
  <td><input type="date" name="fromDate"></td></tr>
  <tr><td>To:</td>
  <td><input type="date" name="toDate"></td></tr>
  <input type="hidden" name="action" value="List">
  <tr><td><input type="submit"></td></tr>
  </table>
  
</form>
<script src="/ccmReports/js/validate.js"></script>
</center>
<div class="footer">
  <p>@Copyright Tcs Internal</p>
</div>
</body>
</html>