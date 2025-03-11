package blackjack.model

abstract class Participant(
    val name: String,
    private val scoreCalculator: ScoreCalculator,
) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards.toList()

    abstract fun recieveCards(getCards: (Int) -> List<Card>): Boolean

    abstract fun showInitialCards(): List<Card>

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
