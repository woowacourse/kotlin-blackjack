package blackjack.model

abstract class Participant(
    val name: String,
    private val scoreCalculator: ScoreCalculator,
) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards.toList()

    abstract fun showInitialCards(): List<Card>

    abstract fun isDrawable(): Boolean

    fun recieveCards(getCards: (Int) -> List<Card>) {
        val count = if (cards.isEmpty()) INITIAL_DRAW_COUNT else DEFAULT_DRAW_COUNT
        addAll(getCards(count))
    }

    fun score(): Int = scoreCalculator.score(cards)

    fun addAll(cards: List<Card>) {
        _cards.addAll(cards)
    }

    fun isBust(): Boolean = scoreCalculator.isBust(cards)

    companion object {
        const val INITIAL_DRAW_COUNT = 2
        const val DEFAULT_DRAW_COUNT = 1
    }
}
