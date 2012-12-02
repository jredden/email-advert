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
</style>

<script type="text/javascript" src="_cj/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="_cj/jquery.datepick.min.js"></script>
<script type="text/javascript" src="_cj/jquery.form.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	$('#allInterestGroupsIDdiv').hide();	
	$('#timeframeIDdiv').hide();
	$('#allTimeFrameIDdiv').hide();
	$('#allEmailAddressIDdiv').hide();

$('#allInterestGroupsID').click(function() {
		$('#allInterestGroupsIDdiv').hide();
		$('#timeframeIDdiv').hide();
		$('#allTimeFrameIDdiv').hide();
		$('#allEmailAddressIDdiv').hide();	
		loadAllInterestGroupInfo();
	    return false;
	}); // allInterestGroupsID

$('#allSentEmailTimeFrameID').click(function(){
	$('#allInterestGroupsIDdiv').hide();
	$('#allTimeFrameIDdiv').hide();
	$('#allEmailAddressIDdiv').hide();
	$('#timeframeIDdiv').show('slow');	
	$('#popupDatepicker').datepick({multiSelect: 2, 
	    showOn: 'both', buttonImageOnly: true, buttonImage: '/_m/calendar-green.gif'});
	return false;
}); // allSentEmailTimeFrameID

$('#allEmailAddressID').click(function(){
	$('#allInterestGroupsIDdiv').hide();
	$('#timeframeIDdiv').hide();
	$('#allTimeFrameIDdiv').hide();	
	$('#allEmailAddressIDdiv').hide();
	loadAllEmailAddressInfo();
	return false;
}); // allEmailAddressID

$('#timeframeReportID').ajaxForm({ 
    dataType:  'json', 
    success:   processJsonTimeframeReport,
    error:	processJsonTimeframeReportError
       }); 
 
	function processJsonTimeframeReport(data) {
		var result = '';
		if(undefined != data.list.sentMessagesResponse[0].statusMessage){
			result += data.list.sentMessagesResponse[0].statusMessage;
		}
		else{
			result += '<table border="0">';
			result+='<tr>';
            result+='<td>';
            result+='E-Mail Address:';
            result+='</td>';
            result+='<td>';
            result+='Time Sent:';
            result+='</td>';
            result+='<td>';
            result+='Transfer State';
            result+='</td>';
            result+='</tr>';
		
			$(data.list.sentMessagesResponse).each(function(){
				if(undefined != this.campaign){
					result+='<tr>';
		            result+='<td>';
		            result+='Campaign:';
		            result+='</td>';
		            result+='<td>';
		            result+='&nbsp;';
		            result+='</td>';
		            result+='<td>';
		            result+='&nbsp;';
		            result+='</td>';
		            result+='</tr>';
		            
					result+='<tr>';
		            result+='<td>';
		            result+=this.campaign;
		            result+='</td>';
		            result+='<td>';
		            result+='&nbsp;';
		            result+='</td>';
		            result+='<td>';
		            result+='&nbsp;';
		            result+='</td>';
		            result+='</tr>';
					
				}
				result+='<tr>';
	            result+='<td>';
	            result+=this.emailAddress;
	            result+='</td>';
	            result+='<td>';
	            result+=this.timeSent;
	            result+='</td>';
	            result+='<td>';
	            result+=this.transferState;
	            result+='</td>';
	            result+='</tr>';
			});
			result+='</table>';
		}
        $("div[id^=allTimeFrameIDdiv]").html(result);
        $('#allTimeFrameIDdiv').show('slow');		
	}
	function processJsonTimeframeReportError(data){
		alert(data);
	}

}); // ready

