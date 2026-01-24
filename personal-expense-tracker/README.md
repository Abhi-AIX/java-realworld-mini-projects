# Personal Expense Tracker (Java)

A minimal, educational Java console application to track expenses. It demonstrates small-scale Java application structure, data validation with domain models, and basic user interaction on the command line. This repository is suitable for learning, interview prep, and as a starting point for enhancements (persistence, UI, APIs).

---

## Quick overview

- Package: `org.example`
- Main class: `org.example.Main`
- Model: `org.example.Expense`
- Build system: Maven (Java 21 as configured in `pom.xml`)

---

## Getting started (Windows, PowerShell)

Requirements:
- JDK 21 (or a compatible Java 21+ JDK)
- Maven 3.6+

From the project root (PowerShell):

```powershell
# Build the project (skip tests if any)
mvn -DskipTests package

# Run directly from compiled classes
java -cp target/classes org.example.Main

# Alternatively, run with Maven exec plugin (if installed/configured):
# mvn -q compile exec:java -Dexec.mainClass="org.example.Main"
```

Notes:
- If you package a JAR with dependencies you'll need to adjust the classpath or create an executable jar.
- The repository uses simple console I/O and in-memory storage (no DB).

---

## Core Java Concepts (short reference)

1. Object-Oriented Programming (OOP)
   - Java is class-based and object-oriented. Classes encapsulate state (fields) and behavior (methods).
   - Example: `Expense` is a POJO with fields `title`, `description`, and `amount` and methods to access them.

2. Primitives vs Objects
   - Primitives (int, long, boolean, etc.) are value types; reference types (String, BigDecimal) are objects.
   - Use `BigDecimal` for precise currency arithmetic instead of `double`.

3. Immutability
   - Making fields `final` and avoiding setters improves thread-safety and reasoning about code.
   - Consider making `Expense` immutable if it should not change after creation.

4. Exceptions and validation
   - Use checked exceptions for recoverable conditions and runtime exceptions (IllegalArgumentException, NullPointerException) for programming errors.
   - Validate constructor arguments to maintain class invariants (see `Expense` constructor).

5. Collections (List, Map, Set)
   - Use `List` for ordered collections (`ArrayList`), `Set` for uniqueness, and `Map` for key-value lookup.
   - Example: `List<Expense> expenses = new ArrayList<>();`

6. Generics
   - Generics provide compile-time type safety (e.g., `List<String>`). Avoid raw types.

7. Concurrency
   - Java provides threads, `ExecutorService`, and synchronization primitives. Prefer higher-level constructs (`Executors`, `CompletableFuture`).

8. Streams & Lambdas (Java 8+)
   - Use streams for declarative collection processing: filtering, mapping, and reductions.
   - Example: `expenses.stream().map(Expense::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add)`

9. I/O and try-with-resources
   - Use `try-with-resources` for `AutoCloseable` resources (Streams, Readers) to avoid leaks.

10. equals() / hashCode()
   - Implement `equals` and `hashCode` for value objects used in collections (or use record types in newer Java).

11. BigDecimal for money
   - Use `BigDecimal.setScale(2, RoundingMode.HALF_EVEN)` for currency and avoid floating-point rounding surprises.

12. Modularity & packaging
   - Keep related classes in packages and prefer small classes with single responsibilities.

---

## Project-focused Scenario Interview Questions (Intermediate)

These questions are focused on the code, design choices, and scenarios you might be asked about when discussing this specific `personal-expense-tracker` project. Each question includes a concise model answer and practical considerations.

1. Why do we use both `Objects.requireNonNull(title, "title cannot be null")` and `if (title.isEmpty())` in the `Expense` constructor?
   - Explanation: `requireNonNull` detects `null` references and throws a clear NullPointerException with a helpful message. The `isEmpty()` check distinguishes a present but empty string ("") from `null` and typically results in an IllegalArgumentException because an empty title is considered invalid input rather than a missing reference. Using both keeps invariants explicit and makes failure modes easier to diagnose.
   - Practical note: also consider trimming (`title.trim()`) before the empty check to reject whitespace-only values.

