FROM openjdk:8-jdk-alpine

COPY ./target/escalade-0.0.1-SNAPSHOT.jar /srv/escalade.jar
VOLUME /srv
WORKDIR /srv

RUN sh -c 'touch escalade.jar'

ENTRYPOINT ["java","-jar","escalade.jar"]