#
# Build stage
#
FROM maven:3.5.2-jdk-8-alpine AS build
COPY src /srv/src
COPY pom.xml /srv
RUN mvn -f /srv/pom.xml clean package -DskipTests=true


#
# Package stage
#
FROM openjdk:8-jdk-alpine

RUN mkdir /TMP
RUN mkdir /images
RUN mkdir /images/background
RUN mkdir /images/avatar
RUN mkdir /images/topo
RUN mkdir /images/sector
RUN mkdir /images/voie

COPY ./images/background/homebg.jpg /images/background
COPY ./images/background/simplebg.jpg /images/background
COPY ./images/avatar/*.* /images/avatar/
COPY ./images/topo/*.* /images/topo/
COPY ./images/sector/*.* /images/sector/
COPY ./images/voie/*.* /images/voie/


COPY --from=build /srv/target/escalade.jar /srv/escalade.jar


WORKDIR /srv

RUN sh -c 'touch escalade.jar'
EXPOSE 9090
ENTRYPOINT ["java","-Djasypt.encryptor.password=Jasypt1418$","-jar","escalade.jar"]

