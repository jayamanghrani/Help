
var responseData;
var webserviceResponse;
var webserviceResponsetwo;
$.fn.doAjaxCall = function(where,what){ 
		$.ajax({
		type: "POST",
		url: where,
		dataType: "json",
		contentType: "application/json",
		data: what,
		success: function (data) {
			webserviceResponse=data;
			
		}
	});
};


function formattedDate(date) {
    var d = new Date(date || Date.now()),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [day, month, year].join('/');
    
}

function formattedDateNew(date) {
	
	var part1=date.substring(0,10);
	var part2=date.substring(11,19);
	var newDate=part1.split("/")[2]+"-";
	newDate = newDate+part1.split("/")[1]+"-";
	newDate=newDate+part1.split("/")[0];

	var newDate2=newDate+"T"+part2;
	
    var d = new Date(newDate2 || Date.now());

    return d;
}

$.fn.populate = function(){
	
	var monthSubs={'01':'Jan','02':'Feb','03':'Mar','04':'Apr','05':'May','06':'Jun','07':'Jul','08':'Aug','09':'Sep','10':'Oct','11':'Nov','12':'Dec'};
	
	var date=webserviceResponse.header.todaysDate;
    $('#signedUser').append('Welcome  ' + webserviceResponse.header.employeeName + ' ( ' +webserviceResponse.header.employeeId+ ' )');
    $('#signedusername').append(webserviceResponse.header.employeeName); 

	var d3=date.split("/")[0]+" ";
	d3 = d3+monthSubs[date.split("/")[1]]+" ";
	d3=d3+date.split("/")[2];
	
    $('#dateheader').append(d3.substring(0,11));
    $('#docuploaddate').val(date.substring(0,11));
    $('#docuploaddateto').val(date.substring(0,11));
    $('#docuploaddatefrom').val("17/12/2016"); 
    //$('#docpublishdateto').val(date.substring(0,11));
   // $('#docpublishdatefrom').val("17/12/2016");

};


$.fn.searchView = function(){
	
	var testStr=webserviceResponse.header.groups;
	 if(testStr.indexOf("docadmin") != -1){
	   $(".visiblilityUpload").show();
	 }
	else
	{
	 $(".visiblilityUpload").hide();	
	 $("#searchclick").trigger('click'); 
	}
};



function dateDiffFinder_Days(d1, d2){
	
	var monthSubs={'Jan':'1','Feb':'02','Mar':'3','Apr':'4','May':'5','Jun':'6','Jul':'7','Aug':'8','Sep':'9','Oct':'10','Nov':'11','Dec':'12'};

	var d3=d1.split(" ")[2]+", ";
	d3 = d3+monthSubs[d1.split(" ")[1]]+", ";
	d3=d3+d1.split(" ")[0];

	var d4=d2.split("/")[2]+", ";
	d4=d4+d2.split("/")[1]+", ";
	d4=d4+d2.split("/")[0];
	
	d1 = new Date(d3);
	d2 = new Date(d4);
	var diff = d1 - d2;
	return (diff / 1000 / 60 / 60 / 24);
		
} 

function compare(d1, d2){

	var d3=d1.split("/")[2]+", ";
	d3=d3+d1.split("/")[1]+", ";
	d3=d3+d1.split("/")[0]; 
	
	var d4=d2.split("/")[2]+", ";
	d4=d4+d2.split("/")[1]+", ";
	d4=d4+d2.split("/")[0];
	
	d1 = new Date(d3);
	d2 = new Date(d4);
	var diff = d1 - d2;
	return (diff / 1000 / 60 / 60 / 24);
}

$.fn.pwdDetails = function(){
	var monthSubs={'01':'Jan','02':'Feb','03':'Mar','04':'Apr','05':'May','06':'Jun','07':'Jul','08':'Aug','09':'Sep','10':'Oct','11':'Nov','12':'Dec'};
	
	var date=webserviceResponse.header.todaysDate;

	var d3=date.split("/")[0]+" ";
	d3 = d3+monthSubs[date.split("/")[1]]+" ";
	d3=d3+date.split("/")[2];
	
    
	var lastLogin=webserviceResponse.lastLoginDt;
	var pwdExpiryDt=webserviceResponse.pwdExpiryDt;
	$('#pwdDetails').append('<div class="pull-left margin-l-10">Last Login on '+lastLogin+'</div><div class="pull-right">Password expires on '+pwdExpiryDt+'</div>');
	$('#signedUserName').append('Welcome  ' + webserviceResponse.header.employeeName + ' ( ' +webserviceResponse.header.employeeId+ ' )');
    $('#signeduser').append(webserviceResponse.header.employeeName); 
    $('#todaysDate').append(d3.substring(0,11)); 
    $.fn.populate();
	$.fn.searchView();
};

