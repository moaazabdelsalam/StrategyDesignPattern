package inheritance

open class BasePayment1 {
    open fun pay(amount: Double) {
        println("Default payment of $$amount")
        showReceipt(amount)
        trackPayment()
    }

    private fun showReceipt(amount: Double) {
        println("Success payment of $$amount")
    }

    protected fun trackPayment() {
        println("send to analytics")
    }
}

class PayPalPayment1(private val email: String) : BasePayment1() {
    override fun pay(amount: Double) {
        super.pay(amount)
        println("Paid $$amount using PayPal ($email)")
    }
}

class CreditCardPayment1(
    private val name: String,
    private val number: String
) : BasePayment1() {
    override fun pay(amount: Double) {
        super.pay(amount)
        println("Paid $$amount using Credit Card ($name)")
    }
}

class ShoppingCartWithInheritanceAndPaymentReceipt {
    private val items = mutableListOf<Pair<String, Double>>()

    fun addItem(name: String, price: Double) {
        items.add(name to price)
    }

    private fun calculateTotal(): Double = items.sumOf { it.second }

    fun checkout(payment: BasePayment1) {
        val amount = calculateTotal()
        payment.pay(amount)
    }
}
