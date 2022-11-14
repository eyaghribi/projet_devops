##use an openJDK Runtime as a parent image
FROM openjdk:8-jre-alpine
ADD target/tpAchatProject-1.0.jar tpAchatProject-1.0.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "tpAchatProject-1.0.jar"]
