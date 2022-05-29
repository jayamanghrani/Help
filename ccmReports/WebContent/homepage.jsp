<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <link rel="stylesheet" href="new.css"> -->
<link rel="stylesheet" type="text/css" href="/ccmReports/css/new.css">
<script type="text/javascript" src="js/myScript.js"></script>
<title>CCM REPORTS</title>
</head>
<body>

<div class="header">
  <center><h2>CCM.Nets Reports</h2></center>

</div>

<div class="topnav">
  <a href="homepage.jsp">HOME</a>
  <a href="contact.jsp">CONTACT </a>
  <a href="http://eamapst1:8888/oam/server/logout" style="float: right;">LOGOUT </a>
</div>
<%-- <jsp:include page="header.jsp" />
<jsp:include page="footer.jsp" />
 --%>
<div id = "marquee">
<MARQUEE DIRECTION=LEFT>Welcome to CCM.Net Reports</MARQUEE> 


<div class="content">
<center>
<!-- buttons -->
<table>
<tr>
<td><button class="openmodal" style="vertical-align:middle">Aging with IP</button></td>
<td><button class="openmodal" style="vertical-align:middle">H1 Query</button></td>
<td><button class="openmodal" style="vertical-align:middle">Newly Opened Tickets</button></tr>
<tr><td><td><td></td></td></td></tr>
<tr><td><td><td></td></td></td></tr>
<tr><td><td><td></td></td></td></tr>
<tr><td><td><td></td></td></td></tr>
<tr>
<td><button class="openmodal" style="vertical-align:middle">Newly Opened HO </button></td>
<td><button class="openmodal" style="vertical-align:middle">Reopened Tickets </button></td>
<td><button class="openmodal" style="vertical-align:middle">Resolved By Ho </button></td></tr>

<tr><td><td><td></td></td></td></tr>
<tr><td><td><td></td></td></td></tr>
<tr><td><td><td></td></td></td></tr>
<tr><td><td><td></td></td></td></tr>

<tr>
<td><button class="openmodal" style="vertical-align:middle">Resolved By Tickets </button></td>
<td><button class="openmodal" style="vertical-align:middle">Return After Clarification</button></td>
<td><button class="openmodal" style="vertical-align:middle">Sent For Clarification  </button></td></tr>

<tr><td><td><td></td></td></td></tr>
<tr><td><td><td></td></td></td></tr>
<tr><td><td><td></td></td></td></tr>
<tr><td><td><td></td></td></td></tr>

<tr>
<td><button class="openmodal" style="vertical-align:middle">Fresh Ticket queries </button></td>
<td><button class="openmodal" style="vertical-align:middle">3 to 6 </button></td>
<td><button class="openmodal" style="vertical-align:middle">OF Weekly </button></td></tr>








<!-- aging with ip-->
<!-- Trigger/Open The Modal -->
<!--<button class="openmodal">Aging with IP</button>-->
<!-- The Modal -->
<div id="Aging with IP" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
      <span class="close">&times;</span>
      <h2>Aging with WIP </h2>
    </div>
    <div class="modal-body"><br><br>
      <a href="AgingwithIPcontroller?action=List"><button class="button" style="vertical-align:middle"><span>Aging with WIP View</span></button></a> &nbsp &nbsp &nbsp &nbsp
	  <a href="AgingwithIPcontrollerExcel?action=List"><button class="button" style="vertical-align:middle"><span>Aging with WIP Download</span></button></a> <br> <br><br>
    </div>
    <div class="modal-footer">
      <h3>Reports</h3>
    </div>
  </div>
</div>


<!-- H1 query-->
<!-- Trigger/Open The Modal -->
<!-- <button class="openmodal">H1 Query</button>-->

