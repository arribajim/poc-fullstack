FROM amazoncorretto:17.0.12-alpine
RUN mkdir /app
COPY target/*.jar /app/app.jar
WORKDIR /app
ENTRYPOINT [ "java","jar","/app/app.jar" ]