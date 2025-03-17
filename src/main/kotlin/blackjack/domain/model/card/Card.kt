package blackjack.domain.model.card

data class Card(val suit: Suit, val rank: Rank) {
    fun isAce(): Boolean {
        return rank == Rank.ACE
    }
}
