package interfaces

open class BasePayment {
    open fun pay(amount: Double) {
        println("Default payment of $$amount")
    }
}

interface Trackable {
    fun trackPayment() {
        println("send to analytics")
    }
}

interface Receiptable {
    fun showReceipt(amount: Double)
}

class CreditCardPayment() : BasePayment(), Trackable, Receiptable {
    override fun pay(amount: Double) {
        super.pay(amount)
        println("Paying $$amount with credit card")
        trackPayment()
        showReceipt(amount)
    }

    override fun showReceipt(amount: Double) {
        println("Success payment of $$amount")
    }
}

class CashOnDeliveryPayment(private val name: String) : BasePayment(), Trackable {
    override fun pay(amount: Double) {
        super.pay(amount)
        println("Cash on delivery with $$amount for ($name)")
        trackPayment()
    }

    override fun trackPayment() {
        println("send to another analytics")
    }
}

/**
 * May include default behavior that’s irrelevant from parent class.
 * What if we need to change tracking? Will go on each implementation and modify it?
 * **/