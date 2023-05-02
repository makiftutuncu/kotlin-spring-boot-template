FROM arm64v8/eclipse-temurin:20-jre-jammy as builder
WORKDIR /cats-api
COPY . /cats-api
RUN ./gradlew build -x test --console=plain

FROM arm64v8/eclipse-temurin:20-jre-jammy
LABEL maintainer="Mehmet Akif Tütüncü <m.akif.tutuncu@gmail.com>"
WORKDIR /cats-api
COPY --from=builder /cats-api/build/libs/cats-api-*.jar /cats-api/cats-api.jar
ENTRYPOINT ["java", "-jar", "/cats-api/cats-api.jar"]
