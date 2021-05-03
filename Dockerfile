#From openjdk:14
#copy ./target/spa.project-0.0.1-SNAPSHOT.jar spa.project-0.0.1-SNAPSHOT.jar
#CMD ["java","-jar","spa.project-0.0.1-SNAPSHOT.jar"]

### STAGE 1: Build ###
FROM maven:3.6.3-openjdk-14-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -f pom.xml clean package

### STAGE 2: Run ###
FROM openjdk:14
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 3000
ENTRYPOINT ["java","-jar","app.jar"]
