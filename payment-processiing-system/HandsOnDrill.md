# OOP + SOLID Hands-On Drill Workbook

This workbook is designed to help you **master OOP principles and SOLID design** in Java through a hands-on, interview-focused project.

---

## 1️⃣ Simple Interface vs Abstract Class

**Goal:** See difference between "contract" and "shared behavior"

* **Create:**

    * `PaymentProcessor` interface → `processPayment(double)`
    * `CardPaymentProcessor` → implements interface
* **Variation:**

    * Refactor to abstract class `AbstractPaymentProcessor` with empty `processPayment()` and override in Card
* **Think:** When do you need shared behavior? When not?

---

## 2️⃣ Abstract Class with Shared State

**Goal:** Learn constructors + shared fields

* **Create:**

    * `AbstractPaymentProcessor` with `protected String paymentMethodName;`
    * Constructor sets `paymentMethodName`
    * `CardPaymentProcessor` passes "Card"
* **Variation:**

    * Add `UPIPaymentProcessor`
    * Both can access `paymentMethodName`

---

## 3️⃣ Interfaces as Capabilities

**Goal:** Add optional behavior

* **Create:**

    * `Refundable` interface → `refund(double)`
    * CardPaymentProcessor implements Refundable
    * UpiPaymentProcessor does not
* **Variation:**

    * Inject into CheckoutService and call refund only if instanceof Refundable

---

## 4️⃣ Combine Abstract + Interface

**Goal:** Learn when both are needed

* **Create:**

    * AbstractPaymentProcessor → shared `processPayment` logic
    * Refundable → optional refund
    * CardPaymentProcessor extends abstract + implements Refundable
    * UpiPaymentProcessor extends abstract

---

## 5️⃣ Template Method Pattern

**Goal:** Force shared algorithm

* **Create:**

    * AbstractPaymentProcessor.processPayment() → validate → log → doProcess
    * Concrete classes implement `doProcess`
* **Variation:**

    * Add `beforeProcess()` hook in abstract class, override optionally in UPI

---

## 6️⃣ Polymorphism Drill

**Goal:** Runtime behavior understanding

* **Create:**

    * List<PaymentProcessor> processors = new ArrayList<>();
    * Add Card + UPI
    * Loop: `processor.processPayment(amount)`
* **Think:** CheckoutService doesn’t care which object runs

---

## 7️⃣ Dependency Injection

**Goal:** SOLID principle: Depend on abstraction

* **Create:**

    * CheckoutService takes PaymentProcessor in constructor
    * Swap Card → UPI → mock
* **Variation:**

    * Write a simple MockProcessor that prints “Test”

---

## 8️⃣ Single Responsibility Drill

**Goal:** Each class does one thing

* **Create:**

    * PaymentProcessor → handles payments only
    * CheckoutService → handles checkout only
* **Variation:**

    * Move logging to a separate `Logger` class, inject it
    * Abstract class uses logger

---

## 9️⃣ Open/Closed Drill

**Goal:** System open for extension, closed for modification

* **Create:**

    * CheckoutService works for any PaymentProcessor
    * Add new `GiftCardPaymentProcessor` → no changes to CheckoutService

---

## 🔟 Liskov Substitution Principle

**Goal:** Ensure derived class can replace base class

* **Create:**

    * AbstractPaymentProcessor → processPayment
    * UpiPaymentProcessor overrides processPayment correctly
    * Test: replace Card with UPI in main → behavior works

---

## 1️⃣1️⃣ Interface Segregation Drill

**Goal:** Optional behaviors without forcing implementation

* **Create:**

    * Refundable → refund(amount)
    * Auditable → audit()
    * Only Card implements Refundable
    * Only UPI implements Auditable

---

## 1️⃣2️⃣ Dependency Inversion Drill

**Goal:** High-level modules depend on abstraction

* **Create:**

    * CheckoutService → PaymentProcessor (interface)
    * No references to Card/UPI inside service
    * Swap implementations easily

---

## 1️⃣3️⃣ Shared Constructor Logic

* AbstractPaymentProcessor → constructor sets `paymentMethodName`
* Card passes “Card”
* UPI passes “UPI”
* Variation: Add base fee field, inherited by all subclasses

---

## 1️⃣4️⃣ Optional Hooks

* AbstractPaymentProcessor → `beforeProcess()` optional hook
* Card overrides hook
* UPI uses default empty

---

## 1️⃣5️⃣ Multiple Capability Interfaces

* Refundable
* Auditable
* Loggable
* Concrete class implements multiple
* CheckoutService can work with subset

---

## 1️⃣6️⃣ Refactor to Avoid Design Smell

* Add method in abstract class that only applies to UPI → notice violation
* Refactor that method to interface → fix

---

## 1️⃣7️⃣ Testing with Mocks

* Create MockPaymentProcessor → prints “mocked”
* Inject into CheckoutService → confirm polymorphism works

---

## 1️⃣8️⃣ Swapping Implementation

* Main: pass Card → Checkout → works
* Swap UPI → Checkout → works without any changes
* Highlight OCP + DIP

---

## 1️⃣9️⃣ Logging Externalized

* AbstractPaymentProcessor uses Logger interface
* Inject logger → shows DI + SRP

---

## 2️⃣0️⃣ End-to-End Interview Scenario

* AbstractPaymentProcessor → shared logic
* Refundable → optional
* Card/UPI processors
* CheckoutService → uses interface
* Main → swaps processors
* Run, observe memory & dynamic dispatch
* Goal: Your brain automatically decides interface vs abstract class usage
