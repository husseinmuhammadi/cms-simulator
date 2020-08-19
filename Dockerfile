#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build

# Since we want to execute the mvn command with RUN (and not when the container gets started),
# we have to do here some manual setup which would be made by the maven's entrypoint script
RUN mkdir -p /root/.m2 \
    && mkdir /root/.m2/repository
# Copy maven settings, containing repository configurations
COPY settings.xml /root/.m2

WORKDIR /app

COPY ./ ./

RUN mvn -f parent package -DskipTests


#
# Package stage
#
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/web-app/target/cms-webapp-1.0.0-SNAPSHOT.jar /app/cms.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/cms.jar"]