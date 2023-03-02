package blackjack.domain

class Cards(cards: List<Card>) {

    private val _cards: MutableList<Card> = cards.toMutableList()
    val values: List<Card>
        get() = _cards.toList()

    init {
        checkCardInitSize()
    }

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun sumCardsNumber(): Int {
        var result = 0
        _cards.forEach {
            result += it.number.value
        }
        return result
    }

    private fun checkCardInitSize() {
        require(_cards.size == CARDS_INITIAL_SIZE) { ERROR_CARDS_INITIAL_SIZE }
    }

    companion object {
        const val CARDS_INITIAL_SIZE = 2
        const val ERROR_CARDS_INITIAL_SIZE = "처음 받는 카드는 두 장이어야 합니다"
    }
}
