package blackjack.model

data class Card(val suit: Suit, val rank: Rank) {
    fun isAce() = (rank == Rank.ACE)

    companion object {
        private val deck = Deck.create()

        fun from(
            suit: Suit,
            rank: Rank,
        ): Card {
            val card =
                deck.cards.first {
                    it.suit == suit && it.rank == rank
                }
            return card
        }
    }
}
