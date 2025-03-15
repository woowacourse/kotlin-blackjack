package blackjack.domain.model.card

class CardFactory {
    fun makeCard(): ArrayDeque<Card> {
        val cards = symbols.flatMap { symbol -> cardNumbers.map { cardNumber -> Card(symbol, cardNumber) } }.toMutableList()
        return ArrayDeque(cards.shuffled())
    }

    companion object {
        val symbols = Suit.entries
        val cardNumbers = Denomination.entries
    }
}
