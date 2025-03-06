package blackjack

class Dealer {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards get() = _cards.toList()
    val name = DEALER_NAME

    fun addCards(cards: List<Card>) = _cards.addAll(cards)

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
