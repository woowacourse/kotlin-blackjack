package blackjack.model

object TestUtils {
    fun Card(value: Int): Card {
        return Card(CardNumber.entries.find { it.value == value }!!, CardShape.HEART)
    }

    fun Hand(vararg cards: Card): Hand {
        return Hand(cards.toList())
    }

    fun createCardDeckFrom(vararg numbers: Int): CardDeck = CardDeck(numbers.map { Card(it) })
}
