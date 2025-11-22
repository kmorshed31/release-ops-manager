FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY target/releaseops-0.0.1-SNAPSHOT.jar /app/releaseops.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/releaseops.jar"]
