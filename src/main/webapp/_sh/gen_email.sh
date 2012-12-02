#!/bin/sh
cd $1
for vbl in $(echo *.php);do
   PHPfile=$vbl
   EMLfile=$(echo $PHPfile |sed -e 's=\.php=\.eml=')
  /usr/local/bin/php -d include_path="/home/email_advert/webapps/email_advert/_php:/home/_php:." $PHPfile > $EMLfile
   echo "$EMLfile generated"
done