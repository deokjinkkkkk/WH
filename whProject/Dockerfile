FROM openjdk:11-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY wallet_quiz/* /home/ubuntu/Wallet_db4jo2023
ENV TZ=Asia/Seoul
ENTRYPOINT ["java","-jar", "/app.jar"]