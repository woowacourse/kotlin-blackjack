package domain.participant

import domain.card.Card
import domain.card.Value

private const val BLACKJACK_SCORE: Int = 21
private const val ACE_SUBTRACT_VALUE = 10

class Hand(private val cards: MutableList<Card> = mutableListOf()) : List<Card> by cards {

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getScore(): Int {
        var tmpSum = cards.sumOf { it.value.score }
        val aceCount = cards.filter { it.value == Value.ACE }
            .count()

        repeat(aceCount) {
            if (tmpSum <= BLACKJACK_SCORE) {
                return tmpSum
            }
            tmpSum -= ACE_SUBTRACT_VALUE
        }
        return tmpSum
    }

    fun isBust(): Boolean {
        return getScore() > BLACKJACK_SCORE
    }

    fun isBlackJackScore(): Boolean {
        return getScore() == BLACKJACK_SCORE
    }

    operator fun minus(other: Hand): Int {
        return this.scoreForResult() - other.scoreForResult()
    }
}


private fun Hand.scoreForResult(): Int {
    if (this.isBust()) {
        return -1
    }
    return this.getScore()
}
