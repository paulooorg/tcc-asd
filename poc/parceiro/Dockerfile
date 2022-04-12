FROM openjdk:11

RUN mkdir -p /opt/app
COPY target/parceiro.jar /opt/app/.

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "./opt/app/parceiro.jar"]