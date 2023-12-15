# Spring Boot REST API 

## Overview

This Spring Boot application serves as a RESTful API for user authentication, providing endpoints for user login and registration. Additionally, it includes social media login functionality for GitHub, Facebook, and Google.

## Table of Contents

- [Features](#Features)
- [Usage](#usage)
  - [Login](#login)
  - [Register](#register)
  - [Social Media Login](#social-media-login)
- [API Endpoints](#api-endpoints)
- [Request and Response Examples](#request-and-response-examples)

## Features

- User authentication through email and password
- User registration
- Social media login (GitHub, Facebook, Google)

## Usage

### Login

**Endpoint:** `POST /api/v1/auth/login`

To authenticate a user, send a POST request with a JSON body containing the user's email and password.

### Register

**Endpoint:** `POST /api/v1/auth/register`

To register a new user, send a POST request with a JSON body containing the user's registration details.

## Social Media Login

The application supports social media login for GitHub, Facebook, and Google. To enable this feature, configure the respective authentication providers in the application properties.

### GitHub Login

**Endpoint:** `POST /api/v1/auth/login/github`

To perform GitHub login, send a POST request to the above endpoint. The application will redirect the user to GitHub for authentication, and upon successful authentication, the user will be redirected back to the application.

### Facebook Login

**Endpoint:** `POST /api/v1/auth/login/facebook`

To perform Facebook login, send a POST request to the above endpoint. The application will redirect the user to Facebook for authentication, and upon successful authentication, the user will be redirected back to the application.

### Google Login

**Endpoint:** `POST /api/v1/auth/login/google`

To perform Google login, send a POST request to the above endpoint. The application will redirect the user to Google for authentication, and upon successful authentication, the user will be redirected back to the application.

Please ensure that you have the necessary API keys and credentials configured in the application for each social media platform.

## API Endpoints

- `POST /api/v1/auth/login`: Authenticate user
- `POST /api/v1/auth/register`: Register a new user

## Request and Response Examples

### Login Request 

```json
{
  "email": "user@example.com",
  "password": "securepassword"
}
```
### Login Response (Success- 200 OK)

```json
{
  "token": "token-example",
}
```
### Login Response (Error - 401 Unauthorized)
```json
{
    "message": "Invalid user credentials"
}
```

### Register Request
```json
{
    "firstName": "first name example",
    "lastName": "last name example",
    "email": "email@domin.com",
    "password":"********"
}
```
### Register Response (Success- 201 CREATED)
```json
{
  "token": "token-example",
}
```


