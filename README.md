# Shopping Cart Showcase - Strategy Design Pattern

## Overview

This project demonstrates the evolution of a **Shopping Cart** and its integration with various **payment methods** while showcasing the **Strategy Design Pattern**. It starts by exploring naive solutions like monolithic designs, transitions into structured approaches using **inheritance** and **interfaces**, and finally arrives at a scalable and flexible solution through the **Strategy Pattern**.

---

## Key Concepts

### 1. **Naive Solution**
This represents the simplest implementation where all logic is consolidated in one place, which might seem convenient initially. However, as the number of payment methods increases, the design becomes harder to maintain and scale.

#### Problems Highlighted:
- **Violation of Single Responsibility Principle (SRP)**: The [`checkout`](src/naive/Problems.kt#L19) method handles multiple responsibilities (e.g., payment logic, condition checks).
- **Violation of Open/Closed Principle (OCP)**: Adding a new payment type requires modifying existing code.
- **Tight Coupling**: Payment logic is tightly coupled to the Shopping Cart, making testing or reusing code elsewhere difficult.
- **Scaling Issues**: Each additional payment method further increases the complexity of the [`checkout`](src/naive/Problems.kt#L19) method.

#### Example:
The [`ShoppingCartSinglePaymentMethod`](src/naive/ShoppingCartSinglePaymentMethod.kt) and [`ShoppingCartTwoPaymentMethod`](src/naive/ShoppingCartTwoPaymentMethod.kt) demonstrate these problems.

---

### 2. **Using Inheritance**
To address some of the problems in the naive solution, inheritance is introduced. A base class is created for payment methods, and child classes override payment logic.

#### Advantages:
- Reduces code duplication for shared behaviors through the base class.
- Payment-specific logic can be encapsulated in child classes.

#### Problems Highlighted:
- **Inheritance Misuse**:
  - Logic that doesn’t apply to all payment methods is still inherited needlessly (violates Interface Segregation Principle (ISP)).
  - Changes to the base class may undesirably impact all child classes (violates Liskov Substitution Principle (LSP)).
- **Testing Challenges**: You can’t effectively test isolated logic in child classes without dependency on parent logic.

#### Example:
The [`ShoppingCartWithInheritance`](src/inheritance/ShoppingCartWithInheritance.kt) and [`ShoppingCartWithInheritanceAndSharedLogic`](src/inheritance/`ShoppingCartWithInheritanceAndSharedLogic.kt) showcase inheritance but highlight the accompanying issues.

---

### 3. **Using Interfaces**
Interfaces are introduced to decouple behaviors like **tracking**, **receipt generation**, and **payment processing**. Each interface focuses on a single responsibility, ensuring better modularity.

#### Advantages:
- **ISP Compliance**: Classes implement only the behaviors relevant to that type (e.g., receipt-related classes don’t implement tracking).
- **Reusable Behaviors**: Common logic (e.g., tracking) can be shared across multiple payment methods.
- **Reduced Coupling**: Payments and behaviors are broken into discrete components, promoting better code structure.

#### Problems Highlighted:
- While introducing interfaces solves many issues, shared logic may still lead to some duplication of default behavior.
- Maintenance complexities may increase as the number of interfaces grows.

#### Example:
The [`ShoppingCartWithInterfaces`](src/interfaces/ShoppingCartWithInterfaces.kt) demonstrates this approach but also hints at its limitations.

---

### 4. **Strategy Design Pattern**
The **Strategy Pattern** is implemented as the final and most robust solution. It encapsulates algorithms (here, payment methods, receipt generation, and tracking) into separate interchangeable entities.

#### Advantages:
- **Open/Closed Principle**: New payment types, tracking mechanisms, or receipt logic can be added without modifying existing code.
- **Behavioral Reusability**: Shared logic like tracking or showing receipts is reused across multiple strategies without duplication.
- **Runtime Strategy Selection**: Behaviors (e.g., which payment, tracking, or receipt strategy to use) are dynamically chosen at runtime.
- **Clarity and Isolation**: Each class has one focus (e.g., payment logic, tracking, or receipt display), improving clarity and making testing easier.

#### Example:
The [`ShoppingCartWithStrategyPattern`](src/pattern/ShoppingCartWithStrategyPattern.kt) implements this pattern and highlights its benefits.

---

## Project Structure

### Packages:
1. **naive**
   - Contains naive implementations, e.g., [`ShoppingCartSinglePaymentMethod`](src/naive/ShoppingCartSinglePaymentMethod.kt) and [`ShoppingCartTwoPaymentMethod`](src/naive/ShoppingCartTwoPaymentMethod.kt).
   - Highlights problems with monolithic design and tightly coupled logic.

2. **inheritance**
   - Focuses on inheritance-based solutions.
   - Classes like [`BasePayment`](src/inheritance/ShoppingCartWithInheritance.kt#L3), [`PayPalPayment`](src/inheritance/ShoppingCartWithInheritance.kt#L9), and [`ShoppingCartWithInheritance`](src/inheritance/ShoppingCartWithInheritance.kt) demonstrate issues related to misuse of inheritance.

3. **interfaces**
   - Uses interfaces to better separate concerns, as showcased in [`Trackable`](src/interfaces/ShoppingCartWithInterfaces.kt#L9), [`Receiptable`](src/interfaces/ShoppingCartWithInterfaces.kt#L15), and their implementations.
   - Highlights improvements and remaining challenges.

4. **pattern**
   - Illustrates the Strategy Pattern.
   - Includes strategies for **Payment**, **Tracking**, and **Receipt** behaviors.
   - Demonstrates runtime flexibility and the benefits of modularity, as shown in [`CreditCardPaymentStrategy`](src/pattern/ShoppingCartWithStrategyPattern.kt#L15), [`DefaultTracking`](src/pattern/ShoppingCartWithStrategyPattern.kt#L27), and [`SuccessReceipt`](src/pattern/ShoppingCartWithStrategyPattern.kt#L39).

---

## Benefits of the Strategy Pattern in This Project

1. **Flexibility**: New payment methods or behaviors are easily introduced as new strategies without altering existing logic.
2. **Code Reuse**: Shared concerns like receipt generation or payment tracking are implemented once and reused across payments.
3. **Maintainability**: Code changes in one strategy don't affect others.
4. **Scalability**: Supports any number of payment methods, tracking systems, or receipt types by simply adding new strategies.

---

## Example Workflow (Strategy Pattern):

1. Customer adds items to the shopping cart.
2. The total is calculated.
3. The customer selects payment, tracking, and receipt strategies.
4. The strategies are applied to process the checkout dynamically.

---

## Key Takeaways

- Start simple but recognize the limitations of monolithic or tightly bound designs early.
- Move toward modular, behavior-focused architecture using **interfaces**.
- For long-term scalability and maintainability, adopt **design patterns** like the Strategy Pattern, which cleanly separates concerns and allows runtime flexibility.

This project is an excellent hands-on example of design evolution, showcasing best practices and the importance of software design principles in building scalable and maintainable applications.
