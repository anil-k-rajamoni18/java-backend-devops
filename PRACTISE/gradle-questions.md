# Gradle Project Guide

## Part 1 – Basics

### Hello Gradle Project
- Create a new Gradle project using the `gradle init` command.
- Choose Java application template.
- Add a `hello.gradle` task that prints "Hello from Gradle".
- Run your task with `gradle hello`.

### Custom Build Script
- Add a task named `greet` that takes a name as input via `-Pname=...`  
  **Example:**  
  `gradle greet -Pname=Alex`  
  Should print: "Hello, Alex".

---

## Part 2 – Dependency Management

### Add External Dependency
- Add the Gson library to your project (`com.google.code.gson:gson`).
- Write a small Java program that serializes and deserializes a simple object.
- Build and run it using `gradle run`.

### Separate Dependencies by Scope
- Add a testing framework (JUnit 5).
- Write a unit test.
- Ensure tests only run when executing `gradle test` (and not in normal run).

---

## Part 3 – Multi-Project Builds

### Multi-Module Setup
- Create a multi-project Gradle build with:
  - `app` module (Java application)
  - `library` module (contains utility functions)
- Make `app` depend on `library`.
- Confirm that changing `library` requires recompiling `app`.

---

## Part 4 – Custom Tasks & Plugins

### Custom Task with Input/Output
- Write a Gradle task `reverseFile` that:
  - Takes an input text file.
  - Produces a reversed-content output file in `build/outputs`.
  - Use Gradle's `@InputFile` and `@OutputFile` annotations.

### Custom Gradle Plugin
- Create a standalone Gradle plugin in `buildSrc` that adds:
  - A `sayHello` task.
  - A configurable extension where the user can set their name in `build.gradle`.

---

## Part 5 – Advanced Topics

### Conditional Builds
- Modify your build so that:
  - Tests run only if a certain property (`-PrunTests=true`) is passed.
  - Otherwise, skip tests gracefully.

### Gradle Wrapper
- Add Gradle wrapper to your project (`gradle wrapper --gradle-version X.X`).
- Commit wrapper files to version control.
- Run the build using `./gradlew`.

### Build Profiles
- Configure different build profiles (`dev` and `prod`) with:
  - Different dependencies
  - Different application configuration files
- Switch profiles via `-Penv=dev` or `-Penv=prod`.

---

## Part 6 – Bonus Advanced

### Custom Task for API Call
- Create a task that makes an HTTP request to a public API and saves the response JSON to a file.
- Use Java or Groovy inside Gradle.

### Publish to Local Maven Repository
- Configure your `library` module to publish a `.jar` to your local Maven repo.
- Add that dependency to another Gradle project via `mavenLocal()`.