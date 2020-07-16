###################################
###      Build Environment      ###
###################################

## 1. Specify base image or sdk image


FROM maven:3.6.3-jdk-11 AS build-env


## 2. Create app directory as workdir
WORKDIR /usr/src/app

## 3. Copy files which contains project dependencies
COPY pom.xml /usr/src/app

## 4. Restore Dependencies
RUN mvn -B dependency:resolve dependency:resolve-plugins

## 5. Copy all source codes from application / microservice
COPY src /usr/src/app

## 6. Package the artifact
RUN mvn package

###################################
###     Runtime Environment     ###
###################################

## 1. Specify runtime image


FROM openjdk:11-jdk-slim as runtime


## 2. Specify work directory
WORKDIR /app
ENV JAVA_OPTS=""

## 3. Copy build output from Build environment 
COPY --from=build-env /usr/src/app/target/mvp-transaction-service-0.0.1-SNAPSHOT.jar app.jar

## 4. Expose application port
EXPOSE 8080

## 5. Start application using CMD or Entrypoint
# ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar" ]
ENTRYPOINT [ "java", "-jar", "/app.jar" ]
