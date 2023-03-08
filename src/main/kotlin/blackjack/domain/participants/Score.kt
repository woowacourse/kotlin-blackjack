package blackjack.domain.participants

import blackjack.domain.card.Cards

class Score(cards: Cards) {

    private val minScore: Int = cards.toList().sumOf { it.value.value }

    val maxScore: Int =
        if (cards.isContainsAce && isMaxNumberBust.not()) {
            minScore + ACE_OTHER_NUMBER_DIFF
        } else {
            minScore
        }

    val score: Int
        get() = if (isMaxScoreInRange) maxScore else minScore

    val isBlackJackNumber: Boolean
        get() = score == BLACKJACK_NUMBER

    val isOverBlackJackNumber: Boolean
        get() = score > BLACKJACK_NUMBER

    private val isMaxNumberBust: Boolean
        get() = minScore + ACE_OTHER_NUMBER_DIFF > BLACKJACK_NUMBER

    private val isMaxScoreInRange: Boolean
        get() = maxScore <= BLACKJACK_NUMBER

    companion object {
        private const val ACE_OTHER_NUMBER_DIFF = 10
        private const val BLACKJACK_NUMBER = 21
    }
}
