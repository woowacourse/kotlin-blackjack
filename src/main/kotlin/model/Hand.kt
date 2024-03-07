package model

class Hand(private val deck: Deck = TrumpDeck) {
    private var _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = _cards

    fun draw() {
        _cards.add(deck.pop())
    }

    fun getPoint(): Point {
        return _cards.sumOf { card ->
            card.value.amount
        }.run { Point(this) }
    }
}
