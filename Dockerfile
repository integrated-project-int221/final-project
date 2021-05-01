From openjdk:14
copy ./target/spa.project-0.0.1-SNAPSHOT.jar spa.project-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","spa.project-0.0.1-SNAPSHOT.jar"]