2. How would you change the `Expense` constructor if `description` should be optional?
   - Model answer: Provide an overloaded constructor or accept `null`/empty and normalize to an empty `Optional<String>` or `""` internally. Example: `public Expense(String title, BigDecimal amount)` delegates to the main constructor with `description = ""`.
   - Consideration: If using `Optional` as a field is discouraged; instead use `Optional` in APIs and keep fields as nullable or empty strings with clear documentation.

3. The app currently stores expenses in a `List<Expense>` in memory. How would you add persistence for scale and reliability?
   - Model answer: Add a repository/DAO layer. For a simple step, write CSV/JSON file persistence. For production, use a relational database and JPA (Hibernate) or a lightweight embedded DB (H2, SQLite). Abstract the storage behind an interface (ExpenseRepository) so tests can use an in-memory implementation.
   - Migration path: start with file-based persistence, then introduce a DAO interface and switch to JDBC/JPA when needed.

4. How should currency and localization be handled if the app supports multiple regions?
   - Model answer: Store monetary amounts with `BigDecimal` and the currency code (ISO 4217) as a separate field (e.g., `Currency` or `String currency`). Use `java.text.NumberFormat` or `java.util.Currency` with locale-aware formatting. For conversions, store exchange rates outside the domain model and apply conversions explicitly.
   - Trade-offs: Storing only converted amounts loses source-currency fidelity—prefer storing original amount + currency + optional converted amount with a timestamped rate.

5. If multiple threads can add expenses concurrently, how would you make the collection and total calculation safe?
   - Model answer: Use a thread-safe collection (e.g., `Collections.synchronizedList(new ArrayList<>())` or `CopyOnWriteArrayList` or a concurrent queue). For calculating totals, snapshot to avoid holding locks: copy list into a local unmodifiable list and reduce. Alternatively use atomic accumulators when updating a running total.
   - Performance note: `CopyOnWriteArrayList` is good for more reads than writes; for heavy writes consider other structures.

6. How would you prevent duplicate expense entries (same title, description, amount) from being added?
   - Model answer: Implement `equals` and `hashCode` (or use a unique ID) and maintain a `Set<Expense>` or track deduplication in the repository layer. Consider tolerances for amounts (floating point) — with `BigDecimal` use exact equality at a normalized scale.
   - UX: Ask whether duplicates should be silently ignored, rejected, or merged—choose behavior accordingly.

7. How to unit test the `Expense` constructor and behavior effectively?
   - Model answer: Write JUnit tests for the happy path (valid inputs) and negative cases (null title, empty title, null amount, negative amount). Use parameterized tests for several invalid inputs. Also add tests to assert `toString()` formatting and getters.
   - Example: use `org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> new Expense("", "d", BigDecimal.TEN));`

