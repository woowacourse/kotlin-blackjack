package blackjack.model

class Player(val name: String, handCards: HandCards) {
    private val _handCards: MutableList<Card> = handCards.cards.toMutableList()
    val handCards: HandCards get() = HandCards(_handCards.toList())

    constructor(pair: Pair<String, HandCards>) : this(pair.first, pair.second)

    fun hit(card: Card) {
        _handCards.add(card)
    }
}
