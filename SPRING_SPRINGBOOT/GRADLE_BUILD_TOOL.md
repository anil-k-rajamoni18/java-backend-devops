# 📚 Gradle for Java Development
- Gradle is an open-source build automation tool.
- Designed for multi-language projects (Java, Kotlin, Groovy, Android, Scala, C/C++, etc.).
- Uses a Groovy or Kotlin DSL for configuration (build.gradle or build.gradle.kts).
- Combines Ant’s flexibility and Maven’s conventions.
- Focuses on incremental builds and performance.

**Key Strengths:**
- Faster builds via incremental compilation and build cache
- Flexible plugin system
- Easy integration with IDEs, CI/CD, and dependency repositories

**Why Use Gradle?**
- `Automation`: Handles compilation, testing, packaging, and deployment.
- `Performance`: Supports parallel and incremental builds.
- `Flexibility`: Can build almost any kind of software.
- `Dependency Management`: Integrates with Maven Central, JCenter, etc.
- `Customization`: Uses scripting to define build logic.

**Gradle vs Maven vs Ant**
| Feature           | Gradle                       | Maven  | Ant       |
| ----------------- | ---------------------------- | ------ | --------- |
| DSL               | Groovy/Kotlin                | XML    | XML       |
| Performance       | High (incremental, cache)    | Medium | Low       |
| Conventions       | Yes                          | Yes    | No        |
| Dependency Mgmt   | Yes                          | Yes    | No        |
| Flexibility       | High                         | Medium | High      |
| Popularity (Java) | Very high in modern projects | High   | Declining |

> Convention over Configuration means:
- A framework or tool provides default behavior and structure so you don’t have to specify every little detail.
You only configure when you want to change the default.


### Gradle Project Structure
```pgsql
my-project/               # Root directory of your Gradle project
 ├── build.gradle          # Main build script (Groovy DSL)
 ├── settings.gradle       # Defines project name & modules
 ├── gradle/               # Gradle wrapper files (version configs)
 ├── src/                  # Source code folder (default convention)
 │    ├── main/            # Main application code
 │    │    └── java/       # Java source files (*.java)
 │    └── test/            # Test code
 │         └── java/       # Java test files (*.java)
 └── gradlew / gradlew.bat # Gradle wrapper scripts (Unix & Windows)
```

### Core Gradle Concepts

`a) Build Scripts`
- The heart of your build configuration.
- Main configuration file: build.gradle (Groovy) or build.gradle.kts (Kotlin).
- Declares:
    - Plugins (java, application, etc.)
    - Dependencies
    - Tasks
    - Build settings

- Example (Groovy DSL):
```groovy
plugins {
    id 'java'
}

group = 'com.example'
version = '1.0.0'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.apache.commons:commons-lang3:3.12.0'
    testImplementation 'org.junit.jupiter:junit-jupiter:5.9.3'
}

test {
    useJUnitPlatform()
}
```

`b) Settings File`
- settings.gradle defines project name and multi-module structure.
```groovy
rootProject.name = 'my-project'
```

`gradle/ directory`
- Contains Gradle wrapper JAR and properties:
    - gradle-wrapper.jar
    - gradle-wrapper.properties
- Specifies the Gradle version to use so everyone builds with the same version.

`src/ directory`
- Gradle follows Convention over Configuration:
    - src/main/java → main Java source code
    - src/main/resources → resource files (config, properties, etc.)
    - src/test/java → test Java code
    - src/test/resources → test-specific resources

```bash
src/
 ├── main/
 │    ├── java/            # com/example/App.java
 │    └── resources/       # application.properties
 └── test/
      ├── java/            # com/example/AppTest.java
      └── resources/       # test-data.json
```

`gradlew & gradlew.bat`
- Gradle wrapper scripts for: Lets you run Gradle without installing it system-wide:
    - gradlew → Unix/Linux/Mac
    - gradlew.bat → Windows

```bash
./gradlew build
```
- Ensures everyone uses the same Gradle version defined in gradle/wrapper/gradle-wrapper.properties.

