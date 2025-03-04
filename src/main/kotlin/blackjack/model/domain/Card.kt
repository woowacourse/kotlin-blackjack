package blackjack.model.domain

import blackjack.model.strategy.CardShuffler

data class Card(
    val symbol: Shape,
    val number: CardNumber,
) {
    companion object {
        private val symbols = Shape.entries
        private val cardNumbers = CardNumber.entries

        fun initCard(cardShuffler: CardShuffler): Deck {
            val card = symbols.flatMap { symbol -> cardNumbers.map { cardNumber -> Card(symbol, cardNumber) } }.toMutableList()
            return Deck(cardShuffler.spread(card).toMutableList())
        }
    }
}
