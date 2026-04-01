# Grab Maven + JDK 21
FROM maven:3.9.9-eclipse-temurin-21

# Set working directory
WORKDIR /app

# Copy source & config
COPY pom.xml .
COPY testng.xml .
COPY src ./src

# Cache Maven dependencies
RUN mvn dependency:go-offline -B

# Install Allure CLI
# Install curl, unzip and Allure CLI
RUN apt-get update && apt-get install -y curl unzip && \
    curl -sSL https://github.com/allure-framework/allure2/releases/download/2.35.1/allure-2.35.1.zip -o /tmp/allure.zip && \
    unzip -q /tmp/allure.zip -d /opt && \
    ln -s /opt/allure-2.35.1/bin/allure /usr/bin/allure && \
    rm /tmp/allure.zip

# Default command: run tests + generate Allure report
CMD mvn clean test && \
    allure generate target/allure-results -o target/allure-report --clean && \
    echo "Allure report ready in target/allure-report"