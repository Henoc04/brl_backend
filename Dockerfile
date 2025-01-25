FROM maven:3.9.7-sapmachine-22 AS build

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /build/target/brl-0.0.1-SNAPSHOT.jar /app/brl-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "brl-0.0.1-SNAPSHOT.jar"]