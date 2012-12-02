<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Matrix Blast Administration</title>
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
$('#loginFailed').hide();
$('#homeHome').hide();
$('#oHomeMenu').hide();
$('#who').hide();
            $('#LoginFormID').ajaxForm({ 
         // dataType identifies the expected content type of the server response 
        dataType:  'json', 
 
        // success identifies the function to invoke when the server response 
        // has been received 
        success:   processJson 
           }); 

	function processJson(data) { 
		var role = data.userRole.userRole;
		$('#menuLogin').hide();
		$('#oHomeMenu').show();
// alert(role);
		if(role == "root"){
			window.location = 'http://www.email_advert.com/home.do';
		}
		else{
			if(role == "admin" || role == "test" || role == "provider"){
				window.location = 'http://www.email_advert.com/home.do';
			}else{
			if(role == "failed"){
				$('#oHomeMenu').hide();
				$('#loginFailed').show('slow');
			}
			}
		}
		$('#who').html("logged in as " + role);
		$('#who').show();
	    // 'data' is the json object returned from the server 
	    
	}
});

</script>

<body>
<span id="who"></span>
<div id="homeHome">
<span>
Matrix Blast Home
<a href="./home.do">
<img src="/_m/Home.jpg" width="50" height="50" alt="" border="0"></a>
</span>
<span>
Log Off Matrix Blast
<a href="/logout.do">
<img src="/_m/login.jpg" width="50" height="50" alt="" border="0"></a>
</span>
</div>


<div id="menuTop">

<div id="menuLogin">
<h1> Matrix Blast Login </h1>
Login
<span id="spanLogin">
<img src="/_m/login.jpg" width="100" height="100" alt="" border="0">
</span>
<div id="loginForm">
<form name="LoginForm" id="LoginFormID" action="/userAndPassword.do" method="post">
<div id="uName">User Name:<span id="uNames"><input type="text" name="userName" maxlength=255 size=64/></span></div>
<div id="loginPassword">Password:<span id="loginPasswords"><input type="password" name="password" maxlength=255 size=64/></span></div>
</div>
<div id="submit">
<input class="button" type="submit" id="loginOperation" name="submit" value="Submit User Name and Password"/>
</div>					
</form>
</div>


<div id="loginFailed">
<h1> Matrix Blast Login </h1>
Login failed ... try again
<a href="/login.do">
<img src="/_m/login.jpg" width="100" height="100" alt="" border="0">
</a>
</div>

</div>

<div id="oHomeMenu">
<span>
Log Off Matrix Blast
<a href="/logout.do">
<img src="/_m/login.jpg" width="50" height="50" alt="" border="0"></a>
</span>
</div>


</body>
</html>