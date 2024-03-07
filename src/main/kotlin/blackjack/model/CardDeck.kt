package blackjack.model

object CardDeck {
    private val selectedCards: MutableSet<Card> = mutableSetOf()

    fun pick(): Card {
        val shape = Shape.entries.shuffled().first()
        val cardValue = CardValue.entries.shuffled().first()
        val card = Card(shape.title, cardValue.title, cardValue.value)

        return if (isAvailable(card)) {
            card.also { selectedCards.add(card) }
        } else {
            pick()
        }
    }

    private fun isAvailable(card: Card): Boolean {
        return card !in selectedCards
    }
}
