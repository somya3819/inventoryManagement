# Multi-stage build for smaller image size
FROM maven:3.9-eclipse-temurin-17-alpine AS build

WORKDIR /build

# Copy parent pom first
COPY pom.xml .

# Copy both modules (Maven reactor needs to see all modules)
COPY web-service/pom.xml ./web-service/
COPY web-service/src ./web-service/src
COPY inventory-service/pom.xml ./inventory-service/

# Build only web-service (but Maven needs to see inventory-service pom)
RUN mvn clean package -DskipTests -pl web-service -am

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the built jar from build stage
COPY --from=build /build/web-service/target/*.jar app.jar

# Expose port (Render will set PORT env variable)
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s \
  CMD wget --no-verbose --tries=1 --spider http://localhost:${PORT:-8080}/ || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
