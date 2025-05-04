package naive

class ShoppingCartSinglePaymentMethod {
    private val items = mutableListOf<Pair<String, Double>>()

    fun addItem(name: String, price: Double) {
        items.add(name to price)
    }

    private fun calculateTotal(): Double = items.sumOf { it.second }

    fun checkout(paymentType: String, userInfo: Map<String, String>) {
        val amount = calculateTotal()

        when (paymentType.lowercase()) {
            "paypal" -> {
                val email = userInfo["email"] ?: throw IllegalArgumentException("Email required for PayPal")
                println("Paid $$amount using PayPal ($email)")
            }
            else -> {
                println("Unsupported payment method: $paymentType")
            }
        }
    }
}