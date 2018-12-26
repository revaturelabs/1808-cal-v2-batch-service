FROM java:8
COPY target/BatchService-0.0.1-SNAPSHOT.jar /tmp/BatchService-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/tmp/BatchService-0.0.1-SNAPSHOT.jar","--server.servlet.context-path=/batch","&"]
