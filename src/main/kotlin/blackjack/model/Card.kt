package blackjack.model

class Card(
    val shape: CardShape,
    val denomination: String,
) {
    fun changeDenominationToInt(): Int {
        if (denomination.toIntOrNull() in 2..10) {
            return denomination.toInt()
        }
        if (denomination == "Q" || denomination == "J" || denomination == "K") {
            return 10
        }
        return 0
    }
}
