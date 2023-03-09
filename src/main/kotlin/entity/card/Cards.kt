package entity.card

import misc.GameRule

class Cards(value: List<Card>) {
    private val _value: MutableList<Card> = value.toMutableList()
    val value: List<Card>
        get() = _value.toList()

    fun sumOfNumbers(): Int {
        var sumNumber = value.filter { it.cardNumber != CardNumber.ACE }.sumOf { it.cardNumber.value }
        sumNumber += value.filter { it.cardNumber == CardNumber.ACE }.sumOf { judgeAce(sumNumber) }
        return sumNumber
    }

    fun judgeAce(sumOfCards: Int): Int {
        return if (sumOfCards + 11 > GameRule.WINNING_NUMBER) ACE_ONE
        else ACE_ELEVEN
    }

    fun addCards(cards: Cards) {
        cards.value.forEach { _value.add(it) }
    }

    companion object {
        private const val ACE_ONE = 1
        private const val ACE_ELEVEN = 11
    }
}
