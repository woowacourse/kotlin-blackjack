package model.domain.card

class Deck private constructor(givenCards: List<Card>) {
    private val _cards: MutableList<Card> = givenCards.toMutableList()
    val cards: List<Card> get() = _cards.toList()
    fun getCard(): Card = _cards.removeFirst()

    companion object {
        fun create(cards: List<Card>): Deck = Deck(cards)
    }
}
