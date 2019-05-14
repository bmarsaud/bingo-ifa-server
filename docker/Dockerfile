FROM adoptopenjdk/openjdk11:alpine

LABEL maintainer="Bastien Marsaud <bastien@mouseover.fr>"

ARG HOST_PORT
EXPOSE ${HOST_PORT}

RUN mkdir -p /opt/bingo-ifa-server
COPY bingo-ifa-server.jar /opt/bingo-ifa-server
COPY config /opt/bingo-ifa-server/config

CMD ["java", "-jar", "/opt/bingo-ifa-server/bingo-ifa-server.jar"]