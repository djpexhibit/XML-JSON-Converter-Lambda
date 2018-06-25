# XML-JSON-Converter-Lambda

## Overview

`XML-JSON-Converter-Lambda` is a general purpose lambda implementation of converting XML - JSON. This implemented as a microservice on top of `AWS`. 

Purpose of this project is to provide serverless microservice implementation using the following technologies.

- AWS Services
  - AWS API Gateway, Lambda and DynamoDB
- Programming Language
  - JAVA 8
- Build Tool
  - Gradle
- Unit Testing
  - Junit

# Prerequisites
  - Create an AWS account [https://aws.amazon.com/]
  - Configure AWS CLI [http://docs.aws.amazon.com/cli/latest/userguide/cli-chap-getting-started.html]
  
# Architecture
![](https://image.ibb.co/exrk0T/Untitled_Diagram_2.png)


# Design and Implementation
  - Command (invoke) pattern is used to invoke service as requested by API gateway
  - Service is designed & implemented carefully to improve the testability. Thus making it more maintainable, robust.
