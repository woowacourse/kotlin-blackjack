package blackjack.domain.card

class Cards(_cards: List<Card> = listOf()) {

    private val _cards: MutableList<Card> = _cards.toMutableList()
    val values: List<Card>
        get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun sumCardsNumber(): Int {
        if (checkHavingAce()) return calculateAceSum()
        return _cards.sumOf { it.number.value }
    }

    fun isBlackjack(): Boolean = ((_cards.size == 2) and (sumCardsNumber() == MAX_SUM_NUMBER))

    fun isBurst(): Boolean = (sumCardsNumber() > MAX_SUM_NUMBER)

    private fun checkHavingAce(): Boolean = _cards.any { it.isAce() }

    private fun calculateAceSum(): Int {
        var result = _cards.sumOf { it.number.value }
        if ((MAX_SUM_NUMBER - result) >= 10) result += 10
        return result
    }

    companion object {
        const val MAX_SUM_NUMBER = 21
    }
}
