package domain


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
        return cards.removeFirstOrNull() ?: throw IllegalArgumentException("남은 카드가 없습니다.")
    }
}
