FROM arm64v8/eclipse-temurin:17-jre-jammy as builder
COPY . .
RUN ./gradlew build -x test --console=plain

FROM arm64v8/eclipse-temurin:17-jre-jammy
LABEL maintainer="Mehmet Akif Tütüncü <m.akif.tutuncu@gmail.com>"
COPY --from=builder build/libs/cats-api-*.jar cats-api.jar
ENTRYPOINT ["java", "-jar", "cats-api.jar"]
