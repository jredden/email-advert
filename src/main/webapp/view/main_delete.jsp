<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Matrix Blast Delete Message</title>
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
	$('#existingCampaign').show('slow');
	$('#execDelete').hide(); 
	$('#execDeleted').hide();
	loadCampaignNames();

	$('#existingCampaignID').ajaxForm({ 
	       dataType:  'json', 
	       success:   processJsonExistingCampaign,
	       error:	processJsonExistingCampaignError
	          }); 
	    
		function processJsonExistingCampaign(data) {
			$('#execDelete').hide(); 
			$('#execDeleted').hide();
			var campaignInput = "<input type='hidden' name='campaign' value='"+data.list.submitToDelete[0].campaign+"'/>";
			var checkBoxesDelete = "";
			$(data.list.submitToDelete).each(function() {
				var checkb = "<div> Delete: " + this.uri + ' ' + this.version + "<input type='checkbox' name='deleteMessage|" + this.uri+ "|"+ this.version + "' value='" + this.uri+ "|"+ this.version+"'/><div>";
				checkBoxesDelete+=checkb;
			});
			$("div[id^=campaignInput]").html(campaignInput);
			$("div[id^=checkBoxesDelete]").html(checkBoxesDelete);
			$('#execDelete').show('slow');
		
		}
		function processJsonExistingCampaignError(data){
			alert(data);
		}

		$('#execDeleteID').ajaxForm({ 
		       dataType:  'json', 
		       success:   processJsonExecDelete,
		       error:	processJsonExecDeleteError
		          }); 
		function processJsonExecDelete(data){
			var message = data.execDeleteMessage.deleteMessage;
			$("div[id^=execDeleted]").html("<label>"+message+"</label>");
			$('#execDeleted').show('slow');
		}		
		function processJsonExecDeleteError(data){
			alert(data);
		}

});

function loadCampaignNames() {
    $.getJSON("/fetchCampaign.do",     // url
     function(json){           // callback
        var options = '';
        $(json.list.campaignName).each(function() {
            options += '<option value="' + this.campaignName + '">' + this.campaignName + '</option>';
        });
        $("select[name^=campaigns]").html(options);
     }
  );
}	

</script>
<body>

<div>
<span id="who">logged in as <c:out
	value='${sessionScope["userRole"]}' />
</span>
</div>

<h1>Matrix Blast Advertising Promotion and Execution</h1>

<div id="oHomeMenu"><span> Matrix Blast Home 
	<a href="/home.do"> <img src="/_m/Home.jpg" width="50" height="50"
	alt="" border="0"></a> 
	</span> 
	<span> Log Off Matrix Blast <a
	href="/logout.do"> <img src="/_m/login.jpg" width="50" height="50"
	alt="" border="0"></a> </span>
</div>

<div id="existingCampaign">
<form name="existingCampaignForm" id="existingCampaignID" method="post"
	action="/submitCampaignMessagesDelete.do">
<div><label>Campaigns</label> <select id="campaignValue"
	name="campaigns"></select></div>
<div id="submit"><input class="button" type="submit"
	id="existingCampaignId" name="submit" value="Submit a campaign to Delete Messages From" /></div>
</form>

<div  id="execDelete">
<form name="execDeleteForm" id="execDeleteID" action="/execDelete.do" method="post">
<div id="campaignInput"></div>
<div id="checkBoxesDelete"></div>
<div id="submit"><input class="button" type="submit"
	id="promExecDeleteID" name="submit" value="Delete Messages and Associated Attributes" /></div>
</form>

<div id="execDeleted">
</div>

</div>

</body>
</html>