package blackjack.domain.gamer

import blackjack.domain.card.Card
import blackjack.domain.gamer.Score.Companion.ACE_SUBTRACT_VALUE
import blackjack.domain.gamer.Score.Companion.BLACKJACK_SCORE

class Hand(cards: MutableList<Card> = mutableListOf()) {
    private val _cards : MutableList<Card> = cards.toMutableList()
    val cards: List<Card>
    get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun totalScore(): Score {
        var tempSum = _cards.map { it.score() }
            .reduce(Score::plus)
        val aceCount = aceCount()

        repeat(aceCount) {
            if (tempSum <= BLACKJACK_SCORE) {
                return tempSum
            }
            tempSum -= ACE_SUBTRACT_VALUE
        }
        return tempSum
    }

    private fun aceCount() = _cards.filter { it.isAce() }
        .count()

    fun isBust(): Boolean {
        return totalScore() > BLACKJACK_SCORE
    }

    fun isBlackjack(): Boolean {
        return totalScore() == BLACKJACK_SCORE
    }
}
