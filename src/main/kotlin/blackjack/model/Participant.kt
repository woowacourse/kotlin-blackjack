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

    private fun hasAce() = cardList.filter { it.cardNumber == CardNumber.A }.isNotEmpty()

    fun isBusted(): Boolean {
        val score = getCardSum()
        val threshold = 21
        return threshold < score
    }

    fun isBlackJack(): Boolean {
        val score = getCardSum()
        val threshold = 21
        return threshold == score
    }

    abstract fun isHitable(): Boolean
}
