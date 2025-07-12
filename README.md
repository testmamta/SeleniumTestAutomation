# SeleniumTestAutomation

# 🧪 Selenium-Cucumber-TestNG Automation Framework

## 🔧 Overview

This is a robust, modular, and scalable test automation framework built using **Selenium WebDriver**, **Cucumber (BDD)**, and **TestNG**. It supports **parallel cross-browser execution** using **Dockerized Selenium Grid**, integrated with **Jenkins CI/CD**, and follows the **Page Factory** design pattern. The framework also includes **Log4j2-based logging**, **ExtentReports**,strong **SOLID principle** adherence, and **OOP concepts** for maintainability and extensibility.

---

## 📦 Tech Stack

- **Selenium WebDriver**
- **Cucumber** (Gherkin syntax for feature files)
- **TestNG** (test orchestration & parallel execution)
- **Docker + Selenium Grid** (Chrome, Firefox, Edge nodes)
- **ExtentReports** (HTML reporting with screenshots)
- **Log4j2** (centralized logging)
- **WebDriverManager** (auto-manages browser drivers)
- **Jenkins** (CI/CD integration)
- **Maven** (`pom.xml` build management)
- **Page Factory** (for maintainable page objects)

---

## 🧪 Key Features

### ✅ Parallel Test Execution
- `@DataProvider(parallel = true)` for concurrent scenario execution
- Executes across multiple browsers (Chrome, Firefox, Edge)

### 🌐 Dockerized Cross-Browser Testing
- Uses Docker Selenium Grid (`localhost:4444/wd/hub`)
- Remote execution configured using parameters from TestNG XML

### 🧵 Thread Safety and Singleton Behavior
- `ThreadLocal<WebDriver>` for isolated WebDriver instances per thread
- `ExecutionContext` uses `ThreadLocal` to store parameters like `browser` and `runMode` per thread

### 🧠 Smart Execution Context Management
- `TestParameterListener` dynamically assigns browser/runMode per thread before each test
- Ensures correct context propagation for parallel scenarios

### 🧰 Logging (Log4j2)
- Captures logs at key lifecycle events: browser initialization, test start/end, failure handling, cleanup
- Helps in root cause analysis during CI/CD failures or local debug
- Logs include timestamps, thread IDs, and execution metadata

### 📷 Screenshots & Reporting
- Screenshots captured automatically on failure
- Rich ExtentReports integration with embedded screenshots and logs
- Cucumber HTML reports generated as well

### 🚀 CI/CD Integration
- Jenkins integration with parameterized builds
- Logs, reports, and screenshots saved as build artifacts

---

## 📁 Project Structure Highlights

```
DemoQAUITests/
├── src
│   ├── main
│   │   └── java
│   │       └── pages/                    # Page Object Models using Page Factory
│   ├── test
│   │   └── java
│   │       ├── listeners/                # TestNG Listeners for Reporting and Execution Context
│   │       ├── runners/                  # Cucumber TestNG Runner
│   │       ├── stepDefinitions/          # Step Definitions for Cucumber Scenarios
│   │       └── utilities/                # DriverFactory, ConfigReader, ScreenshotUtil, etc.
│   └── resources
│       ├── features/                     # Cucumber .feature files (BDD Scenarios)
│       ├── config.properties             # Global config like headless, timeout
│       ├── extent.properties             # ExtentReport customization
│       ├── log4j2.xml                    # Logging configuration file
│       └── testng.xml                    # TestNG suite for parameterized execution
├── reports/
│   └── extent/                           # Extent HTML Report (generated after execution)
├── target/                               # Build artifacts (generated)
├── test-output/                          # TestNG reports
├── pom.xml                               # Maven build configuration


---

## 🧠 OOP & SOLID Design Principles in Use

- **S (Single Responsibility):** `DriverFactory`, `ExecutionContext`, `ScreenshotUtil` all have focused responsibilities.
- **O (Open/Closed):** Adding new browsers or test environments requires minimal code change.
- **L (Liskov Substitution):** WebDriver implementations (Chrome, Firefox, Edge) used polymorphically.
- **I (Interface Segregation):** Listeners handle only the events they care about (ITestListener, IInvokedMethodListener).
- **D (Dependency Inversion):** Config values, browser options are abstracted away via `ConfigReader` and injected contextually.

---

## 📌 Usage

1. Configure your `testng.xml` with parallel parameters and `<parameter name="browser" value="chrome"/>`.
2. Update `log4j2.xml` to control logging verbosity.
3. Execute via command line: `mvn clean test`
4. View reports in `target/extent-reports/` and `target/cucumber-report.html`
5. View logs in `logs/automation.log`

---

## 🛠 Future Enhancements

- Integration with Allure Reports
- BrowserStack / Sauce Labs cloud execution support
- API + UI combined test pipelines
