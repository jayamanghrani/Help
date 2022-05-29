
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
	
	var date=responseData.header.todaysDate;
    $('#signedUser').append('Welcome  ' + responseData.header.employeeName + ' ( ' +responseData.header.employeeId+ ' )');
    $('#signedusername').append(responseData.header.employeeName); 

	var d3=date.split("/")[0]+" ";
	d3 = d3+monthSubs[date.split("/")[1]]+" ";
	d3=d3+date.split("/")[2];
	
    $('#dateheader').append(d3.substring(0,11));
    $('#docuploaddate').val(date.substring(0,11));
    $('#docuploaddateto').val(date.substring(0,11));
	//Logic Added by avinash [Starts]
    var cwissLinksArray = responseData.cwissLinks;
    
    var cwissLinksLength = cwissLinksArray.length;
    
    for(var i=0; i < cwissLinksLength - 1; i++){
		var index = i;
		for(var j=i+1; j < cwissLinksLength; j++) {
			if(cwissLinksArray[j].orderNumber < cwissLinksArray[index].orderNumber) {
			index = j;
			}
		}
		var smallerNumber = cwissLinksArray[index];
		cwissLinksArray[index] = cwissLinksArray[i];
		cwissLinksArray[i] = smallerNumber;
	}
    
	for(var i=0; i<cwissLinksArray.length;i++) {
		$('#cwissRedirectBlock').append('<li class="cwissRedirectList"><a  href="'+cwissLinksArray[i].additionalLink+'" class="cwissRedirectLink" target="_blank" id="'+ cwissLinksArray[i].label.replace(/ /g, '').toLowerCase()+'"return false;">'+ cwissLinksArray[i].label +'</a></li>');
	}
	
	//Logic Added by avinash[Ends]

};


$.fn.searchView = function(){
	
	var testStr=responseData.header.groups;
	 if(testStr.indexOf("docadmin") != -1){
	   $(".visiblilityUpload").show();
	 }
	else
	{
	 $(".visiblilityUpload").hide();	
	 $("#searchclick").trigger('click'); 
	}
};

