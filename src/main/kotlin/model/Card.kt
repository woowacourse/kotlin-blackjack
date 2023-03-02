package model

data class Card(val rank: Rank, val suit: Suit) {
    override fun toString(): String {
        if (rank.description != null) return rank.description + suit.description
        return rank.getScore().toString() + suit.description
    }
}
