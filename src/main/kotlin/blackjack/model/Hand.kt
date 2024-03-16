package blackjack.model

class Hand {
    private val cardList: MutableList<Card> = mutableListOf()

    fun pickCard(
        dealingShoe: DealingShoe,
        repeatSize: Int = 1,
    ) {
        repeat(repeatSize) {
            addCard(dealingShoe.pickCard())
        }
    }

    private fun addCard(card: Card) {
        cardList.add(card)
    }

    fun isBlackJack(): Boolean = cardList.size == INITIAL_CARD_SIZE && isMaxScore()

    fun isBusted(): Boolean {
        return MAX_SCORE < getCardSum()
    }

    fun isMaxScore(): Boolean {
        return MAX_SCORE == getCardSum()
    }

    fun showCard(): List<Card> = cardList.toList()

    fun getCardSum(): Int {
        val sum = cardList.sumOf { it.cardNumber.score }
        val additionalAceScore =
            if (hasAce() && sum <= ACE_ADDITION_THRESHOLD) UPPER_ACE_ADDITION else LOWER_ACE_ADDITION
        return sum + additionalAceScore
    }

    private fun hasAce() = cardList.any { it.cardNumber == CardNumber.ACE }

    companion object {
        const val INITIAL_CARD_SIZE = 2
        const val MAX_SCORE = 21
        const val ACE_ADDITION_THRESHOLD = 11
        const val UPPER_ACE_ADDITION = 10
        const val LOWER_ACE_ADDITION = 0
    }
}
