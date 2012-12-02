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
$('#userDeleted').hide();
$('#userAdded').hide();
$('#messagesToProductionID').hide();
$('#productionPromotionID').hide();
$('#aUpload').hide();
$('#updatePassword').hide();
$('#passwordUpdated').hide();
$('#oemID').hide();
$('#validateMenu').show('slow');
$('#homeMenuRoot').show('slow');
$('#oHomeMenu').show('slow');
$('#aUpload').show('slow');

            $('#rootFormID').ajaxForm({ 
         // dataType identifies the expected content type of the server response 
        dataType:  'json', 
 
        // success identifies the function to invoke when the server response 
        // has been received 
        success:   processJson,
        error:	   processJsonError
           }); 

	function processJson(data) { 
		var role = data.addUser.addUser;
		$("div[id^=userAdded]").html("<label> adding a user was a "+role+"</label>");
		$('#userAdded').show('slow');
	    // 'data' is the json object returned from the server 
	    // alert(role); 
	    
	}
	function processJsonError(data) {
		alert(data);
	}

            $('#deleteFormID').ajaxForm({ 
         // dataType identifies the expected content type of the server response 
        dataType:  'json', 
 
        // success identifies the function to invoke when the server response 
        // has been received 
        success:   processJsonDel,
        error:	   processJsonDelError 
           }); 

	function processJsonDel(data) { 
		var user = data.deleteUser.deleteUser;
		$("div[id^=userDeleted]").html("<label>"+user+"</label>");
		loadUsers();
	    // 'data' is the json object returned from the server 
	    // alert(role); 
		$('#userDeleted').show('slow');
	}
	function processJsonDelError(data){
		alert(data);
	}


	   $('#validateFormID').ajaxForm({ 
	        dataType:  'json', 
	        success:   processJsonvalidateForm,
	        error:	   processJsonvalidateFormError 
	           }); 

	function processJsonvalidateForm(data) { 
			$('#messagesToProductionID').hide();
			var checkboxes = "";
			$(data.list.promoteProduction).each(function() {
				var tag=this.campaign+'|'+this.uri+'|'+this.revision;
				var tag2=this.uri+'|'+this.revision;
				checkboxes += '<div> Promote to Production:'+tag+'<input type="checkbox" id="'+tag2+'" name="uri|'+tag2+'" value="'+tag+'"/><span id="dateschedule">'+this.dateMinimum+' '+this.dateMaximum+'<input type="text" name="date|'+tag2+'" id="'+tag2+'" /></span> </div>';
			});
			checkboxes += '<div id="submitProd"><input class="button" type="submit" id="promoteProduction" name="submit|" value="Promote Messages to Production" /></div>';
			$("form[id^=messagesProductionID]").html(checkboxes);
			$(data.list.promoteProduction).each(function() {
				var tag2=this.uri+'|'+this.revision;
				$('div:contains("+tag2+")').datepick({minDate: this.dateMinimum, maxDate: this.dateMaximum}); 
			});
			$('#messagesProductionID :checkbox').datepick({minDate: this.dateMinimum, maxDate: this.dateMaximum, buttonImage: '/_m/calendar-green.gif'}) 
			$('#messagesToProductionID').show('slow');
	}
	function processJsonvalidateFormError(data){
		alert(data);
	}

	$('#messagesProductionID').ajaxForm({
		dataType: 	'json',
		success: processProduction,
		error: processProductionError
		});

	function processProduction(data){
		$('#messagesToProductionID').hide();
		$('#productionPromotionID').hide();
		$('#userDeleted').hide();
		$('#userAdded').hide();
				
		var labels = "";
		$(data.list.productionPromotion).each(function (){
			labels += '<div><label>Scheduled for distribution:'+this.stata+'</label></div>';
		});
		$("div[id^=productionPromotionID]").html(labels);
		$('#productionPromotionID').show('slow');
	}
	function processProductionError(data){
		alert(data);
	}
	
    $('#updatePasswordFormId').ajaxForm({         
        dataType:  'json', 
        success:   processPasswordJson,
        error:	processPasswordJsonError 
     }); 
 	function processPasswordJson(data){
 		$('#userDeleted').hide();
 		$('#userAdded').hide();
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
  		loadProviderUsers();
  		$('#updatePassword').show('slow');
 	    return false;
 	});
 	
  $('#aRemoveUser').click(function() {
		$('#rootMenu').hide();
		$('#userDeleted').hide();
		$('#userAdded').hide();
		$('#validateMenu').show('slow');
		$('#messagesToProductionID').hide();
		$('#productionPromotionID').hide();
		$('#updatePassword').hide();
		$('#passwordUpdated').hide();
		$('#oHomeMenu').show();
   		$('#deleteMenu').show('slow');
   		loadUsers();
	    return false;
	});
  $('#aNewUser').click(function() {
		$('#deleteMenu').hide();
		$('#userDeleted').hide();
		$('#userAdded').hide();
		$('#validateMenu').show('slow');
		$('#messagesToProductionID').hide();
		$('#productionPromotionID').hide();
		$('#updatePassword').hide();
		$('#passwordUpdated').hide();
		$('#oHomeMenu').show();
		loadOems();
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
	  
 // $('#aDisplayUser').click(function() {
//		$('#deleteMenu').hide();
//		$('#validateMenu').hide();
//		$('#oHomeMenu').show();
// 		$('#users').show();
// 		loadUsers();
//	    return false;
//	});
			
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

function loadProviderUsers() {
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
<div id="aRemoveUser">Remove Users <a href="#aRemoveUser"> <img
	src="/_m/Remove.png" width="50" height="50" alt="" border="0"></a></div>
<div>Campaign Administration <a href="/admin.do"> <img
	src="/_m/Campaign.png" width="50" height="50" alt="" border="0"></a>
</div>

<div>Promote to QA and Execute Campaign<a href="/executeQA.do">
<img src="/_m/Execute.jpg" width="50" height="50" alt="" border="0"></a>
</div>

<div id="aUpload">Spread Sheet Upload <a
	href="/upLoad.do"> <img src="/_m/spreadsheet.jpg" width="50"
	height="50" alt="" border="0"></a></div>
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
<div id="roleID">New User Role:<input type='radio' name='groupNewEmailAddress'
	value='test' /> Test &nbsp;<input type='radio'
	name='groupNewEmailAddress' value='admin' checked  /> Administrator &nbsp;<input
	type='radio' name='groupNewEmailAddress' value='provider'/>
Provider &nbsp;<input type='radio' name='groupNewEmailAddress'
	value='subscriber' /> Subscriber &nbsp;</div>
	<div id="oemID"><label>Providers</label> <select name="oems"></select></div>
<div id="submit"><input class="button" type="submit" id="addUser"
	name="submit" value="Add New User" /></div>
</form>
</div>

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
</div>

<div id="userDeleted"></div>
</div>



<div id="validateMenu">
<form name="validateMenuID" id="validateFormID"
	action="/promoteToProduction.do" method="post">
<div id="submit"><label>Promote Messages To Production</label> <input
	class="button" type="image" src="/_m/production.jpg" width="50"
	height="50" alt="" border="0" id="promoteProduction" name="submit"
	value="Promote Messages to Production" /></div>
</form>
</div>

<div id="messagesToProductionID">
<form name="productionMessageForm" id="messagesProductionID"
	action="/productionPromotion.do" method="post">


</form>
</div>

<div id="productionPromotionID">
</div>

<div>Delete Message in a Campaign<a href="/MessageDelete.do">
<img src="/_m/GreenDelete.jpg" width="50" height="50" alt="" border="0"></a>
</div>

<div>Interest Group Management<a href="/interestGroup.do">
<img src="/_m/interest.jpg" width="50" height="50" alt="" border="0"></a>
</div>


</div>

<div id="updatePassword">
<form  name="updatePasswordForm" id="updatePasswordFormId" action="/updatePassword.do" method="post">
<div><label>Password Update for this Provider </label></div>
<div><select name="providerusers"></select></div>
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

<div id="passwordUpdated">
</div>

<div>Generate Reports<a href="/reports.do">
<img src="/_m/report.jpg" width="50" height="50" alt="" border="0"></a>
</div>
</body>
</html>
