package blackjack.model

class Hand(val cards: MutableList<Card>) {
    fun isBust(): Boolean = cards.sumOf { it.number.value } > THRESHOLD_BUST

    companion object {
        const val THRESHOLD_BUST = 21
    }
}
