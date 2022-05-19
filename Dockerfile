FROM openjdk:11
COPY "./target/back-test-tta-0.0.1-SNAPSHOT.jar" "app.jar" 
EXPOSE 8683
ENTRYPOINT ["java","-jar","app.jar"]