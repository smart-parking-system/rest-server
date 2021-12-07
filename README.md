# SPS Rest Server
The goal of this app is to provide a REST API to the SPS Database

## Configuration
Spring Boot server & database connection is configured through `application.properties` file.  
Users are configured through a config file, which is a json file with following structure:
```json
{
  "users": [
    {
      "name": "test",
      "pass": "1234"
    },
    {
      "name": "admin",
      "pass": "abcde"
    }
  ]
}
```

## How To Run
Prerequisites:  
 - Maven  
 - JRE That supports at least java 8  

Steps to run:  
 - Clone the repo  
 - `mvn compile package`  
 - run `sps-rest-server.jar` file with `-c CONFIG_FILE` arguments  
