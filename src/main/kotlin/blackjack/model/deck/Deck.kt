package blackjack.model.deck

class Deck {
    private val cards: MutableList<Card>

    init {
        cards = createCards().toMutableList()
    }

    private fun createCards(): List<Card> {
        val cards: List<Card> =
            Pattern.entries.flatMap { pattern: Pattern ->
                assignNumber(pattern)
            }
        return CardMachineManager.shuffle(cards)
    }

    private fun assignNumber(pattern: Pattern): List<Card> =
        CardNumber.entries.map { cardNumber: CardNumber ->
            Card(cardNumber, pattern)
        }

    fun draw(cardAmount: Int): List<Card> {
        val drawCard = cards.take(cardAmount)
        cards.removeAll(drawCard)
        return drawCard
    }
}
