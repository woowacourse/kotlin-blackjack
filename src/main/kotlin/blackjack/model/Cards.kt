package blackjack.model

class Cards(val cards: MutableList<Card>) {
    fun isBust(): Boolean = cards.sumOf { it.number.value } > 21

    companion object {
        const val THRESHOLD_BUST = 21
    }
}
