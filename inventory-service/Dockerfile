# Multi-stage build for smaller image size
FROM maven:3.9-eclipse-temurin-17-alpine AS build

WORKDIR /build

# Copy parent pom first
COPY pom.xml .

# Copy both modules (Maven reactor needs to see all modules)
COPY inventory-service/pom.xml ./inventory-service/
COPY inventory-service/src ./inventory-service/src
COPY web-service/pom.xml ./web-service/

# Build only inventory-service (but Maven needs to see web-service pom)
RUN mvn clean package -DskipTests -pl inventory-service -am

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the built jar from build stage
COPY --from=build /build/inventory-service/target/*.jar app.jar

# Expose port (Render will set PORT env variable)
EXPOSE 8081

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s \
  CMD wget --no-verbose --tries=1 --spider http://localhost:${PORT:-8081}/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
