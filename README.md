# 🌍 AJSD Chatbot — Countries Knowledge Base

A rule-based conversational chatbot built with **Spring Boot** that helps users learn about countries through an interactive dialogue. Users can explore a country's **capital city**, **national animal**, and **national flower** using a guided conversation flow.

---

# 🚀 Features

* Interactive country-learning chatbot
* Guided conversation using a finite-state machine
* Session-based conversation tracking
* Country data loaded from a JSON knowledge base
* REST API for chatbot interactions
* Dynamic web interface powered by Thymeleaf and JavaScript
* Easy extension by adding new countries to the dataset

---

# 🛠️ Technology Stack

| Category           | Technology                        |
| ------------------ | --------------------------------- |
| Backend            | Java 17, Spring Boot 3.2.5        |
| Web Framework      | Spring MVC                        |
| Template Engine    | Thymeleaf                         |
| JSON Processing    | Jackson                           |
| Session Management | HttpSession                       |
| Frontend           | HTML, CSS, JavaScript (Fetch API) |
| Build Tool         | Maven                             |

---

# 📂 Project Structure

```text
src/
└── main/
    ├── java/com/ajsd/chatbot/
    │   ├── ChatbotApplication.java
    │   ├── config/
    │   │   └── CountryDataLoader.java
    │   ├── controller/
    │   │   ├── HomeController.java
    │   │   └── ChatbotController.java
    │   ├── model/
    │   │   ├── CountryInfo.java
    │   │   └── ConversationContext.java
    │   └── service/
    │       ├── ChatbotService.java
    │       └── RuleBasedEngine.java
    │
    └── resources/
        ├── templates/
        │   └── index.html
        ├── countries_data.json
        └── application.properties
```

---

# ⚙️ How the Chatbot Works

The chatbot follows a **Finite State Machine (FSM)** architecture. Each user's conversation state is stored in a session-specific `ConversationContext`, allowing the chatbot to guide users through a structured learning experience.

## Conversation Flow

```text
ASK_INTENT
     │
     ▼
SELECT_COUNTRY
     │
     ▼
CHOOSE_OPTION
     │
     └──► (E) Choose Another Country
```

### States

| State            | Purpose                                                                |
| ---------------- | ---------------------------------------------------------------------- |
| `ASK_INTENT`     | Waits for the user to express an interest in learning about countries. |
| `SELECT_COUNTRY` | Requests a country name and validates it against the knowledge base.   |
| `CHOOSE_OPTION`  | Allows the user to choose what information they want to learn.         |

### Available Options

| Option | Description                           |
| ------ | ------------------------------------- |
| A      | Learn about the capital city          |
| B      | Learn about the national animal       |
| C      | Learn about the national flower       |
| D      | Learn about all available information |
| E      | Select another country                |

---

# 🔄 Special Commands

| Command | Action                                                   |
| ------- | -------------------------------------------------------- |
| `start` | Starts a new conversation and displays a welcome message |
| `clear` | Resets the current conversation state                    |

---

# 📡 REST API

## POST `/chat`

### Request

```json
{
  "message": "France"
}
```

### Response

```json
{
  "response": "Great! I know about that country.\nWhat do you want to learn about it?\nA. Learn about the capital\nB. Learn about the national animal\nC. Learn about the national flower\nD. Learn about all information\nE. Choose another country"
}
```

---

# 🌎 Supported Countries

The application currently includes information for 20 countries:

* USA
* Canada
* United Kingdom
* France
* Germany
* Japan
* China
* India
* Brazil
* Australia
* South Africa
* Mexico
* Argentina
* Russia
* Italy
* Spain
* Netherlands
* Sweden
* Norway
* Egypt

## Adding New Countries

To add a country, update the file:

```text
src/main/resources/countries_data.json
```

Example:

```json
{
  "CountryName": {
    "capital": "Capital City",
    "nationalAnimal": "National Animal",
    "nationalFlower": "National Flower"
  }
}
```

---

# 🚀 Getting Started

## Prerequisites

* Java 17 or higher
* Maven 3.6 or higher

## Clone the Repository

```bash
git clone https://github.com/<your-username>/<repository-name>.git
cd chatbot
```

## Run the Application

```bash
mvn spring-boot:run
```

Open your browser and navigate to:

```text
http://localhost:8080
```

---

# 📦 Build Executable JAR

```bash
mvn clean package
java -jar target/chatbot-0.0.1-SNAPSHOT.jar
```

---

# 💬 Example Interaction

```text
User: start

Bot: Hello! How can I help you today?

User: Teach me about countries

Bot: I can teach you about countries, their capitals,
     national animals, and national flowers.
     What country would you like to learn about?

User: Japan

Bot: Great! I know about that country.
     What would you like to learn?

     A. Capital
     B. National Animal
     C. National Flower
     D. All Information
     E. Choose Another Country

User: D

Bot: The capital of Japan is Tokyo.
     The national animal of Japan is the Green Pheasant.
     The national flower of Japan is the Cherry Blossom.
```

---

# 🏗️ Design Decisions

### Singleton Data Loading

`CountryDataLoader` loads the country dataset only once during application startup, minimizing file I/O operations and improving performance.

### Session-Based Context Management

Conversation state is stored in `HttpSession`, allowing each user to maintain an independent chatbot session.

### Dynamic Frontend Updates

The user interface communicates with the backend through the Fetch API, enabling real-time message updates without page reloads.

### Layered Architecture

The project follows a clean separation of concerns:

* **Controller Layer** → Handles HTTP requests
* **Service Layer** → Business logic and data access
* **Model Layer** → Domain objects and conversation state
* **Configuration Layer** → Data initialization and setup

---

# 📜 License

This project was developed as part of the **AJSD Chatbot educational project** and serves as a demonstration of Spring Boot, REST APIs, session management, and rule-based conversational systems.
