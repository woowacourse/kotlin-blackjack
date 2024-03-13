package blackjack.model

data class Card(val suit: Suit, val rank: Rank) {
    fun isAce() = (rank == Rank.ACE)

    companion object {
        private val cardsCache: MutableList<Card> = mutableListOf()

        fun from(
            suit: Suit,
            rank: Rank,
        ): Card {
            val card = Card(suit, rank)
            if (!cardsCache.contains(card)) {
                cardsCache.add(card)
            }
            return cardsCache.first { it == card }
        }
    }
}
