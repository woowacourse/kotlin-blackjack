package domain

class Dealer(private val _cards: MutableList<Card>) {
    val cards: List<Card> get() = _cards.toList()

    fun calculateCardValueSum(): Int = _cards.sumOf { Card.valueOf(it) } + addAce()

    fun addAce(): Int = when (countAce()) {
        1 -> 10
        2 -> 10
        else -> 0
    }

    fun pickNewCard() = _cards.add(CardMachine.getNewCard())

    fun isOverSumCondition(): Boolean = (calculateCardValueSum() > SUM_CONDITION)
    private fun countAce(): Int = _cards.filter { it.value == Card.Value.ACE }.size

    companion object {
        private const val SUM_CONDITION = 16
    }
}
