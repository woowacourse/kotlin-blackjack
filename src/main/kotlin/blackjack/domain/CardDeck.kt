package blackjack.domain

class CardDeck(private val _cards: List<Card>) {
    private val cards = _cards.toMutableList()

    fun drawCard(): Card {
        return cards.removeFirstOrNull() ?: throw IllegalArgumentException()
    }

    fun drawTwoCards(): MutableList<Card> {
        return mutableListOf(drawCard(), drawCard())
    }
}
