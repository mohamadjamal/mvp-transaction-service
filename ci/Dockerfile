###################################
###      Build Environment      ###
###################################

## 1. Specify base image or sdk image


FROM maven:3.6.3-jdk-11 AS build-env


## 2. Install Git
RUN apt-get update
RUN apt-get install -y git

## 3. Checkout code
WORKDIR /usr/src/app
RUN git clone https://github.com/mohamadjamal/mvp-transaction-service.git

## 4. Update work directory
WORKDIR /usr/src/app/mvp-transaction-service

## 5. Package the artifact
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
COPY --from=build-env /usr/src/app/mvp-transaction-service/target/mvp-transaction-service-0.0.1-SNAPSHOT.jar app.jar

## 4. Expose application port
EXPOSE 8080

## 5. Start application using CMD or Entrypoint
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar" ]
