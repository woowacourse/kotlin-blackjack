package blackjack.domain

import blackjack.domain.Score.Companion.SCORE_BLACKJACK
import blackjack.domain.card.Card

class Hand(
    vararg initialCards: Card,
) {
    var cards: List<Card> = initialCards.toList()
        private set

    val size: Int get() = cards.size

    val score: Score
        get() {
            val possibleScores: Set<Int> = ScoreCalculator.possibleScoreOf(*(cards.toTypedArray()))
            if (!possibleScores.hasHittableScore()) return Score(possibleScores.min())
            return Score(possibleScores.filter { score: Int -> score <= SCORE_BLACKJACK }.max())
        }

    fun draw(card: Card) {
        cards = cards + card
    }

    private fun Set<Int>.hasHittableScore(): Boolean = any { score: Int -> score <= SCORE_BLACKJACK }
}