function loadAllInterestGroupInfo() {
    $.getJSON("/fetchProviderInterestGroupInfo.do",
     function(json){   
        var result = '<table border="0">';
        $(json.list.interestGroupInfo).each(function() {
            result+='<tr>';
            result+='<td>';
            result+='&nbsp;';
            result+='</td>';
            result+='<td>';
            result+='&nbsp;';
            result+='</td>';
            result+='</tr>';
            result+='<tr>';
            result+='<td>';
            result+='interest group name:';
            result+='</td>';
            result+='<td>';
            result+=this.interestGroup;
            result+='</td>';
            result+='</tr>';
            $(this.oemList).each(function(){
                result+='<tr>';
                result+='<td>';
                result+='provider name:';
                result+='</td>';
                result+='<td>';
                result+=this.string;
                result+='</td>';
                result+='</tr>';
            });
            $(this.campaignList).each(function(){
                result+='<tr>';
                result+='<td>';
                result+='campaign name:';
                result+='</td>';
                result+='<td>';
                result+=this.string;
                result+='</td>';
                result+='</tr>';
            });
        });
        $(json.interestGroupInfo).each(function() {
            result+='<tr>';
            result+='<td>';
            result+='&nbsp;';
            result+='</td>';
            result+='<td>';
            result+='&nbsp;';
            result+='</td>';
            result+='</tr>';
            result+='<tr>';
            result+='<td>';
            result+='interest group name:';
            result+='</td>';
            result+='<td>';
            result+=this.interestGroup;
            result+='</td>';
            result+='</tr>';
            $(this.oemList).each(function(){
                result+='<tr>';
                result+='<td>';
                result+='provider name:';
                result+='</td>';
                result+='<td>';
                result+=this.string;
                result+='</td>';
                result+='</tr>';
            });
            $(this.campaignList).each(function(){
                result+='<tr>';
                result+='<td>';
                result+='campaign name:';
                result+='</td>';
                result+='<td>';
                result+=this.string;
                result+='</td>';
                result+='</tr>';
            });
        });
         result+='</table>';
        $("div[id^=allInterestGroupsIDdiv]").html(result);
        $('#allInterestGroupsIDdiv').show('slow');
     }
  );
}	// loadAllInterestGroupInfo	

function loadAllEmailAddressInfo() {
    $.getJSON("/EAIGForProviders.do",
     function(json){   
        var result = '<table border="0">';
        result+='<tr>';
        result+='<td>';
        result+='e-mail address ';
        result+='</td>';
        result+='<td>';
        result+='interest group ';
        result+='</td>';
         result+='<td>';
        result+='provider ';
        result+='</td>';
        result+='</tr>';
    
        $(json.list.addressInterestGroupProvider).each(function() {
            result+='<tr>';
            result+='<td>';
            result+=this.emailAddress;
            result+='</td>';
            result+='<td>';
            result+=this.interestGroup;
            result+='</td>';
             result+='<td>';
            result+=this.provider;
            result+='</td>';
            result+='</tr>';
	    });
        result+='</table>';
        $("div[id^=allEmailAddressIDdiv]").html(result);
        $('#allEmailAddressIDdiv').show('slow');
     }
  );
}	// loadAllEmailAddressInfo	

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


<div id="allInterestGroups">
<span id="allInterestGroupsID"> Display All Interest Groups <a
	href="#allInterestGroupsHref"> <img src="/_m/interest.jpg" width="50"
	height="50" alt="" border="0"></a> 
</span> 
</div> <!--  allInterestGroups -->

<div id="allSentEmailTimeFrame">
<span id="allSentEmailTimeFrameID"> Display E-Mail Sent Over A Period  <a
	href="#allSentEmailTimeFrameHref"> <img src="/_m/sentEmail.jpg" width="50"
	height="50" alt="" border="0"></a> 
</span> 
</div> <!-- allSentEmailTimeFrame -->

<div id="allEmailAddress">
<span id="allEmailAddressID">Display E-Mail Addresses
<a href="#allEmailAddressHref"><img src="/_m/emailAddress.jpg" width="50"
	height="50" alt="" border="0"></a>
</span>	
</div> <!-- allEmailAddress -->

<div id="allInterestGroupsIDdiv">
</div>

<div id="timeframeIDdiv">
<form name="timeframeReportForm" id="timeframeReportID" method="post"
	action="/timeframeProviderReport.do">
<div id="datepickerID">Select two dates to report on sent E-Mail messages:
	<span id="datepickers">
	<input type="text" name="reportTimeFrame"
	id="popupDatepicker" />
	</span>
</div>	<!--  datepickerID  -->
<div id="submit"><input class="button" type="submit"
	id="timeframeIDSubmitdiv" name="submit" value="See All E-Mail Messages for Time Period" />
</div>	<!--  submit  -->
</form>
</div>	<!--   timeframeIDdiv -->

<div id="allTimeFrameIDdiv">
</div>

<div id="allEmailAddressIDdiv">
</div>

</body>
</html>
