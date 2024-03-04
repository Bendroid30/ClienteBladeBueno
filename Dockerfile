FROM openjdk:17
WORKDIR /
ADD ServidorBlade.jar ServidorBlade.jar
EXPOSE 9000
CMD java -jar ServidorBlade.jar