package blackjack.domain.update

import blackjack.domain.Card
import blackjack.domain.ScoreCalculator
import blackjack.domain.update.NewScore.Companion.SCORE_BLACKJACK

class NewHand(
    vararg initialCards: Card,
) {
    var cards: List<Card> = initialCards.toList()
        private set

    val size: Int get() = cards.size

    val score: NewScore
        get() {
            val possibleScores: Set<Int> = ScoreCalculator.possibleScoreOf(*(cards.toTypedArray()))
            if (!possibleScores.hasHittableScore()) return NewScore(possibleScores.min())
            return NewScore(possibleScores.filter { score: Int -> score <= SCORE_BLACKJACK }.max())
        }

    fun draw(card: Card) {
        cards = cards + card
    }

    private fun Set<Int>.hasHittableScore(): Boolean = any { score: Int -> score <= SCORE_BLACKJACK }
}
