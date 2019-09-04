FROM openjdk:8-jdk-alpine

LABEL maintainer="shreyas.ramanath@idexcel.net"

EXPOSE 8080

WORKDIR /usr/local/bin

COPY ./target/shreyasRamnath-admin-service-0.0.1-SNAPSHOT.jar shreyasRamnath-admin-service-0.0.1-SNAPSHOT.jar

CMD ["java","-jar","shreyasRamnath-admin-service-0.0.1-SNAPSHOT.jar"]