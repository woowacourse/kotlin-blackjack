package entity

class Cards(val value: MutableList<Card>) {
    fun sumOfNumbers(): Int {
        return value.sumOf { it.cardNumber.numberStrategy(this) }
    }
}
