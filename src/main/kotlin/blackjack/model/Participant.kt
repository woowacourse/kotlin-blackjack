package blackjack.model

abstract class Participant(val name: String) {
    private val cardList: MutableList<Card> = mutableListOf()

    fun addCard(card: Card) {
        cardList.add(card)
    }

    fun showCard(): List<Card> = cardList.toList()

    fun getCardSum(): Int {
        val sum = cardList.sumOf { it.cardNumber.score }
        val additionalScore = if (hasAce() && sum <= 11) 10 else 0
        return sum + additionalScore
    }

    private fun hasAce() = cardList.any { it.cardNumber == CardNumber.A }

    fun isBusted(): Boolean {
        val score = getCardSum()
        val threshold = 21
        return threshold < score
    }

    fun isMaxScore(): Boolean {
        val score = getCardSum()
        val threshold = 21
        return threshold == score
    }

    fun isBlackJack(): Boolean = cardList.size == 2 && isMaxScore()

    abstract fun isHitable(): Boolean
}
