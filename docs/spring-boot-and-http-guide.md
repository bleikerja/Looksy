# Spring Boot and HTTP Requests Guide

A comprehensive guide to understanding Spring Boot, HTTP requests, and REST APIs in the context of the Looksy Digital Wardrobe project.

## Table of Contents
1. [What is Spring Boot?](#what-is-spring-boot)
2. [Spring Boot Syntax and Annotations](#spring-boot-syntax-and-annotations)
3. [Spring Framework vs Spring Boot](#spring-framework-vs-spring-boot)
4. [HTTP Requests and REST APIs](#http-requests-and-rest-apis)
5. [JSON as the Common Language](#json-as-the-common-language)
6. [Dependency Injection and IoC](#dependency-injection-and-ioc)
7. [Data Persistence with JPA](#data-persistence-with-jpa)
8. [Application Architecture](#application-architecture)

---

## What is Spring Boot?

**Spring Boot** is a Java framework that simplifies building production-ready applications. Think of it as a "batteries-included" version of Java web development that eliminates most of the tedious configuration work.

### Purpose of Spring Boot:
- **Rapid Development**: Get a web application running in minutes, not hours
- **Convention over Configuration**: Sensible defaults so you don't need to configure everything
- **Embedded Server**: No need to deploy to external servers like Tomcat
- **Production-Ready**: Built-in monitoring, health checks, and metrics
- **Microservices Friendly**: Perfect for building small, focused services

### In the Looksy Project:
Our main application class demonstrates Spring Boot's simplicity:

```java
@SpringBootApplication
public class LooksyApplication {
    public static void main(String[] args) {
        SpringApplication.run(LooksyApplication.class, args);
    }
}
```

This single annotation `@SpringBootApplication` does three things:
- `@Configuration`: Marks this as a configuration class
- `@EnableAutoConfiguration`: Automatically configures Spring based on dependencies
- `@ComponentScan`: Scans for Spring components in this package and subpackages

---

## Spring Boot Syntax and Annotations

Spring Boot uses **annotations** (those `@Something` declarations) to tell the framework how to handle your code. Here are the most important ones used in Looksy:

### 1. Component Annotations

#### `@RestController`
Marks a class as a REST API controller that returns JSON data directly.

```java
@RestController
@RequestMapping("/api/clothes")
public class ClothesController {
    // This class handles HTTP requests and returns JSON
}
```

#### `@Service`
Marks a class as a service layer component containing business logic.

```java
@Service
public class ClothesService {
    // Business logic goes here
}
```

#### `@Repository`
Marks an interface as a data access layer (though often inherited from JpaRepository).

```java
public interface ClothesRepository extends JpaRepository<Clothes, Long> {
    // Database operations
}
```

#### `@Entity`
Marks a class as a database table.

```java
@Entity
public class Clothes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // This class represents a database table
}
```

### 2. HTTP Mapping Annotations

#### `@RequestMapping`
Base URL path for all endpoints in a controller.

```java
@RequestMapping("/api/clothes")  // Base path
public class ClothesController {
```

#### `@GetMapping`, `@PostMapping`, `@DeleteMapping`
Define HTTP methods for specific endpoints.

```java
@GetMapping                    // GET /api/clothes
public List<Clothes> getAllClothes() {

@PostMapping                   // POST /api/clothes
public ResponseEntity<Clothes> createClothes(@RequestBody Clothes clothes) {

@GetMapping("/{id}")          // GET /api/clothes/1
public Clothes findClothes(@PathVariable long id) {

@DeleteMapping("/{id}")       // DELETE /api/clothes/1
public void deleteClothes(@PathVariable long id) {
```

### 3. Data Binding Annotations

#### `@RequestBody`
Converts JSON from HTTP request into a Java object.

```java
@PostMapping
public ResponseEntity<Clothes> createClothes(@RequestBody Clothes clothes) {
    // JSON from frontend becomes a Clothes object
}
```

#### `@PathVariable`
Extracts values from the URL path.

```java
@GetMapping("/{id}")
public Clothes findClothes(@PathVariable long id) {
    // If URL is /api/clothes/5, then id = 5
}
```

### 4. Configuration Annotations

#### `@Configuration` and `@Bean`
Define configuration and create managed objects.

```java
@Configuration
public class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(ClothesRepository repository) {
        // This method runs at startup and initializes the database
    }
}
```

---

## Spring Framework vs Spring Boot

### The Spring Framework
The **Spring Framework** is a comprehensive framework that solves the problem of **tightly coupled code** in Java applications. Before Spring, Java applications often had objects directly creating and managing their dependencies, making code hard to test and maintain.

**Problem Spring Solves:**
```java
// Without Spring - tightly coupled
public class ClothesController {
    private ClothesRepository repository = new ClothesRepository(); // Hard-coded dependency
    private ClothesService service = new ClothesService(repository); // Manual wiring
}
```

**Spring's Solution - Dependency Injection:**
```java
// With Spring - loosely coupled
public class ClothesController {
    private final ClothesRepository repository;
    
    public ClothesController(ClothesRepository repository) {
        this.repository = repository; // Spring injects this automatically
    }
}
```

### How Spring Boot Enhances Spring Framework

**Traditional Spring** required extensive XML configuration files and manual setup. **Spring Boot** eliminates this complexity:

- **Auto-Configuration**: Automatically configures components based on what's on your classpath
- **Starter Dependencies**: Pre-packaged dependency bundles (like `spring-boot-starter-web`)
- **Embedded Servers**: No need to deploy to external Tomcat/Jetty
- **Production Features**: Health checks, metrics, and monitoring out-of-the-box

**Before Spring Boot** (lots of XML configuration):
```xml
<!-- web.xml, applicationContext.xml, dispatcher-servlet.xml, etc. -->
<bean id="clothesRepository" class="...">
<bean id="clothesService" class="..." depends-on="clothesRepository">
```

**With Spring Boot** (just annotations):
```java
@SpringBootApplication  // That's it!
public class LooksyApplication {
```

---

## HTTP Requests and REST APIs

### What is REST?
**REST** (Representational State Transfer) is an architectural style for designing web APIs. It uses standard HTTP methods to perform operations on resources.

### HTTP Methods in Looksy

#### GET - Retrieve Data
```java
@GetMapping                    // GET /api/clothes - get all clothes
public List<Clothes> getAllClothes() {
    return repository.findAll();
}

@GetMapping("/{id}")          // GET /api/clothes/1 - get specific item
public Clothes findClothes(@PathVariable long id) {
    return repository.findById(id).orElse(null);
}
```

#### POST - Create Data
```java
@PostMapping                  // POST /api/clothes - create new item
public ResponseEntity<Clothes> createClothes(@RequestBody Clothes clothes) {
    return ResponseEntity.ok(clothes);
}
```

#### DELETE - Remove Data
```java
@DeleteMapping("/{id}")       // DELETE /api/clothes/1 - remove item
public void deleteClothes(@PathVariable long id) {
    repository.deleteById(id);
}
```

### REST API Design Principles in Looksy

1. **Resource-Based URLs**: `/api/clothes` represents the clothes resource
2. **HTTP Methods for Actions**: 
   - GET for reading
   - POST for creating
   - DELETE for removing
3. **Hierarchical Structure**: `/api/clothes/{id}` for specific items
4. **Stateless**: Each request contains all necessary information

### Filter Controller Example
The `FilterController` shows REST API design for filtering:

```java
@GetMapping("/type/{type}")           // GET /api/filter/type/Pants
public List<Clothes> getClothesByType(@PathVariable Type type) {

@GetMapping("/season/{season}")       // GET /api/filter/season/Summer
public List<Clothes> getClothesBySaison(@PathVariable Season season) {

@GetMapping("/material/{material}")   // GET /api/filter/material/Cotton
public List<Clothes> getClothesByMaterial(@PathVariable Material material) {
```

---

## JSON as the Common Language

**JSON** (JavaScript Object Notation) is the universal data format for web APIs. It's language-agnostic, lightweight, and human-readable.

### Why JSON?
- **Frontend ↔ Backend Communication**: JavaScript frontends and Java backends both understand JSON
- **Cross-Platform**: Works with any programming language
- **Simple Structure**: Easy to read and write
- **Web Standard**: Built into HTTP and modern web browsers

### JSON Serialization in Looksy

When a Java object is returned from a controller, Spring Boot automatically converts it to JSON:

```java
@GetMapping
public List<Clothes> getAllClothes() {
    return repository.findAll(); // Spring converts this List to JSON array
}
```

**Java Object:**
```java
Clothes clothes = new Clothes(Color.BLACK, Size._38, Season.inBetween, Type.Pants, Material.jeans, true);
```

**Becomes JSON:**
```json
{
  "id": 1,
  "color": {"red": 0, "green": 0, "blue": 0},
  "size": "_38",
  "seasonUsage": "inBetween",
  "type": "Pants",
  "material": "jeans",
  "clean": true
}
```

### Custom JSON Handling
The `Clothes` class uses custom serialization for the `Color` object:

```java
@JsonSerialize(using = ColorSerializer.class)
private Color color;

@JsonCreator
public Clothes(@JsonProperty("color") Color color,
               @JsonProperty("size") Size size, ...) {
```

This ensures that Java's `Color` object (with RGB values) is properly converted to/from JSON.

### Frontend-Backend Communication Flow

1. **Frontend Request**: JavaScript sends HTTP request with JSON data
```javascript
fetch('/api/clothes', {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({color: {red: 255, green: 0, blue: 0}, size: "_M", ...})
})
```

2. **Backend Processing**: Spring Boot converts JSON to Java object
```java
@PostMapping
public ResponseEntity<Clothes> createClothes(@RequestBody Clothes clothes) {
    // clothes is now a Java object created from JSON
}
```

3. **Backend Response**: Java object converted back to JSON
4. **Frontend Processing**: JavaScript receives and uses JSON data

---

## Dependency Injection and IoC

**Dependency Injection (DI)** is Spring's core feature. Instead of objects creating their dependencies, Spring provides them automatically.

### Constructor Injection (Recommended)
```java
@RestController
public class ClothesController {
    private final ClothesRepository repository;
    
    public ClothesController(ClothesRepository repository) {
        this.repository = repository; // Spring injects this
    }
}
```

### Field Injection (Used in Service)
```java
@Service
public class ClothesService {
    @Autowired
    private ClothesRepository clothesRepository; // Spring injects this
}
```

### Benefits of DI:
- **Testability**: Easy to inject mock objects for testing
- **Flexibility**: Change implementations without changing code
- **Loose Coupling**: Classes don't depend on specific implementations

---

## Data Persistence with JPA

**JPA** (Java Persistence API) is Java's standard for database operations. Spring Data JPA makes it even simpler.

### Entity Class
```java
@Entity  // This class maps to a database table
public class Clothes {
    @Id  // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment
    private Long id;
    
    private Size size;      // These become database columns
    private Season seasonUsage;
    // ...
}
```

### Repository Interface
```java
public interface ClothesRepository extends JpaRepository<Clothes, Long> {
    // JpaRepository provides: findAll(), findById(), save(), deleteById(), etc.
    // No implementation needed - Spring generates it!
}
```

### Database Operations in Controller
```java
@GetMapping
public List<Clothes> getAllClothes() {
    return repository.findAll();  // SELECT * FROM clothes
}

@DeleteMapping("/{id}")
public void deleteClothes(@PathVariable long id) {
    repository.deleteById(id);    // DELETE FROM clothes WHERE id = ?
}
```

---

## Application Architecture

The Looksy application follows a **layered architecture**:

```
┌─────────────────────────────────────────────────────────┐
│                  Presentation Layer                     │
│  ┌─────────────────────┐  ┌─────────────────────────────┐│
│  │  ClothesController  │  │    FilterController        ││
│  │  - HTTP endpoints   │  │    - Filter endpoints      ││
│  │  - JSON handling    │  │    - Parameter binding     ││
│  └─────────────────────┘  └─────────────────────────────┘│
└─────────────────────────────────────────────────────────┘
                             │
                             ▼
┌─────────────────────────────────────────────────────────┐
│                   Service Layer                         │
│  ┌─────────────────────┐                                │
│  │   ClothesService    │                                │
│  │   - Business logic  │                                │
│  │   - Data processing │                                │
│  └─────────────────────┘                                │
└─────────────────────────────────────────────────────────┘
                             │
                             ▼
┌─────────────────────────────────────────────────────────┐
│                   Data Access Layer                     │
│  ┌─────────────────────┐                                │
│  │  ClothesRepository  │                                │
│  │  - Database ops     │                                │
│  │  - Query methods    │                                │
│  └─────────────────────┘                                │
└─────────────────────────────────────────────────────────┘
                             │
                             ▼
┌─────────────────────────────────────────────────────────┐
│                     Database                            │
│              (H2/MySQL/PostgreSQL)                      │
└─────────────────────────────────────────────────────────┘
```

### Flow Example (Getting All Clothes):
1. **HTTP Request**: `GET /api/clothes`
2. **Controller**: `ClothesController.getAllClothes()` receives request
3. **Repository**: Calls `repository.findAll()` to get data
4. **Database**: Executes `SELECT * FROM clothes`
5. **Response**: Data flows back up, converted to JSON, sent to client

### Configuration and Initialization:
- **LooksyApplication**: Main class that starts the application
- **LoadDatabase**: Configuration class that initializes sample data
- **Components**: Entity classes like `Clothes`, enums like `Size`, `Type`, etc.

---

## Key Concepts Summary

### For a Developer Coming from Other Languages:

1. **Annotations Are Your Friends**: Spring Boot heavily uses annotations instead of XML configuration files. Think of them as metadata that tells Spring how to handle your classes.

2. **Inversion of Control (IoC)**: You don't create objects yourself; Spring creates and manages them. This is the opposite of traditional programming where you use `new` everywhere.

3. **Convention over Configuration**: If you follow naming conventions and use standard patterns, Spring Boot automatically configures everything for you.

4. **HTTP is the Transport Layer**: Your Java methods become web endpoints that can be called from anywhere on the internet via HTTP requests.

5. **JSON is the Data Format**: Everything that goes in and out of your API is JSON. Spring automatically converts between Java objects and JSON.

6. **Database Abstraction**: You rarely write SQL. JPA and Spring Data handle database operations through method calls on repository interfaces.

7. **Embedded Everything**: Unlike traditional Java web development, you don't need separate web servers, configuration files, or deployment descriptors. Everything is embedded and self-contained.

### Common Patterns in the Project:

- **Controller → Repository**: Controllers often directly use repositories (though Service layer exists)
- **Filter Pattern**: Separate controller for filtering operations
- **Enum Usage**: Heavy use of enums for type safety (`Size`, `Season`, `Type`, etc.)
- **Constructor Injection**: Dependencies injected through constructors
- **ResponseEntity**: Used for more control over HTTP responses

This architecture allows for rapid development while maintaining clean separation of concerns and following established Java enterprise patterns.