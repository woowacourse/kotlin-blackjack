package blackjack

class Deck {
    companion object {
        private val CARDS = generateCards()

        fun poll(): Card {
            return CARDS.removeFirst()
        }

        private fun generateCards(): MutableList<Card> {
            val cards = mutableListOf<Card>()

            Shape.entries.forEach { shape ->
                Number.entries.forEach { number ->
                    cards.add(Card(shape, number))
                }
            }
            return cards.shuffled().toMutableList()
        }
    }
}
