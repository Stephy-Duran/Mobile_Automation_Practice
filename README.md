# Mobile_Automation_Practice

## Project Overview

The **Mobile Automation Framework** is designed to automate testing for various features on the WEBDRIVER I/O app using Java, Maven, and Appium. This framework allows for efficient and reliable mobile test automation.

## Project Requirements

To ensure the framework functions correctly, please follow these requirements:

1. **Java SDK**:
   - The project is built with Java 22 SDK. Ensure you have Java 22 installed on your system. If using IntelliJ IDEA, it should be version 2024-2. You can download it from JetBrains IntelliJ IDEA, as it is the only version of the IDE that supports Java 22.

2. **Clone the Repository**:

   ```bash
   git clone <repo-url>
   cd Mobile_Automation_Practice
   cd mobilePractice  # Ensure this is the folder you open in the IDE

3. **Configure the IDE with Java 22:**:
   - 3.1. Right-click on the PomPractice folder > Open Module Settings > In the Modules section, verify that the language level is set to "22 - Unnamed variables and patterns".

   - 3.2. Click on the Project tab, verify that the SDK is set to "22 - Oracle OpenJDK 22" and that the Language Level is set to "22 - Unnamed variables and patterns".

   - 3.3. Click Ok.

4. **Maven**:

   - The project uses Maven for dependency management. Ensure Maven is installed and properly configured on your system.

   - To build the project and skip tests, use the following command:

   ```bash
      mvn clean install -DskipTests\





