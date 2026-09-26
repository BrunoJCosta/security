FROM amazoncorretto:21

COPY target/security-0.0.1-SNAPSHOT.jar /app/security-0.0.1-SNAPSHOT.jar

EXPOSE 2734

CMD ["java", "-jar", "/app/security-0.0.1-SNAPSHOT.jar"]