8. If you need to import 1M expenses from CSV, how do you design the importer for performance and robustness?
   - Model answer: Stream the CSV lines (don't load whole file). Use batching to persist (e.g., write 1000 records at a time). Validate rows and record failures separately. Use an `ExecutorService` to parallelize parsing if CPU-bound, but keep I/O single-threaded unless using concurrent writers.
   - Reliability: add checkpointing and resume support for large imports.

9. How would you expose this functionality as a REST API?
   - Model answer: Create a DTO for requests/responses, add a lightweight web framework (Spring Boot), and map endpoints like `POST /expenses` (create), `GET /expenses` (list), `GET /expenses/total` (aggregate). Validate input with `@Valid` and `@NotBlank` annotations and return appropriate HTTP status codes (201 Created, 400 Bad Request).
   - Security: add authentication/authorization (JWT, OAuth) before exposing to users.

10. Suppose totals sometimes show off-by-cent errors after many adds. What's the likely cause and fix?
    - Model answer: Likely inconsistent scaling or use of `double`. Ensure every amount is normalized (`setScale(2, RoundingMode.HALF_EVEN`) at creation and use `BigDecimal` for accumulation. Avoid setting scale only at output; normalize when constructing values.

11. How to handle edit/delete operations: mutable `Expense` vs immutable `Expense`?
    - Model answer: For auditability prefer immutable value objects with an ID and store versions or a separate edit history. For simplicity, a mutable POJO with setters may be acceptable; document concurrency implications. Immutable models make reasoning and multithreading easier.

12. How to add logging and observability for debugging production issues?
    - Model answer: Use a logging framework (SLF4J + Logback) with structured logs. Add correlation IDs for request tracing, log validation failures with context, and capture metrics (counts, latencies) with Micrometer/Prometheus if the app becomes a service.


### Why both null and empty checks are useful (concrete example)

Code (conceptual snippet from `Expense`):

```java
// called after reading user input and trimming
this.title = Objects.requireNonNull(title, "title cannot be null");
if (this.title.isEmpty()) throw new IllegalArgumentException("title cannot be empty");
```

Explanation:
- Null vs empty are different failure modes: `null` often indicates a missing value (programming bug or deserialization problem). An empty string usually means the user provided no characters. Throwing clear, different exceptions (NPE vs IAE) helps quickly pinpoint whether the issue is a missing reference or invalid input.
- For better UX, you can normalize earlier: `title = (title == null) ? null : title.trim();` then check `requireNonNull` and `isEmpty()` so that whitespace-only input is rejected.
- Alternative approach: throw the same exception type with different messages (e.g., `IllegalArgumentException("title required and must not be empty")`) if you prefer a single error type for input validation.

---

## Real-world Usage & Examples

1. Data modeling with POJOs
   - Use simple classes (POJOs) to represent domain data. In this repo, `Expense` encapsulates title, description, and amount with validation in the constructor to ensure invariants.
   - Trade-off: in-memory model is simple but not persistent; add a DAO/repository layer to persist.

2. Handling user input and validation
   - Validate early and give user-friendly errors. `Main` validates amount parsing and `Expense` validates fields in its constructor.

3. Working with money (BigDecimal)
   - Example: `amount.setScale(2, RoundingMode.HALF_EVEN)` ensures consistent currency rounding.
   - Avoid `double` for sums; use `BigDecimal.ZERO` as an accumulator.

4. Aggregation with Streams
   - Sum amounts: `expenses.stream().map(Expense::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add)`
   - This is concise and avoids manual loops. For very large lists, consider parallel streams with care for thread-safety.

5. Multithreaded processing (example)
   - Use `ExecutorService` to process many expense records in parallel (e.g., importing large CSVs). Ensure shared collections are thread-safe (ConcurrentLinkedQueue) or use thread confinement.

6. Validation and defensive programming
   - Keep validation close to the data (constructors/factory methods). Throw descriptive exceptions for incorrect inputs.

---

## How I think — Problem solving checklist

1. Clarify requirements: ask what success looks like and constraints.
2. Identify data shapes and invariants (what must always be true).
3. Define small API/contract: inputs, outputs, errors.
4. Consider edge cases (null/empty, negative numbers, huge inputs).
5. Choose types and abstractions (BigDecimal vs double, List vs Set).
6. Implement minimal code with validation and clear errors.
7. Add tests (happy path + edge cases).
8. Run quick performance and memory checks if needed.
9. Iterate based on feedback and add persistence/logging.
10. Document decisions.

Apply to `add expense` feature (short example):
- Requirements: add a new expense with a title, optional description, and non-negative amount.
- Data shapes: `String title`, `String description`, `BigDecimal amount` (scale 2).
- Validation: title and amount required, amount >= 0, description optional or non-empty depending on UX.
- API: constructor `Expense(String title, String description, BigDecimal amount)` (throws IllegalArgumentException on invalid input).
- Tests: create expenses with valid and invalid inputs and assert behavior.

---

## Further reading & resources

- Official Java Documentation: https://docs.oracle.com/en/java/
- Effective Java (Joshua Bloch)
- Java Concurrency in Practice (Brian Goetz)
- Baeldung (https://www.baeldung.com)
- Oracle Java Tutorials (https://docs.oracle.com/javase/tutorial/)
- Java Platform, Standard Edition API Specification (https://docs.oracle.com/en/java/javase/21/docs/api/index.html)

---

Notes for repo-specific adjustments
- The README references `org.example.Main` as the entry point and `m`
