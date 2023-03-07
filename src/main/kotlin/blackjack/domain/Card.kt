package blackjack.domain

data class Card(private val suit: Suit, private val number: CardNumber) {
    fun getScore(): Int = number.score

    fun isAce(): Boolean = number == CardNumber.ACE

    companion object {
        private val CARDS = Suit.values().flatMap { suit ->
            CardNumber.values().map { cardNumber ->
                Card(suit, cardNumber)
            }
        }

        fun all(): List<Card> = CARDS.toList()
    }
}
