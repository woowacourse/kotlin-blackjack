package blackjack.model

abstract class Participant(
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
}
