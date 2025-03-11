package blackjack.model.domain.card

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
