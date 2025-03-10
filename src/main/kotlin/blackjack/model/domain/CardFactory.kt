package blackjack.model.domain

import blackjack.model.domain.card.Card
import blackjack.model.domain.card.CardNumber
import blackjack.model.domain.card.Shape

class CardFactory {
    fun makeCard(): ArrayDeque<Card> {
        val cards = symbols.flatMap { symbol -> cardNumbers.map { cardNumber -> Card(symbol, cardNumber) } }.toMutableList()
        return ArrayDeque(cards.shuffled())
    }

    companion object {
        val symbols = Shape.entries
        val cardNumbers = CardNumber.entries
    }
}
