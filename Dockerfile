FROM openjdk:11
EXPOSE 9090
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} lab-wp.jar
ENTRYPOINT ["java", "-jar", "lab-wp.jar"]