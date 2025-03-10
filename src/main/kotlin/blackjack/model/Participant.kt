package blackjack.model

abstract class Participant(
    private val blackjackCalculator: BlackjackCalculator,
) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards.toList()

    fun score(): Int = blackjackCalculator.score(cards)

    fun isBust(): Boolean = blackjackCalculator.isBust(cards)

    fun draw(cardDeck: CardDeck) {
        val count = if (cards.isEmpty()) INITIAL_DRAW_COUNT else DEFAULT_DRAW_COUNT
        addAll(cardDeck.draw(count))
    }

    private fun addAll(cards: List<Card>) {
        _cards.addAll(cards)
    }

    companion object {
        private const val INITIAL_DRAW_COUNT = 2
        private const val DEFAULT_DRAW_COUNT = 1
    }
}
