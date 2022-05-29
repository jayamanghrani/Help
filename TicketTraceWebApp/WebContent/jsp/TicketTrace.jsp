<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="ticketTraceWebApp.config.ReportConstants"%>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean"%>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic"%>
<bean:define id="TicketTraceResultsForm" name="TicketTraceResultsForm" type="ticketTraceWebApp.form.TicketTraceResultsForm"/>

<logic:present name="TicketTraceResultsForm" property="ticketTraceResultList" >
<bean:define id="ticketTraceResultList" name="TicketTraceResultsForm" property="ticketTraceResultList" type="java.util.ArrayList"/>
</logic:present>

<html>
  <head>
  <LINK rel="stylesheet" href="<%=request.getContextPath()%>/css/stdcss.css"  type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>TicketTraceSearch</title>
      <script language="javascript" type="text/javascript" src="javascript/datetimepicker.js"></script>
      
  
    <script type="text/javascript">
         
        function numbersCommaOnly(e, decimal) {
            var key;
            var keychar;
            if (window.event) { key = window.event.keyCode;}
            else if (e) {key = e.which;}
            else {return true;}
            keychar = String.fromCharCode(key);
            if ((key==null) || (key==0) || (key==8) ||  (key==9) || (key==13) || (key==27) ) { return true;}
            else if ((("0123456789").indexOf(keychar) > -1)) { return true;}
            else if (decimal && (keychar == ",")) { return true;}
            else
            return false;
        }
            
        function numbersOnly(evt){
            var charCode = (evt.which) ? evt.which : event.keyCode
            if(charCode!=13){ 
                if (charCode > 31 && (charCode < 48 || charCode > 57)){
                    return false;
                }
                }
                else if(charCode==13){
                }
                return true;
        }
             
    
        
        function differ(datestart,dateend){
            var datestart1 = document.getElementById(datestart);
            var dateend1 = document.getElementById(dateend);
            var t1 = datestart1.value;
            var t2 = dateend1.value;
            if(t1!='' && t2!=''){
                var one_day=1000*60*60*24; 
                var x=t1.split("/");     
                var y=t2.split("/");
                var date1=new Date(x[2],x[1]-1,x[0]);
                var date2=new Date(y[2],y[1]-1,y[0]);
                var diff_ms=date2.getTime()-date1.getTime();
                var dbd = Math.ceil((diff_ms)/(one_day)); 
                if(dbd>30){
                    alert("The difference between the dates must not be more than 30 days!");
                    //document.getElementById("toDate").value="";
                     document.getElementById(dateend).focus();
                    return false;
                }
                if(dbd >= 0 ){
                    return true;
                }
                else{
                    alert("To Date must be greater than From date !");
                    document.getElementById(dateend).value="";
                    document.getElementById(dateend).focus();
                    return false;
                }
            }
        }
            
        function getSysDate(){
            var currentTime = new Date();
            var month = currentTime.getMonth() + 1;
            var day = currentTime.getDate();
            var year = currentTime.getFullYear();
            var sysDate = day + "/" + month + "/" + year;
            return sysDate;
        }
            
             function submitForm(){
            
                   var objFormSubmit = document.getElementById('ticketSearch');
                   var objForm=document.ticketSearch.range;
                   var ticketnumber= document.getElementById("ticketno").value;
                   var ticketFrom= document.getElementById("ticketFrom").value;
                   var ticketTo= document.getElementById("ticketTo").value;
                   var startDate= document.getElementById("startDate").value;
                   var endDate= document.getElementById("endDate").value;
                   
                   var len = objForm.length;
                   
                    
                      for (i = 0; i <len; i++) {
                           if ( objForm[i].checked == true) {
                               if(i ==0 && ticketnumber ==""){
                               alert("Please Enter Ticket Number(s) ");
                               return false;
                               }
                                
                               var myString = new String (ticketnumber);
                               var myStringList = myString.split(',');
                             
                              if(i ==0 && ticketnumber !=""){
                             
                               for(k = 0; k < myStringList.length; k++){
                                   if(myStringList[k]==""){
                                    alert("Enter valid numbers separated by commas");
                                   return false;
                                   }
                                }
                               
                               
                               if(
                               (ticketnumber.charAt(0) == ",") ||
                               (ticketnumber.charAt(ticketnumber.length-1) == ",")
                               ){
                               alert("Enter valid numbers separated by commas");
                               return false;
                               }     
                            }   
                           }
                           if ( objForm[i].checked == true) {
                               if(i ==1 && ticketFrom == "" && ticketTo == ""){
                               alert("Please enter the Ticket Range.");
                               return false;
                               }
                               ticketfrom = ticketFrom;
                               ticketto =  ticketTo;
                               
                               if(ticketto == ""){
                                      if(ticketfrom != ""){
                                              alert("Please enter the Ticket No in the 'Ticket To' field.");
                                              return false;
                                      }
                               }
                                              
                               if(ticketfrom == ""){
                                      if(ticketto != ""){
                                              alert("Please enter the Ticket No in the 'Ticket From' field.");
                                              return false;
                                      }
                               }
                               
                                if(ticketFrom!='' && ticketTo!=''){
                                var diff = ticketTo - ticketFrom;
                                    if(diff < 0 ){
                                      alert("Ticket From' value cannot be greater than 'Ticket To' value!");
                                        document.getElementById("ticketTo").value="";
                                        document.getElementById("ticketTo").focus();
                                        return false;
                                    }
                                    
                                    a = ticketfrom *1;
                                    b = ticketto * 1;
                                    c = b-a;
                                    if(c > 25){
                                        alert("Difference between Ticket From and Ticket To should not exceed 25.");
                                              return false;
                                }
                                }
                              
                                
                           }
                           if ( objForm[i].checked == true) {
                          
                               if(i==2 && startDate == "" && endDate == "" ){
                               alert("Please enter the Date Range.");
                               return false;
                               }
                              
                               if(endDate == ""){
                                      if(startDate != ""){
                                              alert("Please enter the date in the 'Date To' field.");
                                              return false;
                                      }
                               }
                                              
                               if(startDate == ""){
                                      if(endDate != ""){
                                              alert("Please enter the date in the 'Date From' field.");
                                              return false;
                                      }
                               }
                               
                                var startDate= document.getElementById("startDate");
                                var endDate= document.getElementById("endDate"); 
                                //var RegExPattern = /^((((0?[1-9]|[12]\d|3[01])[\/](0?[13578]|1[02])[\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|30)[\/](0?[13456789]|1[012])[\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[\/]0?2[\/]((1[6-9]|[2-9]\d)?\d{2}))|(29[\/]0?2[\/]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$/; dd/mm/yy format
                                var RegExPattern=/^(((((0[1-9])|(1\d)|(2[0-8]))\/((0[1-9])|(1[0-2])))|((31\/((0[13578])|(1[02])))|((29|30)\/((0[1,3-9])|(1[0-2])))))\/((20[0-9][0-9])|(19[0-9][0-9])))|((29\/02\/(19|20)(([02468][048])|([13579][26]))))$/; /* DD/MM/YYYY FORMAT  */
                                if(i==2 && (endDate != '' || startDate != '')){
                                     if (!((startDate.value.match(RegExPattern)) && (startDate.value!=''))) {
                                        alert('Please enter valid date in DD/MM/YYYY format!');
                                         startDate.value="";
                                        startDate.focus();
                                        return false;
                                        }    
                                      
                                     if (!((endDate.value.match(RegExPattern)) && (endDate.value!=''))) {
                                        alert('Please enter valid date in DD/MM/YYYY format!');
                                        endDate.value="";
                                        endDate.focus();
                                        return false;
                                        } 
                                  
                               }
                                var t1 = startDate.value;
                                var t2 = endDate.value;
                                if(t1!='' && t2!=''){
                                var one_day=1000*60*60*24; 
                                var x=t1.split("/");     
                                var y=t2.split("/");
                                var date1=new Date(x[2],x[1]-1,x[0]);
                                var date2=new Date(y[2],y[1]-1,y[0]);
                                var diff_ms=date2.getTime()-date1.getTime();
                                var dbd = Math.ceil((diff_ms)/(one_day)); 
                                
                                var sysDate = getSysDate();
                                var t3 =   sysDate;
                                var z = t3.split("/");
                                var date3=   new Date(z[2],z[1]-1,z[0]);
                                var diff_ms1=date3.getTime()-date2.getTime();
                                var dbd1 = Math.ceil((diff_ms1)/(one_day)); 
                                
                                      if(dbd1 < 0 ){
                                        alert("To date should not be greater than Todays Date !");
                                        document.getElementById("endDate").value="";
                                        document.getElementById("endDate").focus();
                                        return false;
                                    }
                                    
                                    if(dbd>=30){
                                        alert("The difference between the dates must not be more than 30 days!");
                                         document.getElementById("endDate").value="";
                                         document.getElementById("endDate").focus();
                                        return false;
                                    }
                                    
                                    if(dbd < 0 ){
                                        alert("To Date must be greater than From date !");
                                        document.getElementById("endDate").value="";
                                        document.getElementById("endDate").focus();
                                        return false;
                                    }
                                }
                           }
                   }
                  
                   document.getElementById("ticketTraceResults").style.display ="none";
                   document.getElementById("tabPageExport").style.display ="none";
                   objFormSubmit.action='<%=request.getContextPath()%>'+"/TicketTrace.do"+ "?submitButton=getTicketTraceReport";   
                   objFormSubmit.submit();
                  
               //    self.close();
               
               disable();
               
                   return true;  
                 
               }
               
             function disable()
             {
                   var ticketnumber= document.getElementById("ticketno").value;
                   var ticketFrom= document.getElementById("ticketFrom").value;
                   var ticketTo= document.getElementById("ticketTo").value;
                   var startDate= document.getElementById("startDate").value;
                   var endDate= document.getElementById("endDate").value;
                   //alert ("ticketnumber"+ticketnumber);
                   if((ticketnumber!="" && ticketFrom=="" && ticketTo=="" && startDate=="" && endDate=="")||(ticketnumber=="" && ticketFrom!="" && ticketTo!="" && startDate=="" && endDate=="")||(ticketnumber=="" && ticketFrom=="" && ticketTo=="" && startDate!="" && endDate!=""))
                   {
                   //alert ("inside value");
                   document.getElementById("Submit1").disabled =true;
                   
                   }
                   else
                   {
                   //alert ("inside blank");
                   document.getElementById("Submit1").disabled =false;
                   }
             }
               


        
        function cmdHelp_onclick() {
            window.open("jsp/help.html","","","");
            return true;
        }  
        
        function resetForm() {
             var objForm = document.getElementById('ticketSearch');
                objForm.action='<%=request.getContextPath()%>'+"/TicketTrace.do"+ "?submitButton=reset";    
                objForm.submit();  
                return true;
        }  
            
        function chkDisplayResults() {
    
    var chkRadio = '<%=session.getAttribute("RADIO")%>';  
          var objForm=document.ticketSearch.range;
           objForm[chkRadio].checked = true;
            document.getElementById("ticketTraceResults").style.display ="block";
           
        } 
        function chkDisplayLinks() {
           
            document.getElementById("tabPageExport").style.display ="block";
        } 
        
        function getPage(act){
            objForm = document.getElementById('ticketSearch');
            objForm.action='<%=request.getContextPath()%>'+"/TicketTrace.do"+ "?submitButton="+act;   
            objForm.submit();
            return true;  
        }
        
        function editable() 
            {
                document.getElementById("ticketno").value="";
                document.getElementById("ticketFrom").value="";
                document.getElementById("ticketTo").value="";
                document.getElementById("startDate").value="";
                document.getElementById("endDate").value="";
                document.getElementById("ticketno").disabled=false;
                document.getElementById("Submit1").disabled =false;
                document.getElementById("ticketFrom").disabled =true;
                document.getElementById("ticketTo").disabled = true;
                document.getElementById("startDate").disabled = true;
                document.getElementById("endDate").disabled = true;
                 document.getElementById("cmdFromCalendar").disabled = true;
                document.getElementById("cmdToCalendar").disabled = true;
                
                //document.ticketSearch.ticketno.readonly = false;
                return true;
            } 
            
            function editavlefromto()
            {
                document.getElementById("ticketno").value="";
                document.getElementById("ticketFrom").value="";
                document.getElementById("ticketTo").value="";
                document.getElementById("startDate").value="";
                document.getElementById("endDate").value="";
                document.getElementById("ticketFrom").disabled = false;
                document.getElementById("ticketTo").disabled = false;
                document.getElementById("Submit1").disabled =false;
                document.getElementById("ticketno").disabled=true;
                document.getElementById("startDate").disabled = true;
                document.getElementById("endDate").disabled = true;
                document.getElementById("cmdFromCalendar").disabled = true;
                document.getElementById("cmdToCalendar").disabled = true;
             return true;
            } 
            function editavledatefromto()
            {
                document.getElementById("ticketno").value="";
                document.getElementById("ticketFrom").value="";
                document.getElementById("ticketTo").value="";
                document.getElementById("startDate").value="";
                document.getElementById("endDate").value="";
                document.getElementById("startDate").disabled = false;
                document.getElementById("endDate").disabled = false;
                document.getElementById("Submit1").disabled =false;
                document.getElementById("cmdFromCalendar").disabled = false;
                document.getElementById("cmdToCalendar").disabled = false;
                document.getElementById("ticketno").disabled=true;
                document.getElementById("ticketFrom").disabled =true;
                document.getElementById("ticketTo").disabled = true;
                //alert('Select from and to date from calender');
             return true;
            } 
            
              function exportPDFDocument(){
                    var objFormSubmit = document.getElementById('ticketSearch');
                    objFormSubmit.action='<%=request.getContextPath()%>'+"/TicketTrace.do"+ "?submitButton=generateexportpdf";   
                    objFormSubmit.submit();
                    return true;  
               
               }
            
            
               function exportWordDocument(){
                    var objFormSubmit = document.getElementById('ticketSearch');
                    objFormSubmit.action='<%=request.getContextPath()%>'+"/TicketTrace.do"+ "?submitButton=generateexportWord";   
                    objFormSubmit.submit();
                    return true;  
               
               }
               
    function noCopyMouse(e) 
        {
          if (event.button==2)//RIGHT CLICK
           {
             alert("Not allow right click!");
            }
         return false;
         }
        
      

    function noCopyKey(e) {
        var forbiddenKeys = new Array('c','x','v');
        var keyCode = (e.keyCode) ? e.keyCode : e.which;
        var isCtrl;

        if(window.event)
            isCtrl = e.ctrlKey
        else
            isCtrl = (window.Event) ? ((e.modifiers & Event.CTRL_MASK) == Event.CTRL_MASK) : false;
    
        if(isCtrl) {
            for(i = 0; i < forbiddenKeys.length; i++) {
                if(forbiddenKeys[i] == String.fromCharCode(keyCode).toLowerCase()) {
                    //alert('You are prompted to type this twice for a reason!');
                    return false;
                }
            }
        }
        return true;
    }
               
               
               
    
   </script>
    </head>
  
