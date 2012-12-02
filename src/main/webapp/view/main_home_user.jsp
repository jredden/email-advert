<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
$('#updatePassword').hide();
$('#passwordUpdated').hide();
$('#homeMenuRoot').show('slow');
$('#oHomeMenu').show('slow');
	
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
		$('userAdded').hide();
		$('#deleteMenu').hide();
 		$('#rootMenu').hide();
 		$('#passwordUpdated').hide();
 		$('#updatePassword').show('slow');
	    return false;
	});
	

});


</script>

<body>
<span id="who">logged in as <c:out value='${sessionScope["userName"]}'/></span>

<div id="menuTop">

<div id="menuHomePage">
<h1> Matrix Blast Home Page </h1>
</div>

<div id="oHomeMenu">
<span>
Log Off Matrix Blast
<a href="/logout.do">
<img src="/_m/login.jpg" width="50" height="50" alt="" border="0"></a>
</span>
</div>




<div id="homeMenuRoot">

<h1> </h1>

<div>
Campaign Administration
<a href="/edit.do">
<img src="/_m/Campaign.png" width="50" height="50" alt="" border="0"></a>
</div>

<div>Promote to QA and Execute Campaign<a href="/executeUserQA.do">
<img src="/_m/Execute.jpg" width="50" height="50" alt="" border="0"></a>
</div>
</div>




<div id="updatePassword">
<form  name="updatePasswordForm" id="updatePasswordFormId" action="/updatePassword.do" method="post">
<div><label>Password Update for this Provider </label></div>
<div><input type="hidden" name="providerusers" value="<c:out value='${sessionScope["userName"]}'/>:::<c:out value='${sessionScope["userRole"]}'/>"/></div>
<div id="NewuPassword">New User Password:<span id="NewuPassword">
<input type="password" name="userPassword" maxlength=255 size=64/></span>
</div>
<div id="NewuPassword2">(Retype) New User Password:<span id="NewuPassword2">
<input type="password" name="userPassword2" maxlength=255 size=64/></span>
</div>
<div id="submit"><input class="button" type="submit"
	id="selectToEditId" name="submit" value="Update Provider Password" /></div>
</form>
</div>

<div id="aPass"> 
Update Provider Password
<a href="#aPass">
<img src="/_m/password.jpg" width="50" height="50" alt="" border="0"></a>
</div>

<div>Generate User Reports<a href="/reports.do">
<img src="/_m/report.jpg" width="50" height="50" alt="" border="0"></a>
</div>

<div id="passwordUpdated">
</div>

</body>
</html>