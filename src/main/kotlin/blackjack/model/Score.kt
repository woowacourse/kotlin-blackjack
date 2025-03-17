package blackjack.model

import blackjack.model.card.Card
import blackjack.model.card.CardNumber

@JvmInline
value class Score private constructor(
    val number: Int,
) : Comparable<Score> {
    fun isBust(): Boolean = number > BUST_NUMBER

    fun isBlackjackNumber(): Boolean = number == BUST_NUMBER

    fun isDrawableCardByDealer(): Boolean = number < DEALER_DRAW_CARD_MINIMUM_SCORE

    override fun compareTo(other: Score): Int = this.number.compareTo(other.number)

    companion object {
        private const val ADJUST_ACE_NUMBER = 10
        private const val DEALER_DRAW_CARD_MINIMUM_SCORE = 17
        const val BUST_NUMBER = 21

        fun from(number: Int): Score = Score(number)

        fun optimizedSum(cards: List<Card>): Score {
            var totalScore: Score = sum(cards)
            var countOfAce = countOfAce(cards)
            while (countOfAce-- > 0) {
                if (totalScore.number > BUST_NUMBER) totalScore -= from(ADJUST_ACE_NUMBER)
            }
            return totalScore
        }

        private fun sum(cards: List<Card>): Score = cards.fold(from(0)) { acc: Score, card: Card -> acc + card.number.score }

        private fun countOfAce(cards: List<Card>): Int = cards.count { card -> card.number == CardNumber.ACE }

        private operator fun Score.plus(other: Score): Score = Score(this.number + other.number)

        private operator fun Score.minus(other: Score): Score = Score(this.number - other.number)
    }
}