<body style="margin:0"  >
<form name="ticketSearch" id="ticketSearch" action="<%=request.getContextPath()%>/TicketTrace.do" method="POST">
 
<table bgcolor="#226FB3" width="100%" cellspacing=0 cellpadding=0><tr><td>
<img  src="images/logo.jpg" alt="logo" width="75" height="75"></td>
<td align="center" width="1024"><font size="4" color="white"><b>TICKET TRACE REPORT</b></font></td></tr></table>

 
   <table align="CENTER" border="1" bgcolor="#EBF0F5">
   <tr>
   <td>
     <fieldset class="fieldset_whole_width"  >
     <legend align="left">Ticket Search</legend>
        <table align="CENTER" cellpadding="5" cellspacing="5" >
        <tr align="CENTER">
             <tr class="label1">
             <td align="left" ><input type="radio" id="TicketNumber"  name="range" onclick="editable();"   />Ticket No &nbsp;&nbsp;&nbsp;
             <input id="ticketno" name="ticketno" type="textbox" disabled="disabled" title="Enter the ticket number in comma separated value" class="textbox_medium" value="<%=TicketTraceResultsForm.getTicketno()%>"   onkeypress="return numbersCommaOnly(event, true);" onkeydown="return noCopyKey(event);" onmousedown="return noCopyMouse(event);"></input></td>
        </tr>
             
         <tr class="label1">
             <td align="left"><input type="radio" id="TicketRange" name="range" onclick="editavlefromto();"   />Ticket From  
            <input id="ticketFrom" name="ticketFrom" type="textbox" class="textbox_medium" disabled="disabled" title="Enter the ticket from Value " value="<%=TicketTraceResultsForm.getTicketFrom()%>"  onkeypress="return numbersOnly(event);" onkeydown="return noCopyKey(event);" onmousedown="return noCopyMouse(event);"></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ticket To&nbsp;&nbsp;
             <input id="ticketTo" name="ticketTo" type="textbox" class="textbox_medium" disabled="disabled" title="Enter the ticket to Value " value="<%=TicketTraceResultsForm.getTicketTo()%>"  onkeypress="return numbersOnly(event);" onkeydown="return noCopyKey(event);" onmousedown="return noCopyMouse(event);"></input></td>
          </tr>
             
           <tr class="label1">
           <td align="left"> <input type="radio" id="DateRange" name="range" onclick="editavledatefromto();" />From Date &nbsp;
              <input type="textbox" id="startDate"  class="textbox_medium" name="startDate" value="<%=TicketTraceResultsForm.getStartDate()%>" disabled="disabled" title="Enter the  from date " />
              <input  id="cmdFromCalendar" type="button" class="formButton_report" title="Select the from Date" style="WIDTH: 23px; HEIGHT: 22px" onclick="javascript: show_cal('document.ticketSearch.startDate.value')" value="..." disabled="disabled" /> To Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="textbox" id="endDate"  class="textbox_medium" name="endDate" value="<%=TicketTraceResultsForm.getEndDate()%>" disabled="disabled" title="Enter the  to date "/>
              <input  id="cmdToCalendar" type="button"  class="formButton_report" title="Select the to Date" style="WIDTH: 23px; HEIGHT: 22px" onclick="javascript: show_cal('document.ticketSearch.endDate.value')" value="..."  disabled="disabled" />
              
              </td>
              </tr>
         <tr align="CENTER">
                <td >
                 <input type="button" class="formButton_report" value="Submit" id="Submit1" disabled="disabled"  title="Search the ticket" onclick="submitForm();">
                
                 <input type="button" class="formButton_report" value="Reset" id="Reset1" title="Reset the values" onclick="resetForm();">
                
                 <input type="button" class="formButton_report" value="Help" id="Help1" title="Help fot ticket trace" onclick="cmdHelp_onclick();">
                </td>
            </tr>      
           
    </table>
    </fieldset>
    <table id="tabPageExport" width="950" style="display:none">
    <tr>
    <td>Pages
     &nbsp;&nbsp;<a href=#here onclick="getPage('getFirstPage');" ><img class="clickable_span" src="images/firstpage.png" alt="First Page" /></a>&nbsp;
     &nbsp;<a href=#here onclick="getPage('getPreviousPage');" ><img class="clickable_span" src="images/prevpage.png" alt="Prev Page" /></a>&nbsp;
     &nbsp;<a href=#here onclick="getPage('getNextPage');"><img class="clickable_span" src="images/nextpage.png" alt="Next Page" /></a>&nbsp;
      &nbsp;<a href=#here onclick="getPage('getLastPage');"><img class="clickable_span" src="images/lastpage.png" alt="Last Page" /></a>
    </td>
    <td align="right">Export in
    <a id="exportPDF" href="#here" onclick="exportPDFDocument();">PDF</a>&nbsp;
    <a id="exportWord" href="#here" onclick="exportWordDocument();">Word</a></td>
    </tr></table>
    
    
    </td></tr>
    </table>
   
   <div id="ticketTraceResults" style="width:100%;overflow-x:hidden;overflow-y:hidden" >  
   <table width="100%" align="center">
  
    <tr>
    <td align="center">
    <logic:present name="TicketTraceResultsForm" property="ticketTraceResultList">   
     <fieldset class="fieldset_whole_width" >
    <legend >Ticket Trace </legend>
    <table >
        <tr>
        <td align=right>
                                
     <%
        int count=1;
        int counter=TicketTraceResultsForm.getCounter();
        int size=TicketTraceResultsForm.getTicketTraceResultList().size();
        int pages=size;
        int lastPage=size;
        if(pages==0 || lastPage!=0){
        pages=pages+1;
        }%>
    
     </td></tr>
     <tr><td>
    <div id="searchResult" style="width:100%;height:100%;overflow-x:hidden;overflow-y:hidden">
    <table border="0" cellpadding="1" cellspacing="1" style="width:100%; table-layout:fixed">
   

    <logic:notEmpty name="TicketTraceResultsForm" property="ticketTraceResultList">
    <logic:iterate  name="ticketTraceResultList" id="TicketTraceVO"  indexId="sno"  type="ticketTraceWebApp.vo.TicketTraceVO">
        <%
        if(count >= counter && count < counter+1){%>
        
        <th align="center" colspan="5"  ><h4 align="center">Ticket Trace Report - &nbsp;&nbsp;<bean:write name="TicketTraceVO" property="callid"/></h4></th>
        <tr class="label1"><td align="left"><u>CompanyName</u></td><td>NIA</td></tr>
        <tr><td>&nbsp;</td></tr> 
        <tr class="label1"><td align="left"><u>Location</u></td><td><bean:write name="TicketTraceVO" property="location"/></td></tr>
        <tr><td>&nbsp;</td></tr> 
        <tr class="label1"><td align="left"><b><u><i>Call Description : </i></u></b></td></tr>
        <tr><td>&nbsp;</td></tr> 
        <tr class="label1"><th><u>Callid</u></th><th><u>Department</u></th><th><u>Status</u></th><th><u>Ticket Log Date</u></th><th><u>Priority</u></th><th><u>OfficeCode</u></th></tr>
        <tr class="label1"><td align="left"><bean:write name="TicketTraceVO" property="callid"/></td>
        <td align="left"><bean:write name="TicketTraceVO" property="department"/></td>
        <td align="left"><bean:write name="TicketTraceVO" property="ticketStatus"/></td>
        <td align="left"><bean:write name="TicketTraceVO" property="ticketLoggedDate"/></td>
        <td align="left"><bean:write name="TicketTraceVO" property="priority"/></td>
        <td align="left"><bean:write name="TicketTraceVO" property="officeCode"/></td>
        </tr>
        <tr><td>&nbsp;</td></tr> 
        <tr class="label1"><th><u>Ticket Logged By - User id </u></th><th colspan="2" ><u>Ticket Logged By - User Name </u></th></tr>
        <tr class="label1">
        <td align="left"><bean:write name="TicketTraceVO" property="userId"/></td>
        <td align="left"><bean:write name="TicketTraceVO" property="userName"/></td>
        </tr>
        <tr><td>&nbsp;</td></tr> 
        <tr class="label1"><td align="left"><b><u><i>Problem Description : </i></u></b></td></tr>
        <tr class="label1"><td align="left" colspan="5"><p><bean:write name="TicketTraceVO" property="problemDescription"/></p></td></tr>
         <tr><td>&nbsp;</td></tr>        
        <tr><td><b><u><i>Activity Description : </i></u></b></td></tr>
        <tr><td>&nbsp;</td></tr> 
        <tr class="label1"><td align="left" colspan="5">
        
        <logic:present name="TicketTraceVO" property="activityList" >
        <bean:define id="activityList" name="TicketTraceVO" property="activityList" type="java.util.ArrayList"/>
        </logic:present>
        <logic:notEmpty name="TicketTraceVO" property="activityList">
        <table  cellpadding="0" cellspacing="0" style="width:95%; table-layout:fixed" align="left">
            <tr class="label">
                <td width="5%"></td>
                <td width="10%"></td>
                <td width="10%"></td>
                <td width="10%"></td>
            </tr>  
            <tr class="label">
                <th><u>Seq No:</u></th>
                <th><u>Activity Date</u></th>
                <th><u>Person Responsible</u></th>
                <th><u>Activity log Description</u></th>
            </tr>
            
            <logic:iterate  name="activityList" id="ActivityVO"   type="ticketTraceWebApp.vo.ActivityVO">                                         
            <tr class="label">
                <td><bean:write name="ActivityVO" property="seqNosForActivities"/></td>
                <td><bean:write name="ActivityVO" property="activityLoggedDate"/></td>
                <td><bean:write name="ActivityVO" property="personResponsibleName"/></td>
                <td><bean:write name="ActivityVO" property="activityLogDescription"/></td>
        
            </tr>
            <tr><td>&nbsp;</td></tr> 
            </logic:iterate>
            
        </table>
        </logic:notEmpty>
        
        <logic:empty name="TicketTraceVO" property="activityList">
            <a>No Records.</a>
        </logic:empty>
        
       
        </td></tr>
       <tr class="label1"><td align="left" colspan="5" ><u>TicketSolveDate</u>&nbsp;&nbsp;<bean:write name="TicketTraceVO" property="ticketSolveDate"/></td></tr>
        <tr><td>&nbsp;</td></tr> 
        <tr class="label1"><td align="left" colspan="5"><u>Solution</u>&nbsp;&nbsp;<bean:write name="TicketTraceVO" property="solution"/></td></tr>
       
    
    <%count++;
    }else{
    count++;
    }%>
  
  </logic:iterate>
  </logic:notEmpty>
   <logic:empty name="TicketTraceResultsForm" property="ticketTraceResultList">
    <p><font color="red"> No Records Found </font></p>
   </logic:empty>
   </table>
   </div>
    </td></tr></table>
</fieldset>
  </logic:present>
  
  
  </tr>
  </table> 
 </div> 

  </form>
  <logic:equal value="DISPLAYRESULTS" name="DISPLAYRESULTS" >
        <script language="JavaScript" type="text/javascript">
            chkDisplayResults();   
        </script>
    </logic:equal>
     <logic:equal value="DISPLAYLINKS" name="DISPLAYLINKS" >
        <script language="JavaScript" type="text/javascript">
            chkDisplayLinks();   
        </script>
    </logic:equal>
  </body>
</html>