package blackjack.model.card

class Card(val denomination: Denomination, val suit: Suit) {
    override fun toString(): String {
        val number =
            when (denomination) {
                Denomination.JACK -> JACK
                Denomination.QUEEN -> QUEEN
                Denomination.KING -> KING
                Denomination.ACE -> ACE
                else -> denomination.score
            }

        val shape =
            when (suit) {
                Suit.CLUBS -> CLOVER
                Suit.DIAMONDS -> DIAMOND
                Suit.HEARTS -> HEART
                Suit.SPADES -> SPADE
            }
        return "$number$shape"
    }

    companion object {
        const val CLOVER = "클로버"
        const val DIAMOND = "다이아몬드"
        const val HEART = "하트"
        const val SPADE = "스페이드"
        const val JACK = "J"
        const val QUEEN = "Q"
        const val KING = "K"
        const val ACE = "A"
    }
}
