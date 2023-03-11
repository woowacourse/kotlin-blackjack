package blackjack.domain.card

class MultiDeck private constructor(
    cards: List<Card> = listOf()
) {
    constructor(vararg cards: Pair<CardNumber, CardShape>) : this(cards.map { Card.from(it.first, it.second) }.toList())

    private val _cards: MutableList<Card> =
        if (cards.isEmpty()) List(DECK_COUNT) { createCardPack() }.flatten().shuffled().toMutableList()
        else cards.toMutableList()

    fun draw(): Card = _cards.removeAt(0)

    private fun createCardPack(): List<Card> =
        CardNumber.values().flatMap { number -> cardShapesMap(number) }.toList()

    private fun cardShapesMap(number: CardNumber): List<Card> =
        CardShape.values().map { shape -> Card.from(number, shape) }.toList()

    companion object {
        private const val DECK_COUNT = 6
    }
}
