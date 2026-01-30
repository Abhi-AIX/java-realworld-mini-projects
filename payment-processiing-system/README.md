# payment-processing-system

Checklist
- [x] Add `README.md` to repo root
- [ ] Verify class descriptions match code in `src/main/java/org/example`
- [ ] Verify PowerShell commands work on your machine

Project summary

A small Java Maven demo that models a simple payment processing flow and refund capability. Built as a learning exercise to master **OOP principles** and **SOLID principles**, it demonstrates clean object-oriented design, the Template Method and Strategy patterns, constructor injection for testability, and small, focused classes to represent payment processors and a checkout service.

---

## OOP Principles Explained (with project examples)

### 1. Encapsulation
Hide internal state and expose behavior through methods. Keep fields private and provide controlled access.

**In this project:**
- `CheckoutService` keeps its `paymentProcessor` field private; callers interact only via `checkout(amount)`.
- Processors encapsulate payment logic; clients don't need to know internal steps.

### 2. Abstraction
Expose only essential details; hide complex implementation behind simple interfaces.

**In this project:**
- `PaymentProcessor` interface abstracts what a processor does (`processPayment`) without revealing how.
- `CheckoutService` depends on the abstraction, not on concrete processors.

### 3. Inheritance
Create new classes based on existing ones to reuse and extend behavior.

**In this project:**
- `CardPaymentProcessor` and `UpiPaymentProcessor` extend `AbstractPaymentProcessor`, inheriting validation and logging logic while providing their own `executePayment` implementations.

### 4. Polymorphism
Use a single interface to represent different underlying forms (classes). Code written against a base type works with any subtype.

**In this project:**
- `CheckoutService` works with any `PaymentProcessor` implementation. At runtime, you can inject `CardPaymentProcessor` or `UpiPaymentProcessor` without changing service code.
- In `Main`, a `PaymentProcessor` reference is checked with `instanceof Refundable` to call refund only when supported.

---

## SOLID Principles Explained (with project examples)

### S — Single Responsibility Principle (SRP)
A class should have only one reason to change — one job.

**In this project:**
- `CheckoutService` handles checkout orchestration only.
- `CardPaymentProcessor` handles card payment execution only.
- `AbstractPaymentProcessor` handles the shared template workflow only.

### O — Open/Closed Principle (OCP)
Classes should be open for extension but closed for modification. Add new behavior by extending, not by changing existing code.

**In this project:**
- To add a new payment method (e.g., PayPal), create a new class extending `AbstractPaymentProcessor`. No changes needed in `CheckoutService` or existing processors.

### L — Liskov Substitution Principle (LSP)
Subtypes must be substitutable for their base types without breaking the program.

**In this project:**
- `CardPaymentProcessor` and `UpiPaymentProcessor` can replace `PaymentProcessor` anywhere it's expected. `CheckoutService` works correctly with any processor.

### I — Interface Segregation Principle (ISP)
Clients should not be forced to depend on interfaces they don't use. Prefer small, focused interfaces.

**In this project:**
- `PaymentProcessor` declares only `processPayment`.
- `Refundable` is a separate interface for refund capability.
- A processor that doesn't support refunds simply doesn't implement `Refundable`; it isn't forced to provide an unused method.

### D — Dependency Inversion Principle (DIP)
High-level modules should not depend on low-level modules. Both should depend on abstractions.

**In this project:**
- `CheckoutService` (high-level) depends on the `PaymentProcessor` interface (abstraction), not on `CardPaymentProcessor` or `UpiPaymentProcessor` (low-level).
- Concrete processors are injected at runtime, making the service flexible and testable.

---

High-level architecture & design decisions

- Layering: a service layer (`CheckoutService`) orchestrates payments using a `PaymentProcessor` abstraction.
- `PaymentProcessor` is an interface defining the contract for processing payments.
- `AbstractPaymentProcessor` provides a template workflow (validation, logging, execution) — Template Method pattern.
- Concrete processors (`CardPaymentProcessor`, `UpiPaymentProcessor`) implement transport-specific logic.
- `Refundable` is a marker/optional interface for processors that support refunds (Interface Segregation).
- `CheckoutService` receives a `PaymentProcessor` via constructor injection (simple DI / Strategy pattern) to allow swapping implementations and easy testing.

Source files (short descriptions)

- `PaymentProcessor` (interface)
  - Declares `processPayment(double amount)` — the contract for processors.

- `Refundable` (interface)
  - Declares `processRefund(double amount)` for processors that support refunds.

- `AbstractPaymentProcessor` (abstract class)
  - Implements shared workflow: validate input, log actions, and delegates concrete work to `executePayment(double)` (protected abstract). Implements a Template Method pattern to keep behavior consistent across processors.

- `CardPaymentProcessor` (class)
  - Concrete implementation that executes card payments and supports refunds via `Refundable`.

