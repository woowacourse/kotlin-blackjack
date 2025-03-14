package blackjack.model

data class Card(
    val shape: CardShape,
    val denomination: Denomination,
) {
    fun isAce(): Boolean = denomination == Denomination.ACE

    companion object {
        private val DENOMINATIONS: List<Denomination> = Denomination.entries
        private val SHAPES: List<CardShape> = CardShape.entries

        val WHOLE_CARDS: List<Card> =
            DENOMINATIONS
                .flatMap { denomination ->
                    SHAPES.map { shape ->
                        Card(shape, denomination)
                    }
                }.shuffled()
    }
}
