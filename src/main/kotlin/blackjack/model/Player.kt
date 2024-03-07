package blackjack.model

class Player(val name: String, handCards: HandCards) {
    private val _handCards: MutableList<Card> = handCards.cards.toMutableList()
    val handCards: HandCards get() = HandCards(_handCards.toList())

    fun hit(card: Card) {
        _handCards.add(card)
    }
}
