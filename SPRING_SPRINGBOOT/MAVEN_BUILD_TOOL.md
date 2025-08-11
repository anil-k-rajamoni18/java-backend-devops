## What is a Build Tool?
- A build tool is a software utility that automates the process of converting source code into an executable application.
- It typically handles:
  - Compilation (Java source → bytecode)
  - Packaging (e.g., JAR, WAR)
  - Dependency management 
  - Testing
  - Deployment

> Think of it as your project’s “chef” — you give it ingredients (source code, dependencies, configs) and it serves you the final dish (working application).

### Why is a Build Tool Needed?
- Without a build tool, you’d have to:
  - Manually compile each .java file with javac
  - Manually package them into a JAR/WAR
  - Track and download all library dependencies yourself
  - Run tests manually
  - Deploy manually

- Build tools solve these problems by:
  - Automating repetitive tasks
  - Ensuring consistency across environments
  - Managing dependencies (downloads, versioning)
  - Integrating with CI/CD pipelines for automated builds and deployment


### Types of Build Tools for Java
| **Category**                                  | **Examples**               | **Key Features**                                                                  |
| --------------------------------------------- | -------------------------- | --------------------------------------------------------------------------------- |
| **Traditional Build Tools**                   | **Apache Ant**             | XML-based, very flexible but verbose, procedural approach                         |
| **Convention-over-Configuration Build Tools** | **Apache Maven**           | XML-based, uses conventions for project structure, built-in dependency management |
| **Modern/Declarative Build Tools**            | **Gradle**                 | Groovy/Kotlin DSL, faster builds, flexible, supports multi-language projects      |
| **Specialized Build/Automation Tools**        | **SBT (Scala)**, **Bazel** | Optimized for specific languages or large-scale builds                            |



###  Popular Java Build Tools
a) Apache Ant
  - First widely used Java build tool
  - Procedural — you tell it how to build
  - No built-in dependency management (needs Ivy)

b) Apache Maven
  - Declarative — you describe what to build
  - Uses a pom.xml file for configuration
  - Built-in dependency management via Maven Central

c) Gradle
  - Combines Ant’s flexibility and Maven’s conventions
  - Uses Groovy/Kotlin DSL instead of XML
  - Supports incremental builds for speed

**When to Choose Which?**
- Ant → Legacy projects or very custom build logic
- Maven → Standard enterprise apps with well-defined dependencies
- Gradle → Modern projects needing speed, flexibility, and multi-language support



## Maven 
- Apache Maven is a build automation and project management tool used primarily for Java-based applications. 
- It simplifies the build process like compiling code, packaging binaries, managing dependencies, documentation, and deployment.


**Why Maven?**
- Eliminates manual configuration of the build process.
- Handles dependency resolution and management automatically.
- Standardizes the project layout across teams.

**Maven Project Structure (Convention over Configuration)**
```bash
my-app/
├── pom.xml              # Project Object Model (POM) file
├── src/
│   ├── main/
│   │   ├── java/        # Java source files
│   │   └── resources/   # Properties, XML, YAML, etc.
│   └── test/
│       ├── java/        # Unit tests
│       └── resources/   # Test resources
└── target/              # Output folder (build artifacts)
```
- This structure is conventional, not mandatory, but strongly recommended

### **Project Object Model (POM.xml)**
- The pom.xml is the core file in a Maven project. It contains all configuration needed for:
    - Project metadata
    - Dependencies
    - Plugins
    - Build steps
    - Profiles
    - Properties

**Minimal POM Example:**
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.example</groupId>
  <artifactId>my-app</artifactId>
  <version>1.0.0</version>
</project>
```

**Key Elements:**
| Element        | Description                                           |
| -------------- | ----------------------------------------------------- |
| `groupId`      | Organization or domain name (e.g., `com.example`)     |
| `artifactId`   | Name of the artifact/project (e.g., `my-app`)         |
| `version`      | Current version of the project                        |
| `packaging`    | Type: `jar`, `war`, `pom`, etc. (default: `jar`)      |
| `dependencies` | List of external libraries                            |
| `build`        | Configuration for plugins and build goals             |
| `properties`   | Custom properties, reusable across the POM            |
| `profiles`     | Conditional configurations for different environments |

---
### Dependency Management
- A dependency is a library that your project needs to compile and run.
- Maven Repository Types:
    - Local Repository (~/.m2/repository)
    - Central Repository (default: https://repo.maven.apache.org)
    - Remote/Internal Repository (e.g., Nexus, Artifactory)


- Adding a Dependency:
```xml
<dependencies>
  <dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.36</version>
  </dependency>