- `UpiPaymentProcessor` (class)
  - Concrete implementation for UPI payments and supports refunds via `Refundable`.

- `CheckoutService` (class)
  - High-level service that depends on `PaymentProcessor` and delegates `checkout(amount)` calls to it. Shows constructor injection and the Strategy pattern.

- `Main` (class)
  - Simple runner that wires processors and `CheckoutService` and demonstrates payment and refund flows.

Prerequisites

- Java JDK 11+ (the project sets source/target to 21 in pom.xml; ensure you have a compatible JDK installed)
- Apache Maven 3.6+
- Windows PowerShell (these examples target PowerShell)

Build & run (Windows PowerShell)

Compile only:

```powershell
mvn -q compile
```

Build package (skip tests):

```powershell
mvn -q -DskipTests package
```

Run the app from compiled classes (no packaging required):

```powershell
java -cp target/classes org.example.Main
```

You can chain commands in PowerShell using `;`, for example:

```powershell
mvn -q -DskipTests package; java -cp target/classes org.example.Main
```

Testing

- Current project contains no unit tests in `src/test/java`.
- Recommended additions:
  - Add JUnit 5 and Mockito to test `CheckoutService` by mocking `PaymentProcessor`.
  - Add tests for `AbstractPaymentProcessor` by creating a small concrete test subclass to verify validation and template flow.
- Run tests (after adding them):

```powershell
mvn -q test
```

Extension ideas

- Replace System.out prints with SLF4J + Logback for configurable logging.
- Add more processors (PayPal, BankTransfer) and a factory or service loader to select processors at runtime.
- Add integration tests, CI pipeline, and static analysis (Checkstyle/SpotBugs).
- Add proper error handling, retries, and idempotency for networked processors.

Troubleshooting

- "JAVA_HOME not set" or incorrect Java version: ensure JAVA_HOME points to a JDK and `java -version` shows a compatible version.
- `mvn` not found: install Maven and add to PATH.
- Class not found when running Main: ensure `mvn compile` succeeded and `target/classes` exists.
- IllegalArgumentException from processor validation: check input amounts; processors expect positive amounts.

Contribution notes

- Small repo: feature branches and PRs welcome.
- Add tests for new behaviors and keep changes small and focused.
- Maintain single responsibility and SOLID principles.

Interview-ready questions (12) with short talking points

1) What OOP principles are demonstrated in this project?
- Encapsulation: classes hide internal state and expose methods. Polymorphism and inheritance: processors extend `AbstractPaymentProcessor` and are used via `PaymentProcessor` reference. Single Responsibility: each class has a focused role.

2) Explain the Template Method pattern and where it appears here.
- `AbstractPaymentProcessor` defines the fixed steps for processing a payment (validate, log, execute) and defers `executePayment` to subclasses, letting them customize the concrete step while keeping a shared workflow.

3) How does Dependency Injection appear in this project and why is it useful?
- `CheckoutService` receives a `PaymentProcessor` via its constructor. This decouples the service from concrete implementations, making the system easier to test and extend.

4) How would you unit test `CheckoutService`?
- Use Mockito to mock `PaymentProcessor`, construct `CheckoutService` with the mock, call `checkout(amount)` and verify `processPayment(amount)` was invoked.

5) How can you test the behavior of `AbstractPaymentProcessor`?
- Create a tiny concrete subclass inside the test that overrides `executePayment` to record calls. Test that `processPayment` validates input and calls `executePayment` for valid amounts and throws for invalid amounts.

6) Why prefer an optional `Refundable` interface instead of adding refund methods to `PaymentProcessor`?
- Interface Segregation: not all processors must support refunds. Keeping refund behavior separate prevents forcing unused methods onto processors that don't implement refunds.

7) What are pros and cons of `System.out.println` vs a logging framework?
- `System.out` is simple but not configurable. Logging frameworks provide levels, appenders, configuration, and better control in production environments.

8) Which Maven commands are commonly used during development and CI?
- `mvn compile`, `mvn test`, `mvn -DskipTests package`, `mvn clean`, and in CI `mvn -B -q clean verify`.

9) How would you add integration tests for end-to-end behavior?
- Construct real concrete processors and `CheckoutService` in tests, capture logs or output, and assert expected behavior. For networked processors, use mock servers or test containers.

10) How is the Strategy pattern used here?
- Different `PaymentProcessor` implementations encapsulate different payment algorithms. `CheckoutService` chooses a processor and delegates to it at runtime.

11) How should payment errors be handled in a production-ready system?
- Use typed result objects or exceptions for errors, implement retries and backoff for transient network failures, ensure idempotency, and add monitoring/alerts.

12) What would you change to prepare this for production?
- Add logging framework, configuration management, robust error handling, metrics/tracing, secure handling of payment information, dependency injection framework (e.g., Spring), integration tests against sandbox gateways, and CI/CD with quality gates.

