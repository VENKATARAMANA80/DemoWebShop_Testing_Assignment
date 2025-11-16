# DemoWebShop Testing Assignment

Selenium + Java + TestNG project that automates dynamic e-commerce flows on:
https://demowebshop.tricentis.com

## Structure
```
DemoWebShop_Testing_Assignment/
├── src/
│   ├── main/java/pages/
│   ├── test/java/tests/
│   └── main/java/utils/
├── testdata.csv
├── screenshots/
├── report.txt
├── README.md
├── pom.xml
└── testng.xml
```

## Requirements
- Java 11+
- Maven
- Chrome browser (or change driver in BaseTest)

## How to run
1. Extract project.
2. From project root run:
   `mvn clean test -q`
3. Reports:
   - Failure screenshots are saved to `/screenshots/`
   - Execution summary in `/report.txt`

## Notes
- Uses WebDriverManager to manage ChromeDriver.
- Test data in `testdata.csv`.

