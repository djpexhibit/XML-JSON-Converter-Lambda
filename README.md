# XML-JSON-Converter-Lambda

## Overview

`XML-JSON-Converter-Lambda` is a sample lambda implementation of converting XML - JSON. This implemented as a microservice on top of `AWS`. 

Purpose of this project is to provide serverless microservice implementation using the following technologies.

- AWS Services
  - AWS API Gateway and Lambda
- Programming Language
  - JAVA 8
- Build Tool
  - Gradle
- Unit Testing
  - Junit

## Prerequisites
  - Create an AWS account [https://aws.amazon.com/]
  - Configure AWS CLI [http://docs.aws.amazon.com/cli/latest/userguide/cli-chap-getting-started.html]
  
## Architecture
![](https://image.ibb.co/dFqXwd/converter.png)

## Design and Implementation
  - Command (invoke) pattern is used to invoke service as requested by API gateway
  - Service is designed & implemented carefully to improve the testability. Thus making it more maintainable, robust.

## API endpoints 

| Resource | `POST` |
| ------ |  ------ | 
| `/v1/json-to-xml` | Convert JSON to XML output | 
| `/v1/xml-to-json` | Convert XML to JSON |

## Sample Request / Response

Following shows a sample a request for the resource `/v1/json-to-xml` for the `POST` operation.

```
{
  "testKey" : "testValue"
}
```

```
{
  "genXML" : "<?xml version=\'1.0\' encoding=\'UTF-8\' ?> <testKey> testValue </testKey>
}
```

### Note : In json-to-xml, response is in json format which includes generated XML as a string value to support API gateway in order to track requests/responses
