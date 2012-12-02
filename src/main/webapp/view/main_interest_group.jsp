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
	$('#existingCampaign').hide();
	$('#assocCampResult').hide();
	$('#existingProvider').hide();
	$('#assocProvResult').hide();
	$('#newAnonInterestGroup').hide();
	$('#newInterestResult').hide();
	
	  $('#associateCampaign').click(function() {
		  $('#assocCampResult').hide();
		  $('#newAnonInterestGroup').hide();
		  $('#newInterestResult').hide();
		  $('#existingProvider').hide();
		  $('#assocProvResult').hide();
		  $('#existingCampaign').show('slow');
		    loadCampaignNames();
		    loadInterestGroupNames();
		    return false;
		});
	  $('#associateProvider').click(function() {
		  $('#assocCampResult').hide();
		  $('#newAnonInterestGroup').hide();
		  $('#newInterestResult').hide();
		  $('#existingCampaign').hide();
		  $('#assocProvResult').hide();
		  $('#existingProvider').show('slow');
		    loadOems();
		    loadInterestGroupNames();
		    return false;
		});
	  $('#newInterestGroup').click(function() {
		  $('#newInterestResult').hide();
		  $('#assocCampResult').hide();
		  $('#existingCampaign').hide();
		  $('#newAnonInterestGroup').show('slow');
		    return false;
		});

		$('#existingCampaignID').ajaxForm({ 
		       dataType:  'json', 
		       success:   processJsonExistingCampaign,
		       error:	processJsonExistingCampaignError
		          }); 
		    
			function processJsonExistingCampaign(data) { 
				$('#existingCampaign').hide();
				var message = data.associateCampaign.message;
				$("div[id^=assocCampResult]").html("<label>"+message+"</label>");
				$('#assocCampResult').show('slow');
			}
			function processJsonExistingCampaignError(data){
				alert(data);
			}
		$('#existingProviderID').ajaxForm({ 
			   dataType:  'json', 
			   success:   processJsonExistingProvider,
			   error:	processJsonExistingProviderError
			      }); 
			    
			function processJsonExistingProvider(data) { 
					$('#existingProvider').hide();
					var message = data.associateProvider.message;
					$("div[id^=assocProvResult]").html("<label>"+message+"</label>");
					$('#assocProvResult').show('slow');
				}
			function processJsonExistingProviderError(data){
					alert(data);
				}
		$('#newInterestGroupID').ajaxForm({ 
	    		   dataType:  'json', 
	       			success:   processJsonNewInterestGroup,
	       			error:	processJsonNewInterestGroupError
	          }); 	
			function processJsonNewInterestGroup(data){
				$('#newAnonInterestGroup').hide();
				var message = data.newInterestGroup.message;
				$("div[id^=newInterestResult]").html("<label>"+message+"</label>");
				$('#newInterestResult').show('slow');
			}
			function processJsonNewInterestGroupError(data){
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
function loadInterestGroupNames() {
    $.getJSON("/fetchInterestGroup.do",     // url
     function(json){           // callback
        var options = '';
        $(json.list.interestGroupName).each(function() {
            options += '<option value="' + this.interestGroupName + '">' + this.interestGroupName + '</option>';
        });
        $("select[name^=interestGroups]").html(options);
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
        $("select[name^=providers]").html(options);
     }
  );
}		

</script>
<body>

<div><span id="who">logged in as <c:out
	value='${sessionScope["userRole"]}' /> </span></div>

<h1>Matrix Blast Advertising Interest Group Operations</h1>

<div id="oHomeMenu"><span> Matrix Blast Home <a
	href="/home.do"> <img src="/_m/Home.jpg" width="50" height="50"
	alt="" border="0"></a> </span> <span> Log Off Matrix Blast <a
	href="/logout.do"> <img src="/_m/login.jpg" width="50" height="50"
	alt="" border="0"></a> </span></div>


<div id="menuTop"><span id="associateCampaign"> Associate
campaign to interest group <a href="#anExistingCampaign"> <img
	src="/_m/associate.jpg" width="50" height="50" alt="" border="0"></a>
</span><span id="associateProvider"> Associate
provider to interest group <a href="#anExistingProvider"> <img
	src="/_m/provider.jpg" width="50" height="50" alt="" border="0"></a>
</span>  <span id="newInterestGroup"> Create a new Interest Group <a
	href="#newInterestGroup"> <img src="/_m/newInterest.jpg" width="50"
	height="50" alt="" border="0"></a> </span></div>

<div id="existingCampaign">
<form name="existingCampaignForm" id="existingCampaignID" method="post"
	action="/associateInterestCampaign.do">
<div><label>Campaigns</label> <select id="campaignValue"
	name="campaigns"></select></div>
<div><label>Interest Groups</label> <select
	id="interestGroupValue" name="interestGroups"></select></div>
<div id="submit"><input class="button" type="submit"
	id="existingCampaignId" name="submit"
	value="Submit a campaign for association to the Interest Group" /></div>
</form>
</div>

<div id="assocCampResult"></div>

<div id="existingProvider">
<form name="existingProviderForm" id="existingProviderID" method="post"
	action="/associateInterestProvider.do">
<div><label>Providers</label> <select id="ProviderValue"
	name="providers"></select></div>
<div><label>Interest Groups</label> <select
	id="interestGroupValue" name="interestGroups"></select></div>
<div id="submit"><input class="button" type="submit"
	id="existingProviderId" name="submit"
	value="Submit a Provider for association to the Interest Group" /></div>
</form>
</div>

<div id="assocProvResult"></div>

<div id="newAnonInterestGroup">
<form name="newInterestGroupForm" id="newInterestGroupID" method="post"
	action="/newInterestGroup.do">
<div id="interestGroupName">Interest Group Name:<span id="igname"><input
	type="text" name="interestGroupName" maxlength=255 size=64 /></span></div>
<div id="submit"><input class="button" type="submit"
	id="newInterestGroupId" name="submit"
	value="Submit to create a new Interest Group" /></div>
</form>
</div>

<div id="newInterestResult"></div>

</body>
</html>