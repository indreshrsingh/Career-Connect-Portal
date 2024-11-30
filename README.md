# Microservices Application with Docker

This repository contains a microservices-based application using Docker. Below are the details of the services and their corresponding Docker images.

## Microservices Overview

| Service Name      | Docker Image                  | Description                                    |
|--------------------|-------------------------------|------------------------------------------------|
| Gateway Service   | `indresh5/gateway-ms`        | Handles API routing and requests to services. |
| Job Management    | `indresh5/jobms`             | Manages job-related data and operations.      |
| Company Service   | `indresh5/companyms`         | Handles company-related operations.           |
| Review Service    | `indresh5/reviewms`          | Manages reviews and related data.             |
| Service Registry  | `indresh5/servicereg`        | Central registry for service discovery.       |

## Prerequisites

Ensure you have the following installed:
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

## Setting Up the Application

### Clone the Repository
bash

git clone https://github.com/indreshrsingh/Career-Connect-Portal.git

cd Career-Connect-Portal

docker pull indresh5/gateway-ms

docker pull indresh5/jobms

docker pull indresh5/companyms

docker pull indresh5/reviewms

docker pull indresh5/servicereg

docker-compose up -d


Access the Service Registry at: http://localhost:8761/

Gateway API is available at: http://localhost:8084/