`c) Plugins`
- Extend Gradle’s functionality.
- They add tasks, set conventions, and integrate tools.
- Can be core plugins (built-in) or community plugins.
- Examples: java, application, war, maven-publish
```groovy
plugins {
    id 'java' // core plugin
    id 'application' // for running Java apps
}

application {
    mainClass = 'com.example.Main'
}
```

**Popular Core Plugins**
| Plugin          | Purpose                                          | Example Usage                                    |
| --------------- | ------------------------------------------------ | ------------------------------------------------ |
| `java`          | Builds Java projects                             | Adds compile/test/package tasks                  |
| `application`   | Runs Java apps                                   | `application { mainClass = 'com.example.Main' }` |
| `maven-publish` | Publishes to Maven repos                         | Used for deploying artifacts                     |
| `war`           | Builds Web Application Archives                  | For Java web projects                            |
| `java-library`  | For libraries with API/implementation separation | Improves dependency control                      |


`d)  Repository & Types`
- Gradle needs repositories to find dependencies.
```groovy
repositories {
    mavenCentral()
}
```
- Types of Repositories

| Type                   | Example                                             | Notes                         |
| ---------------------- | --------------------------------------------------- | ----------------------------- |
| **Maven Central**      | `mavenCentral()`                                    | Default public repo for Java  |
| **JCenter**            | `jcenter()`                                         | Was popular, now deprecated   |
| **Google**             | `google()`                                          | Required for Android builds   |
| **Local Maven Repo**   | `mavenLocal()`                                      | Uses `~/.m2/repository`       |
| **Custom Remote Repo** | `maven { url 'https://repo.mycompany.com/maven2' }` | For private/internal packages |
| **Flat Directory**     | `flatDir { dirs 'libs' }`                           | For local JAR files           |

```groovy
repositories {
    mavenCentral()
    mavenLocal()
    maven {
        url "https://jitpack.io"
    }
}
```

`d) Dependencies`
- Dependencies are external libraries your project needs.
- Gradle resolves dependencies from repositories like Maven Central.
- Scopes:

| Scope                | Purpose                                     |
| -------------------- | ------------------------------------------- |
| `implementation`     | Used in main code, not exposed to consumers |
| `api`                | Used in libraries; exposed to consumers     |
| `compileOnly`        | Available at compile time only              |
| `runtimeOnly`        | Available at runtime only                   |
| `testImplementation` | Available in test code                      |
| `testRuntimeOnly`    | Available at test runtime only              |



```groovy
dependencies {
    implementation 'org.slf4j:slf4j-api:2.0.9'
    compileOnly 'org.projectlombok:lombok:1.18.28'
    runtimeOnly 'ch.qos.logback:logback-classic:1.4.11'
}
```

**Dependency Management**
- Gradle resolves versions from repositories.
- Version conflict resolution: Gradle picks the highest version by default.
- Use constraints for strict control:

```groovy
dependencies {
    implementation 'org.apache.commons:commons-lang3'
    constraints {
        implementation('org.apache.commons:commons-lang3:3.12.0') {
            because 'Ensure compatibility'
        }
    }
}
```

`e) Tasks`
- Smallest unit of work in Gradle (compilation, testing, packaging, etc.).
- Examples: compileJava, test, jar, clean.
- You can create custom tasks:

**1. Simple inline task**
- This is the fastest way to define a task directly in build.gradle:
```groovy
tasks.register("hello") {
    doLast {
        println 'Hello Gradle!'
    }
}
```
- doLast adds an action that will run at the end of the task.
- You can also use doFirst for actions at the start.

- Run with:
```sh
./gradlew taskName
./gradlew hello
```

**2. Typed task (built-in type)**
- Gradle has built-in task types like Copy, Exec, Delete.
```groovy
task copyFiles(type: Copy) {
    from 'src/main/resources'
    into "$buildDir/copied-resources"
}
```

**3. Custom task with Task API**
- For more flexibility, you can create a task by specifying a name and configuring it:
```groovy
tasks.register('customTask') {
    group = 'Custom'
    description = 'My custom Gradle task'

    doLast {
        println "Custom logic here"
    }
}
```
- Prefer tasks.register over task for better configuration avoidance (improves build performance).

