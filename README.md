# README for AI-Integrated Java Spring Boot Project

## Overview

This project explores the integration of Artificial Intelligence (AI) models into a Java Spring Boot application. It incorporates both pre-trained models and custom-built models to demonstrate various AI capabilities.

## Features

### 1. **Chat Model Using Ollama**

- Integrated a conversational AI model into the application using **Ollama**.
- This feature enables natural language interactions, allowing the system to respond intelligently to user inputs.

### 2. **Linear Regression Model Integration**

- Built a custom **Linear Regression model** for predicting outcomes based on input data.
- The trained model was integrated into the Java Spring Boot application to enable real-time predictions.

### 3. **Object Detection Using Pre-trained Models**

- Used **ResNet50**, a pre-trained deep learning model, for object detection.
- Integrated this model into the application using the **Deep Java Library (DJL)** to detect and classify objects in images.

## Implementation Details

### Chat Model Integration

- **Library/Tool**: Ollama
- **Purpose**: Enhance user experience with conversational AI.
- **Steps**:
  1. Configured the Ollama API to interact with the chat model.
  2. Developed REST endpoints for user interaction.
  3. Enabled multi-turn conversation support with session handling.

### Linear Regression Model

- **Steps**:
  1. Trained a linear regression model using Python (e.g., scikit-learn).
  2. Exported the trained model as a serialized file (e.g., `.joblib` or `.pkl`).
  3. Integrated the model into the Java Spring Boot application using libraries like **JPMML** or custom deserialization.
  4. Exposed REST APIs for making predictions based on user input.

### Object Detection Using ResNet50

- **Library/Tool**: Deep Java Library (DJL)
- **Purpose**: Leverage pre-trained models for object detection and classification.
- **Steps**:
  1. Set up the DJL library in the Spring Boot project.
  2. Loaded the ResNet50 model using DJL.
  3. Created REST endpoints to upload images and return detected objects.

## Tools and Technologies

- **Backend**: Java Spring Boot
- **AI Libraries**:
  - Ollama for chat models
  - Deep Java Library (DJL) for object detection
  - JPMML or similar for integrating custom ML models
- **Programming Languages**: Java, Python (for training custom models)
- **Models**: ResNet50, Linear Regression

## Future Enhancements

1. Expand the chat model to support domain-specific knowledge.
2. Add more pre-trained models (e.g., sentiment analysis, text summarization).
3. Explore real-time video object detection.
4. Optimize the application for scalability and performance.

## How to Run the Application

1. Clone the repository:
   ```bash
   git clone [repository-url]
   ```
2. Install dependencies:
   ```bash
   mvn install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Use the provided REST APIs to interact with the chat model, make predictions, or perform object detection.

## Contributing

Feel free to contribute by submitting issues or pull requests. All contributions are welcome!