<!-- The Modal -->
<div id="myModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
      <span class="close">&times;</span>
      <h2>H1 Query</h2>
    </div>
    <div class="modal-body"><br><br>
      <a href="H1Querycontroller?action=H1List"><button class="button" style="vertical-align:middle"><span>H1 Query View</span></button></a> &nbsp &nbsp &nbsp &nbsp
	  <a href="H1QuerycontrollerExcel?action=H1List"><button class="button" style="vertical-align:middle"><span>H1 Query Download</span></button></a> 	  <br><br><br>
    </div>
    <div class="modal-footer">
      <h3>Reports</h3>
    </div>
  </div>

</div>


<!-- Newly Opened Tickets-->
<!-- Trigger/Open The Modal -->
<!-- <button class="openmodal">Newly Opened Tickets</button><br><br>-->

<!-- The Modal -->
<div id="myModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
      <span class="close">&times;</span>
      <h2>Newly Opened Tickets</h2>
    </div>
    <div class="modal-body"><br><br>
       <a href="NewlyOpenedTicketsController?action=NewlyOpenedTicketsList"><button class="button" style="vertical-align:middle"><span>Newly Opened Tickets View</span></button></a> &nbsp &nbsp &nbsp &nbsp 
	   <a href="NewlyOpenedTicketsControllerExcel?action=NewlyOpenedTicketsList"> <button class="button" style="vertical-align:middle"><span>Newly Opened Tickets Download</span></button></a>  <br><br><br>
    </div>
    <div class="modal-footer">
      <h3>Reports</h3>
    </div>
  </div>

</div>


<!-- Newly Opened HO -->
<!-- Trigger/Open The Modal -->
<!-- <button class="openmodal">Newly Opened HO </button>-->

<!-- The Modal -->
<div id="myModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
      <span class="close">&times;</span>
      <h2>Newly Opened HO</h2>
    </div>
    <div class="modal-body"><br><br>
       <a href="NewlyOpenedHOController?action=NewlyOpenedHOList"><button class="button" style="vertical-align:middle"><span>Newly Opened HO  View</span></button></a>&nbsp &nbsp &nbsp &nbsp
	   <a href="NewlyOpenedHOControllerExcel?action=NewlyOpenedHOList"><button class="button" style="vertical-align:middle"><span>Newly Opened HO Download </span></button></a> <br><br><br>
    </div>
    <div class="modal-footer">
      <h3>Reports</h3>
    </div>
  </div>

</div>


<!-- Reopened Tickets -->
<!-- Trigger/Open The Modal -->
<!-- <button class="openmodal">Reopened Tickets </button>-->

<!-- The Modal -->
<div id="myModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
      <span class="close">&times;</span>
      <h2>Reopened Tickets</h2>
    </div>
    <div class="modal-body"><br><br>
      <a href="ReopenedController?action=ReopenedList"><button class="button" style="vertical-align:middle"><span>Reopened Tickets View </span></button></a>&nbsp &nbsp &nbsp &nbsp
	  <a href="ReopenedControllerExcel?action=ReopenedList"><button class="button" style="vertical-align:middle"><span>Reopened Tickets Download</span></button></a> 	<br><br><br>  
    </div>
    <div class="modal-footer">
      <h3>Reports</h3>
    </div>
  </div>

</div>


<!-- Resolved By Ho -->
<!-- Trigger/Open The Modal -->
<!-- <button class="openmodal">Resolved By Ho </button><br>-->

<!-- The Modal -->
<div id="myModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
      <span class="close">&times;</span>
      <h2>Resolved By Ho</h2>
    </div>
    <div class="modal-body"><br><br>
		<a href="ResolvedByHoController?action=ResolvedByHoList"><button class="button" style="vertical-align:middle"><span>Resolved By Ho View </span></button></a>&nbsp &nbsp &nbsp &nbsp
		<a href="ResolvedByHoControllerExcel?action=ResolvedByHoList"><button class="button" style="vertical-align:middle"><span>Resolved By Ho Download </span></button></a> <br><br><br>
    </div>
    <div class="modal-footer">
      <h3>Reports</h3>
    </div>
  </div>