**4. Custom task class inside build.gradle**
- If your task is complex, you can define your own class:
```groovy
class MyTask extends DefaultTask {
    @TaskAction
    def myAction() {
        println 'Running MyTask logic...'
    }
}

tasks.register('myTask', MyTask)
```
- Extend DefaultTask for most cases.
- Annotate the main method with @TaskAction.

**5. Reusable task in buildSrc**
- For reusable tasks across projects:
    - Create a buildSrc directory in your project root.
    - Add your task class in buildSrc/src/main/groovy or java.
    - Gradle will automatically compile and add it to the classpath.
- Example buildSrc/src/main/groovy/MyTask.groovy:

```groovy
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class MyTask extends DefaultTask {
    @TaskAction
    def run() {
        println 'This is a reusable task'
    }
}
```
- Then in build.gradle:
```groovy
tasks.register('myReusableTask', MyTask)
````

**Popular Built-in Tasks (Java plugin):**
| Task          | Purpose                         |
| ------------- | ------------------------------- |
| `build`       | Full build (compile, test, jar) |
| `clean`       | Deletes `build/`                |
| `compileJava` | Compiles source                 |
| `test`        | Runs unit tests                 |
| `jar`         | Creates JAR file                |





### Gradle Wrapper
- Files: gradlew, gradlew.bat, and gradle/wrapper/*.
- Ensures consistent Gradle version across environments.
- Run commands via:

```sh
./gradlew build
```

**Gradle Build Lifecycle**
1. Initialization – Identify projects and settings.gradle.
2. Configuration – Evaluate build.gradle files, configure tasks.
3. Execution – Run tasks in dependency order.
```bash
./gradlew build
```
> Init → Config → Executes tasks: compileJava, processResources, test, jar


**Multi-Module Projects**
- Used for large projects with multiple related modules.

- Example Structure:
```cpp
my-project/
 ├── settings.gradle
 ├── build.gradle
 ├── module-a/
 │    └── build.gradle
 └── module-b/
      └── build.gradle
```
- settings.gradle lists subprojects:
```groovy
rootProject.name = 'my-project'
include 'module-a', 'module-b'
```
- Each module has its own build.gradle.
- module-a/build.gradle:
```bash
plugins { id 'java' }
dependencies {
    implementation project(':module-b')
}
```

- Benefits:
    - Code reuse between modules
    - Independent builds
    - Shared dependencies and settings

**Sample End-to-End Example**
- Java app with JUnit tests
```groovy
plugins {
    id 'java'
    id 'application'
}

group = 'com.example'
version = '1.0'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.apache.commons:commons-lang3:3.12.0'
    testImplementation 'org.junit.jupiter:junit-jupiter:5.9.3'
}

application {
    mainClass = 'com.example.Main'
}

