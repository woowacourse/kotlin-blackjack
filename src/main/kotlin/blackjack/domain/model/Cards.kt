package blackjack.domain.model

class Cards(initCards: List<Card> = deckCards.shuffled()) {
    private val cards: MutableList<Card> = initCards.toMutableList()

    fun draw(): Card {
        return cards.removeFirst()
    }

    companion object {
        private val deckCards = Suit.entries.flatMap { suit -> makeSuitCards(suit) }

        private fun makeSuitCards(suit: Suit): List<Card> {
            return Rank.entries.map { rank ->
                Card(suit, rank)
            }
        }
    }
}