</div>

<!-- Resolved By Tickets -->
<!-- Trigger/Open The Modal -->
<!-- <button class="openmodal">Resolved By Tickets </button><br>-->

<!-- The Modal -->
<div id="myModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
      <span class="close">&times;</span>
      <h2>Resolved By Tickets</h2>
    </div>
    <div class="modal-body"><br><br>
      <a href="ResolvedByTicketsController?action=ResolvedByTicketsList"><button class="button" style="vertical-align:middle"><span>Resolved By Tickets View</span></button></a>&nbsp &nbsp &nbsp &nbsp
	  <a href="ResolvedByTicketsControllerExcel?action=ResolvedByTicketsList"><button class="button" style="vertical-align:middle"><span>Resolved By Tickets Download</span></button></a> <br><br><br>
    </div>
    <div class="modal-footer">
      <h3>Reports</h3>
    </div>
  </div>
</div>

<!-- Return After Clarifications -->
<!-- Trigger/Open The Modal -->
<!-- <button class="openmodal">Return After Clarification  </button><br><br>-->

<!-- The Modal -->
<div id="myModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
      <span class="close">&times;</span>
      <h2>Return After Clarifications</h2>
    </div>
    <div class="modal-body"><br><br>
      <a href="ReturnAfterClarificationController?action=ReturnAfterClarificationList"><button class="button" style="vertical-align:middle"><span>Return After Clarifications View</span></button></a>&nbsp &nbsp &nbsp &nbsp
	  <a href="ReturnAfterClarificationControllerExcel?action=ReturnAfterClarificationList"><button class="button" style="vertical-align:middle"><span>Return After Clarifications Download</span></button></a> <br><br><br>
    </div>
    <div class="modal-footer">
      <h3>Reports</h3>
    </div>
  </div>
</div>
<!-- Sent For Clarification -->
<!-- Trigger/Open The Modal -->
<!-- <button class="openmodal">Sent For Clarification  </button><br><br>-->

<!-- The Modal -->
<div id="myModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
      <span class="close">&times;</span>
      <h2>Sent For Clarification</h2>
    </div>
    <div class="modal-body"><br><br>
      <a href="SentForClarificationController?action=SentForClarificationList"><button class="button" style="vertical-align:middle"><span>Sent For Clarification View</span></button></a>&nbsp &nbsp &nbsp &nbsp 
	  <a href="SentForClarificationControllerExcel?action=SentForClarificationList"><button class="button" style="vertical-align:middle"><span>Sent For Clarification Download</span></button></a>  <br><br><br>
    </div>
    <div class="modal-footer">
      <h3>Reports</h3>
    </div>
  </div>
</div>
  
  
  <!-- Fresh Ticket queries -->
<!-- Trigger/Open The Modal -->
<!-- <button class="openmodal">Fresh Ticket queries </button><br><br>-->

<!-- The Modal -->
<div id="myModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
      <span class="close">&times;</span>
      <h2>Fresh Ticket queries</h2>
    </div>
    <div class="modal-body"><br><br>
        <a href="Reopen10To3pmController?action=Reopen10To3pmList"><button class="button" style="vertical-align:middle">Reopen 10 To 3 PM View</button></a>
		<a href="updated3pmQueryController?action=updated3pmQueryList"><button class="button" style="vertical-align:middle">Updated 3 PM Query View</button></a>
		<a href="updated10amQueryController?action=updated10amQueryList"><button class="button" style="vertical-align:middle">Updated 10 AM  Query View</button></a><br><br><br>
		
		<a href="Reopen10To3pmControllerExcel?action=Reopen10To3pmList"><button class="button" style="vertical-align:middle">Reopen 10 To 3 PM Download</button></a>
		<a href="updated3pmQueryControllerExcel?action=updated3pmQueryList"><button class="button" style="vertical-align:middle">Updated 3 PM Query Download</button></a>
		<a href="updated10amQueryControllerExcel?action=updated10amQueryList"><button class="button" style="vertical-align:middle">Updated 10 AM  Query Download</button></a>
		<br><br><br>
    </div>
    <div class="modal-footer">
      <h3>Reports</h3>
    </div>
  </div>
