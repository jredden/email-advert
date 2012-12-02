<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Matrix Blast Execution and Promotion</title>
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
	$('#promToQA').hide();
	$('#promToQAError').hide();
	$('#promQAResults').hide();
	loadCampaignNames();

	$('#existingCampaignID').ajaxForm({ 
	       dataType:  'json', 
	       success:   processJsonExistingCampaign,
	       error:	processJsonExistingCampaignError
	          }); 
	    
		function processJsonExistingCampaign(data) { 
			$('#promToQA').hide();
			$('#promToQAError').hide();
			$('#promQAResults').hide();
			if(undefined != data.submitToQA){
				var error = "<div><label>"+data.submitToQA.error+"</label></div>";
				$("div[id^=promToQAError]").html(error);
				$('#promToQAError').show('slow');
			}
			else{
				var campaignInput = "<input type='hidden' name='campaign' value='"+data.list.submitToQA[0].campaign+"'/>";
				var checkBoxesCandidateQA = "";
				$(data.list.submitToQA).each(function() {
					var checkb = "<div> Promote to QA: " + this.uri + ' ' + this.version + "<input type='checkbox' name='promoteMessageToQA|" + this.uri+ "|"+ this.version + "' value='" + this.uri+ "|"+ this.version+"'/>&nbsp;Subject:<span><input type='text' size='64' name='subject" + this.uri+ "::"+ this.version+"' value=''/></span><div>";
					checkBoxesCandidateQA+=checkb;
				});
				$("div[id^=campaignInput]").html(campaignInput);
				$("div[id^=checkBoxesCandidateQA]").html(checkBoxesCandidateQA);
				$('#promToQA').show('slow');
			}
		}
		function processJsonExistingCampaignError(data){
			alert(data);
		}
		
		$('#promToQAID').ajaxForm({ 
	       dataType:  'json', 
	       success:   processJsonPromoteToQA,
	       error:	processJsonPromoteToQAError
	          }); 
	    
		function processJsonPromoteToQA(data) { 
			$('#promToQA').hide();
			$('#promToQAError').hide();
			$('#promQAResults').hide();
			var campaignName = '';
	        var actions = '';
			$(data.list.promoteMessage).each(function() {
				actions += '<div><label> message:' + this.message + '</label></div>';
				actions += '<div><label> uri:' + this.uri + '</label></div>';
				actions += '<div><label> image URI List:' + this.imageUriList + '</label></div>';
				actions += '<div><label> version:' + this.version + '</label></div>';
				actions += '<div><label> subject:' + this.subject + '</label></div>';
				campaignName = this.campaign;
			 });
			$("div[id^=promQAResults]").html('<div> ' + campaignName + ' has these messages waiting for approval </div>');
			$("div[id^=promQAResults]").html(actions);
			$('#promQAResults').show('slow');
		}
		function processJsonPromoteToQAError(data){
			alert(data);
		}
});

function loadCampaignNames() {
    $.getJSON("/userCampaign.do",     // url
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
	action="/submitCampaignQA.do">
<div><label>Campaigns</label> <select id="campaignValue"
	name="campaigns"></select></div>
<div id="submit"><input class="button" type="submit"
	id="existingCampaignId" name="submit" value="Submit a campaign for validation for QA evaluation" /></div>
</form>

<div  id="promToQA">
<form name="promToQAForm" id="promToQAID" action="/promoteMessageToQA.do" method="post">
<div id="campaignInput"></div>
<div id="checkBoxesCandidateQA"></div>
<div id="submit"><input class="button" type="submit"
	id="promToQAID" name="submit" value="Submit messages for QA evaluation" /></div>
</form>
</div>

<div id="promToQAError"></div>
</div>

<div id="promQAResults">
</div>
</body>
</html>