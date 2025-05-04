package inheritance

open class BasePayment {
    open fun pay(amount: Double) {
        println("Default payment of $$amount")
    }
}

class PayPalPayment(private val email: String) : BasePayment() {
    override fun pay(amount: Double) {
        println("Paid $$amount using PayPal ($email)")
    }
}

class CreditCardPayment(
    private val name: String,
    private val number: String
) : BasePayment() {
    override fun pay(amount: Double) {
        println("Paid $$amount using Credit Card ($name)")
    }
}

class ShoppingCartWithInheritance {
    private val items = mutableListOf<Pair<String, Double>>()

    fun addItem(name: String, price: Double) {
        items.add(name to price)
    }

    private fun calculateTotal(): Double = items.sumOf { it.second }

    fun checkout(payment: BasePayment) {
        val amount = calculateTotal()
        payment.pay(amount)
    }
}