package blackjack.model

class PlayerCards(private val deck: Deck) {
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
        return cards.sumOf { it.cardNumber.score }
    }

}
