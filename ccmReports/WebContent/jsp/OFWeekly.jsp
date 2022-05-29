<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/ccmReports/css/new.css">
<title>OF Weekly</title>
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="footer.jsp" />
<center>

<tr><button class="button"><a href="ResolvedByHO_OFWeeklyController?action=ResolvedByHO_OFWeeklyList">Resolved By HO View</a></button></tr><br><br>
<tr><button class="button"><a href="ResolvedByHO_OFWeeklyControllerExcel?action=ResolvedByHO_OFWeeklyList">Resolved By HO Download</a></button></tr><br><br>

<tr><button class="button"><a href="ResolvedbyTCS_OFWeeklyController?action=ResolvedbyTCS_OFWeeklyList">Resolved by TCS View</a></button></tr><br><br>
<tr><button class="button"><a href="ResolvedbyTCS_OFWeeklyControllerExcel?action=ResolvedbyTCS_OFWeeklyList">Resolved by TCS Download</a></button></tr><br><br>

<tr><button class="button"><a href="SentForClarificationHO_NEW_OFWeeklyController?action=SentForClarificationHO_NEW_OFWeeklyList">Sent For Clarification HO View</a></button></tr><br><br>
<tr><button class="button"><a href="SentForClarificationHO_NEW_OFWeeklyControllerExcel?action=SentForClarificationHO_NEW_OFWeeklyList">Sent For Clarification HO Download</a></button></tr><br><br>

<tr><button class="button"><a href="SentForClarificationTCS_NEW_OFWeeklyController?action=SentForClarificationTCS_NEW_OFWeeklyList">Sent For Clarification TCS View</a></button></tr><br><br>
<tr><button class="button"><a href="SentForClarificationTCS_NEW_OFWeeklyControllerExcel?action=SentForClarificationTCS_NEW_OFWeeklyList">Sent For Clarification TCS Download</a></button></tr><br><br>
</center>
</div>
<!-- <div class="footer">
  <p>Copyright TCS Internal</p>
</div> -->

</body>
</html>