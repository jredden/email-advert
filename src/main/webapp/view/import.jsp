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
$('#deleteMenu').hide();
$('#homeMenuRoot').hide();
$('#rootMenu').hide();
$('#homeMenuRoot').show('slow');
$('#oHomeMenu').show('slow');

            $('#rootFormID').ajaxForm({ 
         // dataType identifies the expected content type of the server response 
        dataType:  'json', 
 
        // success identifies the function to invoke when the server response 
        // has been received 
        success:   processJson 
           }); 

	function processJson(data) { 
		var role = data.addUser.addUser;
		$("div[id^=userAdded]").html("<label> adding a user was a "+role+"</label>");
	    // 'data' is the json object returned from the server 
	    // alert(role); 
	}

            $('#deleteFormID').ajaxForm({ 
         // dataType identifies the expected content type of the server response 
        dataType:  'json', 
 
        // success identifies the function to invoke when the server response 
        // has been received 
        success:   processJsonDel 
           }); 

	function processJsonDel(data) { 
		var user = data.deleteUser.deleteUser;
		$("div[id^=userDeleted]").html("<label>"+user+"</label>");
		loadUsers();
	    // 'data' is the json object returned from the server 
	    // alert(role); 
	}

  $('#aRemoveUser').click(function() {
		$('#rootMenu').hide();
		$('#oHomeMenu').show();
   		$('#deleteMenu').show('slow');
   		loadUsers();
	    return false;
	});
  $('#aNewUser').click(function() {
		$('#deleteMenu').hide();
		$('#oHomeMenu').show();
   		$('#rootMenu').show('slow');
	    return false;
	});

  $('#aDisplayUser').click(function() {
		$('#deleteMenu').hide();
		$('#oHomeMenu').show();
 		$('#rootMenu').show('slow');
 		loadUsers();
	    return false;
	});

function loadUsers() {
    $.getJSON("/fetchUsers.do",     // url
	     function(json){           // callback
	        var options = '';
	        $(json.list.readUsers).each(function() {
	            options += '<option value="' + this.userNames+':::'+this.userRoles + '">' + this.userNames + '.' + this.userRoles + '</option>';
	        });
	        $("select[name^=users]").html(options);
	     }
	  );
 }		
});

$(document).ready(function() {


});

</script>

<body>
<span id="who">logged in as <c:out
	value='${sessionScope["userRole"]}' /></span>

<div id="menuTop">

<div id="menuHomePage">
<h1>Matrix IMPORT Page</h1>
</div>

<div id="oHomeMenu"><span> Log Off Matrix Blast <a
	href="/logout.do"> <img src="/_m/login.jpg" width="50" height="50"
	alt="" border="0"></a> </span></div>


<div id="homeMenuRoot">

<h1></h1>
<div id="aNewUser">Add A New User <a href="#aNewUser"> <img
	src="/_m/User.png" width="50" height="50" alt="" border="0"></a></div>
<!--<div id="aRemoveUser">Remove Users <a href="#aRemoveUser"> <img
	src="/_m/Remove.png" width="50" height="50" alt="" border="0"></a></div>
<div>Campaign Administration <a href="/admin.do"> <img
	src="/_m/Campaign.png" width="50" height="50" alt="" border="0"></a>
</div>
<div>Promote to QA and Execute Campaign<a href="/executeQA.do"> <img
	src="/_m/Execute.jpg" width="50" height="50" alt="" border="0"></a>
</div>
-->
<div id="aDisplayUser">Display User <a href="#aDisplayUser"> <img
	src="/_m/User.png" width="50" height="50" alt="" border="0"></a></div>
</div>

<div id="rootMenu">
<h1>Create New User</h1>
<form name="rootForm" id="rootFormID" action="/addUser.do" method="post">
<div id="NewuName">New User Name:<span id="NewuNames"><input
	type="text" name="userName" maxlength=255 size=64 /></span></div>
<div id="NewuPassword">New User Password:<span id="NewuPassword"><input
	type="text" name="userPassword" maxlength=255 size=64 /></span></div>
<div id="NewuPassword2">(Retype) New User Password:<span
	id="NewuPassword2"><input type="text" name="userPassword2"
	maxlength=255 size=64 /></span></div>
<div>New User Role:<input type='radio' name='groupNewEmailAddress'
	value='test' /> Test &nbsp;<input type='radio'
	name='groupNewEmailAddress' value='admin' /> Administrator &nbsp;<input
	type='radio' name='groupNewEmailAddress' value='provider' checked />
Provider &nbsp;<input type='radio' name='groupNewEmailAddress'
	value='subscriber' /> Subscriber &nbsp;</div>
<div id="submit"><input class="button" type="submit" id="addUser"
	name="submit" value="Add New User" /></div>
</form>

<div id="userAdded"></div>

</div>

<div id="deleteMenu">
<h1>Delete User</h1>
<form name="deleteForm" id="deleteFormID" action="/deleteUser.do"
	method="post">
<div><label>Users</label> <select name="users"></select></div>
<div id="submit"><input class="button" type="submit"
	id="deleteUser" name="submit" value="Delete User" /></div>
</form>

<div id="userDeleted"></div>

</div>
</body>
</html>