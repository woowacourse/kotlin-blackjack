package domain.gamer

import domain.card.Card
import domain.gamer.Score.Companion.ACE_SUBTRACT_VALUE
import domain.gamer.Score.Companion.BLACKJACK_SCORE


class Hand(private val cards: MutableList<Card> = mutableListOf()) : List<Card> by cards {

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun totalScore(): Int {
        var tempSum = cards.map { it.score() }
            .reduce { total, num -> total + num }
        val aceCount = aceCount()

        repeat(aceCount) {
            if (tempSum <= BLACKJACK_SCORE) {
                return tempSum.value
            }
            tempSum -= ACE_SUBTRACT_VALUE
        }
        return tempSum.value
    }

    private fun aceCount() = cards.filter { it.isAce() }
        .count()

    fun isBust(): Boolean {
        return Score(totalScore()) > BLACKJACK_SCORE
    }
}