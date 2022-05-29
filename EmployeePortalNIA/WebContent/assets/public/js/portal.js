var webserviceResponse;
var news;
$.fn.doAjaxCall = function(where,what){ 
		$.ajax({
		type: "POST",
		url: where,
		dataType: "json",
		contentType: "application/json",
		data: what,
		success: function (data) {
			webserviceResponse=data;
		    $.fn.sample();
			
		},
		error: function() {
			//alert("Failure in latest update Webservice");
		}
	});
};

$("#login_Submit_Button").click(function(){
	$('#loginForm').attr('action', loginURL).submit();
});

$( "#loginForm" ).validate({
	  rules: {
		  username: {
	      required: true}
	  },
	    messages: {
	        username: {

	            required: "Enter username"}

	        }

	    
	});

function formattedDate(date) {
    var d = new Date(date || Date.now());
        month = '' + (d.getMonth() + 1);
        day = '' + d.getDate();
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

$.fn.display = function(){
	
	var monthSubs={'01':'Jan','02':'Feb','03':'Mar','04':'Apr','05':'May','06':'Jun','07':'Jul','08':'Aug','09':'Sep','10':'Oct','11':'Nov','12':'Dec'};
	
	var date=webserviceResponse.header.todaysDate;
	var d3=date.split("/")[0]+" ";
	d3 = d3+monthSubs[date.split("/")[1]]+" ";
	d3=d3+date.split("/")[2];
	
    $('#dateheader').append(d3.substring(0,11));

    var date=webserviceResponse.header.todaysDate;
	var todayDate = formattedDateNew(date);
	
    for(var i=webserviceResponse.contentDataList.length-1; i >= 0;  --i) {
	var news=webserviceResponse.contentDataList[i];
	var contentEnddate = news.contentEndDate;
	var contentEnddateNew= formattedDateNew(contentEnddate);
	var contentStartDate = news.contentStartDate;

	var contentStartDateNew= formattedDateNew(contentStartDate);
	
	if(contentEnddateNew > todayDate && contentStartDateNew < todayDate){
		if(compare(date.substring(0, 10),contentStartDate.substring(0, 10)) < 5) 
			{
				
				$("#latestUpdateNews").append('<a  class="datatest text-blue underline"  onClick="l_update();"  id="latestTitle'+i+'" name="'+news.content+'">'+news.title+' </a> <span style="color: Red;"><blink>NEW</blink></span><br><br>');
			}else{
				
				$("#latestUpdateNews").append('<a  class="datatest text-blue underline"  onClick="l_update();"  id="latestTitle'+i+'" name="'+news.content+'">'+news.title+'</a><br><br>');
				}
			}
	
	}	
	
};

$.fn.latestUpdateCall = function(){ 
	$.ajax({
	type: "POST",
	url: latestUpdateUrl,
	dataType: "json",
	contentType: "application/json",
	data: "{\"alfrescoInput\" :{\"typeOfContent\":\"LatestNews\", \"channel\":\"[EMPLOYEE]\"}}", 
	success: function (data1) {
		webserviceResponse=data1;
		$.fn.display();
	},
	error: function() {
		//alert("Failure in latest update Webservice");
	}
});
	

};

$.fn.password = function(){
		$('#passwordMessages').empty();
		if(webserviceResponse.statusCode!=null){
		if(webserviceResponse.statusCode=="0")
			{
		$('#passwordMessages').append('<font color="green">'+webserviceResponse.statusMessage+'</font>');
			}
		else
			{
			
		 $('#passwordMessages').append('<font color="red">'+webserviceResponse.statusMessage+'</font>');
			}

		}
		if(webserviceResponse.errorCode!=null)
		{
		$('#passwordMessages').append('<font color="red">'+webserviceResponse.errorMessage+'</font>');
		}
};
	

$.fn.forgotpwdCall = function(){
	$.ajax({
	type:"POST",
	url: forgotpwdUrl,
	dataType: "json",
	contentType: "application/json",
	data: "{\"userId\":\""+$('#forgotPwd_Uname').val()+"\", \"dob\":\""+$('#forgotPwd_Dob').val()+"\", \"alfrescoInput\":{\"channel\":\"[EMPLOYEE]\"}}",
	success: function (data){
		webserviceResponse=data;
		$('#forgotPwd_submit').prop('disabled', false);
		$('#forgotPwd_submit').removeClass( "grey" );
		$.fn.password();
	},
	error: function() {
		alert("Failure in forgot password Webservice");
	}
		
});
};


$("#forgotPwd_Uname").click(function(){
	$("#passwordMessages").empty();
});

$("#viewAllLink").click(function(){
	window.open('/EmployeePortalNIA/latestupdateViewAllpage.html', '_blank');
});


$.fn.authenticationErrors = function(data){
	if(data!=null && data!=""){
		$('#loginErrorMsg').text(loginAlertMessages[data.split(",")[0]]);
	}
};


$.fn.carouseldisplay = function(){ 
	$.ajax({
	type: "POST",
	url: carouselURL,
	dataType: "json",
	contentType: "application/json",
	data: "{\"alfrescoInput\" :{\"typeOfContent\":\"Carousel\", \"channel\":\"[EMPLOYEE]\"}}", 
	success: function (data1) {
		webserviceResponse=data1;
    	$.fn.carouselpopulate(webserviceResponse);
	},
	error: function() {
		//alert("Failure in carousel Webservice");
	}
});
};
var img;
$.fn.carouselpopulate = function(webserviceResponse){
	   var carouselArray = webserviceResponse.contentDataList;
	    
	    var carouselLength = carouselArray.length;
	    
	    for(var i=0; i < carouselLength - 1; i++){
			var index = i;
			for(var j=i+1; j < carouselLength; j++) {
				if(carouselArray[j].orderNum > carouselArray[index].orderNum) {
				index = j;
				}
			}
			var largerNumber = carouselArray[index];
			carouselArray[index] = carouselArray[i];
			carouselArray[i] = largerNumber;
			
	    }
	
	    for(var i=0; i<carouselArray.length;i++) {
			$('#flex_slider').append('<li> <img id="hello'+i+'" src="'+carouselArray[i].imageContentURL+'" /><p class="flex-caption"> '+carouselArray[i].description+'</p></li>');
		}
	    

$('.flexslider').flexslider({
    animation: "slide",
    pauseOnHover: true
  });		
};

