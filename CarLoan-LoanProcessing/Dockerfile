FROM openjdk:8-jdk-alpine
FROM maven
RUN mkdir -p /loanprocessing
WORKDIR /loanprocessing
COPY . /loanprocessing
RUN mvn clean install
RUN mvn clean package
EXPOSE 8444
ENTRYPOINT ["mvn", "spring-boot:run"]
