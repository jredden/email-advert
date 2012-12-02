<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Matrix Blast Home Page</title>
</head>

<style type="text/css">
@import "/_css/admin.css";

@import "/_css/jquery.datepick.css";
</style>

<script type="text/javascript" src="_cj/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="_cj/jquery.datepick.min.js"></script>
<script type="text/javascript" src="_cj/jquery.form.js"></script>
<script type="text/javascript">

$(document).ready(function() {
$('#homeMenuRoot').hide();
$('#rootMenu').hide();
$('#aUpload').hide();
$('#userAdded').hide();
$('#updatePassword').hide();
$('#passwordUpdated').hide();
$('#homeMenuRoot').show('slow');
$('#oHomeMenu').show('slow');
$('#aUpload').show('slow');


    $('#rootFormID').ajaxForm({ 
         // dataType identifies the expected content type of the server response 
        dataType:  'json', 
 
        // success identifies the function to invoke when the server response 
        // has been received 
        success:   processJson,
        error:	processJsonError 
     }); 
	function processJson(data) { 
		$('#rootMenu').hide();
		$('#passwordUpdated').hide();
		var role = data.addUser.addUser;
		$("div[id^=userAdded]").html("<label> adding a user was a "+role+"</label>");
		$('#userAdded').show('slow');
	    // 'data' is the json object returned from the server 
	    // alert(role); 
	}
	function processJsonError(data){
		alert(data);
	}

	
    $('#updatePasswordFormId').ajaxForm({         
       dataType:  'json', 
       success:   processPasswordJson,
       error:	processPasswordJsonError 
    }); 
	function processPasswordJson(data){
		$('#updatePassword').hide();
		var message = data.updatePassword.updatePassword;
		$("div[id^=passwordUpdated]").html("<label>"+message+"</lable>");
		$('#passwordUpdated').show('slow');
	}
	function processPasswordJsonError(data){
		alert(data);
	}

	$('#aPass').click(function() {
		$('#updatePassword').hide();
		$('#userAdded').hide();
		$('#deleteMenu').hide();
 		$('#rootMenu').hide();
 		$('#passwordUpdated').hide();
 		loadUsers();
 		$('#updatePassword').show('slow');
	    return false;
	});
	
  $('#aNewUser').click(function() {
	  $('#oemID').hide();
	  	$('#userAdded').hide();
		$('#deleteMenu').hide();
		$('#updatePassword').hide();
		$('#passwordUpdated').hide();
   		$('#rootMenu').show('slow');
	    return false;
	});

  $('#roleID').click(function() {
	var clicker = $('#roleID input:radio:checked').val();
	if(clicker == 'provider'){
		loadOems();
		$('#oemID').show('slow');
	}
	else{
		$('#oemID').hide();
	}
	return true;
  });

  function loadUsers() {
	    $.getJSON("/fetchProviderUsers.do",     // url
	     function(json){           // callback
	        var options = '';
	        $(json.list.readUsers).each(function() {
	            options += '<option value="' + this.userNames+':::'+this.userRoles + '">' + this.userNames + '.' + this.userRoles + '</option>';
	        });
	        $("select[name^=providerusers]").html(options);
	    }
	    );
	 }	
	 
	function loadOems() {
	    $.getJSON("/fetchOems.do",     // url
	     function(json){           // callback
	        var options = '';
	        $(json.list.oem).each(function() {
	            options += '<option value="' + this.Oem + '">' + this.Oem + '</option>';
	        });
	        $("select[name^=oems]").html(options);
	     }
	  );
	}		
	

});


</script>

<body>
<span id="who">logged in as <c:out
	value='${sessionScope["userRole"]}' /></span>

<div id="menuTop">

<div id="menuHomePage">
<h1>Matrix Blast Home Page</h1>
</div>

<div id="oHomeMenu"><span> Log Off Matrix Blast <a
	href="/logout.do"> <img src="/_m/login.jpg" width="50" height="50"
	alt="" border="0"></a> </span></div>




<div id="homeMenuRoot">

<h1></h1>

<div id="aNewUser">Add A New User <a href="#aNewUser"> <img
	src="/_m/User.png" width="50" height="50" alt="" border="0"></a></div>



<div>Campaign Administration <a href="/admin.do"> <img
	src="/_m/Campaign.png" width="50" height="50" alt="" border="0"></a>
</div>
<div>Promote to QA and Execute Campaign<a href="/executeQA.do">
<img src="/_m/Execute.jpg" width="50" height="50" alt="" border="0"></a>
</div>
</div>

<div id="aUpload">Spread Sheet Upload <a href="/upLoad.do"> <img
	src="/_m/spreadsheet.jpg" width="50" height="50" alt="" border="0"></a></div>
</div>

<div id="rootMenu">
<h1>Create New User</h1>
<form name="rootForm" id="rootFormID" action="/addUser.do" method="post">
<div id="NewuName">New User Name:<span id="NewuNames"><input
	type="text" name="userName" maxlength=255 size=64 /></span></div>
<div id="NewuPassword">New User Password:<span id="NewuPassword"><input
	type="password" name="userPassword" maxlength=255 size=64 /></span></div>
<div id="NewuPassword2">(Retype) New User Password:<span
	id="NewuPassword2"><input type="password" name="userPassword2"
	maxlength=255 size=64 /></span></div>
<div id="roleID">New User Role:<input type='radio'
	name='groupNewEmailAddress' value='test' /> Test &nbsp;<input
	type='radio' name='groupNewEmailAddress' value='admin' checked />
Administrator &nbsp;<input type='radio' name='groupNewEmailAddress'
	value='provider' /> Provider &nbsp;<input type='radio'
	name='groupNewEmailAddress' value='subscriber' /> Subscriber &nbsp;</div>
<div id="oemID"><label>Providers</label> <select name="oems"></select></div>	
<div id="submit"><input class="button" type="submit" id="addUser"
	name="submit" value="Add New User" /></div>
</form>
</div>

<div id="userAdded"></div>



<div id="updatePassword">
<form name="updatePasswordForm" id="updatePasswordFormId"
	action="/updatePassword.do" method="post">
<div><label>Password Update for this Provider </label></div>
<div><select name="providerusers"></select></div>
<div id="NewuPassword">New User Password:<span id="NewuPassword">
<input type="password" name="userPassword" maxlength=255 size=64 /></span></div>
<div id="NewuPassword2">(Retype) New User Password:<span
	id="NewuPassword2"> <input type="password" name="userPassword2"
	maxlength=255 size=64 /></span></div>
<div id="submit"><input class="button" type="submit"
	id="selectToEditId" name="submit" value="Update Provider Password" /></div>
</form>
</div>


<div>Interest Group Management<a href="/interestGroup.do"> <img
	src="/_m/interest.jpg" width="50" height="50" alt="" border="0"></a>
</div>

<div id="aPass">Update Provider Password <a href="#aPass"> <img
	src="/_m/password.jpg" width="50" height="50" alt="" border="0"></a>
</div>

<div>Generate Reports<a href="/reports.do">
<img src="/_m/report.jpg" width="50" height="50" alt="" border="0"></a>
</div>

<div id="passwordUpdated"></div>


</body>
</html>