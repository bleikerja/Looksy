# Looksy Digital Wardrobe - Sequence Diagrams

## Main Application Flows

### 1. Application Startup Flow
```mermaid
sequenceDiagram
    participant User
    participant LooksyApplication
    participant LoadDatabase
    participant ClothesRepository
    participant Database
    
    User->>+LooksyApplication: java -jar looksy.jar
    LooksyApplication->>+LoadDatabase: @Bean initDatabase()
    LoadDatabase->>+ClothesRepository: save(blackPants)
    ClothesRepository->>+Database: INSERT INTO clothes
    Database-->>-ClothesRepository: saved entity
    ClothesRepository-->>-LoadDatabase: Clothes object
    LoadDatabase->>+ClothesRepository: save(cyanTop)
    ClothesRepository->>+Database: INSERT INTO clothes
    Database-->>-ClothesRepository: saved entity
    ClothesRepository-->>-LoadDatabase: Clothes object
    LoadDatabase-->>-LooksyApplication: Database initialized
    LooksyApplication-->>-User: Server started on port 8080
```

### 2. Get All Clothes Flow
```mermaid
sequenceDiagram
    participant Client
    participant ClothesController
    participant ClothesRepository
    participant Database
    participant ColorSerializer
    
    Client->>+ClothesController: GET /api/clothes
    ClothesController->>+ClothesRepository: findAll()
    ClothesRepository->>+Database: SELECT * FROM clothes
    Database-->>-ClothesRepository: List<Clothes>
    ClothesRepository-->>-ClothesController: List<Clothes>
    
    loop For each Clothes object
        ClothesController->>+ColorSerializer: serialize(color)
        ColorSerializer-->>-ClothesController: JSON {"red":x, "green":y, "blue":z}
    end
    
    ClothesController-->>-Client: JSON Array of Clothes
```

### 3. Create New Clothes Flow
```mermaid
sequenceDiagram
    participant Client
    participant ClothesController
    participant ColorDeserializer
    participant ClothesRepository
    participant Database
    
    Client->>+ClothesController: POST /api/clothes (JSON)
    ClothesController->>+ColorDeserializer: deserialize(colorJSON)
    ColorDeserializer-->>-ClothesController: Color object
    
    Note over ClothesController: Currently createClothes() doesn't save to DB!
    Note over ClothesController: Should call repository.save(clothes)
    
    ClothesController-->>-Client: ResponseEntity.ok(clothes)
```

### 4. Filter Clothes by Type Flow
```mermaid
sequenceDiagram
    participant Client
    participant FilterController
    participant ClothesRepository
    participant Database
    participant Filter
    
    Client->>+FilterController: GET /api/filter/type/Pants
    FilterController->>+ClothesRepository: findAll()
    ClothesRepository->>+Database: SELECT * FROM clothes
    Database-->>-ClothesRepository: List<Clothes>
    ClothesRepository-->>-FilterController: List<Clothes>
    
    FilterController->>+Filter: new Filter()
    Filter-->>-FilterController: Filter instance
    FilterController->>+Filter: byType(Type.Pants, clothesList)
    
    loop Filter each item
        Filter->>Filter: c.getType() == Type.Pants ?
    end
    
    Filter-->>-FilterController: Filtered List<Clothes>
    FilterController-->>-Client: JSON Array of filtered Clothes
```

### 5. Find Clothes by ID Flow
```mermaid
sequenceDiagram
    participant Client
    participant ClothesController
    participant ClothesRepository
    participant Database
    
    Client->>+ClothesController: GET /api/clothes/1
    ClothesController->>+ClothesRepository: findById(1L)
    ClothesRepository->>+Database: SELECT * FROM clothes WHERE id = 1
    Database-->>-ClothesRepository: Optional<Clothes>
    ClothesRepository-->>-ClothesController: Optional<Clothes>
    ClothesController->>ClothesController: orElse(null)
    ClothesController-->>-Client: Clothes object or null
```

### 6. Delete Clothes Flow
```mermaid
sequenceDiagram
    participant Client
    participant ClothesController
    participant ClothesRepository
    participant Database
    
    Client->>+ClothesController: DELETE /api/clothes/1
    ClothesController->>+ClothesRepository: deleteById(1L)
    ClothesRepository->>+Database: DELETE FROM clothes WHERE id = 1
    Database-->>-ClothesRepository: void
    ClothesRepository-->>-ClothesController: void
    ClothesController-->>-Client: 200 OK (void response)
```

### 7. Size Conversion with Exception Flow
```mermaid
sequenceDiagram
    participant Client
    participant SomeController
    participant Size
    participant NoKnownSize
    
    Client->>+SomeController: convertSize(Size._46)
    SomeController->>+Size: onlyLetters(Size._46)
    
    alt Size not in conversion range
        Size->>+NoKnownSize: throw new NoKnownSize()
        NoKnownSize-->>-Size: RuntimeException
        Size-->>-SomeController: Exception thrown
        SomeController-->>-Client: 500 Internal Server Error
    else Size in conversion range
        Size-->>-SomeController: Converted size (e.g., _XL)
        SomeController-->>-Client: Converted size response
    end
```

## Notes:
- **Issue in ClothesController.createClothes()**: Currently doesn't save to database, only returns the object
- **FilterController Pattern**: Each filter method creates a new Filter instance instead of injecting it
- **Error Handling**: Most endpoints lack proper error handling and validation
- **Service Layer**: ClothesService exists but isn't used by controllers