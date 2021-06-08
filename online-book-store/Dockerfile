FROM openjdk:8-jdk-alpine
RUN mkdir app
ADD target/online-book-store-0.0.1-SNAPSHOT.jar /app/
WORKDIR app
EXPOSE 8888
CMD ["java","-jar","online-book-store-0.0.1-SNAPSHOT.jar"]