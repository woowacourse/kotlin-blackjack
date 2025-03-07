package blackjack.model

class CardDeck(
    private val cards: List<Card>,
) {
    private var index = 0

    fun pickCard(): Card = cards[index++]
}