</div>
  
    <!-- Fresh Ticket queries -->
<!-- Trigger/Open The Modal -->
<!-- <button class="openmodal">3 to 6 </button><br><br>-->

<!-- <!-- The Modal -->
 <div id="myModal" class="modal">
  Modal content
  <div class="modal-content">
    <div class="modal-header">
      <span class="close">&times;</span>
      <h2>Modal Header</h2>
    </div>
    <div class="modal-body"><br><br>
	<div>
    <a href="Reopen3To6pmController?action=Reopen3To6pmList"><button class="button">Reopen 3 To 6 pm View</button></a>
	<a href="updated_3pm_OF_3To6Controller?action=updated_3pm_OF_3To6List"><button class="button" style="vertical-align:middle">updated 3PM View</button></a>
	<a href="updated_6pm_OF_3To6Controller?action=updated_6pm_OF_3To6List"><button class="button" style="vertical-align:middle">updated 6PM View</button></a><br><br><br>
	</div>
	<div>
	
	<a href="Reopen3To6pmControllerExcel?action=Reopen3To6pmList"><button class="button">Reopen 3 To 6 pm Download</button></a>
	<a href="updated_3pm_OF_3To6ControllerExcel?action=updated_3pm_OF_3To6List"><button class="button" style="vertical-align:middle">updated 3PM Download</button></a>
	<a href="updated_6pm_OF_3To6ControllerExcel?action=updated_6pm_OF_3To6List"><button class="button" style="vertical-align:middle">updated 6PM Download</button></a>
	</div>
	<br><br><br>	
	
    </div>
    <div class="modal-footer">
      <h3>Reports</h3>
    </div>
  </div>
</div> 
  
<!-- OF Weekly -->
<!-- Trigger/Open The Modal -->
<!-- <button class="openmodal">OF Weekly </button><br><br>-->
<div id="myModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
      <span class="close">&times;</span>
      <h2>Modal Header</h2>
    </div>
    <div class="modal-body">
			<br><br>
		<a href="ResolvedByHO_OFWeeklyController?action=ResolvedByHO_OFWeeklyList"><button class="button" style="vertical-align:middle">OFWeekly Resolved By HO View</button></a>
		<a href="ResolvedByHO_OFWeeklyControllerExcel?action=ResolvedByHO_OFWeeklyList"><button class="button" style="vertical-align:middle">OFWeekly Resolved By HO Download</button></a><br><br>
		
		<a href="ResolvedbyTCS_OFWeeklyController?action=ResolvedbyTCS_OFWeeklyList"><button class="button" style="vertical-align:middle">OFWeekly Resolved by TCS View</button></a>
		<a href="ResolvedbyTCS_OFWeeklyControllerExcel?action=ResolvedbyTCS_OFWeeklyList"><button class="button" style="vertical-align:middle">OFWeekly Resolved by TCS Download</button></a><br><br>
		
		<a href="SentForClarificationHO_NEW_OFWeeklyController?action=SentForClarificationHO_NEW_OFWeeklyList"><button class="button" style="vertical-align:middle">OFWeekly Sent For Clarification HO View</button></a>
		<a href="SentForClarificationHO_NEW_OFWeeklyControllerExcel?action=SentForClarificationHO_NEW_OFWeeklyList"><button class="button" style="vertical-align:middle">OFWeekly Sent For Clarification HO Download</button></a><br><br>
		
		
		<a href="SentForClarificationTCS_NEW_OFWeeklyController?action=SentForClarificationTCS_NEW_OFWeeklyList"><button class="button" style="vertical-align:middle">OFWeekly Sent For Clarification TCS View</button></a>
		<a href="SentForClarificationTCS_NEW_OFWeeklyControllerExcel?action=SentForClarificationTCS_NEW_OFWeeklyList"><button class="button" style="vertical-align:middle">OFWeekly Sent For Clarification TCS Download</button></a><br><br><br>
		
	</div>
    <div class="modal-footer">
      <h3>Reports</h3>
      
    </div>
  </div>
