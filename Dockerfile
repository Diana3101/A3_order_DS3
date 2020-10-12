FROM java:8

EXPOSE 8084

ADD target/A3_order-1.0-SNAPSHOT.jar A3_order-1.0-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "A3_order-1.0-SNAPSHOT.jar"]