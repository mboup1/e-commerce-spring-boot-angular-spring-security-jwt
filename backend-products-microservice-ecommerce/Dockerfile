FROM openjdk:17-jdk-slim

COPY target/backend-products-microservice-ecommerce-0.0.1-SNAPSHOT.jar /app/backend-products-microservice-ecommerce.jar

WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "backend-products-microservice-ecommerce"]
