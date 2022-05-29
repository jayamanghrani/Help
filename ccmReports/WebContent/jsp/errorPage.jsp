<%@page import="java.io.PrintWriter"%>
<%@ page import="java.util.*" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/ccmReports/css/new.css">
<title>Error Page</title>
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="footer.jsp" />

<center>
<%-- <% ArrayList<String> d = (ArrayList<String>)request.getAttribute("hi");  %>
<%
for(String b: d)
{
%>
<%=b %>
<%}%> --%>
<h2>You did something wrong</h2>

<h3>click below to to redirect to home Page</h3>
<a href = "/ccmReports/login.jsp"><h2><button class="openmodal" type="button">Home!</button></h2> </a>
</center>

<div class="push"></div>
<!-- <div class="footer">
  <p>Copyright@ TCS Internal</p>
</div>
 -->
</body>
</html>