$.fn.doSwizzLinkCall = function(){
	$.ajax({
	type: "POST",
	url:doSwizzLinkCallURL,
	dataType: "json",
	contentType: "application/json",
	data: "{\"alfrescoInput\" :{\"channel\":\"[EMPLOYEE]\"} }",
	success: function (data) {
		responseData=data;
		userid=data;
		$.fn.populate();
		$.fn.searchView();
	},
	error: function() {
		//alert("Failure in Webservice");
	}
});
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
$.fn.notification = function(){


	var date = webserviceResponse.header.todaysDate;
	var todayDate = formattedDateNew(date);
    for(var i=webserviceResponse.contentDataList.length-1; i >= 0;  --i) {
	var notification=webserviceResponse.contentDataList[i];
	var contentEnddate = notification.contentEndDate;
	var contentEnddateNew= formattedDateNew(contentEnddate);
	var contentStartDate = notification.contentStartDate;
	var contentStartDateNew= formattedDateNew(contentStartDate);
	if(contentEnddateNew > todayDate && contentStartDateNew < todayDate){
		if(compare(date.substring(0, 10),contentStartDate.substring(0, 10)) < 5) {
			$('#notifications').append('<p  class="text-blue">'+notification.title+'<span style="color: Red;">NEW</span></p><p>'+notification.content+'</p>');
		}
		else {
		$('#notifications').append('<p  class="text-blue">'+notification.title+'</p><p>'+notification.content+'</p>');
	}
	}
}
};

$.fn.notificationCall = function(){
	$.ajax({
		type: "POST",
		url:notificationCallURL,
		dataType: "json",
		contentType: "application/json",
		data: "{\"alfrescoInput\" :{\"typeOfContent\":\"Notification\", \"channel\":\"[EMPLOYEE]\"}}",
		success: function (data) {
			webserviceResponse=data;
			$.fn.notification();	
		},
		error: function() {
			//alert("Failure in notification Webservice");
		}
	});
};


$.fn.executiveMsg = function(){
		    
		var date = webserviceResponse.header.todaysDate;
		var todayDate = formattedDateNew(date);
	    for(var i=webserviceResponse.contentDataList.length-1; i >= 0;  --i) {
		var news=webserviceResponse.contentDataList[i];
		var contentEnddate = news.contentEndDate;
		var contentEnddateNew= formattedDateNew(contentEnddate);
		var contentStartDate = news.contentStartDate;
		var contentStartDateNew= formattedDateNew(contentStartDate);
		if(contentEnddateNew > todayDate && contentStartDateNew < todayDate){
				if(compare(date.substring(0, 10),contentStartDate.substring(0, 10)) < 5) {
					
					$('#executiveMsg').append('<p class="clearfix" id="'+news.title+'">'+news.title+' <span style="color: Red;"><blink>NEW</blink></span><a target="_blank" class="btn pull-right text-underline" href="'+news.docUrl+'">Read more</a></p>');
					$('#exMessages').append('<p class="clearfix"id="'+news.title+'">'+news.title+' <span style="color: Red;">NEW</span><a target="_blank" class="btn pull-right text-underline" href="'+news.docUrl+'">Read more</p>');
				
					}else{
				$('#executiveMsg').append('<p class="clearfix" id="'+news.title+'">'+news.title+'<a target="_blank" class="btn pull-right text-underline" href="'+news.docUrl+'">Read more</a></p>');
				$('#exMessages').append('<p class="clearfix"id="'+news.title+'">'+news.title+'<a target="_blank" class="btn pull-right text-underline" href="'+news.docUrl+'">Read more</p>');
					}
		}		
		}	
	
};

$.fn.executiveMessageCall = function(){
		$.ajax({
		type: "POST",
		url: executiveMessageCallURL,
		dataType: "json",
		contentType: "application/json",
		data: "{\"alfrescoInput\" :{\"typeOfContent\":\"ExecutiveMsg\", \"channel\":\"[EMPLOYEE]\"}}",
		success: function (data) {
			webserviceResponse=data;
			$.fn.executiveMsg();
			},
		error: function() {
			//alert("Failure in ExecutiveMsgs Webservice");
		}
	});
};


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


$.fn.tickerValue = function(){
	$('#premiumValue').append('Premium update as on '+webserviceResponse.datevalue+':: Upto Date: Rs.'+webserviceResponse.uptoPremium+' Cr. For the Month: Rs.'+webserviceResponse.monthlyPremium+' Cr. For the Day: Rs.'+webserviceResponse.dailyPremium+' Cr.');
};

$("#logout").on("click", function() {
	 $.fn.logOut();
	
});

$.fn.logOut = function(){
	$.ajax({
		type: "POST",
		url:logOutSecondUrl,
		dataType: "json",
		contentType: "application/json",
		data: {},
		success: function (data) {
			 $(location).attr('href', logoutURL);
			 
		 },
	 error: function() {
		 $(location).attr('href', logoutURL);
			//alert("Failure in getTickerValues Webservice");
		 }
	 });
};

//Calling rest service getInfoPolicyURL

$.fn.getInfoAcceptPolicy = function(){
	$.ajax({
		type: "POST",
		url:getuserIdvaluesURL,
		dataType: "json",
		contentType: "application/json",
		data: "{\"userIdinput\":\"userIdvalues\"}",
		success: function (data) {
			webserviceResponse=data;
			$.fn.userIdvalues();
		 },
	 error: function() {
			alert("Failure in getInfoAcceptPolicy Webservice");
		 }
	 });
	
};


 $.fn.getDetails = function(){
	 var getDetails =webserviceResponse.contentDataList;
		var firstName = getDetails.firstName;
		var lastName =  getDetails.lastName;
		var userId = getDetails.userId;
		var status = getDetails.status;
		if(status=="Y")
		{
		$('#getDetails').append('<p  class="text-blue">'+getDetails.title+'<span style="color: Red;">NEW</span></p><p>'+getDetails.content+'</p>');
		$('#firstName').append('<div class="pull-left margin-l-10">Last Login on '+firstName+'</div><div class="pull-right">Password expires on </div>');
		$('#lastName').append('<div class="pull-left margin-l-10">Last Login on '+lastName+'</div><div class="pull-right">Password expires on </div>');
	    $('#userId').append('<div class="pull-left margin-l-10">Last Login on '+userId+'</div><div class="pull-right">Password expires on </div>'); 
	    $('#status').append('<div class="pull-left margin-l-10">Last Login on '+status+'</div><div class="pull-right">Password expires on </div>');
		}
		else 
		{
		$('#getDetails').append('<p  class="text-blue">'+getDetails.title+'</p><p>'+getDetails.content+'</p>');
		}
	};


	$.fn.getDetailsCall = function(){
		$.ajax({
			type: "POST",
			url:getInfoPolicyURL,
			dataType: "json",
			contentType: "application/json",
			data: {},
	
			success: function (data) {
				webserviceResponse=data;
				if(webserviceResponse.status=="Y")
					{
					 window.location.href="PolicyAcceptUser.html";
				
					}
				 else{
					 
				 }
					
			},
			error: function() {
				//alert("Failure in notification Webservice");
			}
		});
	};
	
	
	$.fn.getDetailsCallDisplay = function(){
		$.ajax({
			type: "POST",
			url:getInfoPolicyURL,
			dataType: "json",
			contentType: "application/json",
			data: {},
			success: function (data) {
				webserviceResponse=data;
				 if(webserviceResponse.status=="Y")
					{
					 	$('#name').append('Name : '+webserviceResponse.firstName+' '+ webserviceResponse.lastName);
					 	$('#serialNo').append('Sr No : '+webserviceResponse.userId);
					 	$('#serialNo').addClass( "grey" );
					 	$('#officeCode').append('Office Code : ' +webserviceResponse.officeCode);
					 	var d = webserviceResponse.header.todaysDate;
					 	d = d.split(' ')[0];
					 	$('#date').append('Date :' +d);
					}
				 else{
					 
				 }
					
			},
			error: function() {
				//alert("Failure in notification Webservice");
			}
		});
	};
	
	
	$.fn.getInsert = function(){
		$.ajax({
			type: "POST",
			url:getuserInsertURL,
			dataType: "json",
			contentType: "application/json",
			data: {},
			success: function (data)
			{
				webserviceResponse=data;
				if(webserviceResponse.status=="Submited Successfully")
					{
					
					$("#cmd").attr("disabled", "disabled");
					 $('#cmd').addClass( "grey" );
				
					}
				else
					{
					alert(webserviceResponse.status);
					}
					},
			error: function() {
				//alert("Failure in notification Webservice");
			}
		});
	};

$.fn.getTickerValues = function(){
	$.ajax({
		type: "POST",
		url:getTickerValueUrl,
		dataType: "json",
		contentType: "application/json",
		data: "{\"tickerinput\":\"tickervalues\"}",
		success: function (data){
		
			webserviceResponse=data;
			$.fn.tickerValue();
		 },
	 error: function() {
			//alert("Failure in getTickerValues Webservice");
		 }
	 });
};

function getURLParams_GET() {
	 return window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');

	}

			
$.fn.passwordChange = function(){
	if(webserviceResponse.statusCode!=null){
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
	}}
	if(webserviceResponse.errorCode!=null)
	{
		$('#changeMessages').append('<font color="red">'+webserviceResponse.errorMessage+'</font>');
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

