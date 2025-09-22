# Get All Clothes Flow

This diagram shows how the system retrieves all clothes from the database and serializes them to JSON for the client.

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

## API Endpoint:
- **Method**: GET
- **URL**: `/api/clothes`
- **Response**: JSON array of all clothes in the wardrobe

## Key Components:
- **ClothesController**: REST controller handling HTTP requests
- **ClothesRepository**: JPA repository for database access
- **ColorSerializer**: Custom Jackson serializer for Color objects

## Process:
1. Client sends GET request to retrieve all clothes
2. Controller calls repository to fetch data from database
3. Database returns all clothes records
4. For each clothes item, the Color field is serialized to JSON format
5. Complete JSON array is returned to client