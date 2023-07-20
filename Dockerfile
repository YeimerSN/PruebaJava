#
# Build stage
#
FROM maven:3.9.3-sapmachine-17 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM openjdk:22-slim-bullseye
COPY --from=build /target/PruebaBackDesarrolladorJr-0.0.1-SNAPSHOT.jar proyecto.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","proyecto.jar"]