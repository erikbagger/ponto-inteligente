FROM springio/gs-spring-boot-docker
MAINTAINER lordbagger
COPY ./target/ponto-inteligente.jar /home/ponto-inteligente-api/
WORKDIR /home/ponto-inteligente-api/
ENTRYPOINT java -jar ponto-inteligente.jar
EXPOSE 8080