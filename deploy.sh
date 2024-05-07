#!/bin/bash

REPOSITORY=/home/ubuntu/app/step1
PROJECT_NAME=TestCode

cd $REPOSITORY/$PROJECT_NAME/

echo "> Git Pull..."
git pull origin yorer

echo "> Start Build..."
./gradlew build

echo "> cd step1..."
cd $REPOSITORY

echo "> Copy jar To step1 folder..."
cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/

echo "> Check exist PID..."
CURRENT_PID=$(pgrep -f ${PROJECT_NAME}.*.jar)

echo "> Current Running PID: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
    echo "> Not exist Running PID"
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> Deploy Application..."
JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"
nohup java -jar \
-Dspring.config.location=classpath:/application.properties \
-Dspring.config.location=/home/ubuntu/app/application-oauth.properties \
-Dspring.config.location=/home/ubuntu/app/application-real-db.properties \
-Dspring.config.location=classpath:/application-real.properties \
-Dspring.profiles.active=real \
$REPOSITORY/$JAR_NAME 2>&1 &

echo "Done!!"

