FROM openjdk:8-jdk-alpine

LABEL maintainer="shreyas.ramanath@idexcel.net"

EXPOSE 8080

COPY ./target/shreyasRamnath-admin-service-0.0.1-SNAPSHOT.jar shreyasRamnath-admin-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","shreyasRamnath-admin-service-0.0.1-SNAPSHOT.jar"]	