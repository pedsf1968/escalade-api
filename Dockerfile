
FROM openjdk:8-jdk-alpine

EXPOSE 9090

# Copy escalade app
COPY ./target/escalade.jar /srv/escalade.jar

# Set working directory
WORKDIR /srv

RUN sh -c 'touch escalade.jar'

ENTRYPOINT ["java","-jar","escalade.jar"]