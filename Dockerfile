#FROM openjdk-8-slim
FROM adoptopenjdk/maven-openjdk8:latest AS build
RUN mkdir /app
WORKDIR /app
COPY . /app
RUN mvn clean package -Pshade -DskipTests

FROM adoptopenjdk/maven-openjdk8:latest
RUN mkdir /app
COPY --from=build /app/target/min-triangle-path-1.0-SNAPSHOT.jar /app/min-triangle-path-1.0-SNAPSHOT.jar
COPY --from=build /app/data.sample.txt /app/data.sample.txt
COPY --from=build /app/be_data_small.txt /app/be_data_small.txt
COPY --from=build /app/be_data_big.txt /app/be_data_big.txt
COPY --from=build /app/docker_run_test.sh /app/docker_run_test.sh
WORKDIR /app