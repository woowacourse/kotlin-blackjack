package blackjack.model

class Cards(
    value: List<Card>,
) {
    private val _value: MutableList<Card> = value.toMutableList()
    val value: List<Card> get() = _value.map { card -> card.copy() }

    var status: CardsStatus = CardsStatus.from(cardsScore = calculateScore(), firstTurn = true)
        private set

    fun add(card: Card) {
        _value.add(card)
        status = CardsStatus.from(cardsScore = calculateScore(), firstTurn = false)
    }

    fun calculateScore(): Int {
        var score: Int = value.sumOf { card -> card.denomination.number }
        if (value.any { card -> card.isAce() } && score + 10 <= 21) {
            score += 10
        }
        return score
    }

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
