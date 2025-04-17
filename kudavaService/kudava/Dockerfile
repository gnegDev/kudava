FROM maven:3.9.9
LABEL authors="gnegDev"

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

ENTRYPOINT ["java","-jar","./target/kudava-0.0.1-SNAPSHOT.jar"]
