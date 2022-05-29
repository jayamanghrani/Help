<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.bean.aginingWithIPBean"%>
<%-- <%@ page trimDirectiveWhitespaces="true" %> --%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/ccmReports/css/new.css">
<title>Aging with ip</title>
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="footer.jsp" />
<% ArrayList<aginingWithIPBean> eList  = (ArrayList<aginingWithIPBean>) request.getAttribute("allData");%>
<!-- <div style="overflow-x:auto;"> -->
<center>
<table id="commonStyle" border="1">
<tr>
	<td>TICKET NO</td>
	<td>TICKET LOGGED DATE </td>
	<td>ASSSIGNED ON</td>
	<td>PROBLEM CATEGORY</td>
	<td>PROBLEM TYPE</td>
	<td>PROBLEM ITEM</td>
	<td>PROBLEM SUMMARY</td>
	<td>PROBLEM DESCRIPTION</td>
	<td>TICKET LOGGED USERID</td>
	<td>TICKET LOGGED USERNAME</td>
	<td>PERSON RESPONSIBLE USER ID</td>
	<td>PERSON RESPONSIBLE USERNAME</td>
	<td>ROLE</td>
	<td>PENDING TO WIP</td>
	<td>DEPARTMENT</td>
	<td>STATUS</td>
	<td>USER OFFICE CODE</td>
	<td>PRIORITY</td>
	<td>SEVERITY</td>
	<td>CUSTOMER GROUP NAME</td>
	<td>CALL CLASSIFICATION</td>
	<td>SP CALL  CLASSIFICATION</td>
	<td>NO OF AGING DAYS</td>
</tr>

<%
for(aginingWithIPBean b:eList)
{
%>

<tr>
	
    <td><%=b.getTicketNO() %></td>
    <td><%=b.getTicketLogDate() %></td>
    <td><%=b.getAssignedOn() %></td>
    <td><%=b.getProblemCategory() %></td>
    <td><%=b.getProblemType() %></td>
    <td><%=b.getProblemItem() %></td>
    <td><%=b.getProblemSummary() %></td>
    <td><%=b.getProblemDescription() %></td>
    <td><%=b.getTicketLoggedUserID() %></td>
    <td><%=b.getTicketLoggedUsername()%></td>
    <td><%=b.getPersonResponsibleUserId() %></td>
    <td><%=b.getPersonResponsibeUserName() %></td>
    <td><%=b.getRole() %></td>
    <td><%=b.getPendingToWIP() %></td>
    <td><%=b.getDepartment() %></td>
    <td><%=b.getStatus() %></td>
    <td><%=b.getUserOfficeCode() %></td>
    <td><%=b.getPriority() %></td>
    <td><%=b.getSeverity() %></td>
    <td><%=b.getCustomerGroupName() %></td>
    <td><%=b.getCallClassification() %></td>
    <td><%=b.getSpCallClassification() %></td>
    <td><%=b.getNoOfAgingDays() %></td>
    
    
    </tr>
    <%
}
%>

</table>
</center>
<!-- </div> -->
<div class="push"></div>

<!-- <div class="footer">
  <p>Copyright@ TCS Internal</p>
</div> -->



</body>
</html>