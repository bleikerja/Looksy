classDiagram
    class LooksyApplication{
        +main()
    }
    
    class LoadDatabase{
        +initDatabase()
    }
    
    class ClothesController{
        -ClothesRepository repository
        +createClothes()
        +getAllClothes()
        +findClothes()
        +deleteClothes()
    }
    
    class FilterController{
        -ClothesRepository repository
        +getClothesByType()
        +getClothesBySeason()
        +getClothesBySize()
        +getClothesByMaterial()
        +getClothesByCleanliness()
        +getClothesByColor()
    }
    
    class ClothesService{
        -ClothesRepository clothesRepository
        +getAllClothes()
        +findClothesById()
        +saveClothes()
        +deleteClothes()
    }
    
    class ClothesRepository{
        <<interface>>
    }
    
    class Clothes{
        -Long id
        -Color color
        -Size size
        -Season seasonUsage
        -Type type
        -Material material
        -Boolean clean
        -WashingNotes washingNotes
        +getColor()
        +getSize()
        +getSeasonUsage()
        +getType()
        +getMaterial()
        +getClean()
        +setClean()
        +getWashingNotes()
        +setWashingNotes()
    }
    
    class Filter{
        +byType()
        +bySeason()
        +bySize()
        +byMaterial()
        +byCleanliness()
        +byColor()
    }
    
    class Size{
        <<enumeration>>
        _34
        _36
        _38
        _40
        _42
        _44
        _46
        _48
        _50
        _52
        _54
        _56
        _58
        _60
        _XS
        _S
        _M
        _L
        _XL
        +onlyLetters()
    }
    
    class Season{
        <<enumeration>>
        Winter
        Summer
        inBetween
    }
    
    class Type{
        <<enumeration>>
        Dress
        Tops
        Skirt
        Pants
        Jacket
    }
    
    class Material{
        <<enumeration>>
        Wool
        Cotton
        Polyester
        cashmere
        silk
        linen
        fur
        jeans
    }
    
    class WashingNotes{
        <<enumeration>>
        Temperature30
        Hand
        Dying
        Dryer
    }
    
    class ColorSerializer{
        +serialize()
    }
    
    class ColorDeserializer{
        +deserialize()
    }
    
    class JacksonConfig{
        +colorModule()
    }
    
    class NoKnownSize{
        +String message
    }
    
    class Color{
        <<external>>
        +getRed()
        +getGreen()
        +getBlue()
    }
    
    class JpaRepository{
        <<interface>>
        <<external>>
    }
    
    LooksyApplication --> LoadDatabase
    LoadDatabase --> ClothesRepository
    LoadDatabase --> Clothes
    
    ClothesController --> ClothesRepository
    FilterController --> ClothesRepository
    FilterController --> Filter
    ClothesService --> ClothesRepository
    
    ClothesRepository --|> JpaRepository
    
    Clothes --> Size
    Clothes --> Season
    Clothes --> Type
    Clothes --> Material
    Clothes --> WashingNotes
    Clothes --> Color
    
    Filter --> Clothes
    
    Size --> NoKnownSize
    
    ColorSerializer --> Color
    ColorDeserializer --> Color
    JacksonConfig --> ColorSerializer
    JacksonConfig --> ColorDeserializer
```