<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Matrix Consultants Importing Excel Files</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
</head>
<style type="text/css">
@import "/_css/admin.css";
@import "/_css/jquery.datepick.css";
</style>

<script type="text/javascript" src="_cj/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="_cj/jquery.datepick.min.js"></script>
<script type="text/javascript" src="_cj/jquery.form.js"></script>

<script type="text/javascript">

   function validate(el)
   {
      var len = el.excel.value.length;
   		var ind=el.excel.value.lastIndexOf('.'); 
   		var ext = el.excel.value.substring(ind+1, len);
   		if((ext!='xls')&&(ext!='csv'))
   		{alert("This file is not allowed! Please select a proper file.");return false;}
      if(!el.excel.value) { alert("Please Browse for an uploadable file"); return false; }
      return true;
   }
   
</script>


<script type="text/javascript">

$(document).ready(function() {
	$('#emailAddrAdded').hide();
	
	$('#fileUploadID').ajaxForm({ 
 	       dataType:  'json', 
	       success:   processJson
	          }); 

	function processJson(data) { 
		// 'data' is the json object returned from the server
		var emailAdd = data.noEmailAddress.emailAddresses;
		$("div[id^=emailAddrAdded]").html("<label> Number of Email Addresses added to Database = " + emailAdd + "</label>");
		$('#emailAddrAdded').show('slow');
//	     alert(emailAdd);
		}
	});

</script>

<body>

<div>
<span id="who">logged in as <c:out
	value='${sessionScope["userRole"]}' />
</span>
</div>
<h1>Matrix Blast Advertising Email List And Subscriber Upload</h1>
<div id="oHomeMenu"><span> Matrix Blast Home 
	<a href="/home.do"> <img src="/_m/Home.jpg" width="50" height="50"
	alt="" border="0"></a> 
	</span> 
	<span> Log Off Matrix Blast <a
	href="/logout.do"> <img src="/_m/login.jpg" width="50" height="50"
	alt="" border="0"></a> </span>
</div>



<div id="content">
<p>Please upload Excel file or CSV File</p>
<p>
<form action="/uploadFile.do" id="fileUploadID" style="width: 450px"
	method="post" enctype="multipart/form-data">
<fieldset><legend>Upload Excel or CSV File</legend> <label>File:
</label><br> <input type="file" name="file" id="file"><br>
</fieldset>

<div id="submit"><input class="button" type="submit"
	id="existingCampaignId" name="submitX" value="Submit a Spread Sheet or CSV File for Matrix Blast data repository" /></div>
</form>

<div id="emailAddrAdded"></div>

</p>
<p>Valid file types to upload are:
<ul style="margin-left: 20px;">
	<li>.xls</li>
	<li>.csv</li>
</ul>
</p>
</div>
</div>

</body>
</html>