</dependencies>
```

- Transitive Dependencies: Maven resolves transitive dependencies automatically (dependencies of dependencies)
```bash
mvn dependency:tree
```
- Dependency Scope:

| Scope      | Used For                                    | Included In Final Artifact? |
| ---------- | ------------------------------------------- | --------------------------- |
| `compile`  | Default, used everywhere                    | ✅ Yes                       |
| `provided` | Available at runtime (e.g., Servlet API)    | ❌ No                        |
| `runtime`  | Needed only at runtime (e.g., JDBC drivers) | ✅ Yes                       |
| `test`     | Only for testing (JUnit, Mockito)           | ❌ No                        |
| `system`   | Provided via system path (discouraged)      | ❌ No                        |


---
### 🌀 Maven Life Cycle
- A Life Cycle in Maven is a sequence of phases that define the steps in the build process. Maven has three built-in life cycles:

**1. Default Life Cycle**
- Used to build the application. It contains the main phases:

| Phase      | Description                                                                  |
| ---------- | ---------------------------------------------------------------------------- |
| `validate` | Validates the project is correct and all necessary information is available. |
| `compile`  | Compiles the source code of the project.                                     |
| `test`     | Tests the compiled source code using a unit testing framework (e.g., JUnit). |
| `package`  | Packages the compiled code into a distributable format like a JAR or WAR.    |
| `verify`   | Runs any checks to verify the package is valid.                              |
| `install`  | Installs the package into the local Maven repository.                        |
| `deploy`   | Copies the final package to a remote repository for sharing.                 |

**2. Clean Life Cycle**
- Handles project cleaning:

| Phase        | Description                                     |
| ------------ | ----------------------------------------------- |
| `pre-clean`  | Perform operations before cleaning.             |
| `clean`      | Deletes files generated during previous builds. |
| `post-clean` | Perform operations after cleaning.              |


**3. Site Life Cycle**
- Generates project documentation:

| Phase         | Description                                 |
| ------------- | ------------------------------------------- |
| `pre-site`    | Prepares for site generation.               |
| `site`        | Generates the project’s site documentation. |
| `post-site`   | Finalizes the site generation.              |
| `site-deploy` | Deploys the site to a web server.           |

---
### 🎯 Maven Goals
- A goal is a specific task that contributes to building and managing the project (e.g., compiling code, generating documentation).
- Goals are bound to phases in a life cycle.
- You can also invoke goals directly.
- Examples:

| Goal                   | Plugin                | Description                 |
| ---------------------- | --------------------- | --------------------------- |
| `compiler:compile`     | maven-compiler-plugin | Compiles main source files. |
| `compiler:testCompile` | maven-compiler-plugin | Compiles test source files. |
| `surefire:test`        | maven-surefire-plugin | Runs unit tests.            |
| `jar:jar`              | maven-jar-plugin      | Creates a JAR file.         |

**✅ How Goals and Phases Work Together**
- When you run:
```bash
mvn install
```
- Maven:
    - Executes all phases up to install.
    - Internally calls goals like compile, test, package, etc., based on their binding to those phases.

- You can also run a goal directly:
```bash
mvn compiler:compile
```

---
## 🔌 Maven Plugins

**What are Plugins?**
- Plugins extend Maven’s functionality.
- Used to perform tasks like compiling code, running tests, packaging, etc.

- Common Plugins:

| Plugin                  | Purpose                                  |
| ----------------------- | ---------------------------------------- |
| `maven-compiler-plugin` | Compiles Java source files               |
| `maven-surefire-plugin` | Runs unit tests                          |
| `maven-jar-plugin`      | Packages JAR files                       |
| `maven-war-plugin`      | Builds WAR files for web apps            |
| `maven-deploy-plugin`   | Deploys artifacts to a remote repository |

- Example Plugin Configuration:
```xml
<build>
  <plugins>
    <plugin>
      <artifactId>maven-compiler-plugin</artifactId>
      <version>3.10.1</version>
      <configuration>
        <source>1.8</source>
        <target>1.8</target>
      </configuration>
    </plugin>
  </plugins>
