FROM openjdk:8-jdk-alpine
FROM maven
RUN mkdir -p /userprofile
WORKDIR /userprofile
COPY . /userprofile
RUN mvn clean install
RUN mvn clean package
EXPOSE 8443
ENTRYPOINT ["mvn", "spring-boot:run"]