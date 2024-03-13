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
            val tempSum = totalSum + CardNumber.ACE_VALUE_INCREMENT
            if (tempSum <= THRESHOLD_BUST) totalSum = tempSum else return totalSum
        }
        return totalSum
    }

    fun calculateState(): State {
        return when {
            calculateCardsSum() == THRESHOLD_BLACKJACK && cards.size == BLACKJACK_CARD_SIZE -> Blackjack()
            calculateCardsSum() > THRESHOLD_BUST -> Bust()
            else -> Normal()
        }
    }

    companion object {
        private const val BLACKJACK_CARD_SIZE = 2
        private const val THRESHOLD_BUST = 21
        private const val THRESHOLD_BLACKJACK = 21
    }
}
