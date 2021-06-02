package domain.card

class Deck(private val cards: MutableList<Card> = mutableListOf()) : List<Card> by cards {
    init {
        if (cards.isEmpty()) {
            for (symbol in enumValues<Symbol>()) {
                enumValues<Value>().forEach { cards.add(Card(symbol, it)) }
            }
            cards.shuffle()
        }
    }

    fun pop(): Card {
        val card = cards.component1()
        cards.remove(card)
        return card
    }
}
