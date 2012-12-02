<?php
 require_once("classes/phpmailer.php");
ob_start();
?>
{0}
<p>If you do not wish to receive future e-mails from our system click on this link <a href="www.email_advert.com/optOut.do?emailAddress={9}">Please un-register my e-mail address</a></p>
<?
$buffer = ob_get_contents();
ob_clean();
define("MESSAGE_BODY", $buffer);
?>
<?
$buffer = ob_get_clean();

$mailer = new PHPMailer();
$mailer->From = "{1}";   				
$mailer->FromName = "{2}";			
$mailer->Sender = $mailer->From;
$mailer->AddReplyTo("{3}","{4}");
$mailer->Subject = "{5}";
$mailer->IsHTML(true);
$mailer->Body = MESSAGE_BODY;
{6}
$mailer->IsSave();
$mailer->AddAddress("{7}","{8}");
if( ! ($result = $mailer->Send()) ):
		echo $mailer->ErrorInfo;
endif;
echo $result;

