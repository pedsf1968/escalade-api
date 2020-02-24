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

COPY --from=build /srv/target/escalade.jar /srv/escalade.jar

WORKDIR /srv

RUN sh -c 'touch escalade.jar'
VOLUME /images:/images
EXPOSE 9090
ENTRYPOINT ["java","-Djasypt.encryptor.password=$JASYPT_ENCRYPT_SECRET","-jar","escalade.jar"]

