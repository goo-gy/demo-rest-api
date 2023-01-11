# Build
FROM adoptopenjdk/openjdk11 AS back-build
WORKDIR /build
COPY . .
RUN chmod +x ./gradlew && ./gradlew bootJar

# Run
FROM adoptopenjdk/openjdk11
COPY --from=back-build /build/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]