# Maven Hands-On Project

## Level 1 — Basics

### 1. Create a Maven project using the `maven-archetype-quickstart`
- Compile it and run the default `App.java`.
- Change the Java compiler version to 17 (or whichever your setup uses).  
**Hint:** Modify `maven-compiler-plugin` in `pom.xml`.

### 2. Add an external library and use it
- Add **Apache Commons Lang** (`org.apache.commons:commons-lang3`) as a dependency.
- Use `StringUtils.capitalize()` in your `App.java`.  
**Hint:** Dependency section goes inside `<dependencies>` in `pom.xml`.

### 3. Change packaging type
- Make your project produce a `.war` file instead of a `.jar`.
- Verify `target/` contains the correct artifact.  
**Hint:** `<packaging>` tag controls this.

---

## Level 2 — Lifecycle & Plugins

### 4. Custom build goal
- Add a Maven plugin to run `App.java` directly using `mvn exec:java`.  
**Hint:** `exec-maven-plugin` will help.

### 5. Skip tests in build
- Add a test class that fails.
- Build the project using `mvn package` **without** failing.  
**Hint:** Use `-DskipTests` or configure `maven-surefire-plugin`.

### 6. Add a clean phase customization
- Before cleaning, print “Cleaning Project…” to the console.  
**Hint:** Use `maven-antrun-plugin` bound to the `pre-clean` phase.

---

## Level 3 — Dependency Management

### 7. Use dependency scopes
- Add a logging library (`log4j`) but make it available **only** at runtime.  
**Hint:** `<scope>runtime</scope>`.

### 8. Force a specific transitive dependency version
- Add a dependency that brings a conflicting transitive dependency.
- Use `<dependencyManagement>` to control the version.

---

## Level 4 — Multi-Module Maven

### 9. Create a multi-module project
- **Parent module:** `pom` packaging.
- **Two child modules:**
  - `module-a`: produces a jar with a utility class.
  - `module-b`: depends on module-a and calls the utility.  
**Hint:** Parent’s `pom.xml` contains `<modules>` list.

### 10. Build only one module without building the whole project
**Hint:** Use `-pl` and `-am` flags.

---

## Level 5 — Advanced

### 11. Create a Maven profile
- Add a `dev` profile that uses a different dependency version.
- Run `mvn package -Pdev` to test.

### 12. Generate a site report
- Configure `maven-site-plugin` to generate a project report with dependencies, plugins, and Javadoc.
- Run `mvn site` and check `target/site/index.html`.

### 13. Integrate with an external tool
- Configure `jacoco-maven-plugin` to generate code coverage reports during the test phase.
