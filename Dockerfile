FROM maven:3-openjdk-17 as target

# copy the project files
COPY ./pom.xml ./pom.xml

# build all dependencies for offline use
RUN mvn dependency:go-offline -B

COPY . /code/
WORKDIR /code

RUN cd /code/ && \
    mvn clean package -DskipTests && \
    mv /code/target/features-0.0.1-SNAPSHOT.jar /app.jar

# for M1 mac use: 'FROM arm64v8/openjdk:17' instead
FROM azul/zulu-openjdk-alpine:17-jre
ENV JAVA_OPTS="-XX:InitialRAMPercentage=80.0 -XX:MaxRAMPercentage=80.0"
EXPOSE 8080

COPY --from=target /app.jar /app.jar
ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar

