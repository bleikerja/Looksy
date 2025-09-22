# Size Conversion with Exception Flow

This diagram shows how the Size enum's `onlyLetters()` method works and how it throws exceptions for unknown sizes.

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

## Size Conversion Logic:
The `Size.onlyLetters()` method converts numeric sizes to letter sizes:

- `_34, _36` → `_XS`
- `_38` → `_S`  
- `_40` → `_M`
- `_42` → `_L`
- `_44` → `_XL`
- **All other sizes** → `NoKnownSize` exception

## Exception Details:
- **Exception Type**: `NoKnownSize extends RuntimeException`
- **Message**: "I don't know which Size this is."
- **Trigger**: Any size not in the conversion mapping

## Usage Example:
```java
try {
    Size letterSize = Size._38.onlyLetters(Size._38);  // Returns _S
    Size unknownSize = Size._46.onlyLetters(Size._46); // Throws exception
} catch (NoKnownSize e) {
    // Handle unknown size
    System.out.println(e.getMessage());
}
```

## Potential Issues:
- **Limited conversion range**: Only handles 5 specific sizes
- **Runtime exception**: Could crash application if not properly handled
- **No controller currently uses this method**: It's available but unused in the API