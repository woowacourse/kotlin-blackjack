package blackjack.domain.model

data class Cards(private val cards: List<Card> = deckCards.shuffled()) {

    fun draw(): Card {
        return cards.first()
    }

    fun remove(card: Card) = this.copy(cards = cards - card)

    companion object {
        private val deckCards = Suit.entries.flatMap { suit -> makeSuitCards(suit) }
        private fun makeSuitCards(suit: Suit): List<Card> {
            return Rank.entries.map { rank ->
                Card(suit, rank)
            }
        }
    }
}