$.fn.getPwdDetailsCall = function(){
		$.ajax({
		type: "POST",
		url:getPwdDetailsCallURL ,
		dataType: "json",
		contentType: "application/json",
		data: {},
		success: function (data) {
			webserviceResponse=data;
			$.fn.pwdDetails();
		 },
	 error: function() {
			//alert("Failure in getPwdDetails Webservice");
		 }
	 });
};


$.fn.logOut = function(){
	$.ajax({
		type: "POST",
		url:logOutSecondUrl,
		dataType: "json",
		contentType: "application/json",
		data: {},
		success: function (data) {
			 console.log("inside logOut call");
			 $(location).attr('href', logoutURL);
			 
		 },
	 error: function() {
		 $(location).attr('href', logoutURL);
		 }
	 });
}; 

function getURLParams_GET() {
	 return window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');

	}

$.fn.departmentName = function(){
	
	$.ajax({
		type: "POST",
        url:getDeptName,
        datatype: 'json',
        contentType: "application/json",
        data: "{\"listDepartments\":\"listdepartmnet\"}",
        success: function(data){
        	webserviceResponsetwo=data;
        	
        	var json_obj = webserviceResponsetwo;
        	
        	$.each(json_obj.getDepartmentNameList, function (i, item) {
        		
        	    $('#uploaddepartmentname').append($('<option>', { 
        	        value: item,
        	        text : item 
        	    }));
        	    
        	});
			   }, error: function() {
					//alert("Failure in departmentname Webservice");
				 }
        });
	};
	

/* Added for new document store portal Starts*/
	
	$.fn.departmentNameVisibleTo = function(){
		$.ajax({
			type: "POST",
	        url:getViewDept,
	        datatype: 'json',
	        contentType: "application/json",
	        data: "{\"listDepartments\":\"listdepartmnet\"}",
	        success: function(data){
	        	webserviceResponsetwo=data;
	        	var json_obj = webserviceResponsetwo;
	        	$.each(json_obj.getDepartmentNameList, function (i, item) {
	        	    $('#uploaddepartmentnameVisibleTo').append($('<option>', { 
	        	        value: item,
	        	        text : item 
	        	    }));
	        	    
	        	       	    
	        	    $('#searchdepartmentnameVisibleTo').append($('<option>', { 
	        	        value: item,
	        	        text : item 
	        	    }));
	        	    
	        	    
	        	});
				   }, error: function() {
						//alert("Failure in departmentname Webservice");
					 }
	        });
		};
		
		
		
		$.fn.departmentNameSearchTo = function(){
			$.ajax({
				type: "POST",
		        url:getSearchDept,
		        datatype: 'json',
		        contentType: "application/json",
		        data: "{\"listDepartments\":\"listdepartmnet\"}",
		        success: function(data){
		        	webserviceResponsetwo=data;
		        	var json_obj = webserviceResponsetwo;
		        	$.each(json_obj.getDepartmentNameList, function (i, item) {
		        		
		        		$('#searchdepartmentname').append($('<option>', { 
		        	        value: item,
		        	        text : item 
		        	    }));
		        	    
		        		  /****Code added for old docs****/
		        		
		        	    $('#searchdepartmentname_old').append($('<option>', { 
		        	        value: item,
		        	        text : item 
		        	    }));
		        	    /****Code added for old docs****/
		        	});
					   }, error: function() {
							//alert("Failure in departmentname Webservice");
						 }
		        });
			};
			
/* Added for new document store portal Ends*/
			
			
$.fn.passwordChange = function(){
	if(webserviceResponse.statusCode=="0"){
		$('#changeMessages').append('<font color="green">'+webserviceResponse.statusMessage+'</font>');
		$("#oldPassword").val("");
		$("#newPassword").val("");
		$("#confirmPassword").val("");
		$('#passwordSubmit').prop('disabled', true);
		$('#passwordSubmit').addClass( "grey" );
	}else{
		$('#changeMessages').append('<font color="red">'+webserviceResponse.statusMessage+'</font>');
		$("#oldPassword").val("");
		$("#newPassword").val("");
		$("#confirmPassword").val("");
		$('#passwordSubmit').prop('disabled', true);	
		$('#passwordSubmit').addClass( "grey" );
	}
};

