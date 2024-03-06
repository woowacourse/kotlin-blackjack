package blackjack.model

class Player(val name: String, private val deck: Deck) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = _cards.toList()

    init {
        _cards.addAll(deck.draw(2))
    }

    fun addCard(isAdd: Boolean) {
        if (isAdd) {
            _cards.addAll(deck.draw(1))
        }
    }

    fun calculateCardScore(): Int {
        return _cards.sumOf { it.cardNumber.score }
    }
}
