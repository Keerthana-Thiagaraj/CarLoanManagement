#FROM openjdk:8-jdk-alpine
#ADD target/ServiceDiscovery-Service.jar ServiceDiscovery-Service.jar
#EXPOSE 8761
#ENTRYPOINT ["java", "-jar", "/ServiceDiscovery-Service.jar"]

FROM openjdk:8-jdk-alpine
FROM maven
RUN mkdir -p /servicediscovery
WORKDIR /servicediscovery
COPY . /servicediscovery
RUN mvn clean install
RUN mvn clean package
EXPOSE 8761
ENTRYPOINT ["mvn", "spring-boot:run"]