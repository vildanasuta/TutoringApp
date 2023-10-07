FROM ubuntu:22.04 AS builder

# Make sure the package repository is up to date.
RUN apt-get update && \
    apt-get install -qy git && \
    apt-get install -y wget && \
# Install JDK 17 and Maven 3.8.6
    apt-get install -qy openjdk-17-jdk && \ 
    wget https://dlcdn.apache.org/maven/maven-3/3.8.7/binaries/apache-maven-3.8.7-bin.tar.gz -P /tmp && \
    tar xf /tmp/apache-maven-*.tar.gz -C /opt && \
    ln -s /opt/apache-maven-3.8.7 /opt/maven

#Enviornment variables for Maven and Java
ENV JAVA_HOME=/usr/lib/jvm/java-1.17.0-openjdk-amd64 
ENV M2_HOME=/opt/maven 
ENV MAVEN_HOME=/opt/maven 
ENV PATH=${M2_HOME}/bin:${PATH}

COPY . .

RUN mvn clean install

FROM openjdk:17

COPY --from=builder ./app/target/ .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./app-0.0.1-SNAPSHOT.jar"]
