<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/ccmReports/css/new.css">
<title>3 to 6</title>
</head>
<body>
<div class="header">
  <center><h2>CCM.Nets Reports</h2></center>

</div>

<jsp:include page="header.jsp" />
<jsp:include page="footer.jsp" />
<div class="content">
<center>
<table>


<tr><button class="button"><a href="Reopen3To6pmController?action=Reopen3To6pmList">Reopen 3 To 6 pm View</a></button></tr><br><br>
<tr><button class="button"><a href="Reopen3To6pmControllerExcel?action=Reopen3To6pmList">Reopen 3 To 6 pm Download</a></button></tr><br><br>

<tr><button class="button"><a href="updated_3pm_OF_3To6Controller?action=updated_3pm_OF_3To6List">updated 3PM View</a></button></tr><br><br>
<tr><button class="button"><a href="updated_3pm_OF_3To6ControllerExcel?action=updated_3pm_OF_3To6List">updated 3PM Download</a></button></tr><br><br>


<tr><button class="button"><a href="updated_6pm_OF_3To6Controller?action=updated_6pm_OF_3To6List">updated 6PM View</a></button></tr><br><br>
<tr><button class="button"><a href="updated_6pm_OF_3To6ControllerExcel?action=updated_6pm_OF_3To6List">updated 6PM Download</a></button></tr><br><br>


</table>
</center>
</div>
<!-- <div class="footer">
  <p>Copyright@ TCS Internal</p>
</div> -->

</body>
</html>