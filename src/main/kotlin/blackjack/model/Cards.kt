package blackjack.model

data class Cards(val cards: List<Card>) {
    companion object {
        val DECK: Cards = createDeck()

        @JvmStatic
        fun createDeck(): Cards {
            val suits = Suit.entries
            val ranks = Rank.entries
            val deck: List<Card> =
                suits.flatMap { suit ->
                    ranks.map { rank -> Card(suit, rank) }
                }
            return Cards(deck)
        }
    }
}
