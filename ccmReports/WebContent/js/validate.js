/*function validateUsername()
{
	var usrname = document.myform.Username.value;
	if(usrname.search())
	}

var splChars = "*|,\":<>[]{}`\';()@&$#%";
    for (var i = 0; i < usrname.length; i++) {
        if (splChars.indexOf(usrname.value.charAt(i)) != -1){
        alert ("Illegal characters detected!"); 
        usrname.focus(); 
    }
    return true;     
    }	
 
    if( /[^a-zA-Z0-9\-\/]/.test( pass ) ) {
        alert('Input is not alphanumeric');
        return false;
    }
    */
function compare()
	{
	    var startDt = document.myform.fromDate.value;
	    var endDt = document.myform.toDate.value;
	    
	    if( !(new Date(startDt).getTime() <= new Date(endDt).getTime()))
	    {
	    	alert("please enter valid date");
	    	return false;
	    }
	}



function validate(){
	
    var usrname = document.myform.Username.value;
    var pass= document.myform.psw.value;
    var usernameregex = /^[a-zA-Z0-9!.@_#$%&]+$/;
    var passRegex = /(?=.*[0-9])(?=.*[!@#$%&])(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9!@#$%&]{8,15}$/;
    if(!passRegex.test(pass)) {
        alert("password should contain atleast one number, one special character, one upper caseletter and one lower case letter /n" +
        		"length off password should be between 8 to 15 characters only");
        return false;
    }
    
    if(!usernameregex.test(usrname)) {
        alert("username should not contain space, and it only support special characters which are " +
        		'"!.@_#$%&"');
        return false;
    }
    
    return true
   
}

