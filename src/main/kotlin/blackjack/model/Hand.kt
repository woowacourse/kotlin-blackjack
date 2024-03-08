package blackjack.model

class Hand(cards: List<Card>) {
    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun calculateSum(): Int {
        val sumWithoutAces = cards.filterNot { it.number == CardNumber.ACE }.sumOf { it.number.value }
        val acesCount = cards.count { it.number == CardNumber.ACE }
        var totalSum = sumWithoutAces + acesCount

        repeat(acesCount) {
            val tempSum = totalSum + ACE_OPTIMIZED_VALUE
            if (tempSum <= State.THRESHOLD_BUST) totalSum = tempSum else return totalSum
        }
        return totalSum
    }

    companion object {
        const val ACE_OPTIMIZED_VALUE = 10
    }
}
