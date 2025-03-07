package blackjack.model

import blackjack.model.ResultType.Companion.BUST_NUMBER

class Player(
    val name: String,
) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards get() = _cards.toList()

    fun addCard(card: Card) = _cards.add(card)

    fun addCards(cards: List<Card>) = _cards.addAll(cards)

    fun adjustScore(): Int {
        var sumScore = calculateTotalScore()
        var countAce = countAce()
        while (countAce-- > 0) {
            if (sumScore > BUST_NUMBER) {
                sumScore -= ADJUST_ACE_NUMBER
            }
        }
        return sumScore
    }

    fun calculateTotalScore() = cards.sumOf { card -> card.number.score }

    fun isBust() = adjustScore() > BUST_NUMBER

    fun countAce() = _cards.count { it.number == Number.ACE }

    companion object{
        private const val ADJUST_ACE_NUMBER = 10
    }
}
