package blackjack.model

class Card(private val denomination: String, private val suite: String) {
    fun getValue(): Int {
        if (denomination == "K" || denomination == "Q" || denomination == "J") {
            return 10
        }
        return denomination.toInt()
    }
}
