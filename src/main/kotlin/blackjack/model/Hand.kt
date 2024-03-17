package blackjack.model

class Hand(cards: Set<Card> = emptySet()) {
    private val _cards: MutableSet<Card> = cards.toMutableSet()
    val cards: Set<Card>
        get() = _cards.toSet()

    var score: Score = Score(_cards)
        private set

    fun drawCard(card: Card) {
        _cards.add(card)
        updateScore()
    }

    private fun updateScore() {
        score = Score(_cards)
    }
}
