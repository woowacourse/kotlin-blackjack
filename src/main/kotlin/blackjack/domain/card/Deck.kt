package blackjack.domain.card

class Deck private constructor(cards: List<Card>) {
    private val _cards = cards.toMutableList()

    fun draw(): Card {
        if (_cards.isEmpty()) {
            _cards.addAll(DeckMaker.getDeck())
        }
        return _cards.removeFirst()
    }

    companion object {
        fun create() = Deck(DeckMaker.getDeck())
    }
}
