package blackjack.model

class CardDeck(cards: List<Card>) {
    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    fun pick(): Card = _cards.removeLast()
}
