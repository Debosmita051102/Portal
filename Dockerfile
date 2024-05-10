FROM openjdk:20
ADD target/PortalRestAPI.jar /PortalRestAPI.jar
EXPOSE 6002
ENTRYPOINT ["java", "-jar", "PortalRestAPI.jar"]
