# SPS Rest Server
The goal of this app is to provide a REST API to the SPS Database

## Config File
Config File is a json file with following structure:
```json
{
  "ip": "localhost",
  "port": 8080,
  "db": {
    "url": "jdbc:postgresql:sps",
    "user": "username",
    "pass": "password"
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

## How To Run
Prerequisites:  
 - Maven  
 - JRE That supports java 8  

Steps to run:  
 - Clone the repo  
 - `mvn compile package`  
 - run `sps-rest-server-0.1-jar-with-dependencies.jar` file with `-c CONFIG_FILE` arguments  