</build>
```

---
### 🧪 Maven Testing Support
- JUnit is the default testing framework.
- Uses the Surefire plugin for unit testing.
- Uses Failsafe plugin for integration testing.
- Running Tests:
```bash
mvn test
```
- Surefire Plugin (Unit Tests):
```xml
<plugin>
  <artifactId>maven-surefire-plugin</artifactId>
  <version>3.0.0-M5</version>
</plugin>
```

- Failsafe Plugin (Integration Tests):
```xml
<plugin>
  <artifactId>maven-failsafe-plugin</artifactId>
  <version>3.0.0-M5</version>
</plugin>
```

---
### 🔧 Maven Profiles
- Use profiles to customize builds for different environments (e.g., dev, qa, prod).
- Allows you to define different configurations per environment (e.g., dev, staging, prod).

```xml
<profiles>
  <profile>
    <id>dev</id>
    <properties>
      <env>development</env>
    </properties>
    <activation>
      <activeByDefault>true</activeByDefault>
    </activation>
  </profile>
  <profile>
    <id>prod</id>
    <properties>
      <env>production</env>
    </properties>
  </profile>
</profiles>

```

- Activate Profile:
```bash
mvn clean install -P dev
```

---
### 🧩 Multi-Module Projects
- A multi-module project allows you to manage multiple sub-projects under a single parent project. 
- Each submodule can be compiled and built independently or as part of the whole.

- Structure:
```xml
project-root/
│
├── pom.xml (Parent POM)
├── module-core/
│   └── pom.xml
├── module-service/
│   └── pom.xml
├── module-web/
│   └── pom.xml

```
- Parent POM:
```xml
<packaging>pom</packaging>
<modules>
  <module>module-core</module>
  <module>module-service</module>
  <module>module-web</module>
</modules>

```

- Child Module POM:
```xml
<parent>
  <groupId>com.example</groupId>
  <artifactId>parent-project</artifactId>
  <version>1.0</version>
</parent>
```

---
### Dependency Management vs Dependencies
- `dependencies`
    - Declared inside the <dependencies> section in your pom.xml
    - Used to include and define actual libraries with <groupId>, <artifactId>, <version>
    - These are actual libraries your project directly uses.

```xml
<dependencies>
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>5.3.9</version>
  </dependency>
</dependencies>
```

- `dependencyManagement`
    - Declared inside the <dependencyManagement> section.
    - It does not include the dependency in your build unless explicitly added to <dependencies>.
    - It is used to control versions across multiple modules (especially in multi-module projects or parent POMs).
    - Used only in parent POMs to define versioning rules
    - Modules inherit versions automatically
    - Child modules can inherit versions from it without needing to specify them.

- Example:
```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>5.3.9</version> <!-- version defined once -->
    </dependency>
  </dependencies>
</dependencyManagement>
```
- In a child module:
```xml
<dependencies>
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <!-- no version needed -->
  </dependency>
</dependencies>
```

**🔗 Transitive Dependencies**
- Maven automatically includes transitive dependencies—dependencies of your dependencies.
- Example
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

- It may internally bring:
    - spring-core
    - jackson-databind
    - tomcat

- These transitive dependencies can sometimes cause:
    - Version conflicts
    - Duplicate classes
    - Increased build size

**🚫 How to Exclude Transitive Dependencies**
- To prevent unwanted transitive dependencies, use the <exclusions> tag:
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
  <exclusions>
    <exclusion>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-tomcat</artifactId>
    </exclusion>
  </exclusions>
</dependency>
```
- You can also override versions by explicitly declaring the dependency with your desired version.


**🔁 Cyclic Dependencies**
- A cyclic dependency occurs when two or more modules depend on each other, either directly or indirectly:
    - `A → B → A (direct)`
        - Module A depends on Module B
        - Module B, in turn, depends on Module A
    - `A → B → C → A (indirect)`

**🚨 Why are Cycles Bad?**
- They break modularity and create tight coupling.
- They can lead to build failures, stack overflow errors, or runtime exceptions.
- They make maintenance and testing harder.

**✅ How to Fix Cyclic Dependencies**
- Refactor Code
- Reorganize Packages/Modules
- Use Dependency Inversion Principle (DIP)


