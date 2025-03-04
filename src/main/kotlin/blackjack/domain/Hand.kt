package blackjack.domain

class Hand {
    private val _cards: MutableList<Card> = mutableListOf()

    val cards: List<Card>
        get() = _cards.toList()

    fun addCard(cards: List<Card>) {
        _cards.addAll(cards)
    }
}
