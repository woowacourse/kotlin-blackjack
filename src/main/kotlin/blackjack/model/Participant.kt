package blackjack.model

open class Participant {
    private val cardList: MutableList<Card> = mutableListOf()
    fun addCard(card: Card) {
        cardList.add(card)
    }

    fun getCardSum(): Int {
        val sum = cardList.map { it.cardNumber.score }.sum()
        val additionalScore = if (hasAce() && sum <= 11) 10 else 0
        return sum + additionalScore
    }

    private fun hasAce() = cardList.filter { it.cardNumber == CardNumber.ACE }.isNotEmpty()
}