</div>

<!--<button class="button">OFWeekly Resolved By HO </button>-->
<!--<div id="myModal" class="modal">
  <!-- Modal content -->
<!--  <div class="modal-content">
    <div class="modal-header">
      <span class="close">&times;</span>
      <h2>Modal Header</h2>
    </div>
    <div class="modal-body"><br><br>
    <button class="button">Reopen 3 To 6 pm View</button>
	<button class="button">updated 3PM View</button>
	<button class="button">updated 6PM View</button><br><br><br>
	<button class="button">Reopen 3 To 6 pm Download</button>
	<button class="button">updated 3PM Download</button>
	<button class="button">updated 6PM Download</button><br><br><br>
    </div>
    <div class="modal-footer">
      <h3>Reports</h3>
    </div>
  </div>
</div>
//-->

<!-- java script file called   -->



<!-- <script type="text/javascript">
var modals = document.getElementsByClassName('modal');
// Get the button that opens the modal
var btns = document.getElementsByClassName("openmodal");
var spans=document.getElementsByClassName("close");
for(let i=0;i<btns.length;i++){
   btns[i].onclick = function() {
      modals[i].style.display = "block";
   }
}
for(let i=0;i<spans.length;i++){
    spans[i].onclick = function() {
       modals[i].style.display = "none";
    }
 }
 
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>
 -->






<!--
 <<button class="button" style="vertical-align:middle"><span><a href="AgingwithIPcontroller?action=List">Aging with IP  </a></span></button>  		
 <button class="button" style="vertical-align:middle"><span><a href="H1Querycontroller?action=H1List">H1 Query  </a></span></button>  
 <button class="button" style="vertical-align:middle"><span><a href="NewlyOpenedTicketsController?action=NewlyOpenedTicketsList">Newly Opened Tickets  </a></span></button>  <br><br>
 <button class="button" style="vertical-align:middle"><span><a href="NewlyOpenedHOController?action=NewlyOpenedHOList">Newly Opened HO  </a></span></button>  
 <button class="button" style="vertical-align:middle"><span><a href="ReopenedController?action=ReopenedList">Reopened Tickets  </a></span></button>  
 <button class="button" style="vertical-align:middle"><span><a href="ResolvedByHoController?action=ResolvedByHoList">Resolved By Ho  </a></span></button>  <br><br>
 <button class="button" style="vertical-align:middle"><span><a href="ResolvedByTicketsController?action=ResolvedByTicketsList">Resolved By Tickets  </a></span></button>  
 <button class="button" style="vertical-align:middle"><span><a href="ReturnAfterClarificationController?action=ReturnAfterClarificationList">Return After Clarification  </a></span></button>  
 <button class="button" style="vertical-align:middle"><span><a href="SentForClarificationController?action=SentForClarificationList">Sent For Clarification  </a></span></button>  <br><br>
 <button class="button" style="vertical-align:middle"><span><a href="FreshTicketqueries.jsp">Fresh Ticket queries</a></span></button>   
 <button class="button" style="vertical-align:middle"><span><a href="3to6.jsp">3 to 6</a></span></button>  
 <button class="button" style="vertical-align:middle"><span><a href="OFWeekly.jsp">OF Weekly</a></span></button>   
	

//-->

<br><br><br>
</center>
</div>
</div>
<div class="footer">
  <p>@Copyright Tcs Internal</p>
</div>

</body>
</html>