package blackjack.model

class HandCards(private val deck: Deck) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = _cards.toList()

    init {
        _cards.addAll(deck.draw(2))
    }

    fun add() {
        _cards.addAll(deck.draw(1))
    }

    fun calculateCardScore(): Int {
        val baseScore = cards.sumOf { it.cardNumber.score }
        val hasAce = cards.any { it.cardNumber == CardNumber.ACE }
        return if (hasAce && baseScore + 10 <= 21) baseScore + 10 else baseScore
    }
}
