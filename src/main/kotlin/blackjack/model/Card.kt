package blackjack.model

data class Card(
    val shape: Suit,
    val denomination: Denomination,
) {
    fun isAce(): Boolean = denomination == Denomination.ACE

    companion object {
        private val denominations: List<Denomination> = Denomination.entries
        private val suits: List<Suit> = Suit.entries

        val whole_cards: List<Card> =
            denominations
                .flatMap { denomination ->
                    suits.map { shape ->
                        Card(shape, denomination)
                    }
                }.shuffled()
    }
}
