FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

ADD target/*.jar app.jar

EXPOSE 8080
CMD ["java","-jar","/app/app.jar"]