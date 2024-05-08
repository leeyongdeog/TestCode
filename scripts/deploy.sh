#!/bin/bash

REPOSITORY=/home/ubuntu/app/step2
PROJECT_NAME=TestCode

echo "> Remove before jar..."
rm $REPOSITORY/*.jar

echo "> Copy Build files..."
cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> Check running PID..."
CURRENT_PID=$(pgrep -fl $PROJECT_NAME | jar | awk '{print $1}')

echo "> Running PID: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "> Not exist running PID..."
else
  echo "> Kill -15 $CURRENT_PID..."
  kill -15 CURRENT_PID
  sleep 5
fi

echo "> Deploy..."
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"
echo "> Set Permission to $JAR_NAME"
chmod +x $JAR_NAME
echo "> Run $JAR_NAME..."

nohup java -jar \
  -Dspring.config.location=classpath:/application.properties,/home/ubuntu/app/application-oauth.properties,/home/ubuntu/app/application-real-db.properties,classpath:/application-real.properties \
  -Dspring.profiles.active=real \
  $JAR_NAME 2>&1 &

echo "> Done!!"