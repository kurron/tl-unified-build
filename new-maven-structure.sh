#!/bin/bash

if [ "$1" = "" ]
then
 echo "Usage: $0 <name of new folder>"
 exit
fi

PROJ=$1

echo Creating project ${PROJ}

mkdir ${PROJ}
mkdir ${PROJ}/config
mkdir ${PROJ}/config/checkstyle
mkdir ${PROJ}/config/cobertura
mkdir ${PROJ}/src
mkdir ${PROJ}/src/test
mkdir ${PROJ}/src/test/java
mkdir ${PROJ}/src/test/groovy
mkdir ${PROJ}/src/test/resources
mkdir ${PROJ}/src/test/filters
mkdir ${PROJ}/src/main
mkdir ${PROJ}/src/main/webapp
mkdir ${PROJ}/src/main/webapp/images
mkdir ${PROJ}/src/main/webapp/WEB-INF
mkdir ${PROJ}/src/main/webapp/WEB-INF/tags
mkdir ${PROJ}/src/main/webapp/WEB-INF/i18n
mkdir ${PROJ}/src/main/webapp/WEB-INF/spring
mkdir ${PROJ}/src/main/webapp/WEB-INF/classes
mkdir ${PROJ}/src/main/webapp/WEB-INF/layouts
mkdir ${PROJ}/src/main/webapp/WEB-INF/views
mkdir ${PROJ}/src/main/webapp/styles
mkdir ${PROJ}/src/main/resources
mkdir ${PROJ}/src/main/resources/META-INF
mkdir ${PROJ}/src/main/resources/META-INF/spring
mkdir ${PROJ}/src/main/groovy
mkdir ${PROJ}/src/main/java
mkdir ${PROJ}/src/main/filters
mkdir ${PROJ}/src/main/assembly
mkdir ${PROJ}/src/main/scripts

touch ${PROJ}/README.txt
touch ${PROJ}/build.gradle
touch ${PROJ}/config/checkstyle/suppressions.xml
touch ${PROJ}/config/cobertura/cobertura.ignore
