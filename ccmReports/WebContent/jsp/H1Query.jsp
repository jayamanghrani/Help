<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.bean.H1QueryBean"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/ccmReports/css/new.css">
<title>H1 Query</title>
</head>
<body>

<jsp:include page="header.jsp" />
<jsp:include page="footer.jsp" />
<% ArrayList<H1QueryBean> list = (ArrayList<H1QueryBean>)request.getAttribute("alldata"); %>
<center>
<table id="commonStyle" border="1">
<tr>
<td>TICKET NO</td>
<td>SP USERNAME</td>
<td>DEPARTMENT</td>
</tr>
<%
for(H1QueryBean b: list)
{
%>
<tr>
    <td><%=b.getTicketNo() %></td>
    <td><%=b.getSPUsername() %></td>
    <td><%=b.getDepartment() %></td>
    
    
    </tr>
    <%
}
%>
</table>
</center>
</div>
<div class="push"></div>
<!-- <div class="footer">
  <p>Copyright@ TCS Internal</p>
</div> -->
</body>
</html>