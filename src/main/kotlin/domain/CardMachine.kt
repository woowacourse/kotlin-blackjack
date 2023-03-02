package domain

class CardMachine {
    private val cards: MutableList<Card> = mutableListOf()

    init {
        addCards()
        cards.shuffle()
    }

    fun getNewCard() = cards.removeFirst()

    fun getCardPairs(count: Int): List<List<Card>> {
        return List(count) { getCardPair() }
    }

    fun getCardPair(): List<Card> {
        val pickedCard = cards.take(2)
        cards.removeAll(pickedCard)
        return pickedCard
    }

    private fun addCards() {
        for (shape in Card.Shape.values()) {
            addCardValue(shape)
        }
    }

    private fun addCardValue(shape: Card.Shape) {
        Card.Value.values().map { value ->
            cards.add(Card(shape, value))
        }
    }
}
