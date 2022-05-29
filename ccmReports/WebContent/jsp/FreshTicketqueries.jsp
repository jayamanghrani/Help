<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/ccmReports/css/new.css">
<title>Freshly open tickets</title>
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="footer.jsp" />


<tr><button class="button"><a href="Reopen10To3pmController?action=Reopen10To3pmList">Reopen 10 To 3 PM View</a></button></tr><br><br>
<tr><button class="button"><a href="Reopen10To3pmControllerExcel?action=Reopen10To3pmList">Reopen 10 To 3 PM Download</a></button></tr><br><br>

<tr><button class="button"><a href="updated3pmQueryController?action=updated3pmQueryList">Updated 3 PM Query View</a></button></tr><br><br>
<tr><button class="button"><a href="updated3pmQueryControllerExcel?action=updated3pmQueryList">Updated 3 PM Query Download</a></button></tr><br><br>


<tr><button class="button"><a href="updated10amQueryController?action=updated10amQueryList">Updated 10 AM  Query View</a></button></tr><br><br>
<tr><button class="button"><a href="updated10amQueryControllerExcel?action=updated10amQueryList">Updated 10 AM  Query Download</a></button></tr><br><br>

</table>
</center>
</div>
<!-- <div class="footer">
  <p>Copyright@ TCS Internal</p>
</div> -->
</body>
</html>