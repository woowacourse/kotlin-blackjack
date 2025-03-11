package blackjack.model.domain.card

class Hand(val cards: MutableList<Card>) {
    fun getSumNumber(): Int {
        var sum = cards.sumOf { it.cardNumber.number }
        val haveAce: Boolean = CardNumber.Ace in cards.map { it.cardNumber }

        if (haveAce && sum + CardNumber.BONUS_SCORE <= BUST_STANDARD) {
            sum += CardNumber.BONUS_SCORE
        }

        return sum
    }

    fun append(card: Card) {
        cards.add(card)
    }

    companion object {
        const val BUST_STANDARD: Int = 21
    }
}
