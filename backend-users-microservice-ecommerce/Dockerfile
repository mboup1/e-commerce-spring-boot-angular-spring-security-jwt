FROM openjdk:17-jdk-slim

COPY target/backend-users-microservice-ecommerce-0.0.1-SNAPSHOT.jar /app/backend-users-microservice-ecommerce.jar

WORKDIR /app

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "backend-users-microservice-ecommerce.jar"]
