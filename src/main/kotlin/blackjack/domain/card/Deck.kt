package blackjack.domain.card

class Deck private constructor(cards: List<Card>) {
    private val _cards = cards.toMutableList()

    fun draw(): Card {
        return _cards.removeFirst()
    }

    companion object {
        fun create() = Deck(DeckMaker.getDeck())
    }
}
