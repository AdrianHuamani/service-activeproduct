FROM openjdk:11
VOLUME /tmp
EXPOSE 8082
ADD ./target/activeProduct-0.0.1-SNAPSHOT.jar ms-activeproduct.jar
ENTRYPOINT ["java","-jar","/ms-activeproduct.jar"]
