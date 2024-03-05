package blackjack.model

class Card(private val denomination: String, private val suite: String) {
    fun getValue(): Int {
        if (denomination == "A") return 1
        if (denomination == "K" || denomination == "Q" || denomination == "J") {
            return 10
        }
        return denomination.toInt()
    }

    fun isAce(): Boolean {
        return denomination == "A"
    }

    companion object {
        const val ADDITIONAL_ACE_VALUE = 10
    }
}
