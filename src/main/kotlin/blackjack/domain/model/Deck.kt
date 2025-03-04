package blackjack.domain.model

data class Deck(val cards: List<Card> = deckCards) {

    companion object {
        private val deckCards = Suit.entries.flatMap { suit -> makeSuitCards(suit) }
        private fun makeSuitCards(suit: Suit): List<Card> {
            return Rank.entries.map { rank ->
                Card(suit, rank)
            }
        }
    }
}