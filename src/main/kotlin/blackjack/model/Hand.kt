package blackjack.model

class Hand(
    private val scoreCalculator: ScoreCalculator,
) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards.toList()

    fun addAll(cards: List<Card>) {
        _cards.addAll(cards)
    }

    fun score(): Int = scoreCalculator.score(cards)

    fun isBust(): Boolean = scoreCalculator.isBust(score())
}
