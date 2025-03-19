package blackjack.model

data class Card(
    val suit: Suit,
    val denomination: Denomination,
) {
    fun isAce(): Boolean = denomination == Denomination.ACE

    companion object {
        private val denominations: List<Denomination> = Denomination.entries
        private val suits: List<Suit> = Suit.entries

        val wholeCards: List<Card> =
            denominations
                .flatMap { denomination ->
                    suits.map { shape ->
                        Card(shape, denomination)
                    }
                }.shuffled()
    }
}
