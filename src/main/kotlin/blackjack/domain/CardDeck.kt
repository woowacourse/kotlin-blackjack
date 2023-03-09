package blackjack.domain

class CardDeck(deck: List<Card> = Card.all().shuffled()) {
    constructor(vararg cards: Card) : this(cards.toList())

    private val deck: MutableList<Card> = deck.toMutableList()

    fun draw(): Card = deck.removeFirst()
}