---
### `<repositories>` in pom.xml
- To add additional remote repositories where Maven can search for dependencies (JARs, POMs, plugins) not available in Maven Central.

**1. Private/Internal Artifacts**
- Your company builds internal Java libraries and hosts them on Nexus or Artifactory.
- These are not in Maven Central.

```xml
<repositories>
  <repository>
    <id>company-internal</id>
    <url>https://repo.company.com/maven2</url>
  </repository>
</repositories>
```

**2. Third-Party Libraries Not in Maven Central**
- Some open-source projects or vendors (like JBoss, Spring milestones, etc.) host their artifacts on separate repos.
```xml
<repositories>
  <repository>
    <id>jboss</id>
    <url>https://repository.jboss.org/nexus/content/groups/public/</url>
  </repository>
</repositories>

```

**3. Snapshot Repositories**
- If you use dependencies like my-lib-1.0-SNAPSHOT, Maven must fetch them from a snapshot repository.
- Maven Central does not host snapshots.

```xml
<repositories>
  <repository>
    <id>sonatype-snapshots</id>
    <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
    <releases><enabled>false</enabled></releases>
    <snapshots><enabled>true</enabled></snapshots>
  </repository>
</repositories>
```

- `<repositories>` → for regular dependencies (libraries your app uses)
- `<pluginRepositories>` → for build plugins (e.g., Maven Shade, Compiler Plugin)
---
### 📦 Working with Nexus/Artifactory (Internal Repos)

**🔍 Why Needed:**
- Store internal JARs
- Cache 3rd party dependencies
- Faster, secure builds

**settings.xml**
```xml
<servers>
  <server>
    <id>internal-repo</id>
    <username>user</username>
    <password>password</password>
  </server>
</servers>

<mirrors>
  <mirror>
    <id>internal</id>
    <mirrorOf>*</mirrorOf>
    <url>https://nexus.company.com/repository/maven-public</url>
  </mirror>
</mirrors>
```
---

### Maven settings.xml
- A Maven configuration file for local/environment-specific settings.
- Not part of the project (pom.xml is for project-specific config).
- Locations:
  - User-level: ~/.m2/settings.xml → affects only your user
  - Global-level: ${MAVEN_HOME}/conf/settings.xml → affects all users on the machine

**Why is it Needed?**
- To configure Maven behavior without changing the project pom.xml.
- Store environment-specific info like:
  - Repository credentials
  - Proxy settings
  - Mirror configuration
  - Local repository path
  - Build profiles

**Common Usages**
| **Purpose**                        | **Example**                                        |
| ---------------------------------- | -------------------------------------------------- |
| **Private Repository Credentials** | `<servers>` block with username/password           |
| **Repository Mirrors**             | `<mirrors>` block to use Nexus/Artifactory         |
| **Proxy Config**                   | `<proxies>` block for corporate proxy              |
| **Profiles**                       | `<profiles>` block for dev/test/prod settings      |
| **Local Repo Path**                | `<localRepository>` tag to change default location |


**Example settings.xml**
```xml
<settings>
    <localRepository>/opt/maven/repo</localRepository>

    <servers>
        <server>
            <id>private-repo</id>
            <username>admin</username>
            <password>secret</password>
        </server>
    </servers>

    <mirrors>
        <mirror>
            <id>nexus</id>
            <mirrorOf>*</mirrorOf>
            <url>http://nexus.company.com/repo/maven-public/</url>
        </mirror>
    </mirrors>

    <proxies>
        <proxy>
            <id>proxy1</id>
            <active>true</active>
            <protocol>http</protocol>
            <host>proxy.company.com</host>
            <port>8080</port>
            <username>proxyuser</username>
            <password>proxypass</password>
        </proxy>
    </proxies>

    <profiles>
        <profile>
            <id>dev</id>
            <properties>
                <env>development</env>
            </properties>
        </profile>
    </profiles>
</settings>
```

**Key Differences — pom.xml vs settings.xml**
| **`pom.xml`**                  | **`settings.xml`**                       |
| ------------------------------ | ---------------------------------------- |
| Project-specific config        | Environment/user-specific config         |
| Shared in version control      | Not committed to version control         |
| Declares dependencies, plugins | Configures mirrors, proxies, credentials |
| Same for all devs              | Can differ per developer                 |

