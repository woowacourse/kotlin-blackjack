package blackjack.model.card

data class Card(val suit: Suit, val rank: Rank) {
    fun isAce() = (rank == Rank.ACE)
}
