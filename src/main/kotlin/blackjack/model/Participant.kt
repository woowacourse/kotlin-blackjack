package blackjack.model

open class Participant {
    private val cardList: MutableList<Card> = mutableListOf()

    fun addCard(card: Card) {
        cardList.add(card)
    }

    fun getCardSum(): Int {
        val sum = cardList.sumOf { it.cardNumber.score }
        val additionalScore = if (hasAce() && sum <= 11) 10 else 0
        return sum + additionalScore
    }

    private fun hasAce() = cardList.filter { it.cardNumber == CardNumber.ACE }.isNotEmpty()

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
}
