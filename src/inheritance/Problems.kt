package inheritance

/**
 * Violates Interface Segregation: some childes will inherit logic they don't need.
 * Violates Liskov: not all logic of parent applicable to childes.
 * Any change to Base will affect all childes.
 * **/
class CashOnDeliveryPayment(private val name: String) : BasePayment1() {
    override fun pay(amount: Double) {
        super.pay(amount)
        println("Cash on delivery with $$amount for ($name)")
    }
}

class NaiveCashOnDeliveryPayment(private val name: String) : BasePayment1() {
    override fun pay(amount: Double) {
//        super.pay(amount)
        println("Default payment of $$amount")
        println("Cash on delivery with $$amount for ($name)")
        super.trackPayment()
    }
}

// OR add method to check if to show receipt or not and all childes calls it
// OR could add some flags