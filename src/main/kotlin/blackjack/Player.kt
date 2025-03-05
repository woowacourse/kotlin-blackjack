package blackjack

class Player(
    val name: String,
) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards get() = _cards.toList()

    fun addCard(card: Card) = _cards.add(card)
}
