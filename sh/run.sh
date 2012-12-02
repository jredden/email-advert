#!/bin/sh
cd /home/email_advert/webapps/email_advert/WEB-INF/classes
export JAVA_HOME=/usr/local/java
export PATH=$PATH:/usr/local/java/bin:/usr/local/bin


export EA_HOME=/home/email_advert/webapps/email_advert/WEB-INF
JARLIB=$EA_HOME/lib
CLASSPATH=.
CLASSPATH=$CLASSPATH:$JARLIB/activation-1.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/ant-1.5.jar
CLASSPATH=$CLASSPATH:$JARLIB/ant-1.7.0.jar
CLASSPATH=$CLASSPATH:$JARLIB/ant-launcher-1.7.0.jar
CLASSPATH=$CLASSPATH:$JARLIB/antlr-3.1.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/aopalliance-1.0.jar
CLASSPATH=$CLASSPATH:$JARLIB/asm-3.0.jar
CLASSPATH=$CLASSPATH:$JARLIB/asm-attrs-1.5.4.jar
CLASSPATH=$CLASSPATH:$JARLIB/asm-commons-2.2.3.jar
CLASSPATH=$CLASSPATH:$JARLIB/asm-tree-2.2.3.jar
CLASSPATH=$CLASSPATH:$JARLIB/asm-util-2.2.3.jar
CLASSPATH=$CLASSPATH:$JARLIB/aspectj-1.6.8.jar
CLASSPATH=$CLASSPATH:$JARLIB/aspectjrt-1.5.4.jar
CLASSPATH=$CLASSPATH:$JARLIB/aspectjweaver-1.5.4.jar
CLASSPATH=$CLASSPATH:$JARLIB/backport-util-concurrent-3.0.jar
CLASSPATH=$CLASSPATH:$JARLIB/c3p0-0.9.1.2.jar
CLASSPATH=$CLASSPATH:$JARLIB/carol-2.0.5.jar
CLASSPATH=$CLASSPATH:$JARLIB/cglib-nodep-2.1_3.jar
CLASSPATH=$CLASSPATH:$JARLIB/commonj-twm-1.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/commons-attributes-api-2.2.jar
CLASSPATH=$CLASSPATH:$JARLIB/commons-attributes-compiler-2.2.jar
CLASSPATH=$CLASSPATH:$JARLIB/commons-collections-3.2.jar
CLASSPATH=$CLASSPATH:$JARLIB/commons-fileupload-1.2.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/commons-io-1.3.2.jar
CLASSPATH=$CLASSPATH:$JARLIB/commons-lang-2.3.jar
CLASSPATH=$CLASSPATH:$JARLIB/commons-logging-1.1.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/commons-pool-1.4.jar
CLASSPATH=$CLASSPATH:$JARLIB/connector-1.0.jar
CLASSPATH=$CLASSPATH:$JARLIB/dom4j-1.6.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/ejb-3_0.jar
CLASSPATH=$CLASSPATH:$JARLIB/freemarker-2.3.12.jar
CLASSPATH=$CLASSPATH:$JARLIB/hamcrest-all-1.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/hamcrest-core-1.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/hamcrest-library-1.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/hibernate-3.2.5.jar
CLASSPATH=$CLASSPATH:$JARLIB/hibernate-entitymanager-3.3.2.ga.jar
CLASSPATH=$CLASSPATH:$JARLIB/howl-logger-0.1.11.jar
CLASSPATH=$CLASSPATH:$JARLIB/ibatis-sqlmap-2.3.0.677.jar
CLASSPATH=$CLASSPATH:$JARLIB/imageinfo-1.9.jar
CLASSPATH=$CLASSPATH:$JARLIB/jamon-2.4.jar
CLASSPATH=$CLASSPATH:$JARLIB/jaxrpc-api-1.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/jdeo2-api-2.jar
CLASSPATH=$CLASSPATH:$JARLIB/jettison-1.0.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/jmock-2.4.0.jar
CLASSPATH=$CLASSPATH:$JARLIB/jmock-junit3-2.4.0.jar
CLASSPATH=$CLASSPATH:$JARLIB/jmock-junit4-2.4.0.jar
CLASSPATH=$CLASSPATH:$JARLIB/jmock-legacy-2.4.0.jar
CLASSPATH=$CLASSPATH:$JARLIB/jms-1.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/jotm-2.0.10.jar
CLASSPATH=$CLASSPATH:$JARLIB/jotm_jrmp_stubs-2.0.10.jar
CLASSPATH=$CLASSPATH:$JARLIB/jta-1.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/junit-dep-4.4.jar
CLASSPATH=$CLASSPATH:$JARLIB/jxl-2.6.6.jar
CLASSPATH=$CLASSPATH:$JARLIB/log4j-1.2.14.jar
CLASSPATH=$CLASSPATH:$JARLIB/mysql-connector-java-5.1.6.jar
CLASSPATH=$CLASSPATH:$JARLIB/objenesis-1.0.jar
CLASSPATH=$CLASSPATH:$JARLIB/odmg-3.0.jar
CLASSPATH=$CLASSPATH:$JARLIB/org.springframework-spring-aspects-2.5.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/org.springframework-spring-beans-2.5.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/org.springframework-spring-core-2.5.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/org.spring-spring-aspects-2.5.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/org.spring-spring-beans-2.5.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/org.spring-spring-core-2.5.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/oro-2.0.8.jar
CLASSPATH=$CLASSPATH:$JARLIB/qdox-1.5.jar
CLASSPATH=$CLASSPATH:$JARLIB/quartz-1.5.2.jar
CLASSPATH=$CLASSPATH:$JARLIB/resin-4.0.8.jar
CLASSPATH=$CLASSPATH:$JARLIB/saaj-api-1.3.jar
CLASSPATH=$CLASSPATH:$JARLIB/spring-agent-2.5.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/spring-aop-2.5.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/spring-context-2.5.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/spring-context-support-2.5.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/spring-jdbc-2.5.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/spring-orm-2.5.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/spring-test-2.5.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/spring-tx-2.5.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/spring-web-2.5.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/spring-webmvc-2.5.1.jar
CLASSPATH=$CLASSPATH:$JARLIB/standard-1.1.2.jar
CLASSPATH=$CLASSPATH:$JARLIB/tools-1.6.0_04.jar
CLASSPATH=$CLASSPATH:$JARLIB/toplink-10.1.3.jar
CLASSPATH=$CLASSPATH:$JARLIB/toplink-essentials-2.41.jar
CLASSPATH=$CLASSPATH:$JARLIB/xalan-2.7.0.jar
CLASSPATH=$CLASSPATH:$JARLIB/xml-apis-1.0.b2.jar
CLASSPATH=$CLASSPATH:$JARLIB/xpp3_min-1.1.4c.jar
CLASSPATH=$CLASSPATH:$JARLIB/xstream-1.3.1.jar


java -classpath $CLASSPATH com.zenred.eadvert.app.ScanForProductionMessage 2>stderr 1>stdout  

echo "============ stdout $(/bin/date +%m%d%y) ============" >> log.log
/bin/cat stdout >> log.log  
echo "============ stderr $(/bin/date +%m%d%y) ============" >> log.log
/bin/cat stderr >> log.log  
