package blackjack.domain.card

class Cards() {

    private val _cards: MutableList<Card> = mutableListOf()
    val values: List<Card>
        get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun sumCardsNumber(): Int {
        if (isBlackjack()) return MAX_SUM_NUMBER
        val result = _cards.sumOf { it.number.value }
        return result
    }

    private fun isBlackjack(): Boolean {
        if (_cards.size != 2) return false

        val condition1 = _cards.filter { it.number == CardNumber.ONE }.size
        val condition2 = _cards.filter {
            it.number == CardNumber.KING ||
                it.number == CardNumber.QUEEN ||
                it.number == CardNumber.JACK
        }.size
        if (condition1 == 1 && condition2 == 1) return true

        return false
    }

    companion object {
        const val MAX_SUM_NUMBER = 21
    }
}
