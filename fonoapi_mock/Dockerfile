FROM amazoncorretto:17-alpine
VOLUME /tmp
COPY build/libs/fonoapi-service-mock.jar /app/app.jar
COPY docker/readme /app
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "cat /app/readme && java ${JAVA_OPTS} -jar /app/app.jar"]