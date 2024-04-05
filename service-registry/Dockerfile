FROM openjdk:18-jdk

COPY target/*.jar app.jar

COPY wait-for.sh .

ENTRYPOINT ["java","-jar","app.jar"]