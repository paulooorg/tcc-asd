FROM openjdk:11

RUN mkdir -p /opt/app
COPY target/documentacao-api.jar /opt/app/.

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "./opt/app/documentacao-api.jar"]