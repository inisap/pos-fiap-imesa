FROM eclipse-temurin:17-jdk-alpine
LABEL authors="diego"

WORKDIR /app

COPY 'target/*.jar' '/app/appImesa.jar'

EXPOSE 9000

CMD ["java", "-jar", "appImesa.jar"]
