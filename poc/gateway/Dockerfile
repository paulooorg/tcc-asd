FROM openjdk:11

RUN mkdir -p /opt/app
COPY target/gateway.jar /opt/app/.

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./opt/app/gateway.jar"]