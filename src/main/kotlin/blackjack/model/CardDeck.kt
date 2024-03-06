package blackjack.model

class CardDeck {
    private val selectedCards: MutableSet<Card> = mutableSetOf()

    fun pick(): Card {
        val shape = Shape.entries.shuffled()[0]
        val cardValue = CardValue.entries.shuffled()[0]
        val card = Card(shape.title, cardValue.title, cardValue.value)

        return if (isAvailable(card)) {
            card
        } else {
            pick()
        }
    }

    private fun isAvailable(card: Card): Boolean {
        return card !in selectedCards
    }
}
