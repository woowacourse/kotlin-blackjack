package blackjack.model

class Hand(cards: List<Card>) {
    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    fun isBust(): Boolean = cards.sumOf { it.number.value } > THRESHOLD_BUST

    fun addCard(card: Card) {
        _cards.add(card)
    }

    companion object {
        const val THRESHOLD_BUST = 21
    }
}
