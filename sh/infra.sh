#!/bin/sh
mkdir userfiles
mkdir userfiles/qa
mkdir userfiles/review
mkdir userfiles/prod

chown resin:www-data  userfiles
chown www-data:www-data  userfiles/qa
chown www-data:www-data  userfiles/review
chown www-data:www-data  userfiles/prod

chmod 775  userfiles
chmod 775  userfiles/qa
chmod 775  userfiles/review
chmod 775  userfiles/prod

chmod o+x webapps/email_advert/_sh/gen_email.sh
chmod g+x webapps/email_advert/_sh/gen_email.sh