$.fn.changePassword = function(){
	$.ajax({
		type: "POST",
		url:changePassworURL,
		dataType: "json",
		contentType: "application/json",
		data: "{\"flag\":\"Y\",\"oldPassword\":\""+$('#oldPassword').val()+"\",\"newPassword\":\""+$('#newPassword').val()+"\",\"userId\":\""+responseData.header.employeeId+"\"}",
		success: function (data) {
			webserviceResponse=data;
			$.fn.passwordChange();
		 },
	 error: function() {
			//alert("Failure in Change Password Webservice");
		 }
	 });
};


$("#passwordSubmit").on("click", function() {
$.fn.changePassword();
$("#changeMessages").empty();
    		 	
});

$("#passwordCancel").on("click", function() {
	window.location.href='/EmployeePortalNIA/home.html';
});



$.fn.displaysearchresults = function(){
	$('#displayResults').empty();
	for(var i=webserviceResponse.contentDataList.length-1; i >= 0;  --i)  {
  	
		$('#displayResults').append('<tr class="searchList"> <td class="table_style2">' +webserviceResponse.contentDataList[i].uploadDate+'</td><td class="table_style2">' +webserviceResponse.contentDataList[i].publishDate+'</td><td class="table_style2">' +webserviceResponse.contentDataList[i].departmentName+'</td><td class="table_style2">'+webserviceResponse.contentDataList[i].description+'</td><td class="table_style2">'+webserviceResponse.contentDataList[i].docName+'</td><td class="table_style2"><a  href="'+webserviceResponse.contentDataList[i].docUrl+'" class="searchLink underline" id="docName'+i+'" target="_blank">View/Download</a></td> </tr>');
		
	} 
	$('#fourth-container').simplePagination({
	   	first_content: '&lt;&lt;',
	   	previous_content: '<',
	   	next_content: '>',
	   	last_content: '>>',
	   	items_per_page: 25
	   });
};



$.fn.documentSearch = function(){
	$('#loading_ajax').show();
	$.ajax({
		type: "POST",
		url:searchDocumentURL,
		dataType: "json",
		contentType: "application/json",
		data:"{\"alfrescoInput\" :{\"typeOfContent\":\"EmployeeDocument\", \"channel\":\"[EMPLOYEE]\",\"matchMethod\":\""+$('input[name=method]:checked','#docSearchForm').val()+"\",\"docName\":\""+$('#searchfilename').val()+"\",\"deptName\":\""+$('#searchdepartmentname option:selected').val()+"\",\"docType\":\""+$('#searchdocumenttype option:selected').val()+"\",\"issuedBy\":\""+$('#issuedBy option:selected').val()+"\",\"searchbytype\":\""+$('input[name=search]:checked','#docSearchForm').val()+"\",\"docuploaddatestart\":\""+$('#docuploaddatefrom').val()+"\",\"docuploaddateend\":\""+$('#docuploaddateto').val()+"\",\"docpublishdatestart\":\""+$('#docpublishdatefrom').val()+"\",\"docpublishdateend\":\""+$('#docpublishdateto').val()+"\"}}",
		
		success: function (data) {
			webserviceResponse=data;
			for(var i=0; i < 1; i++) {
			
				if(webserviceResponse.errorCode!=null)
				{
				$('#loading_ajax').hide();
				setTimeout(function(){ alert(webserviceResponse.errorMessage); }, 100);
				}
			if(webserviceResponse.contentDataList[i].docName==""||webserviceResponse.contentDataList[i].docName==null){
				$('#loading_ajax').hide();
				$('#searchview').hide();
				 $('#displayResultsNull').empty();
				 $('#searchviewNull').show();
					$('#displayResultsNull').append('<th style="width:900px;color:red">' +webserviceResponse.contentDataList[i].statusOfDocSearch+'</th>');
				/*alert(webserviceResponse.contentDataList[i].statusOfDocSearch);*/
			    }else
				{
			    	$('#loading_ajax').hide();
			    $('#searchviewNull').hide();
			    $('#searchview').show();
			    $.fn.displaysearchresults();
			    
				}
		
			}
		 },
	  error: 
			 function (jqXHR, timeout, message) {
			 if(jqXHR.status===500)
				 {
				 alert(message);
				 }else{
					 alert("Session Timed Out.Kindly click OK to relogin to Document Store.");
					 $.fn.logOut();
				 }

		 }
	 });
};



