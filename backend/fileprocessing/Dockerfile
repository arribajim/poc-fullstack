FROM amazoncorretto:17.0.12-alpine

RUN mkdir /app
COPY backend/fileprocessing/target/*.jar /app/app.jar
WORKDIR /app
ENTRYPOINT [ "java","jar","/app/app.jar" ]