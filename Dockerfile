FROM openjdk:21

COPY target/matchodds-0.0.1-SNAPSHOT.jar matchodds_app.jar

ENTRYPOINT ["java", "-jar", "matchodds_app.jar"]