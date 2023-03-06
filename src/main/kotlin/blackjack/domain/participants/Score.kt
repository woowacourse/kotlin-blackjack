package blackjack.domain.participants

import blackjack.domain.card.Cards

class Score(cards: Cards) {

    private val minScore: Int = cards.toList().sumOf { it.value.value }

    val maxScore: Int =
        minScore + if (cards.isContainsACE() && isMaxNumberBust().not()) ACE_OTHER_NUMBER_DIFF else 0

    fun score(): Int = if (isMaxScoreInRange()) maxScore else minScore

    private fun isMaxNumberBust(): Boolean = minScore + ACE_OTHER_NUMBER_DIFF > BLACKJACK_NUMBER

    private fun isMaxScoreInRange(): Boolean = maxScore <= BLACKJACK_NUMBER

    companion object {
        private const val ACE_OTHER_NUMBER_DIFF = 10
        private const val BLACKJACK_NUMBER = 21
    }
}
