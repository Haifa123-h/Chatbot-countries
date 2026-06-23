# AJSD Chatbot — Countries Knowledge Base

A rule-based conversational chatbot built with **Spring Boot** that teaches users about countries — their capitals, national animals, and national flowers — through a guided, step-by-step dialogue flow.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 17, Spring Boot 3.2.5 |
| Template Engine | Thymeleaf |
| REST | Spring MVC (`@RestController`) |
| JSON Parsing | Jackson (`jackson-databind`) |
| Session Management | `HttpSession` |
| Frontend | HTML, CSS, Vanilla JS (Fetch API) |
| Build Tool | Maven |

---

## Project Structure

```
src/
└── main/
    ├── java/com/ajsd/chatbot/
    │   ├── ChatbotApplication.java        # Entry point
    │   ├── config/
    │   │   └── CountryDataLoader.java     # Loads countries_data.json into memory (Singleton)
    │   ├── controller/
    │   │   ├── HomeController.java        # Serves the UI (GET /)
    │   │   └── ChatbotController.java     # Handles chat messages (POST /chat)
    │   ├── model/
    │   │   ├── CountryInfo.java           # Holds capital, national animal, national flower
    │   │   └── ConversationContext.java   # Tracks conversation state per session
    │   └── service/
    │       ├── ChatbotService.java        # Service layer wrapping CountryDataLoader
    │       └── RuleBasedEngine.java       # Core dialogue logic (state machine)
    └── resources/
        ├── templates/index.html           # Thymeleaf UI template
        ├── countries_data.json            # Country data source (20 countries)
        └── application.properties
```

---

## How It Works

The chatbot uses a **finite state machine** pattern managed through `ConversationContext`, which holds the current step in the conversation per user session.

### Conversation States

```
ASK_INTENT  ──► SELECT_COUNTRY  ──► CHOOSE_OPTION
    ▲                                     │
    └─────────────── (E) ─────────────────┘
```

| State | Description |
|---|---|
| `ASK_INTENT` | Initial state. Waits for user to express intent to learn about countries. |
| `SELECT_COUNTRY` | Prompts user to enter a country name. Validates against loaded data. |
| `CHOOSE_OPTION` | User picks what to learn: capital (A), national animal (B), national flower (C), all (D), or change country (E). |

### Special Commands

| Command | Effect |
|---|---|
| `start` | Returns a greeting message |
| `clear` | Resets the conversation context for the session |

---

## API

### `POST /chat`

**Request body:**
```json
{ "message": "France" }
```

**Response:**
```json
{ "response": "Great! I know about that country.\nWhat do you want to learn about it?\nA. learn about the capital\n..." }
```

---

## Countries Available

20 countries are loaded from `countries_data.json`:

`USA`, `Canada`, `United Kingdom`, `France`, `Germany`, `Japan`, `China`, `India`, `Brazil`, `Australia`, `South Africa`, `Mexico`, `Argentina`, `Russia`, `Italy`, `Spain`, `Netherlands`, `Sweden`, `Norway`, `Egypt`

To add more countries, edit `src/main/resources/countries_data.json` following the existing structure:

```json
"CountryName": {
  "capital": "...",
  "nationalAnimal": "...",
  "nationalFlower": "..."
}
```

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+

### Run locally

```bash
git clone https://github.com/<your-username>/<your-repo>.git
cd chatbot
mvn spring-boot:run
```

Then open [http://localhost:8080](http://localhost:8080) in your browser.

### Build JAR

```bash
mvn clean package
java -jar target/chatbot-0.0.1-SNAPSHOT.jar
```

---

## Example Conversation

```
You:     start
Bot:     Hello! How can I help you today?

You:     teach me about countries
Bot:     I can teach you about countries, their capitals, national animals, and national flowers.
         What country do you want to learn about?

You:     Japan
Bot:     Great! I know about that country.
         What do you want to learn about it?
         A. learn about the capital
         B. learn about the national animal
         C. learn about the national flower
         D. learn about all of the above
         E. Choose another country

You:     D
Bot:     The capital of Japan is Tokyo.
         The national animal of Japan is Green Pheasant.
         The national flower of Japan is Cherry Blossom.
```

---

## Design Notes

- `CountryDataLoader` applies the **Singleton pattern** alongside Spring's `@Component` to ensure country data is loaded once at startup.
- Conversation state is stored **server-side** in `HttpSession`, meaning each browser session maintains its own independent dialogue context.
- The frontend maintains **client-side message history** and appends new messages dynamically via the Fetch API without full page reloads.
