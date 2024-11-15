# Liferay Registration App and Registration Module

## Overview

This repository contains an implementation of a registration app for Liferay, integrated with Google reCAPTCHA for form validation. The solution includes the development of both a **ReactJS client extension** (registration form) and a **Liferay module** (REST API and services) to handle the backend validation and user registration logic. 

The project is designed to work in a separate Liferay workspace with clear separation between the frontend (ReactJS) and backend (Liferay modules). It focuses on user registration, form validation, and integrates reCAPTCHA to prevent spam.

## Exercise Objectives

- Implement a **registration form** in **ReactJS** that integrates **CAPTCHA** for validation.
- Develop a **Liferay module** to handle backend processes including REST API services, containerization, and user registration logic.
- Set up a **client-extension** for the registration form to interact with the Liferay instance.
- Ensure the **backend (Liferay module)** is able to handle reCAPTCHA validation using Google's **API**.
- 
## Testing the Project Using Docker

The easiest way to test the project is to use the pre-packaged Docker image. This image comes with all necessary dependencies and configurations, allowing you to quickly run the project without needing to set up a separate Liferay workspace.

To run the project using Docker, use the following command:

```bash
docker run -v $(pwd)/mounts:/mnt/liferay -it -m 8g -p 8080:8080 -e JAVA_VERSION=zulu11 redopit/liferay-sa-interview
```

## Project Structure

### 1. `client-extensions/registration-app/`
- This folder contains the **ReactJS** client-side code for the registration form. 
- **reCAPTCHA Integration**: The form includes Google reCAPTCHA for form validation before submission.
- It communicates with the Liferay REST API (`registration-rest` module) to handle user registration.
- - It uses **ClayUI** components for the UI.

### 2. `modules/registration/`
This directory contains the Liferay module for handling the backend logic and services.

- **`registration-api/`**: generated using the service-builder template, includes interfaces and abstract classes that outline what services are available for the registration process.
- **`registration-service/`**: generated using the service-builder template,contains the implementations of the API interfaces defined in the registration-api folder. It contains the business logic of the registration process.
- **`registration-admin/`**: Contains the admin UI components to view registrations data from Liferay Panel.
- **`registration-rest/`**: Provides the REST API endpoints to the `registration-app` in client-extensions, it is also responsible for recaptcha server-side validation.

### 3. `.gitignore`
- the gitignore is configured to ignore everything from the liferay workspace, except for the above mentioned folders.


---

## Prerequisites

To run this project in a separate Liferay workspace, make sure you have the following installed:

1. **Liferay CE** (Liferay Portal).
2. **Liferay Workspace** setup with Maven.
3. **Node.js** and **npm** for running the ReactJS app.
4. **Google reCAPTCHA API key** (both public and private keys for the site).

## Setting Up the Liferay Workspace

### 1. Setting Up the Liferay Environment

To set up the Liferay workspace and run the project, follow the step 1 in [this Blog](https://liferay.dev/es/blogs/-/blogs/from-react-app-to-react-client-extension-in-5-easy-steps)

**From the Blog**: 
The easiest method is to just go to https://github.com/liferay/liferay-portal/, download the ZIP from the master branch, expand it and then copy the whole liferay-sample-workspace folder to some location on your drive where you plan to put all of your React elements.


- Then, Clone the repository to your local machine.
- Place this project’s folders inside the workspace directory as follows:

```
liferay-workspace/
├── modules/
│   └── registration/
├── client-extensions/
│   └── registration-app/
└── .gitignore
```

### 2. Building and Deploying the Liferay Module

1. **Build the module**:
   From the Liferay workspace directory, run the following command to build the Liferay modules:
   
   ```bash
   ./gradlew build
   ```

2. **Deploy the module**:
   After the build completes successfully, deploy the Liferay module to the Liferay server by running the following command:
   
   ```bash
   ./gradlew deploy
   ```

### 3. Setting Up the ReactJS Client Extension

1. **Navigate to the React app** folder located inside `client-extensions/registration-app/`:
   
   ```bash
   cd client-extensions/registration-app
   ```

2. **Install the necessary dependencies** using npm:
   
   ```bash
   npm install
   ```

3. **Start the React development server**:
   
   ```bash
   npm start
   ```

This will start the development server for the React app, and it will run on the default port (usually `3000`).

### 4. Configure Google reCAPTCHA

To integrate Google reCAPTCHA, you'll need to add your **reCAPTCHA site key** into the React app. The site key can be obtained from the [Google reCAPTCHA Console](https://www.google.com/recaptcha).

- **In the ReactJS client extension**: In `src/components/RegistrationForm.js`, replace `YOUR_RECAPTCHA_SITE_KEY` with your actual site key.
  
---

## How It Works

### 1. React Registration Form
- The user submits their details in the form, which includes the reCAPTCHA widget.
- The form triggers a reCAPTCHA verification process to ensure that it’s a human interacting with the form.
- Upon successful validation, the form submits the registration data to the Liferay backend.

### 2. Liferay Module (REST API)
- The **Liferay module** handles the backend logic, which includes validating the reCAPTCHA token via the **Google reCAPTCHA API**.
- If the token is valid, the backend processes the registration by creating a new entry in Liferay.
- The registration process occurs via REST API, which is exposed in the **`/modules/registration/registration-rest`** package.

### 3. Liferay Admin

- The **Registration Admin** module registers a new menu item in the **Liferay Admin Panel**, and allow the admins to view the registrations.

---

## Running the Project in Docker

To run this project in a separate Liferay workspace:

1. **Set up the Liferay Workspace** as described above.
2. **Deploy the Liferay module** (`registration` modules).
3. **Start the React client** (`registration-app`).
4. **Access the Registration Form** through the URL provided by the React app (usually `http://localhost:3000`).
5. **Complete the registration** and submit the form.

---
