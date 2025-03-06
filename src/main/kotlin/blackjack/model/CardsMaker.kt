package blackjack.model

object CardsMaker {
    private val DENOMINATIONS: List<Denomination> = Denomination.entries
    private val SHAPES: List<CardShape> = CardShape.entries

    val CARDS: List<Card> =
        DENOMINATIONS
            .flatMap { denomination ->
                SHAPES.map { shape ->
                    Card(shape, denomination.title)
                }
            }.shuffled()
}
