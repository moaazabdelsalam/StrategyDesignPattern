package pattern

interface PaymentStrategy {
    fun pay(amount: Double)
}

interface TrackingStrategy {
    fun trackPayment()
}

interface ReceiptStrategy {
    fun showReceipt(amount: Double)
}

class CreditCardPaymentStrategy : PaymentStrategy {
    override fun pay(amount: Double) {
        println("Paying $$amount with credit card")
    }
}

class CashOnDeliveryPaymentStrategy(private val name: String) : PaymentStrategy {
    override fun pay(amount: Double) {
        println("Cash on delivery with $$amount for ($name)")
    }
}

class DefaultTracking : TrackingStrategy {
    override fun trackPayment() {
        println("send to analytics")
    }
}

class AlternateTracking : TrackingStrategy {
    override fun trackPayment() {
        println("send to another analytics")
    }
}

class SuccessReceipt : ReceiptStrategy {
    override fun showReceipt(amount: Double) {
        println("Success payment of $$amount")
    }
}

fun processPayment(
    amount: Double,
    paymentStrategy: PaymentStrategy,
    trackingStrategy: TrackingStrategy? = null,
    receiptStrategy: ReceiptStrategy? = null
) {
    paymentStrategy.pay(amount)
    trackingStrategy?.trackPayment()
    receiptStrategy?.showReceipt(amount)
}

fun main() {
    val amount = 150.0

    processPayment(
        amount = amount,
        paymentStrategy = CreditCardPaymentStrategy(),
        trackingStrategy = DefaultTracking(),
        receiptStrategy = SuccessReceipt()
    )

    println()

    processPayment(
        amount = amount,
        paymentStrategy = CashOnDeliveryPaymentStrategy(name = "Moaaz"),
        trackingStrategy = AlternateTracking()
    )
}

/**
 * Open/Closed Principle: You can add new payment types, tracking, or receipt logic without modifying existing classes.
 * You can dynamically choose or switch behavior (e.g., tracking, receipt generation) at runtime.
 * Code Reuse: Shared behaviors like tracking or receipt display are reused across multiple payment types without duplication.
 * Each strategy class focuses on a single responsibility (e.g., just payment, just tracking), enhancing clarity and maintainability.
 * Each strategy can be tested in isolation.
 * **/