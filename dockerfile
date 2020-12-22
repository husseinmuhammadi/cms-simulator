#
# Build stage
#
FROM maven:3.6.0-jdk-11 AS build

# Since we want to execute the mvn command with RUN (and not when the container gets started),
# we have to do here some manual setup which would be made by the maven's entrypoint script
RUN mkdir -p /root/.m2 \
    && mkdir /root/.m2/repository
# Copy maven settings, containing repository configurations
COPY settings.xml /root/.m2

WORKDIR /workspace
COPY ./ ./
RUN mvn package -Pstage

#
# Package stage
#
FROM openjdk:11
COPY --from=build /workspace/web-app/target/cms-simulator.jar /app/cms-simulator.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/cms-simulator.jar"]

# docker build -t cms-simulator:1.0.0 ..
# docker run -d -p 8090:8090 --name cms-simulator cms-simulator:1.0.0
