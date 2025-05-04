package naive

/**
 * Violates Single Responsibility Principle.
 * Violates Open/Closed Principle: Adding a new payment method requires modifying.
 * payment logic is not reusable elsewhere.
 * Tight Coupling/Difficult to Test: You can’t test individual payment logic in isolation.
 * Scaling Becomes a Nightmare: The more payment types you add, the more unreadable the checkout() method becomes.
 **/
class NaiveProblems {
    private val items = mutableListOf<Pair<String, Double>>()

    fun addItem(name: String, price: Double) {
        items.add(name to price)
    }

    private fun calculateTotal(): Double = items.sumOf { it.second }

    fun checkout(paymentType: String, userInfo: Map<String, String>) {
        val amount = calculateTotal()

        when (paymentType) {
            "paypal" -> {  }
            "credit_card" -> {  }
            "google_pay" -> {  }
            "apple_pay" -> {  }
            "cash" -> {  }
        }
    }
}