FROM maven:3.5-jdk-8-alpine

EXPOSE 8080

RUN apk update && apk upgrade && \
    apk add --no-cache bash git openssh

RUN git clone https://github.com/blackenedsystems/random-sort.git
WORKDIR /random-sort
RUN mvn clean package

CMD java -jar target/random-sort-0.0.1-SNAPSHOT.jar
