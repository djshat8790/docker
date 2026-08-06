# ========================================================
# STAGE 1: Dependency Cache & Compilation
# ========================================================
FROM maven:3.9.5-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy POM and download dependencies offline (Cached Layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source and compile tests
COPY src ./src
RUN mvn test-compile -B

# ========================================================
# STAGE 2: Minimal Runtime Execution Container
# ========================================================
FROM eclipse-temurin:17-jre

# Security Best Practice: Run as non-root user
RUN groupadd -r qa && useradd -r -g qa qa
USER qa

WORKDIR /home/qa/app

# Copy cached dependencies and compiled binaries from builder stage
COPY --chown=qa:qa --from=builder /root/.m2 /home/qa/.m2
COPY --chown=qa:qa --from=builder /app /home/qa/app

ENTRYPOINT ["mvn", "test"]