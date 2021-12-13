# SPS Rest Server
The goal of this app is to provide a REST API to the SPS Database

## Configuration
Spring Boot server & database connection is configured through `application.properties` file.  
Users & auth are configured through a config file, which is a json file with following structure:
```json
{
  "security": {
    "iv": "some_init_vec",
    "key": "some_key"
  },
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

## Authorization
Some APIs require the user to authorize.  
For those APIs you'll need to provide `X_SPS_USER_DATA` HTTP header in your request.  
The value of that header is `USER:PASSWORD` encrypted using AES with `key` and `iv`, that are specified in the server config, and encoded with base64.  

## How To Run
You can either download the latest [release](https://github.com/smart-parking-system/rest-server/releases) or build from sources  

Prerequisites:  
 - Maven  
 - JRE That supports at least java 8  

Steps to build:  
 - Clone the repo  
 - `mvn compile package`  
 - run `sps-rest-server.jar` file with `-c CONFIG_FILE` arguments  