test {
    useJUnitPlatform()
}
```

----
### 📌 Popular Gradle Commands

**1. Project Info & Help**
| Command               | Purpose                       |
| --------------------- | ----------------------------- |
| `gradle -v`           | Check Gradle version          |
| `gradle help`         | Display help                  |
| `gradle projects`     | List all projects/subprojects |
| `gradle tasks`        | List available tasks          |
| `gradle dependencies` | Show dependency tree          |
| `gradle properties`   | Show project properties       |


**2. Build & Clean**
| Command              | Purpose                               |
| -------------------- | ------------------------------------- |
| `gradle build`       | Full build: compile, test, package    |
| `gradle assemble`    | Build outputs (without running tests) |
| `gradle clean`       | Delete build directory                |
| `gradle clean build` | Clean and rebuild project             |


**3. Running & Testing**
| Command        | Purpose                                     |
| -------------- | ------------------------------------------- |
| `gradle run`   | Run application (with `application` plugin) |
| `gradle test`  | Run unit tests                              |
| `gradle check` | Run all checks (tests, lint, etc.)          |

**4. Dependency Management**
| Command                                        | Purpose                                  |
| ---------------------------------------------- | ---------------------------------------- |
| `gradle dependencies`                          | Show dependency tree                     |
| `gradle dependencyInsight --dependency <name>` | Inspect a specific dependency            |
| `gradle buildEnvironment`                      | Show build script classpath dependencies |

**5. Gradle Wrapper (Recommended)**
| Command                | Purpose                               |
| ---------------------- | ------------------------------------- |
| `./gradlew build`      | Build using project’s wrapper version |
| `./gradlew tasks`      | List tasks using wrapper              |
| `./gradlew clean test` | Clean & run tests with wrapper        |


**6. Task Control**
| Command                   | Purpose                                 |
| ------------------------- | --------------------------------------- |
| `gradle <task1> <task2>`  | Run multiple tasks                      |
| `gradle <task> --info`    | Show extra info while running           |
| `gradle <task> --debug`   | Run with debug logging                  |
| `gradle <task> --dry-run` | Simulate task execution without running |

**7. Advanced**
| Command                               | Purpose                                           |
| ------------------------------------- | ------------------------------------------------- |
| `gradle --offline build`              | Build without checking remote repositories        |
| `gradle build --scan`                 | Generate a build scan (performance & diagnostics) |
| `gradle build --parallel`             | Run tasks in parallel for faster builds           |
| `gradle build --refresh-dependencies` | Force update dependencies                         |

---
###  Advanced Topics
`a) Build Cache`
- Reuses outputs from previous builds.
```bash
gradle build --build-cache
```

`b) Incremental Builds`
- Only re-builds what’s changed.

`c) Profiles via Properties`
```bash
gradle build -Penv=dev
```

```groovy
if (project.hasProperty('env') && project.env == 'dev') {
    println "Building for development"
}
```

`d) Publishing Artifacts`
```groovy
plugins {
    id 'maven-publish'
}
publishing {
    publications {
        mavenJava(MavenPublication) {
            from components.java
        }
    }
}
```


---
## 📚 Gradle with Spring Boot

**Why Use Gradle with Spring Boot?**
- Spring Boot simplifies application development.
- Gradle automates the build, dependency management, and running of the app.
> Together, they allow fast development, easy dependency handling, and consistent builds.


**Project Structure (Default Convention)**
```bash
springboot-gradle-app/
 ├── build.gradle
 ├── settings.gradle
 ├── src/
 │    ├── main/
 │    │    ├── java/        # Application code
 │    │    └── resources/   # application.properties / application.yml
 │    └── test/
 │         ├── java/        # Unit/Integration tests
 │         └── resources/   # Test resources
 ├── gradlew
 ├── gradlew.bat
 └── gradle/
```

**Basic build.gradle for Spring Boot**
```bash
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.3'
    id 'io.spring.dependency-management' version '1.1.4'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'
java {
    sourceCompatibility = '17'
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
    useJUnitPlatform()
}
```

**Key Plugins**
| Plugin                            | Purpose                                       |
| --------------------------------- | --------------------------------------------- |
| `org.springframework.boot`        | Adds Spring Boot tasks (`bootRun`, `bootJar`) |
| `io.spring.dependency-management` | Manages dependency versions                   |
| `java`                            | Enables Java project build support            |


**Common Gradle + Spring Boot Commands**
| Command                  | Purpose               |
| ------------------------ | --------------------- |
| `./gradlew bootRun`      | Run the application   |
| `./gradlew build`        | Build app JAR/WAR     |
| `./gradlew clean build`  | Clean and rebuild     |
| `./gradlew bootJar`      | Create executable JAR |
| `./gradlew dependencies` | View dependency tree  |


**Running a Spring Boot App with Gradle**
- With bootRun
```bash
./gradlew bootRun
```
- Compiles code
- Starts the embedded Tomcat server
- Loads application.properties or application.yml

**Customizing Build**
- Change JAR Name:
```groovy
bootJar {
    archiveBaseName.set('myapp')
    archiveVersion.set('1.0.0')
}
```

- Add Profiles:
```groovy
bootRun {
    args = ["--spring.profiles.active=dev"]
}
```

**Multi-Module Spring Boot with Gradle**
- settings.gradle:
```groovy
rootProject.name = 'spring-multi'
include 'service-api', 'service-impl'
```

- Root build.gradle:
```groovy
plugins {
    id 'org.springframework.boot' version '3.2.3' apply false
    id 'io.spring.dependency-management' version '1.1.4'
}

subprojects {
    apply plugin: 'java'
    apply plugin: 'io.spring.dependency-management'

    repositories {
        mavenCentral()
    }
}
```