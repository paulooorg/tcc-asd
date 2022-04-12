FROM openjdk:11

RUN mkdir -p /opt/app
COPY target/deposito.jar /opt/app/.

EXPOSE 8088

ENTRYPOINT ["java", "-jar", "./opt/app/deposito.jar"]