/****Code added for old docs****/
	
$.fn.displaysearchresults_old = function(){
	$('#displayResults_old').empty();
	for(var i=webserviceResponse.contentDataList.length-1; i >= 0;  --i)  {
  	
		$('#displayResults_old').append('<tr class="searchList"><td class="table_style2">' +webserviceResponse.contentDataList[i].uploadDate+'</td><td class="table_style2">' +webserviceResponse.contentDataList[i].deptName+'</td><td class="table_style2">'+webserviceResponse.contentDataList[i].issuedBy+'</td><td class="table_style2">'+webserviceResponse.contentDataList[i].docName+'</td><td class="table_style2">'+webserviceResponse.contentDataList[i].descOldEmp+'</td><td class="table_style2"><a  href="'+webserviceResponse.contentDataList[i].docUrl+'" class="searchLink underline" id="docName'+i+'" target="_blank">View/Download</a></td> </tr>');
		
	} 
	$('#fourth-container_old').simplePagination({
	   	first_content: '&lt;&lt;',
	   	previous_content: '<',
	   	next_content: '>',
	   	last_content: '>>',
	   	items_per_page: 25
	   });
};

$.fn.documentSearch_old = function(){
	$('#loading_ajax_old').show();
	$.ajax({
		type: "POST",
		url:searchOldDocumentURL,
		dataType: "json",
		contentType: "application/json",
		data:"{\"alfrescoInput\" :{\"typeOfContent\":\"OldEmployeeDocument\", \"channel\":\"[EMPLOYEE]\",\"matchMethod\":\""+$('input[name=method_old]:checked','#docSearchForm_old').val()+"\",\"docName\":\""+$('#searchfilename_old').val()+"\",\"deptName\":\""+$('#searchdepartmentname_old option:selected').val()+"\",\"issuedBy\":\""+$('#issuedBy_old option:selected').val()+"\",\"docuploaddatestart\":\""+$('#docuploaddatefrom_old').val()+"\",\"docuploaddateend\":\""+$('#docuploaddateto_old').val()+"\",\"descOldEmp\":\""+$('#description_old').val()+"\"}}",
		
		success: function (data) {
			webserviceResponse=data;
			/*for(var i=0; i < 1; i++) {*/
			
			if(webserviceResponse.result=="" || webserviceResponse.result==null){
				$('#loading_ajax_old').hide();
				$('#searchviewNull_old').hide();
				$('#searchview_old').show();
               if(webserviceResponse.errorCode==901)
            	   {
            	   setTimeout(function(){ alert(webserviceResponse.errorMessage); }, 100);
            	   }
               else{
			       $.fn.displaysearchresults_old();
               }
				
			    }else
				{
			    	$('#loading_ajax_old').hide();
			    $('#displayResultsNull_old').empty();
				$('#searchview_old').hide();
				$('#searchviewNull_old').show();
				$('#displayResultsNull_old').append('<th style="width:900px;color:red">' +webserviceResponse.result+'</th>');

			    
				}
		
		//	}
		 },
	 error:function (jqXHR, timeout, message) {
			 if(jqXHR.status===500)
			 {
			 alert(message);
			 }else{
				 alert("Session Timed Out.Kindly click OK to relogin to Document Store..");
				 $.fn.logOut();
			 }
	 }
	 });
};

	
$.fn.reset = function(){
	var date=webserviceResponse.header.todaysDate;
	$('#docuploaddate').val(date.substring(0,11));
	$('#docuploaddateto').val(date.substring(0,11));
    $('#searchview').hide();
    $('#searchview_old').hide();
    $('#searchviewNull_old').hide();
    $('#searchviewNull').hide();
    $('#searchForm_file_Error').css( "display","none");
    $('#searchForm_file_Error_old').css( "display","none");
    $('#searchForm_DateLimt_Error_old').css( "display","none");
    $('#searchForm_DateLimt_Error').css( "display","none");
    $('#docuploaddatefrom').val("17/12/2016"); 
	//$('#docpublishdateto').val(date.substring(0,11));
  //  $('#docpublishdatefrom').val("17/12/2016");


   };

   
