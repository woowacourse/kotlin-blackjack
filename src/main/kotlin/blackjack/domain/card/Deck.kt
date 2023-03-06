package blackjack.domain.card

class Deck {
    private lateinit var cards: MutableList<Card>

    init {
        setCards()
    }

    fun draw(): Card = cards.removeAt(0)

    private fun setCards() {
        cards = createCards()
        repeat(DECK_COUNT) { cards += cards }
        cards = cards.shuffled().toMutableList()
    }

    private fun createCards(): MutableList<Card> =
        CardNumber.values().flatMap { number -> cardShapesMap(number) }.toMutableList()

    private fun cardShapesMap(number: CardNumber): MutableList<Card> =
        CardShape.values().map { shape -> Card(number, shape) }.toMutableList()

    companion object {
        private const val DECK_COUNT = 6
    }
}
