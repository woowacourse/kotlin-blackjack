package blackjack.model

class Cards {
    private val cards: MutableList<Card> = mutableListOf()
    val size: Int
        get() = cards.size

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun showCard(): List<Card> = cards.toList()

    fun sum(): Int {
        val sum = cards.sumOf { it.cardNumber.score }
        val additionalScore = if (hasAce() && sum <= 11) 10 else 0
        return sum + additionalScore
    }

    private fun hasAce() = cards.any { it.cardNumber == CardNumber.A }
}
