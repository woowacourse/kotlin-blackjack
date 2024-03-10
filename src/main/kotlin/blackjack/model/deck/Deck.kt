package blackjack.model.deck

import blackjack.util.CardNumber
import blackjack.util.Pattern

class Deck {
    private val cards: MutableList<Card>

    init {
        cards = createCards().toMutableList()
    }

    private fun createCards(): List<Card> {
        val cards: List<Card> =
            Pattern.entries.flatMap { pattern ->
                assignNumber(pattern)
            }
        return CardMachineManager.handle(cards)
    }

    private fun assignNumber(pattern: Pattern) =
        CardNumber.entries.map { cardNumber ->
            Card(cardNumber, pattern)
        }

    fun draw(cardAmount: Int): List<Card> {
        val drawCard = cards.take(cardAmount)
        cards.removeAll(drawCard)
        return drawCard
    }
}
