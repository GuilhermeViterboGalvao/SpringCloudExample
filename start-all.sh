#!/usr/bin/env bash

currentPath=$(dirname "$0")

command -v java > /dev/null 2>&1 || { echo "Command 'java' was not found.\nPlease install JDK 8.\nFinishing the execution." >&2; exit 1; }
command -v mvn > /dev/null 2>&1 || { echo "Command 'mvn' was not found.\nPlease install Apache Maven.\nFinishing the execution." >&2; exit 1; }

mvn clean install

echo "Starting in background Remote Config App..."
nohup java -jar -Dspring.profiles.active=dev config/target/config-1.0-SNAPSHOT-exec.jar > /dev/null 2>&1 &
echo "Sleep for 10 seconds to run the next command..."
sleep 10
echo "Starting in background Eureka App..."
nohup java -jar -Dspring.profiles.active=dev eureka/target/eureka-1.0-SNAPSHOT-exec.jar > /dev/null 2>&1 &
echo "Sleep for 10 seconds to run the next command..."
sleep 10
echo "Starting in background Zuul App..."
nohup java -jar -Dspring.profiles.active=dev zuul/target/zuul-1.0-SNAPSHOT-exec.jar > /dev/null 2>&1 &
echo "Sleep for 10 seconds to run the next command..."
sleep 10
echo "Starting in background Contacts App..."
nohup java -jar -Dspring.profiles.active=dev contacts/target/contacts-1.0-SNAPSHOT-exec.jar > /dev/null 2>&1 &
sleep 10
echo "Sleep for 10 seconds to run the next command..."
echo "Execution finished"
echo "**********************"
echo "Remote Config Server: "
echo "  Health URL: http://localhost:8001/health"
echo "  Check Remote Config of Contacts example: http://localhost:8001/ms-contacts-dev/dev/dev"
echo "**********************"
echo "Eureka: "
echo "  Health URL: http://localhost:8002/health"
echo "  Registred Apps: http://localhost:8002/"
echo "**********************"
echo "Zuul: "
echo "  Health URL: http://localhost:8003/actuator/health"
echo "  Proxing Contacts Swagger URL: http://localhost:8003/ms-contacts-dev/swagger-ui.html"
echo "**********************"
echo "Contacts: "
echo "  Health URL: http://localhost:8004/health"
echo "  Swagger URL: http://localhost:8004/swagger-ui.html"