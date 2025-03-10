package blackjack.model

abstract class Participant(
    private val scoreCalculator: ScoreCalculator,
) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards.toList()

    fun score(): Int = scoreCalculator.score(cards)

    fun isBust(): Boolean = scoreCalculator.isBust(cards)

    fun draw(cardDeck: CardDeck) {
        val count = if (cards.isEmpty()) INITIAL_DRAW_COUNT else DEFAULT_DRAW_COUNT
        addAll(cardDeck.draw(count))
    }

    fun addAll(cards: List<Card>) {
        _cards.addAll(cards)
    }

    companion object {
        private const val INITIAL_DRAW_COUNT = 2
        private const val DEFAULT_DRAW_COUNT = 1
    }
}
