<?php
/*
	to use:
	to save test:
		bash> php -q run_text_00.php > eblast-12-22-2009.eml
	to send test:
		bash> php -q run_text_00.php send
*/
require_once("classes/phpmailer.php");
include "define_text_vars_00.php";

define("SAVE", $_SERVER['argc'] < 2 || $_SERVER['argv'][1] != 'send');

// set up the email
$mailer = new PHPMailer();

$mailer->From = FROM_ADDR;
$mailer->FromName = FROM_NAME;
$mailer->Sender = $mailer->From;
$mailer->AddReplyTo(REPLY_ADDR,REPLY_NAME);
$mailer->Subject = MESSAGE_SUBJECT;
$mailer->IsHTML(true);
$mailer->Body = MESSAGE_BODY;

//ADD_EMBEDDED_IMAGES
// none for text

//$mailer->AltBody = MESSAGE_ALT_BODY;
if (SAVE):
//	$mailer->IsSave();  ${XXXXXX_MAIL_SAVE_PATTERN_XXXXXX}
endif;

// testing
//$mailer->AddBCC(TEST_ADDR,TEST_NAME);


// email addresses
// fill this in from database
$list = array(
//${XXXXXX_ARRAY_PATTERN_XXXXXX}
);

// send it
foreach( $list as $user ):
	
	$mailer->ClearAddresses();
	
	$to = $user['email'];
	$name = $user['name'];
	$mailer->AddAddress($to,$name);
	
	if( ! ($result = $mailer->Send()) ):
		echo $mailer->ErrorInfo;
	endif;
	
	
	echo $result;
	
	
	if (SAVE):
		break;
	endif;
endforeach;
