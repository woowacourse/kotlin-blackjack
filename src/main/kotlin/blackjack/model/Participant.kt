package blackjack.model

abstract class Participant(val name: String) {
    private val cardList: MutableList<Card> = mutableListOf()

    fun addCard(card: Card) {
        cardList.add(card)
    }

    fun showCard(): List<Card> = cardList.toList()

    fun getCardSum(): Int {
        val sum = cardList.sumOf { it.cardNumber.score }
        val additionalAceScore =
            if (hasAce() && sum <= ACE_ADDITION_THRESHOLD) UPPER_ACE_ADDITION else LOWER_ACE_ADDITION
        return sum + additionalAceScore
    }

    private fun hasAce() = cardList.any { it.cardNumber == CardNumber.A }

    fun isBusted(): Boolean {
        return MAX_SCORE < getCardSum()
    }

    fun isMaxScore(): Boolean {
        return MAX_SCORE == getCardSum()
    }

    fun isBlackJack(): Boolean = cardList.size == INITIAL_CARD_SIZE && isMaxScore()

    abstract fun isHitable(): Boolean

    companion object {
        const val ACE_ADDITION_THRESHOLD = 11
        const val UPPER_ACE_ADDITION = 10
        const val LOWER_ACE_ADDITION = 0

        @JvmStatic
        protected val MAX_SCORE = 21
        const val INITIAL_CARD_SIZE = 2
    }
}