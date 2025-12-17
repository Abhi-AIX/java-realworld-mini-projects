# Mini Bank Ledger (Data Types)

Concepts:
- long for IDs/timestamps
- BigDecimal for money
- char for txn type
- boolean flags, int counters
- wrapper types for optional parsing

Run:
- Run `MiniBankLedger.main()`

# Java Data Types — Real-World & Interview Notes

These notes focus on **why data types are chosen in real systems**, not just definitions.
Use this as a long-term reference before interviews or while designing code.

---

## 1. Why Data Types Matter
Data types define:
- how data is stored in memory
- what operations are allowed
- performance characteristics
- correctness (precision, overflow, null safety)

Poor data type choices lead to:
- money precision bugs
- overflow issues
- NullPointerExceptions
- hidden performance problems

---

## 2. Categories of Data Types

### 2.1 Primitive Types
Primitive types store the **actual value**.

| Type | Size | Typical Usage |
|----|----|----|
| byte | 1 byte | binary data, streams |
| short | 2 bytes | rare |
| int | 4 bytes | counters, indexes |
| long | 8 bytes | IDs, timestamps |
| float | 4 bytes | rarely used |
| double | 8 bytes | scientific calculations |
| char | 2 bytes | single Unicode character |
| boolean | JVM dependent | flags |

**Key properties**
- Stored on stack
- Cannot be null
- Faster than objects
- No methods

---

### 2.2 Reference Types
Reference types store a **reference (address)** to an object.

Examples:
- String
- Arrays
- Classes
- Records
- Collections

**Key properties**
- Objects live on heap
- Can be null
- Have methods
- Slightly slower due to indirection

---

## 3. Primitive vs Reference (Core Interview Topic)

| Aspect | Primitive | Reference |
|----|----|----|
| Stores | value | memory address |
| Null allowed | ❌ | ✅ |
| Memory | stack | heap |
| Methods | ❌ | ✅ |
| Performance | faster | slower |

---

## 4. Integer Types — Real-World Usage

### `int`
Default integer type.
- Best balance of speed and memory
- Used for loop counters and indexes

```java
int count = 10;
```

### `long`
Used when values may exceed the `int` range.

**Real-world usage**
- Database primary keys
- Epoch timestamps (milliseconds)
- Distributed system IDs
- Large counters

```java
long accountId = 100000000001L;
long timestamp = System.currentTimeMillis();
```

**Why not `int`?**
- `int` max value: ~2.1 billion
- IDs and timestamps easily exceed this

**Rule**
> If there is any doubt about numeric range, use `long`.

---

## 5. Floating Point Types (CRITICAL)

### `float` and `double`
Stored in **binary**, so many decimal numbers cannot be represented exactly.

```java
double d = 0.1 + 0.2;
System.out.println(d); // 0.30000000000000004
```

### Real-World Rule
Never use `float` or `double` for money  
Always use `BigDecimal`

---

## 6. BigDecimal (Money & Precision)

Why BigDecimal:
- Exact decimal representation
- Predictable rounding
- Industry standard for financial systems

**Best practices**
- Always use the String constructor
- Always define scale and rounding

```java
BigDecimal amount =
    new BigDecimal("250.50")
        .setScale(2, RoundingMode.HALF_UP);
```

Bad:
```java
new BigDecimal(0.1); // precision bug
```

---

## 7. `char` vs `String`

### `char`
- Represents a single Unicode character
- Size: 2 bytes
- Strict and efficient

```java
char type = 'D'; // Deposit
```

Use `char` when:
- Only one character is needed
- Valid values are limited and known

---

### `String`
- Represents text
- Immutable
- Stored on heap

Use `String` for:
- Names
- Messages
- Notes
- User input

---

## 8. boolean

Used for flags and conditions.

```java
boolean isValid = true;
```

Notes:
- JVM does not guarantee exact size
- Usually 1 byte (implementation dependent)

---

## 9. Wrapper Classes

| Primitive | Wrapper |
|---------|---------|
| int | Integer |
| long | Long |
| double | Double |
| boolean | Boolean |

### Why wrappers exist
- Required by collections
- Allow `null`
- Provide utility methods

```java
Integer count = null;
```

---

## 10. Autoboxing & Unboxing (Common Bug Source)

```java
Integer x = null;
int y = x; // NullPointerException
```

**Rule**
> Never assume wrapper values are non-null.

Always validate before unboxing.

---

## 11. `==` vs `equals()` (Very Important)

```java
Integer a = 100;
Integer b = 100;
a == b; // true (cached)

Integer x = 200;
Integer y = 200;
x == y; // false
```

**Why?**
- Integer cache range: `-128` to `127`
- `==` compares references, not values

**Rule**
> Use `equals()` for object value comparison.

---

## 12. Method Arguments (Pass-by-Value)

Java is **always pass-by-value**.

- Primitive → value is copied
- Object → reference is copied

```java
void update(BigDecimal x) {
    x = x.add(BigDecimal.ONE);
}
// original reference is unchanged
```

---

## 13. Real-World Data Type Selection Guide

| Scenario | Data Type |
|--------|-----------|
| Loop counters | int |
| Database IDs | long |
| Money | BigDecimal |
| Flags | boolean |
| Text | String |
| Optional numeric value | Wrapper |
| Immutable data carrier | record |

---

## 14. Records (Modern Java)

Records are:
- Immutable
- Ideal for DTOs
- Reduce boilerplate

```java
record Transaction(
    char type,
    long accountId,
    BigDecimal amount,
    long timestampMillis,
    String note
) {}
```

Use records when:
- Data should not change
- Logic is minimal
- Purpose is data transport

---

## 15. Interview Quick Checks

- Why is `long` safer than `int` for IDs?
- Why does `double` fail for money?
- When should wrapper types be used?
- Why is `Integer == Integer` unreliable?
- What bugs does autoboxing cause?

---

## 16. Key Takeaways

- Use `long` when numeric range is uncertain
- Never use floating point for money
- BigDecimal is mandatory for financial values
- Wrappers introduce null risk
- Prefer records for immutable data
- Choose data types intentionally
