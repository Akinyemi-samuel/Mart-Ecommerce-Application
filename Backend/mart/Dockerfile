FROM openjdk:17
LABEL authors="Samuel"
WORKDIR /app
COPY target/mart-file.jar /app
ENTRYPOINT ["java", "-jar", "/app/mart-file.jar"]
EXPOSE 8080