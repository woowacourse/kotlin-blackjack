package blackjack

class Dealer {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards get() = _cards.toList()
    val name = DEALER_NAME

    fun addCard(card: Card) = _cards.add(card)

    fun addCards(cards: List<Card>) = _cards.addAll(cards)

    fun sumScore() = cards.sumOf { card -> card.number.score }

    fun isMoreCard() = sumScore() < 17

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
