FROM openjdk:8-jdk-alpine

COPY ./target/escalade-0.0.1-SNAPSHOT.jar /usr/app/escalade.jar

WORKDIR /usr/app

RUN sh -c 'touch escalade.jar'

ENTRYPOINT ["java","-jar","escalade.jar"]