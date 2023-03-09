package blackjack.domain.card

class Deck {
    private val cards: MutableList<Card> =
        List(DECK_COUNT) { createCardPack() }.flatten().shuffled().toMutableList()

    fun draw(): Card = cards.removeAt(0)

    private fun createCardPack(): List<Card> =
        CardNumber.values().flatMap { number -> cardShapesMap(number) }.toList()

    private fun cardShapesMap(number: CardNumber): List<Card> =
        CardShape.values().map { shape -> Card.from(Card(number, shape)) }.toList()

    companion object {
        private const val DECK_COUNT = 6
    }
}
