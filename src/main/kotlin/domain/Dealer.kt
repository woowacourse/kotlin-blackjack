package domain

class Dealer(private val _cards: MutableList<Card>) {
    val cards: List<Card> get() = _cards.toList()

    fun calculateCardValueSum(): Int = _cards.sumOf { Card.valueOf(it) }

    fun pickNewCard() = _cards.add(CardMachine.getNewCard())

    fun isOverSumCondition(): Boolean = (calculateCardValueSum() > SUM_CONDITION)

    companion object {
        private const val SUM_CONDITION = 16
    }
}
