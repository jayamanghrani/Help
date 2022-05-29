<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.bean.updated3pmQueryBean"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/ccmReports/css/new.css">
<title>Updated 3pm</title>
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="footer.jsp" />

<% ArrayList<updated3pmQueryBean> list = (ArrayList<updated3pmQueryBean>)request.getAttribute("alldata"); %>
<center>
<table id="commonStyle" border="1">
<tr>
<td>Ticket No</td>
<td>Problem Summary</td>
</tr>
<%
for(updated3pmQueryBean b: list)
{
%>
<tr>
    <td><%=b.getTicketNo() %></td>
    <td><%=b.getProblemSummary() %></td>
      </tr>
    <%
}
%>
</table>
</center>

</div>
<!-- <div class="footer">
  <p>Copyright TCS Internal</p>
</div>
 -->
<div class="push"></div>
</body>
</html>