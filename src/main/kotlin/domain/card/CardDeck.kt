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

    fun drawCards(count: Int): List<Card> {
        val pickedCards = mutableListOf<Card>()
        repeat(count) {
            pickedCards.add(deck.removeFirst())
        }
        return pickedCards
    }

    fun contains(card: Card): Boolean {
        return deck.contains(card)
    }

    fun size(): Int {
        return deck.size
    }
}