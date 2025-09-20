# Looksy Digital Wardrobe - Documentation

This folder contains comprehensive documentation for the Looksy digital wardrobe application.

## 📁 Documentation Structure

### 📄 [Class Diagram](class-diagram.md)
Complete UML class diagram showing all classes, relationships, and dependencies in the Looksy application.

### 📁 [Sequence Diagrams](sequence-diagrams/)
Individual sequence diagrams for each major application flow:

1. **[Application Startup](sequence-diagrams/01-application-startup.md)** - How the app starts and initializes the database
2. **[Get All Clothes](sequence-diagrams/02-get-all-clothes.md)** - Retrieving all clothes from the wardrobe
3. **[Create Clothes (Bug)](sequence-diagrams/03-create-clothes-bug.md)** - Creating new clothes (has implementation issue)
4. **[Filter Clothes](sequence-diagrams/04-filter-clothes.md)** - Filtering clothes by various criteria
5. **[Find Clothes by ID](sequence-diagrams/05-find-clothes-by-id.md)** - Getting a specific clothing item
6. **[Delete Clothes](sequence-diagrams/06-delete-clothes.md)** - Removing clothes from the wardrobe  
7. **[Size Conversion Exception](sequence-diagrams/07-size-conversion-exception.md)** - Size conversion with error handling

## 🏗️ Architecture Overview

The Looksy application follows a layered Spring Boot architecture:

- **Controller Layer**: REST endpoints for HTTP requests (`ClothesController`, `FilterController`)
- **Service Layer**: Business logic (currently `ClothesService` - unused by controllers)
- **Repository Layer**: Data access via JPA (`ClothesRepository`)
- **Entity Layer**: Data models (`Clothes` with various enums)
- **Component Layer**: Utilities (`Filter`, JSON serializers/deserializers)

## 🐛 Known Issues

1. **ClothesController.createClothes()**: Doesn't save to database
2. **FilterController**: Creates new Filter instance each request instead of dependency injection
3. **Error Handling**: Missing proper HTTP status codes and error responses
4. **Service Layer**: Exists but unused by controllers

## 📊 Key Features

- **CRUD Operations**: Create, Read, Update, Delete clothes
- **Filtering**: Filter by type, season, size, material, cleanliness, color
- **JSON Serialization**: Custom Color object serialization
- **Database Integration**: JPA/Hibernate with auto-initialization
- **API Documentation**: Swagger/OpenAPI integration

## 🚀 Getting Started

1. Run the application: `gradle bootRun`
2. Access API at: `http://localhost:8080/api/clothes`
3. View Swagger docs at: `http://localhost:8080/swagger-ui.html` (if configured)

## 📋 API Endpoints

### Clothes Management
- `GET /api/clothes` - Get all clothes
- `GET /api/clothes/{id}` - Get clothes by ID
- `POST /api/clothes` - Create new clothes (⚠️ doesn't save to DB)
- `DELETE /api/clothes/{id}` - Delete clothes

### Filtering
- `GET /api/filter/type/{type}` - Filter by type
- `GET /api/filter/season/{season}` - Filter by season
- `GET /api/filter/size/{size}` - Filter by size
- `GET /api/filter/material/{material}` - Filter by material
- `GET /api/filter/clean/{clean}` - Filter by cleanliness
- `GET /api/filter/color/{color}` - Filter by color