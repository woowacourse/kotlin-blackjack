package blackjack.model.card

class Card(val denomination: Denomination, val suit: Suit) {
    override fun toString(): String {
        val number =
            when (denomination) {
                Denomination.JACK -> "J"
                Denomination.QUEEN -> "Q"
                Denomination.KING -> "K"
                Denomination.ACE -> "A"
                else -> denomination.score
            }

        val shape =
            when (suit) {
                Suit.CLUBS -> "클로버"
                Suit.DIAMONDS -> "다이아몬드"
                Suit.HEARTS -> "하트"
                Suit.SPADES -> "스페이드"
            }
        return "${number}$shape"
    }
}
