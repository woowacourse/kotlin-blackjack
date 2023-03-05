package entity

import model.NumberStrategy

class Cards(value: List<Card>) {
    private val _value: MutableList<Card> = value.toMutableList()
    val value: List<Card>
        get() = _value.toList()

    fun sumOfNumbers(): Int {
        var sumNumber = value.filter { it.cardNumber != CardNumber.ACE }.sumOf { it.cardNumber.value }
        sumNumber += value.filter { it.cardNumber == CardNumber.ACE }.sumOf { NumberStrategy().judgeAce(sumNumber) }
        return sumNumber
    }

    fun addCards(cards: Cards) {
        cards.value.forEach { _value.add(it) }
    }
}
