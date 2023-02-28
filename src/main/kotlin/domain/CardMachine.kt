package domain

class CardMachine {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards.toList()

    init {
        addCards()
        _cards.shuffled()
    }

    fun getCardPair(): List<Card> {
        val pickedCard = _cards.take(2)
        _cards.removeAll(pickedCard)
        return pickedCard
    }

    private fun addCards() {
        for (shape in Card.Shape.values()) {
            addCardValue(shape)
        }
    }

    private fun addCardValue(shape: Card.Shape) {
        Card.Value.values().map { value ->
            _cards.add(Card(shape, value))
        }
    }
}