$.fn.getPwdDetailsCall_reset = function(){
	$.ajax({
	type: "POST",
	url:getPwdDetailsCallURL ,
	dataType: "json",
	contentType: "application/json",
	data: {},
	success: function (data) {
		webserviceResponse=data;
		$.fn.reset();
		//$.fn.validateThisReset();
	 },
 error: function() {
		//alert("Failure in getPwdDetails Webservice");
	 }
 });
};

/****Code added for old docs****/

$('.surveyor').click(function() {
    if($('#lgnprf').css('display') == "block"){
    	$('#lgnprf').hide();
    }
});

function toggle_visibility(id){
	  var e = document.getElementById(id);
	  if (e.style.display == 'block' ) {
	       e.style.display = 'none';
	     } else {
	       e.style.display = 'block';
	    }
	}

/******Code added for recent documents tab starts*******/

$.fn.documentSearch_recent = function(){
	$('#loading_ajax').show();
	$.ajax({
		type: "POST",
		url:searchDocumentURL,
		dataType: "json",
		contentType: "application/json",
		data:"{\"alfrescoInput\" :{\"typeOfContent\":\"EmployeeDocument\", \"channel\":\"[EMPLOYEE]\",\"matchMethod\":\"ALL\",\"docName\":\"\",\"deptName\":\"RECENTDOCUMENTS\",\"docType\":\"\",\"issuedBy\":\"\",\"searchbytype\":\"UDR\",\"docuploaddatestart\":\""+moment().subtract(30,'d').format('DD/MM/YYYY')+"\",\"docuploaddateend\":\""+moment().format('DD/MM/YYYY')+"\",\"docpublishdatestart\":\"\",\"docpublishdateend\":\"\"}}",
		
		success: function (data) {
			webserviceResponse=data;
			for(var i=0; i < 1; i++) {
			
				if(webserviceResponse.errorCode!=null)
				{
				$('#loading_ajax_recent').hide();
				setTimeout(function(){ alert(webserviceResponse.errorMessage); }, 100);
				}
			if(webserviceResponse.contentDataList[i].docName==""||webserviceResponse.contentDataList[i].docName==null){
				$('#loading_ajax_recent').hide();
				$('#searchview_recent').hide();
				 $('#displayResultsNull_recent').empty();
				 $('#searchviewNull_recent').show();
					$('#displayResultsNull_recent').append('<th style="width:900px;color:red">' +webserviceResponse.contentDataList[i].statusOfDocSearch+'</th>');
				/*alert(webserviceResponse.contentDataList[i].statusOfDocSearch);*/
			    }else
				{
			    $('#loading_ajax_recent').hide();
			    $('#searchviewNull_recent').hide();
			    $('#searchview_recent').show();
			    $.fn.displayRecentResults();
			    
				}
		
			}
		 },
	 error:function (jqXHR, timeout, message) {
			 if(jqXHR.status===500)
			 {
			 alert(message);
			 }else{
				 alert("Session Timed Out.Kindly click OK to relogin to Document Store..");
				 $.fn.logOut();
			 }
	  }
	 });
	
	
	$.fn.displayRecentResults = function(){
		$('#displayResults_recent').empty();
		for(var i=webserviceResponse.contentDataList.length-1; i >= 0;  --i)  {
	  	
			$('#displayResults_recent').append('<tr class="searchList"> <td class="table_style2">' +webserviceResponse.contentDataList[i].uploadDate+'</td><td class="table_style2">' +webserviceResponse.contentDataList[i].publishDate+'</td><td class="table_style2">' +webserviceResponse.contentDataList[i].departmentName+'</td><td class="table_style2">'+webserviceResponse.contentDataList[i].description+'</td><td class="table_style2">'+webserviceResponse.contentDataList[i].docName+'</td><td class="table_style2"><a  href="'+webserviceResponse.contentDataList[i].docUrl+'" class="searchLink underline" id="docName'+i+'" target="_blank">View/Download</a></td> </tr>');
			
		} 
		$('#fourth-container_recent').simplePagination({
		   	first_content: '&lt;&lt;',
		   	previous_content: '<',
		   	next_content: '>',
		   	last_content: '>>',
		   	items_per_page: 25
		   });
	};
	
};

/******Code added for recent documents tab ends*******/
