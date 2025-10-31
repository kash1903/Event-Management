# ============================================================
# 🏗️ Stage 1: Build the application
# ============================================================
FROM maven:3.9.9-eclipse-temurin-17 AS builder

# Set working directory inside container
WORKDIR /app

# Copy pom.xml and download dependencies first (layer caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy entire project and build the jar
COPY . .
RUN mvn clean package -DskipTests

# ============================================================
# 🚀 Stage 2: Run the application
# ============================================================
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy jar from build stage
COPY --from=builder /app/target/*.jar app.jar

# Expose application port (Render expects 8080)
EXPOSE 8080

# Define environment variables (Render will override these)
ENV SPRING_PROFILES_ACTIVE=prod

# Start the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
