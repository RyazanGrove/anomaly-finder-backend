# Anomaly Finder Backend
Backend application for Anomaly Finder game. Provides API for generating a new sequence of images,where player should correctly locate the target, and storing the scores.

## Related project repositories
[Anomaly Finder Frontend](https://github.com/RyazanGrove/anomaly-finder-frontend)

## Table of Contents
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [Documentation](#documentation)
- [Configuration](#configuration)
- [Testing](#testing)

## Requirements
* Java 17.0.11+
* Application uses Spring Boot 3.3.2

## Installation
Project uses Gradle to build the application.
1. Clone the repository:
```bash
git clone https://github.com/RyazanGrove/anomaly-finder-backend.git
```
2. Navigate to the project directory:
```bash
cd anomaly-finder-backend
```
3. Build the project and download dependencies:
```bash
./gradlew build
```
4. Run the application:
```bash
./gradlew bootRun
```

## Usage
To start the application, use the following command:
```bash
./gradlew bootRun
```

### H2 Database Console
By default application stores all the data in H2 Runtime Database, which could be accessed using this link:
`http://localhost:8080/h2-console/`

## Documentation
### API Documentation and Interactive Swagger UI
Application uses automatic documentation generation tools. APIs, Data Schemas and Methods' descriptions could be found in Swagger UI:
`http://localhost:8080/swagger-ui/index.html`

## Configuration
The application can be configured via the `src/main/resources/application.properties` file

## Testing
All tests are located in `src/test/java/com/ryazangrove/anomaly_finder_backend` folder and could be run using command:
```
./gradlew clean test
```
To see more detailed tests output you can run:
```
./gradlew clean test --info
```