#JAVA 17
FROM openjdk:17

COPY target/desafio.jar desafio.jar

ENTRYPOINT ["java","-jar","/desafio.jar"]