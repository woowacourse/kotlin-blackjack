package blackjack.domain

data class Card(val number: CardNumber, val suit: Suit) {
    fun getScore(): Int = number.score

    fun isAce(): Boolean = number == CardNumber.ACE

    companion object {
        private val CARDS = Suit.values().flatMap { suit ->
            CardNumber.values().map { cardNumber ->
                Card(cardNumber, suit)
            }
        }

        fun all(): List<Card> = CARDS.toList()
    }
}
