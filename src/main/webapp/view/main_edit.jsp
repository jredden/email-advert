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
	  $('#newMessage').hide();
	  $('#newMessage2').hide();
	  $('#newUpload').hide();
	  $('#selectToEdit').hide();
	  $('#selectToEditCandidates').hide();
	  
	  $('#ANewMessage').click(function(){  
		  $('#newMessage').show('slow');
		  $('#newMessage2').hide();
		  $('#newUpload').hide();
		  $('#selectToEdit').hide();
		  $('#selectToEditCandidates').hide();
		  loadCampaignNames();
		    return false;
		});

		$('#EditReviewMessage').click(function(){  
			$('#newMessage').hide();
			$('#newMessage2').hide();
			$('#newUpload').hide();
			$('#selectToEditCandidates').hide();
			$('#selectToEdit').show('slow');
			loadCampaignNames();
			return false;
			});
		
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

});

function FCKeditor_OnComplete(editorInstance) {
	window.status = editorInstance.Description;
}

function loadCampaignNames() {
	$.getJSON("/userCampaign.do",
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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Matrix Blast Administration</title>
</head>

<body>
<div>
<span id="who">logged in as <c:out
	value='${sessionScope["userName"]}' />
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


<div id="menuTop2">
<span id="ANewMessage"> Build New Message <a
	href="#aNewMessage"> <img src="/_m/iTemplateGreen.png" width="50"
	height="50" alt="" border="0"></a> 
</span> 


<span id="EditReviewMessage"> Edit or Review Blast<a
	href="#editReviewMessage"> <img src="/_m/message.jpg" width="50"
	height="50" alt="" border="0"></a> 
</span> 

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
