package domain


class Deck(val cards: MutableList<Card> = mutableListOf()) {
    init {
        if (cards.isEmpty()) {
            for (symbol in enumValues<Symbol>()) {
                enumValues<Value>().forEach { cards.add(Card(symbol, it)) }
            }
            cards.shuffle()
        }
    }

    fun draw(): Card {
        val card = cards.component1()
        cards.remove(card)
        return card
    }
}
