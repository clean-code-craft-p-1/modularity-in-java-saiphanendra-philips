# Modularity Challenge

Welcome to the art of breaking things apart! 🧩

## What was implemented

The `temperature` feature is now split into small, purpose-based modules so teams can work independently:

- `temperature.application` - orchestration use case (`TemperatureProcessingService`)
- `temperature.domain` - core data models (`TemperatureReading`, `TemperatureSummary`, `ProcessingResult`)
- `temperature.parsing` - input validation and parsing (`TemperatureLineParser`)
- `temperature.analysis` - pure analytics logic (`TemperatureAnalyzer`)
- `temperature.io` - file I/O abstractions and implementations (`ReadingSource`, `ReportSink`)
- `temperature.reporting` - formatting and console presentation
- `temperature.tests` - focused tests with boundary-oriented assertions

## Assignment principles covered

- **Small and well-named**: each class has a single, explicit responsibility.
- **Separate by lifecycle**: parsing, analytics, orchestration, and side-effecting I/O are separated.
- **Name by purpose**: package and class names describe intent instead of technical details.
- **Avoid side effects**: core service logic returns results; I/O is handled at the edges.
- **Inject dependencies**: `TemperatureProcessingService` receives source/parser/analyzer via constructor injection.
- **FIRST testing mindset**:
  - Fast and repeatable (pure unit-style tests)
  - Independent (tests do not depend on each other)
  - Self-validating (explicit assertions)
  - Timely and focused on the **boundary under test**

## Run locally

Compile all classes:

```powershell
Set-Location "C:\Users\320287761\kt_java\repo\modularity-in-java-saiphanendra-philips"
Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName } | Set-Content .java_sources.txt
javac "@.java_sources.txt"
```

Run the application:

```powershell
java temperature.Main
```

Run tests:

```powershell
java temperature.tests.TestRunner
```
