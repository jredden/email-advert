<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Matrix Blast Administration</title>
</head>

<style type="text/css">
@import "/_css/admin.css";

@import "/_css/jquery.datepick.css";
@import "/_css/fck.css";
</style>

<script type="text/javascript" src="_cj/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="_cj/jquery.datepick.min.js"></script>
<script type="text/javascript" src="_cj/jquery.form.js"></script>
<script type="text/javascript">

$(function() {
	$('#popupDatepicker').datepick({multiSelect: 2, 
    showOn: 'both', buttonImageOnly: true, buttonImage: '/_m/calendar-green.gif'});
});


String.prototype.startsWith = function(prefix) {
    return this.indexOf(prefix) === 0;
}

$(document).ready(function() {

	  $('#homeHome').show();
	  $('#newCamp').hide();
	  $('#newOEM').hide();
	  $('#existingCampaign').hide();
	  $('#updateACampaign').hide();
	  $('#updateCampaign2').hide();
	  $('#newMessage').hide();
	  $('#newMessage2').hide();
	  $('#newUpload').hide();
	  $('#assocEmailAddrSetup').hide();
	  $('#unAssociatedEmailAddressess').hide();
	  $('#unAssociatedEmailAddressess2').hide();
	  $('#selectToEdit').hide();
	  $('#selectToEditCandidates').hide();
	  
	  $('#ANewOem').click(function() {
		  $('#newCamp').hide();
		  $('#existingCampaign').hide();
		  $('#newMessage').hide();
		  $('#newMessage2').hide();
		  $('#newUpload').hide();
		  $('#updateACampaign').hide();
		  $('#updateCampaign2').hide();
		  $('#assocEmailAddrSetup').hide();
		  $('#unAssociatedEmailAddressess').hide();
		  $('#unAssociatedEmailAddressess2').hide();
		  $('#selectToEdit').hide();
		  $('#selectToEditCandidates').hide();
		  $('#newOEM').show('slow');
		  	return false;
		});
	  $('#ANewCamp').click(function() {
		  $('#newOEM').hide();
		  $('#existingCampaign').hide();
		  $('#newMessage').hide();
		  $('#newMessage2').hide();
		  $('#newUpload').hide();
		  $('#updateACampaign').hide();
		  $('#updateCampaign2').hide();
		  $('#assocEmailAddrSetup').hide();
		  $('#unAssociatedEmailAddressess').hide();
		  $('#unAssociatedEmailAddressess2').hide();
		  $('#selectToEdit').hide();
		  $('#selectToEditCandidates').hide();
		  $('#newCamp').show('slow');
			loadOems();
		    return false;
		});
	  $('#menuExistingCampaign').click(function() {
		  $('#newCamp').hide();
		  $('#newOEM').hide();
		  $('#newMessage').hide();
		  $('#newMessage2').hide();
		  $('#newUpload').hide();
		  $('#updateACampaign').hide();
		  $('#updateCampaign2').hide();
		  $('#existingCampaign').show('slow');
		  $('#assocEmailAddrSetup').hide();
		  $('#unAssociatedEmailAddressess').hide();
		  $('#unAssociatedEmailAddressess2').hide();
		  $('#selectToEdit').hide();
		  $('#selectToEditCandidates').hide();
		    loadCampaignNames();
		    return false;
		});
	  $('#ANewMessage').click(function(){  
		  $('#newCamp').hide();
		  $('#newOEM').hide();
		  $('#existingCampaign').hide();
		  $('#updateACampaign').hide();
		  $('#updateCampaign2').hide();
		  $('#newMessage').show('slow');
		  $('#newMessage2').hide();
		  $('#newUpload').hide();
		  $('#assocEmailAddrSetup').hide();
		  $('#unAssociatedEmailAddressess').hide();
		  $('#unAssociatedEmailAddressess2').hide();
		  $('#selectToEdit').hide();
		  $('#selectToEditCandidates').hide();
		  loadCampaignNames();
		    return false;
		});
	  $('#UnAssocEmails').click(function(){  
		  $('#newCamp').hide();
		  $('#newOEM').hide();
		  $('#existingCampaign').hide();
		  $('#updateACampaign').hide();
		  $('#updateCampaign2').hide();
		  $('#newMessage').hide();
		  $('#newMessage2').hide();
		  $('#newUpload').hide();
		  $('#unAssociatedEmailAddressess').hide();
		  $('#unAssociatedEmailAddressess2').hide();
		  $('#selectToEdit').hide();
		  $('#selectToEditCandidates').hide();
		  $('#assocEmailAddrSetup').show('slow');

		  loadCampaignNames();
		  loadUnAssocEmailAddresses();
		    return false;
		});

		$('#EditReviewMessage').click(function(){  
			$('#newCamp').hide();
			$('#newOEM').hide();
			$('#existingCampaign').hide();
			$('#updateACampaign').hide();
			$('#updateCampaign2').hide();
			$('#newMessage').hide();
			$('#newMessage2').hide();
			$('#newUpload').hide();
			$('#unAssociatedEmailAddressess').hide();
			$('#unAssociatedEmailAddressess2').hide();
			$('#assocEmailAddrSetup').hide();
			$('#selectToEditCandidates').hide();
			$('#selectToEdit').show('slow');
			loadCampaignNames();
			return false;
			});

      $('#newCampID').ajaxForm({ 
          // dataType identifies the expected content type of the server response 
         dataType:  'json', 
  
         // success identifies the function to invoke when the server response 
         // has been received 
         success:   processJsonNewCamp,
         error: processJsonNewCampError
      });
       
	  	function processJsonNewCamp(data) { 
			var message = data.message.message;
			$("div[id^=newCampAdded]").html("<label>"+message+" added</label>");
			loadCampaignNames();
		}
	  	function processJsonNewCampError(data){
            alert(data);
    	}
    			
	    $('#newOemID').ajaxForm({ 
 	      dataType:  'json', 
 	      success:   processJsonNewOem,
 	      error:	 processJsonNewOemError
          }); 
    
		function processJsonNewOem(data) { 
			var message = data.storeOem.message;
			$("div[id^=newOemAdded]").html("<label> Provider "+message+" added</label>");
			loadOems();
		}
		function processJsonNewOemError(data){
			alert(data);
		}

		$('#existingCampaignID').ajaxForm({ 
 	       dataType:  'json', 
	       success:   processJsonExistingCampaign,
	       error:	  processJsonExistingCampaignError
	    }); 
	    
		function processJsonExistingCampaign(data) { 
			var campaignName = data.updateCampaign.dateEvent.CampaignName;
			var name = data.updateCampaign.dateEvent.Name;
			var eventName = data.updateCampaign.dateEvent.EventName;
			var dateSegmentOne = data.updateCampaign.dateEvent.DateSegmentOne;
			var dateSegmentTwo = data.updateCampaign.dateEvent.DateSegmentTwo;
			$("label[id^=ucampL]").html(campaignName);
			$("span[id^=ucampNa]").html("<input  type='text' name='Name' value='"+name+"'/>");
			// $("span[id^=ucampEvNa]").html("<input  type='text' name='eventName' value='"+eventName+"'/>");
			$("span[id^=ucampSD]").html("<input  type='text' name='campaignDate1' value='"+dateSegmentOne+"'/>");
			$("span[id^=ucampED]").html("<input  type='text' name='campaignDate2' value='"+dateSegmentTwo+"'/>");

			// don't know why this extra level of indirection is getting added ... ?
			// Browser weirdness
			var emailCheckBoxes = "";
			if(undefined != data.updateCampaign.campaignEmailAddresses[0].string && undefined != data.updateCampaign.campaignEmailAddresses[0]){
				for(count=0; count < data.updateCampaign.campaignEmailAddresses[0].string.length; count++){
				var str = "<div> Delete " +data.updateCampaign.campaignEmailAddresses[0].string[count]+"["+data.updateCampaign.campaignEmailTypes.string[count]+"]" + "<input type='checkbox' name='emailDelete" + data.updateCampaign.campaignEmailAddresses[0].string[count] + ':' + data.updateCampaign.campaignEmailTypes.string[count]+ "' value='" +data.updateCampaign.campaignEmailAddresses[0].string[count]+"'/><div>";
				emailCheckBoxes += str;
				}
			}

			var emailTextBox = "<div>Add Email Address <input type='text' name='emailAddress' value=''/><input type='radio' name='groupNewEmailAddress' value='test'/> Test &nbsp;<input type='radio' name='groupNewEmailAddress' value='admin'/> Administrator &nbsp;<input type='radio' name='groupNewEmailAddress' value='provider' checked/> Provider &nbsp;<input type='radio' name='groupNewEmailAddress' value='subscriber'/> Subscriber &nbsp;</div>";

			
			var campaignAssociateTemplates = "";
			if(undefined != data.updateCampaign.associatedTemplates.string){
				for(count=0; count < data.updateCampaign.associatedTemplates.string.length; count++){
				var str = "<div> Unassociate " +data.updateCampaign.associatedTemplates.string[count] + "<input type='checkbox' name='emailUnassociate'" + data.updateCampaign.associatedTemplates.string[count] + "' value='" +data.updateCampaign.associatedTemplates.string[count]+"'/><div>";
				campaignAssociateTemplates += str;
				}
			}

			var campaignUnassociateTemplates = "";
			if(undefined != data.updateCampaign.unAssociatedTemplates.string){
				for(count=0; count < data.updateCampaign.unAssociatedTemplates.string.length; count++){
				var str = "<div> Associate " +data.updateCampaign.unAssociatedTemplates.string[count] + "<input type='checkbox' name='emailAssociate'" + data.updateCampaign.unAssociatedTemplates.string[count] + "' value='" +data.updateCampaign.unAssociatedTemplates.string[count]+"'/><div>";
				campaignUnassociateTemplates += str;
				}
			}

			var campaignNamePost = "<input type='hidden' name='campaignName' value='"+campaignName+"'/>";

			$("div[id^=updateACampaignEventsToDates]").html(emailCheckBoxes + emailTextBox + campaignAssociateTemplates + campaignUnassociateTemplates + campaignNamePost);
			
			
			$('#updateACampaign').show('slow');
		}
		function processJsonExistingCampaignError(data){
			alert(data);
		}

		$('#updateCampaignID').ajaxForm({
	 	       dataType:  'json', 
		       success:   processJsonUpdateCampaign,
		       error:	  processJsonUpdateCampaignError
		 }); 
		    
		function processJsonUpdateCampaign(data) { 
			var dateEvent = '<div>CampaignName:<label>'+data.storeCampaignUpdate.dateEvent.CampaignName+'</label></div>';
			dateEvent += '<div>(optional) Additional Name:<label>'+data.storeCampaignUpdate.dateEvent.Name+'</label></div>';
			dateEvent += '<div>Event Name:<label>'+data.storeCampaignUpdate.dateEvent.EventName+'</label></div>';
			dateEvent += '<div>Start Date:<label>'+data.storeCampaignUpdate.dateEvent.DateSegmentOne+'</label></div>';
			dateEvent += '<div>End Date:<label>'+data.storeCampaignUpdate.dateEvent.DateSegmentTwo+'</label></div>';
			
			$(data.storeCampaignUpdate.unAssociatedTemplates).each(function() {
				dateEvent += '<div>UnAssocated Templates:<label>'+this.string+'</label></div>';
			});
			$(data.storeCampaignUpdate.associatedTemplates).each(function() {
				dateEvent += '<div>Assocated Templates:<label>'+this.string+'</label></div>';
			});
			$(data.storeCampaignUpdate.deletedEmails).each(function() {
				dateEvent += '<div>Deleted Emails:<label>'+this.string+'</label></div>';
			});
			$(data.storeCampaignUpdate.campaignEmailAddresses).each(function() {
				dateEvent += '<div>Campaign E-Mail Addresses:<label>'+this.string+'</label></div>';
			});
			$(data.storeCampaignUpdate.campaignEmailTypes).each(function() {
				dateEvent += '<div>Campaign E-Mail Types:<label>'+this.string+'</label></div>';
			});
			dateEvent += '<div>Email Added:<label>'+data.storeCampaignUpdate.addedEmail+'</label></div>';
				
			$("div[id^=updateCampaign2]").html(dateEvent);
			$('#updateACampaign').hide();
			$('#updateCampaign2').show('slow');
		}
		function processJsonUpdateCampaignError(data){
			alert(data);
		}
		
		$('#messageCampaignID').ajaxForm({ 
	 	       dataType:  'json', 
		       success:   processJsonMessageCampaign,
		       error:	  processJsonMessageCampaignError
		 }); 
		    
		function processJsonMessageCampaign(data) { 
			$('#newMessage2').show('slow');
		}
		function processJsonMessageCampaignError(data){
			alert(data);
		}

		$('#newMessage2ID').ajaxForm({
	 	       dataType:  'json', 
		       success:   processJsonMessageUpload,
		       error:	  processJsonMessageUploadError
		 }); 
		    
		function processJsonMessageUpload(data) { 
			$('#newMessage').hide();
			$('#newMessage2').hide();
			var response = data.fileUpLoad.message;
			if(response.startsWith("message compiled on server")){
				// alert(response);
				$('#newMessage2ID').submit();
			}
			$("div[id^=newUpload]").html("<label>"+response+"</label>");
			$('#newUpload').show('slow');
		}
		function processJsonMessageUploadError(data){
			alert(data);
		}

		$('#assocEmailAddrSetupID').ajaxForm({ 
	 	       dataType:  'json', 
		       success:   processJsonEmailAddrSetup,
		       error:	  processJsonEmailAddrSetupError
		 }); 
		    
		function processJsonEmailAddrSetup(data) { 
			$('#unAssociatedEmailAddressess').show('slow');
		}
		function processJsonEmailAddrSetupError(data){
			alert(data);
		}

		$('#unAssociatedEmailAddressessID').ajaxForm({ 
	 	       dataType:  'json', 
		       success:   processJsonAssociatedEmails,
		       error:	  processJsonAssociatedEmailsError
		 }); 

		function processJsonAssociatedEmails(data){
			  $('#assocEmailAddrSetup').hide();
			  $('#unAssociatedEmailAddressess').hide();
			  var labels = '<br/><br/>Addresses Added to Campaign:<c:out value="${sessionScope.campaign}"/><br/>';
			  $(data.list.assocEmails).each(function() {
				  labels += '<div><span><label>'+this.address+'</label></span>&nbsp;<span><label>'+this.type+'</label></span></div>';
			  });
			  $("div[id^=unAssociatedEmailAddressess2]").html(labels);
			  $('#unAssociatedEmailAddressess2').show('slow');
			  loadUnAssocEmailAddresses();
		}
		function processJsonAssociatedEmailsError(data){
			alert(data);
		}

		$('#selectToEditID').ajaxForm({ 
	 	       dataType:  'json', 
		       success:   processJsonSelectToEdit,
		       error:	  processJsonSelectToEditError
		 }); 

		function processJsonSelectToEdit(data){
			var campaignName = '';
	        var options = '';
	        $(data.list.reviewOrUpdate).each(function() {
	            options += '<option value="' + this.name  + ' ' + this.version + '">' + this.name  + ' ' + this.version + '</option>';
	            campaignName = this.campaign;
	        });
	        $("select[name^=editView]").html(options);
	        $("span[id^=campaignID]").html('<input type="hidden" name="campaign" value="'+campaignName+'"/>');
	  	  $('#selectToEdit').hide();
		  $('#selectToEditCandidates').show('slow');
		}
		function processJsonSelectToEditError(data){
			alert(data);
		}

		$('#selectToEditCandidatesID').ajaxForm({ 
		 	       dataType:  'json', 
			       success:   processJsonSelectToEditExec
			 }); 

		function processJsonSelectToEditExec(data){
			  $('#selectToEditCandidates').hide();
			  var input1 = '<input type="hidden" name="content" value="'+data.reviewUpdate.content+'';
			  var input2 = '<input type="hidden" name="version" value="'+data.reviewUpdate.version+'"/>';
			  var input3 = '<input type="hidden" name="campaign" value="'+data.reviewUpdate.campaign+'"/>';
			  var input4 = '<input type="hidden" name="uri" value="'+data.reviewUpdate.uri+'"/>';
			  $("span[id^=contentID]").html(input1);
			  $("span[id^=versionID]").html(input2);
			  $("span[id^=campaignID]").html(input3);
			  $("span[id^=campaignID]").html(input4);
			  $('#newMessage2').show('slow');
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

	function loadUnAssocEmailAddresses() {
		    $.getJSON("/unAssocEmailAddr.do",     // url
		     function(json){           // callback
		        var options = '';
		        $(json.list.unassocEmails).each(function() {
		            options += "<div>Associate " + this.address+'['+this.type+' '+this.name+' '+this.eventName + ']'+"<input type='checkbox' name='emailAssociate." + this.address+':'+this.type+"' value='" +this.address+':'+this.type+"'/>"+"<div>";
		        });
		        $("span[id^=unassocEmails]").html(options);
		     }
		  );
	 }		
});

function FCKeditor_OnComplete(editorInstance) {
	window.status = editorInstance.Description;
}

</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Matrix Blast Administration</title>
</head>

<body>
<div>
<span id="who">logged in as <c:out
	value='${sessionScope["userRole"]}' />
</span>
</div>

<h1>Matrix Blast Advertising Administration</h1>

<div id="oHomeMenu"><span> Matrix Blast Home 
	<a href="/home.do"> <img src="/_m/Home.jpg" width="50" height="50"
	alt="" border="0"></a> 
	</span> 
	<span> Log Off Matrix Blast <a
	href="/logout.do"> <img src="/_m/login.jpg" width="50" height="50"
	alt="" border="0"></a> </span>
</div>

<div id="menuTop">
<span id="ANewCamp"> Add new campaign <a
	href="#aNewCamp"> <img src="/_m/ButtonAdd_256.png" width="50"
	height="50" alt="" border="0"></a> 
</span> 
<span id="menuExistingCampaign">
Modify existing campaign <a href="#anExistingCampaign"> <img
	src="/_m/ButtonReload_256.png" width="50" height="50" alt="" border="0"></a>
</span> 
<span id="ANewOem"> Add new Provider <a href="#aNewOem"> <img
	src="/_m/SpeakerGreen_256.png" width="50" height="50" alt="" border="0"></a>
</span>
</div>
<div id="menuTop2">
<span id="ANewMessage"> Build New Message <a
	href="#aNewMessage"> <img src="/_m/iTemplateGreen.png" width="50"
	height="50" alt="" border="0"></a> 
</span> 
<span id="UnAssocEmails"> Associate External Email Addresses <a
	href="#unAssocEmails"> <img src="/_m/email.jpg" width="50"
	height="50" alt="" border="0"></a> 
</span> 

<span id="EditReviewMessage"> Edit or Review Blast<a
	href="#editReviewMessage"> <img src="/_m/message.jpg" width="50"
	height="50" alt="" border="0"></a> 
</span> 

</div>

<div id="newCamp">Define a new Campaign
<form name="CampaignForm" id="newCampID" method="post"
	action="/storeCampaign.do">
<div id="cname">Campaign Name:<span id="cnames"><input
	type="text" name="campaignName" maxlength=255 size=64 /></span></div>
<div id="datepicker">Choose event date or date range (two dates):<span
	id="datepickers"><input type="text" name="campaignDates"
	id="popupDatepicker" /></span></div>
<div><label>Providers</label> <select name="oems"></select></div>

<div id="submit"><input class="button" type="submit" id="submit"
	name="submit" value="Add new Campaign" /></div>
</form>
<div id="newCampAdded" /></div>
</div>

<div id="newOEM">Define a new Provider
<form name="OemForm" id="newOemID" method="post" action="/storeOem.do">
<div id="oname">Provider Name:<span id="onames"><input
	type="text" name="oemName" maxlength=255 size=64 /></span></div>
<div>Add Provider Email Address: <input type='text' name='emailAddress' maxlength=255 size=64 value=''/></div>
<div id="submit"><input class="button" type="submit" id="submit"
	name="submit" value="Add new Provider" /></div>
</form>
<div id="newOemAdded" /></div>
</div>

<div id="existingCampaign">
<form name="existingCampaignForm" id="existingCampaignID" method="post"
	action="/updateCampaign.do">
<div><label>Campaigns</label> <select id="campaignValue"
	name="campaigns"></select></div>
<div id="submit"><input class="button" type="submit"
	id="existingCampaignId" name="submit" value="Update a campaign" /></div>
</form>
</div>

<div id="updateACampaign">
<form  name='updateACampaignForm' id="updateCampaignID" method='post' action='/storeCampaignUpdates.do'>
CampaignName:&nbsp;<label id="ucampL"></label>
<div><label>Update Name:&nbsp;</label><span id="ucampNa">

</span></div>
<!--  
<div><label>Update Event Name:&nbsp;</label><span id="ucampEvNa">

</span></div>
-->

<div><label>Update Campaign Start Date:&nbsp;</label><span id="ucampSD">

</span></div>
<div><label>Update Campaign End Date:&nbsp;</label><span id="ucampED">

</span></div>
<div id="updateACampaignEventsToDates">

</div>
<div id='submitCampaignUpdatesId'>
<input class='button' type='submit' name='submit' value='Update Event,Email and Templates'/>
</div>

</form>
</div>

<div id="updateCampaign2">results<br/>
</div>

<div id="newMessage">
<form name="newMessageForm" id="messageCampaignID" method="post"
	action="/newMessage.do">
<div><label>Campaigns</label> <select id="campaignValue"
	name="campaigns"></select></div>
<div id="submit"><input class="button" type="submit"
	id="messageCampaignId" name="submit" value="Create Message for this Campaign" /></div>
</form>
</div>

<div id="newMessage2">
	<%
		net.fckeditor.FCKeditor fckEditor = new net.fckeditor.FCKeditor(request, "file");
	%>
		<form method="post" action="/upLoadEdit.do" id="newMessage2ID">
		<input type="hidden" name="FileName" value="<%=(String) request.getSession().getAttribute("campaign") %>.htm"/>
		<input type="hidden" name="type" value="multiPartFileSingle"/>
		<span id="contentID"></span><span id="versionID"></span><span id="campaignID"></span><span id="versionActiveID"></span>
		<%
			if(null == request.getSession().getAttribute("content")){
			fckEditor.setValue("RETURN_TO_CLIENT");
			}
			else{
				fckEditor.setValue(request.getSession().getAttribute("content").toString());
			}
			out.println(fckEditor);
		%>
		<br />
		<input type="submit" value="Upload" />
		</form>

</div>

<div id="newUpload">
</div>

<div id="assocEmailAddrSetup">
<form name="assocEmailAddrSetupForm" id="assocEmailAddrSetupID" method="post"
	action="/newMessage.do">
<div><label>Campaigns</label> <select id="campaignValue"
	name="campaigns"></select></div>
<div id="submit"><input class="button" type="submit"
	id="assocEmailAddrSetupId" name="submit" value="Campaign to associate emails to" /></div>
</form>
</div>
<div id="unAssociatedEmailAddressess">
<form name="unAssociatedEmailAddressessForm" id="unAssociatedEmailAddressessID" method="post"
	action="/associateEmailAddresses.do">
<div><label>Emails Not Part of a Campaign</label> <span id="unassocEmails">
	</span></div>
<div id="submit"><input class="button" type="submit"
	id="unAssociatedEmailAddressess" name="submit" value="associate email address" /></div>
</form>
</div>

<div id="unAssociatedEmailAddressess2">

</div>

<div id="selectToEdit">
<form name="selectToEditForm" id="selectToEditID" method="post"
	action="/editOrReviewThese.do">
<div><label>Campaigns</label> <select id="campaignValue"
	name="campaigns"></select></div>
<div id="submit"><input class="button" type="submit"
	id="selectToEditId" name="submit" value="Edit or Review Campaign Blasts " /></div>
</form>
</div>

<div id="selectToEditCandidates">
<form name="selectToEditForm" id="selectToEditCandidatesID" method="post"
	action="/editOrReviewThisOne.do">
<label>Messages to Review and Edit</label>
<select name="editView"></select><span id="campaignID"></span>
<div id="submit"><input class="button" type="submit"
	id="selectToEditId" name="submit" value="Edit or Review Campaign Blasts " /></div>
</form>
</div>

</body>
</html>