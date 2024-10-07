FROM amazoncorretto:17.0.12-alpine
COPY target/*.jar app.jar
WORKDIR /
ENTRYPOINT [ "java","-jar","app.jar" ]