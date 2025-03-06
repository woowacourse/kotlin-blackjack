package blackjack

class Player(
    val name: String,
) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards get() = _cards.toList()

    fun addCards(cards: List<Card>) = _cards.addAll(cards)

    fun sumScore() = cards.sumOf { card -> card.number.score }
}
