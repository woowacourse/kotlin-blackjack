package blackjack.domain.card

class Cards(_cards: List<Card> = listOf()) {

    private val _cards: MutableList<Card> = _cards.toMutableList()
    val values: List<Card>
        get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun sumCardsNumber(): Int {
        if (isBlackjack()) return MAX_SUM_NUMBER
        if (_cards.any { it.number == CardNumber.ACE }) return calculateAceSum()
        return _cards.sumOf { it.number.value }
    }

    private fun isBlackjack(): Boolean {
        if (_cards.size != 2) return false

        val condition1 = _cards.filter { it.number == CardNumber.ACE }.size
        val condition2 = _cards.filter {
            listOf(CardNumber.KING, CardNumber.QUEEN, CardNumber.JACK).contains(it.number)
        }.size

        if (condition1 == 1 && condition2 == 1) return true
        return false
    }

    private fun calculateAceSum(): Int {
        var result = _cards.sumOf { it.number.value }
        if ((MAX_SUM_NUMBER - result) >= 10) result += 10
        return result
    }

    companion object {
        const val MAX_SUM_NUMBER = 21
    }
}
