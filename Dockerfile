# Step 1: Use a Maven image with JDK 17
FROM maven:3.9.6-eclipse-temurin-17

# Step 2: Install Chrome for Headless testing
RUN apt-get update && apt-get install -y wget gnupg \
    && wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list \
    && apt-get update && apt-get install -y google-chrome-stable

# Step 3: Set working directory
WORKDIR /app

# Step 4: Copy your project files
COPY . .

# Step 5: Run the tests when the container starts
ENTRYPOINT ["mvn", "test", "-Dheadless=true"]