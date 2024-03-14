package blackjack.model

class Hand(private val cards: MutableList<Card>) {
    fun getCards() = cards

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun calculateCardsSum(): Int {
        val sumWithoutAces = cards.filterNot { it.number == CardNumber.ACE }.sumOf { it.number.value }
        val acesCount = cards.count { it.number == CardNumber.ACE }
        var totalSum = sumWithoutAces + acesCount
        repeat(acesCount) {
            val tempSum = totalSum + ACE_VALUE_INCREMENT
            if (tempSum <= Participant.THRESHOLD_BUST) totalSum = tempSum else return totalSum
        }
        return totalSum
    }

    companion object {
        const val ACE_VALUE_INCREMENT = 10
    }
}
