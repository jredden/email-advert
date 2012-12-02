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
$('#validateMenu').hide();
$('#messagesToProductionID').hide();
$('#productionPromotionID').hide();

$('#oHomeMenu').show('slow');
$('#validateMenu').show('slow');

$('#validateFormID').ajaxForm({ 
    dataType:  'json', 
    success:   processJsonvalidateForm,
    error:	processJsonvalidateFormError 
    }); 

function processJsonvalidateForm(data) { 
	$('#messagesToProductionID').hide();
	$('#productionPromotionID').hide();
	var checkboxes = "";
	$(data.list.promoteProduction).each(function() {
		var tag=this.campaign+'|'+this.uri+'|'+this.revision;
		var tag2=this.uri+'|'+this.revision;
		checkboxes += '<div> Promote to Production:'+tag+'<input type="checkbox" id="'+tag2+'" name="uri|'+tag2+'" value="'+tag+'"/><span id="dateschedule">'+this.dateMinimum+' '+this.dateMaximum+'<input type="text" name="date|'+tag2+'" id="'+tag2+'" /></span> </div>';
	});
	checkboxes += '<div id="submitProd"><input class="button" type="submit" id="promoteProduction" name="submit" value="Promote Messages to Production" /></div>';
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

});



</script>

<body>
<span id="who">logged in as <c:out value='${sessionScope["userRole"]}'/></span>

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

<h1></h1>

<div id="validateMenu">
<form name="validateMenuID" id="validateFormID" action="/promoteToProduction.do" method="post">
<div id="submit">
<label>Promote Messages To Production</label>
<input class="button" type="image" src="/_m/production.jpg" width="50" height="50" alt="" border="0" id="promoteProduction" name="submit" value="Promote Messages to Production"/>
</div>	
</form>
</div>

<div>Generate Reports<a href="/reports.do">
<img src="/_m/report.jpg" width="50" height="50" alt="" border="0"></a>
</div>

<div id="messagesToProductionID">
<form name="productionMessageForm"  id="messagesProductionID" action="/productionPromotion.do" method="post">

</form>
</div>

<div id="productionPromotionID">
</div>

</div>

</div>

</body>
</html>