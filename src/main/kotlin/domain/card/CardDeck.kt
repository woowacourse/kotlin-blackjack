package domain.card

class CardDeck(private val deck: MutableList<Card> = mutableListOf()) {

    init {
        for (shape in ShapeType.values()) {
            for (number in NumberType.values()) {
                deck.add(Card(number, shape))
            }
        }
        deck.shuffle()
    }

    fun drawCards(count: Int = 1): List<Card> {
        return (1..count).map { deck.removeFirst() }
    }

    fun contains(card: Card) = deck.contains(card)
}
