package blackjack.model.domain

import blackjack.model.strategy.CardShuffler
import java.util.ArrayDeque

class Deck(cardShuffler: CardShuffler) {
    private val deck: ArrayDeque<Card> = initCard(cardShuffler)

    fun spreadCard(): Card {
        return deck.pop()
    }

    companion object {
        private val symbols = Shape.entries
        private val cardNumbers = CardNumber.entries

        private fun initCard(cardShuffler: CardShuffler): ArrayDeque<Card> {
            val card = symbols.flatMap { symbol -> cardNumbers.map { cardNumber -> Card(symbol, cardNumber) } }.toMutableList()
            return ArrayDeque(cardShuffler.spread(card))
        }
    }
}
