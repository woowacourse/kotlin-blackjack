package model

class Hand {
    private var _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = _cards

    fun hit(deck: Deck) {
        _cards.add(deck.pop())
    }

    fun getPoint(): Int {
        return _cards.sumOf { card ->
            card.value.amount
        }
    }
}
