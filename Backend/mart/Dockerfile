FROM openjdk:17
LABEL authors="Samuel"
WORKDIR /app
COPY target/mart-jar-file.jar /app
ENTRYPOINT ["java", "-jar", "/app/mart-jar-file.jar"]
EXPOSE 8080