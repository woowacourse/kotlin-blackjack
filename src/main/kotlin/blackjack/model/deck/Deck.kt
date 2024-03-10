package blackjack.model.deck

import blackjack.util.CardNumber
import blackjack.util.Shape

class Deck(private val cardMachine: CardMachine = ShuffleCardMachine()) {
    private val cards: MutableList<Card>

    init {
        cards = createCards().toMutableList()
    }

    private fun createCards(): List<Card> {
        val cards: List<Card> =
            Shape.entries.flatMap { pattern ->
                assignNumber(pattern)
            }
        return cardMachine.handle(cards)
    }

    private fun assignNumber(shape: Shape) =
        CardNumber.entries.map { cardNumber ->
            Card(cardNumber, shape)
        }

    fun draw(cardAmount: Int): List<Card> {
        val drawCard = cards.take(cardAmount)
        cards.removeAll(drawCard)
        return drawCard
    }
}
