<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.bean.ResolvedByHO_OFWeeklyBean"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/ccmReports/css/new.css">
<title>Resolved by HO Of Weekly</title>
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="footer.jsp" />
<% ArrayList<ResolvedByHO_OFWeeklyBean> list = (ArrayList<ResolvedByHO_OFWeeklyBean>)request.getAttribute("alldata"); %>
<center>
<table id="commonStyle" border="1">
<tr>
<td>Ticket NO</td>
<td>Solved Date</td>
<td>Assigned On</td>
<td>Ticket LogDate</td>
<td>Closed Date</td>
<td>Problem Category</td>
<td>Problem Type</td>
<td>Problem Item</td>
<td>Problem Summary</td>
<td>Problem Description</td>
<td>Ticket Logged UserID</td>
<td>Ticket Logged Username</td>
<td>Sp UserId</td>
<td>Sp UserName</td>
<td>Role</td>
<td>Department</td>
<td>Status</td>
<td>User Office Code</td>
<td>Priority</td>
<td>Severity</td>
<td>Customer Group Name</td>
<td>Call Classification</td>
<td>Sp Call Classification</td>
</tr>
<%
for(ResolvedByHO_OFWeeklyBean b: list)
{
%>
<tr>
    <td><%=b.getTicketNO() %></td>
    <td><%=b.getSolvedDate() %></td>
    <td><%=b.getAssignedDate() %></td>
    <td><%=b.getTicketLogDate() %></td>
    <td><%=b.getClosedDate() %></td>
    <td><%=b.getProblemCategory() %></td>
    <td><%=b.getProblemType() %></td>
    <td><%=b.getProblemItem() %></td>
    <td><%=b.getProblemSummary() %></td>
    <td><%=b.getProblemDescription() %></td>
    <td><%=b.getTicketLoggedUserID() %></td>
    <td><%=b.getTicketLoggedUsername() %></td>
    <td><%=b.getSpUserId() %></td>
    <td><%=b.getSpUserName() %></td>
    <td><%=b.getRole() %></td>
    <td><%=b.getDepartment() %></td>
    <td><%=b.getStatus() %></td>
    <td><%=b.getUserOfficeCode() %></td>
    <td><%=b.getPriority() %></td>
    <td><%=b.getSeverity() %></td>
    <td><%=b.getCustomerGroupName() %></td>
    <td><%=b.getCallClassification() %></td>
    <td><%=b.getSpCallClassification() %></td>
    </tr>
    <%
}
%>
</table>
</center>
<!-- <div class="footer">
  <p>Copyright@ TCS Internal</p>
</div> -->

<div class="push"></div>
</body>
</html>